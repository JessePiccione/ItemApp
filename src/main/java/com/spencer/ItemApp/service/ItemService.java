package com.spencer.ItemApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spencer.ItemApp.models.Item;
import com.spencer.ItemApp.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;
	
	//regular crud operations 
	public long count(String type, List<String> values) {
		long count = 0;
		switch(type){
			case "id":
				count=itemRepository.countId(values);
				break;
			case "sku":
				count=itemRepository.countSku(values);
				break;
			case "date":
				count=itemRepository.countDate(values);
				break;
			case "brand":
				count=itemRepository.countBrand(values);
				break;
			case "dept":
				count=itemRepository.countDept(values);
				break;
			case "itemClass":
				count=itemRepository.countClass(values);
				break;
			case "originalPrice":
				count=itemRepository.countOriginalPrice(values);
				break;
			case "salePrice":
				count=itemRepository.countSalePrice(values);
				break;
			case "activeFlag":
				count=itemRepository.countActiveFlag(values);
				break;
			case "imageFile":
				count=itemRepository.countImageFile(values);
				break;
			case "variants":
				count=itemRepository.countVariants(values);
				break;
		}
		return count;
	}
	public long countAllItems() {
		return itemRepository.count();
	}
	public List<Item> findAll(Pageable page){
		return itemRepository.findAll(page);
	}
	public void delete(Item i) {
		itemRepository.delete(i);
	}
	public List<Item> findAllById(List<String> id, Pageable page) {
		return this.itemRepository.findByIdIn(id, page);
	}
	public List<Item> findAllBySku(List<String> sku, Pageable page){
		return this.itemRepository.findBySkuIn(sku, page);
	}
	public Item save(Item i) {
		return itemRepository.save(i);
	}
	public Iterable saveAll(List<Item> list) {
		return itemRepository.saveAll(list);
	}
	public List<Item> findByDate(List<String> date, Pageable page){
		return itemRepository.findByDateIn(date, page);
	}
	public List<Item> findByBrand(List<String> brand, Pageable page){
		return itemRepository.findByBrandIn(brand, page);
	}
	public List<Item> findByDept(List<String> dept, Pageable page){
		return itemRepository.findByDeptIn(dept, page);
	}
	public List<Item> findByItemClass(List<String> itemClass, Pageable page){
		return itemRepository.findByItemClassIn(itemClass, page);
	}
	public List<Item> findByOriginalPrice(List<String> originalPrice, Pageable page){
		return itemRepository.findByOriginalPriceIn(originalPrice, page);
	}
	public List<Item> findbySalePrice(List<String> salePrice, Pageable page){
		return itemRepository.findBySalePriceIn(salePrice, page);
	}
	public List<Item> findByActiveFlag(List<String> activeFlag, Pageable page){
		return itemRepository.findByActiveFlagIn(activeFlag, page);
	}
	public List<Item> findByImageFile(List<String> imageFile, Pageable page){
		return itemRepository.findByImageFileIn(imageFile, page);
	}
	public List<Item> findByVariants(List<String> variants, Pageable page){
		return itemRepository.findByVariantsIn(variants, page);
	}
	public Item findByUniqueId(long uniqueId) {
		return itemRepository.findByUniqueId(uniqueId);
	}
}
