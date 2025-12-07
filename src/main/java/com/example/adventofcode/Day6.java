package com.example.adventofcode;

import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6 {
    public final String FILE_DIR = "./src/main/resources/day6.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day6() {
        partOne();
        partTwo();
    }

    @SneakyThrows
    private void partOne() {
        List<List<Character>> grid = readGrid();
        long sum = 0;

        List<Integer> emptyCols = findEmptyCols(grid);
        int leftCol = 0;
        for (int rightCol : emptyCols) {
            List<String> operands = new ArrayList<>();
            char operator = 'A';

            for (int row = 0; row < grid.size(); row++) {
                StringBuilder operand = new StringBuilder();
                for (int col = leftCol; col < rightCol; col++) {
                    char c = grid.get(row).get(col);
                    if (c != ' ') {
                        operand.append(c);
                    }
                }

                if (row != grid.size() - 1) {
                    operands.add(operand.toString());
                } else {
                    operator = operand.charAt(0);
                }
            }

            sum += calculateLocalExpression(operator, operands);
            leftCol = rightCol;
        }

        System.out.println("Day 6 Part 1: " + sum);
    }

    @SneakyThrows
    private void partTwo() {
        List<List<Character>> grid = readGrid();
        long sum = 0;

        List<Integer> emptyCols = findEmptyCols(grid);
        int leftCol = 0;
        for (int rightCol : emptyCols) {
            List<String> operands = new ArrayList<>();
            char operator = 'A';

            for (int col = leftCol; col < rightCol; col++) {
                StringBuilder operand = new StringBuilder();
                for (int row = 0; row < grid.size() - 1; row++) {
                    char c = grid.get(row).get(col);

                    if (c != ' ') {
                        operand.append(c);
                    }
                }

                if (operand.toString().isEmpty()) {
                    continue;
                }

                operands.add(operand.toString());
            }

            for (int col = leftCol; col < rightCol; col++) {
                for (int row = grid.size() - 1; row < grid.size(); row++) {
                    char c = grid.get(row).get(col);
                    if (c != ' ') {
                        operator = c;
                    }
                }
            }

            sum += calculateLocalExpression(operator, operands);
            leftCol = rightCol;
        }

        System.out.println("Day 6 Part 2: " + sum);
    }

    private List<Integer> findEmptyCols(List<List<Character>> grid) {
        List<Integer> emptyCols = new ArrayList<>();
        int rows = grid.size();
        int cols = grid.getFirst().size();

        for (int col = 0; col < cols; col++) {
            boolean isEmpty = true;

            for (int row = 0; row < rows; row++) {
                if (grid.get(row).get(col) != ' ') {
                    isEmpty = false;
                    break;
                }
            }

            if (isEmpty) {
                emptyCols.add(col);
            }
        }

        emptyCols.add(cols);
        return emptyCols;
    }

    private long calculateLocalExpression(char operator, List<String> operands) {
        long local;
        if (operator == '*') {
            local = 1;
            for (String operand : operands) {
                local = local * Long.parseLong(operand);
            }
        } else {
            local = 0;
            for (String operand : operands) {
                local = local + Long.parseLong(operand);
            }
        }
        return local;
    }

    @SneakyThrows
    private List<List<Character>> readGrid() {
        scanner = new Scanner(file);

        List<List<Character>> grid = new ArrayList<>();
        int maxRowLength = -1;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Character> row = new ArrayList<>();

            for (Character c : line.toCharArray()) {
                row.add(c);
            }

            maxRowLength = Math.max(maxRowLength, row.size());
            grid.add(row);
        }

        for (List<Character> row : grid) {
            if (row.size() != maxRowLength) {
                for (int k = row.size(); k < maxRowLength; k++) {
                    row.add(' ');
                }
            }
        }

        return grid;
    }

}
