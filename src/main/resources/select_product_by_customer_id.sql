SELECT o.product_name
FROM CUSTOMERS c
         JOIN ORDERS o ON c.id = o.customer_id
WHERE name = ?
ORDER BY c.id;

