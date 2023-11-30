package com.spencer.ItemApp.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Data
public class RatedItem extends Item {
    private int rating;
    protected RatedItem() {
        super();
    }
    public RatedItem(Item i) {
        super(i.getId(), i.getSku(), i.getDate().toString(), i.getBrand(), i.getDept(), i.getItemClass(), i.getOriginalPrice(), i.getSalePrice(), i.getActiveFlag(), i.getImageFile(), i.getVariants());
        this.setRating(0);
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public void incrementRate() {
        this.rating++;
    }
}
