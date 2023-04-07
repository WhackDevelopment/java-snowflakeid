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

import com.whackdevelopment.snowflake.SnowflakeGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * snowflakeid; PACKAGE_NAME:SnowflakeGeneratorTest
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 07.04.2023
 */
class SnowflakeGeneratorTest {

    private SnowflakeGenerator snowflakeGenerator;
    private List<String> flakes;

    @BeforeEach
    void setUp() {
        snowflakeGenerator = new SnowflakeGenerator(420, 0);
        flakes = new ArrayList<>();
    }

    @Test
    void testInstantiateSnowflake() {
        assertNotNull(snowflakeGenerator);
    }

    @Test
    void testSnowflakeGeneration() throws Exception {
        int length = 5000;

        for (int i = 0; i < length; i++) {
            flakes.add(snowflakeGenerator.generate().toString());
        }

        assertEquals(length, flakes.size());
    }

    @Test
    void testSnowflakeUnique() {
        List<String> tmp = new ArrayList<>();

        for (String flake : flakes) {
            if (!tmp.contains(flake)) {
                tmp.add(flake);
            }
        }

        assertEquals(flakes.size(), tmp.size());
    }
}
