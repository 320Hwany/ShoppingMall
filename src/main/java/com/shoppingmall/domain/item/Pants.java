package com.shoppingmall.domain.item;

import com.shoppingmall.request.item.ItemBasicField;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@Entity
public class Pants extends Item {

    private Integer pantsSize;

    @Builder
    public Pants(ItemBasicField itemBasicField, Integer pantsSize) {
        super(itemBasicField);
        this.pantsSize = pantsSize;
    }
}
