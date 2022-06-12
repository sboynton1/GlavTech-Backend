package tech.GlavTech.SD2022.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class RequestOperations {

    public RequestOperations() {}

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    private static final ObjectMapper mapper = new ObjectMapper();

    public String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

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

    public String ObjectToJSON(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}
