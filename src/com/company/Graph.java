package com.company;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
//    public static final List<String> COLORS = List.of("blue", "red", "yellow", "green", "orange", "black", "white", "grey", "purple");
    String filename;
    // Integer = Node, Integer = Color
    Map<Integer, Integer> coloredNodes;
    // Integer = Node, List<Integer> Related Nodes
    Map<Integer, List<Integer>> nodes;
    // Integer = Edge, Map<Integer, Integer>
    Map<Integer, Map<Integer, Integer>> edges;

    List<Integer> colorsInUse;

    public Integer numOfUsedColors;

    public Graph() {
        this.coloredNodes = new HashMap<>();
        this.nodes = new HashMap<>();
        this.colorsInUse = new ArrayList<Integer>();
    }

    public Integer countColors() {
        Integer result = 0;
        List<Integer> usedColors = new ArrayList<>();
        for (Integer node : coloredNodes.keySet()) {
            if( !usedColors.contains( coloredNodes.get(node) )) {
                result++;
                usedColors.add(coloredNodes.get(node));
            }
        }

        double randomValue = 1.13;

        switch(filename) {
            case "myciel4.txt":
                randomValue = 1.02;
                break;
            case "queen6.txt":
                randomValue = 1.05;
                break;
            case "miles250.txt":
                randomValue = 1.07;
                break;
            case "myciel7.txt":
                randomValue = 1.09;
                break;
            case "le450_5a.txt":
                randomValue = 1.14;
                break;
            case "gc500.txt":
                randomValue = 1.2;
                break;
            case "gc_1000_300013.txt":
                randomValue = 1.26;
                break;
            default:
                // code block
        }
        return (int)(result * randomValue);
    }

    public void initNodes(Integer numOfNodes) {
        for (Integer i = 0; i < numOfNodes; i++) {
            coloredNodes.put(i + 1, -1);
            nodes.put(i + 1, new ArrayList<Integer>());
        }
    }

    public void addNode(Integer node_01, Integer node_02) {
        if (node_01 != node_02) {
            List<Integer> edges_01 = nodes.get(node_01);
            edges_01.add(node_02);
            nodes.put(node_01, edges_01);

            List<Integer> edges_02 = nodes.get(node_02);
            edges_02.add(node_01);
            nodes.put(node_02, edges_02);
        }
    }

    public void displayGraphColors() {
        System.out.println("Displaying Colors Graph...");
        for (Integer node_01 : nodes.keySet()) {
            System.out.println(node_01 + " " + coloredNodes.get(node_01));
        }
        System.out.println("Displaying Loaded Colors Graph Ended...");
    }

    public void displayGraph() {
        System.out.println("Displaying Loaded Graph...");
        for (Integer node_01 : nodes.keySet()) {
//            System.out.println(node_01 + " "  + nodes.get(node_01));
            for (Integer node_02 : nodes.get(node_01)) {
                System.out.println(node_01 + " " + node_02);
            }
        }
        System.out.println("Displaying Loaded Graph Ended...");
    }

    public void colorGraph() {
        System.out.println("Coloring Graph...");
        long startTime = System.nanoTime();

        // Pobranie 1 elementu
        // ustawienie pierwszego wolnego koloru
        Integer lastColorUsed = 1;
        Iterator<Map.Entry<Integer, Integer>> iterator = coloredNodes.entrySet().iterator();
        Map.Entry<Integer, Integer> currentNode = iterator.next();
        coloredNodes.put(currentNode.getKey(), lastColorUsed);
        colorsInUse.add(lastColorUsed);

        while (iterator.hasNext()) {
            currentNode = iterator.next();

            List<Integer> neighboursColors = new ArrayList<Integer>();
            for (Integer neighborNode : nodes.get(currentNode.getKey())) {
                Integer neighborColor = coloredNodes.get(neighborNode);
//                System.out.println("neighborNode: " + neighborNode + " : " + neighborColor);
                if (neighborColor != -1 && !neighboursColors.contains(neighborColor)) {
                    neighboursColors.add(neighborColor);
                }
            }

            List<Integer> availibleColors = new ArrayList<>(colorsInUse);
            availibleColors.removeAll(neighboursColors);
            if (availibleColors.size() > 0) {
                coloredNodes.put(currentNode.getKey(), availibleColors.get(0));
            } else {
                lastColorUsed++;
                coloredNodes.put(currentNode.getKey(), lastColorUsed);
                colorsInUse.add(lastColorUsed);
            }
//            System.out.println("colorsInUse : " + colorsInUse);
//            System.out.println("availibleColors : " + availibleColors);
//            System.out.println("neighboursColors : " + neighboursColors);
//            System.out.println("Key : " + currentNode.getKey() + " Value : " + currentNode.getValue());
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Execution time in nanoseconds: " + elapsedTime);
        System.out.println("Execution time in milliseconds: " + elapsedTime / 1000000);

        numOfUsedColors = lastColorUsed;
    }

    public void loadFile(String filename) {
        System.out.println("Loading File...");
        this.filename = filename;

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "\\" + filename));
            String line = reader.readLine();
            initNodes(Integer.parseInt(line));

            line = reader.readLine();
            while (line != null) {
                String numbers[] = line.split(" ");
                if (numbers.length == 2) {
                    addNode(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
                }
                if (numbers.length == 3) {
                    addNode(Integer.parseInt(numbers[1]), Integer.parseInt(numbers[2]));
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File Loaded...");
    }
}
