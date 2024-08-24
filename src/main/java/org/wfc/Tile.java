package org.wfc;

public class Tile {
    public int[] north, south, east, west;

    public Tile(int[] north, int[] south, int[] east, int[] west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }
}
