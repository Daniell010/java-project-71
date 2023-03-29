package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;


public class Json {

    public static String formatJson(ArrayList<Map<String, Object>> diff) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(diff);
    }
}
