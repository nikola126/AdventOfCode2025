package com.example.adventofcode;

import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day7 {
    public final String FILE_DIR = "./src/main/resources/day7.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day7() {
        partOne();
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
