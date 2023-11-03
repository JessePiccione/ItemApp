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
	public long count(String type, List<String> values, String startDate, String endDate) {
		long count = 0;
		ArrayList<Double> doubleValues;
		ArrayList<LocalDate> dateValues; 
		switch(type){
			case "id":
				count=itemRepository.countId(values, LocalDate.parse(startDate), LocalDate.parse(endDate));
				break;
			case "sku":
				count=itemRepository.countSku(values, LocalDate.parse(startDate), LocalDate.parse(endDate));
				break;
			case "date":
				dateValues = new ArrayList<>();
				for(String s: values) {
					dateValues.add(LocalDate.parse(s));
				}
				count=itemRepository.countDate(dateValues, LocalDate.parse(startDate), LocalDate.parse(endDate));
				break;
			case "brand":
				count=itemRepository.countBrand(values, LocalDate.parse(startDate), LocalDate.parse(endDate));
				break;
			case "dept":
				count=itemRepository.countDept(values, LocalDate.parse(startDate), LocalDate.parse(endDate));
				break;
			case "itemClass":
				count=itemRepository.countClass(values, LocalDate.parse(startDate), LocalDate.parse(endDate));
				break;
			case "originalPrice":
				doubleValues = new ArrayList<>();
				convertToDoubles(doubleValues, values);
;				count=itemRepository.countOriginalPrice(doubleValues, LocalDate.parse(startDate), LocalDate.parse(endDate));
				break;
			case "salePrice":
				doubleValues = new ArrayList<>();
				convertToDoubles(doubleValues, values);
				count=itemRepository.countSalePrice(doubleValues, LocalDate.parse(startDate), LocalDate.parse(endDate));
				break;
			case "activeFlag":
				count=itemRepository.countActiveFlag(values, LocalDate.parse(startDate), LocalDate.parse(endDate));
				break;
			case "imageFile":
				count=itemRepository.countImageFile(values, LocalDate.parse(startDate), LocalDate.parse(endDate));
				break;
			case "variants":
				count=itemRepository.countVariants(values, LocalDate.parse(startDate), LocalDate.parse(endDate));
				break;
		}
		return count;
	}
	public long countAllItems(String startDate, String endDate) {
		return itemRepository.count(LocalDate.parse(startDate), LocalDate.parse(endDate));
	}
	public List<RatedItem> findAll(Pageable page, String startDate, String endDate){
		return rateItemsOverPeriod(itemRepository.findAll(page), startDate, endDate);
	}
	public void delete(Item i) {
		itemRepository.delete(i);
	}
	public List<RatedItem> findAllById(List<String> id, Pageable page, String startDate, String endDate) {
		return rateItemsOverPeriod(itemRepository.findByIdIn(id, page),startDate, endDate);
	}
	public List<RatedItem> findAllBySku(List<String> sku, Pageable page, String startDate, String endDate){
		return rateItemsOverPeriod(itemRepository.findBySkuIn(sku, page), startDate, endDate);
	}
	public Item save(Item i) {
		return itemRepository.save(i);
	}
	public Iterable saveAll(List<Item> list) {
		return itemRepository.saveAll(list);
	}
	///TODO fix implementation
	public List<Item> last20DaysWithSku(LocalDate date, String sku){
		return itemRepository.findItemFromDaysAgo(date.minusDays(20), sku);
	}
	public List<Item> last20Days(LocalDate date){
		return itemRepository.findItemFromDaysAgo(date.minusDays(20));
	}
	public List<RatedItem> findByDate(List<LocalDate> date, Pageable page, String startDate, String endDate){
		return rateItemsOverPeriod(itemRepository.findByDateIn(date, page), startDate, endDate);
	}
	public List<RatedItem> findByBrand(List<String> brand, Pageable page, String startDate, String endDate){
		return rateItemsOverPeriod(itemRepository.findByBrandIn(brand, page), startDate, endDate);
	}
	public List<RatedItem> findByDept(List<String> dept, Pageable page, String startDate, String endDate){
		return rateItemsOverPeriod(itemRepository.findByDeptIn(dept, page), startDate, endDate);
	}
	public List<RatedItem> findByItemClass(List<String> itemClass, Pageable page, String startDate, String endDate){
		return rateItemsOverPeriod(itemRepository.findByItemClassIn(itemClass, page), startDate, endDate);
	}
	public List<RatedItem> findByOriginalPrice(List<String> originalPrice, Pageable page, String startDate, String endDate){
		ArrayList<Double> doubleValues = new ArrayList<Double>();
		convertToDoubles(doubleValues, originalPrice);
		return rateItemsOverPeriod(itemRepository.findByOriginalPriceIn(doubleValues, page), startDate, endDate);
	}
	public List<RatedItem> findbySalePrice(List<String> salePrice, Pageable page, String startDate, String endDate){
		ArrayList<Double> doubleValues = new ArrayList<Double>();
		convertToDoubles(doubleValues, salePrice);
		return rateItemsOverPeriod(itemRepository.findBySalePriceIn(doubleValues, page), startDate, endDate);
	}
	public List<RatedItem> findByActiveFlag(List<String> activeFlag, Pageable page, String startDate, String endDate){
		return rateItemsOverPeriod(itemRepository.findByActiveFlagIn(activeFlag, page), startDate, endDate);
	}
	public List<RatedItem> findByImageFile(List<String> imageFile, Pageable page, String startDate, String endDate){
		return rateItemsOverPeriod(itemRepository.findByImageFileIn(imageFile, page), startDate, endDate);
	}
	public List<RatedItem> findByVariants(List<String> variants, Pageable page, String startDate, String endDate){
		return rateItemsOverPeriod(itemRepository.findByVariantsIn(variants, page), startDate, endDate);
	}
	public Item findByUniqueId(long uniqueId) {
		return itemRepository.findByUniqueId(uniqueId);
	}
	public List<RatedItem> rateItemsOverPeriod(List<Item> items, String startDate, String endDate){
		ArrayList<RatedItem> ratedItems = new ArrayList<RatedItem>();
		HashMap<String, Integer> indexOf = new HashMap<String, Integer>();
		//map skus
		for(int i = 0; i < items.size(); i++) {
			indexOf.put(items.get(i).getSku(), i);
			ratedItems.add(new RatedItem(items.get(i)));
		}
		ArrayList<String> skus = new ArrayList<String>(indexOf.keySet());
		List<Item> itemRatingData = itemRepository.getRatingDataOverPeriod(skus, LocalDate.parse(startDate), LocalDate.parse(endDate));
		for(Item i: itemRatingData) {
			if(i.getActiveFlag().equals("Y")) ratedItems.get(indexOf.get(i.getSku())).incrementRate();
		}
		return ratedItems;
	}
}
