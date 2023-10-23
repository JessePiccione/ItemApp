package com.spencer.ItemApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spencer.ItemApp.models.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{
	Item findByUniqueId(long UniqueId);
	List<Item> findByIdIn(@Param("id") List<String> id,Pageable page);
	List<Item> findBySkuIn(@Param("id") List<String> sku, Pageable page);
	List<Item> findAll(Pageable page);
	List<Item> findByDateIn(@Param("id") List<String> date, Pageable page);
	List<Item> findByBrandIn(@Param("id") List<String> brand, Pageable page);
	List<Item> findByDeptIn(@Param("id") List<String> dept, Pageable page);
	List<Item> findByItemClassIn(@Param("id") List<String> itemClass, Pageable page);
	List<Item> findByOriginalPriceIn(@Param("id") List<String> originalPrice, Pageable page);
	List<Item> findBySalePriceIn(@Param("id") List<String> salePrice, Pageable page);
	List<Item> findByActiveFlagIn(@Param("id") List<String> activeFlag, Pageable page);
	List<Item> findByImageFileIn(@Param("id") List<String> ImageFile, Pageable page);
	List<Item> findByVariantsIn(@Param("id") List<String> variants, Pageable page);
}