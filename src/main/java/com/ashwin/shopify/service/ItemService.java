package com.ashwin.shopify.service;

import com.ashwin.shopify.exception.InvalidDataException;
import com.ashwin.shopify.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAllItems();

    Item saveItem(Item item) throws InvalidDataException;

    Item updateItem(Item item) throws InvalidDataException;

    Item getItemById(Long id);

    void deleteItemById(Long id);
}
