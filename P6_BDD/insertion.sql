
--table role_utilisateur--
INSERT INTO role_utilisateur (id_role, role) VALUES (1, 'administrateur');
INSERT INTO role_utilisateur (id_role, role) VALUES (2, 'moderateur');
INSERT INTO role_utilisateur (id_role, role) VALUES (3, 'utilisateur');

--table utilisateur--
INSERT INTO utilisateur (id_utilisateur, nom, prenom, pseudo, password, id_role) VALUES (1, 'ncg', 'nicolas', 'yogj', 'admin', 1);