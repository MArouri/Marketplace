package edu.birzeit.marketplace.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class BasicResponse {

    private String status = "Success";
}
