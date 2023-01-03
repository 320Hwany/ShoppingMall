package com.shoppingmall.domain.item;

import com.shoppingmall.request.item.ItemRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@Entity
public class Shoes extends Item {

    private int ShoesSize;

    @Builder
    public Shoes(ItemRequest itemRequest, int shoesSize) {
        super(itemRequest);
        ShoesSize = shoesSize;
    }
}
