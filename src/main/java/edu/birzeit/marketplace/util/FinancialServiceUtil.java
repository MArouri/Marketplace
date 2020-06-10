package edu.birzeit.marketplace.util;

import edu.birzeit.marketplace.dto.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class FinancialServiceUtil {

    @Value("${edu.birzeit.financial.protocol}")
    private String protocol;

    @Value("${edu.birzeit.financial.base_url}")
    private String baseUrl;

    @Value("${edu.birzeit.financial.port}")
    private int port;

    private FinancialCognitoHelper financialCognitoHelper;

    public FinancialServiceUtil(@Autowired FinancialCognitoHelper financialCognitoHelper){
        this.financialCognitoHelper = financialCognitoHelper;
    }

    public Subscription[] getActiveSubscriptions(Long businessId){

        final String uri = protocol + "://" + baseUrl + ":" + port + "/financial/businesses/" + businessId +"/subscriptions";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(this.financialCognitoHelper.getAccessToken());

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        System.out.println("Financial uri: " + uri);
        ResponseEntity<Subscription[]> result = restTemplate.exchange(uri, HttpMethod.GET, entity, Subscription[].class);
        System.out.println(result);

        if (result.getStatusCode() == HttpStatus.OK) {
            result.getBody();
        }

        return null;
    }
}
