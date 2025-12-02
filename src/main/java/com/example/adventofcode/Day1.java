package com.example.adventofcode;

import lombok.SneakyThrows;

import java.io.File;
import java.util.*;

public class Day1 {
    public final String FILE_DIR = "./src/main/resources/day1.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    @SneakyThrows
    public Day1() {
        scanner = new Scanner(file);
        int dial = 50;
        int countPartOne = 0;
        int countPartTwo = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            char direction = line.charAt(0);
            int rotation = Integer.parseInt(line.substring(1));

            for (int i = 1; i <= rotation; i++) {
                int currentPosition = direction == 'L' ? dial - 1 : dial + 1;

                if (currentPosition == 100) {
                    currentPosition = 0;
                } else if (currentPosition == -1) {
                    currentPosition = 99;
                }

                dial = currentPosition;

                if (dial == 0 && i != rotation) {
                    countPartTwo++;
                }
            }

            if (dial == 0) {
                countPartOne++;
                countPartTwo++;
            }
        }

        System.out.println("Day 1 Part 1: " + countPartOne);
        System.out.println("Day 1 Part 2: " + countPartTwo);
    }

}
