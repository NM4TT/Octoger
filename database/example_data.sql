--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2 (Ubuntu 15.2-1.pgdg22.04+1)
-- Dumped by pg_dump version 15.2 (Ubuntu 15.2-1.pgdg22.04+1)

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

--
-- Data for Name: type; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public.type (id, identifier, description) FROM stdin;
3	USR00	User Admin
4	USR01	User Regular
1	TEST	ABCDEFG111
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public."user" (id, personal_identifier, name, lastname, type) FROM stdin;
35	123456	Manuel	Matute	USR00
1	12345	John	Doe	USR01
36	12345ABC	Pepe	Admin	USR00
\.


--
-- Data for Name: credential; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public.credential (id, user_id, username, password) FROM stdin;
17	35	nm4tt	6zgiIYwu3te4MlHkBbgnaw==
1	1	johnd	PiWtPbQfDq5lbP7SAA/g7g==
18	36	pepeP	PiWtPbQfDq5lbP7SAA/g7g==
\.


--
-- Data for Name: product_collection; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public.product_collection (id, user_id, provider, cost, description, product_quantity) FROM stdin;
3	35	COCHES S.A	500	Puertas de madera	30
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public.product (id, collection_id, price, benefit, is_available) FROM stdin;
47	3	14	30	t
\.


--
-- Data for Name: product_collection_log; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public.product_collection_log (id, user_id, provider, cost, description, product_quantity, action, log_time) FROM stdin;
\.


--
-- Data for Name: product_log; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public.product_log (id, collection_id, price, benefit, is_available, action, log_time) FROM stdin;
\.


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public.transaction (id, type, value, date) FROM stdin;
1	TEST	50.00	2023-03-28 11:28:53.679937
3	TEST	9999	2001-01-01 11:11:11
5	TEST	1000	2023-03-11 14:30:00
6	TEST	250	2023-03-12 18:00:00
7	TEST	750	2023-03-13 09:15:00
8	TEST	50	2023-03-14 21:45:00
9	TEST	175	2023-03-15 11:00:00
10	TEST	340	2003-03-03 13:13:13
11	TEST	350	2003-03-03 13:13:13
\.


--
-- Data for Name: product_operation; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public.product_operation (id, type, collection_id, product_amount, date, user_id, transaction_id) FROM stdin;
44	TEST	3	999	2021-03-10 11:48:00	1	1
46	TEST	3	50	2023-03-11 15:25:00	1	1
47	TEST	3	50	2023-03-12 08:00:00	1	1
48	TEST	3	50	2023-03-13 23:45:00	1	1
49	TEST	3	50	2023-03-14 14:30:00	1	1
50	TEST	3	50	2023-03-15 11:20:00	1	1
51	TEST	3	20	2003-03-03 13:13:13	36	10
52	TEST	3	20	2003-03-03 13:13:13	36	11
\.


--
-- Data for Name: product_operation_log; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public.product_operation_log (id, type, collection_id, product_amount, date, user_id, transaction_id, action, log_time) FROM stdin;
\.


--
-- Data for Name: sell; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public.sell (id, date, collection_id, user_id, product_operation_id) FROM stdin;
4	2021-03-10 11:48:00	3	1	44
6	2023-03-11 13:48:00	3	1	44
7	2023-03-12 09:48:00	3	1	44
8	2023-03-13 16:48:00	3	1	44
9	2023-03-14 07:48:00	3	1	44
10	2023-03-15 18:48:00	3	1	44
11	2003-03-03 13:13:13	3	36	52
\.


--
-- Data for Name: sell_log; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public.sell_log (id, date, collection_id, user_id, product_operation_id, action, log_time) FROM stdin;
\.


--
-- Data for Name: transaction_log; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public.transaction_log (id, type, value, date, action, log_time) FROM stdin;
\.


--
-- Data for Name: type_log; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public.type_log (id, identifier, description, action, log_time) FROM stdin;
\.


--
-- Data for Name: user_log; Type: TABLE DATA; Schema: public; Owner: nmatute
--

COPY public.user_log (id, personal_identifier, name, lastname, action, log_time) FROM stdin;
\.


--
-- Name: credential_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.credential_id_seq', 18, true);


--
-- Name: product_collection_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.product_collection_id_seq', 3, true);


--
-- Name: product_collection_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.product_collection_log_id_seq', 1, false);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.product_id_seq', 48, true);


--
-- Name: product_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.product_log_id_seq', 1, false);


--
-- Name: product_operation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.product_operation_id_seq', 52, true);


--
-- Name: product_operation_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.product_operation_log_id_seq', 1, false);


--
-- Name: sell_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.sell_id_seq', 11, true);


--
-- Name: sell_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.sell_log_id_seq', 1, false);


--
-- Name: transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.transaction_id_seq', 11, true);


--
-- Name: transaction_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.transaction_log_id_seq', 1, false);


--
-- Name: type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.type_id_seq', 4, true);


--
-- Name: type_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.type_log_id_seq', 1, false);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.user_id_seq', 36, true);


--
-- Name: user_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nmatute
--

SELECT pg_catalog.setval('public.user_log_id_seq', 1, false);


--
-- PostgreSQL database dump complete
--

