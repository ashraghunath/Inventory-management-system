package com.ashwin.shopify.controller;

import com.ashwin.shopify.exception.InvalidDataException;
import com.ashwin.shopify.model.Item;
import com.ashwin.shopify.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
 * Controller class that handles various CRUD operations and export CSV endpoint
 * @author Ashwin Raghunath
 * @since 1.0
 */
@Controller
public class InventoryController {

    @Autowired
    private ItemServiceImpl itemService;

    /**
     * gets the list of all items in inventory
     * @param model Model object to set items as attribute
     * @return items html page
     */
    @GetMapping("/items")
    public String listItems(Model model){
        List<Item> allItems = itemService.getAllItems();
        model.addAttribute("items",allItems);
        return "items";
    }

    /**
     * opens up the form to add item data
     * @param model Model object to set item as attribute
     * @return add item form html page
     */
    @GetMapping("/items/add")
    public String addItem(Model model)
    {
        Item item = new Item();
        model.addAttribute("item",item);
        return "add_item";
    }

    /**
     * adds the new created item to inventory
     * @param item new Item object to be saved
     * @return items html page
     * @throws InvalidDataException when data entered is invalid
     */
    @PostMapping("/items")
    public String addItem(@ModelAttribute("item") Item item) throws InvalidDataException {
        itemService.saveItem(item);
        return "redirect:/items";
    }

    /**
     * opens up the form to update item data
     * @param id id of Item to be updated
     * @param model Model object to set item as attribute
     * @return update item form html page
     */
    @GetMapping("/items/update/{id}")
    public String updateItemForm(@PathVariable Long id, Model model)
    {
        Item itemById = itemService.getItemById(id);
        model.addAttribute("item",itemById);
        return "update_item";
    }

    /**
     *
     * @param id id of item to be updated
     * @param item Item to be updated
     * @param model Model type object to add item
     * @return redirection to items page
     * @throws InvalidDataException when data entered is invalid
     */
    @PostMapping("/item/{id}")
    public String uodateItem(@PathVariable Long id, @ModelAttribute("item") Item item, Model model) throws InvalidDataException {
        Item existingItem = itemService.getItemById(id);
        existingItem.setId(id);
        existingItem.setName(item.getName());
        existingItem.setQuantity(item.getQuantity());

        itemService.updateItem(existingItem);

        return "redirect:/items";
    }

    /**
     * deletes item from inventory
     * @param id id of item to be deleted
     * @return redirection to items page
     */
    @GetMapping("/items/{id}")
    public String deleteItem(@PathVariable Long id){
        itemService.deleteItemById(id);
        return "redirect:/items";
    }

    /**
     * exports the data available on items page to a csv file
     * @param response HttpServletResponse to send csv to.
     * @return items page
     * @throws IOException
     */
    @GetMapping("/items/export")
    public String exportToCSV(HttpServletResponse response) throws IOException
    {
        List<Item> listOfItems = itemService.getAllItems();
        itemService.exportToCSV(response,listOfItems);
        return "items";
    }

}
