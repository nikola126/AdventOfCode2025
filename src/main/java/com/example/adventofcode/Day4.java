package com.example.adventofcode;

import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    public final String FILE_DIR = "./src/main/resources/day4.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day4() {
        partOne();
        partTwo();
    }

    @SneakyThrows
    private void partOne() {
        scanner = new Scanner(file);

        List<List<Character>> grid = readGrid();
        int count = collectAccessibleRolls(grid).size();

        System.out.println("Day 4 Part 1: " + count);
    }

    @SneakyThrows
    private void partTwo() {
        scanner = new Scanner(file);

        List<List<Character>> grid = readGrid();
        int count = 0;

        List<List<Integer>> accessibleRolls = collectAccessibleRolls(grid);
        count += accessibleRolls.size();

        while (!accessibleRolls.isEmpty()) {
            removeAccessibleRolls(grid, accessibleRolls);
            accessibleRolls = collectAccessibleRolls(grid);
            count += accessibleRolls.size();
        }

        System.out.println("Day 4 Part 2: " + count);
    }

    private void removeAccessibleRolls(List<List<Character>> grid, List<List<Integer>> accessibleRolls) {
        for (List<Integer> coordinates : accessibleRolls) {
            grid.get(coordinates.get(0)).set(coordinates.get(1), 'x');
        }
    }

    private List<List<Integer>> collectAccessibleRolls(List<List<Character>> grid) {
        List<List<Integer>> coordinates = new ArrayList<>();

        for (int row = 0; row < grid.size(); row++) {
            for (int col = 0; col < grid.get(row).size(); col++) {
                if (grid.get(row).get(col) != '@') {
                    continue;
                }

                long adjacentRolls = getAdjacentRolls(row, col, grid);

                if (grid.get(row).get(col) == '@' && adjacentRolls < 4) {
                    coordinates.add(List.of(row, col));
                }
            }
        }

        return coordinates;
    }

    private List<List<Character>> readGrid() {
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

    private long getAdjacentRolls(int row, int col, List<List<Character>> grid) {
        List<Character> surroundings = new ArrayList<>();
        surroundings.add(getCharAt(row - 1, col - 1, grid));    // up left
        surroundings.add(getCharAt(row - 1, col, grid));            // up
        surroundings.add(getCharAt(row - 1, col + 1, grid));    // up right
        surroundings.add(getCharAt(row, col - 1, grid));             // left
        surroundings.add(getCharAt(row, col + 1, grid));             // right
        surroundings.add(getCharAt(row + 1, col - 1, grid));    // down left
        surroundings.add(getCharAt(row + 1, col, grid));            // down
        surroundings.add(getCharAt(row + 1, col + 1, grid));    // down right

        return surroundings.stream().filter(c -> c != null && c == '@').count();
    }

    private Character getCharAt(int row, int col, List<List<Character>> grid) {
        try {
            return grid.get(row).get(col);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

}
