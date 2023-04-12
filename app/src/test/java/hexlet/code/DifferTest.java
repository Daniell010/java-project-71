package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DifferTest {

    private static String getCorrectResultString(String correctStringPath) throws Exception {
        Path correctStringAbsolutePath = Paths.get(correctStringPath).toAbsolutePath().normalize();
        return Files.readString(correctStringAbsolutePath).trim().replaceAll("\\r", "");
    }

    @Test
    public void diffJson() throws Exception {
        final String path1 = "./src/test/resources/input_files/file1.json";
        final String path2 = "./src/test/resources/input_files/file2.json";
        String pathExpected = "./src/test/resources/expected/expectedFile2.txt";
        String butWas = Differ.generate(path1, path2, "json");
        var str = getCorrectResultString(pathExpected);
        assertEquals(butWas, str);
    }

    @Test
    public void diffYml() throws Exception {
        String pathExpected = "./src/test/resources/expected/expectedFile.txt";
        final String path1 = "./src/test/resources/input_files/file3.yml";
        final String path2 = "./src/test/resources/input_files/file4.yml";
        String butWas = Differ.generate(path1, path2, "json");
        var str = getCorrectResultString(pathExpected);
        assertEquals(butWas, str);
    }

    @Test
    public void diffFormatPlain() throws Exception {
        String pathExpected = "./src/test/resources/expected/expectedFileForPlain.txt";
        final String path1 = "./src/test/resources/input_files/file3.yml";
        final String path2 = "./src/test/resources/input_files/file4.yml";
        String butWas = Differ.generate(path1, path2, "plain");
        var str = getCorrectResultString(pathExpected);
        assertEquals(butWas, str);
    }
    @Test
    public void diffFormatDefault() throws Exception {
        final String path1 = "./src/test/resources/input_files/file3.yml";
        final String path2 = "./src/test/resources/input_files/file4.yml";
        String pathExpected = "./src/test/resources/expected/expectedFileForDefault.txt";
        String butWus = Differ.generate(path1, path2);
        var str = getCorrectResultString(pathExpected);
        assertEquals(butWus, str);
    }
    @Test
    public void diffFormatStylish() throws Exception {
        final String path1 = "./src/test/resources/input_files/file3.yml";
        final String path2 = "./src/test/resources/input_files/file4.yml";
        String pathExpected = "./src/test/resources/expected/expectedFileForStylish.txt";
        String butWus = Differ.generate(path1, path2, "stylish");
        var str = getCorrectResultString(pathExpected);
        assertEquals(butWus, str);
    }

}
