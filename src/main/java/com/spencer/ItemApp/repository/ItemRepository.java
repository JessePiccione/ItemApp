package com.spencer.ItemApp.repository;

import java.util.List;
import java.time.LocalDate;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spencer.ItemApp.models.Item;
public interface ItemRepository extends CrudRepository<Item, Long>{
	Item findByUniqueId(long UniqueId);
	@Query(value= "SELECT * FROM item WHERE item.id IN :id AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	List<Item> findByIdIn(List<String> id,Pageable page, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT * FROM item WHERE item.sku IN :sku AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	List<Item> findBySkuIn(List<String> sku, Pageable page, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT * FROM item WHERE item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	List<Item> findAll(Pageable page, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT * FROM item WHERE item.date IN :date AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	List<Item> findByDateIn(List<LocalDate> date, Pageable page, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT * FROM item WHERE item.brand IN :brand AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	List<Item> findByBrandIn(List<String> brand, Pageable page, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT * FROM item WHERE item.dept IN :dept AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	List<Item> findByDeptIn(List<String> dept, Pageable page, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT * FROM item WHERE item.itemkClass IN :itemClass AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	List<Item> findByItemClassIn(List<String> itemClass, Pageable page, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT * FROM item WHERE item.originalPrice IN :originalPrice AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	List<Item> findByOriginalPriceIn(List<Double> originalPrice, Pageable page, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT * FROM item WHERE item.salePrice IN :salePrice AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	List<Item> findBySalePriceIn(List<Double> salePrice, Pageable page, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT * FROM item WHERE item.active_flag IN :activeflag AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	List<Item> findByActiveflagIn(List<String> activeflag, Pageable page, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT * FROM item WHERE item.imageFile IN :imageFile AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	List<Item> findByImageFileIn(List<String> imageFile, Pageable page, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT * FROM item WHERE item.variants IN :variants AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	List<Item> findByVariantsIn(List<String> variants, Pageable page, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT * FROM item WHERE item.sku = :sku AND item.date >= :startDate AND item.date <= :endDate", nativeQuery=true)
	List<Item> findItemBySkuDate(String sku, LocalDate startDate, LocalDate endDate, Pageable page);
	@Query(value="SELECT * FROM item WHERE item.id = :id AND item.sku!=:sku AND item.date >= :startDate AND item.date <= :endDate", nativeQuery=true)
	List<Item> findItemByIdSkuDate(String id, String sku, LocalDate startDate, LocalDate endDate);
	@Query(value="SELECT COUNT(*) FROM item WHERE item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	long count(LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.id IN :values AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	long countId(List<String> values, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.sku IN :values AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	long countSku(List<String> values, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.date IN :values AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	long countDate(List<LocalDate> values, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.brand IN :values AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	long countBrand(List<String> values, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.dept IN :values AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	long countDept(List<String> values, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.itemClass IN :values AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	long countClass(List<String> values, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.originalPrice IN :values AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	long countOriginalPrice(List<Double> values, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.salePrice IN :values AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	long countSalePrice(List<Double> values, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.active_flag IN :values AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	long countActiveflag(List<String> values, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.imageFile IN :values AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	long countImageFile(List<String> values, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value= "SELECT COUNT(*) FROM item WHERE item.variants IN :values AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery = true)
	long countVariants(List<String> values, LocalDate startDate, LocalDate endDate, List<String> flags);
	@Query(value="SELECT * FROM item WHERE item.sku IN :skus AND item.date >= :startDate AND item.date <= :endDate AND item.active_flag IN :flags", nativeQuery=true)
	List<Item> getRatingDataOverPeriod(List<String> skus, LocalDate startDate, LocalDate endDate, List<String> flags);
}