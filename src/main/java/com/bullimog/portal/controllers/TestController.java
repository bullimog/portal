package com.bullimog.portal.controllers;

import com.bullimog.portal.models.ISpindelData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(path="/data")
public class TestController {

    @RequestMapping(value = "/tester", method = RequestMethod.POST)
    public ResponseEntity<String> tester(@RequestBody ISpindelData test) {
        System.out.println("Hi");
        System.out.println("Gravity is "+test.getGravity());
        return new ResponseEntity<String>("Done it!", HttpStatus.CREATED);
    }
}
