package com.ashwin.shopify.service.impl;

import com.ashwin.shopify.exception.InvalidDataException;
import com.ashwin.shopify.model.Item;
import com.ashwin.shopify.repository.ItemRepository;
import com.ashwin.shopify.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item saveItem(Item item) throws InvalidDataException {
        if(item.getQuantity()<=0)
        {
            throw new InvalidDataException("Invalid quantity entered");
        }
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Item item) throws InvalidDataException {
        if(item.getQuantity()<=0)
        {
            throw new InvalidDataException("Invalid quantity entered");
        }
        return itemRepository.save(item);
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}
