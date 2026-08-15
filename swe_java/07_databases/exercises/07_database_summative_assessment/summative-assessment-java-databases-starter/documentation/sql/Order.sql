-- ==========================================
-- OrderRepo
-- ==========================================


-- ==========================================
-- getOrderById(int id)
-- Order + Server
-- ==========================================

SELECT
    o.order_id,
    o.server_id,
    o.order_date,
    o.sub_total,
    o.tax,
    o.tip,
    o.total,
    s.first_name AS server_first_name,
    s.last_name AS server_last_name,
    s.hire_date AS server_hire_date,
    s.term_date AS server_term_date
FROM orders o
         INNER JOIN server s
                    ON o.server_id = s.server_id
WHERE o.order_id = ?;


-- ==========================================
-- Order Items for an Order
-- ==========================================

SELECT
    oi.order_item_id,
    oi.order_id,
    oi.item_id,
    oi.quantity,
    oi.price,
    i.item_category_id,
    i.item_name,
    i.item_description,
    i.start_date AS item_start_date,
    i.end_date AS item_end_date,
    i.unit_price,
    ic.item_category_name
FROM order_item oi
         INNER JOIN item i
                    ON oi.item_id = i.item_id
         INNER JOIN item_category ic
                    ON i.item_category_id = ic.item_category_id
WHERE oi.order_id = ?
ORDER BY oi.order_item_id;


-- ==========================================
-- Payments for an Order
-- ==========================================

SELECT
    p.payment_id,
    p.payment_type_id,
    p.order_id,
    p.amount,
    pt.payment_type_name
FROM payment p
         INNER JOIN payment_type pt
                    ON p.payment_type_id = pt.payment_type_id
WHERE p.order_id = ?
ORDER BY p.payment_id;

-- ==========================================
-- getAllOrders()
-- ==========================================

SELECT
    o.order_id,
    o.server_id,
    o.order_date,
    o.sub_total,
    o.tax,
    o.tip,
    o.total,
    s.first_name AS server_first_name,
    s.last_name AS server_last_name,
    s.hire_date AS server_hire_date,
    s.term_date AS server_term_date
FROM orders o
         INNER JOIN server s
                    ON o.server_id = s.server_id
ORDER BY o.order_id;