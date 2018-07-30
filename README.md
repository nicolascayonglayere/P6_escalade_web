# P6_escalade_web par Nicolas Cayon-Glayère 

Vous trouverez ici mon travail sur ce projet.

-- Arborescence du projet -- 
le dossier P6_BDD contient le script de creation des tables de la base de donnée postgreSQL
		-> creation_tables_v3.sql
	le script d'insertion d'un jeu de données
		-> insertion.sql
	le script pour purger les tables
		-> purge_tables.sql
	le modèle physique de données
		-> MPD_P6_v2.architect (à ouvrir avec powerArchitect)

le dossier UML_WEB contient une petite webapp faites avec J2EE pour pratiquer.
Dans ce dossier, vous trouverez la démarche UML ainsi que les différents diagrammes.

le dossier P6_structure/warez_escalade contient la webapp du projet
Dans ce dossier, vous trouverez les différents modules (business, consummer, technical, model et webapp) qui interagissent.

-- Chemin à modifier -- 
dans le module webapp dans le package oc.P6.escalade.WebappHelper dans la classe GestionFichierProperties : 
-> chemin du fichier configMail.properties à modifier dans le constructeur de la classe

dans le module technical : 
-> chemin du fichier récupérant les log : src/main/resources/log4j2.xml
-> chemin de stockage des images : src/data/conf/configMail.properties; la propriete se nomme chemin.upload.


-- Configuration de l'envoi de mail -- 
dans le module technical, dans le fichier src/data/conf/configMail.properties, vous trouverez les différentes propriétés à modifier pour affecter un compte pour l'envoi de mail.
