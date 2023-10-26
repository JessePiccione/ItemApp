package com.spencer.ItemApp.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
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
import com.spencer.ItemApp.models.MyData;
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
	@PostMapping({"/count"})
	public ResponseEntity<String> getSpecialCount(@RequestBody MyData body, @RequestParam String type){
		System.out.print(false);
		return new ResponseEntity(itemService.count(type, body.getValues()),HttpStatus.OK);
	}
	@GetMapping({"/item"})
	public ResponseEntity<String> getItems(@RequestParam(required=false, defaultValue="missing") String sort,
										   @RequestParam(required=false, defaultValue="-1") int pageNumber,
										   @RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findAll(page(pageNumber!=-1?pageNumber:0,
													itemsPerPage!=-1?itemsPerPage:pageSize,
													sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@GetMapping({"/item/graphData"})
	public ResponseEntity<String> getGraphData(@RequestParam String date, @RequestParam String sku){
		List<Item> items = itemService.last20DaysWithSku(LocalDate.parse(date), sku);
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@PostMapping("/item/id")
	public ResponseEntity<String> searchItemsById(@RequestBody MyData id,
												  @RequestParam(required=false, defaultValue="missing") String sort,
												  @RequestParam(required=false, defaultValue="-1") int pageNumber,
			                                      @RequestParam(required=false, defaultValue="-1") int itemsPerPage) { 
		List<Item> items = itemService.findAllById(id.getValues(), page(pageNumber!=-1?pageNumber:0,
															itemsPerPage!=-1?itemsPerPage:pageSize,
															sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@PostMapping("/item/sku")
	public ResponseEntity<String> searchItemsBySku(@RequestBody MyData sku,
												   @RequestParam(required=false, defaultValue="missing") String sort,
												   @RequestParam(required=false, defaultValue="-1") int pageNumber,
			                                       @RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findAllBySku(sku.getValues(), page(pageNumber!=-1?pageNumber:0,
															  itemsPerPage!=-1?itemsPerPage:pageSize,
															  sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@PostMapping("/item/date")
	public ResponseEntity<String> searchItemsByDate(@RequestBody MyData date,
													@RequestParam(required=false, defaultValue="missing") String sort,
													@RequestParam(required=false, defaultValue="-1") int pageNumber,
													@RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		ArrayList<LocalDate> dates = new ArrayList<>();
		for (String s: date.getValues()) {
			dates.add(LocalDate.parse(s));
		}
		List<Item> items = itemService.findByDate(dates, page(pageNumber!=-1?pageNumber:0,
															 itemsPerPage!=-1?itemsPerPage:pageSize,
															 sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@PostMapping("/item/brand")
	public ResponseEntity<String> searchItemsByBrand(@RequestBody MyData brand,
													 @RequestParam(required=false, defaultValue="missing") String sort,
													 @RequestParam(required=false, defaultValue="-1") int pageNumber,
													 @RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findByBrand(brand.getValues(), page(pageNumber!=-1?pageNumber:0,
															   itemsPerPage!=-1?itemsPerPage:pageSize,
															   sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@PostMapping("/item/dept")
	public ResponseEntity<String> searchItemsByDept(@RequestBody MyData dept,
													@RequestParam(required=false, defaultValue="missing") String sort,
													@RequestParam(required=false, defaultValue="-1") int pageNumber,
													@RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findByDept(dept.getValues(), page(pageNumber!=-1?pageNumber:0,
															 itemsPerPage!=-1?itemsPerPage:pageSize,
															 sort));
		return new ResponseEntity(items, HttpStatus.OK);	
	}
	@PostMapping("/item/itemClass")
	public ResponseEntity<String> searchItemsByItemClass(@RequestBody MyData itemClass,
														 @RequestParam(required=false, defaultValue="missing") String sort,
														 @RequestParam(required=false, defaultValue="-1") int pageNumber,
														 @RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findByItemClass(itemClass.getValues(), page(pageNumber!=-1?pageNumber:0,
																	   itemsPerPage!=-1?itemsPerPage:pageSize,
																	   sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@PostMapping("/item/originalPrice")
	public ResponseEntity<String> searchItemByOriginalPrice(@RequestBody MyData originalPrice,
															@RequestParam(required=false, defaultValue="missing") String sort,
															@RequestParam(required=false, defaultValue="-1") int pageNumber,
															@RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findByOriginalPrice(originalPrice.getValues(), page(pageNumber!=-1?pageNumber:0,
														   					   itemsPerPage!=-1?itemsPerPage:pageSize,
														   					   sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@PostMapping("/item/salePrice")
	public ResponseEntity<String> searchItemPageBySalePrice(@RequestBody MyData salePrice,
															@RequestParam(required=false, defaultValue="missing") String sort,
															@RequestParam(required=false, defaultValue="-1") int pageNumber,
															@RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findbySalePrice(salePrice.getValues(), page(pageNumber!=-1?pageNumber:0,
																	   itemsPerPage!=-1?itemsPerPage:pageSize,
																	   sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@PostMapping("/item/activeFlag")
	public ResponseEntity<String> searchItemPageByActiveFlag(@RequestBody MyData activeFlag,
															 @RequestParam(required=false, defaultValue="missing") String sort, 
															 @RequestParam(required=false, defaultValue="-1") int pageNumber,
															 @RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findByActiveFlag(activeFlag.getValues(), page(pageNumber!=-1?pageNumber:0,
																		 itemsPerPage!=-1?itemsPerPage:pageSize,
																		 sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@PostMapping("/item/imageFile")
	public ResponseEntity<String> searchItemPageByImageFile(@RequestBody MyData imageFile,
															@RequestParam(required=false, defaultValue="missing") String sort,
															@RequestParam(required=false, defaultValue="-1") int pageNumber,
															@RequestParam(required=false, defaultValue="-1") int itemsPerPage) {

		List<Item> items = itemService.findByImageFile(imageFile.getValues(), page(pageNumber!=-1?pageNumber:0,
																	   itemsPerPage!=-1?itemsPerPage:pageSize,
																	   sort));
		return new ResponseEntity(items, HttpStatus.OK);
	}
	@PostMapping("/item/variants")
	public ResponseEntity<String> searchItemPageByVaraints(@RequestBody MyData variants,
														   @RequestParam(required=false, defaultValue="missing") String sort, 
														   @RequestParam(required=false, defaultValue="-1") int pageNumber,
														   @RequestParam(required=false, defaultValue="-1") int itemsPerPage) {
		List<Item> items = itemService.findByVariants(variants.getValues(), page(pageNumber!=-1?pageNumber:0,
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
							"",0));
			}
			for(Item i: items) {
				String variant = variants.get(i.getId()).toString().replace("\"","");
				i.setVariants(variant.substring(1,variant.length()-1));
			}
			List<Item> last20 = itemService.last20Days(LocalDate.parse(date));
			rateEntries(items, last20);
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
	public static void mapRatings(HashMap<String, Integer> ratings, List<Item> items) {
		int count;
		for (Item i: items) {
			if(i.getActiveFlag().equals("Y")) {
				if(!ratings.containsKey(i.getSku())) {
					ratings.put(i.getSku(), 1);
				}
				else {
					count = ratings.get(i.getSku());
					count++;
					ratings.put(i.getSku(), count);
				}
			}
		}
	}
	public static void setRatings(List<Item> items, HashMap<String, Integer> ratings) {
		for(Item i: items) {
			if(ratings.containsKey(i.getSku())) {
				i.setRating(ratings.get(i.getSku()));
			}
			//item is no longer active essentially if it gets to this else.
			else {
				i.setRating(0);
			}
		}
	}
	public static void rateEntries(List<Item> items, List<Item> last20) {
		HashMap<String, Integer> ratings = new HashMap<>();
		mapRatings(ratings, last20);
		mapRatings(ratings, items);
		setRatings(items, ratings);
	}
	public static Sort sort(String sort) {
		return Sort.by(Sort.Order.desc("date"), Sort.Order.asc("id"));
	}
}