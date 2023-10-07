package com.spencer.ItemApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spencer.ItemApp.models.Item;
import com.spencer.ItemApp.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;
	
	//regular crud operations 
	public List<Item> findAll(Sort sort){
		return itemRepository.findAll(sort);
	}
	public void delete(Item i) {
		itemRepository.delete(i);
	}
	public List<Item> findAllById(long id, Sort sort) {
		return this.itemRepository.findAllById(id, sort);
	}
	public List<Item> findAllBySku(long sku, Sort sort){
		return this.itemRepository.findAllBySku(sku, sort);
	}
	public Item save(Item i) {
		return itemRepository.save(i);
	}
	public List<Item> findByDate(String date, Sort sort){
		return itemRepository.findByDate(date, sort);
	}
	public List<Item> findByBrand(String brand, Sort sort){
		return itemRepository.findByBrand(brand, sort);
	}
	public List<Item> findByDept(String dept, Sort sort){
		return itemRepository.findByDept(dept, sort);
	}
	public List<Item> findByItemClass(String itemClass, Sort sort){
		return itemRepository.findByItemClass(itemClass, sort);
	}
	public List<Item> findByOriginalPrice(double originalPrice, Sort sort){
		return itemRepository.findByOriginalPrice(originalPrice, sort);
	}
	public List<Item> findbySalePrice(double salePrice, Sort sort){
		return itemRepository.findBySalePrice(salePrice, sort);
	}
	public List<Item> findByActiveFlag(String activeFlag, Sort sort){
		return itemRepository.findByActiveFlag(activeFlag, sort);
	}
	public List<Item> findByImageFile(String imageFile, Sort sort){
		return itemRepository.findByImageFile(imageFile, sort);
	}
	public List<Item> findByVariants(String variants, Sort sort){
		return itemRepository.findByVariants(variants, sort);
	}
	public Item findByUniqueId(long uniqueId) {
		return itemRepository.findByUniqueId(uniqueId);
	}
}
