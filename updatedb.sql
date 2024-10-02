ALTER TABLE `registre_caisse` CHANGE `discriminator` `discriminator`
    VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'ADMIN';
