package com.ashwin.shopify.controller;

import com.ashwin.shopify.exception.InvalidDataException;
import com.ashwin.shopify.model.Item;
import com.ashwin.shopify.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class InventoryController {

    @Autowired
    private ItemServiceImpl itemService;

    @GetMapping("/items")
    public String listItems(Model model){
        List<Item> allItems = itemService.getAllItems();
        model.addAttribute("items",allItems);
        return "items";
    }

    @GetMapping("/items/add")
    public String addItem(Model model)
    {
        Item item = new Item();
        model.addAttribute("item",item);
        return "add_item";
    }

    @PostMapping("/items")
    public String addItem(@ModelAttribute("item") Item item) throws InvalidDataException {
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("/items/update/{id}")
    public String updateItemForm(@PathVariable Long id, Model model)
    {
        Item itemById = itemService.getItemById(id);
        model.addAttribute("item",itemById);
        return "update_item";
    }

    @PostMapping("/item/{id}")
    public String uodateItem(@PathVariable Long id, @ModelAttribute("item") Item item, Model model) throws InvalidDataException {
        Item existingItem = itemService.getItemById(id);
        existingItem.setId(id);
        existingItem.setName(item.getName());
        existingItem.setQuantity(item.getQuantity());

        itemService.updateItem(existingItem);

        return "redirect:/items";
    }

    @GetMapping("/items/{id}")
    public String deleteItem(@PathVariable Long id){
        itemService.deleteItemById(id);
        return "redirect:/items";
    }

    @GetMapping("/items/export")
    public String exportToCSV(HttpServletResponse response) throws IOException
    {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=items_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Item> listOfItems = itemService.getAllItems();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Item ID", "Name", "Quantity", "Stock added on"};
        String[] nameMapping = {"id", "name", "quantity", "created_At"};

        csvWriter.writeHeader(csvHeader);

        for (Item item : listOfItems) {
            csvWriter.write(item, nameMapping);
        }

        csvWriter.close();
        return "items";
    }

}
