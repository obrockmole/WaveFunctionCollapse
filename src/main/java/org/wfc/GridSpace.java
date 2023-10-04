package org.wfc;

public class GridSpace {
    public boolean collapsed;
    public Tiles[] tiles;

    public int indexX, indexY;

    public GridSpace(int indexX, int indexY) {
        collapsed = false;
        tiles = new Tiles[]{Tiles.WATER, Tiles.SAND, Tiles.GRASS, Tiles.FOREST};

        this.indexX = indexX;
        this.indexY = indexY;
    }

    public void collapse(Tiles tile) {
        collapsed = true;
        tiles = new Tiles[]{tile};
    }

    public void print() {
        if (collapsed) {
            tiles[0].tile.print();
        } else {
            System.out.print(tiles.length + " ");
        }
    }
}
