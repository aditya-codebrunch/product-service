package dev.codebrunch.productservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/hello1/{name}")
    public String sample1(@PathVariable("name") String personName){
        return "Hello World from one to " + personName;
    }

    @GetMapping("/hello2")
    public String sample2(@RequestParam("x") int x){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < x; i++){
            sb.append("Hello World from two \n");
        }
        return sb.toString();
    }

}