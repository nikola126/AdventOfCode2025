package com.example.adventofcode;

import lombok.*;

import java.io.File;
import java.util.*;

public class Day5 {
    public final String FILE_DIR = "./src/main/resources/day5.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day5() {
        partOne();
    }

    @SneakyThrows
    private void partOne() {
        scanner = new Scanner(file);

        List<Long> lefts = new ArrayList<>();
        List<Long> rights = new ArrayList<>();
        int countFresh = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.contains("-")) {
                String[] parts = line.split("-");
                lefts.add(Long.parseLong(parts[0]));
                rights.add(Long.parseLong(parts[1]));
            } else if (!line.isBlank()) {
                long ingredient = Long.parseLong(line);

                for (int i = 0; i < lefts.size(); i++) {
                    if (lefts.get(i) <= ingredient && ingredient <= rights.get(i)) {
                        countFresh++;
                        break;
                    }
                }
            }
        }

        System.out.println("Day 5 Part 1: " + countFresh);
    }


}
