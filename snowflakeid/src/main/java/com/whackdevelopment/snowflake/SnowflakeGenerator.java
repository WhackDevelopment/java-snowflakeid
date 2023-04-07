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

/**
 * snowflakeid; com.whackdevelopment.snowflake:SnowflakeGenerator
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 07.04.2023
 */

/**
 * This is a class that generates unique 64-bit snowflake IDs
 */
public class SnowflakeGenerator {
    // Define some constants
    public static final long EPOCH = 1627833600000L; // epoch time in milliseconds (set to 2021-08-01 00:00:00 UTC)
    public static final int SEQUENCE_BITS = 12; // number of bits reserved for the sequence number
    public static final int MACHINE_BITS = 10; // number of bits reserved for the machine ID
    public static final int MAX_SEQUENCE = (int) Math.pow(2, SnowflakeGenerator.SEQUENCE_BITS) - 1; // maximum sequence number
    public static final int MAX_MACHINE_ID = (int) Math.pow(2, SnowflakeGenerator.MACHINE_BITS) - 1; // maximum machine ID

    private int sequence;
    private long lastTimestamp;
    private int machineId;
    private long timeOffset;

    /**
     * Constructor for the SnowflakeGenerator class
     *
     * @param machineId  - Optional machine ID to use for the SnowflakeGenerator instance
     * @param timeOffset - Optional time offset (in milliseconds) to use for the SnowflakeGenerator instance
     * @throws IllegalArgumentException If the provided machine ID is invalid (not between 0 and MAX_MACHINE_ID) or if the provided time offset is not a number
     */
    public SnowflakeGenerator(int machineId, long timeOffset) throws IllegalArgumentException {
        // Initialize the sequence number and last timestamp to 0
        this.sequence = 0;
        this.lastTimestamp = 0;

        // Generate a random machine ID if none is provided, or use the provided one
        if (machineId > SnowflakeGenerator.MAX_MACHINE_ID) {
            throw new IllegalArgumentException(
                    String.format("Invalid machine id: %d", machineId));
        }
        this.machineId = machineId;

        // Set the time offset (in milliseconds) from the epoch time, or use 0 if none is provided
        if (timeOffset < 0L) {
            throw new IllegalArgumentException(
                    String.format("Invalid time offset: %d", timeOffset));
        }
        this.timeOffset = timeOffset;
    }

    /**
     * Constructor for the SnowflakeGenerator class
     *
     * @param machineId - Optional machine ID to use for the SnowflakeGenerator instance
     * @throws IllegalArgumentException If the provided machine ID is invalid (not between 0 and MAX_MACHINE_ID) or if the provided time offset is not a number
     */
    public SnowflakeGenerator(int machineId) {
        this.timeOffset = 0;
        // Generate a random machine ID if none is provided, or use the provided one
        if (machineId > SnowflakeGenerator.MAX_MACHINE_ID) {
            throw new IllegalArgumentException(
                    String.format("Invalid machine id: %d", machineId));
        }
        this.machineId = (int) (Math.random() * SnowflakeGenerator.MAX_MACHINE_ID);
    }

    /**
     * Constructor for the SnowflakeGenerator class
     */
    public SnowflakeGenerator() {
        this.timeOffset = 0;
        this.machineId = (int) (Math.random() * SnowflakeGenerator.MAX_MACHINE_ID);
    }

    /**
     * Method to generate a new snowflake ID
     *
     * @return Snowflake - A new Snowflake object representing the generated ID
     * @throws Exception If the clock moves backwards (i.e. the timestamp is earlier than the last timestamp)
     */
    public Snowflake generate() throws Exception {
        // Get the current timestamp in milliseconds since the epoch, and add the time offset
        long timestamp = System.currentTimeMillis() - EPOCH + timeOffset;

        // Check if the clock has moved backwards
        if (timestamp < lastTimestamp) {
            throw new Exception("Clock moved backwards, waiting for the next millisecond");
        }

        // If the timestamp is the same as the last timestamp, increment the sequence number
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;

            // If the sequence number overflows, wait until the next millisecond
            if (sequence == 0) {
                while (System.currentTimeMillis() - EPOCH + timeOffset <= lastTimestamp) {
                    Thread.sleep(1);
                }
            }
        } else {
            // If the timestamp is different from the last timestamp, reset the sequence number to 0
            sequence = 0;
        }

        // Update the last timestamp to the current timestamp
        lastTimestamp = timestamp;

        // Convert the timestamp, machine ID, and sequence number to binary strings
        String timeBinary = Long.toBinaryString(timestamp);
        timeBinary = String.format("%42s", timeBinary).replace(' ', '0');
        String machineBinary = Long.toBinaryString(machineId);
        machineBinary = String.format("%10s", machineBinary).replace(' ', '0');
        String sequenceBinary = Integer.toBinaryString(sequence);
        sequenceBinary = String.format("%12s", sequenceBinary).replace(' ', '0');

        // Concatenate the binary strings to form the 64-bit snowflake ID
        String binary = timeBinary + machineBinary + sequenceBinary;

        // Convert the binary string to a long and return a new Snowflake object
        long flake = Long.parseLong(binary, 2);
        return new Snowflake(Long.toString(flake));
    }

}