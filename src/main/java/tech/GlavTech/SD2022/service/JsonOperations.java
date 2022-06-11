package tech.GlavTech.SD2022.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class JsonOperations {

    public JsonOperations() {}

    /**
     * Used for creating wuick json objects
     * @param key: key string
     * @param value: value string for key
     * @return JSON object with key value pair
     */
    public JSONObject toJSON(String key, Object value) {
        return new JSONObject().put(key, value);
    }

    /**
     * Used for creating quick json object strings
     * @param key: key string
     * @param value: value string for key
     * @return JSON String with key value pair
     */
    public String toJSONString(String key, Object value) {
        return new JSONObject().put(key, value).toString();
    }
}
