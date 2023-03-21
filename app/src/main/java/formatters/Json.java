package formatters;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Json {
    public static boolean differ(Map<String, Object> file1, Map<String, Object> file2, String key) {
        Object obj1 = file1.get(key);
        Object obj2 = file2.get(key);
        return (obj1 == null || obj2 == null ? obj1 != obj2 : !obj1.equals(obj2));
    }

    public static String formatJson(Map<String, Object> map, Map<String, Object> map2) {

        Set<String> keys = new TreeSet<>(map.keySet());
        keys.addAll(map2.keySet());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (String key : keys) {
            if (map.containsKey(key) && !map2.containsKey(key)) {
                stringBuilder.append("  - ").append(key).append(": ").append(map.get(key)).append("\n");
            } else if (!map.containsKey(key) && map2.containsKey(key)) {
                stringBuilder.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
            } else if (differ(map, map2, key)) {
                stringBuilder.append("  - ").append(key).append(": ").append(map.get(key)).append("\n");
                stringBuilder.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
            } else {
                stringBuilder.append("    ").append(key).append(": ").append(map2.get(key)).append("\n");
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
