package br.com.fujideia.iesp.tecback.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/cache")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CacheController {


    @GetMapping
    @Cacheable("CACHE_HELLO")
    public String hello(){
        log.info("Hello");
        return "Texto Hello";
    }
}
