package com.shoppingmall.item.domain.item;

import com.shoppingmall.item.domain.item.size.TopSize;
import com.shoppingmall.item.dto.request.ItemBasicField;
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
