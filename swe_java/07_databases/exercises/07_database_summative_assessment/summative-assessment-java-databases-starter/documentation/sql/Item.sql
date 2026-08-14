-- ==========================================
-- ItemRepo
-- ==========================================

-- getItemById(int id)
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
WHERE i.item_id = ?;


-- getAllAvailableItems(LocalDate today)
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
ORDER BY i.item_id;


-- getItemsByCategory(LocalDate today, int itemCategoryID)
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
ORDER BY i.item_id;


-- getAllItemCategories()
SELECT
    item_category_id,
    item_category_name
FROM item_category
ORDER BY item_category_id;