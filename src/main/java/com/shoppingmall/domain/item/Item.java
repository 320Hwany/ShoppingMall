package com.shoppingmall.domain.item;

import com.shoppingmall.request.item.ItemRequest;
import com.shoppingmall.response.ItemResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.*;

@Getter
@Inheritance(strategy = JOINED)
@DiscriminatorColumn
@NoArgsConstructor
@Entity
public abstract class Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String itemName;

    private int itemPrice;

    private String itemColor;

    public Item(ItemRequest itemRequest) {
        this.itemName = itemRequest.getItemName();
        this.itemPrice = itemRequest.getItemPrice();
        this.itemColor = itemRequest.getItemColor();
    }

    public static ItemResponse getItemResponse(Item item) {
        if (item instanceof Pants) {
            return new ItemResponse((Pants) item);
        }
        if (item instanceof Shoes) {
            return new ItemResponse((Shoes) item);
        }
        return new ItemResponse((Top) item);
    }
}
