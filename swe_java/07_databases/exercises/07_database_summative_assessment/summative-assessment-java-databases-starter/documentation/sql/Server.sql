-- ==========================================
-- ServerRepo
-- ==========================================

-- getServerById(int id)
SELECT
    server_id,
    first_name,
    last_name,
    hire_date,
    term_date
FROM server
WHERE server_id = ?;


-- getAllAvailableServers(LocalDate date)
SELECT
    server_id,
    first_name,
    last_name,
    hire_date,
    term_date
FROM server
WHERE hire_date <= ?
  AND (
    term_date IS NULL
        OR term_date >= ?
    )
ORDER BY server_id;