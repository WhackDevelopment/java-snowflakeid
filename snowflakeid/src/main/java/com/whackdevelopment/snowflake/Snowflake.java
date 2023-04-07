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

import java.util.Date;

/**
 * snowflakeid; com.whackdevelopment.snowflake:Snowflake
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 07.04.2023
 */

/**
 * A class that extends the built-in String class to represent a Snowflake ID.
 * Snowflake is a unique identifier used in distributed systems, particularly in Twitter's distributed messaging system.
 */
public class Snowflake {

    private Date timestamp;
    private int machineId;
    private int timeOffset;
    private String flake;

    /**
     * Creates a new Snowflake instance from a string.
     *
     * @param flake The Snowflake string to create an instance from.
     */
    public Snowflake(String flake) {
        this.flake = flake;
        this.timestamp = this.extractTimestamp();
        this.machineId = this.extractMachineId();
        this.timeOffset = this.extractTimeOffset();
    }

    /**
     * Extracts the timestamp from the Snowflake string and returns it as a Date object.
     *
     * @return The timestamp as a Date object.
     */
    public Date extractTimestamp() {
        // Convert the Snowflake string to binary and pad with zeroes to 64 bits
        String binary = Long.toBinaryString(Long.parseLong(flake, 10));
        binary = String.format("%64s", binary).replace(' ', '0');
        // Extract the first 42 bits, which represent the timestamp
        String timeBinary = binary.substring(0, 42);
        // Convert the binary timestamp to a number and add the epoch time to get the timestamp in milliseconds since Unix epoch
        long timestamp = Long.parseLong(timeBinary, 2) + SnowflakeGenerator.EPOCH;
        // Convert the timestamp to a Date object and return it
        return new Date(timestamp);
    }

    /**
     * Extracts the machine ID from the Snowflake string and returns it as a number.
     *
     * @return The machine ID as a number.
     */
    public int extractMachineId() {
        // Convert the Snowflake string to binary and pad with zeroes to 64 bits
        String binary = Long.toBinaryString(Long.parseLong(toString(), 10));
        binary = String.format("%64s", binary).replace(' ', '0');
        // Extract the bits from 42 to 52, which represent the machine ID
        String machineBinary = binary.substring(42, 52);
        // Convert the binary machine ID to a number and return it
        return Integer.parseInt(machineBinary, 2);
    }

    /**
     * Extracts the time offset from the Snowflake string and returns it as a number.
     *
     * @return The time offset as a number.
     */
    public int extractTimeOffset() {
        // Convert the Snowflake string to binary and pad with zeroes to 64 bits
        String binary = Long.toBinaryString(Long.parseLong(flake, 10));
        binary = String.format("%64s", binary).replace(' ', '0');
        // Extract the bits from 52 to 64, which represent the time offset
        String offsetBinary = binary.substring(52, 64);
        // Convert the binary time offset to a number and return it
        int offset = Integer.parseInt(offsetBinary, 2);
        return offset;
    }

    /**
     * Returns a new instance of SnowflakeGenerator with default configuration.
     *
     * @return A new SnowflakeGenerator instance with default configuration.
     */
    public SnowflakeGenerator getGenerator() {
        // Create a new SnowflakeGenerator instance with default config and return it
        return new SnowflakeGenerator(machineId, timeOffset);
    }

    @Override
    public String toString() {
        return flake;
    }

    public String getFlake() {
        return flake;
    }
}
