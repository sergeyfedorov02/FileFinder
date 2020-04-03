package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindArgsTest {

    @Test
    void Parse() {
        ArgumentsParser result = new ArgumentsParser();
        Assertions.assertNotNull(result.Parse(new String[] {"-r", "jsondata_test.go"}));
        Assertions.assertNull(result.Parse(new String[] {}));
        Assertions.assertNotNull(result.Parse(new String[] {"jsondata"}));
        Assertions.assertNotNull(result.Parse(new String[] {"-r", "-d", "c:\\go", "jsondata_test.go"}));
        Assertions.assertNull(result.Parse(new String[] {"-r", "-d", "jsondata_test.go"}));
    }

    @Test
    void getIsRecursive() {
        ArgumentsParser parser = new ArgumentsParser();

        FindArgs result = parser.Parse(new String[] {"-r", "jsondata_test.go"});
        Assertions.assertTrue(result.getIsRecursive());

        result = parser.Parse(new String[] { "jsondata_test.go"});
        Assertions.assertFalse(result.getIsRecursive());
    }

    @Test
    void getDirectory() {
        ArgumentsParser parser = new ArgumentsParser();

        FindArgs result = parser.Parse(new String[] {"-r", "jsondata_test.go"});
        Assertions.assertNull(result.getDirectory());

        result = parser.Parse(new String[] {"-r", "-d", "c:\\go", "jsondata_test.go"});
        Assertions.assertNotNull(result.getDirectory());
        Assertions.assertEquals("c:\\go", result.getDirectory());

        result = parser.Parse(new String[] {"-r", "-d", "jsondata_test.go"});
        Assertions.assertNull(result); // так как указан флаг дирректории, значит далее будет имя дирректории, а потом только имя файла(его тут нет)
    }

    @Test
    void getFileName() {
        ArgumentsParser parser = new ArgumentsParser();

        FindArgs result = parser.Parse(new String[] {"-r", "jsondata_test.go"});
        Assertions.assertEquals("jsondata_test.go", result.getFileName());

        result = parser.Parse(new String[] {"jsondata"});
        Assertions.assertEquals("jsondata", result.getFileName());

        result = parser.Parse(new String[] {});
        Assertions.assertNull(result);

        result = parser.Parse(new String[] {"-r", "-d", "c:\\go", "jsondata_test.go"});
        Assertions.assertEquals("jsondata_test.go", result.getFileName());
    }
}