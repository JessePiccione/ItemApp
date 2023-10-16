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
	public long countAllItems() {
		return itemRepository.count();
	}
	public List<Item> findAll(Pageable page){
		return itemRepository.findAll(page);
	}
	public void delete(Item i) {
		itemRepository.delete(i);
	}
	public List<Item> findAllById(long id, Pageable page) {
		return this.itemRepository.findById(id, page);
	}
	public List<Item> findAllBySku(long sku, Pageable page){
		return this.itemRepository.findBySku(sku, page);
	}
	public Item save(Item i) {
		return itemRepository.save(i);
	}
	public Iterable saveAll(List<Item> list) {
		return itemRepository.saveAll(list);
	}
	public List<Item> findByDate(String date, Pageable page){
		return itemRepository.findByDate(date, page);
	}
	public List<Item> findByBrand(String brand, Pageable page){
		return itemRepository.findByBrand(brand, page);
	}
	public List<Item> findByDept(String dept, Pageable page){
		return itemRepository.findByDept(dept, page);
	}
	public List<Item> findByItemClass(String itemClass, Pageable page){
		return itemRepository.findByItemClass(itemClass, page);
	}
	public List<Item> findByOriginalPrice(double originalPrice, Pageable page){
		return itemRepository.findByOriginalPrice(originalPrice, page);
	}
	public List<Item> findbySalePrice(double salePrice, Pageable page){
		return itemRepository.findBySalePrice(salePrice, page);
	}
	public List<Item> findByActiveFlag(String activeFlag, Pageable page){
		return itemRepository.findByActiveFlag(activeFlag, page);
	}
	public List<Item> findByImageFile(String imageFile, Pageable page){
		return itemRepository.findByImageFile(imageFile, page);
	}
	public List<Item> findByVariants(String variants, Pageable page){
		return itemRepository.findByVariants(variants, page);
	}
	public Item findByUniqueId(long uniqueId) {
		return itemRepository.findByUniqueId(uniqueId);
	}
}
