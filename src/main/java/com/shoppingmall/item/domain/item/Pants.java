package com.shoppingmall.item.domain.item;

import com.shoppingmall.item.dto.request.ItemBasicField;
import com.shoppingmall.item.dto.request.PantsSave;
import com.shoppingmall.item.dto.request.PantsUpdate;
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

    public void updatePantsEachField(PantsUpdate pantsUpdate) {
        this.pantsSize = pantsUpdate.getPantsSize();
    }
}
