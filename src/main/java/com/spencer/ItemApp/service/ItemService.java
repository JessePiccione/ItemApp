package com.spencer.ItemApp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spencer.ItemApp.models.Item;
import com.spencer.ItemApp.models.RatedItem;
import com.spencer.ItemApp.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;
	public static void convertToDoubles(List<Double> doubleArr, List<String> stringArr) {
		for (String s : stringArr) {
			doubleArr.add(Double.parseDouble(s));
		}
	}
	//regular crud operations 
	public long count(String type,
					  List<String> values,
					  String startDate,
					  String endDate,
					  String flag) {
		long count = 0;
		ArrayList<Double> doubleValues;
		ArrayList<LocalDate> dateValues; 
		switch(type){
			case "id":
				count=itemRepository.countId(values,
											 LocalDate.parse(startDate),
											 LocalDate.parse(endDate),
											 getFlag(flag));
				break;
			case "sku":
				count=itemRepository.countSku(values,
											  LocalDate.parse(startDate),
											  LocalDate.parse(endDate),
											  getFlag(flag));
				break;
			case "date":
				dateValues = new ArrayList<>();
				for(String s: values) {
					dateValues.add(LocalDate.parse(s));
				}
				count=itemRepository.countDate(dateValues,
											   LocalDate.parse(startDate), 
											   LocalDate.parse(endDate), 
											   getFlag(flag));
				break;
			case "brand":
				count=itemRepository.countBrand(values,
												LocalDate.parse(startDate),
												LocalDate.parse(endDate),
												getFlag(flag));
				break;
			case "dept":
				count=itemRepository.countDept(values,
											   LocalDate.parse(startDate),
											   LocalDate.parse(endDate),
											   getFlag(flag));
				break;
			case "itemClass":
				count=itemRepository.countClass(values,
												LocalDate.parse(startDate),
												LocalDate.parse(endDate),
												getFlag(flag));
				break;
			case "originalPrice":
				doubleValues = new ArrayList<>();
				convertToDoubles(doubleValues, values);
;				count=itemRepository.countOriginalPrice(doubleValues,
														LocalDate.parse(startDate),
														LocalDate.parse(endDate),
														getFlag(flag));
				break;
			case "salePrice":
				doubleValues = new ArrayList<>();
				convertToDoubles(doubleValues, values);
				count=itemRepository.countSalePrice(doubleValues,
													LocalDate.parse(startDate),
													LocalDate.parse(endDate),
													getFlag(flag));
				break;
			case "activeFlag":
				count=itemRepository.countActiveflag(values,
													 LocalDate.parse(startDate),
													 LocalDate.parse(endDate),
													 getFlag(flag));
				break;
			case "imageFile":
				count=itemRepository.countImageFile(values,
													LocalDate.parse(startDate),
													LocalDate.parse(endDate),
													getFlag(flag));
				break;
			case "variants":
				count=itemRepository.countVariants(values,
												   LocalDate.parse(startDate),
												   LocalDate.parse(endDate),
												   getFlag(flag));
				break;
		}
		return count;
	}
	public long countAllItems(String startDate,
							  String endDate,
							  String flag) {
		return itemRepository.count(LocalDate.parse(startDate),
									LocalDate.parse(endDate),
									getFlag(flag));
	}
	public List<RatedItem> findAll(Pageable page,
								   String startDate,
								   String endDate,
								   String flag){
		return rateItemsOverPeriod(itemRepository.findAll(page,
								   						  LocalDate.parse(startDate),
								   						  LocalDate.parse(endDate),
								   						  getFlag(flag)),
								   startDate,
								   endDate,
								   flag);
	}
	public void delete(Item i) {
		itemRepository.delete(i);
	}
	public List<RatedItem> findAllById(List<String> id,
									   Pageable page,
									   String startDate,
									   String endDate,
									   String flag) {
		return rateItemsOverPeriod(itemRepository.findByIdIn(id,
															 page,
															 LocalDate.parse(startDate),
															 LocalDate.parse(endDate),
															 getFlag(flag)),
								   startDate,
								   endDate,
								   flag);
	}
	public List<RatedItem> findAllBySku(List<String> sku,
										Pageable page,
										String startDate,
										String endDate,
										String flag){
		return rateItemsOverPeriod(itemRepository.findBySkuIn(sku,
															  page,
															  LocalDate.parse(startDate),
															  LocalDate.parse(endDate),
															  getFlag(flag)),
								   startDate,
								   endDate,
								   flag);
	}
	public Item save(Item i) {
		return itemRepository.save(i);
	}
	public Iterable saveAll(List<Item> list) {
		return itemRepository.saveAll(list);
	}
	public List<RatedItem> findByDate(List<LocalDate> date,
									  Pageable page,
									  String startDate,
									  String endDate,
									  String flag){
		return rateItemsOverPeriod(itemRepository.findByDateIn(date,
															   page,
															   LocalDate.parse(startDate),
															   LocalDate.parse(endDate),
															   getFlag(flag)),
								   startDate,
								   endDate,
								   flag);
	}
	public List<RatedItem> findByBrand(List<String> brand,
									   Pageable page,
									   String startDate,
									   String endDate,
									   String flag){
		return rateItemsOverPeriod(itemRepository.findByBrandIn(brand,
																page,
																LocalDate.parse(startDate),
																LocalDate.parse(endDate),
																getFlag(flag)),
								   startDate,
								   endDate,
								   flag);
	}
	public List<RatedItem> findByDept(List<String> dept,
									  Pageable page,
									  String startDate,
									  String endDate,
									  String flag){
		return rateItemsOverPeriod(itemRepository.findByDeptIn(dept,
															   page,
															   LocalDate.parse(startDate),
															   LocalDate.parse(endDate),
															   getFlag(flag)),
								   startDate,
								   endDate,
								   flag);
	}
	public List<RatedItem> findByItemClass(List<String> itemClass,
										   Pageable page,
										   String startDate,
										   String endDate,
										   String flag){
		return rateItemsOverPeriod(itemRepository.findByItemClassIn(itemClass,
																	page,
																	LocalDate.parse(startDate),
																	LocalDate.parse(endDate),
																	getFlag(flag)),
								   startDate,
								   endDate,
								   flag);
	}
	public List<RatedItem> findByOriginalPrice(List<String> originalPrice,
											   Pageable page,
											   String startDate,
											   String endDate,
											   String flag){
		ArrayList<Double> doubleValues = new ArrayList<Double>();
		convertToDoubles(doubleValues, originalPrice);
		return rateItemsOverPeriod(itemRepository.findByOriginalPriceIn(doubleValues,
																		page,
																		LocalDate.parse(startDate),
																		LocalDate.parse(endDate),
																		getFlag(flag)),
								   startDate,
								   endDate,
								   flag);
	}
	public List<RatedItem> findbySalePrice(List<String> salePrice,
										   Pageable page,
										   String startDate,
										   String endDate,
										   String flag){
		ArrayList<Double> doubleValues = new ArrayList<Double>();
		convertToDoubles(doubleValues, salePrice);
		return rateItemsOverPeriod(itemRepository.findBySalePriceIn(doubleValues,
																	page,
																	LocalDate.parse(startDate),
																	LocalDate.parse(endDate),
																	getFlag(flag)),
								   startDate,
								   endDate,
								   flag);
	}
	public List<RatedItem> findByActiveFlag(List<String> activeFlag,
											Pageable page,
											String startDate,
											String endDate,
											String flag){
		return rateItemsOverPeriod(itemRepository.findByActiveflagIn(activeFlag,
																	 page,
																	 LocalDate.parse(startDate),
																	 LocalDate.parse(endDate),
																	 getFlag(flag)),
								   startDate,
								   endDate,
								   flag);
	}
	public List<RatedItem> findByImageFile(List<String> imageFile,
										   Pageable page,
										   String startDate,
										   String endDate,
										   String flag){
		return rateItemsOverPeriod(itemRepository.findByImageFileIn(imageFile,
																	page,
																	LocalDate.parse(startDate),
																	LocalDate.parse(endDate),
																	getFlag(flag)),
								   startDate,
								   endDate,
								   flag);
	}
	public List<RatedItem> findByVariants(List<String> variants,
										  Pageable page,
										  String startDate,
										  String endDate,
										  String flag){
		return rateItemsOverPeriod(itemRepository.findByVariantsIn(variants,
													     		   page,
													     		   LocalDate.parse(startDate),
													     		   LocalDate.parse(endDate),
													     		   getFlag(flag)),
								   startDate,
								   endDate,
								   flag);
	}
	public Item findByUniqueId(long uniqueId) {
		return itemRepository.findByUniqueId(uniqueId);
	}
	public List<RatedItem> getTableItem(String sku, String startDate, String endDate, Pageable page){
		return rateItemsOverPeriod(itemRepository.findItemBySkuDate(sku,
								   LocalDate.parse(startDate),
								   LocalDate.parse(endDate),
						           page),
								   startDate,
								   endDate,
								   "B");
	}
	public List<RatedItem> getTableItemVariants(String id, String sku, String startDate, String endDate){
		
		return rateItemsOverPeriod(itemRepository.findItemByIdSkuDate(id, sku, LocalDate.parse(startDate), LocalDate.parse(endDate)), startDate, startDate,"B");
	}
	private List<RatedItem> rateItemsOverPeriod(List<Item> items,
											   String startDate,
											   String endDate,
											   String flag){
		ArrayList<RatedItem> ratedItems = new ArrayList<RatedItem>();
		HashMap<String, Integer> indexOf = new HashMap<String, Integer>();
		//map skus
		for(int i = 0; i < items.size(); i++) {
			indexOf.put(items.get(i).getSku(), i);
			ratedItems.add(new RatedItem(items.get(i)));
		}
		ArrayList<String> skus = new ArrayList<String>(indexOf.keySet());
		List<Item> itemRatingData = itemRepository.getRatingDataOverPeriod(skus,
																		   LocalDate.parse(startDate),
																		   LocalDate.parse(endDate),
																		   getFlag("B"));
		for(Item i: itemRatingData) {
			if(i.getActiveFlag().equals("Y")) ratedItems.get(indexOf.get(i.getSku())).incrementRate();
		}
		return ratedItems;
	}
	public List<String> getFlag(String flag){
		List<String> flags = new ArrayList<String>();
		if(flag.equals("Y")||flag.equals("N")) {
			flags.add(flag);
		}
		else {
			flags.add("Y");
			flags.add("N");
		}
		System.out.println(flags);
		return flags;
	}
}
