package com.spencer.ItemApp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.spencer.ItemApp.models.Item;
import com.spencer.ItemApp.service.ItemService;

@Controller
public class ItemController {
	private static String sort = "id";
	private static String direction = "asc";
	@Autowired 
	private ItemService itemService;
	@GetMapping({"/item","/item/id/","/item/date/","/item/brand/",
				 "/item/dept/","/item/itemClass/","/item/originalPrice/",
				 "/item/salePrice/","/item/activeFlag/","/item/imageFile/",
				 "/item/vairiants/"})
	public String getItemPage(@RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findAll(sort(sort)));
		return "item_view";
	}
	@GetMapping("/item/id/{id}")
	public String searchItemPageById(@PathVariable long id, @RequestParam(required=false, defaultValue="missing") String sort, Model m) { 
		ArrayList<Item> items = new ArrayList<>();
		items.add(itemService.findById(id));
		m.addAttribute("items", items);
		return "item_view";
	}
	@GetMapping("/item/date/{date}")
	public String searchItemPageByDate(@PathVariable String date, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findByDate(date, sort(sort)));
		return "item_view";
	}
	@GetMapping("/item/brand/{brand}")
	public String searchItemPageByBrand(@PathVariable String brand, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findByBrand(brand, sort(sort)));
		return "item_view";
	}
	@GetMapping("/item/dept/{dept}")
	public String searchItemPageByDept(@PathVariable String dept, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findByDept(dept, sort(sort)));
		return "item_view";	
	}
	@GetMapping("/item/itemClass/{itemClass}")
	public String searchItemPageByItemClass(@PathVariable String itemClass, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findByItemClass(itemClass, sort(sort)));
		return "item_view";
	}
	@GetMapping("/item/originalPrice/{originalPrice}")
	public String searchItemPageByOriginalPrice(@PathVariable double originalPrice, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findByOriginalPrice(originalPrice, sort(sort)));
		return "item_view";
	}
	@GetMapping("/item/salePrice/{salePrice}")
	public String searchItemPageBySalePrice(@PathVariable double salePrice, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findbySalePrice(salePrice, sort(sort)));
		return "item_view";
	}
	@GetMapping("/item/activeFlag/{activeFlag}")
	public String searchItemPageByActiveFlag(@PathVariable String activeFlag, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findByActiveFlag(activeFlag, sort(sort)));
		return "item_view";
	}
	@GetMapping("/item/imageFile/{imageFile}")
	public String searchItemPageByImageFile(@PathVariable String imageFile, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findByImageFile(imageFile, sort(sort)));
		return "item_view";
	}
	@GetMapping("/item/variants/{variants}")
	public String searchItemPageByVaraints(@PathVariable String variants, @RequestParam(required=false, defaultValue="missing") String sort,  Model m) {
		m.addAttribute("items", itemService.findByVariants(variants, sort(sort)));
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
	public static Sort sort(String sort) {
		if(ItemController.sort.equals(sort)) toggleDirection();
		else if(!sort.equals("missing")) {
			ItemController.sort = sort;
			ItemController.direction = "asc";
		}
		return Sort.by(ItemController.direction.equals("desc")?Sort.Direction.DESC:Sort.Direction.ASC, ItemController.sort);
	}
	public static void toggleDirection(){
		if (ItemController.direction.equals("asc")) {
			ItemController.direction = "desc";
			return;
		}
		ItemController.direction = "asc";
	}

}