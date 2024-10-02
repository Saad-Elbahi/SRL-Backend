

use sr_caissedb;

DELETE FROM `ligne_registre_caisse` where annee=2023 and mois=5;
DELETE FROM `piece_caisse` where annee=2023 and mois=5 ;
DELETE FROM `registre_caisse` where annee=2023 and mois=5;
# DELETE FROM `budget`;
# DELETE FROM `rubrique`;


# MOIS 04/2023
UPDATE `registre_caisse` SET `solde_initial` = '20736.41' where annee=2023 and mois=4;

# MOIS 05/2023
UPDATE `registre_caisse` SET `solde_initial` = '74651.02' where annee=2023 and mois=5;

UPDATE ligne_registre_caisse SET code_beneficiaire=LEFT(code_beneficiaire,4);
UPDATE `registre_caisse` SET solde = solde_initial+total_encaissement-total_decaissement;


INSERT INTO `rubrique`(`id`, `intitule`, `code`, `numero`, discriminator)
VALUES (1, 'BUDGET DIRECTION EXPLOITATION', 'BDE', 1, 'ADMIN'),
       (2, 'BUDGET DIRECTION GENERALE', 'BDG', 2, 'ADMIN'),
       (3, 'BUDGET DIRECTION TECHNIQUE', 'BDT', 3, 'ADMIN'),
       (4, 'BUDGET LOGISTIQUE', 'BLOG', 4, 'ADMIN'),
       (5, 'BUDGET SERVICE JURIDIQUE', 'BJUR', 5, 'ADMIN'),
       (6, 'DONS', 'BDONTS', 6, 'ADMIN'),
       (7, 'DROIT ENREGISTREMENT ET TIMBRE', 'BENR', 7, 'ADMIN'),
       (8, 'FRAIS DE CHANTIER', 'BFCH', 8, 'ADMIN'),
       (9, 'FRAIS DÉPLACEMENT', 'BDEP', 9, 'ADMIN'),
       (10, 'FRAIS WAFACASH', 'BFTIERS', 10, 'ADMIN'),
       (11, 'MENAGE ET CUISINE', 'BMC', 11, 'ADMIN'),
       (12, 'SALAIRE', 'BSAL', 12, 'ADMIN'),
       (13, 'STR', 'BSTR', 13, 'ADMIN'),
       (14, 'ACCESSOIRE DE NETOYAGE', 'ACC', 14, 'CHANTIER'),
       (15, 'DON', 'DON', 15, 'CHANTIER'),
       (16, 'FRAIS DE REUNION', 'FR', 16, 'CHANTIER'),
       (17, 'INTERNET', 'INTERNET', 17, 'CHANTIER'),
       (18, 'MAINTENANCE ET ENTRETIEN', 'ME', 18, 'CHANTIER'),
       (19, 'MESSAGERIE', 'MSG', 19, 'CHANTIER'),
       (20, 'PETIT OUTILLAGE', 'P0', 20, 'CHANTIER'),
       (21, 'SERVICE LIBRAIRIE', 'LIB', 21, 'CHANTIER'),
       (22, 'STRANSPORT', 'TRS', 22, 'CHANTIER'),
       (23, 'FACTURE EAU', 'EAU', 22, 'CHANTIER'),
       (24, 'FACTURE ELT', 'ELT', 22, 'CHANTIER'),
       (25, 'LOYER', 'LOYER', 22, 'CHANTIER'),
       (26, 'FEMME DE MENAGE', 'FM', 22, 'CHANTIER'),
       (27, 'AUTRES', 'AUTRES', 23, 'CHANTIER');


INSERT INTO `budget` (`id`, `annee`, `date_creation`, `mois`, `numero`, `seuil_blocage`, `rubrique_id`,
                      `budget_prevu`, `seuil_avertissement`, `ecart`, budget_consomme, budget_engage_non_valide, budget_engage_valide, budget_caisse_status)
VALUES (null, 2022, '2022-08-23', 8, 1, 5000, 1, 0, 10000, 0, 0, 0, 0, 'DRAFT'),
       (null, 2022, '2022-08-23', 8, 2, 5000, 2, 0, 10000, 0, 0, 0, 0, 'DRAFT'),
       (null, 2022, '2022-08-23', 8, 3, 5000, 3, 0, 10000, 0, 0, 0, 0, 'DRAFT'),
       (null, 2022, '2022-08-23', 8, 4, 5000, 4, 0, 10000, 0, 0, 0, 0, 'DRAFT'),
       (null, 2022, '2022-08-23', 8, 5, 5000, 5, 0, 10000, 0, 0, 0, 0, 'DRAFT'),
       (null, 2022, '2022-08-23', 8, 6, 5000, 6, 0, 10000, 0, 0, 0, 0, 'DRAFT'),
       (null, 2022, '2022-08-23', 8, 7, 5000, 7, 0, 10000, 0, 0, 0, 0, 'DRAFT'),
       (null, 2022, '2022-08-23', 8, 8, 5000, 8, 0, 10000, 0, 0, 0, 0, 'DRAFT'),
       (null, 2022, '2022-08-23', 8, 9, 5000, 9, 0, 10000, 0, 0, 0, 0, 'DRAFT'),
       (null, 2022, '2022-08-23', 8, 10, 5000, 10, 0, 10000, 0, 0, 0, 0, 'DRAFT'),
       (null, 2022, '2022-08-23', 8, 11, 5000, 11, 0, 10000, 0, 0, 0, 0, 'DRAFT'),
       (null, 2022, '2022-08-23', 8, 12, 5000, 12, 0, 10000, 0, 0, 0, 0, 'DRAFT'),
       (null, 2022, '2022-08-23', 8, 13, 5000, 13, 0, 10000, 0, 0, 0, 0, 'DRAFT');



