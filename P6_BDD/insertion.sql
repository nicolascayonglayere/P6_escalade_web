
--table role_utilisateur--
BEGIN TRANSACTION;
INSERT INTO role_utilisateur (role_utilisateur) VALUES ('administrateur');
INSERT INTO role_utilisateur (role_utilisateur) VALUES ('moderateur');
INSERT INTO role_utilisateur (role_utilisateur) VALUES ('utilisateur');
INSERT INTO role_utilisateur (role_utilisateur) VALUES ('banni');
COMMIT;

--table utilisateur--
BEGIN TRANSACTION;
INSERT INTO utilisateur (nom, prenom, pseudo, password_utilisateur, id_role) VALUES ('ncg', 'nicolas', 'yogj', '$2a$10$PkyNIKEAqbMrZNeQ/QPRyeOV0gB.Q3LFxdjU94YlqUCzj.BhwVaVa', 1);
INSERT INTO utilisateur (nom, prenom, pseudo, password_utilisateur, id_role) VALUES ('abdsc', 'nicole', 'nico', '$2a$10$A/ZfYQ4jybiU5sChtDbSTOsxokbXhpY/2ZHwvuL6fYg2YcZPkncMe', 2);
INSERT INTO utilisateur (nom, prenom, pseudo, password_utilisateur, id_role) VALUES ('qsdfg', 'benoit', 'ben', '$2a$10$GBXyWWH.TkFD1OyrTTfLXerqmHRHClzd2T8HHGAIFU.8dV2xeHf4a', 3);
INSERT INTO utilisateur (nom, prenom, pseudo, password_utilisateur, id_role) VALUES ('wxcvb', 'andre', 'ban', '$2a$10$jpZv/CpgtqUMFYeK3BkAA.QI2sKxbR4l2bQ9kZRBLs4fuFaxqt9ya', 4);
COMMIT;

--table coordonnee_utilisateur--
BEGIN TRANSACTION;
INSERT INTO coordonnee_utilisateur (email, adresse_postale, id_utilisateur) VALUES ('ncg2103@yahoo.fr', 'mon adresse', 1);
INSERT INTO coordonnee_utilisateur (email, adresse_postale, id_utilisateur) VALUES ('nicole@yahoo.fr', 'mon adresse de Nicole', 2);
INSERT INTO coordonnee_utilisateur (email, adresse_postale, id_utilisateur) VALUES ('ben3@yahoo.fr', 'mon adresse de Ben', 3);
INSERT INTO coordonnee_utilisateur (email, adresse_postale, id_utilisateur) VALUES ('andre@yahoo.fr', 'mon adresse de andre', 4);
COMMIT;

--table topo--
BEGIN TRANSACTION;
INSERT INTO topo (nom, id_utilisateur, nombre_exemplaires, description, longitude, latitude, image, construction) VALUES ('Falaise de Voreppe', 1, 3, 
'Seul site de La Cuvette permettant de profiter d’un parfait coucher de soleil sur l’horizon, particulièrement appréciable les après midis d’hiver.
Derrière le ronronnement paisible de la nationale en contrebas se cachent des grandes envolées techniques et exigeantes sur du rocher étonnamment sculpté',
 5.624, 45.311, 'falaisedevoreppe', false);
COMMIT;

--table site--
BEGIN TRANSACTION;
INSERT INTO site (nom, description, id_topo) VALUES ('Secteur du Jardinier', '', 1);
COMMIT;

--table secteur--
BEGIN TRANSACTION;
INSERT INTO secteur (nom, description, id_site, image) VALUES ('Droite', '', 1, 'images/falaisedevoreppe/secteurdroite.jpg');
INSERT INTO secteur (nom, description, id_site, image) VALUES ('Gauche', '', 1, 'images/falaisedevoreppe/secteurgauche.jpg');
COMMIT;

--table voie--
BEGIN TRANSACTION;
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Le poireau', '6b', 0, 2, 1, 1, 'Encore une fissure redoutable');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('La cabane au fond du jardin', '8a', 0, 2, 1, 1, 'Deux pas de blocs…inédits');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('L''oignon', '7a+', 0, 1, 0, 1, 'Complexe et technique');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('La laitue', '7a+', 0, 2, 0, 1, 'The mur à crépi (accès conseillé par 19)');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('La limace', '8a+', 0, 1, 0, 1, 'L’art de repter dans une dalle bien compacte');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('La courgette', '6b+', 0, 2, 1, 1, 'Technique sur un rocher très sculpté');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Le haricot', '6c', 0, 2, 1, 1, '2 surprises artificielles pour rejoindre un beau pilier');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Le haricot du missionnaire', '8a', 0, 1, 0, 1, 'Section très intense. 2 monos à souvenir');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Le missionnaire', '7c', 0, 1, 0, 1, 'Bloc physique. Peu faite');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Le niacoue', '7c', 0, 2, 1, 1, 'Pilier très esthétique (7b+ pour la sortie de gauche)');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Le radis', '7b+', 0, 1, 0, 1, 'Ultra bloc et à doigt');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('La carotte', '7a+', 0, 1, 0, 1, 'Moins moche qu’elle n’y parait');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Le navet', '7a+', 0, 1, 0, 1, 'Effectivement…');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('My own private Idaho', '6b', 0, 2, 1, 2, 'Belle ambiance en fissure à doigt');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Down by law', '7c', 0, 2, 1, 2, 'Grande traversée technique (7b+ par le biscuit à droite en haut)');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Fade to black', '7c+', 0, 2, 1, 2, 'Mur à croutes éloignées');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('L''ultime combat', '8a', 0, 1, 0, 2, 'Interminablement varié');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Le dernier combat', '8a+', 0, 1, 0, 2, 'Un grand voyage, des plus exigeants. 1ère partie plus ardue aujourd’hui,
quelques pieds ayant cassés');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Lasting fight', '8a', 0, 1, 0, 2, 'La même, avec une fin moins rési');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Everlasting fight', '8a', 0, 1, 0, 2, 'Encore la même encore, en encore moins long');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Petit rond', '7b', 0, 1, 0, 2, 'Très court, et très forçu');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Fading light', '8a', 0, 2, 1, 2, 'D’un esthétisme rare, le Dinosaure de LaCuvette');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Le combat de ruses', '8b+', 0, 2, 1, 2, 'Apparemment très physique');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('L''ampoule du jardinier', '7c', 0, 1, 0, 2, 'Incontournable…malheureusement');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Bouze maker', '7c', 0, 1, 0, 2, 'C’est clair…mais même commentaire que la précédente');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('Made in USA', '6b+', 0, 1, 0, 2, 'Et aurait du y rester');
INSERT INTO voie (nom, cotation, hauteur, nombre_longueur, nombre_point, id_secteur, description) VALUES ('La patate', '7a', 0, 1, 0, 2, '….Pourrie ?');
COMMIT;