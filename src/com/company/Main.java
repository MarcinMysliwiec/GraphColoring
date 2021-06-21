package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Graph graphInst = new Graph();
        List<String> data = new ArrayList<>();
        data.add("myciel4.txt");    // 0
        data.add("queen6.txt");     // 1
        data.add("miles250.txt");   // 2
        data.add("myciel7.txt");    // 3
        data.add("le450_5a.txt");   // 4
        data.add("gc500.txt");      // 5
        data.add("gc_1000_300013.txt"); // 6

        graphInst.loadFile(data.get( 4 ));
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
