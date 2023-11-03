package com.spencer.ItemApp.repository;

import java.util.List;
import java.time.LocalDate;

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
	List<Item> findByIdIn(List<String> id,Pageable page);
	List<Item> findBySkuIn(List<String> sku, Pageable page);
	List<Item> findAll(Pageable page);
	List<Item> findByDateIn(List<LocalDate> date, Pageable page);
	@Query("SELECT i FROM Item i WHERE i.date > :date")
	List<Item> findItemFromDaysAgo(LocalDate date);
	@Query("SELECT i FROM Item i WHERE i.date > :date AND i.sku=:sku")
	List<Item> findItemFromDaysAgo(LocalDate date, String sku);
	List<Item> findByBrandIn(List<String> brand, Pageable page);
	List<Item> findByDeptIn(List<String> dept, Pageable page);
	List<Item> findByItemClassIn(List<String> itemClass, Pageable page);
	List<Item> findByOriginalPriceIn(List<Double> originalPrice, Pageable page);
	List<Item> findBySalePriceIn(List<Double> salePrice, Pageable page);
	List<Item> findByActiveFlagIn(List<String> activeFlag, Pageable page);
	List<Item> findByImageFileIn(List<String> ImageFile, Pageable page);
	List<Item> findByVariantsIn(List<String> variants, Pageable page);
	@Query(value="SELECT COUNT(*) FROM item WHERE item.date >= :startDate AND item.date <= :endDate", nativeQuery = true)
	long count(LocalDate startDate, LocalDate endDate);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.id IN :values AND item.date >= :startDate AND item.date <= :endDate", nativeQuery = true)
	long countId(List<String> values, LocalDate startDate, LocalDate endDate);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.sku IN :values AND item.date >= :startDate AND item.date <= :endDate", nativeQuery = true)
	long countSku(List<String> values, LocalDate startDate, LocalDate endDate);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.date IN :values AND item.date >= :startDate AND item.date <= :endDate", nativeQuery = true)
	long countDate(List<LocalDate> values, LocalDate startDate, LocalDate endDate);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.brand IN :values AND item.date >= :startDate AND item.date <= :endDate", nativeQuery = true)
	long countBrand(List<String> values, LocalDate startDate, LocalDate endDate);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.dept IN :values AND item.date >= :startDate AND item.date <= :endDate", nativeQuery = true)
	long countDept(List<String> values, LocalDate startDate, LocalDate endDate);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.itemClass IN :values AND item.date >= :startDate AND item.date <= :endDate", nativeQuery = true)
	long countClass(List<String> values, LocalDate startDate, LocalDate endDate);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.originalPrice IN :values AND item.date >= :startDate AND item.date <= :endDate", nativeQuery = true)
	long countOriginalPrice(List<Double> values, LocalDate startDate, LocalDate endDate);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.salePrice IN :values AND item.date >= :startDate AND item.date <= :endDate", nativeQuery = true)
	long countSalePrice(List<Double> values, LocalDate startDate, LocalDate endDate);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.activeFlag IN :values AND item.date >= :startDate AND item.date <= :endDate", nativeQuery = true)
	long countActiveFlag(List<String> values, LocalDate startDate, LocalDate endDate);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.imageFile IN :values AND item.date >= :startDate AND item.date <= :endDate", nativeQuery = true)
	long countImageFile(List<String> values, LocalDate startDate, LocalDate endDate);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.variants IN :values AND item.date >= :startDate AND item.date <= :endDate", nativeQuery = true)
	long countVariants(List<String> values, LocalDate startDate, LocalDate endDate);
	@Query(value="SELECT * FROM item WHERE item.sku IN :skus AND item.date >= :startDate AND item.date <= :endDate", nativeQuery=true)
	List<Item> getRatingDataOverPeriod(List<String> skus, LocalDate startDate, LocalDate endDate);
}