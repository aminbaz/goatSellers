DROP TABLE IF EXISTS public."Sale";
DROP TABLE IF EXISTS public."Offer";
DROP TABLE IF EXISTS public."UpToSale";
DROP TABLE IF EXISTS public."User";
DROP TABLE IF EXISTS public."Player";
DROP TABLE IF EXISTS public."Notification";
DROP TABLE IF EXISTS public."Club";
DROP TABLE IF EXISTS public."Authority";
DROP TABLE IF EXISTS public."Role";

CREATE TABLE public."Role"
(
    id_role SERIAL,
    rolename character varying(20),
    CONSTRAINT "Role_pkey" PRIMARY KEY (id_role)
);

CREATE TABLE public."Authority"
(
    id_authority SERIAL,
    name character varying(50),
    role integer,
    CONSTRAINT "Authority_pkey" PRIMARY KEY (id_authority),
    CONSTRAINT fk_authority_role FOREIGN KEY (role)
        REFERENCES public."Role" (id_role) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public."Club"
(
    id_club SERIAL,
    name character varying(50),
    logo character varying(200),
    role integer,
    blocked boolean,
    CONSTRAINT "Club_pkey" PRIMARY KEY (id_club),
    CONSTRAINT fk_club_role FOREIGN KEY (role)
        REFERENCES public."Role" (id_role) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public."Notification"
(
    id_notification SERIAL,
    text character varying(200),
    seen boolean,
    club integer,
    CONSTRAINT "Notification_pkey" PRIMARY KEY (id_notification),
    CONSTRAINT fk_notification_club FOREIGN KEY (club)
        REFERENCES public."Club" (id_club) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public."Player"
(
    id_player SERIAL,
    firstname character varying(50),
    lastname character varying(50),
    birthdate date,
    "position" character varying(20),
    contract date,
    club integer,
    CONSTRAINT "Player_pkey" PRIMARY KEY (id_player),
    CONSTRAINT fk_player_club FOREIGN KEY (club)
        REFERENCES public."Club" (id_club) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public."User"
(
    id_user SERIAL,
    mail character varying(50),
    password character varying(50),
    role integer,
    CONSTRAINT "User_pkey" PRIMARY KEY (id_user),
    CONSTRAINT fk_user_role FOREIGN KEY (role)
        REFERENCES public."Role" (id_role) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public."UpToSale"
(
    id_uptosale SERIAL,
    minprice integer,
    date_uptosale date,
    club integer,
    player integer,
    CONSTRAINT "UpToSale_pkey" PRIMARY KEY (id_uptosale),
    CONSTRAINT fk_uptosale_club FOREIGN KEY (club)
        REFERENCES public."Club" (id_club) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_uptosale_player FOREIGN KEY (player)
        REFERENCES public."Player" (id_player) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public."Offer"
(
    id_offer SERIAL,
    amount integer,
    date_offer date,
    status character varying(20),
    id_uptosale integer,
    club integer,
    CONSTRAINT "Offer_pkey" PRIMARY KEY (id_offer),
    CONSTRAINT fk_offer_club FOREIGN KEY (club)
        REFERENCES public."Club" (id_club) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_offer_uptosale FOREIGN KEY (id_uptosale)
        REFERENCES public."UpToSale" (id_uptosale) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public."Sale"
(
    id_sale SERIAL,
    amount_sale integer,
    sale_date date,
    seller integer,
    buyer integer,
    player integer,
    CONSTRAINT "Sale_pkey" PRIMARY KEY (id_sale),
    CONSTRAINT fk_sale_buyer FOREIGN KEY (buyer)
        REFERENCES public."Club" (id_club) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_sale_player FOREIGN KEY (player)
        REFERENCES public."Player" (id_player) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_sale_seller FOREIGN KEY (seller)
        REFERENCES public."Club" (id_club) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);