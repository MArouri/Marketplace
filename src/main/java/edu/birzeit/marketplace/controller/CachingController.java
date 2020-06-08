package edu.birzeit.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CachingController {

    private CacheManager cacheManager;

    public CachingController(@Autowired CacheManager cacheManager){
        this.cacheManager = cacheManager;
    }

    @Scheduled(fixedRate = 10000)
    public void clearCache() {
        System.out.println("clearCache called");
        this.cacheManager.getCache("accessToken").clear();
    }
}
