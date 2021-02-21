package com.main.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main 
{

	@GetMapping("/")
	public String Home(Model model) 
	{
		model.addAttribute("title", "Welcome");
		return "index";
	}
	
	@GetMapping("/about")
	public String About(Model model) 
	{
		model.addAttribute("title", "Welcome");
		return "index";
	}
}