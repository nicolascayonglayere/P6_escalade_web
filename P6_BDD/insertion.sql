
--table role_utilisateur--
BEGIN TRANSACTION;
INSERT INTO role_utilisateur (id_role, role) VALUES (1, 'administrateur');
INSERT INTO role_utilisateur (id_role, role) VALUES (2, 'moderateur');
INSERT INTO role_utilisateur (id_role, role) VALUES (3, 'utilisateur');
INSERT INTO role_utilisateur (id_role, role) VALUES (4, 'banni');
COMMIT;

--table utilisateur--
BEGIN TRANSACTION;
INSERT INTO utilisateur (id_utilisateur, nom, prenom, pseudo, password, id_role) VALUES (1, 'ncg', 'nicolas', 'yogj', 'admin', 1);
INSERT INTO utilisateur (id_utilisateur, nom, prenom, pseudo, password, id_role) VALUES (2, 'abdsc', 'nicole', 'nico', 'modo', 2);
INSERT INTO utilisateur (id_utilisateur, nom, prenom, pseudo, password, id_role) VALUES (3, 'qsdfg', 'benoit', 'ben', 'user', 3);
INSERT INTO utilisateur (id_utilisateur, nom, prenom, pseudo, password, id_role) VALUES (4, 'wxcvb', 'andre', 'ban', 'user', 4);
COMMIT;

--- cryptage des mots de passe
BEGIN TRANSACTION;
UPDATE public.utilisateur SET password = crypt('admin', gen_salt('bf',8)) WHERE id_utilisateur = 1;
UPDATE public.utilisateur SET password = crypt('modo', gen_salt('bf',8)) WHERE id_utilisateur = 2;
UPDATE public.utilisateur SET password = crypt('user', gen_salt('bf',8)) WHERE id_utilisateur = 3;
UPDATE public.utilisateur SET password = crypt('user', gen_salt('bf',8)) WHERE id_utilisateur = 4;
COMMIT;

--table coordonnee_utilisateur--
BEGIN TRANSACTION;
INSERT INTO coordonnee_utilisateur (id_coordonnee, email, adresse_postale, id_utilisateur) VALUES (1, 'ncg2103@yahoo.fr', 'mon adresse', 1);
INSERT INTO coordonnee_utilisateur (id_coordonnee, email, adresse_postale, id_utilisateur) VALUES (2, 'nicole@yahoo.fr', 'mon adresse de Nicole', 2);
INSERT INTO coordonnee_utilisateur (id_coordonnee, email, adresse_postale, id_utilisateur) VALUES (3, 'ben3@yahoo.fr', 'mon adresse de Ben', 3);
INSERT INTO coordonnee_utilisateur (id_coordonnee, email, adresse_postale, id_utilisateur) VALUES (4, 'andre@yahoo.fr', 'mon adresse de andre', 4);
COMMIT;

--table topo--
BEGIN TRANSACTION;
INSERT INTO topo (id_topo, nom, id_utilisateur, nombre_exemplaires, description, longitude, latitude, image) VALUES (1, 'Falaise de Voreppe', 1, 3, 
'Seul site de La Cuvette permettant de profiter d�un parfait coucher de soleil sur l�horizon, particuli�rement appr�ciable les apr�s midis d�hiver.
Derri�re le ronronnement paisible de la nationale en contrebas se cachent des grandes envol�es techniques et exigeantes sur du rocher �tonnamment sculpt�',
  '45�18''41""N', '5�37''29""E', 'images/falaisedevoreppe/' );
COMMIT;

--table site--
BEGIN TRANSACTION;
INSERT INTO site (id_site, nom, description, id_topo) VALUES (1, 'Secteur du Jardinier', '', 1);
COMMIT;

--table secteur--
BEGIN TRANSACTION;
INSERT INTO secteur (id_secteur, nom, description, id_site, image) VALUES (1, 'Droite', '', 1, 'images/falaisedevoreppe/secteurdroite.jpg');
INSERT INTO secteur (id_secteur, nom, description, id_site, image) VALUES (2, 'Gauche', '', 1, 'images/falaisedevoreppe/secteurgauche.jpg');
COMMIT;

--table voie--
BEGIN TRANSACTION;
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (1, 'Le poireau', '6b', 0, 2, 1, 1, 'Encore une fissure redoutable');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (2, 'La cabane au fond du jardin', '8a', 0, 2, 1, 1, 'Deux pas de blocs�in�dits');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (3, 'L''oignon', '7a+', 0, 1, 0, 1, 'Complexe et technique');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (4, 'La laitue', '7a+', 0, 2, 0, 1, 'The mur � cr�pi (acc�s conseill� par 19)');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (5, 'La limace', '8a+', 0, 1, 0, 1, 'L�art de repter dans une dalle bien compacte');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (6, 'La courgette', '6b+', 0, 2, 1, 1, 'Technique sur un rocher tr�s sculpt�');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (7, 'Le haricot', '6c', 0, 2, 1, 1, '2 surprises artificielles pour rejoindre un beau pilier');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (8, 'Le haricot du missionnaire', '8a', 0, 1, 0, 1, 'Section tr�s intense. 2 monos � souvenir');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (9, 'Le missionnaire', '7c', 0, 1, 0, 1, 'Bloc physique. Peu faite');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (10, 'Le niacoue', '7c', 0, 2, 1, 1, 'Pilier tr�s esth�tique (7b+ pour la sortie de gauche)');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (11, 'Le radis', '7b+', 0, 1, 0, 1, 'Ultra bloc et � doigt');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (12, 'La carotte', '7a+', 0, 1, 0, 1, 'Moins moche qu�elle n�y parait');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (13, 'Le navet', '7a+', 0, 1, 0, 1, 'Effectivement�');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (14, 'My own private Idaho', '6b', 0, 2, 1, 2, 'Belle ambiance en fissure � doigt');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (15, 'Down by law', '7c', 0, 2, 1, 2, 'Grande travers�e technique (7b+ par le biscuit � droite en haut)');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (16, 'Fade to black', '7c+', 0, 2, 1, 2, 'Mur � croutes �loign�es');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (17, 'L''ultime combat', '8a', 0, 1, 0, 2, 'Interminablement vari�');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (18, 'Le dernier combat', '8a+', 0, 1, 0, 2, 'Un grand voyage, des plus exigeants. 1�re partie plus ardue aujourd�hui,
quelques pieds ayant cass�s');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (19, 'Lasting fight', '8a', 0, 1, 0, 2, 'La m�me, avec une fin moins r�si');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (20, 'Everlasting fight', '8a', 0, 1, 0, 2, 'Encore la m�me encore, en encore moins long');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (21, 'Petit rond', '7b', 0, 1, 0, 2, 'Tr�s court, et tr�s for�u');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (22, 'Fading light', '8a', 0, 2, 1, 2, 'D�un esth�tisme rare, le Dinosaure de LaCuvette');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (23, 'Le combat de ruses', '8b+', 0, 2, 1, 2, 'Apparemment tr�s physique');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (24, 'L''ampoule du jardinier', '7c', 0, 1, 0, 2, 'Incontournable�malheureusement');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (25, 'Bouze maker', '7c', 0, 1, 0, 2, 'C�est clair�mais m�me commentaire que la pr�c�dente');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (26, 'Made in USA', '6b+', 0, 1, 0, 2, 'Et aurait du y rester');
INSERT INTO voie (id_voie, nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES (27, 'La patate', '7a', 0, 1, 0, 2, '�.Pourrie ?');
COMMIT;