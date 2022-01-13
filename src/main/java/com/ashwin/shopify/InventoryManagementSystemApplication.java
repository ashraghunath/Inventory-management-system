package com.ashwin.shopify;

import com.ashwin.shopify.model.Item;
import com.ashwin.shopify.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagementSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementSystemApplication.class, args);
    }

    @Autowired
    private ItemRepository itemRepository;

    /**
     * Bean to pre populate some data
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        Item book = new Item("Book",3L);
        Item pen = new Item("pen",10L);
        itemRepository.save(book);
        itemRepository.save(pen);
    }
}
