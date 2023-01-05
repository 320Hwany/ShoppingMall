package com.shoppingmall.item.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shoppingmall.item.domain.item.Item;
import com.shoppingmall.item.dto.request.ItemSearch;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.shoppingmall.item.domain.item.QItem.item;

@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Item> getItemsByLatest(ItemSearch itemSearch) {
        return jpaQueryFactory.selectFrom(item)
                .limit(itemSearch.getLimit())
                .offset(itemSearch.getOffset())
                .orderBy(item.id.desc())
                .fetch();
    }

    @Override
    public List<Item> getItemsByLowPrice(ItemSearch itemSearch) {
        return jpaQueryFactory.selectFrom(item)
                .limit(itemSearch.getLimit())
                .offset(itemSearch.getOffset())
                .orderBy(item.itemPrice.asc())
                .fetch();
    }

    @Override
    public List<Item> getItemsByHighPrice(ItemSearch itemSearch) {
        return jpaQueryFactory.selectFrom(item)
                .limit(itemSearch.getLimit())
                .offset(itemSearch.getOffset())
                .orderBy(item.itemPrice.desc())
                .fetch();
    }
}
