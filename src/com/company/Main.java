package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Graph graphInst = new Graph();
        graphInst.loadFile("data.txt");
        graphInst.colorGraph();
        System.out.println(">>> Colors used brutforce: " + graphInst.countColors());
        System.out.println(">>> END BRUTFORCE");


//        graphInst.displayGraph();
//        graphInst.displayGraphColors();

        GraphTaboo graphTabooInst = new GraphTaboo(graphInst.numOfUsedColors);
        graphTabooInst.loadFile("data.txt");
//        graphTabooInst.displayGraphColors();
        graphTabooInst.colorGraph();
//        graphTabooInst.displayGraphColors();

        System.out.println(">>> Colors used taboo: " + graphTabooInst.countColors());
    }
}
