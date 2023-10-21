package com.spencer.ItemApp.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private static final int pageSize = 12;
	@Autowired 
	private ItemService itemService;
	@GetMapping({"/","/home","/index"})
	public String getHomePage(Model m){
		m.addAttribute("url","/item");
		return "item_view";
	}
	@GetMapping("/count")
	public ResponseEntity<String> getCount(){
		return new ResponseEntity(itemService.countAllItems(), HttpStatus.OK);
	}
	@GetMapping({"/item","/item/id/","/item/sku","/item/date/","/item/brand/",
				 "/item/dept/","/item/itemClass/","/item/originalPrice/",
				 "/item/salePrice/","/item/activeFlag/","/item/imageFile/",
				 "/item/vairiants/","/upload/item"})
	public ResponseEntity<String> getItems(@RequestParam(required=false, defaultValue="missing") String sort,
										   @RequestParam(required=false, defaultValue="-1") int pageNumber,
										   @RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findAll(page(pageNumber!=-1?pageNumber:0,
													itemsPerPage!=-1?itemsPerPage:pageSize,
													sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@GetMapping("/item/id/{id}")
	public ResponseEntity<String> searchItemsById(@PathVariable long id,
												  @RequestParam(required=false, defaultValue="missing") String sort,
												  @RequestParam(required=false, defaultValue="-1") int pageNumber,
			                                      @RequestParam(required=false, defaultValue="-1") int itemsPerPage) { 
		List<Item> items = itemService.findAllById(id, page(pageNumber!=-1?pageNumber:0,
															itemsPerPage!=-1?itemsPerPage:pageSize,
															sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@GetMapping("/item/sku/{sku}")
	public ResponseEntity<String> searchItemsBySku(@PathVariable long sku,
												   @RequestParam(required=false, defaultValue="missing") String sort,
												   @RequestParam(required=false, defaultValue="-1") int pageNumber,
			                                       @RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findAllBySku(sku, page(pageNumber!=-1?pageNumber:0,
															  itemsPerPage!=-1?itemsPerPage:pageSize,
															  sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@GetMapping("/item/date/{date}")
	public ResponseEntity<String> searchItemsByDate(@PathVariable String date,
													@RequestParam(required=false, defaultValue="missing") String sort,
													@RequestParam(required=false, defaultValue="-1") int pageNumber,
													@RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findByDate(date, page(pageNumber!=-1?pageNumber:0,
															 itemsPerPage!=-1?itemsPerPage:pageSize,
															 sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@GetMapping("/item/brand/{brand}")
	public ResponseEntity<String> searchItemsByBrand(@PathVariable String brand,
													 @RequestParam(required=false, defaultValue="missing") String sort,
													 @RequestParam(required=false, defaultValue="-1") int pageNumber,
													 @RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findByBrand(brand, page(pageNumber!=-1?pageNumber:0,
															   itemsPerPage!=-1?itemsPerPage:pageSize,
															   sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@GetMapping("/item/dept/{dept}")
	public ResponseEntity<String> searchItemsByDept(@PathVariable String dept,
													@RequestParam(required=false, defaultValue="missing") String sort,
													@RequestParam(required=false, defaultValue="-1") int pageNumber,
													@RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findByDept(dept, page(pageNumber!=-1?pageNumber:0,
															 itemsPerPage!=-1?itemsPerPage:pageSize,
															 sort));
		return new ResponseEntity(items, HttpStatus.OK);	
	}
	@GetMapping("/item/itemClass/{itemClass}")
	public ResponseEntity<String> searchItemsByItemClass(@PathVariable String itemClass,
														 @RequestParam(required=false, defaultValue="missing") String sort,
														 @RequestParam(required=false, defaultValue="-1") int pageNumber,
														 @RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findByItemClass(itemClass, page(pageNumber!=-1?pageNumber:0,
																	   itemsPerPage!=-1?itemsPerPage:pageSize,
																	   sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@GetMapping("/item/originalPrice/{originalPrice}")
	public ResponseEntity<String> searchItemByOriginalPrice(@PathVariable double originalPrice,
															@RequestParam(required=false, defaultValue="missing") String sort,
															@RequestParam(required=false, defaultValue="-1") int pageNumber,
															@RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findByOriginalPrice(originalPrice, page(pageNumber!=-1?pageNumber:0,
														   					   itemsPerPage!=-1?itemsPerPage:pageSize,
														   					   sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@GetMapping("/item/salePrice/{salePrice}")
	public ResponseEntity<String> searchItemPageBySalePrice(@PathVariable double salePrice,
															@RequestParam(required=false, defaultValue="missing") String sort,
															@RequestParam(required=false, defaultValue="-1") int pageNumber,
															@RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findbySalePrice(salePrice, page(pageNumber!=-1?pageNumber:0,
																	   itemsPerPage!=-1?itemsPerPage:pageSize,
																	   sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@GetMapping("/item/activeFlag/{activeFlag}")
	public ResponseEntity<String> searchItemPageByActiveFlag(@PathVariable String activeFlag,
															 @RequestParam(required=false, defaultValue="missing") String sort, 
															 @RequestParam(required=false, defaultValue="-1") int pageNumber,
															 @RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findByActiveFlag(activeFlag, page(pageNumber!=-1?pageNumber:0,
																		 itemsPerPage!=-1?itemsPerPage:pageSize,
																		 sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@GetMapping("/item/imageFile/{imageFile}")
	public ResponseEntity<String> searchItemPageByImageFile(@PathVariable String imageFile,
															@RequestParam(required=false, defaultValue="missing") String sort,
															@RequestParam(required=false, defaultValue="-1") int pageNumber,
															@RequestParam(required=false, defaultValue="-1") int itemsPerPage) {

		List<Item> items = itemService.findByImageFile(imageFile, page(pageNumber!=-1?pageNumber:0,
																	   itemsPerPage!=-1?itemsPerPage:pageSize,
																	   sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@GetMapping("/item/variants/{variants}")
	public ResponseEntity<String> searchItemPageByVaraints(@PathVariable String variants,
														   @RequestParam(required=false, defaultValue="missing") String sort, 
														   @RequestParam(required=false, defaultValue="-1") int pageNumber,
														   @RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findByVariants(variants, page(pageNumber!=-1?pageNumber:0,
																	 itemsPerPage!=-1?itemsPerPage:pageSize,
																	 sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@PostMapping("/item")
	public String postItemPage(@RequestBody(required=true) Item i, Model M) {
		itemService.save(i);
		return "redirect:/item";
	}
	@DeleteMapping("/item/{id}")
	public String deleteItemFromPage(@PathVariable String id) {
		itemService.delete(itemService.findByUniqueId(Long.parseLong(id)));
		return "redirect:/item";
	}
	@PatchMapping("/item")
	public ResponseEntity<String> patchItemFromPage(@RequestBody(required=true) Item i) {
		Item oldItem = itemService.findByUniqueId(i.getUniqueId());
		if(oldItem != null) {
			i.updateNewValues(oldItem);
			itemService.save(i);
		}
		List<Item> items = itemService.findAll(page(0, pageSize, "missing"));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@PostMapping("/item/upload")
	public String handleIncomingFile(@RequestParam("file") MultipartFile file,@RequestParam("date") String date, Model m) {
		if(file.isEmpty()) {
			return "redirect:/home";
		}
		try(InputStream inputStream = file.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
			String[] header = bufferedReader.readLine().split("\t");
			Map<String, Integer> locations = new HashMap<>();
			Map<String, ArrayList<String>> variants = new HashMap<>();
			for(int i = 0; i < header.length; i++) {
				locations.put(header[i],i);
			}
			String temp;
			String[] values;
			ArrayList<Item> items = new ArrayList<>();
			while((temp = bufferedReader.readLine())!= null) {
				values = temp.replace("\"", "").split("\t");
				if(!variants.containsKey(values[locations.get("product_id")])) {
					variants.put(values[locations.get("product_id")],new ArrayList<>());
				}
				variants.get(values[locations.get("product_id")]).add(values[locations.get("sku")]);
				items.add(new Item(
							values[locations.get("product_id")],
							values[locations.get("sku")],
							date,
							values[locations.get("brand")],
							values[locations.get("category_breadcrumbs")].substring(0,
								values[locations.get("category_breadcrumbs")].indexOf(">")!=-1?
									values[locations.get("category_breadcrumbs")].indexOf(">"):
									values[locations.get("category_breadcrumbs")].length()),												
							values[locations.get("size")],
							Double.parseDouble(values[locations.get("price")]),
							Double.parseDouble(values[locations.get("sale_price")]),
							values[locations.get("is_active")].contains("1")?"Y":"N",
							values[locations.get("image_url")],
							""));
			}
			for(Item i: items) {
				String variant = variants.get(i.getId()).toString().replace("\"","");
				i.setVariants(variant.substring(1,variant.length()-1));
			}
			itemService.saveAll(items);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/home";
	}
	public static Pageable page(int pageNumber, int itemsPerPage, String sort) {
		return PageRequest.of(pageNumber, itemsPerPage, sort(sort));
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