
CREATE SEQUENCE public.role_utilisateur_id_role_seq_1;

CREATE TABLE public.role_utilisateur (
                id_role INTEGER NOT NULL DEFAULT nextval('public.role_utilisateur_id_role_seq_1'),
                role VARCHAR(30) NOT NULL,
                CONSTRAINT role_utilisateur_pk PRIMARY KEY (id_role)
);


ALTER SEQUENCE public.role_utilisateur_id_role_seq_1 OWNED BY public.role_utilisateur.id_role;

CREATE SEQUENCE public.utilisateur_id_seq;

CREATE TABLE public.utilisateur (
                id_utilisateur INTEGER NOT NULL DEFAULT nextval('public.utilisateur_id_seq'),
                nom VARCHAR(20) NOT NULL,
                prenom VARCHAR(20) NOT NULL,
                pseudo VARCHAR(30) NOT NULL,
                password VARCHAR(60) NOT NULL,
                id_role INTEGER NOT NULL,
                CONSTRAINT utilisateur_pk PRIMARY KEY (id_utilisateur)
);

CREATE EXTENSION pgcrypto; --utilisation du module de cryptographie de PostgresSQL pour hasher les mots de passe

ALTER SEQUENCE public.utilisateur_id_seq OWNED BY public.utilisateur.id_utilisateur;

CREATE SEQUENCE public.topo_id_topo_seq_1;

CREATE TABLE public.topo (
                id_topo INTEGER NOT NULL DEFAULT nextval('public.topo_id_topo_seq_1'),
                nom VARCHAR(60) NOT NULL,
                id_utilisateur INTEGER NOT NULL,
                nombre_exemplaires INTEGER,
                description TEXT,
                longitude VARCHAR(30) NOT NULL,
                latitude VARCHAR(30) NOT NULL,
                image VARCHAR(60),
                CONSTRAINT topo_pk PRIMARY KEY (id_topo)
);


ALTER SEQUENCE public.topo_id_topo_seq_1 OWNED BY public.topo.id_topo;

CREATE SEQUENCE public.commentaire_topo_id_commentaire_topo_seq;

CREATE TABLE public.commentaire_topo (
                id_commentaire_topo INTEGER NOT NULL DEFAULT nextval('public.commentaire_topo_id_commentaire_topo_seq'),
                id_topo INTEGER NOT NULL,
                id_utilisateur INTEGER,
                date TIMESTAMP NOT NULL,
                commentaire TEXT NOT NULL,
                CONSTRAINT commentaire_topo_pk PRIMARY KEY (id_commentaire_topo)
);


ALTER SEQUENCE public.commentaire_topo_id_commentaire_topo_seq OWNED BY public.commentaire_topo.id_commentaire_topo;

CREATE SEQUENCE public.site_id_site_seq;

CREATE TABLE public.site (
                id_site INTEGER NOT NULL DEFAULT nextval('public.site_id_site_seq'),
                nom VARCHAR(60),
                description TEXT,
                id_topo INTEGER NOT NULL,
                image VARCHAR(60),
                CONSTRAINT site_pk PRIMARY KEY (id_site)
);


ALTER SEQUENCE public.site_id_site_seq OWNED BY public.site.id_site;

CREATE SEQUENCE public.commentaire_site_id_commentaire_site_seq;

CREATE TABLE public.commentaire_site (
                id_commentaire_site INTEGER NOT NULL DEFAULT nextval('public.commentaire_site_id_commentaire_site_seq'),
                id_site INTEGER NOT NULL,
                id_utilisateur INTEGER,
                date TIMESTAMP NOT NULL,
                commentaire TEXT NOT NULL,
                CONSTRAINT commentaire_site_pk PRIMARY KEY (id_commentaire_site)
);


ALTER SEQUENCE public.commentaire_site_id_commentaire_site_seq OWNED BY public.commentaire_site.id_commentaire_site;

CREATE SEQUENCE public.secteur_id_secteur_seq;

CREATE TABLE public.secteur (
                id_secteur INTEGER NOT NULL DEFAULT nextval('public.secteur_id_secteur_seq'),
                nom VARCHAR(60),
                description TEXT,
                id_site INTEGER NOT NULL,
                image VARCHAR(60),
                CONSTRAINT secteur_pk PRIMARY KEY (id_secteur)
);


ALTER SEQUENCE public.secteur_id_secteur_seq OWNED BY public.secteur.id_secteur;

CREATE SEQUENCE public.commentaire_secteur_id_commentaire_secteur_seq;

CREATE TABLE public.commentaire_secteur (
                id_commentaire_secteur INTEGER NOT NULL DEFAULT nextval('public.commentaire_secteur_id_commentaire_secteur_seq'),
                id_secteur INTEGER NOT NULL,
                id_utilisateur INTEGER,
                date TIMESTAMP NOT NULL,
                commentaire TEXT NOT NULL,
                CONSTRAINT commentaire_secteur_pk PRIMARY KEY (id_commentaire_secteur)
);


ALTER SEQUENCE public.commentaire_secteur_id_commentaire_secteur_seq OWNED BY public.commentaire_secteur.id_commentaire_secteur;

CREATE SEQUENCE public.voie_id_voie_seq;

CREATE TABLE public.voie (
                id_voie INTEGER NOT NULL DEFAULT nextval('public.voie_id_voie_seq'),
                nom VARCHAR(60),
                cotation VARCHAR(10) NOT NULL,
                hauteur INTEGER NOT NULL,
                nombre_longueur INTEGER NOT NULL,
                nombre_point INTEGER NOT NULL,
                id_secteur INTEGER NOT NULL,
                description TEXT,
                CONSTRAINT voie_pk PRIMARY KEY (id_voie)
);


ALTER SEQUENCE public.voie_id_voie_seq OWNED BY public.voie.id_voie;

CREATE SEQUENCE public.commentaire_voie_id_commentaire_voie_seq;

CREATE TABLE public.commentaire_voie (
                id_commentaire_voie INTEGER NOT NULL DEFAULT nextval('public.commentaire_voie_id_commentaire_voie_seq'),
                id_voie INTEGER NOT NULL,
                id_utilisateur INTEGER,
                date TIMESTAMP NOT NULL,
                commentaire TEXT NOT NULL,
                CONSTRAINT commentaire_voie_pk PRIMARY KEY (id_commentaire_voie)
);


ALTER SEQUENCE public.commentaire_voie_id_commentaire_voie_seq OWNED BY public.commentaire_voie.id_commentaire_voie;

CREATE SEQUENCE public.topo_emprunt_id_topo_emprunt_seq;

CREATE TABLE public.topo_emprunt (
                id_topo_emprunt INTEGER NOT NULL DEFAULT nextval('public.topo_emprunt_id_topo_emprunt_seq'),
                date_retrait DATE NOT NULL,
                id_utilisateur INTEGER NOT NULL,
                id_topo INTEGER NOT NULL,
                CONSTRAINT topo_emprunt_pk PRIMARY KEY (id_topo_emprunt)
);


ALTER SEQUENCE public.topo_emprunt_id_topo_emprunt_seq OWNED BY public.topo_emprunt.id_topo_emprunt;

CREATE SEQUENCE public.coordonnee_utilisateur_id_coordonnee_seq;

CREATE TABLE public.coordonnee_utilisateur (
                id_coordonnee INTEGER NOT NULL DEFAULT nextval('public.coordonnee_utilisateur_id_coordonnee_seq'),
                email VARCHAR(30) NOT NULL,
                adresse_postale VARCHAR(60) NOT NULL,
                id_utilisateur INTEGER NOT NULL,
                CONSTRAINT coordonnee_utilisateur_pk PRIMARY KEY (id_coordonnee)
);


ALTER SEQUENCE public.coordonnee_utilisateur_id_coordonnee_seq OWNED BY public.coordonnee_utilisateur.id_coordonnee;

ALTER TABLE public.utilisateur ADD CONSTRAINT role_utilisateur_utilisateur_fk
FOREIGN KEY (id_role)
REFERENCES public.role_utilisateur (id_role)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.coordonnee_utilisateur ADD CONSTRAINT utilisateur_coordonnee_utilisateur_fk
FOREIGN KEY (id_utilisateur)
REFERENCES public.utilisateur (id_utilisateur)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo_emprunt ADD CONSTRAINT utilisateur_topo_emprunt_fk
FOREIGN KEY (id_utilisateur)
REFERENCES public.utilisateur (id_utilisateur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo ADD CONSTRAINT utilisateur_topo_fk
FOREIGN KEY (id_utilisateur)
REFERENCES public.utilisateur (id_utilisateur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire_topo ADD CONSTRAINT utilisateur_commentaire_topo_fk
FOREIGN KEY (id_utilisateur)
REFERENCES public.utilisateur (id_utilisateur)
ON DELETE SET NULL
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire_site ADD CONSTRAINT utilisateur_commentaire_site_fk
FOREIGN KEY (id_utilisateur)
REFERENCES public.utilisateur (id_utilisateur)
ON DELETE SET NULL
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire_secteur ADD CONSTRAINT utilisateur_commentaire_secteur_fk
FOREIGN KEY (id_utilisateur)
REFERENCES public.utilisateur (id_utilisateur)
ON DELETE SET NULL
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire_voie ADD CONSTRAINT utilisateur_commentaire_voie_fk
FOREIGN KEY (id_utilisateur)
REFERENCES public.utilisateur (id_utilisateur)
ON DELETE SET NULL
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo_emprunt ADD CONSTRAINT topo_topo_emprunt_fk
FOREIGN KEY (id_topo)
REFERENCES public.topo (id_topo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.site ADD CONSTRAINT topo_site_fk
FOREIGN KEY (id_topo)
REFERENCES public.topo (id_topo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire_topo ADD CONSTRAINT topo_commentaire_topo_fk
FOREIGN KEY (id_topo)
REFERENCES public.topo (id_topo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.secteur ADD CONSTRAINT site_secteur_fk
FOREIGN KEY (id_site)
REFERENCES public.site (id_site)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire_site ADD CONSTRAINT site_commentaire_site_fk
FOREIGN KEY (id_site)
REFERENCES public.site (id_site)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.voie ADD CONSTRAINT secteur_voie_fk
FOREIGN KEY (id_secteur)
REFERENCES public.secteur (id_secteur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire_secteur ADD CONSTRAINT secteur_commentaire_secteur_fk
FOREIGN KEY (id_secteur)
REFERENCES public.secteur (id_secteur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire_voie ADD CONSTRAINT voie_commentaire_voie_fk
FOREIGN KEY (id_voie)
REFERENCES public.voie (id_voie)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
