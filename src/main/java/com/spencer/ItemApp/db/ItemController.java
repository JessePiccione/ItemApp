package com.spencer.ItemApp.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ItemController {
	@Autowired 
	private ItemService itemService;
	@GetMapping("/item")
	public String getItemPage(Model m) {
		System.out.println("I do not work here");
		m.addAttribute("items", itemService.findAll());
		return "item_view";
	}
	
	@PostMapping("/item")
	public String postItemPage(@RequestBody(required=true) Item i, Model M) {
		System.out.println("But I do work here");
		itemService.save(i);
		return "item";
	}
	
}
