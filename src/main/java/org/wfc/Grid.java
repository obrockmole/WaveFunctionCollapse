package org.wfc;

import javax.swing.*;

public class Grid {
    public GridSpace[][] gridSpaces;

    public JFrame window;

    public Grid(int width, int height) {
        gridSpaces = new GridSpace[height][width];
        window = new JFrame();
        window.setSize(width * 50 + 16, height * 50 + 39);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        for (int y = 0; y < gridSpaces.length; y++) {
            for (int x = 0; x < gridSpaces[y].length; x++) {
                gridSpaces[y][x] = new GridSpace(x, y);
                window.add(gridSpaces[y][x].panel);
            }
        }
    }

    public void clearGrid() {
        for (GridSpace[] gridSpace : gridSpaces) {
            for (GridSpace space : gridSpace) {
                space.collapsed = false;
                space.tiles = new Tiles[]{Tiles.WATER, Tiles.SAND, Tiles.GRASS, Tiles.FOREST};
                space.panel.setBackground(new java.awt.Color(255, 255, 255));
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
