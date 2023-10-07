package com.spencer.ItemApp.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

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
import org.springframework.web.multipart.MultipartFile;

import com.spencer.ItemApp.models.Item;
import com.spencer.ItemApp.service.ItemService;

@Controller
public class ItemController {
	private static String sort = "id";
	private static String direction = "asc";
	@Autowired 
	private ItemService itemService;
	@GetMapping({"/item","/item/id/","/item/sku","/item/date/","/item/brand/",
				 "/item/dept/","/item/itemClass/","/item/originalPrice/",
				 "/item/salePrice/","/item/activeFlag/","/item/imageFile/",
				 "/item/vairiants/","/upload/item"})
	public String getItemPage(@RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findAll(sort(sort)));
		return "item_view";
	}
	@GetMapping("/item/id/{id}")
	public String searchItemPageById(@PathVariable long id, @RequestParam(required=false, defaultValue="missing") String sort, Model m) { 
		m.addAttribute("items", itemService.findAllById(id,sort(sort)));
		m.addAttribute("id", id);
		return "item_view";
	}
	@GetMapping("/item/sku/{sku}")
	public String searchPageBySku(@PathVariable long sku, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findAllBySku(sku,sort(sort)));
		m.addAttribute("sku", sku);
		return "item_view";
	}
	@GetMapping("/item/date/{date}")
	public String searchItemPageByDate(@PathVariable String date, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findByDate(date, sort(sort)));
		m.addAttribute("date", date);
		return "item_view";
	}
	@GetMapping("/item/brand/{brand}")
	public String searchItemPageByBrand(@PathVariable String brand, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findByBrand(brand, sort(sort)));
		m.addAttribute("brand", brand);
		return "item_view";
	}
	@GetMapping("/item/dept/{dept}")
	public String searchItemPageByDept(@PathVariable String dept, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findByDept(dept, sort(sort)));
		m.addAttribute("dept", dept);
		return "item_view";	
	}
	@GetMapping("/item/itemClass/{itemClass}")
	public String searchItemPageByItemClass(@PathVariable String itemClass, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findByItemClass(itemClass, sort(sort)));
		m.addAttribute("itemClass", itemClass);
		return "item_view";
	}
	@GetMapping("/item/originalPrice/{originalPrice}")
	public String searchItemPageByOriginalPrice(@PathVariable double originalPrice, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findByOriginalPrice(originalPrice, sort(sort)));
		m.addAttribute("originalPrice", originalPrice);
		return "item_view";
	}
	@GetMapping("/item/salePrice/{salePrice}")
	public String searchItemPageBySalePrice(@PathVariable double salePrice, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findbySalePrice(salePrice, sort(sort)));
		m.addAttribute("salePrice", salePrice);
		return "item_view";
	}
	@GetMapping("/item/activeFlag/{activeFlag}")
	public String searchItemPageByActiveFlag(@PathVariable String activeFlag, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findByActiveFlag(activeFlag, sort(sort)));
		m.addAttribute("activeFlag", activeFlag);
		return "item_view";
	}
	@GetMapping("/item/imageFile/{imageFile}")
	public String searchItemPageByImageFile(@PathVariable String imageFile, @RequestParam(required=false, defaultValue="missing") String sort, Model m) {
		m.addAttribute("items", itemService.findByImageFile(imageFile, sort(sort)));
		m.addAttribute("imageFile", imageFile);
		return "item_view";
	}
	@GetMapping("/item/variants/{variants}")
	public String searchItemPageByVaraints(@PathVariable String variants, @RequestParam(required=false, defaultValue="missing") String sort,  Model m) {
		m.addAttribute("items", itemService.findByVariants(variants, sort(sort)));
		m.addAttribute("variants", variants);
		return "item_view";
	}
	@PostMapping("/item")
	public String postItemPage(@RequestBody(required=true) Item i, Model M) {
		itemService.save(i);
		return "redirect:/item";
	}
	@DeleteMapping("/item/{id}")
	public String deleteItemFromPage(@PathVariable long id) {
		itemService.delete(itemService.findByUniqueId(id));
		return "redirect:/item";
	}
	@PatchMapping("/item")
	public String patchItemFromPage(@RequestBody(required=true) Item i) {
		Item oldItem = itemService.findByUniqueId(i.getUniqueId());
		if(oldItem != null) {
			i.updateNewValues(oldItem);
			itemService.save(i);
		}
		return "item";
	}
	@PostMapping("/item/upload")
	public String handleIncomingFile(@RequestParam("file") MultipartFile file, Model m) {
		if(file.isEmpty()) {
			return "redirect:/item";
		}
		String fileName = file.getOriginalFilename();
		try(InputStream inputStream = file.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
			String line = bufferedReader.readLine(),temp;
			int i;
			while(line.contains("\t")) {
				i = line.indexOf('\t');
				temp = line.substring(0,i);
				switch(temp){
					case "product_id":
						break;
					case "sku":
						break;
					case "brand_code":
						break;
					case "category_breadcrumbs":
						break;
				}
				line = line.substring(i+1);
			}
			while((line = bufferedReader.readLine()) != null) {
				while(line.contains("\t")) {
					i = line.indexOf("\t");
					System.out.println(line.substring(0,i));
					line = line.substring(i+1);
				}
				break;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/item";
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