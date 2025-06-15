package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.serenitybdd.rest.SerenityRest;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class RecordPetResponse {

    private final ObjectMapper mapper = new ObjectMapper();

    public Map<String, String> returned() {
        return mapOfStringsFrom(SerenityRest.lastResponse().getBody().as(Map.class));
    }

    private Map<String, String> mapOfStringsFrom(Map<String, Object> map) {
        return map.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> String.valueOf(entry.getValue())
                ));
    }

    public Map<String, String> fromJson(String json) throws IOException {
        Map<String, Object> rawMap = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        return mapOfStringsFrom(rawMap);
    }
}