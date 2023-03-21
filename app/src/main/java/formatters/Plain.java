package formatters;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static formatters.Json.differ;

public class Plain {
    public static String formatPlain(Map<String, Object> map, Map<String, Object> map2) {

        Set<String> keys = new TreeSet<>(map.keySet());
        keys.addAll(map2.keySet());
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : keys) {
            if (map.containsKey(key) && !map2.containsKey(key)) { // есть в первой нет во второй
                stringBuilder.append("Property '")
                        .append(key)
                        .append("' was removed")
                        .append("\n");
            } else if (!map.containsKey(key) && map2.containsKey(key)) { // нет в первой есть во второй
                stringBuilder.append("Property '")
                        .append(key)
                        .append("' was added with value: ")
                        .append(getValue(map2.get(key)))
                        .append("\n");
            } else if (differ(map, map2, key)) {
                stringBuilder.append("Property '")
                        .append(key)
                        .append("' was updated. From ")
                        .append(getValue(map.get(key)))
                        .append(" to ")
                        .append(getValue(map2.get(key)))
                        .append("\n");
            }
        }

        return stringBuilder.toString().substring(0, stringBuilder.length() - 1);
    }

    private static String getValue(Object obj) {
        if (obj instanceof String) {
            return "'" + obj + "'";
        } else if (obj instanceof List || obj instanceof Map) {
            return "[complex value]";
        }
        return String.valueOf(obj);
    }
}

