-- ==========================================
-- TaxRepo
-- ==========================================

-- getCurrentTax(LocalDate dateOf)
SELECT
    tax_id,
    tax_percentage,
    start_date,
    end_date
FROM tax
WHERE start_date <= ?
  AND (
    end_date IS NULL
        OR end_date >= ?
    );