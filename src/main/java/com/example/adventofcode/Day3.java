package com.example.adventofcode;

import lombok.SneakyThrows;

import java.io.File;
import java.util.Scanner;

public class Day3 {
    public final String FILE_DIR = "./src/main/resources/day3.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day3() {
        partOne();
    }

    @SneakyThrows
    private void partOne() {
        scanner = new Scanner(file);
        long joltageSum = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            int firstMax = -1;
            int firstMaxPosition = -1;
            for (int i = 0; i < line.length(); i++) {
                int battery = Integer.parseInt(line.charAt(i) + "");

                if (battery > firstMax) {
                    firstMax = battery;
                    firstMaxPosition = i;
                }
            }

            int secondMax = -1;
            int secondMaxPosition = -1;
            for (int i = firstMaxPosition + 1; i < line.length(); i++) {
                int battery = Integer.parseInt(line.charAt(i) + "");

                if (battery > secondMax) {
                    secondMax = battery;
                    secondMaxPosition = i;
                }
            }

            if (secondMaxPosition == -1) {
                for (int i = 0; i < firstMaxPosition; i++) {
                    int battery = Integer.parseInt(line.charAt(i) + "");

                    if (battery > secondMax) {
                        secondMax = battery;
                        secondMaxPosition = i;
                    }
                }
            }

            int joltage;
            if (firstMaxPosition < secondMaxPosition) {
                joltage = firstMax * 10 + secondMax;
            } else {
                joltage = secondMax * 10 + firstMax;
            }

            joltageSum = joltageSum + joltage;
        }

        System.out.println("Day 3 Part 1: " + joltageSum);
    }

}
