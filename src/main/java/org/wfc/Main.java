package org.wfc;

import java.util.ArrayList;
import java.util.Arrays;

//Wave function collapse algorithm
public class Main {
    static final int WIDTH = 10, HEIGHT = 5;

    static Grid mainGrid;
    static ArrayList<GridSpace> entropyGrid;

    static int totalTiles, collapsedTiles;

    public static void main(String[] args) {
        mainGrid = new Grid(WIDTH, HEIGHT);
        totalTiles = WIDTH * HEIGHT;
        collapsedTiles = 0;

        while (collapsedTiles < totalTiles) {
            sortLowestEntropy();
            collapseLowestEntropy();
            recalculateEntropy();
        }
        mainGrid.print();
    }

    public static void sortLowestEntropy() {
        entropyGrid = new ArrayList<GridSpace>();
        for (int y = 0; y < mainGrid.gridSpaces.length; y++) {
            entropyGrid.addAll(Arrays.asList(mainGrid.gridSpaces[y]));
        }

        entropyGrid.sort((spaceOne, spaceTwo) -> spaceOne.tiles.length - spaceTwo.tiles.length);

        entropyGrid.removeIf(gridSpace -> gridSpace.collapsed || gridSpace.tiles.length == 1);

        int lowestEntropy = entropyGrid.get(0).tiles.length;
        entropyGrid.removeIf(gridSpace -> gridSpace.tiles.length > lowestEntropy);
    }

    public static void collapseLowestEntropy() {
        GridSpace randomGridSpace = entropyGrid.get((int)(Math.random() * entropyGrid.size()));
        Tiles randomTile = randomGridSpace.tiles[(int)(Math.random() * randomGridSpace.tiles.length)];

        mainGrid.collapse(randomGridSpace.indexX, randomGridSpace.indexY, randomTile);
        collapsedTiles++;
    }

    public static void recalculateEntropy() {
        for (int y = 0; y < mainGrid.gridSpaces.length; y++) {
            for (int x = 0; x < mainGrid.gridSpaces[y].length; x++) {
                if (!mainGrid.gridSpaces[y][x].collapsed) {
                    Tiles[] possibleTiles = getPossibleTiles(x, y);
                    mainGrid.gridSpaces[y][x].tiles = possibleTiles;
                }
            }
        }

        for (int y = 0; y < mainGrid.gridSpaces.length; y++) {
            for (int x = 0; x < mainGrid.gridSpaces[y].length; x++) {
                if (!mainGrid.gridSpaces[y][x].collapsed && mainGrid.gridSpaces[y][x].tiles.length == 1) {
                    mainGrid.collapse(x, y, mainGrid.gridSpaces[y][x].tiles[0]);
                    collapsedTiles++;
                    recalculateEntropy();
                    return;
                }
            }
        }
    }

    public static Tiles[] getPossibleTiles(int x, int y) {
        ArrayList<Tiles> upTiles = new ArrayList<>();
        ArrayList<Tiles> downTiles = new ArrayList<>();
        ArrayList<Tiles> leftTiles = new ArrayList<>();
        ArrayList<Tiles> rightTiles = new ArrayList<>();

        GridSpace space = mainGrid.gridSpaces[y][x];

        if (y > 0 && mainGrid.gridSpaces[y - 1][x].collapsed) {
            Tile tile = mainGrid.gridSpaces[y - 1][x].tiles[0].tile;
            for (int i = 0; i < tile.rules.down.length; i++) {
                upTiles.add(Tiles.values()[tile.rules.down[i]]);
            }
        }

        if (y < mainGrid.gridSpaces.length - 1 && mainGrid.gridSpaces[y + 1][x].collapsed) {
            Tile tile = mainGrid.gridSpaces[y + 1][x].tiles[0].tile;
            for (int i = 0; i < tile.rules.up.length; i++) {
                downTiles.add(Tiles.values()[tile.rules.up[i]]);
            }
        }

        if (x > 0 && mainGrid.gridSpaces[y][x - 1].collapsed) {
            Tile tile = mainGrid.gridSpaces[y][x - 1].tiles[0].tile;
            for (int i = 0; i < tile.rules.right.length; i++) {
                leftTiles.add(Tiles.values()[tile.rules.right[i]]);
            }
        }

        if (x < mainGrid.gridSpaces[y].length - 1 && mainGrid.gridSpaces[y][x + 1].collapsed) {
            Tile tile = mainGrid.gridSpaces[y][x + 1].tiles[0].tile;
            for (int i = 0; i < tile.rules.left.length; i++) {
                rightTiles.add(Tiles.values()[tile.rules.left[i]]);
            }
        }

        ArrayList<Tiles> possibleTiles = new ArrayList<>(Arrays.asList(Tiles.values()));
        possibleTiles.retainAll(!upTiles.isEmpty() ? upTiles : possibleTiles);
        possibleTiles.retainAll(!downTiles.isEmpty() ? downTiles : possibleTiles);
        possibleTiles.retainAll(!leftTiles.isEmpty() ? leftTiles : possibleTiles);
        possibleTiles.retainAll(!rightTiles.isEmpty() ? rightTiles : possibleTiles);

        return possibleTiles.toArray(new Tiles[0]);
    }
}