package edu.birzeit.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Getter @Setter
public class Job {

    private Long id = Math.abs(new Random().nextLong()%10000);
    private String title;

    @JsonIgnore
    private String[] jobs = new String[]{"Bartender","Server","Sous Chef","Food Runner","Dishwasher","Receptionist"};

    public Job(){
        setId(Math.abs(new Random().nextLong()%10000));
        setTitle(this.jobs[new Random().nextInt(this.jobs.length)]);
    }

    public Job(Long id) {
        setId(id);
        setTitle(this.jobs[new Random().nextInt(this.jobs.length)]);
    }
}