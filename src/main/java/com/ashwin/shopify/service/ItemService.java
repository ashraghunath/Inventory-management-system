package com.ashwin.shopify.service;

import com.ashwin.shopify.exception.InvalidDataException;
import com.ashwin.shopify.model.Item;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Interface representing methods of service
 * @author Ashwin Raghunath
 * @since 1.0
 */
public interface ItemService {

    List<Item> getAllItems();

    Item saveItem(Item item) throws InvalidDataException;

    Item updateItem(Item item) throws InvalidDataException;

    Item getItemById(Long id);

    void deleteItemById(Long id);

    void exportToCSV(HttpServletResponse response, List<Item> items) throws IOException;
}
