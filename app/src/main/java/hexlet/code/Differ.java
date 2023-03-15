package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static java.nio.file.Files.readString; //Метод для чтения всех символов из файла в строку.

public class Differ {
    public static boolean differ(Map<String, Object> file1, Map<String, Object> file2, String key) throws Exception {
        Object obj1 = file1.get(key);
        Object obj2 = file2.get(key);
        return (obj1 == null || obj2 == null ? obj1 != obj2 : !obj1.equals(obj2));
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        Path file1 = Paths.get(filePath1).toAbsolutePath().normalize(); // Формируем абсолютный путь
        Path file2 = Paths.get(filePath2).toAbsolutePath().normalize();

        if (!Files.exists(file1)) { // Проверяем существование файлов
            throw new Exception("File '" + file1 + "' does not exist");
        } else if (!Files.exists(file2)) {
            throw new Exception("File '" + file2 + "' does not exist");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        // readValue используется для преобразования (десериализации) JSON из строки, потока или файла в POJO.
        Map<String, Object> map
                = objectMapper.readValue(readString(file1), new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> map2
                = objectMapper.readValue(readString(file2), new TypeReference<Map<String, Object>>() {
        });

        Map<String, Object> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(map.keySet());
        keys.addAll(map2.keySet());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

//        for (String key : keys) {
//            if (!map.containsKey(key)) {
//                result.put("+ " + key,map.get(key));
//                // ключ отсутвовал в первой мапе
//            } else if (!map2.containsKey(key)) {
//                result.put("- " + key, map2.get(key));
//                // ключ есть в первой маппе но нет во второй
//              } else if (map.get(key).equals(map2.get(key))) {
//                result.put("- " + key, map.get(key));
//                result.put("+ " + key, map2.get(key));
//                // ключ есть в первой и во второй мапе но значения разные
//                // - timeout: 50
//                // + timeout: 20
//            } else {
//                // ключи одинаковые
//                result.put("  " + key, map2.get(key));
//
//            }
//        }
        for (String key : keys) {
            if (map.containsKey(key) && !map2.containsKey(key)) {
                //result.put("- " + key, map.get(key));
                stringBuilder.append("  - " + key + " : " + map.get(key) + "\n");
            } else if (!map.containsKey(key) && map2.containsKey(key)) {
                //result.put("+ " + key, map2.get(key));
                stringBuilder.append("  + " + key + " : " + map2.get(key) + "\n");
            } else if (differ(map, map2, key)) {
                stringBuilder.append("  - " + key + " : " + map.get(key) + "\n");
                stringBuilder.append("  + " + key + " : " + map2.get(key) + "\n");
                //result.put("- " + key, map.get(key));
                //result.put("+ " + key, map2.get(key));
            } else {
                //result.put("  " + key, map2.get(key));
                stringBuilder.append("    " + key + " : " + map2.get(key) + "\n");
            }
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
