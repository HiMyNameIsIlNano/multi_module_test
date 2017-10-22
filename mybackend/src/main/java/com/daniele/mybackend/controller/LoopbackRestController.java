package com.daniele.mybackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daniele.mylogger.LogExecutionTime;

@RestController
@RequestMapping("/loopback")
public class LoopbackRestController {
	
	@GetMapping(path = "/{text}")
	@ResponseStatus(code = HttpStatus.OK)
	@LogExecutionTime
	public void getLoopback(@PathVariable("text") String text) {
		// There is no need to add a dto to map this result to an Object
		System.out.println("called loopback: " + text); 
	}
	
}