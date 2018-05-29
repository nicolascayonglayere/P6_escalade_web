
--table role_utilisateur--
INSERT INTO role_utilisateur (id_role, role) VALUES (1, 'administrateur');
INSERT INTO role_utilisateur (id_role, role) VALUES (2, 'moderateur');
INSERT INTO role_utilisateur (id_role, role) VALUES (3, 'utilisateur');
INSERT INTO role_utilisateur (id_role, role) VALUES (4, 'banni');

--table utilisateur--
INSERT INTO utilisateur (id_utilisateur, nom, prenom, pseudo, password, id_role) VALUES (1, 'ncg', 'nicolas', 'yogj', 'admin', 1);

--table coordonnee_utilisateur--
INSERT INTO coordonnee_utilisateur (id_coordonnee, email, adresse_postale, id_utilisateur) VALUES (1, 'ncg2103@yahoo.fr', 'mon adresse', 1);

--table topo--
INSERT INTO topo (id_topo, nom, id_utilisateur) VALUES (1, 'Falaise de Voreppe', 1);

--table site--
INSERT INTO site (id_site, nom, id_topo) VALUES (1, 'Secteur du Jardinier', 1);

--table secteur--
INSERT INTO secteur (id_secteur, nom, id_site) VALUES (1, 'Droite', 1);
INSERT INTO secteur (id_secteur, nom, id_site) VALUES (2, 'Gauche', 1);

--table voie--
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (1, 'Le poireau', '6b', 0, 2, 1, 1);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (2, 'La cabane au fond du jardin', '8a', 0, 2, 1, 1);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (3, 'L''oignon', '7a+', 0, 1, 0, 1);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (4, 'La laitue', '7a+', 0, 2, 0, 1);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (5, 'La limace', '8a+', 0, 1, 0, 1);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (6, 'La courgette', '6b+', 0, 2, 1, 1);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (7, 'Le haricot', '6c', 0, 2, 1, 1);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (8, 'Le haricot du missionnaire', '8a', 0, 1, 0, 1);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (9, 'Le missionnaire', '7c', 0, 1, 0, 1);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (10, 'Le niacoue', '7c', 0, 2, 1, 1);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (11, 'Le radis', '7b+', 0, 1, 0, 1);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (12, 'La carotte', '7a+', 0, 1, 0, 1);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (13, 'Le navet', '7a+', 0, 1, 0, 1);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (14, 'My own private Idaho', '6b', 0, 2, 1, 2);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (15, 'Down by law', '7c', 0, 2, 1, 2);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (16, 'Fade to black', '7c+', 0, 2, 1, 2);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (17, 'L''ultime combat', '8a', 0, 1, 0, 2);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (18, 'Le dernier combat', '8a+', 0, 1, 0, 2);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (19, 'Lasting fight', '8a', 0, 1, 0, 2);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (20, 'Everlasting fight', '8a', 0, 1, 0, 2);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (21, 'Petit rond', '7b', 0, 1, 0, 2);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (22, 'Fading light', '8a', 0, 2, 1, 2);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (23, 'Le combat de ruses', '8b+', 0, 2, 1, 2);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (24, 'L''ampoule du jardinier', '7c', 0, 1, 0, 2);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (25, 'Bouze maker', '7c', 0, 1, 0, 2);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (26, 'Made in USA', '6b+', 0, 1, 0, 2);
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur) VALUES (27, 'La patate', '7a', 0, 1, 0, 2);