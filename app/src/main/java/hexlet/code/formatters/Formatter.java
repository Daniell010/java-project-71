package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(String format, Map<String,
            Object> map, Map<String, Object> map2) throws Exception {

        return switch (format) {
            case "json" -> Json.formatJson(map, map2);
            case "plain" -> Plain.formatPlain(map, map2);
            case "stylish" -> Stylish.formatStylish(map, map2);
            default -> "Format error";
        };
    }
}
