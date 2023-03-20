package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Scanner;


public class DifferTest {
    final String path1 = "./src/test/resources/input_files/file1.json";
    final String path2 = "./src/test/resources/input_files/file2.json";

    @Test
    public void diffJson() throws Exception {
        String pathExpected = "./src/test/resources/expected/expectedFile.txt";
        String butWas = Differ.generate(path1, path2, null);
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
    public void diffYml() throws Exception {

    }

}
