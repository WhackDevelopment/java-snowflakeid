/*
 * Copyright (c) 2023 - present | LuciferMorningstarDev <contact@lucifer-morningstar.xyz>
 * Copyright (c) 2023 - present | whackdevelopment.com <contact@whackdevelopment.com>
 * Copyright (c) 2023 - present | whackdevelopment.com team and contributors
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.whackdevelopment.snowflake;

import java.util.HashSet;
import java.util.Set;

/**
 * java-snowflakeid; com.whackdevelopment.snowflake:SnowflakeTest
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 07.04.2023
 */
public class SnowflakeTest {

    public static void main(String[] args) throws Exception {
        // Create a generator
        SnowflakeGenerator flakeGenerator = new SnowflakeGenerator(420, (2022 - 1970) * 31536000L * 1000L);

        // Check generation
        Snowflake flake1 = flakeGenerator.generate();
        Snowflake flake2 = flakeGenerator.generate();
        Snowflake flake3 = flakeGenerator.generate();

        System.out.println(flake1 + "\n" + new Snowflake(flake1.toString()) + "\nMID:" + flake1.extractMachineId() + " TOS:" + flake1.extractTimeOffset() + " TS:" + flake1.extractTimestamp());
        System.out.println(flake2 + "\n" + new Snowflake(flake2.toString()) + "\nMID:" + flake2.extractMachineId() + " TOS:" + flake2.extractTimeOffset() + " TS:" + flake2.extractTimestamp());
        System.out.println(flake3 + "\n" + new Snowflake(flake3.toString()) + "\nMID:" + flake3.extractMachineId() + " TOS:" + flake3.extractTimeOffset() + " TS:" + flake3.extractTimestamp());

        // Check generations for duplicates
        Set<String> generated = new HashSet<>();
        int iterations = 1_000_000;

        System.out.println("Generations-" + iterations);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < iterations; i++) {
            Snowflake flake = flakeGenerator.generate();
            generated.add(flake.toString());
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (endTime - startTime) + "ms");

        System.out.println("Duplicates-On-Generations-" + iterations);
        startTime = System.currentTimeMillis();
        Set<String> duplicates = findDuplicates(generated);
        endTime = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (endTime - startTime) + "ms");

        System.out.println("Found " + duplicates.size() + " duplicates");
        System.out.println(duplicates);
    }

    private static Set<String> findDuplicates(Set<String> array) {
        Set<String> duplicates = new HashSet<>();
        Set<String> seen = new HashSet<>();

        for (String item : array) {
            if (seen.contains(item)) {
                duplicates.add(item);
            } else {
                seen.add(item);
            }
        }

        return duplicates;
    }

}
