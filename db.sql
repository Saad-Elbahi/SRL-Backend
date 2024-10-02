/*use sr_affaire;
INSERT INTO `champ_imputation` (`id`, `code`, `intitule`, `numero`, discriminator,type_projet,projet_status,imputable) VALUES
       (NULL, 'MRK VILLA ACHRAF', 'MRK VILLA ACHRAF', NULL, 'MARCHE','BATIMENT','EN_COURS',1);

UPDATE sr_affaire.champ_imputation
SET chef_zone_avatar='unknown.png',
    directeur_projet_avatar='unknown.png',
    ingenieur_avatar='unknown.png',
    conducteur_avatar='unknown.png',
    technicien_avatar='unknown.png',
    comptable_avatar='unknown.png',
    pointeur_avatar='unknown.png',
    magazinier_avatar='unknown.png',
    chef_chantier_avatar='unknown.png';

INSERT INTO `champ_imputation` (`id`, `code`, `intitule`, `numero`, discriminator,projet_status,) VALUES

       (NULL, 'SIEGE', 'SIEGE', NULL, 'BUREAU'),
       (NULL, 'BUREAU-DGA-CASA', 'BUREAU DGA CASA', NULL, 'BUREAU'),
       (NULL, 'BUREAU-FLUIDE-CASA', 'BUREAU FLUIDE CASA', NULL, 'BUREAU'),
       (NULL, 'BUREAU-TECHNIQUE-ITRANE', 'BUREAU TECHNIQUE ITRANE', NULL, 'BUREAU'),

       (NULL, 'ATELIER-BOIS', 'ATELIER MENUISERIE BOIS', NULL, 'ATELIER'),
       (NULL, 'ATELIER-METALLIQUE', 'ATELIER MENUISERIE METALLIQUE', NULL, 'ATELIER'),

       (NULL, 'DEPOT-CENTRAL', 'DEPOT CENTRAL', NULL, 'DEPOT'),
       (NULL, 'DEPOT-HARBIL', 'DEPOT HARBIL', NULL, 'DEPOT'),


       (NULL, 'DIRECTION-GENERALE', 'DIRECTION GENERALE', NULL, 'DIRECTION'),
       (NULL, 'DIRECTION-EXPLOITATION', 'DIRECTION D⁄''EXPLOITATION', NULL, 'DIRECTION'),
       (NULL, 'DIRECTION-TECHNIQUE', 'DIRECTION TECHNIQUE', NULL, 'DIRECTION'),

       (NULL, 'ATELIER-BOIS', 'ATELIER MENUISERIE BOIS', NULL, 'SERVICE-SR'),
       (NULL, 'ATELIER-METALLIQUE', 'ATELIER MENUISERIE METALLIQUE', NULL, 'SERVICE-SR'),
       (NULL, 'CHANTIER', 'CHANTIER', NULL, 'SERVICE-SR'),
       (NULL, 'DEPOT-CENTRAL', 'DEPOT CENTRAL', NULL, 'SERVICE-SR'),
       (NULL, 'DEPOT-HARBIL', 'DEPOT HARBIL', NULL, 'SERVICE-SR'),
       (NULL, 'FLUIDE', 'FLUIDE', NULL, 'SERVICE-SR'),
       (NULL, 'LOGISTIQUE', 'LOGISTIQUE', NULL, 'SERVICE-SR'),
       (NULL, 'TECHNIQUE', 'TECHNIQUE', NULL, 'SERVICE-SR'),
       (NULL, 'SIEGE', 'SIEGE', NULL, 'SERVICE-SR'),
       (NULL, 'CLASSE', 'CLASSE', NULL, 'SERVICE-SR'),
       (NULL, 'DIVERS', 'DIVERS', NULL, 'SERVICE-SR'),

        (NULL, 'SR', 'SOCIETE ROUANDI', NULL, 'BU'),
        (NULL, 'RALU', 'RALU', NULL, 'BU'),
        (NULL, 'RWOOD', 'RWOOD', NULL, 'BU'),
        (NULL, 'RMETAL', 'RMETAL', NULL, 'BU'),
        (NULL, 'RFLOW', 'RFLOW', NULL, 'BU'),

       (NULL, 'ZONE-TANGER', 'ZONE TANGER', NULL, 'ZONE'),
       (NULL, 'ZONE-CASA', 'ZONE CASABLANCA', NULL, 'ZONE'),
       (NULL, 'ZONE-MAR', 'ZONE MARRAKECH', NULL, 'ZONE'),
       (NULL, 'ZONE-ESSAOUIRA', 'ZONE ESSAOUIRA', NULL, 'ZONE'),

        (NULL,'DEPT-FLUIDE',	'DEPT FLUIDE', NULL, 'DEPT'),
        (NULL,'DEPT-BOIS',	'DEPT MENUISIRIE BOIS', NULL, 'DEPT'),
        (NULL,'DEPT-METAL',	'DEPT MENUISIRIE METALLIQUE', NULL, 'DEPT'),
        (NULL,'DEPT-GOUJIL',	'DEPT GOUJIL', NULL, 'DEPT'),
        (NULL,'DEPT-HADI',	'DEPT HADI', NULL, 'DEPT'),
        (NULL,'DEPT-LAAMACH',	'DEPT LAAMACH', NULL, 'DEPT'),
        (NULL,'DEPT-LARHMICH',	'DEPT LARHMICH', NULL, 'DEPT'),
        (NULL,'DEPT-DGA',	'DEPT DGA', NULL, 'DEPT');


-- DELETE FROM `budget`;

INSERT INTO sr_affaire.node_bordereau(node_bordereau_level, name, name_reduit, node_parent_id, marche_id, type_projet)
VALUES ('LOT', 'FERRAILLAGE', 'FERRAILLAGE', NULL, 1, 'BATIMENT'),
       ('LOT', 'FERRAILLAGE', 'FERRAILLAGE', NULL, 2, 'BATIMENT'),
       ('LOT', 'FERRAILLAGE', 'FERRAILLAGE', NULL, 51, 'BATIMENT'),
       ('LOT', 'FERRAILLAGE', 'FERRAILLAGE', NULL, 52, 'BATIMENT');

INSERT INTO sr_affaire.node_bordereau(node_bordereau_level, name, name_reduit, node_parent_id, marche_id, type_projet)
VALUES ('SOUS_LOT', 'METRE CARRE COUVERT', 'METRE CARRE COUVERT', 1, 1, 'BATIMENT'),
       ('SOUS_LOT', 'METRE CARRE COUVERT', 'METRE CARRE COUVERT', 59, 2, 'BATIMENT'),
       ('SOUS_LOT', 'METRE CARRE COUVERT', 'METRE CARRE COUVERT', 135, 51, 'BATIMENT'),
       ('SOUS_LOT', 'METRE CARRE COUVERT', 'METRE CARRE COUVERT', 328, 52, 'BATIMENT');



UPDATE sr_affaire.champ_imputation SET chef_zone_username=''
WHERE chef_zone_username IS NULL;
UPDATE sr_affaire.champ_imputation
SET conducteur_username=''
WHERE conducteur_username IS NULL;
UPDATE sr_affaire.champ_imputation
SET technicien_username=''
WHERE technicien_username IS NULL;
UPDATE sr_affaire.champ_imputation
SET comptable_username=''
WHERE comptable_username IS NULL;
UPDATE sr_affaire.champ_imputation
SET pointeur_username=''
WHERE pointeur_username IS NULL;
UPDATE sr_affaire.champ_imputation
SET magazinier_username=''
WHERE magazinier_username IS NULL;
UPDATE sr_affaire.champ_imputation
SET chef_chantier_username=''
WHERE chef_chantier_username IS NULL;

UPDATE sr_affaire.champ_imputation
SET chef_zone_full_name=''
WHERE chef_zone_full_name IS NULL;
UPDATE sr_affaire.champ_imputation
SET conducteur_full_name=''
WHERE conducteur_full_name IS NULL;
UPDATE sr_affaire.champ_imputation
SET technicien_full_name=''
WHERE technicien_full_name IS NULL;
UPDATE sr_affaire.champ_imputation
SET comptable_full_name=''
WHERE comptable_full_name IS NULL;
UPDATE sr_affaire.champ_imputation
SET pointeur_full_name=''
WHERE pointeur_full_name IS NULL;
UPDATE sr_affaire.champ_imputation
SET magazinier_full_name=''
WHERE magazinier_full_name IS NULL;
UPDATE sr_affaire.champ_imputation
SET chef_chantier_full_name=''
WHERE chef_chantier_full_name IS NULL;


SELECT id,
       code,
       chef_zone_username,
       chef_zone_full_name,
       conducteur_username,
       conducteur_full_name,
       technicien_username,
       technicien_full_name,
       comptable_username,
       comptable_full_name,
       pointeur_username,
       pointeur_full_name,
       magazinier_username,
       magazinier_full_name,
       chef_chantier_username,
       chef_chantier_full_name
FROM sr_affaire.champ_imputation
where code = 'RAB-IFMS-5/2021'
   or code = 'TAMS-PRISON-3/2021'
   or code = 'DAKHLA-CMC-11/2022'
   or code = 'ESS-PRISON-4/2022';

# TAMS-PRISON-3/2021
UPDATE sr_affaire.champ_imputation
SET chef_zone_username='6051',
    chef_zone_full_name='YOUNESS HADI',
    conducteur_username='6084',
    conducteur_full_name='MOHAMED YASSI',
    technicien_username='6124',
    technicien_full_name='OUSSAMA TANANI',
    comptable_username='6057',
    comptable_full_name='ABDELFETTAH ALLILI',
    pointeur_username='6057',
    pointeur_full_name='ABDELFETTAH ALLILI',
    magazinier_username='',
    magazinier_full_name='',
    chef_chantier_username='6030',
    chef_chantier_full_name='AZIZ ELBOUAZIZI'
WHERE code = 'TAMS-PRISON-3/2021';

# RAB-IFMS-5/2021
UPDATE sr_affaire.champ_imputation
SET chef_zone_username='6051',
    chef_zone_full_name='YOUNESS HADI',
    conducteur_username='6084',
    conducteur_full_name='MOHAMED YASSI',
    technicien_username='6115',
    technicien_full_name='RAJAE ALLOUANI',
    comptable_username='6034',
    comptable_full_name='BRAHIM BELIYD',
    pointeur_username='6034',
    pointeur_full_name='BRAHIM BELIYD',
    magazinier_username='6034',
    magazinier_full_name='BRAHIM BELIYD',
    chef_chantier_username='6030',
    chef_chantier_full_name='AZIZ ELBOUAZIZI'
WHERE code = 'RAB-IFMS-5/2021';


# DAKHLA-CMC-11/2022



# ESS-PRISON-4/2022
UPDATE sr_affaire.champ_imputation
SET chef_zone_username='6152',
    chef_zone_full_name='TARIK BOULOUIZ',
    conducteur_username='',
    conducteur_full_name='',
    technicien_username='tech-essaouira',
    technicien_full_name='MEHDI',
    comptable_username='',
    comptable_full_name='',
    pointeur_username='',
    pointeur_full_name='',
    magazinier_username='',
    magazinier_full_name='',
    chef_chantier_username='',
    chef_chantier_full_name=''
WHERE code = 'ESS-PRISON-4/2022';



*/
