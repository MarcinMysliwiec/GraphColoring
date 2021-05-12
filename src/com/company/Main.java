package com.company;

public class Main {

    public static void main(String[] args) {
        Graph graphInst = new Graph();
        graphInst.loadFile("data.txt");
        graphInst.colorGraphGreedy();

        System.out.println(">>>");


        graphInst.displayGraph();

    }
}
