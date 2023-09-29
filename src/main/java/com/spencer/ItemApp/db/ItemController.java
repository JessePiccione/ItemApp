package com.spencer.ItemApp.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ItemController {
	@Autowired 
	private ItemService itemService;
	@GetMapping("/item")
	public String getItemPage(Model m) {
		m.addAttribute("items", itemService.findAll());
		return "item_view";
	}
	@PostMapping("/item")
	public String postItemPage(@RequestBody(required=true) Item i, Model M) {
		itemService.save(i);
		return "item";
	}
	@DeleteMapping("/item/{id}")
	public String deleteItemFromPage(@PathVariable String id) {
		long l = Long.parseLong(id);
		itemService.delete(itemService.findById(l));
		return "item";
	}
	@PatchMapping("/item")
	public String patchItemFromPage(@RequestBody(required=true) Item i) {
		Item oldItem = itemService.findById(i.getId());
		if(oldItem != null) {
			i.updateNewValues(oldItem);
			itemService.save(i);
		}
		return "item";
	}
}