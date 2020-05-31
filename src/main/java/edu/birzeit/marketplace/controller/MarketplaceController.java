package edu.birzeit.marketplace.controller;

import edu.birzeit.marketplace.dto.Job;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value="/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
public class MarketplaceController {

    @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
    public List<Job> getAvailableJobs(){

        List<Job> jobs = new ArrayList<>();
        for (int i=0; i< new Random().nextInt(50); i++){
            jobs.add(new Job());
        }
        return jobs;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Job getJobById(@PathVariable("id") Long id){
        return new Job(id);
    }
}