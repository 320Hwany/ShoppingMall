package com.shoppingmall.item.domain.item;

import com.shoppingmall.item.dto.request.ItemBasicField;
import com.shoppingmall.item.dto.request.ShoesUpdate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@Entity
public class Shoes extends Item {

    private Integer shoesSize;

    @Builder
    public Shoes(ItemBasicField itemBasicField, Integer shoesSize) {
        super(itemBasicField);
        this.shoesSize = shoesSize;
    }

    public void updateShoesEachField(ShoesUpdate shoesUpdate) {
        this.shoesSize = shoesUpdate.getShoesSize();
    }
}
