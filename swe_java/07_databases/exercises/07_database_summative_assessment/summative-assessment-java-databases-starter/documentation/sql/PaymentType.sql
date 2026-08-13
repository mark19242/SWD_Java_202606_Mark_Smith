-- ==========================================
-- PaymentTypeRepo
-- ==========================================

-- getAll()
SELECT
    payment_type_id,
    payment_type_name
FROM payment_type
ORDER BY payment_type_id;