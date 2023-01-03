package com.shoppingmall.domain.item;

import com.shoppingmall.request.item.ItemRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@Entity
public class Pants extends Item {

    private int pantsSize;

    @Builder
    public Pants(ItemRequest itemRequest, int pantsSize) {
        super(itemRequest);
        this.pantsSize = pantsSize;
    }
}
