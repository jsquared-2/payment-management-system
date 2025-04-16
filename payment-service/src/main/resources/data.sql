-- Ensure the 'payment' table exists
CREATE TABLE IF NOT EXISTS payment
(
    id              UUID PRIMARY KEY,
    customer_id     UUID                NOT NULL,
    payment_method  VARCHAR(50)         NOT NULL,
    amount          NUMERIC(12, 2)      NOT NULL,
    status          VARCHAR(50)         NOT NULL,
    created_at      TIMESTAMP           NOT NULL
    );

-- Insert seed payments
INSERT INTO payment (id, customer_id, payment_method, amount, status, created_at)
SELECT '78df41df-d3a0-45a9-9e28-c9d3acef83df',
       'a480eb7c-fb2a-7d54-a25c-f1ec83d8fbf7',
       'DEBIT',
       100.00,
       'COMPLETED',
       '2025-04-16 05:14:13.426461'
    WHERE NOT EXISTS (SELECT 1 FROM payment WHERE id = '78df41df-d3a0-45a9-9e28-c9d3acef83df');

INSERT INTO payment (id, customer_id, payment_method, amount, status, created_at)
SELECT '86f52446-1bc2-4814-ac2f-d68d2e44c72f',
       'fb05dbe9-e21b-ecc1-3da1-6cfe87dc3fec',
       'DEBIT',
       63.43,
       'COMPLETED',
       '2025-04-16 05:14:31.59504'
    WHERE NOT EXISTS (SELECT 1 FROM payment WHERE id = '86f52446-1bc2-4814-ac2f-d68d2e44c72f');

INSERT INTO payment (id, customer_id, payment_method, amount, status, created_at)
SELECT 'ceabf387-b80e-4c99-a47d-43eba5be4691',
       'bdd1a9ff-81b2-d5b9-135a-065ffbbb0c8f',
       'DEBIT',
       7896.90,
       'COMPLETED',
       '2025-04-16 05:14:23.518285'
    WHERE NOT EXISTS (SELECT 1 FROM payment WHERE id = 'ceabf387-b80e-4c99-a47d-43eba5be4691');

INSERT INTO payment (id, customer_id, payment_method, amount, status, created_at)
SELECT 'a1b2c3d4-e5f6-7890-1234-56789abcdef0',
       'd3c2b1a4-6f5e-4d3c-a1b2-c3d4e5f6a7b8',
       'CREDIT',
       205.75,
       'FAILED',
       '2025-04-15 13:22:45.123456'
    WHERE NOT EXISTS (SELECT 1 FROM payment WHERE id = 'a1b2c3d4-e5f6-7890-1234-56789abcdef0');

INSERT INTO payment (id, customer_id, payment_method, amount, status, created_at)
SELECT '223e4567-e89b-12d3-a456-426614174003',
       'e3f5c2b1-d4a7-4c1b-83e1-2c4e5f6a7b9d',
       'CREDIT',
       1500.00,
       'FAILED',
       '2025-04-10 09:00:00.426462'
    WHERE NOT EXISTS (SELECT 1 FROM payment WHERE id = '223e4567-e89b-12d3-a456-426614174003');

INSERT INTO payment (id, customer_id, payment_method, amount, status, created_at)
SELECT '333e4567-e89b-12d3-a456-426614174004',
       'f9b8a7c6-d5e4-4c3b-a2f1-3d4e5f6a7b0c',
       'DEBIT',
       42.89,
       'COMPLETED',
       '2025-04-17 10:20:35.426461'
    WHERE NOT EXISTS (SELECT 1 FROM payment WHERE id = '333e4567-e89b-12d3-a456-426614174004');

INSERT INTO payment (id, customer_id, payment_method, amount, status, created_at)
SELECT '444e4567-e89b-12d3-a456-426614174005',
       'abcd1234-abcd-1234-abcd-1234567890ef',
       'CREDIT',
       999.99,
       'FAILED',
       '2025-04-16 12:00:00.425461'
    WHERE NOT EXISTS (SELECT 1 FROM payment WHERE id = '444e4567-e89b-12d3-a456-426614174005');

INSERT INTO payment (id, customer_id, payment_method, amount, status, created_at)
SELECT '555e4567-e89b-12d3-a456-426614174006',
       'aaaa1111-bbbb-2222-cccc-333344445555',
       'DEBIT',
       349.99,
       'COMPLETED',
       '2025-04-14 15:45:20.426463'
    WHERE NOT EXISTS (SELECT 1 FROM payment WHERE id = '555e4567-e89b-12d3-a456-426614174006');

INSERT INTO payment (id, customer_id, payment_method, amount, status, created_at)
SELECT '666e4567-e89b-12d3-a456-426614174007',
       'dddd6666-eeee-7777-ffff-88889999aaaa',
       'DEBIT',
       87.50,
       'COMPLETED',
       '2025-04-13 08:15:55.426761'
    WHERE NOT EXISTS (SELECT 1 FROM payment WHERE id = '666e4567-e89b-12d3-a456-426614174007');

INSERT INTO payment (id, customer_id, payment_method, amount, status, created_at)
SELECT '777e4567-e89b-12d3-a456-426614174008',
       'bbbbcccc-dddd-eeee-ffff-111122223333',
       'CREDIT',
       120.00,
       'COMPLETED',
       '2025-04-11 22:10:10.428461'
    WHERE NOT EXISTS (SELECT 1 FROM payment WHERE id = '777e4567-e89b-12d3-a456-426614174008');
