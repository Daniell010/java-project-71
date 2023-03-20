package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static hexlet.code.Parser.fileToMap;
public class Differ {
    public static boolean differ(Map<String, Object> file1, Map<String, Object> file2, String key) {
        Object obj1 = file1.get(key);
        Object obj2 = file2.get(key);
        return (obj1 == null || obj2 == null ? obj1 != obj2 : !obj1.equals(obj2));
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {


        Map<String, Object> map = fileToMap(filePath1);
        Map<String, Object> map2 = fileToMap(filePath2);

        Set<String> keys = new TreeSet<>(map.keySet());
        keys.addAll(map2.keySet());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

        for (String key : keys) {
            if (map.containsKey(key) && !map2.containsKey(key)) {
                stringBuilder.append("  - " + key + ": " + map.get(key) + "\n");
            } else if (!map.containsKey(key) && map2.containsKey(key)) {
                stringBuilder.append("  + " + key + ": " + map2.get(key) + "\n");
            } else if (differ(map, map2, key)) {
                stringBuilder.append("  - " + key + ": " + map.get(key) + "\n");
                stringBuilder.append("  + " + key + ": " + map2.get(key) + "\n");
            } else {
                stringBuilder.append("    " + key + ": " + map2.get(key) + "\n");
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static Path pathFile(String string) throws Exception { // Формируем абсолютный путь
        Path path = Paths.get(string).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return path;
    }

//    public static Map<String, Object> fileToMap(String filePath) throws Exception {
//        Path file = pathFile(filePath);
//        Map<String, Object> map = null;
//        if (filePath.endsWith(".json")) {
//            map = jsonToMap(file);
//        } else if (file.endsWith(".yml")) {
//            map = ymlToMap(file);
//        }
//        return map;
//    }

//    private static Map<String, Object> jsonToMap(Path file) throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        // readValue используется для преобразования (десериализации) JSON из строки, потока или файла в POJO.
//        return objectMapper.readValue(readString(file), new TypeReference<Map<String, Object>>() {
//        });
//    }
//
//    private static Map<String, Object> ymlToMap(Path file) throws Exception {
//        ObjectMapper objectMapper = new YAMLMapper();
//        return objectMapper.readValue(readString(file), new TypeReference<Map<String, Object>>() {
//        });
//    }

}
