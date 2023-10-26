package com.spencer.ItemApp.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long uniqueId;
	@Column(length=50)
	private String id;
	@Column(length=50)
	private String sku;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	@Column(length = 50)
	private String brand;
	@Column(length = 50)
	private String dept;
	@Column(length = 50)
	private String itemClass;
	private double originalPrice;
	private double salePrice;
	@Column(length = 50)
	private String activeFlag;
	@Column(length = 255)
	private String imageFile;
	@Column(length = 2550)
	private String variants;
	private int rating;
	protected Item() {}
	public Item(String id, String sku, String date, String brand, String dept, String itemClass, double originalPrice, double salePrice, String activeFlag, String imageFile, String variants, int rating) {
		this.id=id;
		this.sku=sku;
		this.setDate(date);
		this.setBrand(brand);
		this.setDept(dept);
		this.setItemClass(itemClass);
		this.setOriginalPrice(originalPrice);
		this.setSalePrice(salePrice);
		this.setActiveFlag(activeFlag);
		this.setImageFile(imageFile);
		this.setVariants(variants);
		this.setRating(rating);
	}
	public void updateNewValues(Item i) {
		if(id.equals("")) {
			this.setId(i.getId());
		}
		if(sku.equals("")) {
			this.setSku(i.getSku());
		}
		if(date.equals(i.getDate())){
			this.setDate(i.getDate().toString());
		}
		if(brand.equals("")) {	
			this.setBrand(i.getBrand());
		}
		if(dept.equals("")) {	
			this.setDept(i.getDept());
		}
		if(itemClass.equals("")) {	
			this.setItemClass(i.getItemClass());
		}
		if(originalPrice==0.0) {	
			this.setOriginalPrice(i.getOriginalPrice());
		}
		if(salePrice==0.0) {	
			this.setSalePrice(i.getSalePrice());
		}
		if(activeFlag.equals("")) {	
			this.setActiveFlag(i.getActiveFlag());
		}
		if(imageFile.equals("")) {	
			this.setImageFile(i.getImageFile());
		}
		if(variants.equals("")) {	
			this.setVariants(i.getVariants());
		}
	}
	@Override 
	public String toString() {
		return String.format(
				"Item[id='%s', date='%s', brand='%s', dept='%s', itemClass='%s', originalPrice='%s', salePrice='%s', actveFlag='%s', imageFile='%s', variants='%s']",
				this.id, this.date.toString(), this.brand, this.dept, this.itemClass, this.originalPrice, this.salePrice, this.activeFlag, this.imageFile, this.variants
				);
	}
	public long getUniqueId() {
		return uniqueId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = LocalDate.parse(date);
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getItemClass() {
		return itemClass;
	}
	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}
	public double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public String getVariants() {
		return variants;
	}
	public void setVariants(String variants) {
		this.variants = variants;
	}
	public int getRating() {
		return this.rating;
	}
	public void setRating(int rating) {
		this.rating=rating;
	}
}
