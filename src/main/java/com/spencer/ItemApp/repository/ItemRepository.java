package com.spencer.ItemApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.spencer.ItemApp.models.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{
	Item findByUniqueId(long UniqueId);
	List<Item> findById(long id,Pageable page);
	List<Item> findBySku(long sku, Pageable page);
	List<Item> findAll(Pageable page);
	List<Item> findByDate(String date, Pageable page);
	List<Item> findByBrand(String brand, Pageable page);
	List<Item> findByDept(String dept, Pageable page);
	List<Item> findByItemClass(String itemClass, Pageable page);
	List<Item> findByOriginalPrice(double originalPrice, Pageable page);
	List<Item> findBySalePrice(double salePrice, Pageable page);
	List<Item> findByActiveFlag(String activeFlag, Pageable page);
	List<Item> findByImageFile(String ImageFile, Pageable page);
	List<Item> findByVariants(String variants, Pageable page);
}