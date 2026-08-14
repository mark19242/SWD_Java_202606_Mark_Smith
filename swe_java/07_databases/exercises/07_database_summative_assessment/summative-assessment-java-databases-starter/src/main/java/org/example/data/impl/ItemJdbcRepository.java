package org.example.data.impl;

import org.example.data.ItemRepo;
import org.example.model.Item;
import org.example.model.ItemCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class ItemJdbcRepository implements ItemRepo {

    private final JdbcTemplate jdbcTemplate;

    public ItemJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ItemCategory> itemCategoryMapper = (rs, rowNum) -> {
        ItemCategory category = new ItemCategory();

        category.setItemCategoryID(
                rs.getInt("item_category_id")
        );
        category.setItemCategoryName(
                rs.getString("item_category_name")
        );

        return category;
    };

    private final RowMapper<Item> itemMapper = (rs, rowNum) -> {
        Item item = new Item();

        item.setItemID(rs.getInt("item_id"));
        item.setItemCategoryID(rs.getInt("item_category_id"));
        item.setItemName(rs.getString("item_name"));
        item.setItemDescription(rs.getString("item_description"));
        item.setStartDate(
                rs.getObject("start_date", LocalDate.class)
        );
        item.setEndDate(
                rs.getObject("end_date", LocalDate.class)
        );
        item.setUnitPrice(rs.getBigDecimal("unit_price"));

        ItemCategory category = new ItemCategory();
        category.setItemCategoryID(
                rs.getInt("item_category_id")
        );
        category.setItemCategoryName(
                rs.getString("item_category_name")
        );

        item.setItemCategory(category);

        return item;
    };
}