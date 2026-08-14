package org.example.data.impl;

import java.util.List;

import org.example.data.ItemRepo;
import org.example.model.Item;
import org.example.model.ItemCategory;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Override
    public Item getItemById(int id)
            throws RecordNotFoundException, InternalErrorException {

        String sql = """
            SELECT
                i.item_id,
                i.item_category_id,
                ic.item_category_name,
                i.item_name,
                i.item_description,
                i.start_date,
                i.end_date,
                i.unit_price
            FROM item i
            INNER JOIN item_category ic
                ON i.item_category_id = ic.item_category_id
            WHERE i.item_id = ?
            """;

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    itemMapper,
                    id
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException(
                    "No item found with ID: " + id
            );
        } catch (DataAccessException ex) {
            throw new InternalErrorException(
                    "Unable to retrieve item information.",
                    ex
            );
        }
    }

    @Override
    public List<Item> getAllAvailableItems(LocalDate today)
            throws InternalErrorException {

        String sql = """
            SELECT
                i.item_id,
                i.item_category_id,
                ic.item_category_name,
                i.item_name,
                i.item_description,
                i.start_date,
                i.end_date,
                i.unit_price
            FROM item i
            INNER JOIN item_category ic
                ON i.item_category_id = ic.item_category_id
            WHERE i.start_date <= ?
              AND (
                    i.end_date IS NULL
                    OR i.end_date >= ?
                  )
            ORDER BY i.item_id
            """;

        try {
            return jdbcTemplate.query(
                    sql,
                    itemMapper,
                    today,
                    today
            );
        } catch (DataAccessException ex) {
            throw new InternalErrorException(
                    "Unable to retrieve available items.",
                    ex
            );
        }
    }

    @Override
    public List<Item> getItemsByCategory(
            LocalDate today,
            int itemCategoryID)
            throws InternalErrorException {

        String sql = """
            SELECT
                i.item_id,
                i.item_category_id,
                ic.item_category_name,
                i.item_name,
                i.item_description,
                i.start_date,
                i.end_date,
                i.unit_price
            FROM item i
            INNER JOIN item_category ic
                ON i.item_category_id = ic.item_category_id
            WHERE i.start_date <= ?
              AND (
                    i.end_date IS NULL
                    OR i.end_date >= ?
                  )
              AND i.item_category_id = ?
            ORDER BY i.item_id
            """;

        try {
            return jdbcTemplate.query(
                    sql,
                    itemMapper,
                    today,
                    today,
                    itemCategoryID
            );
        } catch (DataAccessException ex) {
            throw new InternalErrorException(
                    "Unable to retrieve items by category.",
                    ex
            );
        }
    }

    @Override
    public List<ItemCategory> getAllItemCategories()
            throws InternalErrorException {

        String sql = """
            SELECT
                item_category_id,
                item_category_name
            FROM item_category
            ORDER BY item_category_id
            """;

        try {
            return jdbcTemplate.query(
                    sql,
                    itemCategoryMapper
            );
        } catch (DataAccessException ex) {
            throw new InternalErrorException(
                    "Unable to retrieve item categories.",
                    ex
            );
        }
    }

}

