package hexlet.code.formatters;

import hexlet.code.DiffBuilder;

import java.util.Map;

public class Formatter {
    public static String format(String format, Map<String,
            Object> map, Map<String, Object> map2) throws Exception {

        return switch (format) {
            case "json" -> Json.formatJson(DiffBuilder.diffFormat(map, map2));
            case "plain" -> Plain.formatPlain(DiffBuilder.diffFormat(map, map2));
            case "stylish" -> Stylish.formatStylish(DiffBuilder.diffFormat(map, map2));
            default -> "Format error";
        };
    }
}
