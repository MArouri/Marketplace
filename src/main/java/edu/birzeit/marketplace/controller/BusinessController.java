package edu.birzeit.marketplace.controller;

import edu.birzeit.marketplace.dto.BasicResponse;
import edu.birzeit.marketplace.dto.Job;
import edu.birzeit.marketplace.dto.Subscription;
import edu.birzeit.marketplace.util.FinancialServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value="/businesses/{businessId}", produces = MediaType.APPLICATION_JSON_VALUE)
public class BusinessController {

    private FinancialServiceUtil financialServiceUtil;

   public BusinessController(@Autowired FinancialServiceUtil financialServiceUtil){
       this.financialServiceUtil = financialServiceUtil;
   }

    @RequestMapping(value = {"/jobs"}, method = RequestMethod.GET)
    public List<Job> getAvailableJobs(Authentication authentication, @PathVariable Long businessId) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User has authorities: " + userDetails.getAuthorities());

        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(49)+1; i++) {
            jobs.add(new Job());
        }
        return jobs;
    }

    @RequestMapping(value = {"/jobs"}, method = RequestMethod.POST)
    public Job createJob(@PathVariable Long businessId){
        return new Job();
    }

    @RequestMapping(value = {"/jobs/{jobId}/publish"}, method = RequestMethod.POST)
    public BasicResponse publishJob(@PathVariable Long businessId, @PathVariable Long jobId){

        Subscription[] result = financialServiceUtil.getActiveSubscriptions(businessId);
        System.out.println(result);

        return new BasicResponse();
    }

    @RequestMapping(value = {"/jobs/{jobId}/apply"}, method = RequestMethod.POST)
    public BasicResponse applyForJob(@PathVariable Long businessId, @PathVariable Long jobId){

        return new BasicResponse();
    }
}
