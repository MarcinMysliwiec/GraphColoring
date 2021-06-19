package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GraphTaboo {

    Map<Integer, Integer> coloredNodes;
    Map<Integer, List<Integer>> nodes;
    List<Integer> colorsInUse;
    Map<Integer, List<Integer>> conflicts;

    public GraphTaboo(int colorsInUse) {
        this.coloredNodes = new HashMap<>();
        this.nodes = new HashMap<>();
        this.colorsInUse = new ArrayList<Integer>();
        this.conflicts = new HashMap<>();

        for (Integer i = 1; i <= colorsInUse; i++) {
            this.colorsInUse.add(i);
        }
    }

    public void initNodes(Integer numOfNodes) {
        for (Integer i = 0; i < numOfNodes; i++) {
            coloredNodes.put(i + 1, -1);
            nodes.put(i + 1, new ArrayList<Integer>());

            Random randomizer = new Random();
            Integer color = colorsInUse.get(randomizer.nextInt(colorsInUse.size()));
            coloredNodes.put(i + 1, color);
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
        for (Integer node_01 : coloredNodes.keySet()) {
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
        // Pobranie 1 elementu
        // ustawienie pierwszego wolnego koloru
        Iterator<Map.Entry<Integer, Integer>> iterator = coloredNodes.entrySet().iterator();
        Map.Entry<Integer, Integer> currentNode;

        while (iterator.hasNext()) {
            currentNode = iterator.next();
            List<Integer> neighbourColors = new ArrayList<Integer>();

            for (Integer neighborNode : nodes.get(currentNode.getKey())) {
                neighbourColors.add(coloredNodes.get(neighborNode));
            }

            if (neighbourColors.contains(currentNode.getValue())) {
                System.out.println(">>> Conflict");

                for(Integer color : colorsInUse) {
                    if(!neighbourColors.contains(color)) {
                        coloredNodes.put(currentNode.getKey(), color);
                        break;
                    }
                }
            }
        }
    }

    public void loadFile(String filename) {
        System.out.println("Loading File...");

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
