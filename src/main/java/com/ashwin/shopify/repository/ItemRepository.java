package com.ashwin.shopify.repository;

import com.ashwin.shopify.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to store items in an inventory
 * @author Ashwin Raghunath
 * @since 1.0
 */
@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    Item findByName(String name);
}