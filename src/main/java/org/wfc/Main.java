package org.wfc;

public class Main {
    static WaveFunctionCollapse wfc;
    static boolean complete = false;
    static int WIDTH = 20;
    static int HEIGHT = 15;

    public static void main(String[] args) {
        wfc = new WaveFunctionCollapse(WIDTH, HEIGHT, Tiles.values());
        wfc.start();

        while (wfc.getIterations() < wfc.getWidth() * wfc.getHeight()) {
            wfc.checkLowestEntropy();
        }

        wfc.printGrid();
//        wfc.saveGrid("src/main/generations/");
    }
}