package com.example.adventofcode;

import lombok.SneakyThrows;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day2 {
    public final String FILE_DIR = "./src/main/resources/day2.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day2() {
        partOne();
        partTwo();
    }

    @SneakyThrows
    private void partOne() {
        scanner = new Scanner(file);
        String line = scanner.nextLine();

        String[] intervals = line.split(",");
        long sum = 0;

        for (String interval : intervals) {
            String[] range = interval.split("-");
            long start = Long.parseLong(range[0]);
            long end = Long.parseLong(range[1]);

            for (long i = start; i <= end; i++) {
                if (isInvalidPartOne(i)) {
                    sum += i;
                }
            }
        }

        System.out.println("Day 2 Part 1: " + sum);
    }

    @SneakyThrows
    private void partTwo() {
        scanner = new Scanner(file);
        String line = scanner.nextLine();

        String[] intervals = line.split(",");
        long sum = 0;

        for (String interval : intervals) {
            String[] range = interval.split("-");
            long start = Long.parseLong(range[0]);
            long end = Long.parseLong(range[1]);

            for (long i = start; i <= end; i++) {
                if (isInvalidPartTwo(i)) {
                    sum += i;
                }
            }
        }

        System.out.println("Day 2 Part 2: " + sum);
    }

    private boolean isInvalidPartOne(long input) {
        String asString = Long.toString(input);

        if (asString.length() % 2 != 0) {
            return false;
        }

        Set<Character> set = new HashSet<>();
        for (char c : asString.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }

        if (!set.isEmpty()) {
            return false;
        }

        int maxDuplicateLength = asString.length() / 2;
        for (int i = 1; i <= maxDuplicateLength; i++) {
            String candidate = asString.substring(0, i);
            int requiredOccurrences = asString.length() / i;

            if (asString.length() % requiredOccurrences != 0) {
                continue;
            }

            if (asString.length() % candidate.length() != 0) {
                continue;
            }

            int foundOccurrences = countOccurrences(asString, candidate);
            if (requiredOccurrences == foundOccurrences) {
                return true;
            }
        }

        return false;
    }

    private boolean isInvalidPartTwo(long input) {
        String asString = Long.toString(input);

        for (int i = 1; i <= asString.length(); i++) {
            String candidate = asString.substring(0, i);
            int requiredOccurrences = asString.length() / i;

            if (i != 1) {
                if (asString.length() % requiredOccurrences != 0) {
                    continue;
                }

                if (asString.length() % candidate.length() != 0) {
                    continue;
                }
            }

            int foundOccurrences = countOccurrences(asString, candidate);
            if (foundOccurrences != requiredOccurrences) {
                continue;
            }

            if (foundOccurrences >= 2) {
                return true;
            }
        }

        return false;
    }

    private int countOccurrences(String input, String target) {
        int count = 0;

        while (input.contains(target)) {
            input = input.replaceFirst(target, "X");
            count++;
        }

        return count;
    }

}
