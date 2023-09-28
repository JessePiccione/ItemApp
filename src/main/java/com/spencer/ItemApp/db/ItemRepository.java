package com.spencer.ItemApp.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long>{
	Item findById(long id);
	List<Item> findByDate(String date);
	List<Item> findByBrand(String brand);
	List<Item> findByDept(String dept);
	List<Item> findByItemClass(String itemClass);
	List<Item> findByOriginalPrice(double originalPrice);
	List<Item> findBySalePrice(double salePrice);
	List<Item> findByActiveFlag(String activeFlag);
	List<Item> findByImageFile(String ImageFile);
	List<Item> findByVariants(String variants);
}