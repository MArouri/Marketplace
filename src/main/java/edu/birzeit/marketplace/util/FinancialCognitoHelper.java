package edu.birzeit.marketplace.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class FinancialCognitoHelper {

    @Value("${edu.birzeit.financial.client_id}")
    private String clientId;

    @Value("${edu.birzeit.financial.client_secret}")
    private String clientSecret;

    @Value("${edu.birzeit.financial.endpoint}")
    private String endPoint;

    @Cacheable("accessToken")
    public String getAccessToken() {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(this.clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> entity = new HttpEntity<String>("grant_type=client_credentials&scope=edu.birzeit/financial", headers);
        ResponseEntity<String> result = restTemplate.exchange(this.endPoint, HttpMethod.POST, entity, String.class);

        String accessToken = result.getStatusCode() == HttpStatus.OK ? getTokenFromRawJson(result.getBody()) : null;
        System.out.println("getAccessToken called " + LocalDateTime.now() + "\n" + accessToken);
        return accessToken;
    }

    private String getTokenFromRawJson(String json) {

        ObjectMapper mapper = new ObjectMapper();
        String token = null;
        try {
            Map<String, String> map = mapper.readValue(json, Map.class);
            token = map.get("access_token");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }
}
