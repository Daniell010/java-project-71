package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Scanner;


public class DifferTest {
    private final String str = "Property 'chars2' was updated. From [complex value] to false\n"
            +  "Property 'checked' was updated. From false to true\n"
            + "Property 'default' was updated. From null to [complex value]\n"
            + "Property 'id' was updated. From 45 to null\n"
            + "Property 'key1' was removed\n"
            + "Property 'key2' was added with value: 'value2'\n"
            + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
            + "Property 'numbers3' was removed\n"
            + "Property 'numbers4' was added with value: [complex value]\n"
            + "Property 'obj1' was added with value: [complex value]\n"
            + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
            + "Property 'setting2' was updated. From 200 to 300\n"
            + "Property 'setting3' was updated. From true to 'none'";
    @Test
    public void diffJson() throws Exception {
        final String path1 = "./src/test/resources/input_files/file1.json";
        final String path2 = "./src/test/resources/input_files/file2.json";
        String pathExpected = "./src/test/resources/expected/expectedFile.txt";
        String butWas = Differ.generate(path1, path2, "json");
        File file = new File(pathExpected);
        Scanner scanner = new Scanner(file);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine());
            if (scanner.hasNext()) {
                stringBuilder.append("\n");
            }
        }
        assertEquals(butWas, stringBuilder.toString());
    }

    @Test
    public void diffYml() throws Exception {
        String pathExpected = "./src/test/resources/expected/expectedFile2.txt";
        final String path1 = "./src/test/resources/input_files/file3.yml";
        final String path2 = "./src/test/resources/input_files/file4.yml";
        String butWas = Differ.generate(path1, path2, "json");
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(pathExpected);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine());
            if (scanner.hasNext()) {
                stringBuilder.append("\n");
            }
        }
        assertEquals(butWas, stringBuilder.toString());
    }
    @Test
    public void diffFormatPlain() throws Exception {
        String pathExpected = "./src/test/resources/expected/expectedFileForPlain.txt";
        final String path1 = "./src/test/resources/input_files/file3.yml";
        final String path2 = "./src/test/resources/input_files/file4.yml";
        String butWas = Differ.generate(path1, path2, "plain");
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(pathExpected);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine());
            if (scanner.hasNext()) {
                stringBuilder.append("\n");
            }
        }
        assertEquals(butWas, str);
    }

}
