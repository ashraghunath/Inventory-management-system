package com.ashwin.shopify.service.impl;

import com.ashwin.shopify.exception.InvalidDataException;
import com.ashwin.shopify.model.Item;
import com.ashwin.shopify.repository.ItemRepository;
import com.ashwin.shopify.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Service class containing business logic of all operations
 * @author Ashwin Raghunath
 * @since 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /**
     * saved item to inventory
     * @param item item to be saved
     * @return item that is saved
     * @throws InvalidDataException when item with invalid data is passed.
     */
    @Override
    public Item saveItem(Item item) throws InvalidDataException {
        if(item.getQuantity()<=0)
        {
            throw new InvalidDataException("Invalid quantity entered");
        }
        return itemRepository.save(item);
    }

    /**
     * updated item with given id
     * @param item item to be updated
     * @return item that is updated
     * @throws InvalidDataException when item with invalid data is passed.
     */
    @Override
    public Item updateItem(Item item) throws InvalidDataException {
        if(item.getQuantity()<=0)
        {
            throw new InvalidDataException("Invalid quantity entered");
        }
        return itemRepository.save(item);
    }

    /**
     * gets item with id from inventory
     * @param id id of item to be fetched
     * @return item that is found
     */
    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).get();
    }

    /**
     * deletes item with given id
     * @param id id of item to be deleted
     */
    @Override
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }

    /**
     * exports items data to csv file
     * @param response response to add csv to
     * @param listOfItems items to be added to csv
     * @throws IOException
     */
    @Override
    public void exportToCSV(HttpServletResponse response, List<Item> listOfItems) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=items_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Item ID", "Name", "Quantity", "Stock added on"};
        String[] nameMapping = {"id", "name", "quantity", "created_At"};

        csvWriter.writeHeader(csvHeader);

        for (Item item : listOfItems) {
            csvWriter.write(item, nameMapping);
        }

        csvWriter.close();
    }
}
