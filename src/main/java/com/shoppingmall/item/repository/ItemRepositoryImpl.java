package com.shoppingmall.item.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shoppingmall.item.domain.item.Item;
import com.shoppingmall.item.domain.item.Shoes;
import com.shoppingmall.item.dto.request.ItemSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.shoppingmall.item.domain.item.QItem.item;

@RequiredArgsConstructor
@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final ItemJpaRepository itemJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(Item item) {
        itemJpaRepository.save(item);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemJpaRepository.findById(id);
    }

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

    @Override
    public void delete(Item item) {
        itemJpaRepository.delete(item);
    }

    @Override
    public void saveAll(List<Shoes> shoesList) {
        itemJpaRepository.saveAll(shoesList);
    }

    @Override
    public void deleteAll() {
        itemJpaRepository.deleteAll();
    }
}
