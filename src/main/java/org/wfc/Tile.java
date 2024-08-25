package org.wfc;

public class Tile {
    private int[] north, south, east, west;

    public Tile(int[] north, int[] south, int[] east, int[] west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    public int[] getNorth() {
        return north;
    }

    public int[] getSouth() {
        return south;
    }

    public int[] getEast() {
        return east;
    }

    public int[] getWest() {
        return west;
    }
}
