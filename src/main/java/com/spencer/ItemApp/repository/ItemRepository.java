package com.spencer.ItemApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.spencer.ItemApp.models.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{
	Item findByUniqueId(long UniqueId);
	List<Item> findAllById(long id,Sort sort);
	List<Item> findAllBySku(long sku, Sort sort);
	List<Item> findAll(Sort sort);
	List<Item> findByDate(String date, Sort sort);
	List<Item> findByBrand(String brand, Sort sort);
	List<Item> findByDept(String dept, Sort sort);
	List<Item> findByItemClass(String itemClass, Sort sort);
	List<Item> findByOriginalPrice(double originalPrice, Sort sort);
	List<Item> findBySalePrice(double salePrice, Sort sort);
	List<Item> findByActiveFlag(String activeFlag, Sort sort);
	List<Item> findByImageFile(String ImageFile, Sort sort);
	List<Item> findByVariants(String variants, Sort sort);
}