package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Person;

@Controller
public class HelloController {

	@GetMapping(value = "/hello")
	public String getPersonDetail(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8081/persons/{id}";
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "1");
		ResponseEntity<Person> entity = restTemplate.getForEntity(url, Person.class, map);
		String name = entity.getBody().getName();
		System.out.println("name is :" + name);
		model.addAttribute("person", entity.getBody());
		System.out.println(entity.getBody().getId());
		System.out.println(entity.getBody().getName());
		System.out.println(entity.getBody().getAge());
		System.out.println(entity.getBody().getEmailId());
		return "hello";
	}

	@GetMapping(value = "/save")
	public String savePersonInfo(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8081/persons";
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "aadhya");
		map.put("age", "3");
		map.put("emailId", "aadhya@gmail.com");
		Person person = new Person("radhe", 6, "radhe@gmail.com");
		ResponseEntity<Person> entity = restTemplate.postForEntity(url, person, Person.class, map);
		System.out.println(entity.getBody().getId());
		System.out.println(entity.getBody().getName());
		System.out.println(entity.getBody().getAge());
		System.out.println(entity.getBody().getEmailId());
		return "hello";
	}
}
