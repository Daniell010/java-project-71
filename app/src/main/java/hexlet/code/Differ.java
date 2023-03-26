package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


public class Differ {
    private static final String DEFAULT_FORMAT = "stylish";

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> map = fileToMap(filePath1);
        Map<String, Object> map2 = fileToMap(filePath2);
        return Formatter.format(format, map, map2);
    }

    public static Map<String, Object> fileToMap(String filePath) throws Exception {
        Path file = pathFile(filePath);
        Map<String, Object> map = null;
        if (filePath.endsWith(".json")) {
            map = Parser.jsonToMap(file);
        } else if (filePath.endsWith(".yml")) {
            map = Parser.ymlToMap(file);
        }
        return map;
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, DEFAULT_FORMAT);
    }

    public static Path pathFile(String string) throws Exception {
        Path path = Paths.get(string).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return path;
    }
}
