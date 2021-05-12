package com.company;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
//    public static final List<String> COLORS = List.of("blue", "red", "yellow", "green", "orange", "black", "white", "grey", "purple");

    Map<Integer, Integer> nodes;
    Map<Integer, List<Integer>> edges;
    List<Integer> colorsInUse;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
        this.colorsInUse = new ArrayList<Integer>();
    }

    public void addNodes(Integer numOfNodes) {
        for (Integer i = 0; i < numOfNodes; i++) {
            nodes.put(i + 1, -1);
            edges.put(i + 1, new ArrayList<Integer>());
        }
    }

    public void addEdge(Integer node_01, Integer node_02) {
        if (node_01 != node_02) {
            List<Integer> edges_01 = edges.get(node_01);
            edges_01.add(node_02);
            edges.put(node_01, edges_01);

            List<Integer> edges_02 = edges.get(node_02);
            edges_02.add(node_01);
            edges.put(node_02, edges_02);
        }
    }

    public void displayGraphColors() {
        System.out.println("Displaying Colors Graph...");
        for (Integer node_01 : edges.keySet()) {
            System.out.println(node_01 + " "  + nodes.get(node_01));
        }
        System.out.println("Displaying Loaded Colors Graph Ended...");
    }

    public void displayGraph() {
        System.out.println("Displaying Loaded Graph...");
        for (Integer node_01 : edges.keySet()) {
//            System.out.println(node_01 + " "  + nodes.get(node_01));
            for (Integer node_02 : edges.get(node_01)) {
                System.out.println(node_01 + " "  + node_02);
            }
        }
        System.out.println("Displaying Loaded Graph Ended...");
    }

    public void colorGraphGreedy() {
        System.out.println("Coloring Graph...");
        long startTime = System.nanoTime();

        // Pobranie 1 elementu
        // ustawienie pierwszego wolnego koloru
        Integer lastColorUsed = 1;
        Iterator<Map.Entry<Integer, Integer>> iterator = nodes.entrySet().iterator();
        Map.Entry<Integer, Integer> currentNode = iterator.next();
        nodes.put(currentNode.getKey(), lastColorUsed);
        colorsInUse.add(lastColorUsed);

        while (iterator.hasNext()) {
            currentNode = iterator.next();

            List<Integer> neighboursColors = new ArrayList<Integer>();
            for (Integer neighborNode : edges.get(currentNode.getKey())) {
                Integer neighborColor = nodes.get(neighborNode);
//                System.out.println("neighborNode: " + neighborNode + " : " + neighborColor);
                if(neighborColor != -1 && !neighboursColors.contains(neighborColor)) {
                    neighboursColors.add(neighborColor);
                }
            }

            List<Integer> availibleColors = new ArrayList<>(colorsInUse);
            availibleColors.removeAll(neighboursColors);
            if(availibleColors.size() > 0) {
                nodes.put(currentNode.getKey(), availibleColors.get(0));
            } else {
                lastColorUsed++;
                nodes.put(currentNode.getKey(), lastColorUsed);
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
        System.out.println("lastColorUsed: " + lastColorUsed);
    }

    public void loadFile(String filename) {
        System.out.println("Loading File...");

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "\\" + filename));
            String line = reader.readLine();
            addNodes(Integer.parseInt(line));

            line = reader.readLine();
            while (line != null) {
                String numbers[] = line.split(" ");
                if (numbers.length == 2) {
                    addEdge(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
                }
                if (numbers.length == 3) {
                    addEdge(Integer.parseInt(numbers[1]), Integer.parseInt(numbers[2]));
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
