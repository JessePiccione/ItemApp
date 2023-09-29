package com.spencer.ItemApp.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String date;
	private String brand;
	private String dept;
	private String itemClass;
	private double originalPrice;
	private double salePrice;
	private String activeFlag;
	private String imageFile;
	private String variants;
	
	protected Item() {}
	
	public Item(String date, String brand, String dept, String itemClass, double originalPrice, double salePrice, String activeFlag, String imageFile, String variants) {
		this.setDate(date);
		this.setBrand(brand);
		this.setDept(dept);
		this.setItemClass(itemClass);
		this.setOriginalPrice(originalPrice);
		this.setSalePrice(salePrice);
		this.setActiveFlag(activeFlag);
		this.setImageFile(imageFile);
		this.setVariants(variants);
	}
	public void updateNewValues(Item i) {
		if(date.equals("")) {	
			this.setDate(i.getDate());
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
		if(originalPrice <= 0.0001) {	
			this.setOriginalPrice(i.getOriginalPrice());
		}
		if(salePrice <= 0.0001) {	
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
				"Item[id='%d', date='%s', brand='%s', dept='%s', itemClass='%s', originalPrice='%f.2', salePrice='%f.2', actveFlag='%s', imageFile='%s', variants='%s']",
				this.id, this.date, this.brand, this.dept, this.itemClass, this.originalPrice, this.salePrice, this.activeFlag, this.imageFile, this.variants
				);
	}
	public long getId() {
		return id;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
	

}
