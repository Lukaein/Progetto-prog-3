--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: camerieri; Type: TABLE; Schema: public; Owner: admin_pizzeria
--

CREATE TABLE public.camerieri (
    codice_cameriere integer NOT NULL,
    nome character varying(50) NOT NULL,
    cognome character varying(50) NOT NULL
);


ALTER TABLE public.camerieri OWNER TO admin_pizzeria;

--
-- Name: casse; Type: TABLE; Schema: public; Owner: admin_pizzeria
--

CREATE TABLE public.casse (
    codice_cassiere integer NOT NULL
);


ALTER TABLE public.casse OWNER TO admin_pizzeria;

--
-- Name: ordini_seq; Type: SEQUENCE; Schema: public; Owner: admin_pizzeria
--

CREATE SEQUENCE public.ordini_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1212314214
    CACHE 1;


ALTER TABLE public.ordini_seq OWNER TO admin_pizzeria;

--
-- Name: ordini; Type: TABLE; Schema: public; Owner: admin_pizzeria
--

CREATE TABLE public.ordini (
    codice_ordine smallint DEFAULT nextval('public.ordini_seq'::regclass) NOT NULL,
    codice_cameriere integer NOT NULL,
    codice_cassiere integer NOT NULL,
    totale double precision,
    data date
);


ALTER TABLE public.ordini OWNER TO admin_pizzeria;

--
-- Name: prodotti; Type: TABLE; Schema: public; Owner: admin_pizzeria
--

CREATE TABLE public.prodotti (
    codice_prodotto integer NOT NULL,
    nome character varying(50) NOT NULL,
    prezzo double precision NOT NULL
);


ALTER TABLE public.prodotti OWNER TO admin_pizzeria;

--
-- Name: prodotto_ordine; Type: TABLE; Schema: public; Owner: admin_pizzeria
--

CREATE TABLE public.prodotto_ordine (
    codice_prodotto integer NOT NULL,
    codice_ordine integer NOT NULL
);


ALTER TABLE public.prodotto_ordine OWNER TO admin_pizzeria;

--
-- Name: tavoli; Type: TABLE; Schema: public; Owner: admin_pizzeria
--

CREATE TABLE public.tavoli (
    numero_tavolo integer NOT NULL,
    capienza integer NOT NULL,
    disponibilita_tavolo boolean NOT NULL,
    codice_cameriere integer NOT NULL
);


ALTER TABLE public.tavoli OWNER TO admin_pizzeria;

--
-- Data for Name: camerieri; Type: TABLE DATA; Schema: public; Owner: admin_pizzeria
--

COPY public.camerieri (codice_cameriere, nome, cognome) FROM stdin;
1	luca francesco	russo
2	alessio	cinque
3	simone	caruso
\.


--
-- Data for Name: casse; Type: TABLE DATA; Schema: public; Owner: admin_pizzeria
--

COPY public.casse (codice_cassiere) FROM stdin;
1
2
\.


--
-- Data for Name: ordini; Type: TABLE DATA; Schema: public; Owner: admin_pizzeria
--

COPY public.ordini (codice_ordine, codice_cameriere, codice_cassiere, totale, data) FROM stdin;
12	1	2	5	2021-06-18
13	1	1	9	2021-06-19
14	1	1	6	2021-06-19
15	1	1	17	2021-06-19
16	1	1	17	2021-06-19
17	1	1	4	2021-06-19
18	1	1	4	2021-06-19
19	1	1	4	2021-06-19
20	1	1	4	2021-06-19
21	1	1	4	2021-06-19
22	1	1	4	2021-06-19
23	1	1	14	2021-06-19
24	1	1	14	2021-06-19
25	1	1	11	2021-06-19
26	1	1	11	2021-06-19
28	1	1	13	2021-06-19
29	1	1	10	2021-06-19
30	1	1	10	2021-06-19
31	1	1	19	2021-06-19
32	1	1	19	2021-06-19
33	1	1	11	2021-06-19
34	1	1	11	2021-06-19
35	1	1	14	2021-06-19
36	1	1	14	2021-06-19
37	1	1	10	2021-06-19
38	1	1	10	2021-06-19
39	1	1	13	2021-06-19
40	1	1	13	2021-06-19
27	1	1	19	2021-06-19
41	1	1	23	2021-06-19
42	1	1	23	2021-06-19
\.


--
-- Data for Name: prodotti; Type: TABLE DATA; Schema: public; Owner: admin_pizzeria
--

COPY public.prodotti (codice_prodotto, nome, prezzo) FROM stdin;
1	margherita	4
2	marinara	3
3	wurstel e patatine	6
4	capricciosa	5
5	crocchŠ	6
6	filetto	5
7	ripieno al forno	6
8	pizza fritta	6
9	contadina	6
10	salsicce e friarielli	6
20	acqua naturale	2
21	acqua frizzante	2
22	coca-cola lattina	3
23	birra peroni	4
24	birra heinekein	4
25	birra ichnusa	5
26	fanta lattina	3
27	sprite lattina	3
30	patatine fritte	4
31	crocchŠ	1
32	frittatine	2
33	arancino	2
\.


--
-- Data for Name: prodotto_ordine; Type: TABLE DATA; Schema: public; Owner: admin_pizzeria
--

COPY public.prodotto_ordine (codice_prodotto, codice_ordine) FROM stdin;
1	17
1	23
1	25
1	29
1	31
1	25
1	23
\.


--
-- Data for Name: tavoli; Type: TABLE DATA; Schema: public; Owner: admin_pizzeria
--

COPY public.tavoli (numero_tavolo, capienza, disponibilita_tavolo, codice_cameriere) FROM stdin;
7	4	t	3
4	6	f	2
8	3	f	3
9	2	f	3
1	3	f	1
6	2	f	2
2	2	f	1
3	4	f	1
5	3	t	2
\.


--
-- Name: ordini_seq; Type: SEQUENCE SET; Schema: public; Owner: admin_pizzeria
--

SELECT pg_catalog.setval('public.ordini_seq', 42, true);


--
-- Name: camerieri camerieri_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_pizzeria
--

ALTER TABLE ONLY public.camerieri
    ADD CONSTRAINT camerieri_pkey PRIMARY KEY (codice_cameriere);


--
-- Name: casse casse_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_pizzeria
--

ALTER TABLE ONLY public.casse
    ADD CONSTRAINT casse_pkey PRIMARY KEY (codice_cassiere);


--
-- Name: ordini ordini_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_pizzeria
--

ALTER TABLE ONLY public.ordini
    ADD CONSTRAINT ordini_pkey PRIMARY KEY (codice_ordine);


--
-- Name: prodotti prodotti_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_pizzeria
--

ALTER TABLE ONLY public.prodotti
    ADD CONSTRAINT prodotti_pkey PRIMARY KEY (codice_prodotto);


--
-- Name: tavoli tavoli_pkey; Type: CONSTRAINT; Schema: public; Owner: admin_pizzeria
--

ALTER TABLE ONLY public.tavoli
    ADD CONSTRAINT tavoli_pkey PRIMARY KEY (numero_tavolo);


--
-- Name: tavoli fk_camerieri_tavoli; Type: FK CONSTRAINT; Schema: public; Owner: admin_pizzeria
--

ALTER TABLE ONLY public.tavoli
    ADD CONSTRAINT fk_camerieri_tavoli FOREIGN KEY (codice_cameriere) REFERENCES public.camerieri(codice_cameriere);


--
-- Name: prodotto_ordine fk_ordine_prodotto; Type: FK CONSTRAINT; Schema: public; Owner: admin_pizzeria
--

ALTER TABLE ONLY public.prodotto_ordine
    ADD CONSTRAINT fk_ordine_prodotto FOREIGN KEY (codice_ordine) REFERENCES public.ordini(codice_ordine);


--
-- Name: ordini fk_ordini_camerieri; Type: FK CONSTRAINT; Schema: public; Owner: admin_pizzeria
--

ALTER TABLE ONLY public.ordini
    ADD CONSTRAINT fk_ordini_camerieri FOREIGN KEY (codice_cameriere) REFERENCES public.camerieri(codice_cameriere);


--
-- Name: ordini fk_ordini_casse; Type: FK CONSTRAINT; Schema: public; Owner: admin_pizzeria
--

ALTER TABLE ONLY public.ordini
    ADD CONSTRAINT fk_ordini_casse FOREIGN KEY (codice_cassiere) REFERENCES public.casse(codice_cassiere);


--
-- Name: prodotto_ordine fk_prodotto_ordine; Type: FK CONSTRAINT; Schema: public; Owner: admin_pizzeria
--

ALTER TABLE ONLY public.prodotto_ordine
    ADD CONSTRAINT fk_prodotto_ordine FOREIGN KEY (codice_prodotto) REFERENCES public.prodotti(codice_prodotto);


--
-- PostgreSQL database dump complete
--

