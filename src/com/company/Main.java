package com.company;

public class Main {

    public static void main(String[] args) {
        Graph graphInst = new Graph();
        graphInst.loadFile("data.txt");
        graphInst.colorGraph();

        System.out.println(">>>");


        // graphInst.displayGraph();
        graphInst.displayGraphColors();

        GraphTaboo graphTabooInst = new GraphTaboo(graphInst.numOfUsedColors);
        graphTabooInst.loadFile("data.txt");
        graphTabooInst.displayGraphColors();
        graphTabooInst.colorGraph();
        graphTabooInst.displayGraphColors();
    }
}
