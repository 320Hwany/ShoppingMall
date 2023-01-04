package com.shoppingmall.domain.item;

import com.shoppingmall.domain.item.size.TopSize;
import com.shoppingmall.request.item.ItemBasicField;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import static javax.persistence.EnumType.STRING;

@Getter
@NoArgsConstructor
@Entity
public class Top extends Item {

    @Enumerated(STRING)
    private TopSize topSize;

    @Builder
    public Top(ItemBasicField itemBasicField, TopSize topSize) {
        super(itemBasicField);
        this.topSize = topSize;
    }
}
