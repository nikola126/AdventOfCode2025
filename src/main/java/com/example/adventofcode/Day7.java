package com.example.adventofcode;

import lombok.SneakyThrows;

import java.io.File;
import java.util.*;

public class Day7 {
    public final String FILE_DIR = "./src/main/resources/day7.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day7() {
        partOne();
        partTwo();
    }

    @SneakyThrows
    private void partOne() {
        List<List<Character>> grid = readGrid();
        long count = 0;

        for (int row = 1; row < grid.size(); row++) {
            for (int col = 0; col < grid.get(row).size(); col++) {
                char upperCell = grid.get(row - 1).get(col);
                char currentCell = grid.get(row).get(col);

                if (upperCell == 'S') {
                    grid.get(row).set(col, '|');
                } else if (currentCell == '^' && upperCell == '|') {
                    count++;
                    grid.get(row).set(col - 1, '|');
                    grid.get(row).set(col + 1, '|');
                } else if (upperCell == '|') {
                    grid.get(row).set(col, '|');
                }
            }
        }

        System.out.println("Day 7 Part 1: " + count);
    }

    @SneakyThrows
    private void partTwo() {
        List<List<Character>> grid = readGrid();
        Map<String, Long> futures = new LinkedHashMap<>();

        for (int row = 1; row < grid.size(); row++) {
            for (int col = 0; col < grid.get(row).size(); col++) {
                String upperCoords = (row - 1) + "_" + col;
                String currentCoords = row + "_" + col;
                char upperCell = grid.get(row - 1).get(col);
                char currentCell = grid.get(row).get(col);

                if (upperCell == 'S') {
                    grid.get(row).set(col, '|');

                    futures.put(currentCoords, 1L);
                } else if (currentCell == '^' && upperCell == '|') {
                    grid.get(row).set(col - 1, '|');
                    grid.get(row).set(col + 1, '|');

                    String leftCoords = row + "_" + (col - 1);
                    String rightCoords = row + "_" + (col + 1);

                    long upperFuture = futures.get(upperCoords);
                    long currentLeftFuture =  futures.getOrDefault(leftCoords, 0L);
                    long currentRightFuture = futures.getOrDefault(rightCoords, 0L);

                    futures.put(leftCoords, currentLeftFuture + upperFuture);
                    futures.put(rightCoords, currentRightFuture + upperFuture);
                } else if (upperCell == '|') {
                    grid.get(row).set(col, '|');

                    long currentFuture = futures.getOrDefault(currentCoords, 0L);
                    long upperFuture = futures.getOrDefault(upperCoords, 0L);

                    futures.put(currentCoords, currentFuture + upperFuture);
                }
            }
        }

        String lastRowPrefix = String.valueOf(grid.size() - 1);
        long sum = 0;
        for (Map.Entry<String, Long> entry : futures.entrySet()) {
            if (entry.getKey().startsWith(lastRowPrefix)) {
                sum += entry.getValue();
            }
        }

        System.out.println("Day 7 Part 2: " + sum);
    }

    @SneakyThrows
    private List<List<Character>> readGrid() {
        scanner = new Scanner(file);

        List<List<Character>> grid = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Character> row = new ArrayList<>();

            for (Character c : line.toCharArray()) {
                row.add(c);
            }

            grid.add(row);
        }

        return grid;
    }

}
