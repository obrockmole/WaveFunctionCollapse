package org.wfc;

import javax.swing.*;

public class GridSpace {
    public boolean collapsed;
    public Tiles[] tiles;
    public JPanel panel;

    public int indexX, indexY;

    public GridSpace(int indexX, int indexY) {
        collapsed = false;
        tiles = new Tiles[]{Tiles.WATER, Tiles.SAND, Tiles.GRASS, Tiles.FOREST};

        this.indexX = indexX;
        this.indexY = indexY;

        panel = new JPanel();
        panel.setSize(50, 50);
        panel.setLocation(indexX * 50, indexY * 50);
        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setVisible(true);
    }

    public void collapse(Tiles tile) {
        collapsed = true;
        tiles = new Tiles[]{tile};
        panel.setBackground(tiles[0].tile.color);
    }

    public void print() {
        if (collapsed)
            tiles[0].tile.print();
        else
            System.out.print(tiles.length + " ");
    }
}
