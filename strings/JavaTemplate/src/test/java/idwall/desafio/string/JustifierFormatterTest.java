package idwall.desafio.string;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JustifierFormatterTest {

    private static final String DEFAULT_INPUT_TEXT = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n" +
            "\n" +
            "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";

    @Test
    void formatDefault() {
        StringFormatter stringFormatter = new JustifierFormatter();
        String actual = stringFormatter.format(DEFAULT_INPUT_TEXT);
        Arrays.stream(actual.split("\n")).filter(it -> !it.trim().isEmpty())
                .forEach(it -> assertTrue(it.matches("\\A\\S.*\\S\\Z"))
                );
    }
}
