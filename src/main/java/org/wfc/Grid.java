package org.wfc;

public class Grid {
    public GridSpace[][] gridSpaces;

    public Grid(int width, int height) {
        gridSpaces = new GridSpace[height][width];

        for (int y = 0; y < gridSpaces.length; y++) {
            for (int x = 0; x < gridSpaces[y].length; x++) {
                gridSpaces[y][x] = new GridSpace(x, y);
            }
        }
    }

    public void collapse(int x, int y, Tiles tile) {
        gridSpaces[y][x].collapse(tile);
    }

    public void print() {
        for (GridSpace[] gridSpaces : gridSpaces) {
            for (GridSpace gridSpace : gridSpaces) {
                gridSpace.print();
            }
            System.out.println();
        }
    }
}
