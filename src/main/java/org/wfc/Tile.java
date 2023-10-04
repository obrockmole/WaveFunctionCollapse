package org.wfc;

public class Tile {
    public Rules rules;
    public String color;

    public Tile(String color, Rules rules) {
        this.color = color;
        this.rules = rules;
    }

    public void print() {
        System.out.print(color + "█ " + "\u001B[0m");
    }
}

class Rules {
    public int[] up;
    public int[] down;
    public int[] left;
    public int[] right;

    public Rules(int[] up, int[] down, int[] left, int[] right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }
}

enum Tiles {
    WATER(new Tile("\u001B[34m", new Rules(new int[]{0, 1}, new int[]{0, 1}, new int[]{0, 1}, new int[]{0, 1})), 0),
    SAND(new Tile("\u001B[33m", new Rules(new int[]{0, 1, 2}, new int[]{0, 1, 2}, new int[]{0, 1, 2}, new int[]{0, 1, 2})), 1),
    GRASS(new Tile("\u001B[32m", new Rules(new int[]{1, 2, 3}, new int[]{1, 2, 3}, new int[]{1, 2, 3}, new int[]{1, 2, 3})), 2),
    FOREST(new Tile("\u001B[92m", new Rules(new int[]{2, 3}, new int[]{2, 3}, new int[]{2, 3}, new int[]{2, 3})), 3);

    public final Tile tile;
    public final int index;

    Tiles(Tile tile, int index) {
        this.tile = tile;
        this.index = index;
    }
}