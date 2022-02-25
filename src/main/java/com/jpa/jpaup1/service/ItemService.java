package com.jpa.jpaup1.service;

import com.jpa.jpaup1.domain.Item;
import com.jpa.jpaup1.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional //메소드에 있는 트랜잭션이 우선순위를 가진다
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();

    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }


}
