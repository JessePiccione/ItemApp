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
	@GetMapping("/item/id/{id}")
	public String searchItemPageById(@PathVariable long id, Model m) { 
		m.addAttribute("items", itemService.findById(id));
		return "item_view";
	}
	@GetMapping("/item/date/{date}")
	public String searchItemPageByDate(@PathVariable String date, Model m) {
		m.addAttribute("items", itemService.findByDate(date));
		return "item_view";
	}
	@GetMapping("/item/brand/{brand}")
	public String searchItemPageByBrand(@PathVariable String brand, Model m) {
		m.addAttribute("items", itemService.findByBrand(brand));
		return "item_view";
	}
	@GetMapping("/item/dept/{dept}")
	public String searchItemPageByDept(@PathVariable String dept, Model m) {
		m.addAttribute("items", itemService.findByDept(dept));
		return "item_view";	
	}
	@GetMapping("/item/itemClass/{itemClass}")
	public String searchItemPageByItemClass(@PathVariable String itemClass, Model m) {
		m.addAttribute("items", itemService.findByItemClass(itemClass));
		return "item_view";
	}
	@GetMapping("/item/originalPrice/{originalPrice}")
	public String searchItemPageByOriginalPrice(@PathVariable double originalPrice, Model m) {
		m.addAttribute("items", itemService.findByOriginalPrice(originalPrice));
		return "item_view";
	}
	@GetMapping("/item/salePrice/{salePrice}")
	public String searchItemPageBySalePrice(@PathVariable double salePrice, Model m) {
		m.addAttribute("items", itemService.findbySalePrice(salePrice));
		return "item_view";
	}
	@GetMapping("/item/activeFlag/{activeFlag}")
	public String searchItemPageByActiveFlag(@PathVariable String activeFlag, Model m) {
		m.addAttribute("items", itemService.findByActiveFlag(activeFlag));
		return "item_view";
	}
	@GetMapping("/item/imageFile/{imageFile}")
	public String searchItemPageByImageFile(@PathVariable String imageFile, Model m) {
		m.addAttribute("items", itemService.findByImageFile(imageFile));
		return "item_view";
	}
	@GetMapping("/item/variants/{variants}")
	public String searchItemPageByVaraints(@PathVariable String variants, Model m) {
		m.addAttribute("items", itemService.findByVariants(variants));
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