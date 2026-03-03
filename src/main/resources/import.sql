-- Regions
INSERT INTO regions (code, nom) VALUES ('IDF', 'Île-de-France');
INSERT INTO regions (code, nom) VALUES ('PACA', 'Provence-Alpes-Côte d''Azur');
INSERT INTO regions (code, nom) VALUES ('ARA', 'Auvergne-Rhône-Alpes');
INSERT INTO regions (code, nom) VALUES ('HDF', 'Hauts-de-France');
INSERT INTO regions (code, nom) VALUES ('NAQ', 'Nouvelle-Aquitaine');
INSERT INTO regions (code, nom) VALUES ('OCC', 'Occitanie');
INSERT INTO regions (code, nom) VALUES ('BRE', 'Bretagne');

-- Agences
INSERT INTO agences (code, nom, region_id) VALUES ('PARIS', 'Agence Paris', (SELECT id FROM regions WHERE code = 'IDF'));
INSERT INTO agences (code, nom, region_id) VALUES ('BOULOGNE', 'Agence Boulogne', (SELECT id FROM regions WHERE code = 'IDF'));
INSERT INTO agences (code, nom, region_id) VALUES ('COURBEVOIE', 'Agence Courbevoie', (SELECT id FROM regions WHERE code = 'IDF'));
INSERT INTO agences (code, nom, region_id) VALUES ('NICE', 'Agence Nice', (SELECT id FROM regions WHERE code = 'PACA'));
INSERT INTO agences (code, nom, region_id) VALUES ('LYON', 'Agence Lyon', (SELECT id FROM regions WHERE code = 'ARA'));
INSERT INTO agences (code, nom, region_id) VALUES ('GRENOBLE', 'Agence Grenoble', (SELECT id FROM regions WHERE code = 'ARA'));
INSERT INTO agences (code, nom, region_id) VALUES ('LILLE', 'Agence Lille', (SELECT id FROM regions WHERE code = 'HDF'));
INSERT INTO agences (code, nom, region_id) VALUES ('BORDEAUX', 'Agence Bordeaux', (SELECT id FROM regions WHERE code = 'NAQ'));
INSERT INTO agences (code, nom, region_id) VALUES ('TOULOUSE', 'Agence Toulouse', (SELECT id FROM regions WHERE code = 'OCC'));
INSERT INTO agences (code, nom, region_id) VALUES ('RENNES', 'Agence Rennes', (SELECT id FROM regions WHERE code = 'BRE'));