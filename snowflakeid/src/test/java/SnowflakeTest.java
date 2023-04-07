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

import com.whackdevelopment.snowflake.Snowflake;
import com.whackdevelopment.snowflake.SnowflakeGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * snowflakeid; PACKAGE_NAME:SnowflakeTest
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 07.04.2023
 */
public class SnowflakeTest {

    private SnowflakeGenerator snowflakeGenerator;
    private Snowflake generatedFlake;
    private Snowflake additionalFlake;

    @BeforeEach
    public void setup() {
        snowflakeGenerator = new SnowflakeGenerator(420, 0);
        assertNotNull(snowflakeGenerator);
    }

    @Test
    public void testGeneratedFlakeGeneration() throws Exception {
        generatedFlake = snowflakeGenerator.generate();
        assertNotNull(generatedFlake);
    }

    @Test
    public void testAdditionalFlakeGeneration() throws Exception {
        additionalFlake = snowflakeGenerator.generate();
        assertNotNull(additionalFlake);
    }

    @Test
    public void testSnowflakeCompareAdditionalFlakeWithGeneratedFlake() throws Exception {
        generatedFlake = snowflakeGenerator.generate();
        additionalFlake = new Snowflake(generatedFlake.toString());

        assertEquals(generatedFlake.extractMachineId(), additionalFlake.extractMachineId());
        assertEquals(generatedFlake.extractTimeOffset(), additionalFlake.extractTimeOffset());
        assertEquals(generatedFlake.extractTimestamp(), additionalFlake.extractTimestamp());
    }

}
