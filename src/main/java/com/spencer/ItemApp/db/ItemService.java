package com.spencer.ItemApp.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;
	
	//regular crud operations 
	public List<Item> findAll(){
		return (List<Item>) itemRepository.findAll();
	}
	public void delete(Item i) {
		itemRepository.delete(i);
	}
	public Item findById(long id) {
		return this.itemRepository.findById(id);
	}
	public Item save(Item i) {
		return itemRepository.save(i);
	}
	public List<Item> findByDate(String date){
		return itemRepository.findByDate(date);
	}
	public List<Item> findByBrand(String brand){
		return itemRepository.findByBrand(brand);
	}
	public List<Item> findByDept(String dept){
		return itemRepository.findByDept(dept);
	}
	public List<Item> findByItemClass(String itemClass){
		return itemRepository.findByItemClass(itemClass);
	}
	public List<Item> findByOriginalPrice(double originalPrice){
		return itemRepository.findByOriginalPrice(originalPrice);
	}
	public List<Item> findbySalePrice(double salePrice){
		return itemRepository.findBySalePrice(salePrice);
	}
	public List<Item> findByActiveFlag(String activeFlag){
		return itemRepository.findByActiveFlag(activeFlag);
	}
	public List<Item> findByImageFile(String imageFile){
		return itemRepository.findByImageFile(imageFile);
	}
	public List<Item> findByVariants(String variants){
		return itemRepository.findByVariants(variants);
	}
}
