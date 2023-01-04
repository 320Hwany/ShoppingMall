package com.shoppingmall.domain.item;

import com.shoppingmall.request.item.ItemBasicField;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@Entity
public class Shoes extends Item {

    private Integer ShoesSize;

    @Builder
    public Shoes(ItemBasicField itemBasicField, Integer shoesSize) {
        super(itemBasicField);
        ShoesSize = shoesSize;
    }
}
