--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.12
-- Dumped by pg_dump version 9.5.12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: cidade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cidade (
    id bigint NOT NULL,
    cod_extra character varying(255),
    estado character varying(2),
    nome character varying(255)
);


ALTER TABLE public.cidade OWNER TO postgres;

--
-- Name: cidade_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cidade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cidade_id_seq OWNER TO postgres;

--
-- Name: cidade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cidade_id_seq OWNED BY public.cidade.id;


--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id bigint NOT NULL,
    aniversario character varying(255),
    cpf character varying(255),
    rg character varying(255),
    cnpj character varying(255),
    ie character varying(255),
    im character varying(255),
    razao character varying(255),
    bairro character varying(255),
    celular character varying(255),
    cep character varying(255),
    complemento character varying(255),
    data_criacao timestamp without time zone,
    email character varying(255),
    endereco character varying(255),
    nome character varying(255),
    telefone character varying(255),
    tipo character varying(255),
    cidade_id bigint,
    criado_por_id bigint,
    estado character varying(255),
    numero character varying(255)
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_seq OWNER TO postgres;

--
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;


--
-- Name: complemento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.complemento (
    id bigint NOT NULL,
    nome character varying(255),
    valor numeric(19,2)
);


ALTER TABLE public.complemento OWNER TO postgres;

--
-- Name: complemento_categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.complemento_categoria (
    complemento_id bigint NOT NULL,
    categoria_id bigint NOT NULL
);


ALTER TABLE public.complemento_categoria OWNER TO postgres;

--
-- Name: complemento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.complemento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.complemento_id_seq OWNER TO postgres;

--
-- Name: complemento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.complemento_id_seq OWNED BY public.complemento.id;


--
-- Name: empresa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empresa (
    id bigint NOT NULL,
    cnpj character varying(255),
    ie character varying(255),
    im character varying(255),
    razao character varying(255),
    bairro character varying(255),
    celular character varying(255),
    cep character varying(255),
    complemento character varying(255),
    data_criacao timestamp without time zone,
    email character varying(255),
    endereco character varying(255),
    estado character varying(255),
    nome character varying(255),
    numero character varying(255),
    telefone character varying(255),
    cidade_id bigint,
    criado_por_id bigint
);


ALTER TABLE public.empresa OWNER TO postgres;

--
-- Name: empresa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.empresa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.empresa_id_seq OWNER TO postgres;

--
-- Name: empresa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.empresa_id_seq OWNED BY public.empresa.id;


--
-- Name: observacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.observacao (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.observacao OWNER TO postgres;

--
-- Name: observacao_categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.observacao_categoria (
    observacao_id bigint NOT NULL,
    categoria_id bigint NOT NULL
);


ALTER TABLE public.observacao_categoria OWNER TO postgres;

--
-- Name: observacao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.observacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.observacao_id_seq OWNER TO postgres;

--
-- Name: observacao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.observacao_id_seq OWNED BY public.observacao.id;


--
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produto (
    produto_type character varying(31) NOT NULL,
    id bigint NOT NULL,
    data_criacao timestamp without time zone,
    descricao character varying(255),
    foto character varying(255),
    nome character varying(255),
    personalizado character varying(255),
    unidade character varying(255),
    custofatia numeric(19,2),
    margemfatia double precision NOT NULL,
    vendafatia numeric(19,2),
    fatiahabilitada boolean,
    custogrande numeric(19,2),
    margemgrande double precision NOT NULL,
    vendagrande numeric(19,2),
    grandehabilitada boolean,
    customedia numeric(19,2),
    margemmedia double precision NOT NULL,
    vendamedia numeric(19,2),
    mediahabilitada boolean,
    custopequeno numeric(19,2),
    margempequeno double precision NOT NULL,
    vendapequeno numeric(19,2),
    pequenahabilitada boolean,
    categoria_id bigint,
    criado_por_id bigint,
    receita character varying(255),
    custo numeric(19,2),
    margem numeric(19,2),
    venda numeric(19,2)
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- Name: produto_categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produto_categoria (
    id integer NOT NULL,
    nome character varying
);


ALTER TABLE public.produto_categoria OWNER TO postgres;

--
-- Name: produto_categoria_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produto_categoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produto_categoria_id_seq OWNER TO postgres;

--
-- Name: produto_categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produto_categoria_id_seq OWNED BY public.produto_categoria.id;


--
-- Name: produto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produto_id_seq OWNER TO postgres;

--
-- Name: produto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produto_id_seq OWNED BY public.produto.id;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id bigint NOT NULL,
    ativo integer NOT NULL,
    login character varying(255),
    nivel character varying(255),
    bairro character varying(255),
    celular character varying(255),
    cep character varying(255),
    complemento character varying(255),
    data_criacao timestamp without time zone,
    email character varying(255),
    endereco character varying(255),
    nome character varying(255),
    telefone character varying(255),
    senha character varying(255),
    ultimo_acesso timestamp without time zone,
    cidade_id bigint,
    criado_por_id bigint,
    estado character varying(255),
    numero character varying(255),
    aniversario character varying(255),
    cpf character varying(255),
    rg character varying(255)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO postgres;

--
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cidade ALTER COLUMN id SET DEFAULT nextval('public.cidade_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complemento ALTER COLUMN id SET DEFAULT nextval('public.complemento_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresa ALTER COLUMN id SET DEFAULT nextval('public.empresa_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.observacao ALTER COLUMN id SET DEFAULT nextval('public.observacao_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto ALTER COLUMN id SET DEFAULT nextval('public.produto_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto_categoria ALTER COLUMN id SET DEFAULT nextval('public.produto_categoria_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- Data for Name: cidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cidade (id, cod_extra, estado, nome) FROM stdin;
1	1100015	RO	Alta Floresta D'Oeste
2	1100379	RO	Alto Alegre dos Parecis
3	1100403	RO	Alto Paraíso
4	1100346	RO	Alvorada D'Oeste
5	1100023	RO	Ariquemes
6	1100452	RO	Buritis
7	1100031	RO	Cabixi
8	1100601	RO	Cacaulândia
9	1100049	RO	Cacoal
10	1100700	RO	Campo Novo de Rondônia
11	1100809	RO	Candeias do Jamari
12	1100908	RO	Castanheiras
13	1100056	RO	Cerejeiras
14	1100924	RO	Chupinguaia
15	1100064	RO	Colorado do Oeste
16	1100072	RO	Corumbiara
17	1100080	RO	Costa Marques
18	1100940	RO	Cujubim
19	1100098	RO	Espigão D'Oeste
20	1101005	RO	Governador Jorge Teixeira
21	1100106	RO	Guajará-Mirim
22	1101104	RO	Itapuã do Oeste
23	1100114	RO	Jaru
24	1100122	RO	Ji-Paraná
25	1100130	RO	Machadinho D'Oeste
26	1101203	RO	Ministro Andreazza
27	1101302	RO	Mirante da Serra
28	1101401	RO	Monte Negro
29	1100148	RO	Nova Brasilândia D'Oeste
30	1100338	RO	Nova Mamoré
31	1101435	RO	Nova União
32	1100502	RO	Novo Horizonte do Oeste
33	1100155	RO	Ouro Preto do Oeste
34	1101450	RO	Parecis
35	1100189	RO	Pimenta Bueno
36	1101468	RO	Pimenteiras do Oeste
37	1100205	RO	Porto Velho
38	1100254	RO	Presidente Médici
39	1101476	RO	Primavera de Rondônia
40	1100262	RO	Rio Crespo
41	1100288	RO	Rolim de Moura
42	1100296	RO	Santa Luzia D'Oeste
43	1101484	RO	São Felipe D'Oeste
44	1101492	RO	São Francisco do Guaporé
45	1100320	RO	São Miguel do Guaporé
46	1101500	RO	Seringueiras
47	1101559	RO	Teixeirópolis
48	1101609	RO	Theobroma
49	1101708	RO	Urupá
50	1101757	RO	Vale do Anari
51	1101807	RO	Vale do Paraíso
52	1100304	RO	Vilhena
53	1200013	AC	Acrelândia
54	1200054	AC	Assis Brasil
55	1200104	AC	Brasiléia
56	1200138	AC	Bujari
57	1200179	AC	Capixaba
58	1200203	AC	Cruzeiro do Sul
59	1200252	AC	Epitaciolândia
60	1200302	AC	Feijó
61	1200328	AC	Jordão
62	1200336	AC	Mâncio Lima
63	1200344	AC	Manoel Urbano
64	1200351	AC	Marechal Thaumaturgo
65	1200385	AC	Plácido de Castro
66	1200807	AC	Porto Acre
67	1200393	AC	Porto Walter
68	1200401	AC	Rio Branco
69	1200427	AC	Rodrigues Alves
70	1200435	AC	Santa Rosa do Purus
71	1200500	AC	Sena Madureira
72	1200450	AC	Senador Guiomard
73	1200609	AC	Tarauacá
74	1200708	AC	Xapuri
75	1300029	AM	Alvarães
76	1300060	AM	Amaturá
77	1300086	AM	Anamã
78	1300102	AM	Anori
79	1300144	AM	Apuí
80	1300201	AM	Atalaia do Norte
81	1300300	AM	Autazes
82	1300409	AM	Barcelos
83	1300508	AM	Barreirinha
84	1300607	AM	Benjamin Constant
85	1300631	AM	Beruri
86	1300680	AM	Boa Vista do Ramos
87	1300706	AM	Boca do Acre
88	1300805	AM	Borba
89	1300839	AM	Caapiranga
90	1300904	AM	Canutama
91	1301001	AM	Carauari
92	1301100	AM	Careiro
93	1301159	AM	Careiro da Várzea
94	1301209	AM	Coari
95	1301308	AM	Codajás
96	1301407	AM	Eirunepé
97	1301506	AM	Envira
98	1301605	AM	Fonte Boa
99	1301654	AM	Guajará
100	1301704	AM	Humaitá
101	1301803	AM	Ipixuna
102	1301852	AM	Iranduba
103	1301902	AM	Itacoatiara
104	1301951	AM	Itamarati
105	1302009	AM	Itapiranga
106	1302108	AM	Japurá
107	1302207	AM	Juruá
108	1302306	AM	Jutaí
109	1302405	AM	Lábrea
110	1302504	AM	Manacapuru
111	1302553	AM	Manaquiri
112	1302603	AM	Manaus
113	1302702	AM	Manicoré
114	1302801	AM	Maraã
115	1302900	AM	Maués
116	1303007	AM	Nhamundá
117	1303106	AM	Nova Olinda do Norte
118	1303205	AM	Novo Airão
119	1303304	AM	Novo Aripuanã
120	1303403	AM	Parintins
121	1303502	AM	Pauini
122	1303536	AM	Presidente Figueiredo
123	1303569	AM	Rio Preto da Eva
124	1303601	AM	Santa Isabel do Rio Negro
125	1303700	AM	Santo Antônio do Içá
126	1303809	AM	São Gabriel da Cachoeira
127	1303908	AM	São Paulo de Olivença
128	1303957	AM	São Sebastião do Uatumã
129	1304005	AM	Silves
130	1304062	AM	Tabatinga
131	1304104	AM	Tapauá
132	1304203	AM	Tefé
133	1304237	AM	Tonantins
134	1304260	AM	Uarini
135	1304302	AM	Urucará
136	1304401	AM	Urucurituba
137	1400050	RR	Alto Alegre
138	1400027	RR	Amajari
139	1400100	RR	Boa Vista
140	1400159	RR	Bonfim
141	1400175	RR	Cantá
142	1400209	RR	Caracaraí
143	1400233	RR	Caroebe
144	1400282	RR	Iracema
145	1400308	RR	Mucajaí
146	1400407	RR	Normandia
147	1400456	RR	Pacaraima
148	1400472	RR	Rorainópolis
149	1400506	RR	São João da Baliza
150	1400605	RR	São Luiz
151	1400704	RR	Uiramutã
152	1500107	PA	Abaetetuba
153	1500131	PA	Abel Figueiredo
154	1500206	PA	Acará
155	1500305	PA	Afuá
156	1500347	PA	Água Azul do Norte
157	1500404	PA	Alenquer
158	1500503	PA	Almeirim
159	1500602	PA	Altamira
160	1500701	PA	Anajás
161	1500800	PA	Ananindeua
162	1500859	PA	Anapu
163	1500909	PA	Augusto Corrêa
164	1500958	PA	Aurora do Pará
165	1501006	PA	Aveiro
166	1501105	PA	Bagre
167	1501204	PA	Baião
168	1501253	PA	Bannach
169	1501303	PA	Barcarena
170	1501402	PA	Belém
171	1501451	PA	Belterra
172	1501501	PA	Benevides
173	1501576	PA	Bom Jesus do Tocantins
174	1501600	PA	Bonito
175	1501709	PA	Bragança
176	1501725	PA	Brasil Novo
177	1501758	PA	Brejo Grande do Araguaia
178	1501782	PA	Breu Branco
179	1501808	PA	Breves
180	1501907	PA	Bujaru
181	1502004	PA	Cachoeira do Arari
182	1501956	PA	Cachoeira do Piriá
183	1502103	PA	Cametá
184	1502152	PA	Canaã dos Carajás
185	1502202	PA	Capanema
186	1502301	PA	Capitão Poço
187	1502400	PA	Castanhal
188	1502509	PA	Chaves
189	1502608	PA	Colares
190	1502707	PA	Conceição do Araguaia
191	1502756	PA	Concórdia do Pará
192	1502764	PA	Cumaru do Norte
193	1502772	PA	Curionópolis
194	1502806	PA	Curralinho
195	1502855	PA	Curuá
196	1502905	PA	Curuçá
197	1502939	PA	Dom Eliseu
198	1502954	PA	Eldorado dos Carajás
199	1503002	PA	Faro
200	1503044	PA	Floresta do Araguaia
201	1503077	PA	Garrafão do Norte
202	1503093	PA	Goianésia do Pará
203	1503101	PA	Gurupá
204	1503200	PA	Igarapé-Açu
205	1503309	PA	Igarapé-Miri
206	1503408	PA	Inhangapi
207	1503457	PA	Ipixuna do Pará
208	1503507	PA	Irituia
209	1503606	PA	Itaituba
210	1503705	PA	Itupiranga
211	1503754	PA	Jacareacanga
212	1503804	PA	Jacundá
213	1503903	PA	Juruti
214	1504000	PA	Limoeiro do Ajuru
215	1504059	PA	Mãe do Rio
216	1504109	PA	Magalhães Barata
217	1504208	PA	Marabá
218	1504307	PA	Maracanã
219	1504406	PA	Marapanim
220	1504422	PA	Marituba
221	1504455	PA	Medicilândia
222	1504505	PA	Melgaço
223	1504604	PA	Mocajuba
224	1504703	PA	Moju
225	1504802	PA	Monte Alegre
226	1504901	PA	Muaná
227	1504950	PA	Nova Esperança do Piriá
228	1504976	PA	Nova Ipixuna
229	1505007	PA	Nova Timboteua
230	1505031	PA	Novo Progresso
231	1505064	PA	Novo Repartimento
232	1505106	PA	Óbidos
233	1505205	PA	Oeiras do Pará
234	1505304	PA	Oriximiná
235	1505403	PA	Ourém
236	1505437	PA	Ourilândia do Norte
237	1505486	PA	Pacajá
238	1505494	PA	Palestina do Pará
239	1505502	PA	Paragominas
240	1505536	PA	Parauapebas
241	1505551	PA	Pau D'Arco
242	1505601	PA	Peixe-Boi
243	1505635	PA	Piçarra
244	1505650	PA	Placas
245	1505700	PA	Ponta de Pedras
246	1505809	PA	Portel
247	1505908	PA	Porto de Moz
248	1506005	PA	Prainha
249	1506104	PA	Primavera
250	1506112	PA	Quatipuru
251	1506138	PA	Redenção
252	1506161	PA	Rio Maria
253	1506187	PA	Rondon do Pará
254	1506195	PA	Rurópolis
255	1506203	PA	Salinópolis
256	1506302	PA	Salvaterra
257	1506351	PA	Santa Bárbara do Pará
258	1506401	PA	Santa Cruz do Arari
259	1506500	PA	Santa Isabel do Pará
260	1506559	PA	Santa Luzia do Pará
261	1506583	PA	Santa Maria das Barreiras
262	1506609	PA	Santa Maria do Pará
263	1506708	PA	Santana do Araguaia
264	1506807	PA	Santarém
265	1506906	PA	Santarém Novo
266	1507003	PA	Santo Antônio do Tauá
267	1507102	PA	São Caetano de Odivelas
268	1507151	PA	São Domingos do Araguaia
269	1507201	PA	São Domingos do Capim
270	1507300	PA	São Félix do Xingu
271	1507409	PA	São Francisco do Pará
272	1507458	PA	São Geraldo do Araguaia
273	1507466	PA	São João da Ponta
274	1507474	PA	São João de Pirabas
275	1507508	PA	São João do Araguaia
276	1507607	PA	São Miguel do Guamá
277	1507706	PA	São Sebastião da Boa Vista
278	1507755	PA	Sapucaia
279	1507805	PA	Senador José Porfírio
280	1507904	PA	Soure
281	1507953	PA	Tailândia
282	1507961	PA	Terra Alta
283	1507979	PA	Terra Santa
284	1508001	PA	Tomé-Açu
285	1508035	PA	Tracuateua
286	1508050	PA	Trairão
287	1508084	PA	Tucumã
288	1508100	PA	Tucuruí
289	1508126	PA	Ulianópolis
290	1508159	PA	Uruará
291	1508209	PA	Vigia
292	1508308	PA	Viseu
293	1508357	PA	Vitória do Xingu
294	1508407	PA	Xinguara
295	1600105	AP	Amapá
296	1600204	AP	Calçoene
297	1600212	AP	Cutias
298	1600238	AP	Ferreira Gomes
299	1600253	AP	Itaubal
300	1600279	AP	Laranjal do Jari
301	1600303	AP	Macapá
302	1600402	AP	Mazagão
303	1600501	AP	Oiapoque
304	1600154	AP	Pedra Branca do Amapari
305	1600535	AP	Porto Grande
306	1600550	AP	Pracuúba
307	1600600	AP	Santana
308	1600055	AP	Serra do Navio
309	1600709	AP	Tartarugalzinho
310	1600808	AP	Vitória do Jari
311	1700251	TO	Abreulândia
312	1700301	TO	Aguiarnópolis
313	1700350	TO	Aliança do Tocantins
314	1700400	TO	Almas
315	1700707	TO	Alvorada
316	1701002	TO	Ananás
317	1701051	TO	Angico
318	1701101	TO	Aparecida do Rio Negro
319	1701309	TO	Aragominas
320	1701903	TO	Araguacema
321	1702000	TO	Araguaçu
322	1702109	TO	Araguaína
323	1702158	TO	Araguanã
324	1702208	TO	Araguatins
325	1702307	TO	Arapoema
326	1702406	TO	Arraias
327	1702554	TO	Augustinópolis
328	1702703	TO	Aurora do Tocantins
329	1702901	TO	Axixá do Tocantins
330	1703008	TO	Babaçulândia
331	1703057	TO	Bandeirantes do Tocantins
332	1703073	TO	Barra do Ouro
333	1703107	TO	Barrolândia
334	1703206	TO	Bernardo Sayão
335	1703305	TO	Bom Jesus do Tocantins
336	1703602	TO	Brasilândia do Tocantins
337	1703701	TO	Brejinho de Nazaré
338	1703800	TO	Buriti do Tocantins
339	1703826	TO	Cachoeirinha
340	1703842	TO	Campos Lindos
341	1703867	TO	Cariri do Tocantins
342	1703883	TO	Carmolândia
343	1703891	TO	Carrasco Bonito
344	1703909	TO	Caseara
345	1704105	TO	Centenário
346	1705102	TO	Chapada da Natividade
347	1704600	TO	Chapada de Areia
348	1705508	TO	Colinas do Tocantins
349	1716703	TO	Colméia
350	1705557	TO	Combinado
351	1705607	TO	Conceição do Tocantins
352	1706001	TO	Couto Magalhães
353	1706100	TO	Cristalândia
354	1706258	TO	Crixás do Tocantins
355	1706506	TO	Darcinópolis
356	1707009	TO	Dianópolis
357	1707108	TO	Divinópolis do Tocantins
358	1707207	TO	Dois Irmãos do Tocantins
359	1707306	TO	Dueré
360	1707405	TO	Esperantina
361	1707553	TO	Fátima
362	1707652	TO	Figueirópolis
363	1707702	TO	Filadélfia
364	1708205	TO	Formoso do Araguaia
365	1708254	TO	Fortaleza do Tabocão
366	1708304	TO	Goianorte
367	1709005	TO	Goiatins
368	1709302	TO	Guaraí
369	1709500	TO	Gurupi
370	1709807	TO	Ipueiras
371	1710508	TO	Itacajá
372	1710706	TO	Itaguatins
373	1710904	TO	Itapiratins
374	1711100	TO	Itaporã do Tocantins
375	1711506	TO	Jaú do Tocantins
376	1711803	TO	Juarina
377	1711902	TO	Lagoa da Confusão
378	1711951	TO	Lagoa do Tocantins
379	1712009	TO	Lajeado
380	1712157	TO	Lavandeira
381	1712405	TO	Lizarda
382	1712454	TO	Luzinópolis
383	1712504	TO	Marianópolis do Tocantins
384	1712702	TO	Mateiros
385	1712801	TO	Maurilândia do Tocantins
386	1713205	TO	Miracema do Tocantins
387	1713304	TO	Miranorte
388	1713601	TO	Monte do Carmo
389	1713700	TO	Monte Santo do Tocantins
390	1713957	TO	Muricilândia
391	1714203	TO	Natividade
392	1714302	TO	Nazaré
393	1714880	TO	Nova Olinda
394	1715002	TO	Nova Rosalândia
395	1715101	TO	Novo Acordo
396	1715150	TO	Novo Alegre
397	1715259	TO	Novo Jardim
398	1715507	TO	Oliveira de Fátima
399	1721000	TO	Palmas
400	1715705	TO	Palmeirante
401	1713809	TO	Palmeiras do Tocantins
402	1715754	TO	Palmeirópolis
403	1716109	TO	Paraíso do Tocantins
404	1716208	TO	Paranã
405	1716307	TO	Pau D'Arco
406	1716505	TO	Pedro Afonso
407	1716604	TO	Peixe
408	1716653	TO	Pequizeiro
409	1717008	TO	Pindorama do Tocantins
410	1717206	TO	Piraquê
411	1717503	TO	Pium
412	1717800	TO	Ponte Alta do Bom Jesus
413	1717909	TO	Ponte Alta do Tocantins
414	1718006	TO	Porto Alegre do Tocantins
415	1718204	TO	Porto Nacional
416	1718303	TO	Praia Norte
417	1718402	TO	Presidente Kennedy
418	1718451	TO	Pugmil
419	1718501	TO	Recursolândia
420	1718550	TO	Riachinho
421	1718659	TO	Rio da Conceição
422	1718709	TO	Rio dos Bois
423	1718758	TO	Rio Sono
424	1718808	TO	Sampaio
425	1718840	TO	Sandolândia
426	1718865	TO	Santa Fé do Araguaia
427	1718881	TO	Santa Maria do Tocantins
428	1718899	TO	Santa Rita do Tocantins
429	1718907	TO	Santa Rosa do Tocantins
430	1719004	TO	Santa Tereza do Tocantins
431	1720002	TO	Santa Terezinha do Tocantins
432	1720101	TO	São Bento do Tocantins
433	1720150	TO	São Félix do Tocantins
434	1720200	TO	São Miguel do Tocantins
435	1720259	TO	São Salvador do Tocantins
436	1720309	TO	São Sebastião do Tocantins
437	1720499	TO	São Valério
438	1720655	TO	Silvanópolis
439	1720804	TO	Sítio Novo do Tocantins
440	1720853	TO	Sucupira
441	1720903	TO	Taguatinga
442	1720937	TO	Taipas do Tocantins
443	1720978	TO	Talismã
444	1721109	TO	Tocantínia
445	1721208	TO	Tocantinópolis
446	1721257	TO	Tupirama
447	1721307	TO	Tupiratins
448	1722081	TO	Wanderlândia
449	1722107	TO	Xambioá
450	2100055	MA	Açailândia
451	2100105	MA	Afonso Cunha
452	2100154	MA	Água Doce do Maranhão
453	2100204	MA	Alcântara
454	2100303	MA	Aldeias Altas
455	2100402	MA	Altamira do Maranhão
456	2100436	MA	Alto Alegre do Maranhão
457	2100477	MA	Alto Alegre do Pindaré
458	2100501	MA	Alto Parnaíba
459	2100550	MA	Amapá do Maranhão
460	2100600	MA	Amarante do Maranhão
461	2100709	MA	Anajatuba
462	2100808	MA	Anapurus
463	2100832	MA	Apicum-Açu
464	2100873	MA	Araguanã
465	2100907	MA	Araioses
466	2100956	MA	Arame
467	2101004	MA	Arari
468	2101103	MA	Axixá
469	2101202	MA	Bacabal
470	2101251	MA	Bacabeira
471	2101301	MA	Bacuri
472	2101350	MA	Bacurituba
473	2101400	MA	Balsas
474	2101509	MA	Barão de Grajaú
475	2101608	MA	Barra do Corda
476	2101707	MA	Barreirinhas
477	2101772	MA	Bela Vista do Maranhão
478	2101731	MA	Belágua
479	2101806	MA	Benedito Leite
480	2101905	MA	Bequimão
481	2101939	MA	Bernardo do Mearim
482	2101970	MA	Boa Vista do Gurupi
483	2102002	MA	Bom Jardim
484	2102036	MA	Bom Jesus das Selvas
485	2102077	MA	Bom Lugar
486	2102101	MA	Brejo
487	2102150	MA	Brejo de Areia
488	2102200	MA	Buriti
489	2102309	MA	Buriti Bravo
490	2102325	MA	Buriticupu
491	2102358	MA	Buritirana
492	2102374	MA	Cachoeira Grande
493	2102408	MA	Cajapió
494	2102507	MA	Cajari
495	2102556	MA	Campestre do Maranhão
496	2102606	MA	Cândido Mendes
497	2102705	MA	Cantanhede
498	2102754	MA	Capinzal do Norte
499	2102804	MA	Carolina
500	2102903	MA	Carutapera
501	2103000	MA	Caxias
502	2103109	MA	Cedral
503	2103125	MA	Central do Maranhão
504	2103158	MA	Centro do Guilherme
505	2103174	MA	Centro Novo do Maranhão
506	2103208	MA	Chapadinha
507	2103257	MA	Cidelândia
508	2103307	MA	Codó
509	2103406	MA	Coelho Neto
510	2103505	MA	Colinas
511	2103554	MA	Conceição do Lago-Açu
512	2103604	MA	Coroatá
513	2103703	MA	Cururupu
514	2103752	MA	Davinópolis
515	2103802	MA	Dom Pedro
516	2103901	MA	Duque Bacelar
517	2104008	MA	Esperantinópolis
518	2104057	MA	Estreito
519	2104073	MA	Feira Nova do Maranhão
520	2104081	MA	Fernando Falcão
521	2104099	MA	Formosa da Serra Negra
522	2104107	MA	Fortaleza dos Nogueiras
523	2104206	MA	Fortuna
524	2104305	MA	Godofredo Viana
525	2104404	MA	Gonçalves Dias
526	2104503	MA	Governador Archer
527	2104552	MA	Governador Edison Lobão
528	2104602	MA	Governador Eugênio Barros
529	2104628	MA	Governador Luiz Rocha
530	2104651	MA	Governador Newton Bello
531	2104677	MA	Governador Nunes Freire
532	2104701	MA	Graça Aranha
533	2104800	MA	Grajaú
534	2104909	MA	Guimarães
535	2105005	MA	Humberto de Campos
536	2105104	MA	Icatu
537	2105153	MA	Igarapé do Meio
538	2105203	MA	Igarapé Grande
539	2105302	MA	Imperatriz
540	2105351	MA	Itaipava do Grajaú
541	2105401	MA	Itapecuru Mirim
542	2105427	MA	Itinga do Maranhão
543	2105450	MA	Jatobá
544	2105476	MA	Jenipapo dos Vieiras
545	2105500	MA	João Lisboa
546	2105609	MA	Joselândia
547	2105658	MA	Junco do Maranhão
548	2105708	MA	Lago da Pedra
549	2105807	MA	Lago do Junco
550	2105948	MA	Lago dos Rodrigues
551	2105906	MA	Lago Verde
552	2105922	MA	Lagoa do Mato
553	2105963	MA	Lagoa Grande do Maranhão
554	2105989	MA	Lajeado Novo
555	2106003	MA	Lima Campos
556	2106102	MA	Loreto
557	2106201	MA	Luís Domingues
558	2106300	MA	Magalhães de Almeida
559	2106326	MA	Maracaçumé
560	2106359	MA	Marajá do Sena
561	2106375	MA	Maranhãozinho
562	2106409	MA	Mata Roma
563	2106508	MA	Matinha
564	2106607	MA	Matões
565	2106631	MA	Matões do Norte
566	2106672	MA	Milagres do Maranhão
567	2106706	MA	Mirador
568	2106755	MA	Miranda do Norte
569	2106805	MA	Mirinzal
570	2106904	MA	Monção
571	2107001	MA	Montes Altos
572	2107100	MA	Morros
573	2107209	MA	Nina Rodrigues
574	2107258	MA	Nova Colinas
575	2107308	MA	Nova Iorque
576	2107357	MA	Nova Olinda do Maranhão
577	2107407	MA	Olho d'Água das Cunhãs
578	2107456	MA	Olinda Nova do Maranhão
579	2107506	MA	Paço do Lumiar
580	2107605	MA	Palmeirândia
581	2107704	MA	Paraibano
582	2107803	MA	Parnarama
583	2107902	MA	Passagem Franca
584	2108009	MA	Pastos Bons
585	2108058	MA	Paulino Neves
586	2108108	MA	Paulo Ramos
587	2108207	MA	Pedreiras
588	2108256	MA	Pedro do Rosário
589	2108306	MA	Penalva
590	2108405	MA	Peri Mirim
591	2108454	MA	Peritoró
592	2108504	MA	Pindaré-Mirim
593	2108603	MA	Pinheiro
594	2108702	MA	Pio XII
595	2108801	MA	Pirapemas
596	2108900	MA	Poção de Pedras
597	2109007	MA	Porto Franco
598	2109056	MA	Porto Rico do Maranhão
599	2109106	MA	Presidente Dutra
600	2109205	MA	Presidente Juscelino
601	2109239	MA	Presidente Médici
602	2109270	MA	Presidente Sarney
603	2109304	MA	Presidente Vargas
604	2109403	MA	Primeira Cruz
605	2109452	MA	Raposa
606	2109502	MA	Riachão
607	2109551	MA	Ribamar Fiquene
608	2109601	MA	Rosário
609	2109700	MA	Sambaíba
610	2109759	MA	Santa Filomena do Maranhão
611	2109809	MA	Santa Helena
612	2109908	MA	Santa Inês
613	2110005	MA	Santa Luzia
614	2110039	MA	Santa Luzia do Paruá
615	2110104	MA	Santa Quitéria do Maranhão
616	2110203	MA	Santa Rita
617	2110237	MA	Santana do Maranhão
618	2110278	MA	Santo Amaro do Maranhão
619	2110302	MA	Santo Antônio dos Lopes
620	2110401	MA	São Benedito do Rio Preto
621	2110500	MA	São Bento
622	2110609	MA	São Bernardo
623	2110658	MA	São Domingos do Azeitão
624	2110708	MA	São Domingos do Maranhão
625	2110807	MA	São Félix de Balsas
626	2110856	MA	São Francisco do Brejão
627	2110906	MA	São Francisco do Maranhão
628	2111003	MA	São João Batista
629	2111029	MA	São João do Carú
630	2111052	MA	São João do Paraíso
631	2111078	MA	São João do Soter
632	2111102	MA	São João dos Patos
633	2111201	MA	São José de Ribamar
634	2111250	MA	São José dos Basílios
635	2111300	MA	São Luís
636	2111409	MA	São Luís Gonzaga do Maranhão
637	2111508	MA	São Mateus do Maranhão
638	2111532	MA	São Pedro da Água Branca
639	2111573	MA	São Pedro dos Crentes
640	2111607	MA	São Raimundo das Mangabeiras
641	2111631	MA	São Raimundo do Doca Bezerra
642	2111672	MA	São Roberto
643	2111706	MA	São Vicente Ferrer
644	2111722	MA	Satubinha
645	2111748	MA	Senador Alexandre Costa
646	2111763	MA	Senador La Rocque
647	2111789	MA	Serrano do Maranhão
648	2111805	MA	Sítio Novo
649	2111904	MA	Sucupira do Norte
650	2111953	MA	Sucupira do Riachão
651	2112001	MA	Tasso Fragoso
652	2112100	MA	Timbiras
653	2112209	MA	Timon
654	2112233	MA	Trizidela do Vale
655	2112274	MA	Tufilândia
656	2112308	MA	Tuntum
657	2112407	MA	Turiaçu
658	2112456	MA	Turilândia
659	2112506	MA	Tutóia
660	2112605	MA	Urbano Santos
661	2112704	MA	Vargem Grande
662	2112803	MA	Viana
663	2112852	MA	Vila Nova dos Martírios
664	2112902	MA	Vitória do Mearim
665	2113009	MA	Vitorino Freire
666	2114007	MA	Zé Doca
667	2200053	PI	Acauã
668	2200103	PI	Agricolândia
669	2200202	PI	Água Branca
670	2200251	PI	Alagoinha do Piauí
671	2200277	PI	Alegrete do Piauí
672	2200301	PI	Alto Longá
673	2200400	PI	Altos
674	2200459	PI	Alvorada do Gurguéia
675	2200509	PI	Amarante
676	2200608	PI	Angical do Piauí
677	2200707	PI	Anísio de Abreu
678	2200806	PI	Antônio Almeida
679	2200905	PI	Aroazes
680	2200954	PI	Aroeiras do Itaim
681	2201002	PI	Arraial
682	2201051	PI	Assunção do Piauí
683	2201101	PI	Avelino Lopes
684	2201150	PI	Baixa Grande do Ribeiro
685	2201176	PI	Barra D'Alcântara
686	2201200	PI	Barras
687	2201309	PI	Barreiras do Piauí
688	2201408	PI	Barro Duro
689	2201507	PI	Batalha
690	2201556	PI	Bela Vista do Piauí
691	2201572	PI	Belém do Piauí
692	2201606	PI	Beneditinos
693	2201705	PI	Bertolínia
694	2201739	PI	Betânia do Piauí
695	2201770	PI	Boa Hora
696	2201804	PI	Bocaina
697	2201903	PI	Bom Jesus
698	2201919	PI	Bom Princípio do Piauí
699	2201929	PI	Bonfim do Piauí
700	2201945	PI	Boqueirão do Piauí
701	2201960	PI	Brasileira
702	2201988	PI	Brejo do Piauí
703	2202000	PI	Buriti dos Lopes
704	2202026	PI	Buriti dos Montes
705	2202059	PI	Cabeceiras do Piauí
706	2202075	PI	Cajazeiras do Piauí
707	2202083	PI	Cajueiro da Praia
708	2202091	PI	Caldeirão Grande do Piauí
709	2202109	PI	Campinas do Piauí
710	2202117	PI	Campo Alegre do Fidalgo
711	2202133	PI	Campo Grande do Piauí
712	2202174	PI	Campo Largo do Piauí
713	2202208	PI	Campo Maior
714	2202251	PI	Canavieira
715	2202307	PI	Canto do Buriti
716	2202406	PI	Capitão de Campos
717	2202455	PI	Capitão Gervásio Oliveira
718	2202505	PI	Caracol
719	2202539	PI	Caraúbas do Piauí
720	2202554	PI	Caridade do Piauí
721	2202604	PI	Castelo do Piauí
722	2202653	PI	Caxingó
723	2202703	PI	Cocal
724	2202711	PI	Cocal de Telha
725	2202729	PI	Cocal dos Alves
726	2202737	PI	Coivaras
727	2202752	PI	Colônia do Gurguéia
728	2202778	PI	Colônia do Piauí
729	2202802	PI	Conceição do Canindé
730	2202851	PI	Coronel José Dias
731	2202901	PI	Corrente
732	2203008	PI	Cristalândia do Piauí
733	2203107	PI	Cristino Castro
734	2203206	PI	Curimatá
735	2203230	PI	Currais
736	2203271	PI	Curral Novo do Piauí
737	2203255	PI	Curralinhos
738	2203305	PI	Demerval Lobão
739	2203354	PI	Dirceu Arcoverde
740	2203404	PI	Dom Expedito Lopes
741	2203453	PI	Dom Inocêncio
742	2203420	PI	Domingos Mourão
743	2203503	PI	Elesbão Veloso
744	2203602	PI	Eliseu Martins
745	2203701	PI	Esperantina
746	2203750	PI	Fartura do Piauí
747	2203800	PI	Flores do Piauí
748	2203859	PI	Floresta do Piauí
749	2203909	PI	Floriano
750	2204006	PI	Francinópolis
751	2204105	PI	Francisco Ayres
752	2204154	PI	Francisco Macedo
753	2204204	PI	Francisco Santos
754	2204303	PI	Fronteiras
755	2204352	PI	Geminiano
756	2204402	PI	Gilbués
757	2204501	PI	Guadalupe
758	2204550	PI	Guaribas
759	2204600	PI	Hugo Napoleão
760	2204659	PI	Ilha Grande
761	2204709	PI	Inhuma
762	2204808	PI	Ipiranga do Piauí
763	2204907	PI	Isaías Coelho
764	2205003	PI	Itainópolis
765	2205102	PI	Itaueira
766	2205151	PI	Jacobina do Piauí
767	2205201	PI	Jaicós
768	2205250	PI	Jardim do Mulato
769	2205276	PI	Jatobá do Piauí
770	2205300	PI	Jerumenha
771	2205359	PI	João Costa
772	2205409	PI	Joaquim Pires
773	2205458	PI	Joca Marques
774	2205508	PI	José de Freitas
775	2205516	PI	Juazeiro do Piauí
776	2205524	PI	Júlio Borges
777	2205532	PI	Jurema
778	2205557	PI	Lagoa Alegre
779	2205573	PI	Lagoa de São Francisco
780	2205565	PI	Lagoa do Barro do Piauí
781	2205581	PI	Lagoa do Piauí
782	2205599	PI	Lagoa do Sítio
783	2205540	PI	Lagoinha do Piauí
784	2205607	PI	Landri Sales
785	2205706	PI	Luís Correia
786	2205805	PI	Luzilândia
787	2205854	PI	Madeiro
788	2205904	PI	Manoel Emídio
789	2205953	PI	Marcolândia
790	2206001	PI	Marcos Parente
791	2206050	PI	Massapê do Piauí
792	2206100	PI	Matias Olímpio
793	2206209	PI	Miguel Alves
794	2206308	PI	Miguel Leão
795	2206357	PI	Milton Brandão
796	2206407	PI	Monsenhor Gil
797	2206506	PI	Monsenhor Hipólito
798	2206605	PI	Monte Alegre do Piauí
799	2206654	PI	Morro Cabeça no Tempo
800	2206670	PI	Morro do Chapéu do Piauí
801	2206696	PI	Murici dos Portelas
802	2206704	PI	Nazaré do Piauí
803	2206720	PI	Nazária 
804	2206753	PI	Nossa Senhora de Nazaré
805	2206803	PI	Nossa Senhora dos Remédios
806	2207959	PI	Nova Santa Rita
807	2206902	PI	Novo Oriente do Piauí
808	2206951	PI	Novo Santo Antônio
809	2207009	PI	Oeiras
810	2207108	PI	Olho D'Água do Piauí
811	2207207	PI	Padre Marcos
812	2207306	PI	Paes Landim
813	2207355	PI	Pajeú do Piauí
814	2207405	PI	Palmeira do Piauí
815	2207504	PI	Palmeirais
816	2207553	PI	Paquetá
817	2207603	PI	Parnaguá
818	2207702	PI	Parnaíba
819	2207751	PI	Passagem Franca do Piauí
820	2207777	PI	Patos do Piauí
821	2207793	PI	Pau D'Arco do Piauí
822	2207801	PI	Paulistana
823	2207850	PI	Pavussu
824	2207900	PI	Pedro II
825	2207934	PI	Pedro Laurentino
826	2208007	PI	Picos
827	2208106	PI	Pimenteiras
828	2208205	PI	Pio IX
829	2208304	PI	Piracuruca
830	2208403	PI	Piripiri
831	2208502	PI	Porto
832	2208551	PI	Porto Alegre do Piauí
833	2208601	PI	Prata do Piauí
834	2208650	PI	Queimada Nova
835	2208700	PI	Redenção do Gurguéia
836	2208809	PI	Regeneração
837	2208858	PI	Riacho Frio
838	2208874	PI	Ribeira do Piauí
839	2208908	PI	Ribeiro Gonçalves
840	2209005	PI	Rio Grande do Piauí
841	2209104	PI	Santa Cruz do Piauí
842	2209153	PI	Santa Cruz dos Milagres
843	2209203	PI	Santa Filomena
844	2209302	PI	Santa Luz
845	2209377	PI	Santa Rosa do Piauí
846	2209351	PI	Santana do Piauí
847	2209401	PI	Santo Antônio de Lisboa
848	2209450	PI	Santo Antônio dos Milagres
849	2209500	PI	Santo Inácio do Piauí
850	2209559	PI	São Braz do Piauí
851	2209609	PI	São Félix do Piauí
852	2209658	PI	São Francisco de Assis do Piauí
853	2209708	PI	São Francisco do Piauí
854	2209757	PI	São Gonçalo do Gurguéia
855	2209807	PI	São Gonçalo do Piauí
856	2209856	PI	São João da Canabrava
857	2209872	PI	São João da Fronteira
858	2209906	PI	São João da Serra
859	2209955	PI	São João da Varjota
860	2209971	PI	São João do Arraial
861	2210003	PI	São João do Piauí
862	2210052	PI	São José do Divino
863	2210102	PI	São José do Peixe
864	2210201	PI	São José do Piauí
865	2210300	PI	São Julião
866	2210359	PI	São Lourenço do Piauí
867	2210375	PI	São Luis do Piauí
868	2210383	PI	São Miguel da Baixa Grande
869	2210391	PI	São Miguel do Fidalgo
870	2210409	PI	São Miguel do Tapuio
871	2210508	PI	São Pedro do Piauí
872	2210607	PI	São Raimundo Nonato
873	2210623	PI	Sebastião Barros
874	2210631	PI	Sebastião Leal
875	2210656	PI	Sigefredo Pacheco
876	2210706	PI	Simões
877	2210805	PI	Simplício Mendes
878	2210904	PI	Socorro do Piauí
879	2210938	PI	Sussuapara
880	2210953	PI	Tamboril do Piauí
881	2210979	PI	Tanque do Piauí
882	2211001	PI	Teresina
883	2211100	PI	União
884	2211209	PI	Uruçuí
885	2211308	PI	Valença do Piauí
886	2211357	PI	Várzea Branca
887	2211407	PI	Várzea Grande
888	2211506	PI	Vera Mendes
889	2211605	PI	Vila Nova do Piauí
890	2211704	PI	Wall Ferraz
891	2300101	CE	Abaiara
892	2300150	CE	Acarape
893	2300200	CE	Acaraú
894	2300309	CE	Acopiara
895	2300408	CE	Aiuaba
896	2300507	CE	Alcântaras
897	2300606	CE	Altaneira
898	2300705	CE	Alto Santo
899	2300754	CE	Amontada
900	2300804	CE	Antonina do Norte
901	2300903	CE	Apuiarés
902	2301000	CE	Aquiraz
903	2301109	CE	Aracati
904	2301208	CE	Aracoiaba
905	2301257	CE	Ararendá
906	2301307	CE	Araripe
907	2301406	CE	Aratuba
908	2301505	CE	Arneiroz
909	2301604	CE	Assaré
910	2301703	CE	Aurora
911	2301802	CE	Baixio
912	2301851	CE	Banabuiú
913	2301901	CE	Barbalha
914	2301950	CE	Barreira
915	2302008	CE	Barro
916	2302057	CE	Barroquinha
917	2302107	CE	Baturité
918	2302206	CE	Beberibe
919	2302305	CE	Bela Cruz
920	2302404	CE	Boa Viagem
921	2302503	CE	Brejo Santo
922	2302602	CE	Camocim
923	2302701	CE	Campos Sales
924	2302800	CE	Canindé
925	2302909	CE	Capistrano
926	2303006	CE	Caridade
927	2303105	CE	Cariré
928	2303204	CE	Caririaçu
929	2303303	CE	Cariús
930	2303402	CE	Carnaubal
931	2303501	CE	Cascavel
932	2303600	CE	Catarina
933	2303659	CE	Catunda
934	2303709	CE	Caucaia
935	2303808	CE	Cedro
936	2303907	CE	Chaval
937	2303931	CE	Choró
938	2303956	CE	Chorozinho
939	2304004	CE	Coreaú
940	2304103	CE	Crateús
941	2304202	CE	Crato
942	2304236	CE	Croatá
943	2304251	CE	Cruz
944	2304269	CE	Deputado Irapuan Pinheiro
945	2304277	CE	Ererê
946	2304285	CE	Eusébio
947	2304301	CE	Farias Brito
948	2304350	CE	Forquilha
949	2304400	CE	Fortaleza
950	2304459	CE	Fortim
951	2304509	CE	Frecheirinha
952	2304608	CE	General Sampaio
953	2304657	CE	Graça
954	2304707	CE	Granja
955	2304806	CE	Granjeiro
956	2304905	CE	Groaíras
957	2304954	CE	Guaiúba
958	2305001	CE	Guaraciaba do Norte
959	2305100	CE	Guaramiranga
960	2305209	CE	Hidrolândia
961	2305233	CE	Horizonte
962	2305266	CE	Ibaretama
963	2305308	CE	Ibiapina
964	2305332	CE	Ibicuitinga
965	2305357	CE	Icapuí
966	2305407	CE	Icó
967	2305506	CE	Iguatu
968	2305605	CE	Independência
969	2305654	CE	Ipaporanga
970	2305704	CE	Ipaumirim
971	2305803	CE	Ipu
972	2305902	CE	Ipueiras
973	2306009	CE	Iracema
974	2306108	CE	Irauçuba
975	2306207	CE	Itaiçaba
976	2306256	CE	Itaitinga
977	2306306	CE	Itapagé
978	2306405	CE	Itapipoca
979	2306504	CE	Itapiúna
980	2306553	CE	Itarema
981	2306603	CE	Itatira
982	2306702	CE	Jaguaretama
983	2306801	CE	Jaguaribara
984	2306900	CE	Jaguaribe
985	2307007	CE	Jaguaruana
986	2307106	CE	Jardim
987	2307205	CE	Jati
988	2307254	CE	Jijoca de Jericoacoara
989	2307304	CE	Juazeiro do Norte
990	2307403	CE	Jucás
991	2307502	CE	Lavras da Mangabeira
992	2307601	CE	Limoeiro do Norte
993	2307635	CE	Madalena
994	2307650	CE	Maracanaú
995	2307700	CE	Maranguape
996	2307809	CE	Marco
997	2307908	CE	Martinópole
998	2308005	CE	Massapê
999	2308104	CE	Mauriti
1000	2308203	CE	Meruoca
1001	2308302	CE	Milagres
1002	2308351	CE	Milhã
1003	2308377	CE	Miraíma
1004	2308401	CE	Missão Velha
1005	2308500	CE	Mombaça
1006	2308609	CE	Monsenhor Tabosa
1007	2308708	CE	Morada Nova
1008	2308807	CE	Moraújo
1009	2308906	CE	Morrinhos
1010	2309003	CE	Mucambo
1011	2309102	CE	Mulungu
1012	2309201	CE	Nova Olinda
1013	2309300	CE	Nova Russas
1014	2309409	CE	Novo Oriente
1015	2309458	CE	Ocara
1016	2309508	CE	Orós
1017	2309607	CE	Pacajus
1018	2309706	CE	Pacatuba
1019	2309805	CE	Pacoti
1020	2309904	CE	Pacujá
1021	2310001	CE	Palhano
1022	2310100	CE	Palmácia
1023	2310209	CE	Paracuru
1024	2310258	CE	Paraipaba
1025	2310308	CE	Parambu
1026	2310407	CE	Paramoti
1027	2310506	CE	Pedra Branca
1028	2310605	CE	Penaforte
1029	2310704	CE	Pentecoste
1030	2310803	CE	Pereiro
1031	2310852	CE	Pindoretama
1032	2310902	CE	Piquet Carneiro
1033	2310951	CE	Pires Ferreira
1034	2311009	CE	Poranga
1035	2311108	CE	Porteiras
1036	2311207	CE	Potengi
1037	2311231	CE	Potiretama
1038	2311264	CE	Quiterianópolis
1039	2311306	CE	Quixadá
1040	2311355	CE	Quixelô
1041	2311405	CE	Quixeramobim
1042	2311504	CE	Quixeré
1043	2311603	CE	Redenção
1044	2311702	CE	Reriutaba
1045	2311801	CE	Russas
1046	2311900	CE	Saboeiro
1047	2311959	CE	Salitre
1048	2312205	CE	Santa Quitéria
1049	2312007	CE	Santana do Acaraú
1050	2312106	CE	Santana do Cariri
1051	2312304	CE	São Benedito
1052	2312403	CE	São Gonçalo do Amarante
1053	2312502	CE	São João do Jaguaribe
1054	2312601	CE	São Luís do Curu
1055	2312700	CE	Senador Pompeu
1056	2312809	CE	Senador Sá
1057	2312908	CE	Sobral
1058	2313005	CE	Solonópole
1059	2313104	CE	Tabuleiro do Norte
1060	2313203	CE	Tamboril
1061	2313252	CE	Tarrafas
1062	2313302	CE	Tauá
1063	2313351	CE	Tejuçuoca
1064	2313401	CE	Tianguá
1065	2313500	CE	Trairi
1066	2313559	CE	Tururu
1067	2313609	CE	Ubajara
1068	2313708	CE	Umari
1069	2313757	CE	Umirim
1070	2313807	CE	Uruburetama
1071	2313906	CE	Uruoca
1072	2313955	CE	Varjota
1073	2314003	CE	Várzea Alegre
1074	2314102	CE	Viçosa do Ceará
1075	2400109	RN	Acari
1076	2400208	RN	Açu
1077	2400307	RN	Afonso Bezerra
1078	2400406	RN	Água Nova
1079	2400505	RN	Alexandria
1080	2400604	RN	Almino Afonso
1081	2400703	RN	Alto do Rodrigues
1082	2400802	RN	Angicos
1083	2400901	RN	Antônio Martins
1084	2401008	RN	Apodi
1085	2401107	RN	Areia Branca
1086	2401206	RN	Arês
1087	2401305	RN	Augusto Severo
1088	2401404	RN	Baía Formosa
1089	2401453	RN	Baraúna
1090	2401503	RN	Barcelona
1091	2401602	RN	Bento Fernandes
1092	2401651	RN	Bodó
1093	2401701	RN	Bom Jesus
1094	2401800	RN	Brejinho
1095	2401859	RN	Caiçara do Norte
1096	2401909	RN	Caiçara do Rio do Vento
1097	2402006	RN	Caicó
1098	2402105	RN	Campo Redondo
1099	2402204	RN	Canguaretama
1100	2402303	RN	Caraúbas
1101	2402402	RN	Carnaúba dos Dantas
1102	2402501	RN	Carnaubais
1103	2402600	RN	Ceará-Mirim
1104	2402709	RN	Cerro Corá
1105	2402808	RN	Coronel Ezequiel
1106	2402907	RN	Coronel João Pessoa
1107	2403004	RN	Cruzeta
1108	2403103	RN	Currais Novos
1109	2403202	RN	Doutor Severiano
1110	2403301	RN	Encanto
1111	2403400	RN	Equador
1112	2403509	RN	Espírito Santo
1113	2403608	RN	Extremoz
1114	2403707	RN	Felipe Guerra
1115	2403756	RN	Fernando Pedroza
1116	2403806	RN	Florânia
1117	2403905	RN	Francisco Dantas
1118	2404002	RN	Frutuoso Gomes
1119	2404101	RN	Galinhos
1120	2404200	RN	Goianinha
1121	2404309	RN	Governador Dix-Sept Rosado
1122	2404408	RN	Grossos
1123	2404507	RN	Guamaré
1124	2404606	RN	Ielmo Marinho
1125	2404705	RN	Ipanguaçu
1126	2404804	RN	Ipueira
1127	2404853	RN	Itajá
1128	2404903	RN	Itaú
1129	2405009	RN	Jaçanã
1130	2405108	RN	Jandaíra
1131	2405207	RN	Janduís
1132	2405306	RN	Januário Cicco
1133	2405405	RN	Japi
1134	2405504	RN	Jardim de Angicos
1135	2405603	RN	Jardim de Piranhas
1136	2405702	RN	Jardim do Seridó
1137	2405801	RN	João Câmara
1138	2405900	RN	João Dias
1139	2406007	RN	José da Penha
1140	2406106	RN	Jucurutu
1141	2406155	RN	Jundiá
1142	2406205	RN	Lagoa d'Anta
1143	2406304	RN	Lagoa de Pedras
1144	2406403	RN	Lagoa de Velhos
1145	2406502	RN	Lagoa Nova
1146	2406601	RN	Lagoa Salgada
1147	2406700	RN	Lajes
1148	2406809	RN	Lajes Pintadas
1149	2406908	RN	Lucrécia
1150	2407005	RN	Luís Gomes
1151	2407104	RN	Macaíba
1152	2407203	RN	Macau
1153	2407252	RN	Major Sales
1154	2407302	RN	Marcelino Vieira
1155	2407401	RN	Martins
1156	2407500	RN	Maxaranguape
1157	2407609	RN	Messias Targino
1158	2407708	RN	Montanhas
1159	2407807	RN	Monte Alegre
1160	2407906	RN	Monte das Gameleiras
1161	2408003	RN	Mossoró
1162	2408102	RN	Natal
1163	2408201	RN	Nísia Floresta
1164	2408300	RN	Nova Cruz
1165	2408409	RN	Olho-d'Água do Borges
1166	2408508	RN	Ouro Branco
1167	2408607	RN	Paraná
1168	2408706	RN	Paraú
1169	2408805	RN	Parazinho
1170	2408904	RN	Parelhas
1171	2403251	RN	Parnamirim
1172	2409100	RN	Passa e Fica
1173	2409209	RN	Passagem
1174	2409308	RN	Patu
1175	2409407	RN	Pau dos Ferros
1176	2409506	RN	Pedra Grande
1177	2409605	RN	Pedra Preta
1178	2409704	RN	Pedro Avelino
1179	2409803	RN	Pedro Velho
1180	2409902	RN	Pendências
1181	2410009	RN	Pilões
1182	2410108	RN	Poço Branco
1183	2410207	RN	Portalegre
1184	2410256	RN	Porto do Mangue
1185	2410306	RN	Presidente Juscelino
1186	2410405	RN	Pureza
1187	2410504	RN	Rafael Fernandes
1188	2410603	RN	Rafael Godeiro
1189	2410702	RN	Riacho da Cruz
1190	2410801	RN	Riacho de Santana
1191	2410900	RN	Riachuelo
1192	2408953	RN	Rio do Fogo
1193	2411007	RN	Rodolfo Fernandes
1194	2411106	RN	Ruy Barbosa
1195	2411205	RN	Santa Cruz
1196	2409332	RN	Santa Maria
1197	2411403	RN	Santana do Matos
1198	2411429	RN	Santana do Seridó
1199	2411502	RN	Santo Antônio
1200	2411601	RN	São Bento do Norte
1201	2411700	RN	São Bento do Trairí
1202	2411809	RN	São Fernando
1203	2411908	RN	São Francisco do Oeste
1204	2412005	RN	São Gonçalo do Amarante
1205	2412104	RN	São João do Sabugi
1206	2412203	RN	São José de Mipibu
1207	2412302	RN	São José do Campestre
1208	2412401	RN	São José do Seridó
1209	2412500	RN	São Miguel
1210	2412559	RN	São Miguel do Gostoso
1211	2412609	RN	São Paulo do Potengi
1212	2412708	RN	São Pedro
1213	2412807	RN	São Rafael
1214	2412906	RN	São Tomé
1215	2413003	RN	São Vicente
1216	2413102	RN	Senador Elói de Souza
1217	2413201	RN	Senador Georgino Avelino
1218	2413300	RN	Serra de São Bento
1219	2413359	RN	Serra do Mel
1220	2413409	RN	Serra Negra do Norte
1221	2413508	RN	Serrinha
1222	2413557	RN	Serrinha dos Pintos
1223	2413607	RN	Severiano Melo
1224	2413706	RN	Sítio Novo
1225	2413805	RN	Taboleiro Grande
1226	2413904	RN	Taipu
1227	2414001	RN	Tangará
1228	2414100	RN	Tenente Ananias
1229	2414159	RN	Tenente Laurentino Cruz
1230	2411056	RN	Tibau
1231	2414209	RN	Tibau do Sul
1232	2414308	RN	Timbaúba dos Batistas
1233	2414407	RN	Touros
1234	2414456	RN	Triunfo Potiguar
1235	2414506	RN	Umarizal
1236	2414605	RN	Upanema
1237	2414704	RN	Várzea
1238	2414753	RN	Venha-Ver
1239	2414803	RN	Vera Cruz
1240	2414902	RN	Viçosa
1241	2415008	RN	Vila Flor
1242	2500106	PB	Água Branca
1243	2500205	PB	Aguiar
1244	2500304	PB	Alagoa Grande
1245	2500403	PB	Alagoa Nova
1246	2500502	PB	Alagoinha
1247	2500536	PB	Alcantil
1248	2500577	PB	Algodão de Jandaíra
1249	2500601	PB	Alhandra
1250	2500734	PB	Amparo
1251	2500775	PB	Aparecida
1252	2500809	PB	Araçagi
1253	2500908	PB	Arara
1254	2501005	PB	Araruna
1255	2501104	PB	Areia
1256	2501153	PB	Areia de Baraúnas
1257	2501203	PB	Areial
1258	2501302	PB	Aroeiras
1259	2501351	PB	Assunção
1260	2501401	PB	Baía da Traição
1261	2501500	PB	Bananeiras
1262	2501534	PB	Baraúna
1263	2501609	PB	Barra de Santa Rosa
1264	2501575	PB	Barra de Santana
1265	2501708	PB	Barra de São Miguel
1266	2501807	PB	Bayeux
1267	2501906	PB	Belém
1268	2502003	PB	Belém do Brejo do Cruz
1269	2502052	PB	Bernardino Batista
1270	2502102	PB	Boa Ventura
1271	2502151	PB	Boa Vista
1272	2502201	PB	Bom Jesus
1273	2502300	PB	Bom Sucesso
1274	2502409	PB	Bonito de Santa Fé
1275	2502508	PB	Boqueirão
1276	2502706	PB	Borborema
1277	2502805	PB	Brejo do Cruz
1278	2502904	PB	Brejo dos Santos
1279	2503001	PB	Caaporã
1280	2503100	PB	Cabaceiras
1281	2503209	PB	Cabedelo
1282	2503308	PB	Cachoeira dos Índios
1283	2503407	PB	Cacimba de Areia
1284	2503506	PB	Cacimba de Dentro
1285	2503555	PB	Cacimbas
1286	2503605	PB	Caiçara
1287	2503704	PB	Cajazeiras
1288	2503753	PB	Cajazeirinhas
1289	2503803	PB	Caldas Brandão
1290	2503902	PB	Camalaú
1291	2504009	PB	Campina Grande
1292	2516409	PB	Tacima
1293	2504033	PB	Capim
1294	2504074	PB	Caraúbas
1295	2504108	PB	Carrapateira
1296	2504157	PB	Casserengue
1297	2504207	PB	Catingueira
1298	2504306	PB	Catolé do Rocha
1299	2504355	PB	Caturité
1300	2504405	PB	Conceição
1301	2504504	PB	Condado
1302	2504603	PB	Conde
1303	2504702	PB	Congo
1304	2504801	PB	Coremas
1305	2504850	PB	Coxixola
1306	2504900	PB	Cruz do Espírito Santo
1307	2505006	PB	Cubati
1308	2505105	PB	Cuité
1309	2505238	PB	Cuité de Mamanguape
1310	2505204	PB	Cuitegi
1311	2505279	PB	Curral de Cima
1312	2505303	PB	Curral Velho
1313	2505352	PB	Damião
1314	2505402	PB	Desterro
1315	2505600	PB	Diamante
1316	2505709	PB	Dona Inês
1317	2505808	PB	Duas Estradas
1318	2505907	PB	Emas
1319	2506004	PB	Esperança
1320	2506103	PB	Fagundes
1321	2506202	PB	Frei Martinho
1322	2506251	PB	Gado Bravo
1323	2506301	PB	Guarabira
1324	2506400	PB	Gurinhém
1325	2506509	PB	Gurjão
1326	2506608	PB	Ibiara
1327	2502607	PB	Igaracy
1328	2506707	PB	Imaculada
1329	2506806	PB	Ingá
1330	2506905	PB	Itabaiana
1331	2507002	PB	Itaporanga
1332	2507101	PB	Itapororoca
1333	2507200	PB	Itatuba
1334	2507309	PB	Jacaraú
1335	2507408	PB	Jericó
1336	2507507	PB	João Pessoa
1337	2507606	PB	Juarez Távora
1338	2507705	PB	Juazeirinho
1339	2507804	PB	Junco do Seridó
1340	2507903	PB	Juripiranga
1341	2508000	PB	Juru
1342	2508109	PB	Lagoa
1343	2508208	PB	Lagoa de Dentro
1344	2508307	PB	Lagoa Seca
1345	2508406	PB	Lastro
1346	2508505	PB	Livramento
1347	2508554	PB	Logradouro
1348	2508604	PB	Lucena
1349	2508703	PB	Mãe d'Água
1350	2508802	PB	Malta
1351	2508901	PB	Mamanguape
1352	2509008	PB	Manaíra
1353	2509057	PB	Marcação
1354	2509107	PB	Mari
1355	2509156	PB	Marizópolis
1356	2509206	PB	Massaranduba
1357	2509305	PB	Mataraca
1358	2509339	PB	Matinhas
1359	2509370	PB	Mato Grosso
1360	2509396	PB	Maturéia
1361	2509404	PB	Mogeiro
1362	2509503	PB	Montadas
1363	2509602	PB	Monte Horebe
1364	2509701	PB	Monteiro
1365	2509800	PB	Mulungu
1366	2509909	PB	Natuba
1367	2510006	PB	Nazarezinho
1368	2510105	PB	Nova Floresta
1369	2510204	PB	Nova Olinda
1370	2510303	PB	Nova Palmeira
1371	2510402	PB	Olho d'Água
1372	2510501	PB	Olivedos
1373	2510600	PB	Ouro Velho
1374	2510659	PB	Parari
1375	2510709	PB	Passagem
1376	2510808	PB	Patos
1377	2510907	PB	Paulista
1378	2511004	PB	Pedra Branca
1379	2511103	PB	Pedra Lavrada
1380	2511202	PB	Pedras de Fogo
1381	2512721	PB	Pedro Régis
1382	2511301	PB	Piancó
1383	2511400	PB	Picuí
1384	2511509	PB	Pilar
1385	2511608	PB	Pilões
1386	2511707	PB	Pilõezinhos
1387	2511806	PB	Pirpirituba
1388	2511905	PB	Pitimbu
1389	2512002	PB	Pocinhos
1390	2512036	PB	Poço Dantas
1391	2512077	PB	Poço de José de Moura
1392	2512101	PB	Pombal
1393	2512200	PB	Prata
1394	2512309	PB	Princesa Isabel
1395	2512408	PB	Puxinanã
1396	2512507	PB	Queimadas
1397	2512606	PB	Quixabá
1398	2512705	PB	Remígio
1399	2512747	PB	Riachão
1400	2512754	PB	Riachão do Bacamarte
1401	2512762	PB	Riachão do Poço
1402	2512788	PB	Riacho de Santo Antônio
1403	2512804	PB	Riacho dos Cavalos
1404	2512903	PB	Rio Tinto
1405	2513000	PB	Salgadinho
1406	2513109	PB	Salgado de São Félix
1407	2513158	PB	Santa Cecília
1408	2513208	PB	Santa Cruz
1409	2513307	PB	Santa Helena
1410	2513356	PB	Santa Inês
1411	2513406	PB	Santa Luzia
1412	2513703	PB	Santa Rita
1413	2513802	PB	Santa Teresinha
1414	2513505	PB	Santana de Mangueira
1415	2513604	PB	Santana dos Garrotes
1416	2513653	PB	Joca Claudino
1417	2513851	PB	Santo André
1418	2513927	PB	São Bentinho
1419	2513901	PB	São Bento
1420	2513968	PB	São Domingos
1421	2513943	PB	São Domingos do Cariri
1422	2513984	PB	São Francisco
1423	2514008	PB	São João do Cariri
1424	2500700	PB	São João do Rio do Peixe
1425	2514107	PB	São João do Tigre
1426	2514206	PB	São José da Lagoa Tapada
1427	2514305	PB	São José de Caiana
1428	2514404	PB	São José de Espinharas
1429	2514503	PB	São José de Piranhas
1430	2514552	PB	São José de Princesa
1431	2514602	PB	São José do Bonfim
1432	2514651	PB	São José do Brejo do Cruz
1433	2514701	PB	São José do Sabugi
1434	2514800	PB	São José dos Cordeiros
1435	2514453	PB	São José dos Ramos
1436	2514909	PB	São Mamede
1437	2515005	PB	São Miguel de Taipu
1438	2515104	PB	São Sebastião de Lagoa de Roça
1439	2515203	PB	São Sebastião do Umbuzeiro
1440	2515302	PB	Sapé
1441	2515401	PB	Seridó
1442	2515500	PB	Serra Branca
1443	2515609	PB	Serra da Raiz
1444	2515708	PB	Serra Grande
1445	2515807	PB	Serra Redonda
1446	2515906	PB	Serraria
1447	2515930	PB	Sertãozinho
1448	2515971	PB	Sobrado
1449	2516003	PB	Solânea
1450	2516102	PB	Soledade
1451	2516151	PB	Sossêgo
1452	2516201	PB	Sousa
1453	2516300	PB	Sumé
1454	2516508	PB	Taperoá
1455	2516607	PB	Tavares
1456	2516706	PB	Teixeira
1457	2516755	PB	Tenório
1458	2516805	PB	Triunfo
1459	2516904	PB	Uiraúna
1460	2517001	PB	Umbuzeiro
1461	2517100	PB	Várzea
1462	2517209	PB	Vieirópolis
1463	2505501	PB	Vista Serrana
1464	2517407	PB	Zabelê
1465	2600054	PE	Abreu e Lima
1466	2600104	PE	Afogados da Ingazeira
1467	2600203	PE	Afrânio
1468	2600302	PE	Agrestina
1469	2600401	PE	Água Preta
1470	2600500	PE	Águas Belas
1471	2600609	PE	Alagoinha
1472	2600708	PE	Aliança
1473	2600807	PE	Altinho
1474	2600906	PE	Amaraji
1475	2601003	PE	Angelim
1476	2601052	PE	Araçoiaba
1477	2601102	PE	Araripina
1478	2601201	PE	Arcoverde
1479	2601300	PE	Barra de Guabiraba
1480	2601409	PE	Barreiros
1481	2601508	PE	Belém de Maria
1482	2601607	PE	Belém do São Francisco
1483	2601706	PE	Belo Jardim
1484	2601805	PE	Betânia
1485	2601904	PE	Bezerros
1486	2602001	PE	Bodocó
1487	2602100	PE	Bom Conselho
1488	2602209	PE	Bom Jardim
1489	2602308	PE	Bonito
1490	2602407	PE	Brejão
1491	2602506	PE	Brejinho
1492	2602605	PE	Brejo da Madre de Deus
1493	2602704	PE	Buenos Aires
1494	2602803	PE	Buíque
1495	2602902	PE	Cabo de Santo Agostinho
1496	2603009	PE	Cabrobó
1497	2603108	PE	Cachoeirinha
1498	2603207	PE	Caetés
1499	2603306	PE	Calçado
1500	2603405	PE	Calumbi
1501	2603454	PE	Camaragibe
1502	2603504	PE	Camocim de São Félix
1503	2603603	PE	Camutanga
1504	2603702	PE	Canhotinho
1505	2603801	PE	Capoeiras
1506	2603900	PE	Carnaíba
1507	2603926	PE	Carnaubeira da Penha
1508	2604007	PE	Carpina
1509	2604106	PE	Caruaru
1510	2604155	PE	Casinhas
1511	2604205	PE	Catende
1512	2604304	PE	Cedro
1513	2604403	PE	Chã de Alegria
1514	2604502	PE	Chã Grande
1515	2604601	PE	Condado
1516	2604700	PE	Correntes
1517	2604809	PE	Cortês
1518	2604908	PE	Cumaru
1519	2605004	PE	Cupira
1520	2605103	PE	Custódia
1521	2605152	PE	Dormentes
1522	2605202	PE	Escada
1523	2605301	PE	Exu
1524	2605400	PE	Feira Nova
1525	2605459	PE	Fernando de Noronha
1526	2605509	PE	Ferreiros
1527	2605608	PE	Flores
1528	2605707	PE	Floresta
1529	2605806	PE	Frei Miguelinho
1530	2605905	PE	Gameleira
1531	2606002	PE	Garanhuns
1532	2606101	PE	Glória do Goitá
1533	2606200	PE	Goiana
1534	2606309	PE	Granito
1535	2606408	PE	Gravatá
1536	2606507	PE	Iati
1537	2606606	PE	Ibimirim
1538	2606705	PE	Ibirajuba
1539	2606804	PE	Igarassu
1540	2606903	PE	Iguaraci
1541	2607604	PE	Ilha de Itamaracá
1542	2607000	PE	Inajá
1543	2607109	PE	Ingazeira
1544	2607208	PE	Ipojuca
1545	2607307	PE	Ipubi
1546	2607406	PE	Itacuruba
1547	2607505	PE	Itaíba
1548	2607653	PE	Itambé
1549	2607703	PE	Itapetim
1550	2607752	PE	Itapissuma
1551	2607802	PE	Itaquitinga
1552	2607901	PE	Jaboatão dos Guararapes
1553	2607950	PE	Jaqueira
1554	2608008	PE	Jataúba
1555	2608057	PE	Jatobá
1556	2608107	PE	João Alfredo
1557	2608206	PE	Joaquim Nabuco
1558	2608255	PE	Jucati
1559	2608305	PE	Jupi
1560	2608404	PE	Jurema
1561	2608453	PE	Lagoa do Carro
1562	2608503	PE	Lagoa de Itaenga
1563	2608602	PE	Lagoa do Ouro
1564	2608701	PE	Lagoa dos Gatos
1565	2608750	PE	Lagoa Grande
1566	2608800	PE	Lajedo
1567	2608909	PE	Limoeiro
1568	2609006	PE	Macaparana
1569	2609105	PE	Machados
1570	2609154	PE	Manari
1571	2609204	PE	Maraial
1572	2609303	PE	Mirandiba
1573	2614303	PE	Moreilândia
1574	2609402	PE	Moreno
1575	2609501	PE	Nazaré da Mata
1576	2609600	PE	Olinda
1577	2609709	PE	Orobó
1578	2609808	PE	Orocó
1579	2609907	PE	Ouricuri
1580	2610004	PE	Palmares
1581	2610103	PE	Palmeirina
1582	2610202	PE	Panelas
1583	2610301	PE	Paranatama
1584	2610400	PE	Parnamirim
1585	2610509	PE	Passira
1586	2610608	PE	Paudalho
1587	2610707	PE	Paulista
1588	2610806	PE	Pedra
1589	2610905	PE	Pesqueira
1590	2611002	PE	Petrolândia
1591	2611101	PE	Petrolina
1592	2611200	PE	Poção
1593	2611309	PE	Pombos
1594	2611408	PE	Primavera
1595	2611507	PE	Quipapá
1596	2611533	PE	Quixaba
1597	2611606	PE	Recife
1598	2611705	PE	Riacho das Almas
1599	2611804	PE	Ribeirão
1600	2611903	PE	Rio Formoso
1601	2612000	PE	Sairé
1602	2612109	PE	Salgadinho
1603	2612208	PE	Salgueiro
1604	2612307	PE	Saloá
1605	2612406	PE	Sanharó
1606	2612455	PE	Santa Cruz
1607	2612471	PE	Santa Cruz da Baixa Verde
1608	2612505	PE	Santa Cruz do Capibaribe
1609	2612554	PE	Santa Filomena
1610	2612604	PE	Santa Maria da Boa Vista
1611	2612703	PE	Santa Maria do Cambucá
1612	2612802	PE	Santa Terezinha
1613	2612901	PE	São Benedito do Sul
1614	2613008	PE	São Bento do Una
1615	2613107	PE	São Caitano
1616	2613206	PE	São João
1617	2613305	PE	São Joaquim do Monte
1618	2613404	PE	São José da Coroa Grande
1619	2613503	PE	São José do Belmonte
1620	2613602	PE	São José do Egito
1621	2613701	PE	São Lourenço da Mata
1622	2613800	PE	São Vicente Ferrer
1623	2613909	PE	Serra Talhada
1624	2614006	PE	Serrita
1625	2614105	PE	Sertânia
1626	2614204	PE	Sirinhaém
1627	2614402	PE	Solidão
1628	2614501	PE	Surubim
1629	2614600	PE	Tabira
1630	2614709	PE	Tacaimbó
1631	2614808	PE	Tacaratu
1632	2614857	PE	Tamandaré
1633	2615003	PE	Taquaritinga do Norte
1634	2615102	PE	Terezinha
1635	2615201	PE	Terra Nova
1636	2615300	PE	Timbaúba
1637	2615409	PE	Toritama
1638	2615508	PE	Tracunhaém
1639	2615607	PE	Trindade
1640	2615706	PE	Triunfo
1641	2615805	PE	Tupanatinga
1642	2615904	PE	Tuparetama
1643	2616001	PE	Venturosa
1644	2616100	PE	Verdejante
1645	2616183	PE	Vertente do Lério
1646	2616209	PE	Vertentes
1647	2616308	PE	Vicência
1648	2616407	PE	Vitória de Santo Antão
1649	2616506	PE	Xexéu
1650	2700102	AL	Água Branca
1651	2700201	AL	Anadia
1652	2700300	AL	Arapiraca
1653	2700409	AL	Atalaia
1654	2700508	AL	Barra de Santo Antônio
1655	2700607	AL	Barra de São Miguel
1656	2700706	AL	Batalha
1657	2700805	AL	Belém
1658	2700904	AL	Belo Monte
1659	2701001	AL	Boca da Mata
1660	2701100	AL	Branquinha
1661	2701209	AL	Cacimbinhas
1662	2701308	AL	Cajueiro
1663	2701357	AL	Campestre
1664	2701407	AL	Campo Alegre
1665	2701506	AL	Campo Grande
1666	2701605	AL	Canapi
1667	2701704	AL	Capela
1668	2701803	AL	Carneiros
1669	2701902	AL	Chã Preta
1670	2702009	AL	Coité do Nóia
1671	2702108	AL	Colônia Leopoldina
1672	2702207	AL	Coqueiro Seco
1673	2702306	AL	Coruripe
1674	2702355	AL	Craíbas
1675	2702405	AL	Delmiro Gouveia
1676	2702504	AL	Dois Riachos
1677	2702553	AL	Estrela de Alagoas
1678	2702603	AL	Feira Grande
1679	2702702	AL	Feliz Deserto
1680	2702801	AL	Flexeiras
1681	2702900	AL	Girau do Ponciano
1682	2703007	AL	Ibateguara
1683	2703106	AL	Igaci
1684	2703205	AL	Igreja Nova
1685	2703304	AL	Inhapi
1686	2703403	AL	Jacaré dos Homens
1687	2703502	AL	Jacuípe
1688	2703601	AL	Japaratinga
1689	2703700	AL	Jaramataia
1690	2703759	AL	Jequiá da Praia
1691	2703809	AL	Joaquim Gomes
1692	2703908	AL	Jundiá
1693	2704005	AL	Junqueiro
1694	2704104	AL	Lagoa da Canoa
1695	2704203	AL	Limoeiro de Anadia
1696	2704302	AL	Maceió
1697	2704401	AL	Major Isidoro
1698	2704906	AL	Mar Vermelho
1699	2704500	AL	Maragogi
1700	2704609	AL	Maravilha
1701	2704708	AL	Marechal Deodoro
1702	2704807	AL	Maribondo
1703	2705002	AL	Mata Grande
1704	2705101	AL	Matriz de Camaragibe
1705	2705200	AL	Messias
1706	2705309	AL	Minador do Negrão
1707	2705408	AL	Monteirópolis
1708	2705507	AL	Murici
1709	2705606	AL	Novo Lino
1710	2705705	AL	Olho d'Água das Flores
1711	2705804	AL	Olho d'Água do Casado
1712	2705903	AL	Olho d'Água Grande
1713	2706000	AL	Olivença
1714	2706109	AL	Ouro Branco
1715	2706208	AL	Palestina
1716	2706307	AL	Palmeira dos Índios
1717	2706406	AL	Pão de Açúcar
1718	2706422	AL	Pariconha
1719	2706448	AL	Paripueira
1720	2706505	AL	Passo de Camaragibe
1721	2706604	AL	Paulo Jacinto
1722	2706703	AL	Penedo
1723	2706802	AL	Piaçabuçu
1724	2706901	AL	Pilar
1725	2707008	AL	Pindoba
1726	2707107	AL	Piranhas
1727	2707206	AL	Poço das Trincheiras
1728	2707305	AL	Porto Calvo
1729	2707404	AL	Porto de Pedras
1730	2707503	AL	Porto Real do Colégio
1731	2707602	AL	Quebrangulo
1732	2707701	AL	Rio Largo
1733	2707800	AL	Roteiro
1734	2707909	AL	Santa Luzia do Norte
1735	2708006	AL	Santana do Ipanema
1736	2708105	AL	Santana do Mundaú
1737	2708204	AL	São Brás
1738	2708303	AL	São José da Laje
1739	2708402	AL	São José da Tapera
1740	2708501	AL	São Luís do Quitunde
1741	2708600	AL	São Miguel dos Campos
1742	2708709	AL	São Miguel dos Milagres
1743	2708808	AL	São Sebastião
1744	2708907	AL	Satuba
1745	2708956	AL	Senador Rui Palmeira
1746	2709004	AL	Tanque d'Arca
1747	2709103	AL	Taquarana
1748	2709152	AL	Teotônio Vilela
1749	2709202	AL	Traipu
1750	2709301	AL	União dos Palmares
1751	2709400	AL	Viçosa
1752	2800100	SE	Amparo de São Francisco
1753	2800209	SE	Aquidabã
1754	2800308	SE	Aracaju
1755	2800407	SE	Arauá
1756	2800506	SE	Areia Branca
1757	2800605	SE	Barra dos Coqueiros
1758	2800670	SE	Boquim
1759	2800704	SE	Brejo Grande
1760	2801009	SE	Campo do Brito
1761	2801108	SE	Canhoba
1762	2801207	SE	Canindé de São Francisco
1763	2801306	SE	Capela
1764	2801405	SE	Carira
1765	2801504	SE	Carmópolis
1766	2801603	SE	Cedro de São João
1767	2801702	SE	Cristinápolis
1768	2801900	SE	Cumbe
1769	2802007	SE	Divina Pastora
1770	2802106	SE	Estância
1771	2802205	SE	Feira Nova
1772	2802304	SE	Frei Paulo
1773	2802403	SE	Gararu
1774	2802502	SE	General Maynard
1775	2802601	SE	Gracho Cardoso
1776	2802700	SE	Ilha das Flores
1777	2802809	SE	Indiaroba
1778	2802908	SE	Itabaiana
1779	2803005	SE	Itabaianinha
1780	2803104	SE	Itabi
1781	2803203	SE	Itaporanga d'Ajuda
1782	2803302	SE	Japaratuba
1783	2803401	SE	Japoatã
1784	2803500	SE	Lagarto
1785	2803609	SE	Laranjeiras
1786	2803708	SE	Macambira
1787	2803807	SE	Malhada dos Bois
1788	2803906	SE	Malhador
1789	2804003	SE	Maruim
1790	2804102	SE	Moita Bonita
1791	2804201	SE	Monte Alegre de Sergipe
1792	2804300	SE	Muribeca
1793	2804409	SE	Neópolis
1794	2804458	SE	Nossa Senhora Aparecida
1795	2804508	SE	Nossa Senhora da Glória
1796	2804607	SE	Nossa Senhora das Dores
1797	2804706	SE	Nossa Senhora de Lourdes
1798	2804805	SE	Nossa Senhora do Socorro
1799	2804904	SE	Pacatuba
1800	2805000	SE	Pedra Mole
1801	2805109	SE	Pedrinhas
1802	2805208	SE	Pinhão
1803	2805307	SE	Pirambu
1804	2805406	SE	Poço Redondo
1805	2805505	SE	Poço Verde
1806	2805604	SE	Porto da Folha
1807	2805703	SE	Propriá
1808	2805802	SE	Riachão do Dantas
1809	2805901	SE	Riachuelo
1810	2806008	SE	Ribeirópolis
1811	2806107	SE	Rosário do Catete
1812	2806206	SE	Salgado
1813	2806305	SE	Santa Luzia do Itanhy
1814	2806503	SE	Santa Rosa de Lima
1815	2806404	SE	Santana do São Francisco
1816	2806602	SE	Santo Amaro das Brotas
1817	2806701	SE	São Cristóvão
1818	2806800	SE	São Domingos
1819	2806909	SE	São Francisco
1820	2807006	SE	São Miguel do Aleixo
1821	2807105	SE	Simão Dias
1822	2807204	SE	Siriri
1823	2807303	SE	Telha
1824	2807402	SE	Tobias Barreto
1825	2807501	SE	Tomar do Geru
1826	2807600	SE	Umbaúba
1827	2900108	BA	Abaíra
1828	2900207	BA	Abaré
1829	2900306	BA	Acajutiba
1830	2900355	BA	Adustina
1831	2900405	BA	Água Fria
1832	2900603	BA	Aiquara
1833	2900702	BA	Alagoinhas
1834	2900801	BA	Alcobaça
1835	2900900	BA	Almadina
1836	2901007	BA	Amargosa
1837	2901106	BA	Amélia Rodrigues
1838	2901155	BA	América Dourada
1839	2901205	BA	Anagé
1840	2901304	BA	Andaraí
1841	2901353	BA	Andorinha
1842	2901403	BA	Angical
1843	2901502	BA	Anguera
1844	2901601	BA	Antas
1845	2901700	BA	Antônio Cardoso
1846	2901809	BA	Antônio Gonçalves
1847	2901908	BA	Aporá
1848	2901957	BA	Apuarema
1849	2902054	BA	Araças
1850	2902005	BA	Aracatu
1851	2902104	BA	Araci
1852	2902203	BA	Aramari
1853	2902252	BA	Arataca
1854	2902302	BA	Aratuípe
1855	2902401	BA	Aurelino Leal
1856	2902500	BA	Baianópolis
1857	2902609	BA	Baixa Grande
1858	2902658	BA	Banzaê
1859	2902708	BA	Barra
1860	2902807	BA	Barra da Estiva
1861	2902906	BA	Barra do Choça
1862	2903003	BA	Barra do Mendes
1863	2903102	BA	Barra do Rocha
1864	2903201	BA	Barreiras
1865	2903235	BA	Barro Alto
1866	2903300	BA	Barro Preto
1867	2903276	BA	Barrocas
1868	2903409	BA	Belmonte
1869	2903508	BA	Belo Campo
1870	2903607	BA	Biritinga
1871	2903706	BA	Boa Nova
1872	2903805	BA	Boa Vista do Tupim
1873	2903904	BA	Bom Jesus da Lapa
1874	2903953	BA	Bom Jesus da Serra
1875	2904001	BA	Boninal
1876	2904050	BA	Bonito
1877	2904100	BA	Boquira
1878	2904209	BA	Botuporã
1879	2904308	BA	Brejões
1880	2904407	BA	Brejolândia
1881	2904506	BA	Brotas de Macaúbas
1882	2904605	BA	Brumado
1883	2904704	BA	Buerarema
1884	2904753	BA	Buritirama
1885	2904803	BA	Caatiba
1886	2904852	BA	Cabaceiras do Paraguaçu
1887	2904902	BA	Cachoeira
1888	2905008	BA	Caculé
1889	2905107	BA	Caém
1890	2905156	BA	Caetanos
1891	2905206	BA	Caetité
1892	2905305	BA	Cafarnaum
1893	2905404	BA	Cairu
1894	2905503	BA	Caldeirão Grande
1895	2905602	BA	Camacan
1896	2905701	BA	Camaçari
1897	2905800	BA	Camamu
1898	2905909	BA	Campo Alegre de Lourdes
1899	2906006	BA	Campo Formoso
1900	2906105	BA	Canápolis
1901	2906204	BA	Canarana
1902	2906303	BA	Canavieiras
1903	2906402	BA	Candeal
1904	2906501	BA	Candeias
1905	2906600	BA	Candiba
1906	2906709	BA	Cândido Sales
1907	2906808	BA	Cansanção
1908	2906824	BA	Canudos
1909	2906857	BA	Capela do Alto Alegre
1910	2906873	BA	Capim Grosso
1911	2906899	BA	Caraíbas
1912	2906907	BA	Caravelas
1913	2907004	BA	Cardeal da Silva
1914	2907103	BA	Carinhanha
1915	2907202	BA	Casa Nova
1916	2907301	BA	Castro Alves
1917	2907400	BA	Catolândia
1918	2907509	BA	Catu
1919	2907558	BA	Caturama
1920	2907608	BA	Central
1921	2907707	BA	Chorrochó
1922	2907806	BA	Cícero Dantas
1923	2907905	BA	Cipó
1924	2908002	BA	Coaraci
1925	2908101	BA	Cocos
1926	2908200	BA	Conceição da Feira
1927	2908309	BA	Conceição do Almeida
1928	2908408	BA	Conceição do Coité
1929	2908507	BA	Conceição do Jacuípe
1930	2908606	BA	Conde
1931	2908705	BA	Condeúba
1932	2908804	BA	Contendas do Sincorá
1933	2908903	BA	Coração de Maria
1934	2909000	BA	Cordeiros
1935	2909109	BA	Coribe
1936	2909208	BA	Coronel João Sá
1937	2909307	BA	Correntina
1938	2909406	BA	Cotegipe
1939	2909505	BA	Cravolândia
1940	2909604	BA	Crisópolis
1941	2909703	BA	Cristópolis
1942	2909802	BA	Cruz das Almas
1943	2909901	BA	Curaçá
1944	2910008	BA	Dário Meira
1945	2910057	BA	Dias d'Ávila
1946	2910107	BA	Dom Basílio
1947	2910206	BA	Dom Macedo Costa
1948	2910305	BA	Elísio Medrado
1949	2910404	BA	Encruzilhada
1950	2910503	BA	Entre Rios
1951	2900504	BA	Érico Cardoso
1952	2910602	BA	Esplanada
1953	2910701	BA	Euclides da Cunha
1954	2910727	BA	Eunápolis
1955	2910750	BA	Fátima
1956	2910776	BA	Feira da Mata
1957	2910800	BA	Feira de Santana
1958	2910859	BA	Filadélfia
1959	2910909	BA	Firmino Alves
1960	2911006	BA	Floresta Azul
1961	2911105	BA	Formosa do Rio Preto
1962	2911204	BA	Gandu
1963	2911253	BA	Gavião
1964	2911303	BA	Gentio do Ouro
1965	2911402	BA	Glória
1966	2911501	BA	Gongogi
1967	2911600	BA	Governador Mangabeira
1968	2911659	BA	Guajeru
1969	2911709	BA	Guanambi
1970	2911808	BA	Guaratinga
1971	2911857	BA	Heliópolis
1972	2911907	BA	Iaçu
1973	2912004	BA	Ibiassucê
1974	2912103	BA	Ibicaraí
1975	2912202	BA	Ibicoara
1976	2912301	BA	Ibicuí
1977	2912400	BA	Ibipeba
1978	2912509	BA	Ibipitanga
1979	2912608	BA	Ibiquera
1980	2912707	BA	Ibirapitanga
1981	2912806	BA	Ibirapuã
1982	2912905	BA	Ibirataia
1983	2913002	BA	Ibitiara
1984	2913101	BA	Ibititá
1985	2913200	BA	Ibotirama
1986	2913309	BA	Ichu
1987	2913408	BA	Igaporã
1988	2913457	BA	Igrapiúna
1989	2913507	BA	Iguaí
1990	2913606	BA	Ilhéus
1991	2913705	BA	Inhambupe
1992	2913804	BA	Ipecaetá
1993	2913903	BA	Ipiaú
1994	2914000	BA	Ipirá
1995	2914109	BA	Ipupiara
1996	2914208	BA	Irajuba
1997	2914307	BA	Iramaia
1998	2914406	BA	Iraquara
1999	2914505	BA	Irará
2000	2914604	BA	Irecê
2001	2914653	BA	Itabela
2002	2914703	BA	Itaberaba
2003	2914802	BA	Itabuna
2004	2914901	BA	Itacaré
2005	2915007	BA	Itaeté
2006	2915106	BA	Itagi
2007	2915205	BA	Itagibá
2008	2915304	BA	Itagimirim
2009	2915353	BA	Itaguaçu da Bahia
2010	2915403	BA	Itaju do Colônia
2011	2915502	BA	Itajuípe
2012	2915601	BA	Itamaraju
2013	2915700	BA	Itamari
2014	2915809	BA	Itambé
2015	2915908	BA	Itanagra
2016	2916005	BA	Itanhém
2017	2916104	BA	Itaparica
2018	2916203	BA	Itapé
2019	2916302	BA	Itapebi
2020	2916401	BA	Itapetinga
2021	2916500	BA	Itapicuru
2022	2916609	BA	Itapitanga
2023	2916708	BA	Itaquara
2024	2916807	BA	Itarantim
2025	2916856	BA	Itatim
2026	2916906	BA	Itiruçu
2027	2917003	BA	Itiúba
2028	2917102	BA	Itororó
2029	2917201	BA	Ituaçu
2030	2917300	BA	Ituberá
2031	2917334	BA	Iuiú
2032	2917359	BA	Jaborandi
2033	2917409	BA	Jacaraci
2034	2917508	BA	Jacobina
2035	2917607	BA	Jaguaquara
2036	2917706	BA	Jaguarari
2037	2917805	BA	Jaguaripe
2038	2917904	BA	Jandaíra
2039	2918001	BA	Jequié
2040	2918100	BA	Jeremoabo
2041	2918209	BA	Jiquiriçá
2042	2918308	BA	Jitaúna
2043	2918357	BA	João Dourado
2044	2918407	BA	Juazeiro
2045	2918456	BA	Jucuruçu
2046	2918506	BA	Jussara
2047	2918555	BA	Jussari
2048	2918605	BA	Jussiape
2049	2918704	BA	Lafaiete Coutinho
2050	2918753	BA	Lagoa Real
2051	2918803	BA	Laje
2052	2918902	BA	Lajedão
2053	2919009	BA	Lajedinho
2054	2919058	BA	Lajedo do Tabocal
2055	2919108	BA	Lamarão
2056	2919157	BA	Lapão
2057	2919207	BA	Lauro de Freitas
2058	2919306	BA	Lençóis
2059	2919405	BA	Licínio de Almeida
2060	2919504	BA	Livramento de Nossa Senhora
2061	2919553	BA	Luís Eduardo Magalhães
2062	2919603	BA	Macajuba
2063	2919702	BA	Macarani
2064	2919801	BA	Macaúbas
2065	2919900	BA	Macururé
2066	2919926	BA	Madre de Deus
2067	2919959	BA	Maetinga
2068	2920007	BA	Maiquinique
2069	2920106	BA	Mairi
2070	2920205	BA	Malhada
2071	2920304	BA	Malhada de Pedras
2072	2920403	BA	Manoel Vitorino
2073	2920452	BA	Mansidão
2074	2920502	BA	Maracás
2075	2920601	BA	Maragogipe
2076	2920700	BA	Maraú
2077	2920809	BA	Marcionílio Souza
2078	2920908	BA	Mascote
2079	2921005	BA	Mata de São João
2080	2921054	BA	Matina
2081	2921104	BA	Medeiros Neto
2082	2921203	BA	Miguel Calmon
2083	2921302	BA	Milagres
2084	2921401	BA	Mirangaba
2085	2921450	BA	Mirante
2086	2921500	BA	Monte Santo
2087	2921609	BA	Morpará
2088	2921708	BA	Morro do Chapéu
2089	2921807	BA	Mortugaba
2090	2921906	BA	Mucugê
2091	2922003	BA	Mucuri
2092	2922052	BA	Mulungu do Morro
2093	2922102	BA	Mundo Novo
2094	2922201	BA	Muniz Ferreira
2095	2922250	BA	Muquém de São Francisco
2096	2922300	BA	Muritiba
2097	2922409	BA	Mutuípe
2098	2922508	BA	Nazaré
2099	2922607	BA	Nilo Peçanha
2100	2922656	BA	Nordestina
2101	2922706	BA	Nova Canaã
2102	2922730	BA	Nova Fátima
2103	2922755	BA	Nova Ibiá
2104	2922805	BA	Nova Itarana
2105	2922854	BA	Nova Redenção
2106	2922904	BA	Nova Soure
2107	2923001	BA	Nova Viçosa
2108	2923035	BA	Novo Horizonte
2109	2923050	BA	Novo Triunfo
2110	2923100	BA	Olindina
2111	2923209	BA	Oliveira dos Brejinhos
2112	2923308	BA	Ouriçangas
2113	2923357	BA	Ourolândia
2114	2923407	BA	Palmas de Monte Alto
2115	2923506	BA	Palmeiras
2116	2923605	BA	Paramirim
2117	2923704	BA	Paratinga
2118	2923803	BA	Paripiranga
2119	2923902	BA	Pau Brasil
2120	2924009	BA	Paulo Afonso
2121	2924058	BA	Pé de Serra
2122	2924108	BA	Pedrão
2123	2924207	BA	Pedro Alexandre
2124	2924306	BA	Piatã
2125	2924405	BA	Pilão Arcado
2126	2924504	BA	Pindaí
2127	2924603	BA	Pindobaçu
2128	2924652	BA	Pintadas
2129	2924678	BA	Piraí do Norte
2130	2924702	BA	Piripá
2131	2924801	BA	Piritiba
2132	2924900	BA	Planaltino
2133	2925006	BA	Planalto
2134	2925105	BA	Poções
2135	2925204	BA	Pojuca
2136	2925253	BA	Ponto Novo
2137	2925303	BA	Porto Seguro
2138	2925402	BA	Potiraguá
2139	2925501	BA	Prado
2140	2925600	BA	Presidente Dutra
2141	2925709	BA	Presidente Jânio Quadros
2142	2925758	BA	Presidente Tancredo Neves
2143	2925808	BA	Queimadas
2144	2925907	BA	Quijingue
2145	2925931	BA	Quixabeira
2146	2925956	BA	Rafael Jambeiro
2147	2926004	BA	Remanso
2148	2926103	BA	Retirolândia
2149	2926202	BA	Riachão das Neves
2150	2926301	BA	Riachão do Jacuípe
2151	2926400	BA	Riacho de Santana
2152	2926509	BA	Ribeira do Amparo
2153	2926608	BA	Ribeira do Pombal
2154	2926657	BA	Ribeirão do Largo
2155	2926707	BA	Rio de Contas
2156	2926806	BA	Rio do Antônio
2157	2926905	BA	Rio do Pires
2158	2927002	BA	Rio Real
2159	2927101	BA	Rodelas
2160	2927200	BA	Ruy Barbosa
2161	2927309	BA	Salinas da Margarida
2162	2927408	BA	Salvador
2163	2927507	BA	Santa Bárbara
2164	2927606	BA	Santa Brígida
2165	2927705	BA	Santa Cruz Cabrália
2166	2927804	BA	Santa Cruz da Vitória
2167	2927903	BA	Santa Inês
2168	2928059	BA	Santa Luzia
2169	2928109	BA	Santa Maria da Vitória
2170	2928406	BA	Santa Rita de Cássia
2171	2928505	BA	Santa Teresinha
2172	2928000	BA	Santaluz
2173	2928208	BA	Santana
2174	2928307	BA	Santanópolis
2175	2928604	BA	Santo Amaro
2176	2928703	BA	Santo Antônio de Jesus
2177	2928802	BA	Santo Estêvão
2178	2928901	BA	São Desidério
2179	2928950	BA	São Domingos
2180	2929107	BA	São Felipe
2181	2929008	BA	São Félix
2182	2929057	BA	São Félix do Coribe
2183	2929206	BA	São Francisco do Conde
2184	2929255	BA	São Gabriel
2185	2929305	BA	São Gonçalo dos Campos
2186	2929354	BA	São José da Vitória
2187	2929370	BA	São José do Jacuípe
2188	2929404	BA	São Miguel das Matas
2189	2929503	BA	São Sebastião do Passé
2190	2929602	BA	Sapeaçu
2191	2929701	BA	Sátiro Dias
2192	2929750	BA	Saubara
2193	2929800	BA	Saúde
2194	2929909	BA	Seabra
2195	2930006	BA	Sebastião Laranjeiras
2196	2930105	BA	Senhor do Bonfim
2197	2930204	BA	Sento Sé
2198	2930154	BA	Serra do Ramalho
2199	2930303	BA	Serra Dourada
2200	2930402	BA	Serra Preta
2201	2930501	BA	Serrinha
2202	2930600	BA	Serrolândia
2203	2930709	BA	Simões Filho
2204	2930758	BA	Sítio do Mato
2205	2930766	BA	Sítio do Quinto
2206	2930774	BA	Sobradinho
2207	2930808	BA	Souto Soares
2208	2930907	BA	Tabocas do Brejo Velho
2209	2931004	BA	Tanhaçu
2210	2931053	BA	Tanque Novo
2211	2931103	BA	Tanquinho
2212	2931202	BA	Taperoá
2213	2931301	BA	Tapiramutá
2214	2931350	BA	Teixeira de Freitas
2215	2931400	BA	Teodoro Sampaio
2216	2931509	BA	Teofilândia
2217	2931608	BA	Teolândia
2218	2931707	BA	Terra Nova
2219	2931806	BA	Tremedal
2220	2931905	BA	Tucano
2221	2932002	BA	Uauá
2222	2932101	BA	Ubaíra
2223	2932200	BA	Ubaitaba
2224	2932309	BA	Ubatã
2225	2932408	BA	Uibaí
2226	2932457	BA	Umburanas
2227	2932507	BA	Una
2228	2932606	BA	Urandi
2229	2932705	BA	Uruçuca
2230	2932804	BA	Utinga
2231	2932903	BA	Valença
2232	2933000	BA	Valente
2233	2933059	BA	Várzea da Roça
2234	2933109	BA	Várzea do Poço
2235	2933158	BA	Várzea Nova
2236	2933174	BA	Varzedo
2237	2933208	BA	Vera Cruz
2238	2933257	BA	Vereda
2239	2933307	BA	Vitória da Conquista
2240	2933406	BA	Wagner
2241	2933455	BA	Wanderley
2242	2933505	BA	Wenceslau Guimarães
2243	2933604	BA	Xique-Xique
2244	3100104	MG	Abadia dos Dourados
2245	3100203	MG	Abaeté
2246	3100302	MG	Abre Campo
2247	3100401	MG	Acaiaca
2248	3100500	MG	Açucena
2249	3100609	MG	Água Boa
2250	3100708	MG	Água Comprida
2251	3100807	MG	Aguanil
2252	3100906	MG	Águas Formosas
2253	3101003	MG	Águas Vermelhas
2254	3101102	MG	Aimorés
2255	3101201	MG	Aiuruoca
2256	3101300	MG	Alagoa
2257	3101409	MG	Albertina
2258	3101508	MG	Além Paraíba
2259	3101607	MG	Alfenas
2260	3101631	MG	Alfredo Vasconcelos
2261	3101706	MG	Almenara
2262	3101805	MG	Alpercata
2263	3101904	MG	Alpinópolis
2264	3102001	MG	Alterosa
2265	3102050	MG	Alto Caparaó
2266	3153509	MG	Alto Jequitibá
2267	3102100	MG	Alto Rio Doce
2268	3102209	MG	Alvarenga
2269	3102308	MG	Alvinópolis
2270	3102407	MG	Alvorada de Minas
2271	3102506	MG	Amparo do Serra
2272	3102605	MG	Andradas
2273	3102803	MG	Andrelândia
2274	3102852	MG	Angelândia
2275	3102902	MG	Antônio Carlos
2276	3103009	MG	Antônio Dias
2277	3103108	MG	Antônio Prado de Minas
2278	3103207	MG	Araçaí
2279	3103306	MG	Aracitaba
2280	3103405	MG	Araçuaí
2281	3103504	MG	Araguari
2282	3103603	MG	Arantina
2283	3103702	MG	Araponga
2284	3103751	MG	Araporã
2285	3103801	MG	Arapuá
2286	3103900	MG	Araújos
2287	3104007	MG	Araxá
2288	3104106	MG	Arceburgo
2289	3104205	MG	Arcos
2290	3104304	MG	Areado
2291	3104403	MG	Argirita
2292	3104452	MG	Aricanduva
2293	3104502	MG	Arinos
2294	3104601	MG	Astolfo Dutra
2295	3104700	MG	Ataléia
2296	3104809	MG	Augusto de Lima
2297	3104908	MG	Baependi
2298	3105004	MG	Baldim
2299	3105103	MG	Bambuí
2300	3105202	MG	Bandeira
2301	3105301	MG	Bandeira do Sul
2302	3105400	MG	Barão de Cocais
2303	3105509	MG	Barão de Monte Alto
2304	3105608	MG	Barbacena
2305	3105707	MG	Barra Longa
2306	3105905	MG	Barroso
2307	3106002	MG	Bela Vista de Minas
2308	3106101	MG	Belmiro Braga
2309	3106200	MG	Belo Horizonte
2310	3106309	MG	Belo Oriente
2311	3106408	MG	Belo Vale
2312	3106507	MG	Berilo
2313	3106655	MG	Berizal
2314	3106606	MG	Bertópolis
2315	3106705	MG	Betim
2316	3106804	MG	Bias Fortes
2317	3106903	MG	Bicas
2318	3107000	MG	Biquinhas
2319	3107109	MG	Boa Esperança
2320	3107208	MG	Bocaina de Minas
2321	3107307	MG	Bocaiúva
2322	3107406	MG	Bom Despacho
2323	3107505	MG	Bom Jardim de Minas
2324	3107604	MG	Bom Jesus da Penha
2325	3107703	MG	Bom Jesus do Amparo
2326	3107802	MG	Bom Jesus do Galho
2327	3107901	MG	Bom Repouso
2328	3108008	MG	Bom Sucesso
2329	3108107	MG	Bonfim
2330	3108206	MG	Bonfinópolis de Minas
2331	3108255	MG	Bonito de Minas
2332	3108305	MG	Borda da Mata
2333	3108404	MG	Botelhos
2334	3108503	MG	Botumirim
2335	3108701	MG	Brás Pires
2336	3108552	MG	Brasilândia de Minas
2337	3108602	MG	Brasília de Minas
2338	3108909	MG	Brasópolis
2339	3108800	MG	Braúnas
2340	3109006	MG	Brumadinho
2341	3109105	MG	Bueno Brandão
2342	3109204	MG	Buenópolis
2343	3109253	MG	Bugre
2344	3109303	MG	Buritis
2345	3109402	MG	Buritizeiro
2346	3109451	MG	Cabeceira Grande
2347	3109501	MG	Cabo Verde
2348	3109600	MG	Cachoeira da Prata
2349	3109709	MG	Cachoeira de Minas
2350	3102704	MG	Cachoeira de Pajeú
2351	3109808	MG	Cachoeira Dourada
2352	3109907	MG	Caetanópolis
2353	3110004	MG	Caeté
2354	3110103	MG	Caiana
2355	3110202	MG	Cajuri
2356	3110301	MG	Caldas
2357	3110400	MG	Camacho
2358	3110509	MG	Camanducaia
2359	3110608	MG	Cambuí
2360	3110707	MG	Cambuquira
2361	3110806	MG	Campanário
2362	3110905	MG	Campanha
2363	3111002	MG	Campestre
2364	3111101	MG	Campina Verde
2365	3111150	MG	Campo Azul
2366	3111200	MG	Campo Belo
2367	3111309	MG	Campo do Meio
2368	3111408	MG	Campo Florido
2369	3111507	MG	Campos Altos
2370	3111606	MG	Campos Gerais
2371	3111903	MG	Cana Verde
2372	3111705	MG	Canaã
2373	3111804	MG	Canápolis
2374	3112000	MG	Candeias
2375	3112059	MG	Cantagalo
2376	3112109	MG	Caparaó
2377	3112208	MG	Capela Nova
2378	3112307	MG	Capelinha
2379	3112406	MG	Capetinga
2380	3112505	MG	Capim Branco
2381	3112604	MG	Capinópolis
2382	3112653	MG	Capitão Andrade
2383	3112703	MG	Capitão Enéas
2384	3112802	MG	Capitólio
2385	3112901	MG	Caputira
2386	3113008	MG	Caraí
2387	3113107	MG	Caranaíba
2388	3113206	MG	Carandaí
2389	3113305	MG	Carangola
2390	3113404	MG	Caratinga
2391	3113503	MG	Carbonita
2392	3113602	MG	Careaçu
2393	3113701	MG	Carlos Chagas
2394	3113800	MG	Carmésia
2395	3113909	MG	Carmo da Cachoeira
2396	3114006	MG	Carmo da Mata
2397	3114105	MG	Carmo de Minas
2398	3114204	MG	Carmo do Cajuru
2399	3114303	MG	Carmo do Paranaíba
2400	3114402	MG	Carmo do Rio Claro
2401	3114501	MG	Carmópolis de Minas
2402	3114550	MG	Carneirinho
2403	3114600	MG	Carrancas
2404	3114709	MG	Carvalhópolis
2405	3114808	MG	Carvalhos
2406	3114907	MG	Casa Grande
2407	3115003	MG	Cascalho Rico
2408	3115102	MG	Cássia
2409	3115300	MG	Cataguases
2410	3115359	MG	Catas Altas
2411	3115409	MG	Catas Altas da Noruega
2412	3115458	MG	Catuji
2413	3115474	MG	Catuti
2414	3115508	MG	Caxambu
2415	3115607	MG	Cedro do Abaeté
2416	3115706	MG	Central de Minas
2417	3115805	MG	Centralina
2418	3115904	MG	Chácara
2419	3116001	MG	Chalé
2420	3116100	MG	Chapada do Norte
2421	3116159	MG	Chapada Gaúcha
2422	3116209	MG	Chiador
2423	3116308	MG	Cipotânea
2424	3116407	MG	Claraval
2425	3116506	MG	Claro dos Poções
2426	3116605	MG	Cláudio
2427	3116704	MG	Coimbra
2428	3116803	MG	Coluna
2429	3116902	MG	Comendador Gomes
2430	3117009	MG	Comercinho
2431	3117108	MG	Conceição da Aparecida
2432	3115201	MG	Conceição da Barra de Minas
2433	3117306	MG	Conceição das Alagoas
2434	3117207	MG	Conceição das Pedras
2435	3117405	MG	Conceição de Ipanema
2436	3117504	MG	Conceição do Mato Dentro
2437	3117603	MG	Conceição do Pará
2438	3117702	MG	Conceição do Rio Verde
2439	3117801	MG	Conceição dos Ouros
2440	3117836	MG	Cônego Marinho
2441	3117876	MG	Confins
2442	3117900	MG	Congonhal
2443	3118007	MG	Congonhas
2444	3118106	MG	Congonhas do Norte
2445	3118205	MG	Conquista
2446	3118304	MG	Conselheiro Lafaiete
2447	3118403	MG	Conselheiro Pena
2448	3118502	MG	Consolação
2449	3118601	MG	Contagem
2450	3118700	MG	Coqueiral
2451	3118809	MG	Coração de Jesus
2452	3118908	MG	Cordisburgo
2453	3119005	MG	Cordislândia
2454	3119104	MG	Corinto
2455	3119203	MG	Coroaci
2456	3119302	MG	Coromandel
2457	3119401	MG	Coronel Fabriciano
2458	3119500	MG	Coronel Murta
2459	3119609	MG	Coronel Pacheco
2460	3119708	MG	Coronel Xavier Chaves
2461	3119807	MG	Córrego Danta
2462	3119906	MG	Córrego do Bom Jesus
2463	3119955	MG	Córrego Fundo
2464	3120003	MG	Córrego Novo
2465	3120102	MG	Couto de Magalhães de Minas
2466	3120151	MG	Crisólita
2467	3120201	MG	Cristais
2468	3120300	MG	Cristália
2469	3120409	MG	Cristiano Otoni
2470	3120508	MG	Cristina
2471	3120607	MG	Crucilândia
2472	3120706	MG	Cruzeiro da Fortaleza
2473	3120805	MG	Cruzília
2474	3120839	MG	Cuparaque
2475	3120870	MG	Curral de Dentro
2476	3120904	MG	Curvelo
2477	3121001	MG	Datas
2478	3121100	MG	Delfim Moreira
2479	3121209	MG	Delfinópolis
2480	3121258	MG	Delta
2481	3121308	MG	Descoberto
2482	3121407	MG	Desterro de Entre Rios
2483	3121506	MG	Desterro do Melo
2484	3121605	MG	Diamantina
2485	3121704	MG	Diogo de Vasconcelos
2486	3121803	MG	Dionísio
2487	3121902	MG	Divinésia
2488	3122009	MG	Divino
2489	3122108	MG	Divino das Laranjeiras
2490	3122207	MG	Divinolândia de Minas
2491	3122306	MG	Divinópolis
2492	3122355	MG	Divisa Alegre
2493	3122405	MG	Divisa Nova
2494	3122454	MG	Divisópolis
2495	3122470	MG	Dom Bosco
2496	3122504	MG	Dom Cavati
2497	3122603	MG	Dom Joaquim
2498	3122702	MG	Dom Silvério
2499	3122801	MG	Dom Viçoso
2500	3122900	MG	Dona Eusébia
2501	3123007	MG	Dores de Campos
2502	3123106	MG	Dores de Guanhães
2503	3123205	MG	Dores do Indaiá
2504	3123304	MG	Dores do Turvo
2505	3123403	MG	Doresópolis
2506	3123502	MG	Douradoquara
2507	3123528	MG	Durandé
2508	3123601	MG	Elói Mendes
2509	3123700	MG	Engenheiro Caldas
2510	3123809	MG	Engenheiro Navarro
2511	3123858	MG	Entre Folhas
2512	3123908	MG	Entre Rios de Minas
2513	3124005	MG	Ervália
2514	3124104	MG	Esmeraldas
2515	3124203	MG	Espera Feliz
2516	3124302	MG	Espinosa
2517	3124401	MG	Espírito Santo do Dourado
2518	3124500	MG	Estiva
2519	3124609	MG	Estrela Dalva
2520	3124708	MG	Estrela do Indaiá
2521	3124807	MG	Estrela do Sul
2522	3124906	MG	Eugenópolis
2523	3125002	MG	Ewbank da Câmara
2524	3125101	MG	Extrema
2525	3125200	MG	Fama
2526	3125309	MG	Faria Lemos
2527	3125408	MG	Felício dos Santos
2528	3125606	MG	Felisburgo
2529	3125705	MG	Felixlândia
2530	3125804	MG	Fernandes Tourinho
2531	3125903	MG	Ferros
2532	3125952	MG	Fervedouro
2533	3126000	MG	Florestal
2534	3126109	MG	Formiga
2535	3126208	MG	Formoso
2536	3126307	MG	Fortaleza de Minas
2537	3126406	MG	Fortuna de Minas
2538	3126505	MG	Francisco Badaró
2539	3126604	MG	Francisco Dumont
2540	3126703	MG	Francisco Sá
2541	3126752	MG	Franciscópolis
2542	3126802	MG	Frei Gaspar
2543	3126901	MG	Frei Inocêncio
2544	3126950	MG	Frei Lagonegro
2545	3127008	MG	Fronteira
2546	3127057	MG	Fronteira dos Vales
2547	3127073	MG	Fruta de Leite
2548	3127107	MG	Frutal
2549	3127206	MG	Funilândia
2550	3127305	MG	Galiléia
2551	3127339	MG	Gameleiras
2552	3127354	MG	Glaucilândia
2553	3127370	MG	Goiabeira
2554	3127388	MG	Goianá
2555	3127404	MG	Gonçalves
2556	3127503	MG	Gonzaga
2557	3127602	MG	Gouveia
2558	3127701	MG	Governador Valadares
2559	3127800	MG	Grão Mogol
2560	3127909	MG	Grupiara
2561	3128006	MG	Guanhães
2562	3128105	MG	Guapé
2563	3128204	MG	Guaraciaba
2564	3128253	MG	Guaraciama
2565	3128303	MG	Guaranésia
2566	3128402	MG	Guarani
2567	3128501	MG	Guarará
2568	3128600	MG	Guarda-Mor
2569	3128709	MG	Guaxupé
2570	3128808	MG	Guidoval
2571	3128907	MG	Guimarânia
2572	3129004	MG	Guiricema
2573	3129103	MG	Gurinhatã
2574	3129202	MG	Heliodora
2575	3129301	MG	Iapu
2576	3129400	MG	Ibertioga
2577	3129509	MG	Ibiá
2578	3129608	MG	Ibiaí
2579	3129657	MG	Ibiracatu
2580	3129707	MG	Ibiraci
2581	3129806	MG	Ibirité
2582	3129905	MG	Ibitiúra de Minas
2583	3130002	MG	Ibituruna
2584	3130051	MG	Icaraí de Minas
2585	3130101	MG	Igarapé
2586	3130200	MG	Igaratinga
2587	3130309	MG	Iguatama
2588	3130408	MG	Ijaci
2589	3130507	MG	Ilicínea
2590	3130556	MG	Imbé de Minas
2591	3130606	MG	Inconfidentes
2592	3130655	MG	Indaiabira
2593	3130705	MG	Indianópolis
2594	3130804	MG	Ingaí
2595	3130903	MG	Inhapim
2596	3131000	MG	Inhaúma
2597	3131109	MG	Inimutaba
2598	3131158	MG	Ipaba
2599	3131208	MG	Ipanema
2600	3131307	MG	Ipatinga
2601	3131406	MG	Ipiaçu
2602	3131505	MG	Ipuiúna
2603	3131604	MG	Iraí de Minas
2604	3131703	MG	Itabira
2605	3131802	MG	Itabirinha
2606	3131901	MG	Itabirito
2607	3132008	MG	Itacambira
2608	3132107	MG	Itacarambi
2609	3132206	MG	Itaguara
2610	3132305	MG	Itaipé
2611	3132404	MG	Itajubá
2612	3132503	MG	Itamarandiba
2613	3132602	MG	Itamarati de Minas
2614	3132701	MG	Itambacuri
2615	3132800	MG	Itambé do Mato Dentro
2616	3132909	MG	Itamogi
2617	3133006	MG	Itamonte
2618	3133105	MG	Itanhandu
2619	3133204	MG	Itanhomi
2620	3133303	MG	Itaobim
2621	3133402	MG	Itapagipe
2622	3133501	MG	Itapecerica
2623	3133600	MG	Itapeva
2624	3133709	MG	Itatiaiuçu
2625	3133758	MG	Itaú de Minas
2626	3133808	MG	Itaúna
2627	3133907	MG	Itaverava
2628	3134004	MG	Itinga
2629	3134103	MG	Itueta
2630	3134202	MG	Ituiutaba
2631	3134301	MG	Itumirim
2632	3134400	MG	Iturama
2633	3134509	MG	Itutinga
2634	3134608	MG	Jaboticatubas
2635	3134707	MG	Jacinto
2636	3134806	MG	Jacuí
2637	3134905	MG	Jacutinga
2638	3135001	MG	Jaguaraçu
2639	3135050	MG	Jaíba
2640	3135076	MG	Jampruca
2641	3135100	MG	Janaúba
2642	3135209	MG	Januária
2643	3135308	MG	Japaraíba
2644	3135357	MG	Japonvar
2645	3135407	MG	Jeceaba
2646	3135456	MG	Jenipapo de Minas
2647	3135506	MG	Jequeri
2648	3135605	MG	Jequitaí
2649	3135704	MG	Jequitibá
2650	3135803	MG	Jequitinhonha
2651	3135902	MG	Jesuânia
2652	3136009	MG	Joaíma
2653	3136108	MG	Joanésia
2654	3136207	MG	João Monlevade
2655	3136306	MG	João Pinheiro
2656	3136405	MG	Joaquim Felício
2657	3136504	MG	Jordânia
2658	3136520	MG	José Gonçalves de Minas
2659	3136553	MG	José Raydan
2660	3136579	MG	Josenópolis
2661	3136652	MG	Juatuba
2662	3136702	MG	Juiz de Fora
2663	3136801	MG	Juramento
2664	3136900	MG	Juruaia
2665	3136959	MG	Juvenília
2666	3137007	MG	Ladainha
2667	3137106	MG	Lagamar
2668	3137205	MG	Lagoa da Prata
2669	3137304	MG	Lagoa dos Patos
2670	3137403	MG	Lagoa Dourada
2671	3137502	MG	Lagoa Formosa
2672	3137536	MG	Lagoa Grande
2673	3137601	MG	Lagoa Santa
2674	3137700	MG	Lajinha
2675	3137809	MG	Lambari
2676	3137908	MG	Lamim
2677	3138005	MG	Laranjal
2678	3138104	MG	Lassance
2679	3138203	MG	Lavras
2680	3138302	MG	Leandro Ferreira
2681	3138351	MG	Leme do Prado
2682	3138401	MG	Leopoldina
2683	3138500	MG	Liberdade
2684	3138609	MG	Lima Duarte
2685	3138625	MG	Limeira do Oeste
2686	3138658	MG	Lontra
2687	3138674	MG	Luisburgo
2688	3138682	MG	Luislândia
2689	3138708	MG	Luminárias
2690	3138807	MG	Luz
2691	3138906	MG	Machacalis
2692	3139003	MG	Machado
2693	3139102	MG	Madre de Deus de Minas
2694	3139201	MG	Malacacheta
2695	3139250	MG	Mamonas
2696	3139300	MG	Manga
2697	3139409	MG	Manhuaçu
2698	3139508	MG	Manhumirim
2699	3139607	MG	Mantena
2700	3139805	MG	Mar de Espanha
2701	3139706	MG	Maravilhas
2702	3139904	MG	Maria da Fé
2703	3140001	MG	Mariana
2704	3140100	MG	Marilac
2705	3140159	MG	Mário Campos
2706	3140209	MG	Maripá de Minas
2707	3140308	MG	Marliéria
2708	3140407	MG	Marmelópolis
2709	3140506	MG	Martinho Campos
2710	3140530	MG	Martins Soares
2711	3140555	MG	Mata Verde
2712	3140605	MG	Materlândia
2713	3140704	MG	Mateus Leme
2714	3171501	MG	Mathias Lobato
2715	3140803	MG	Matias Barbosa
2716	3140852	MG	Matias Cardoso
2717	3140902	MG	Matipó
2718	3141009	MG	Mato Verde
2719	3141108	MG	Matozinhos
2720	3141207	MG	Matutina
2721	3141306	MG	Medeiros
2722	3141405	MG	Medina
2723	3141504	MG	Mendes Pimentel
2724	3141603	MG	Mercês
2725	3141702	MG	Mesquita
2726	3141801	MG	Minas Novas
2727	3141900	MG	Minduri
2728	3142007	MG	Mirabela
2729	3142106	MG	Miradouro
2730	3142205	MG	Miraí
2731	3142254	MG	Miravânia
2732	3142304	MG	Moeda
2733	3142403	MG	Moema
2734	3142502	MG	Monjolos
2735	3142601	MG	Monsenhor Paulo
2736	3142700	MG	Montalvânia
2737	3142809	MG	Monte Alegre de Minas
2738	3142908	MG	Monte Azul
2739	3143005	MG	Monte Belo
2740	3143104	MG	Monte Carmelo
2741	3143153	MG	Monte Formoso
2742	3143203	MG	Monte Santo de Minas
2743	3143401	MG	Monte Sião
2744	3143302	MG	Montes Claros
2745	3143450	MG	Montezuma
2746	3143500	MG	Morada Nova de Minas
2747	3143609	MG	Morro da Garça
2748	3143708	MG	Morro do Pilar
2749	3143807	MG	Munhoz
2750	3143906	MG	Muriaé
2751	3144003	MG	Mutum
2752	3144102	MG	Muzambinho
2753	3144201	MG	Nacip Raydan
2754	3144300	MG	Nanuque
2755	3144359	MG	Naque
2756	3144375	MG	Natalândia
2757	3144409	MG	Natércia
2758	3144508	MG	Nazareno
2759	3144607	MG	Nepomuceno
2760	3144656	MG	Ninheira
2761	3144672	MG	Nova Belém
2762	3144706	MG	Nova Era
2763	3144805	MG	Nova Lima
2764	3144904	MG	Nova Módica
2765	3145000	MG	Nova Ponte
2766	3145059	MG	Nova Porteirinha
2767	3145109	MG	Nova Resende
2768	3145208	MG	Nova Serrana
2769	3136603	MG	Nova União
2770	3145307	MG	Novo Cruzeiro
2771	3145356	MG	Novo Oriente de Minas
2772	3145372	MG	Novorizonte
2773	3145406	MG	Olaria
2774	3145455	MG	Olhos-d'Água
2775	3145505	MG	Olímpio Noronha
2776	3145604	MG	Oliveira
2777	3145703	MG	Oliveira Fortes
2778	3145802	MG	Onça de Pitangui
2779	3145851	MG	Oratórios
2780	3145877	MG	Orizânia
2781	3145901	MG	Ouro Branco
2782	3146008	MG	Ouro Fino
2783	3146107	MG	Ouro Preto
2784	3146206	MG	Ouro Verde de Minas
2785	3146255	MG	Padre Carvalho
2786	3146305	MG	Padre Paraíso
2787	3146552	MG	Pai Pedro
2788	3146404	MG	Paineiras
2789	3146503	MG	Pains
2790	3146602	MG	Paiva
2791	3146701	MG	Palma
2792	3146750	MG	Palmópolis
2793	3146909	MG	Papagaios
2794	3147105	MG	Pará de Minas
2795	3147006	MG	Paracatu
2796	3147204	MG	Paraguaçu
2797	3147303	MG	Paraisópolis
2798	3147402	MG	Paraopeba
2799	3147600	MG	Passa Quatro
2800	3147709	MG	Passa Tempo
2801	3147501	MG	Passabém
2802	3147808	MG	Passa-Vinte
2803	3147907	MG	Passos
2804	3147956	MG	Patis
2805	3148004	MG	Patos de Minas
2806	3148103	MG	Patrocínio
2807	3148202	MG	Patrocínio do Muriaé
2808	3148301	MG	Paula Cândido
2809	3148400	MG	Paulistas
2810	3148509	MG	Pavão
2811	3148608	MG	Peçanha
2812	3148707	MG	Pedra Azul
2813	3148756	MG	Pedra Bonita
2814	3148806	MG	Pedra do Anta
2815	3148905	MG	Pedra do Indaiá
2816	3149002	MG	Pedra Dourada
2817	3149101	MG	Pedralva
2818	3149150	MG	Pedras de Maria da Cruz
2819	3149200	MG	Pedrinópolis
2820	3149309	MG	Pedro Leopoldo
2821	3149408	MG	Pedro Teixeira
2822	3149507	MG	Pequeri
2823	3149606	MG	Pequi
2824	3149705	MG	Perdigão
2825	3149804	MG	Perdizes
2826	3149903	MG	Perdões
2827	3149952	MG	Periquito
2828	3150000	MG	Pescador
2829	3150109	MG	Piau
2830	3150158	MG	Piedade de Caratinga
2831	3150208	MG	Piedade de Ponte Nova
2832	3150307	MG	Piedade do Rio Grande
2833	3150406	MG	Piedade dos Gerais
2834	3150505	MG	Pimenta
2835	3150539	MG	Pingo-d'Água
2836	3150570	MG	Pintópolis
2837	3150604	MG	Piracema
2838	3150703	MG	Pirajuba
2839	3150802	MG	Piranga
2840	3150901	MG	Piranguçu
2841	3151008	MG	Piranguinho
2842	3151107	MG	Pirapetinga
2843	3151206	MG	Pirapora
2844	3151305	MG	Piraúba
2845	3151404	MG	Pitangui
2846	3151503	MG	Piumhi
2847	3151602	MG	Planura
2848	3151701	MG	Poço Fundo
2849	3151800	MG	Poços de Caldas
2850	3151909	MG	Pocrane
2851	3152006	MG	Pompéu
2852	3152105	MG	Ponte Nova
2853	3152131	MG	Ponto Chique
2854	3152170	MG	Ponto dos Volantes
2855	3152204	MG	Porteirinha
2856	3152303	MG	Porto Firme
2857	3152402	MG	Poté
2858	3152501	MG	Pouso Alegre
2859	3152600	MG	Pouso Alto
2860	3152709	MG	Prados
2861	3152808	MG	Prata
2862	3152907	MG	Pratápolis
2863	3153004	MG	Pratinha
2864	3153103	MG	Presidente Bernardes
2865	3153202	MG	Presidente Juscelino
2866	3153301	MG	Presidente Kubitschek
2867	3153400	MG	Presidente Olegário
2868	3153608	MG	Prudente de Morais
2869	3153707	MG	Quartel Geral
2870	3153806	MG	Queluzito
2871	3153905	MG	Raposos
2872	3154002	MG	Raul Soares
2873	3154101	MG	Recreio
2874	3154150	MG	Reduto
2875	3154200	MG	Resende Costa
2876	3154309	MG	Resplendor
2877	3154408	MG	Ressaquinha
2878	3154457	MG	Riachinho
2879	3154507	MG	Riacho dos Machados
2880	3154606	MG	Ribeirão das Neves
2881	3154705	MG	Ribeirão Vermelho
2882	3154804	MG	Rio Acima
2883	3154903	MG	Rio Casca
2884	3155108	MG	Rio do Prado
2885	3155009	MG	Rio Doce
2886	3155207	MG	Rio Espera
2887	3155306	MG	Rio Manso
2888	3155405	MG	Rio Novo
2889	3155504	MG	Rio Paranaíba
2890	3155603	MG	Rio Pardo de Minas
2891	3155702	MG	Rio Piracicaba
2892	3155801	MG	Rio Pomba
2893	3155900	MG	Rio Preto
2894	3156007	MG	Rio Vermelho
2895	3156106	MG	Ritápolis
2896	3156205	MG	Rochedo de Minas
2897	3156304	MG	Rodeiro
2898	3156403	MG	Romaria
2899	3156452	MG	Rosário da Limeira
2900	3156502	MG	Rubelita
2901	3156601	MG	Rubim
2902	3156700	MG	Sabará
2903	3156809	MG	Sabinópolis
2904	3156908	MG	Sacramento
2905	3157005	MG	Salinas
2906	3157104	MG	Salto da Divisa
2907	3157203	MG	Santa Bárbara
2908	3157252	MG	Santa Bárbara do Leste
2909	3157278	MG	Santa Bárbara do Monte Verde
2910	3157302	MG	Santa Bárbara do Tugúrio
2911	3157336	MG	Santa Cruz de Minas
2912	3157377	MG	Santa Cruz de Salinas
2913	3157401	MG	Santa Cruz do Escalvado
2914	3157500	MG	Santa Efigênia de Minas
2915	3157609	MG	Santa Fé de Minas
2916	3157658	MG	Santa Helena de Minas
2917	3157708	MG	Santa Juliana
2918	3157807	MG	Santa Luzia
2919	3157906	MG	Santa Margarida
2920	3158003	MG	Santa Maria de Itabira
2921	3158102	MG	Santa Maria do Salto
2922	3158201	MG	Santa Maria do Suaçuí
2923	3159209	MG	Santa Rita de Caldas
2924	3159407	MG	Santa Rita de Ibitipoca
2925	3159308	MG	Santa Rita de Jacutinga
2926	3159357	MG	Santa Rita de Minas
2927	3159506	MG	Santa Rita do Itueto
2928	3159605	MG	Santa Rita do Sapucaí
2929	3159704	MG	Santa Rosa da Serra
2930	3159803	MG	Santa Vitória
2931	3158300	MG	Santana da Vargem
2932	3158409	MG	Santana de Cataguases
2933	3158508	MG	Santana de Pirapama
2934	3158607	MG	Santana do Deserto
2935	3158706	MG	Santana do Garambéu
2936	3158805	MG	Santana do Jacaré
2937	3158904	MG	Santana do Manhuaçu
2938	3158953	MG	Santana do Paraíso
2939	3159001	MG	Santana do Riacho
2940	3159100	MG	Santana dos Montes
2941	3159902	MG	Santo Antônio do Amparo
2942	3160009	MG	Santo Antônio do Aventureiro
2943	3160108	MG	Santo Antônio do Grama
2944	3160207	MG	Santo Antônio do Itambé
2945	3160306	MG	Santo Antônio do Jacinto
2946	3160405	MG	Santo Antônio do Monte
2947	3160454	MG	Santo Antônio do Retiro
2948	3160504	MG	Santo Antônio do Rio Abaixo
2949	3160603	MG	Santo Hipólito
2950	3160702	MG	Santos Dumont
2951	3160801	MG	São Bento Abade
2952	3160900	MG	São Brás do Suaçuí
2953	3160959	MG	São Domingos das Dores
2954	3161007	MG	São Domingos do Prata
2955	3161056	MG	São Félix de Minas
2956	3161106	MG	São Francisco
2957	3161205	MG	São Francisco de Paula
2958	3161304	MG	São Francisco de Sales
2959	3161403	MG	São Francisco do Glória
2960	3161502	MG	São Geraldo
2961	3161601	MG	São Geraldo da Piedade
2962	3161650	MG	São Geraldo do Baixio
2963	3161700	MG	São Gonçalo do Abaeté
2964	3161809	MG	São Gonçalo do Pará
2965	3161908	MG	São Gonçalo do Rio Abaixo
2966	3125507	MG	São Gonçalo do Rio Preto
2967	3162005	MG	São Gonçalo do Sapucaí
2968	3162104	MG	São Gotardo
2969	3162203	MG	São João Batista do Glória
2970	3162252	MG	São João da Lagoa
2971	3162302	MG	São João da Mata
2972	3162401	MG	São João da Ponte
2973	3162450	MG	São João das Missões
2974	3162500	MG	São João del Rei
2975	3162559	MG	São João do Manhuaçu
2976	3162575	MG	São João do Manteninha
2977	3162609	MG	São João do Oriente
2978	3162658	MG	São João do Pacuí
2979	3162708	MG	São João do Paraíso
2980	3162807	MG	São João Evangelista
2981	3162906	MG	São João Nepomuceno
2982	3162922	MG	São Joaquim de Bicas
2983	3162948	MG	São José da Barra
2984	3162955	MG	São José da Lapa
2985	3163003	MG	São José da Safira
2986	3163102	MG	São José da Varginha
2987	3163201	MG	São José do Alegre
2988	3163300	MG	São José do Divino
2989	3163409	MG	São José do Goiabal
2990	3163508	MG	São José do Jacuri
2991	3163607	MG	São José do Mantimento
2992	3163706	MG	São Lourenço
2993	3163805	MG	São Miguel do Anta
2994	3163904	MG	São Pedro da União
2995	3164100	MG	São Pedro do Suaçuí
2996	3164001	MG	São Pedro dos Ferros
2997	3164209	MG	São Romão
2998	3164308	MG	São Roque de Minas
2999	3164407	MG	São Sebastião da Bela Vista
3000	3164431	MG	São Sebastião da Vargem Alegre
3001	3164472	MG	São Sebastião do Anta
3002	3164506	MG	São Sebastião do Maranhão
3003	3164605	MG	São Sebastião do Oeste
3004	3164704	MG	São Sebastião do Paraíso
3005	3164803	MG	São Sebastião do Rio Preto
3006	3164902	MG	São Sebastião do Rio Verde
3007	3165206	MG	São Thomé das Letras
3008	3165008	MG	São Tiago
3009	3165107	MG	São Tomás de Aquino
3010	3165305	MG	São Vicente de Minas
3011	3165404	MG	Sapucaí-Mirim
3012	3165503	MG	Sardoá
3013	3165537	MG	Sarzedo
3014	3165560	MG	Sem-Peixe
3015	3165578	MG	Senador Amaral
3016	3165602	MG	Senador Cortes
3017	3165701	MG	Senador Firmino
3018	3165800	MG	Senador José Bento
3019	3165909	MG	Senador Modestino Gonçalves
3020	3166006	MG	Senhora de Oliveira
3021	3166105	MG	Senhora do Porto
3022	3166204	MG	Senhora dos Remédios
3023	3166303	MG	Sericita
3024	3166402	MG	Seritinga
3025	3166501	MG	Serra Azul de Minas
3026	3166600	MG	Serra da Saudade
3027	3166808	MG	Serra do Salitre
3028	3166709	MG	Serra dos Aimorés
3029	3166907	MG	Serrania
3030	3166956	MG	Serranópolis de Minas
3031	3167004	MG	Serranos
3032	3167103	MG	Serro
3033	3167202	MG	Sete Lagoas
3034	3165552	MG	Setubinha
3035	3167301	MG	Silveirânia
3036	3167400	MG	Silvianópolis
3037	3167509	MG	Simão Pereira
3038	3167608	MG	Simonésia
3039	3167707	MG	Sobrália
3040	3167806	MG	Soledade de Minas
3041	3167905	MG	Tabuleiro
3042	3168002	MG	Taiobeiras
3043	3168051	MG	Taparuba
3044	3168101	MG	Tapira
3045	3168200	MG	Tapiraí
3046	3168309	MG	Taquaraçu de Minas
3047	3168408	MG	Tarumirim
3048	3168507	MG	Teixeiras
3049	3168606	MG	Teófilo Otoni
3050	3168705	MG	Timóteo
3051	3168804	MG	Tiradentes
3052	3168903	MG	Tiros
3053	3169000	MG	Tocantins
3054	3169059	MG	Tocos do Moji
3055	3169109	MG	Toledo
3056	3169208	MG	Tombos
3057	3169307	MG	Três Corações
3058	3169356	MG	Três Marias
3059	3169406	MG	Três Pontas
3060	3169505	MG	Tumiritinga
3061	3169604	MG	Tupaciguara
3062	3169703	MG	Turmalina
3063	3169802	MG	Turvolândia
3064	3169901	MG	Ubá
3065	3170008	MG	Ubaí
3066	3170057	MG	Ubaporanga
3067	3170107	MG	Uberaba
3068	3170206	MG	Uberlândia
3069	3170305	MG	Umburatiba
3070	3170404	MG	Unaí
3071	3170438	MG	União de Minas
3072	3170479	MG	Uruana de Minas
3073	3170503	MG	Urucânia
3074	3170529	MG	Urucuia
3075	3170578	MG	Vargem Alegre
3076	3170602	MG	Vargem Bonita
3077	3170651	MG	Vargem Grande do Rio Pardo
3078	3170701	MG	Varginha
3079	3170750	MG	Varjão de Minas
3080	3170800	MG	Várzea da Palma
3081	3170909	MG	Varzelândia
3082	3171006	MG	Vazante
3083	3171030	MG	Verdelândia
3084	3171071	MG	Veredinha
3085	3171105	MG	Veríssimo
3086	3171154	MG	Vermelho Novo
3087	3171204	MG	Vespasiano
3088	3171303	MG	Viçosa
3089	3171402	MG	Vieiras
3090	3171600	MG	Virgem da Lapa
3091	3171709	MG	Virgínia
3092	3171808	MG	Virginópolis
3093	3171907	MG	Virgolândia
3094	3172004	MG	Visconde do Rio Branco
3095	3172103	MG	Volta Grande
3096	3172202	MG	Wenceslau Braz
3097	3200102	ES	Afonso Cláudio
3098	3200169	ES	Água Doce do Norte
3099	3200136	ES	Águia Branca
3100	3200201	ES	Alegre
3101	3200300	ES	Alfredo Chaves
3102	3200359	ES	Alto Rio Novo
3103	3200409	ES	Anchieta
3104	3200508	ES	Apiacá
3105	3200607	ES	Aracruz
3106	3200706	ES	Atilio Vivacqua
3107	3200805	ES	Baixo Guandu
3108	3200904	ES	Barra de São Francisco
3109	3201001	ES	Boa Esperança
3110	3201100	ES	Bom Jesus do Norte
3111	3201159	ES	Brejetuba
3112	3201209	ES	Cachoeiro de Itapemirim
3113	3201308	ES	Cariacica
3114	3201407	ES	Castelo
3115	3201506	ES	Colatina
3116	3201605	ES	Conceição da Barra
3117	3201704	ES	Conceição do Castelo
3118	3201803	ES	Divino de São Lourenço
3119	3201902	ES	Domingos Martins
3120	3202009	ES	Dores do Rio Preto
3121	3202108	ES	Ecoporanga
3122	3202207	ES	Fundão
3123	3202256	ES	Governador Lindenberg
3124	3202306	ES	Guaçuí
3125	3202405	ES	Guarapari
3126	3202454	ES	Ibatiba
3127	3202504	ES	Ibiraçu
3128	3202553	ES	Ibitirama
3129	3202603	ES	Iconha
3130	3202652	ES	Irupi
3131	3202702	ES	Itaguaçu
3132	3202801	ES	Itapemirim
3133	3202900	ES	Itarana
3134	3203007	ES	Iúna
3135	3203056	ES	Jaguaré
3136	3203106	ES	Jerônimo Monteiro
3137	3203130	ES	João Neiva
3138	3203163	ES	Laranja da Terra
3139	3203205	ES	Linhares
3140	3203304	ES	Mantenópolis
3141	3203320	ES	Marataízes
3142	3203346	ES	Marechal Floriano
3143	3203353	ES	Marilândia
3144	3203403	ES	Mimoso do Sul
3145	3203502	ES	Montanha
3146	3203601	ES	Mucurici
3147	3203700	ES	Muniz Freire
3148	3203809	ES	Muqui
3149	3203908	ES	Nova Venécia
3150	3204005	ES	Pancas
3151	3204054	ES	Pedro Canário
3152	3204104	ES	Pinheiros
3153	3204203	ES	Piúma
3154	3204252	ES	Ponto Belo
3155	3204302	ES	Presidente Kennedy
3156	3204351	ES	Rio Bananal
3157	3204401	ES	Rio Novo do Sul
3158	3204500	ES	Santa Leopoldina
3159	3204559	ES	Santa Maria de Jetibá
3160	3204609	ES	Santa Teresa
3161	3204658	ES	São Domingos do Norte
3162	3204708	ES	São Gabriel da Palha
3163	3204807	ES	São José do Calçado
3164	3204906	ES	São Mateus
3165	3204955	ES	São Roque do Canaã
3166	3205002	ES	Serra
3167	3205010	ES	Sooretama
3168	3205036	ES	Vargem Alta
3169	3205069	ES	Venda Nova do Imigrante
3170	3205101	ES	Viana
3171	3205150	ES	Vila Pavão
3172	3205176	ES	Vila Valério
3173	3205200	ES	Vila Velha
3174	3205309	ES	Vitória
3175	3300100	RJ	Angra dos Reis
3176	3300159	RJ	Aperibé
3177	3300209	RJ	Araruama
3178	3300225	RJ	Areal
3179	3300233	RJ	Armação dos Búzios
3180	3300258	RJ	Arraial do Cabo
3181	3300308	RJ	Barra do Piraí
3182	3300407	RJ	Barra Mansa
3183	3300456	RJ	Belford Roxo
3184	3300506	RJ	Bom Jardim
3185	3300605	RJ	Bom Jesus do Itabapoana
3186	3300704	RJ	Cabo Frio
3187	3300803	RJ	Cachoeiras de Macacu
3188	3300902	RJ	Cambuci
3189	3301009	RJ	Campos dos Goytacazes
3190	3301108	RJ	Cantagalo
3191	3300936	RJ	Carapebus
3192	3301157	RJ	Cardoso Moreira
3193	3301207	RJ	Carmo
3194	3301306	RJ	Casimiro de Abreu
3195	3300951	RJ	Comendador Levy Gasparian
3196	3301405	RJ	Conceição de Macabu
3197	3301504	RJ	Cordeiro
3198	3301603	RJ	Duas Barras
3199	3301702	RJ	Duque de Caxias
3200	3301801	RJ	Engenheiro Paulo de Frontin
3201	3301850	RJ	Guapimirim
3202	3301876	RJ	Iguaba Grande
3203	3301900	RJ	Itaboraí
3204	3302007	RJ	Itaguaí
3205	3302056	RJ	Italva
3206	3302106	RJ	Itaocara
3207	3302205	RJ	Itaperuna
3208	3302254	RJ	Itatiaia
3209	3302270	RJ	Japeri
3210	3302304	RJ	Laje do Muriaé
3211	3302403	RJ	Macaé
3212	3302452	RJ	Macuco
3213	3302502	RJ	Magé
3214	3302601	RJ	Mangaratiba
3215	3302700	RJ	Maricá
3216	3302809	RJ	Mendes
3217	3302858	RJ	Mesquita
3218	3302908	RJ	Miguel Pereira
3219	3303005	RJ	Miracema
3220	3303104	RJ	Natividade
3221	3303203	RJ	Nilópolis
3222	3303302	RJ	Niterói
3223	3303401	RJ	Nova Friburgo
3224	3303500	RJ	Nova Iguaçu
3225	3303609	RJ	Paracambi
3226	3303708	RJ	Paraíba do Sul
3227	3303807	RJ	Paraty
3228	3303856	RJ	Paty do Alferes
3229	3303906	RJ	Petrópolis
3230	3303955	RJ	Pinheiral
3231	3304003	RJ	Piraí
3232	3304102	RJ	Porciúncula
3233	3304110	RJ	Porto Real
3234	3304128	RJ	Quatis
3235	3304144	RJ	Queimados
3236	3304151	RJ	Quissamã
3237	3304201	RJ	Resende
3238	3304300	RJ	Rio Bonito
3239	3304409	RJ	Rio Claro
3240	3304508	RJ	Rio das Flores
3241	3304524	RJ	Rio das Ostras
3242	3304557	RJ	Rio de Janeiro
3243	3304607	RJ	Santa Maria Madalena
3244	3304706	RJ	Santo Antônio de Pádua
3245	3304805	RJ	São Fidélis
3246	3304755	RJ	São Francisco de Itabapoana
3247	3304904	RJ	São Gonçalo
3248	3305000	RJ	São João da Barra
3249	3305109	RJ	São João de Meriti
3250	3305133	RJ	São José de Ubá
3251	3305158	RJ	São José do Vale do Rio Preto
3252	3305208	RJ	São Pedro da Aldeia
3253	3305307	RJ	São Sebastião do Alto
3254	3305406	RJ	Sapucaia
3255	3305505	RJ	Saquarema
3256	3305554	RJ	Seropédica
3257	3305604	RJ	Silva Jardim
3258	3305703	RJ	Sumidouro
3259	3305752	RJ	Tanguá
3260	3305802	RJ	Teresópolis
3261	3305901	RJ	Trajano de Moraes
3262	3306008	RJ	Três Rios
3263	3306107	RJ	Valença
3264	3306156	RJ	Varre-Sai
3265	3306206	RJ	Vassouras
3266	3306305	RJ	Volta Redonda
3267	3500105	SP	Adamantina
3268	3500204	SP	Adolfo
3269	3500303	SP	Aguaí
3270	3500402	SP	Águas da Prata
3271	3500501	SP	Águas de Lindóia
3272	3500550	SP	Águas de Santa Bárbara
3273	3500600	SP	Águas de São Pedro
3274	3500709	SP	Agudos
3275	3500758	SP	Alambari
3276	3500808	SP	Alfredo Marcondes
3277	3500907	SP	Altair
3278	3501004	SP	Altinópolis
3279	3501103	SP	Alto Alegre
3280	3501152	SP	Alumínio
3281	3501202	SP	Álvares Florence
3282	3501301	SP	Álvares Machado
3283	3501400	SP	Álvaro de Carvalho
3284	3501509	SP	Alvinlândia
3285	3501608	SP	Americana
3286	3501707	SP	Américo Brasiliense
3287	3501806	SP	Américo de Campos
3288	3501905	SP	Amparo
3289	3502002	SP	Analândia
3290	3502101	SP	Andradina
3291	3502200	SP	Angatuba
3292	3502309	SP	Anhembi
3293	3502408	SP	Anhumas
3294	3502507	SP	Aparecida
3295	3502606	SP	Aparecida d'Oeste
3296	3502705	SP	Apiaí
3297	3502754	SP	Araçariguama
3298	3502804	SP	Araçatuba
3299	3502903	SP	Araçoiaba da Serra
3300	3503000	SP	Aramina
3301	3503109	SP	Arandu
3302	3503158	SP	Arapeí
3303	3503208	SP	Araraquara
3304	3503307	SP	Araras
3305	3503356	SP	Arco-Íris
3306	3503406	SP	Arealva
3307	3503505	SP	Areias
3308	3503604	SP	Areiópolis
3309	3503703	SP	Ariranha
3310	3503802	SP	Artur Nogueira
3311	3503901	SP	Arujá
3312	3503950	SP	Aspásia
3313	3504008	SP	Assis
3314	3504107	SP	Atibaia
3315	3504206	SP	Auriflama
3316	3504305	SP	Avaí
3317	3504404	SP	Avanhandava
3318	3504503	SP	Avaré
3319	3504602	SP	Bady Bassitt
3320	3504701	SP	Balbinos
3321	3504800	SP	Bálsamo
3322	3504909	SP	Bananal
3323	3505005	SP	Barão de Antonina
3324	3505104	SP	Barbosa
3325	3505203	SP	Bariri
3326	3505302	SP	Barra Bonita
3327	3505351	SP	Barra do Chapéu
3328	3505401	SP	Barra do Turvo
3329	3505500	SP	Barretos
3330	3505609	SP	Barrinha
3331	3505708	SP	Barueri
3332	3505807	SP	Bastos
3333	3505906	SP	Batatais
3334	3506003	SP	Bauru
3335	3506102	SP	Bebedouro
3336	3506201	SP	Bento de Abreu
3337	3506300	SP	Bernardino de Campos
3338	3506359	SP	Bertioga
3339	3506409	SP	Bilac
3340	3506508	SP	Birigui
3341	3506607	SP	Biritiba-Mirim
3342	3506706	SP	Boa Esperança do Sul
3343	3506805	SP	Bocaina
3344	3506904	SP	Bofete
3345	3507001	SP	Boituva
3346	3507100	SP	Bom Jesus dos Perdões
3347	3507159	SP	Bom Sucesso de Itararé
3348	3507209	SP	Borá
3349	3507308	SP	Boracéia
3350	3507407	SP	Borborema
3351	3507456	SP	Borebi
3352	3507506	SP	Botucatu
3353	3507605	SP	Bragança Paulista
3354	3507704	SP	Braúna
3355	3507753	SP	Brejo Alegre
3356	3507803	SP	Brodowski
3357	3507902	SP	Brotas
3358	3508009	SP	Buri
3359	3508108	SP	Buritama
3360	3508207	SP	Buritizal
3361	3508306	SP	Cabrália Paulista
3362	3508405	SP	Cabreúva
3363	3508504	SP	Caçapava
3364	3508603	SP	Cachoeira Paulista
3365	3508702	SP	Caconde
3366	3508801	SP	Cafelândia
3367	3508900	SP	Caiabu
3368	3509007	SP	Caieiras
3369	3509106	SP	Caiuá
3370	3509205	SP	Cajamar
3371	3509254	SP	Cajati
3372	3509304	SP	Cajobi
3373	3509403	SP	Cajuru
3374	3509452	SP	Campina do Monte Alegre
3375	3509502	SP	Campinas
3376	3509601	SP	Campo Limpo Paulista
3377	3509700	SP	Campos do Jordão
3378	3509809	SP	Campos Novos Paulista
3379	3509908	SP	Cananéia
3380	3509957	SP	Canas
3381	3510005	SP	Cândido Mota
3382	3510104	SP	Cândido Rodrigues
3383	3510153	SP	Canitar
3384	3510203	SP	Capão Bonito
3385	3510302	SP	Capela do Alto
3386	3510401	SP	Capivari
3387	3510500	SP	Caraguatatuba
3388	3510609	SP	Carapicuíba
3389	3510708	SP	Cardoso
3390	3510807	SP	Casa Branca
3391	3510906	SP	Cássia dos Coqueiros
3392	3511003	SP	Castilho
3393	3511102	SP	Catanduva
3394	3511201	SP	Catiguá
3395	3511300	SP	Cedral
3396	3511409	SP	Cerqueira César
3397	3511508	SP	Cerquilho
3398	3511607	SP	Cesário Lange
3399	3511706	SP	Charqueada
3400	3557204	SP	Chavantes
3401	3511904	SP	Clementina
3402	3512001	SP	Colina
3403	3512100	SP	Colômbia
3404	3512209	SP	Conchal
3405	3512308	SP	Conchas
3406	3512407	SP	Cordeirópolis
3407	3512506	SP	Coroados
3408	3512605	SP	Coronel Macedo
3409	3512704	SP	Corumbataí
3410	3512803	SP	Cosmópolis
3411	3512902	SP	Cosmorama
3412	3513009	SP	Cotia
3413	3513108	SP	Cravinhos
3414	3513207	SP	Cristais Paulista
3415	3513306	SP	Cruzália
3416	3513405	SP	Cruzeiro
3417	3513504	SP	Cubatão
3418	3513603	SP	Cunha
3419	3513702	SP	Descalvado
3420	3513801	SP	Diadema
3421	3513850	SP	Dirce Reis
3422	3513900	SP	Divinolândia
3423	3514007	SP	Dobrada
3424	3514106	SP	Dois Córregos
3425	3514205	SP	Dolcinópolis
3426	3514304	SP	Dourado
3427	3514403	SP	Dracena
3428	3514502	SP	Duartina
3429	3514601	SP	Dumont
3430	3514700	SP	Echaporã
3431	3514809	SP	Eldorado
3432	3514908	SP	Elias Fausto
3433	3514924	SP	Elisiário
3434	3514957	SP	Embaúba
3435	3515004	SP	Embu
3436	3515103	SP	Embu-Guaçu
3437	3515129	SP	Emilianópolis
3438	3515152	SP	Engenheiro Coelho
3571	3526803	SP	Lençóis Paulista
3439	3515186	SP	Espírito Santo do Pinhal
3440	3515194	SP	Espírito Santo do Turvo
3441	3557303	SP	Estiva Gerbi
3442	3515301	SP	Estrela do Norte
3443	3515202	SP	Estrela d'Oeste
3444	3515350	SP	Euclides da Cunha Paulista
3445	3515400	SP	Fartura
3446	3515608	SP	Fernando Prestes
3447	3515509	SP	Fernandópolis
3448	3515657	SP	Fernão
3449	3515707	SP	Ferraz de Vasconcelos
3450	3515806	SP	Flora Rica
3451	3515905	SP	Floreal
3452	3516002	SP	Flórida Paulista
3453	3516101	SP	Florínia
3454	3516200	SP	Franca
3455	3516309	SP	Francisco Morato
3456	3516408	SP	Franco da Rocha
3457	3516507	SP	Gabriel Monteiro
3458	3516606	SP	Gália
3459	3516705	SP	Garça
3460	3516804	SP	Gastão Vidigal
3461	3516853	SP	Gavião Peixoto
3462	3516903	SP	General Salgado
3463	3517000	SP	Getulina
3464	3517109	SP	Glicério
3465	3517208	SP	Guaiçara
3466	3517307	SP	Guaimbê
3467	3517406	SP	Guaíra
3468	3517505	SP	Guapiaçu
3469	3517604	SP	Guapiara
3470	3517703	SP	Guará
3471	3517802	SP	Guaraçaí
3472	3517901	SP	Guaraci
3473	3518008	SP	Guarani d'Oeste
3474	3518107	SP	Guarantã
3475	3518206	SP	Guararapes
3476	3518305	SP	Guararema
3477	3518404	SP	Guaratinguetá
3478	3518503	SP	Guareí
3479	3518602	SP	Guariba
3480	3518701	SP	Guarujá
3481	3518800	SP	Guarulhos
3482	3518859	SP	Guatapará
3483	3518909	SP	Guzolândia
3484	3519006	SP	Herculândia
3485	3519055	SP	Holambra
3486	3519071	SP	Hortolândia
3487	3519105	SP	Iacanga
3488	3519204	SP	Iacri
3489	3519253	SP	Iaras
3490	3519303	SP	Ibaté
3491	3519402	SP	Ibirá
3492	3519501	SP	Ibirarema
3493	3519600	SP	Ibitinga
3494	3519709	SP	Ibiúna
3495	3519808	SP	Icém
3496	3519907	SP	Iepê
3497	3520004	SP	Igaraçu do Tietê
3498	3520103	SP	Igarapava
3499	3520202	SP	Igaratá
3500	3520301	SP	Iguape
3501	3520426	SP	Ilha Comprida
3502	3520442	SP	Ilha Solteira
3503	3520400	SP	Ilhabela
3504	3520509	SP	Indaiatuba
3505	3520608	SP	Indiana
3506	3520707	SP	Indiaporã
3507	3520806	SP	Inúbia Paulista
3508	3520905	SP	Ipaussu
3509	3521002	SP	Iperó
3510	3521101	SP	Ipeúna
3511	3521150	SP	Ipiguá
3512	3521200	SP	Iporanga
3513	3521309	SP	Ipuã
3514	3521408	SP	Iracemápolis
3515	3521507	SP	Irapuã
3516	3521606	SP	Irapuru
3517	3521705	SP	Itaberá
3518	3521804	SP	Itaí
3519	3521903	SP	Itajobi
3520	3522000	SP	Itaju
3521	3522109	SP	Itanhaém
3522	3522158	SP	Itaóca
3523	3522208	SP	Itapecerica da Serra
3524	3522307	SP	Itapetininga
3525	3522406	SP	Itapeva
3526	3522505	SP	Itapevi
3527	3522604	SP	Itapira
3528	3522653	SP	Itapirapuã Paulista
3529	3522703	SP	Itápolis
3530	3522802	SP	Itaporanga
3531	3522901	SP	Itapuí
3532	3523008	SP	Itapura
3533	3523107	SP	Itaquaquecetuba
3534	3523206	SP	Itararé
3535	3523305	SP	Itariri
3536	3523404	SP	Itatiba
3537	3523503	SP	Itatinga
3538	3523602	SP	Itirapina
3539	3523701	SP	Itirapuã
3540	3523800	SP	Itobi
3541	3523909	SP	Itu
3542	3524006	SP	Itupeva
3543	3524105	SP	Ituverava
3544	3524204	SP	Jaborandi
3545	3524303	SP	Jaboticabal
3546	3524402	SP	Jacareí
3547	3524501	SP	Jaci
3548	3524600	SP	Jacupiranga
3549	3524709	SP	Jaguariúna
3550	3524808	SP	Jales
3551	3524907	SP	Jambeiro
3552	3525003	SP	Jandira
3553	3525102	SP	Jardinópolis
3554	3525201	SP	Jarinu
3555	3525300	SP	Jaú
3556	3525409	SP	Jeriquara
3557	3525508	SP	Joanópolis
3558	3525607	SP	João Ramalho
3559	3525706	SP	José Bonifácio
3560	3525805	SP	Júlio Mesquita
3561	3525854	SP	Jumirim
3562	3525904	SP	Jundiaí
3563	3526001	SP	Junqueirópolis
3564	3526100	SP	Juquiá
3565	3526209	SP	Juquitiba
3566	3526308	SP	Lagoinha
3567	3526407	SP	Laranjal Paulista
3568	3526506	SP	Lavínia
3569	3526605	SP	Lavrinhas
3570	3526704	SP	Leme
3572	3526902	SP	Limeira
3573	3527009	SP	Lindóia
3574	3527108	SP	Lins
3575	3527207	SP	Lorena
3576	3527256	SP	Lourdes
3577	3527306	SP	Louveira
3578	3527405	SP	Lucélia
3579	3527504	SP	Lucianópolis
3580	3527603	SP	Luís Antônio
3581	3527702	SP	Luiziânia
3582	3527801	SP	Lupércio
3583	3527900	SP	Lutécia
3584	3528007	SP	Macatuba
3585	3528106	SP	Macaubal
3586	3528205	SP	Macedônia
3587	3528304	SP	Magda
3588	3528403	SP	Mairinque
3589	3528502	SP	Mairiporã
3590	3528601	SP	Manduri
3591	3528700	SP	Marabá Paulista
3592	3528809	SP	Maracaí
3593	3528858	SP	Marapoama
3594	3528908	SP	Mariápolis
3595	3529005	SP	Marília
3596	3529104	SP	Marinópolis
3597	3529203	SP	Martinópolis
3598	3529302	SP	Matão
3599	3529401	SP	Mauá
3600	3529500	SP	Mendonça
3601	3529609	SP	Meridiano
3602	3529658	SP	Mesópolis
3603	3529708	SP	Miguelópolis
3604	3529807	SP	Mineiros do Tietê
3605	3530003	SP	Mira Estrela
3606	3529906	SP	Miracatu
3607	3530102	SP	Mirandópolis
3608	3530201	SP	Mirante do Paranapanema
3609	3530300	SP	Mirassol
3610	3530409	SP	Mirassolândia
3611	3530508	SP	Mococa
3612	3530607	SP	Mogi das Cruzes
3613	3530706	SP	Mogi Guaçu
3614	3530805	SP	Moji Mirim
3615	3530904	SP	Mombuca
3616	3531001	SP	Monções
3617	3531100	SP	Mongaguá
3618	3531209	SP	Monte Alegre do Sul
3619	3531308	SP	Monte Alto
3620	3531407	SP	Monte Aprazível
3621	3531506	SP	Monte Azul Paulista
3622	3531605	SP	Monte Castelo
3623	3531803	SP	Monte Mor
3624	3531704	SP	Monteiro Lobato
3625	3531902	SP	Morro Agudo
3626	3532009	SP	Morungaba
3627	3532058	SP	Motuca
3628	3532108	SP	Murutinga do Sul
3629	3532157	SP	Nantes
3630	3532207	SP	Narandiba
3631	3532306	SP	Natividade da Serra
3632	3532405	SP	Nazaré Paulista
3633	3532504	SP	Neves Paulista
3634	3532603	SP	Nhandeara
3635	3532702	SP	Nipoã
3636	3532801	SP	Nova Aliança
3637	3532827	SP	Nova Campina
3638	3532843	SP	Nova Canaã Paulista
3639	3532868	SP	Nova Castilho
3640	3532900	SP	Nova Europa
3641	3533007	SP	Nova Granada
3642	3533106	SP	Nova Guataporanga
3643	3533205	SP	Nova Independência
3644	3533304	SP	Nova Luzitânia
3645	3533403	SP	Nova Odessa
3646	3533254	SP	Novais
3647	3533502	SP	Novo Horizonte
3648	3533601	SP	Nuporanga
3649	3533700	SP	Ocauçu
3650	3533809	SP	Óleo
3651	3533908	SP	Olímpia
3652	3534005	SP	Onda Verde
3653	3534104	SP	Oriente
3654	3534203	SP	Orindiúva
3655	3534302	SP	Orlândia
3656	3534401	SP	Osasco
3657	3534500	SP	Oscar Bressane
3658	3534609	SP	Osvaldo Cruz
3659	3534708	SP	Ourinhos
3660	3534807	SP	Ouro Verde
3661	3534757	SP	Ouroeste
3662	3534906	SP	Pacaembu
3663	3535002	SP	Palestina
3664	3535101	SP	Palmares Paulista
3665	3535200	SP	Palmeira d'Oeste
3666	3535309	SP	Palmital
3667	3535408	SP	Panorama
3668	3535507	SP	Paraguaçu Paulista
3669	3535606	SP	Paraibuna
3670	3535705	SP	Paraíso
3671	3535804	SP	Paranapanema
3672	3535903	SP	Paranapuã
3673	3536000	SP	Parapuã
3674	3536109	SP	Pardinho
3675	3536208	SP	Pariquera-Açu
3676	3536257	SP	Parisi
3677	3536307	SP	Patrocínio Paulista
3678	3536406	SP	Paulicéia
3679	3536505	SP	Paulínia
3680	3536570	SP	Paulistânia
3681	3536604	SP	Paulo de Faria
3682	3536703	SP	Pederneiras
3683	3536802	SP	Pedra Bela
3684	3536901	SP	Pedranópolis
3685	3537008	SP	Pedregulho
3686	3537107	SP	Pedreira
3687	3537156	SP	Pedrinhas Paulista
3688	3537206	SP	Pedro de Toledo
3689	3537305	SP	Penápolis
3690	3537404	SP	Pereira Barreto
3691	3537503	SP	Pereiras
3692	3537602	SP	Peruíbe
3693	3537701	SP	Piacatu
3694	3537800	SP	Piedade
3695	3537909	SP	Pilar do Sul
3696	3538006	SP	Pindamonhangaba
3697	3538105	SP	Pindorama
3698	3538204	SP	Pinhalzinho
3699	3538303	SP	Piquerobi
3700	3538501	SP	Piquete
3701	3538600	SP	Piracaia
3702	3538709	SP	Piracicaba
3703	3538808	SP	Piraju
3704	3538907	SP	Pirajuí
3705	3539004	SP	Pirangi
3706	3539103	SP	Pirapora do Bom Jesus
3707	3539202	SP	Pirapozinho
3708	3539301	SP	Pirassununga
3709	3539400	SP	Piratininga
3710	3539509	SP	Pitangueiras
3711	3539608	SP	Planalto
3712	3539707	SP	Platina
3713	3539806	SP	Poá
3714	3539905	SP	Poloni
3715	3540002	SP	Pompéia
3716	3540101	SP	Pongaí
3717	3540200	SP	Pontal
3718	3540259	SP	Pontalinda
3719	3540309	SP	Pontes Gestal
3720	3540408	SP	Populina
3721	3540507	SP	Porangaba
3722	3540606	SP	Porto Feliz
3723	3540705	SP	Porto Ferreira
3724	3540754	SP	Potim
3725	3540804	SP	Potirendaba
3726	3540853	SP	Pracinha
3727	3540903	SP	Pradópolis
3728	3541000	SP	Praia Grande
3729	3541059	SP	Pratânia
3730	3541109	SP	Presidente Alves
3731	3541208	SP	Presidente Bernardes
3732	3541307	SP	Presidente Epitácio
3733	3541406	SP	Presidente Prudente
3734	3541505	SP	Presidente Venceslau
3735	3541604	SP	Promissão
3736	3541653	SP	Quadra
3737	3541703	SP	Quatá
3738	3541802	SP	Queiroz
3739	3541901	SP	Queluz
3740	3542008	SP	Quintana
3741	3542107	SP	Rafard
3742	3542206	SP	Rancharia
3743	3542305	SP	Redenção da Serra
3744	3542404	SP	Regente Feijó
3745	3542503	SP	Reginópolis
3746	3542602	SP	Registro
3747	3542701	SP	Restinga
3748	3542800	SP	Ribeira
3749	3542909	SP	Ribeirão Bonito
3750	3543006	SP	Ribeirão Branco
3751	3543105	SP	Ribeirão Corrente
3752	3543204	SP	Ribeirão do Sul
3753	3543238	SP	Ribeirão dos Índios
3754	3543253	SP	Ribeirão Grande
3755	3543303	SP	Ribeirão Pires
3756	3543402	SP	Ribeirão Preto
3757	3543600	SP	Rifaina
3758	3543709	SP	Rincão
3759	3543808	SP	Rinópolis
3760	3543907	SP	Rio Claro
3761	3544004	SP	Rio das Pedras
3762	3544103	SP	Rio Grande da Serra
3763	3544202	SP	Riolândia
3764	3543501	SP	Riversul
3765	3544251	SP	Rosana
3766	3544301	SP	Roseira
3767	3544400	SP	Rubiácea
3768	3544509	SP	Rubinéia
3769	3544608	SP	Sabino
3770	3544707	SP	Sagres
3771	3544806	SP	Sales
3772	3544905	SP	Sales Oliveira
3773	3545001	SP	Salesópolis
3774	3545100	SP	Salmourão
3775	3545159	SP	Saltinho
3776	3545209	SP	Salto
3777	3545308	SP	Salto de Pirapora
3778	3545407	SP	Salto Grande
3779	3545506	SP	Sandovalina
3780	3545605	SP	Santa Adélia
3781	3545704	SP	Santa Albertina
3782	3545803	SP	Santa Bárbara d'Oeste
3783	3546009	SP	Santa Branca
3784	3546108	SP	Santa Clara d'Oeste
3785	3546207	SP	Santa Cruz da Conceição
3786	3546256	SP	Santa Cruz da Esperança
3787	3546306	SP	Santa Cruz das Palmeiras
3788	3546405	SP	Santa Cruz do Rio Pardo
3789	3546504	SP	Santa Ernestina
3790	3546603	SP	Santa Fé do Sul
3791	3546702	SP	Santa Gertrudes
3792	3546801	SP	Santa Isabel
3793	3546900	SP	Santa Lúcia
3794	3547007	SP	Santa Maria da Serra
3795	3547106	SP	Santa Mercedes
3796	3547502	SP	Santa Rita do Passa Quatro
3797	3547403	SP	Santa Rita d'Oeste
3798	3547601	SP	Santa Rosa de Viterbo
3799	3547650	SP	Santa Salete
3800	3547205	SP	Santana da Ponte Pensa
3801	3547304	SP	Santana de Parnaíba
3802	3547700	SP	Santo Anastácio
3803	3547809	SP	Santo André
3804	3547908	SP	Santo Antônio da Alegria
3805	3548005	SP	Santo Antônio de Posse
3806	3548054	SP	Santo Antônio do Aracanguá
3807	3548104	SP	Santo Antônio do Jardim
3808	3548203	SP	Santo Antônio do Pinhal
3809	3548302	SP	Santo Expedito
3810	3548401	SP	Santópolis do Aguapeí
3811	3548500	SP	Santos
3812	3548609	SP	São Bento do Sapucaí
3813	3548708	SP	São Bernardo do Campo
3814	3548807	SP	São Caetano do Sul
3815	3548906	SP	São Carlos
3816	3549003	SP	São Francisco
3817	3549102	SP	São João da Boa Vista
3818	3549201	SP	São João das Duas Pontes
3819	3549250	SP	São João de Iracema
3820	3549300	SP	São João do Pau d'Alho
3821	3549409	SP	São Joaquim da Barra
3822	3549508	SP	São José da Bela Vista
3823	3549607	SP	São José do Barreiro
3824	3549706	SP	São José do Rio Pardo
3825	3549805	SP	São José do Rio Preto
3826	3549904	SP	São José dos Campos
3827	3549953	SP	São Lourenço da Serra
3828	3550001	SP	São Luís do Paraitinga
3829	3550100	SP	São Manuel
3830	3550209	SP	São Miguel Arcanjo
3831	3550308	SP	São Paulo
3832	3550407	SP	São Pedro
3833	3550506	SP	São Pedro do Turvo
3834	3550605	SP	São Roque
3835	3550704	SP	São Sebastião
3836	3550803	SP	São Sebastião da Grama
3837	3550902	SP	São Simão
3838	3551009	SP	São Vicente
3839	3551108	SP	Sarapuí
3840	3551207	SP	Sarutaiá
3841	3551306	SP	Sebastianópolis do Sul
3842	3551405	SP	Serra Azul
3843	3551603	SP	Serra Negra
3844	3551504	SP	Serrana
3845	3551702	SP	Sertãozinho
3846	3551801	SP	Sete Barras
3847	3551900	SP	Severínia
3848	3552007	SP	Silveiras
3849	3552106	SP	Socorro
3850	3552205	SP	Sorocaba
3851	3552304	SP	Sud Mennucci
3852	3552403	SP	Sumaré
3853	3552551	SP	Suzanápolis
3854	3552502	SP	Suzano
3855	3552601	SP	Tabapuã
3856	3552700	SP	Tabatinga
3857	3552809	SP	Taboão da Serra
3858	3552908	SP	Taciba
3859	3553005	SP	Taguaí
3860	3553104	SP	Taiaçu
3861	3553203	SP	Taiúva
3862	3553302	SP	Tambaú
3863	3553401	SP	Tanabi
3864	3553500	SP	Tapiraí
3865	3553609	SP	Tapiratiba
3866	3553658	SP	Taquaral
3867	3553708	SP	Taquaritinga
3868	3553807	SP	Taquarituba
3869	3553856	SP	Taquarivaí
3870	3553906	SP	Tarabai
3871	3553955	SP	Tarumã
3872	3554003	SP	Tatuí
3873	3554102	SP	Taubaté
3874	3554201	SP	Tejupá
3875	3554300	SP	Teodoro Sampaio
3876	3554409	SP	Terra Roxa
3877	3554508	SP	Tietê
3878	3554607	SP	Timburi
3879	3554656	SP	Torre de Pedra
3880	3554706	SP	Torrinha
3881	3554755	SP	Trabiju
3882	3554805	SP	Tremembé
3883	3554904	SP	Três Fronteiras
3884	3554953	SP	Tuiuti
3885	3555000	SP	Tupã
3886	3555109	SP	Tupi Paulista
3887	3555208	SP	Turiúba
3888	3555307	SP	Turmalina
3889	3555356	SP	Ubarana
3890	3555406	SP	Ubatuba
3891	3555505	SP	Ubirajara
3892	3555604	SP	Uchoa
3893	3555703	SP	União Paulista
3894	3555802	SP	Urânia
3895	3555901	SP	Uru
3896	3556008	SP	Urupês
3897	3556107	SP	Valentim Gentil
3898	3556206	SP	Valinhos
3899	3556305	SP	Valparaíso
3900	3556354	SP	Vargem
3901	3556404	SP	Vargem Grande do Sul
3902	3556453	SP	Vargem Grande Paulista
3903	3556503	SP	Várzea Paulista
3904	3556602	SP	Vera Cruz
3905	3556701	SP	Vinhedo
3906	3556800	SP	Viradouro
3907	3556909	SP	Vista Alegre do Alto
3908	3556958	SP	Vitória Brasil
3909	3557006	SP	Votorantim
3910	3557105	SP	Votuporanga
3911	3557154	SP	Zacarias
3912	4100103	PR	Abatiá
3913	4100202	PR	Adrianópolis
3914	4100301	PR	Agudos do Sul
3915	4100400	PR	Almirante Tamandaré
3916	4100459	PR	Altamira do Paraná
3917	4128625	PR	Alto Paraíso
3918	4100608	PR	Alto Paraná
3919	4100707	PR	Alto Piquiri
3920	4100509	PR	Altônia
3921	4100806	PR	Alvorada do Sul
3922	4100905	PR	Amaporã
3923	4101002	PR	Ampére
3924	4101051	PR	Anahy
3925	4101101	PR	Andirá
3926	4101150	PR	Ângulo
3927	4101200	PR	Antonina
3928	4101309	PR	Antônio Olinto
3929	4101408	PR	Apucarana
3930	4101507	PR	Arapongas
3931	4101606	PR	Arapoti
3932	4101655	PR	Arapuã
3933	4101705	PR	Araruna
3934	4101804	PR	Araucária
3935	4101853	PR	Ariranha do Ivaí
3936	4101903	PR	Assaí
3937	4102000	PR	Assis Chateaubriand
3938	4102109	PR	Astorga
3939	4102208	PR	Atalaia
3940	4102307	PR	Balsa Nova
3941	4102406	PR	Bandeirantes
3942	4102505	PR	Barbosa Ferraz
3943	4102703	PR	Barra do Jacaré
3944	4102604	PR	Barracão
3945	4102752	PR	Bela Vista da Caroba
3946	4102802	PR	Bela Vista do Paraíso
3947	4102901	PR	Bituruna
3948	4103008	PR	Boa Esperança
3949	4103024	PR	Boa Esperança do Iguaçu
3950	4103040	PR	Boa Ventura de São Roque
3951	4103057	PR	Boa Vista da Aparecida
3952	4103107	PR	Bocaiúva do Sul
3953	4103156	PR	Bom Jesus do Sul
3954	4103206	PR	Bom Sucesso
3955	4103222	PR	Bom Sucesso do Sul
3956	4103305	PR	Borrazópolis
3957	4103354	PR	Braganey
3958	4103370	PR	Brasilândia do Sul
3959	4103404	PR	Cafeara
3960	4103453	PR	Cafelândia
3961	4103479	PR	Cafezal do Sul
3962	4103503	PR	Califórnia
3963	4103602	PR	Cambará
3964	4103701	PR	Cambé
3965	4103800	PR	Cambira
3966	4103909	PR	Campina da Lagoa
3967	4103958	PR	Campina do Simão
3968	4104006	PR	Campina Grande do Sul
3969	4104055	PR	Campo Bonito
3970	4104105	PR	Campo do Tenente
3971	4104204	PR	Campo Largo
3972	4104253	PR	Campo Magro
3973	4104303	PR	Campo Mourão
3974	4104402	PR	Cândido de Abreu
3975	4104428	PR	Candói
3976	4104451	PR	Cantagalo
3977	4104501	PR	Capanema
3978	4104600	PR	Capitão Leônidas Marques
3979	4104659	PR	Carambeí
3980	4104709	PR	Carlópolis
3981	4104808	PR	Cascavel
3982	4104907	PR	Castro
3983	4105003	PR	Catanduvas
3984	4105102	PR	Centenário do Sul
3985	4105201	PR	Cerro Azul
3986	4105300	PR	Céu Azul
3987	4105409	PR	Chopinzinho
3988	4105508	PR	Cianorte
3989	4105607	PR	Cidade Gaúcha
3990	4105706	PR	Clevelândia
3991	4105805	PR	Colombo
3992	4105904	PR	Colorado
3993	4106001	PR	Congonhinhas
3994	4106100	PR	Conselheiro Mairinck
3995	4106209	PR	Contenda
3996	4106308	PR	Corbélia
3997	4106407	PR	Cornélio Procópio
3998	4106456	PR	Coronel Domingos Soares
3999	4106506	PR	Coronel Vivida
4000	4106555	PR	Corumbataí do Sul
4001	4106803	PR	Cruz Machado
4002	4106571	PR	Cruzeiro do Iguaçu
4003	4106605	PR	Cruzeiro do Oeste
4004	4106704	PR	Cruzeiro do Sul
4005	4106852	PR	Cruzmaltina
4006	4106902	PR	Curitiba
4007	4107009	PR	Curiúva
4008	4107108	PR	Diamante do Norte
4009	4107124	PR	Diamante do Sul
4010	4107157	PR	Diamante D'Oeste
4011	4107207	PR	Dois Vizinhos
4012	4107256	PR	Douradina
4013	4107306	PR	Doutor Camargo
4014	4128633	PR	Doutor Ulysses
4015	4107405	PR	Enéas Marques
4016	4107504	PR	Engenheiro Beltrão
4017	4107538	PR	Entre Rios do Oeste
4018	4107520	PR	Esperança Nova
4019	4107546	PR	Espigão Alto do Iguaçu
4020	4107553	PR	Farol
4021	4107603	PR	Faxinal
4022	4107652	PR	Fazenda Rio Grande
4023	4107702	PR	Fênix
4024	4107736	PR	Fernandes Pinheiro
4025	4107751	PR	Figueira
4026	4107850	PR	Flor da Serra do Sul
4027	4107801	PR	Floraí
4028	4107900	PR	Floresta
4029	4108007	PR	Florestópolis
4030	4108106	PR	Flórida
4031	4108205	PR	Formosa do Oeste
4032	4108304	PR	Foz do Iguaçu
4033	4108452	PR	Foz do Jordão
4034	4108320	PR	Francisco Alves
4035	4108403	PR	Francisco Beltrão
4036	4108502	PR	General Carneiro
4037	4108551	PR	Godoy Moreira
4038	4108601	PR	Goioerê
4039	4108650	PR	Goioxim
4040	4108700	PR	Grandes Rios
4041	4108809	PR	Guaíra
4042	4108908	PR	Guairaçá
4043	4108957	PR	Guamiranga
4044	4109005	PR	Guapirama
4045	4109104	PR	Guaporema
4046	4109203	PR	Guaraci
4047	4109302	PR	Guaraniaçu
4048	4109401	PR	Guarapuava
4049	4109500	PR	Guaraqueçaba
4050	4109609	PR	Guaratuba
4051	4109658	PR	Honório Serpa
4052	4109708	PR	Ibaiti
4053	4109757	PR	Ibema
4054	4109807	PR	Ibiporã
4055	4109906	PR	Icaraíma
4056	4110003	PR	Iguaraçu
4057	4110052	PR	Iguatu
4058	4110078	PR	Imbaú
4059	4110102	PR	Imbituva
4060	4110201	PR	Inácio Martins
4061	4110300	PR	Inajá
4062	4110409	PR	Indianópolis
4063	4110508	PR	Ipiranga
4064	4110607	PR	Iporã
4065	4110656	PR	Iracema do Oeste
4066	4110706	PR	Irati
4067	4110805	PR	Iretama
4068	4110904	PR	Itaguajé
4069	4110953	PR	Itaipulândia
4070	4111001	PR	Itambaracá
4071	4111100	PR	Itambé
4072	4111209	PR	Itapejara d'Oeste
4073	4111258	PR	Itaperuçu
4074	4111308	PR	Itaúna do Sul
4075	4111407	PR	Ivaí
4076	4111506	PR	Ivaiporã
4077	4111555	PR	Ivaté
4078	4111605	PR	Ivatuba
4079	4111704	PR	Jaboti
4080	4111803	PR	Jacarezinho
4081	4111902	PR	Jaguapitã
4082	4112009	PR	Jaguariaíva
4083	4112108	PR	Jandaia do Sul
4084	4112207	PR	Janiópolis
4085	4112306	PR	Japira
4086	4112405	PR	Japurá
4087	4112504	PR	Jardim Alegre
4088	4112603	PR	Jardim Olinda
4089	4112702	PR	Jataizinho
4090	4112751	PR	Jesuítas
4091	4112801	PR	Joaquim Távora
4092	4112900	PR	Jundiaí do Sul
4093	4112959	PR	Juranda
4094	4113007	PR	Jussara
4095	4113106	PR	Kaloré
4096	4113205	PR	Lapa
4097	4113254	PR	Laranjal
4098	4113304	PR	Laranjeiras do Sul
4099	4113403	PR	Leópolis
4100	4113429	PR	Lidianópolis
4101	4113452	PR	Lindoeste
4102	4113502	PR	Loanda
4103	4113601	PR	Lobato
4104	4113700	PR	Londrina
4105	4113734	PR	Luiziana
4106	4113759	PR	Lunardelli
4107	4113809	PR	Lupionópolis
4108	4113908	PR	Mallet
4109	4114005	PR	Mamborê
4110	4114104	PR	Mandaguaçu
4111	4114203	PR	Mandaguari
4112	4114302	PR	Mandirituba
4113	4114351	PR	Manfrinópolis
4114	4114401	PR	Mangueirinha
4115	4114500	PR	Manoel Ribas
4116	4114609	PR	Marechal Cândido Rondon
4117	4114708	PR	Maria Helena
4118	4114807	PR	Marialva
4119	4114906	PR	Marilândia do Sul
4120	4115002	PR	Marilena
4121	4115101	PR	Mariluz
4122	4115200	PR	Maringá
4123	4115309	PR	Mariópolis
4124	4115358	PR	Maripá
4125	4115408	PR	Marmeleiro
4126	4115457	PR	Marquinho
4127	4115507	PR	Marumbi
4128	4115606	PR	Matelândia
4129	4115705	PR	Matinhos
4130	4115739	PR	Mato Rico
4131	4115754	PR	Mauá da Serra
4132	4115804	PR	Medianeira
4133	4115853	PR	Mercedes
4134	4115903	PR	Mirador
4135	4116000	PR	Miraselva
4136	4116059	PR	Missal
4137	4116109	PR	Moreira Sales
4138	4116208	PR	Morretes
4139	4116307	PR	Munhoz de Melo
4140	4116406	PR	Nossa Senhora das Graças
4141	4116505	PR	Nova Aliança do Ivaí
4142	4116604	PR	Nova América da Colina
4143	4116703	PR	Nova Aurora
4144	4116802	PR	Nova Cantu
4145	4116901	PR	Nova Esperança
4146	4116950	PR	Nova Esperança do Sudoeste
4147	4117008	PR	Nova Fátima
4148	4117057	PR	Nova Laranjeiras
4149	4117107	PR	Nova Londrina
4150	4117206	PR	Nova Olímpia
4151	4117255	PR	Nova Prata do Iguaçu
4152	4117214	PR	Nova Santa Bárbara
4153	4117222	PR	Nova Santa Rosa
4154	4117271	PR	Nova Tebas
4155	4117297	PR	Novo Itacolomi
4156	4117305	PR	Ortigueira
4157	4117404	PR	Ourizona
4158	4117453	PR	Ouro Verde do Oeste
4159	4117503	PR	Paiçandu
4160	4117602	PR	Palmas
4161	4117701	PR	Palmeira
4162	4117800	PR	Palmital
4163	4117909	PR	Palotina
4164	4118006	PR	Paraíso do Norte
4165	4118105	PR	Paranacity
4166	4118204	PR	Paranaguá
4167	4118303	PR	Paranapoema
4168	4118402	PR	Paranavaí
4169	4118451	PR	Pato Bragado
4170	4118501	PR	Pato Branco
4171	4118600	PR	Paula Freitas
4172	4118709	PR	Paulo Frontin
4173	4118808	PR	Peabiru
4174	4118857	PR	Perobal
4175	4118907	PR	Pérola
4176	4119004	PR	Pérola d'Oeste
4177	4119103	PR	Piên
4178	4119152	PR	Pinhais
4179	4119251	PR	Pinhal de São Bento
4180	4119202	PR	Pinhalão
4181	4119301	PR	Pinhão
4182	4119400	PR	Piraí do Sul
4183	4119509	PR	Piraquara
4184	4119608	PR	Pitanga
4185	4119657	PR	Pitangueiras
4186	4119707	PR	Planaltina do Paraná
4187	4119806	PR	Planalto
4188	4119905	PR	Ponta Grossa
4189	4119954	PR	Pontal do Paraná
4190	4120002	PR	Porecatu
4191	4120101	PR	Porto Amazonas
4192	4120150	PR	Porto Barreiro
4193	4120200	PR	Porto Rico
4194	4120309	PR	Porto Vitória
4195	4120333	PR	Prado Ferreira
4196	4120358	PR	Pranchita
4197	4120408	PR	Presidente Castelo Branco
4198	4120507	PR	Primeiro de Maio
4199	4120606	PR	Prudentópolis
4200	4120655	PR	Quarto Centenário
4201	4120705	PR	Quatiguá
4202	4120804	PR	Quatro Barras
4203	4120853	PR	Quatro Pontes
4204	4120903	PR	Quedas do Iguaçu
4205	4121000	PR	Querência do Norte
4206	4121109	PR	Quinta do Sol
4207	4121208	PR	Quitandinha
4208	4121257	PR	Ramilândia
4209	4121307	PR	Rancho Alegre
4210	4121356	PR	Rancho Alegre D'Oeste
4211	4121406	PR	Realeza
4212	4121505	PR	Rebouças
4213	4121604	PR	Renascença
4214	4121703	PR	Reserva
4215	4121752	PR	Reserva do Iguaçu
4216	4121802	PR	Ribeirão Claro
4217	4121901	PR	Ribeirão do Pinhal
4218	4122008	PR	Rio Azul
4219	4122107	PR	Rio Bom
4220	4122156	PR	Rio Bonito do Iguaçu
4221	4122172	PR	Rio Branco do Ivaí
4222	4122206	PR	Rio Branco do Sul
4223	4122305	PR	Rio Negro
4224	4122404	PR	Rolândia
4225	4122503	PR	Roncador
4226	4122602	PR	Rondon
4227	4122651	PR	Rosário do Ivaí
4228	4122701	PR	Sabáudia
4229	4122800	PR	Salgado Filho
4230	4122909	PR	Salto do Itararé
4231	4123006	PR	Salto do Lontra
4232	4123105	PR	Santa Amélia
4233	4123204	PR	Santa Cecília do Pavão
4234	4123303	PR	Santa Cruz de Monte Castelo
4235	4123402	PR	Santa Fé
4236	4123501	PR	Santa Helena
4237	4123600	PR	Santa Inês
4238	4123709	PR	Santa Isabel do Ivaí
4239	4123808	PR	Santa Izabel do Oeste
4240	4123824	PR	Santa Lúcia
4241	4123857	PR	Santa Maria do Oeste
4242	4123907	PR	Santa Mariana
4243	4123956	PR	Santa Mônica
4244	4124020	PR	Santa Tereza do Oeste
4245	4124053	PR	Santa Terezinha de Itaipu
4246	4124004	PR	Santana do Itararé
4247	4124103	PR	Santo Antônio da Platina
4248	4124202	PR	Santo Antônio do Caiuá
4249	4124301	PR	Santo Antônio do Paraíso
4250	4124400	PR	Santo Antônio do Sudoeste
4251	4124509	PR	Santo Inácio
4252	4124608	PR	São Carlos do Ivaí
4253	4124707	PR	São Jerônimo da Serra
4254	4124806	PR	São João
4255	4124905	PR	São João do Caiuá
4256	4125001	PR	São João do Ivaí
4257	4125100	PR	São João do Triunfo
4258	4125308	PR	São Jorge do Ivaí
4259	4125357	PR	São Jorge do Patrocínio
4260	4125209	PR	São Jorge d'Oeste
4261	4125407	PR	São José da Boa Vista
4262	4125456	PR	São José das Palmeiras
4263	4125506	PR	São José dos Pinhais
4264	4125555	PR	São Manoel do Paraná
4265	4125605	PR	São Mateus do Sul
4266	4125704	PR	São Miguel do Iguaçu
4267	4125753	PR	São Pedro do Iguaçu
4268	4125803	PR	São Pedro do Ivaí
4269	4125902	PR	São Pedro do Paraná
4270	4126009	PR	São Sebastião da Amoreira
4271	4126108	PR	São Tomé
4272	4126207	PR	Sapopema
4273	4126256	PR	Sarandi
4274	4126272	PR	Saudade do Iguaçu
4275	4126306	PR	Sengés
4276	4126355	PR	Serranópolis do Iguaçu
4277	4126405	PR	Sertaneja
4278	4126504	PR	Sertanópolis
4279	4126603	PR	Siqueira Campos
4280	4126652	PR	Sulina
4281	4126678	PR	Tamarana
4282	4126702	PR	Tamboara
4283	4126801	PR	Tapejara
4284	4126900	PR	Tapira
4285	4127007	PR	Teixeira Soares
4286	4127106	PR	Telêmaco Borba
4287	4127205	PR	Terra Boa
4288	4127304	PR	Terra Rica
4289	4127403	PR	Terra Roxa
4290	4127502	PR	Tibagi
4291	4127601	PR	Tijucas do Sul
4292	4127700	PR	Toledo
4293	4127809	PR	Tomazina
4294	4127858	PR	Três Barras do Paraná
4295	4127882	PR	Tunas do Paraná
4296	4127908	PR	Tuneiras do Oeste
4297	4127957	PR	Tupãssi
4298	4127965	PR	Turvo
4299	4128005	PR	Ubiratã
4300	4128104	PR	Umuarama
4301	4128203	PR	União da Vitória
4302	4128302	PR	Uniflor
4303	4128401	PR	Uraí
4304	4128534	PR	Ventania
4305	4128559	PR	Vera Cruz do Oeste
4306	4128609	PR	Verê
4307	4128658	PR	Virmond
4308	4128708	PR	Vitorino
4309	4128500	PR	Wenceslau Braz
4310	4128807	PR	Xambrê
4311	4200051	SC	Abdon Batista
4312	4200101	SC	Abelardo Luz
4313	4200200	SC	Agrolândia
4314	4200309	SC	Agronômica
4315	4200408	SC	Água Doce
4316	4200507	SC	Águas de Chapecó
4317	4200556	SC	Águas Frias
4318	4200606	SC	Águas Mornas
4319	4200705	SC	Alfredo Wagner
4320	4200754	SC	Alto Bela Vista
4321	4200804	SC	Anchieta
4322	4200903	SC	Angelina
4323	4201000	SC	Anita Garibaldi
4324	4201109	SC	Anitápolis
4325	4201208	SC	Antônio Carlos
4326	4201257	SC	Apiúna
4327	4201273	SC	Arabutã
4328	4201307	SC	Araquari
4329	4201406	SC	Araranguá
4330	4201505	SC	Armazém
4331	4201604	SC	Arroio Trinta
4332	4201653	SC	Arvoredo
4333	4201703	SC	Ascurra
4334	4201802	SC	Atalanta
4335	4201901	SC	Aurora
4336	4201950	SC	Balneário Arroio do Silva
4337	4202057	SC	Balneário Barra do Sul
4338	4202008	SC	Balneário Camboriú
4339	4202073	SC	Balneário Gaivota
4340	4212809	SC	Balneário Piçarras
4341	4202081	SC	Bandeirante
4342	4202099	SC	Barra Bonita
4343	4202107	SC	Barra Velha
4344	4202131	SC	Bela Vista do Toldo
4345	4202156	SC	Belmonte
4346	4202206	SC	Benedito Novo
4347	4202305	SC	Biguaçu
4348	4202404	SC	Blumenau
4349	4202438	SC	Bocaina do Sul
4350	4202503	SC	Bom Jardim da Serra
4351	4202537	SC	Bom Jesus
4352	4202578	SC	Bom Jesus do Oeste
4353	4202602	SC	Bom Retiro
4354	4202453	SC	Bombinhas
4355	4202701	SC	Botuverá
4356	4202800	SC	Braço do Norte
4357	4202859	SC	Braço do Trombudo
4358	4202875	SC	Brunópolis
4359	4202909	SC	Brusque
4360	4203006	SC	Caçador
4361	4203105	SC	Caibi
4362	4203154	SC	Calmon
4363	4203204	SC	Camboriú
4364	4203303	SC	Campo Alegre
4365	4203402	SC	Campo Belo do Sul
4366	4203501	SC	Campo Erê
4367	4203600	SC	Campos Novos
4368	4203709	SC	Canelinha
4369	4203808	SC	Canoinhas
4370	4203253	SC	Capão Alto
4371	4203907	SC	Capinzal
4372	4203956	SC	Capivari de Baixo
4373	4204004	SC	Catanduvas
4374	4204103	SC	Caxambu do Sul
4375	4204152	SC	Celso Ramos
4376	4204178	SC	Cerro Negro
4377	4204194	SC	Chapadão do Lageado
4378	4204202	SC	Chapecó
4379	4204251	SC	Cocal do Sul
4380	4204301	SC	Concórdia
4381	4204350	SC	Cordilheira Alta
4382	4204400	SC	Coronel Freitas
4383	4204459	SC	Coronel Martins
4384	4204558	SC	Correia Pinto
4385	4204509	SC	Corupá
4386	4204608	SC	Criciúma
4387	4204707	SC	Cunha Porã
4388	4204756	SC	Cunhataí
4389	4204806	SC	Curitibanos
4390	4204905	SC	Descanso
4391	4205001	SC	Dionísio Cerqueira
4392	4205100	SC	Dona Emma
4393	4205159	SC	Doutor Pedrinho
4394	4205175	SC	Entre Rios
4395	4205191	SC	Ermo
4396	4205209	SC	Erval Velho
4397	4205308	SC	Faxinal dos Guedes
4398	4205357	SC	Flor do Sertão
4399	4205407	SC	Florianópolis
4400	4205431	SC	Formosa do Sul
4401	4205456	SC	Forquilhinha
4402	4205506	SC	Fraiburgo
4403	4205555	SC	Frei Rogério
4404	4205605	SC	Galvão
4405	4205704	SC	Garopaba
4406	4205803	SC	Garuva
4407	4205902	SC	Gaspar
4408	4206009	SC	Governador Celso Ramos
4409	4206108	SC	Grão Pará
4410	4206207	SC	Gravatal
4411	4206306	SC	Guabiruba
4412	4206405	SC	Guaraciaba
4413	4206504	SC	Guaramirim
4414	4206603	SC	Guarujá do Sul
4415	4206652	SC	Guatambú
4416	4206702	SC	Herval d'Oeste
4417	4206751	SC	Ibiam
4418	4206801	SC	Ibicaré
4419	4206900	SC	Ibirama
4420	4207007	SC	Içara
4421	4207106	SC	Ilhota
4422	4207205	SC	Imaruí
4423	4207304	SC	Imbituba
4424	4207403	SC	Imbuia
4425	4207502	SC	Indaial
4426	4207577	SC	Iomerê
4427	4207601	SC	Ipira
4428	4207650	SC	Iporã do Oeste
4429	4207684	SC	Ipuaçu
4430	4207700	SC	Ipumirim
4431	4207759	SC	Iraceminha
4432	4207809	SC	Irani
4433	4207858	SC	Irati
4434	4207908	SC	Irineópolis
4435	4208005	SC	Itá
4436	4208104	SC	Itaiópolis
4437	4208203	SC	Itajaí
4438	4208302	SC	Itapema
4439	4208401	SC	Itapiranga
4440	4208450	SC	Itapoá
4441	4208500	SC	Ituporanga
4442	4208609	SC	Jaborá
4443	4208708	SC	Jacinto Machado
4444	4208807	SC	Jaguaruna
4445	4208906	SC	Jaraguá do Sul
4446	4208955	SC	Jardinópolis
4447	4209003	SC	Joaçaba
4448	4209102	SC	Joinville
4449	4209151	SC	José Boiteux
4450	4209177	SC	Jupiá
4451	4209201	SC	Lacerdópolis
4452	4209300	SC	Lages
4453	4209409	SC	Laguna
4454	4209458	SC	Lajeado Grande
4455	4209508	SC	Laurentino
4456	4209607	SC	Lauro Muller
4457	4209706	SC	Lebon Régis
4458	4209805	SC	Leoberto Leal
4459	4209854	SC	Lindóia do Sul
4460	4209904	SC	Lontras
4461	4210001	SC	Luiz Alves
4462	4210035	SC	Luzerna
4463	4210050	SC	Macieira
4464	4210100	SC	Mafra
4465	4210209	SC	Major Gercino
4466	4210308	SC	Major Vieira
4467	4210407	SC	Maracajá
4468	4210506	SC	Maravilha
4469	4210555	SC	Marema
4470	4210605	SC	Massaranduba
4471	4210704	SC	Matos Costa
4472	4210803	SC	Meleiro
4473	4210852	SC	Mirim Doce
4474	4210902	SC	Modelo
4475	4211009	SC	Mondaí
4476	4211058	SC	Monte Carlo
4477	4211108	SC	Monte Castelo
4478	4211207	SC	Morro da Fumaça
4479	4211256	SC	Morro Grande
4480	4211306	SC	Navegantes
4481	4211405	SC	Nova Erechim
4482	4211454	SC	Nova Itaberaba
4483	4211504	SC	Nova Trento
4484	4211603	SC	Nova Veneza
4485	4211652	SC	Novo Horizonte
4486	4211702	SC	Orleans
4487	4211751	SC	Otacílio Costa
4488	4211801	SC	Ouro
4489	4211850	SC	Ouro Verde
4490	4211876	SC	Paial
4491	4211892	SC	Painel
4492	4211900	SC	Palhoça
4493	4212007	SC	Palma Sola
4494	4212056	SC	Palmeira
4495	4212106	SC	Palmitos
4496	4212205	SC	Papanduva
4497	4212239	SC	Paraíso
4498	4212254	SC	Passo de Torres
4499	4212270	SC	Passos Maia
4500	4212304	SC	Paulo Lopes
4501	4212403	SC	Pedras Grandes
4502	4212502	SC	Penha
4503	4212601	SC	Peritiba
4504	4212700	SC	Petrolândia
4505	4212908	SC	Pinhalzinho
4506	4213005	SC	Pinheiro Preto
4507	4213104	SC	Piratuba
4508	4213153	SC	Planalto Alegre
4509	4213203	SC	Pomerode
4510	4213302	SC	Ponte Alta
4511	4213351	SC	Ponte Alta do Norte
4512	4213401	SC	Ponte Serrada
4513	4213500	SC	Porto Belo
4514	4213609	SC	Porto União
4515	4213708	SC	Pouso Redondo
4516	4213807	SC	Praia Grande
4517	4213906	SC	Presidente Castello Branco
4518	4214003	SC	Presidente Getúlio
4519	4214102	SC	Presidente Nereu
4520	4214151	SC	Princesa
4521	4214201	SC	Quilombo
4522	4214300	SC	Rancho Queimado
4523	4214409	SC	Rio das Antas
4524	4214508	SC	Rio do Campo
4525	4214607	SC	Rio do Oeste
4526	4214805	SC	Rio do Sul
4527	4214706	SC	Rio dos Cedros
4528	4214904	SC	Rio Fortuna
4529	4215000	SC	Rio Negrinho
4530	4215059	SC	Rio Rufino
4531	4215075	SC	Riqueza
4532	4215109	SC	Rodeio
4533	4215208	SC	Romelândia
4534	4215307	SC	Salete
4535	4215356	SC	Saltinho
4536	4215406	SC	Salto Veloso
4537	4215455	SC	Sangão
4538	4215505	SC	Santa Cecília
4539	4215554	SC	Santa Helena
4540	4215604	SC	Santa Rosa de Lima
4541	4215653	SC	Santa Rosa do Sul
4542	4215679	SC	Santa Terezinha
4543	4215687	SC	Santa Terezinha do Progresso
4544	4215695	SC	Santiago do Sul
4545	4215703	SC	Santo Amaro da Imperatriz
4546	4215802	SC	São Bento do Sul
4547	4215752	SC	São Bernardino
4548	4215901	SC	São Bonifácio
4549	4216008	SC	São Carlos
4550	4216057	SC	São Cristovão do Sul
4551	4216107	SC	São Domingos
4552	4216206	SC	São Francisco do Sul
4553	4216305	SC	São João Batista
4554	4216354	SC	São João do Itaperiú
4555	4216255	SC	São João do Oeste
4556	4216404	SC	São João do Sul
4557	4216503	SC	São Joaquim
4558	4216602	SC	São José
4559	4216701	SC	São José do Cedro
4560	4216800	SC	São José do Cerrito
4561	4216909	SC	São Lourenço do Oeste
4562	4217006	SC	São Ludgero
4563	4217105	SC	São Martinho
4564	4217154	SC	São Miguel da Boa Vista
4565	4217204	SC	São Miguel do Oeste
4566	4217253	SC	São Pedro de Alcântara
4567	4217303	SC	Saudades
4568	4217402	SC	Schroeder
4569	4217501	SC	Seara
4570	4217550	SC	Serra Alta
4571	4217600	SC	Siderópolis
4572	4217709	SC	Sombrio
4573	4217758	SC	Sul Brasil
4574	4217808	SC	Taió
4575	4217907	SC	Tangará
4576	4217956	SC	Tigrinhos
4577	4218004	SC	Tijucas
4578	4218103	SC	Timbé do Sul
4579	4218202	SC	Timbó
4580	4218251	SC	Timbó Grande
4581	4218301	SC	Três Barras
4582	4218350	SC	Treviso
4583	4218400	SC	Treze de Maio
4584	4218509	SC	Treze Tílias
4585	4218608	SC	Trombudo Central
4586	4218707	SC	Tubarão
4587	4218756	SC	Tunápolis
4588	4218806	SC	Turvo
4589	4218855	SC	União do Oeste
4590	4218905	SC	Urubici
4591	4218954	SC	Urupema
4592	4219002	SC	Urussanga
4593	4219101	SC	Vargeão
4594	4219150	SC	Vargem
4595	4219176	SC	Vargem Bonita
4596	4219200	SC	Vidal Ramos
4597	4219309	SC	Videira
4598	4219358	SC	Vitor Meireles
4599	4219408	SC	Witmarsum
4600	4219507	SC	Xanxerê
4601	4219606	SC	Xavantina
4602	4219705	SC	Xaxim
4603	4219853	SC	Zortéa
4604	4300034	RS	Aceguá
4605	4300059	RS	Água Santa
4606	4300109	RS	Agudo
4607	4300208	RS	Ajuricaba
4608	4300307	RS	Alecrim
4609	4300406	RS	Alegrete
4610	4300455	RS	Alegria
4611	4300471	RS	Almirante Tamandaré do Sul
4612	4300505	RS	Alpestre
4613	4300554	RS	Alto Alegre
4614	4300570	RS	Alto Feliz
4615	4300604	RS	Alvorada
4616	4300638	RS	Amaral Ferrador
4617	4300646	RS	Ametista do Sul
4618	4300661	RS	André da Rocha
4619	4300703	RS	Anta Gorda
4620	4300802	RS	Antônio Prado
4621	4300851	RS	Arambaré
4622	4300877	RS	Araricá
4623	4300901	RS	Aratiba
4624	4301008	RS	Arroio do Meio
4625	4301073	RS	Arroio do Padre
4626	4301057	RS	Arroio do Sal
4627	4301206	RS	Arroio do Tigre
4628	4301107	RS	Arroio dos Ratos
4629	4301305	RS	Arroio Grande
4630	4301404	RS	Arvorezinha
4631	4301503	RS	Augusto Pestana
4632	4301552	RS	Áurea
4633	4301602	RS	Bagé
4634	4301636	RS	Balneário Pinhal
4635	4301651	RS	Barão
4636	4301701	RS	Barão de Cotegipe
4637	4301750	RS	Barão do Triunfo
4638	4301859	RS	Barra do Guarita
4639	4301875	RS	Barra do Quaraí
4640	4301909	RS	Barra do Ribeiro
4641	4301925	RS	Barra do Rio Azul
4642	4301958	RS	Barra Funda
4643	4301800	RS	Barracão
4644	4302006	RS	Barros Cassal
4645	4302055	RS	Benjamin Constant do Sul
4646	4302105	RS	Bento Gonçalves
4647	4302154	RS	Boa Vista das Missões
4648	4302204	RS	Boa Vista do Buricá
4649	4302220	RS	Boa Vista do Cadeado
4650	4302238	RS	Boa Vista do Incra
4651	4302253	RS	Boa Vista do Sul
4652	4302303	RS	Bom Jesus
4653	4302352	RS	Bom Princípio
4654	4302378	RS	Bom Progresso
4655	4302402	RS	Bom Retiro do Sul
4656	4302451	RS	Boqueirão do Leão
4657	4302501	RS	Bossoroca
4658	4302584	RS	Bozano
4659	4302600	RS	Braga
4660	4302659	RS	Brochier
4661	4302709	RS	Butiá
4662	4302808	RS	Caçapava do Sul
4663	4302907	RS	Cacequi
4664	4303004	RS	Cachoeira do Sul
4665	4303103	RS	Cachoeirinha
4666	4303202	RS	Cacique Doble
4667	4303301	RS	Caibaté
4668	4303400	RS	Caiçara
4669	4303509	RS	Camaquã
4670	4303558	RS	Camargo
4671	4303608	RS	Cambará do Sul
4672	4303673	RS	Campestre da Serra
4673	4303707	RS	Campina das Missões
4674	4303806	RS	Campinas do Sul
4675	4303905	RS	Campo Bom
4676	4304002	RS	Campo Novo
4677	4304101	RS	Campos Borges
4678	4304200	RS	Candelária
4679	4304309	RS	Cândido Godói
4680	4304358	RS	Candiota
4681	4304408	RS	Canela
4682	4304507	RS	Canguçu
4683	4304606	RS	Canoas
4684	4304614	RS	Canudos do Vale
4685	4304622	RS	Capão Bonito do Sul
4686	4304630	RS	Capão da Canoa
4687	4304655	RS	Capão do Cipó
4688	4304663	RS	Capão do Leão
4689	4304689	RS	Capela de Santana
4690	4304697	RS	Capitão
4691	4304671	RS	Capivari do Sul
4692	4304713	RS	Caraá
4693	4304705	RS	Carazinho
4694	4304804	RS	Carlos Barbosa
4695	4304853	RS	Carlos Gomes
4696	4304903	RS	Casca
4697	4304952	RS	Caseiros
4698	4305009	RS	Catuípe
4699	4305108	RS	Caxias do Sul
4700	4305116	RS	Centenário
4701	4305124	RS	Cerrito
4702	4305132	RS	Cerro Branco
4703	4305157	RS	Cerro Grande
4704	4305173	RS	Cerro Grande do Sul
4705	4305207	RS	Cerro Largo
4706	4305306	RS	Chapada
4707	4305355	RS	Charqueadas
4708	4305371	RS	Charrua
4709	4305405	RS	Chiapetta
4710	4305439	RS	Chuí
4711	4305447	RS	Chuvisca
4712	4305454	RS	Cidreira
4713	4305504	RS	Ciríaco
4714	4305587	RS	Colinas
4715	4305603	RS	Colorado
4716	4305702	RS	Condor
4717	4305801	RS	Constantina
4718	4305835	RS	Coqueiro Baixo
4719	4305850	RS	Coqueiros do Sul
4720	4305871	RS	Coronel Barros
4721	4305900	RS	Coronel Bicaco
4722	4305934	RS	Coronel Pilar
4723	4305959	RS	Cotiporã
4724	4305975	RS	Coxilha
4725	4306007	RS	Crissiumal
4726	4306056	RS	Cristal
4727	4306072	RS	Cristal do Sul
4728	4306106	RS	Cruz Alta
4729	4306130	RS	Cruzaltense
4730	4306205	RS	Cruzeiro do Sul
4731	4306304	RS	David Canabarro
4732	4306320	RS	Derrubadas
4733	4306353	RS	Dezesseis de Novembro
4734	4306379	RS	Dilermando de Aguiar
4735	4306403	RS	Dois Irmãos
4736	4306429	RS	Dois Irmãos das Missões
4737	4306452	RS	Dois Lajeados
4738	4306502	RS	Dom Feliciano
4739	4306601	RS	Dom Pedrito
4740	4306551	RS	Dom Pedro de Alcântara
4741	4306700	RS	Dona Francisca
4742	4306734	RS	Doutor Maurício Cardoso
4743	4306759	RS	Doutor Ricardo
4744	4306767	RS	Eldorado do Sul
4745	4306809	RS	Encantado
4746	4306908	RS	Encruzilhada do Sul
4747	4306924	RS	Engenho Velho
4748	4306957	RS	Entre Rios do Sul
4749	4306932	RS	Entre-Ijuís
4750	4306973	RS	Erebango
4751	4307005	RS	Erechim
4752	4307054	RS	Ernestina
4753	4307203	RS	Erval Grande
4754	4307302	RS	Erval Seco
4755	4307401	RS	Esmeralda
4756	4307450	RS	Esperança do Sul
4757	4307500	RS	Espumoso
4758	4307559	RS	Estação
4759	4307609	RS	Estância Velha
4760	4307708	RS	Esteio
4761	4307807	RS	Estrela
4762	4307815	RS	Estrela Velha
4763	4307831	RS	Eugênio de Castro
4764	4307864	RS	Fagundes Varela
4765	4307906	RS	Farroupilha
4766	4308003	RS	Faxinal do Soturno
4767	4308052	RS	Faxinalzinho
4768	4308078	RS	Fazenda Vilanova
4769	4308102	RS	Feliz
4770	4308201	RS	Flores da Cunha
4771	4308250	RS	Floriano Peixoto
4773	4308409	RS	Formigueiro
4774	4308433	RS	Forquetinha
4775	4308458	RS	Fortaleza dos Valos
4776	4308508	RS	Frederico Westphalen
4777	4308607	RS	Garibaldi
4778	4308656	RS	Garruchos
4779	4308706	RS	Gaurama
4780	4308805	RS	General Câmara
4781	4308854	RS	Gentil
4782	4308904	RS	Getúlio Vargas
4783	4309001	RS	Giruá
4784	4309050	RS	Glorinha
4785	4309100	RS	Gramado
4786	4309126	RS	Gramado dos Loureiros
4787	4309159	RS	Gramado Xavier
4788	4309209	RS	Gravataí
4789	4309258	RS	Guabiju
4790	4309308	RS	Guaíba
4791	4309407	RS	Guaporé
4792	4309506	RS	Guarani das Missões
4793	4309555	RS	Harmonia
4794	4307104	RS	Herval
4795	4309571	RS	Herveiras
4796	4309605	RS	Horizontina
4797	4309654	RS	Hulha Negra
4798	4309704	RS	Humaitá
4799	4309753	RS	Ibarama
4800	4309803	RS	Ibiaçá
4801	4309902	RS	Ibiraiaras
4802	4309951	RS	Ibirapuitã
4803	4310009	RS	Ibirubá
4804	4310108	RS	Igrejinha
4805	4310207	RS	Ijuí
4806	4310306	RS	Ilópolis
4807	4310330	RS	Imbé
4808	4310363	RS	Imigrante
4809	4310405	RS	Independência
4810	4310413	RS	Inhacorá
4811	4310439	RS	Ipê
4812	4310462	RS	Ipiranga do Sul
4813	4310504	RS	Iraí
4814	4310538	RS	Itaara
4815	4310553	RS	Itacurubi
4816	4310579	RS	Itapuca
4817	4310603	RS	Itaqui
4818	4310652	RS	Itati
4819	4310702	RS	Itatiba do Sul
4820	4310751	RS	Ivorá
4821	4310801	RS	Ivoti
4822	4310850	RS	Jaboticaba
4823	4310876	RS	Jacuizinho
4824	4310900	RS	Jacutinga
4825	4311007	RS	Jaguarão
4826	4311106	RS	Jaguari
4827	4311122	RS	Jaquirana
4828	4311130	RS	Jari
4829	4311155	RS	Jóia
4830	4311205	RS	Júlio de Castilhos
4831	4311239	RS	Lagoa Bonita do Sul
4832	4311270	RS	Lagoa dos Três Cantos
4833	4311304	RS	Lagoa Vermelha
4834	4311254	RS	Lagoão
4835	4311403	RS	Lajeado
4836	4311429	RS	Lajeado do Bugre
4837	4311502	RS	Lavras do Sul
4838	4311601	RS	Liberato Salzano
4839	4311627	RS	Lindolfo Collor
4840	4311643	RS	Linha Nova
4841	4311718	RS	Maçambará
4842	4311700	RS	Machadinho
4843	4311734	RS	Mampituba
4844	4311759	RS	Manoel Viana
4845	4311775	RS	Maquiné
4846	4311791	RS	Maratá
4847	4311809	RS	Marau
4848	4311908	RS	Marcelino Ramos
4849	4311981	RS	Mariana Pimentel
4850	4312005	RS	Mariano Moro
4851	4312054	RS	Marques de Souza
4852	4312104	RS	Mata
4853	4312138	RS	Mato Castelhano
4854	4312153	RS	Mato Leitão
4855	4312179	RS	Mato Queimado
4856	4312203	RS	Maximiliano de Almeida
4857	4312252	RS	Minas do Leão
4858	4312302	RS	Miraguaí
4859	4312351	RS	Montauri
4860	4312377	RS	Monte Alegre dos Campos
4861	4312385	RS	Monte Belo do Sul
4862	4312401	RS	Montenegro
4863	4312427	RS	Mormaço
4864	4312443	RS	Morrinhos do Sul
4865	4312450	RS	Morro Redondo
4866	4312476	RS	Morro Reuter
4867	4312500	RS	Mostardas
4868	4312609	RS	Muçum
4869	4312617	RS	Muitos Capões
4870	4312625	RS	Muliterno
4871	4312658	RS	Não-Me-Toque
4872	4312674	RS	Nicolau Vergueiro
4873	4312708	RS	Nonoai
4874	4312757	RS	Nova Alvorada
4875	4312807	RS	Nova Araçá
4876	4312906	RS	Nova Bassano
4877	4312955	RS	Nova Boa Vista
4878	4313003	RS	Nova Bréscia
4879	4313011	RS	Nova Candelária
4880	4313037	RS	Nova Esperança do Sul
4881	4313060	RS	Nova Hartz
4882	4313086	RS	Nova Pádua
4883	4313102	RS	Nova Palma
4884	4313201	RS	Nova Petrópolis
4885	4313300	RS	Nova Prata
4886	4313334	RS	Nova Ramada
4887	4313359	RS	Nova Roma do Sul
4888	4313375	RS	Nova Santa Rita
4889	4313490	RS	Novo Barreiro
4890	4313391	RS	Novo Cabrais
4891	4313409	RS	Novo Hamburgo
4892	4313425	RS	Novo Machado
4893	4313441	RS	Novo Tiradentes
4894	4313466	RS	Novo Xingu
4895	4313508	RS	Osório
4896	4313607	RS	Paim Filho
4897	4313656	RS	Palmares do Sul
4898	4313706	RS	Palmeira das Missões
4899	4313805	RS	Palmitinho
4900	4313904	RS	Panambi
4901	4313953	RS	Pantano Grande
4902	4314001	RS	Paraí
4903	4314027	RS	Paraíso do Sul
4904	4314035	RS	Pareci Novo
4905	4314050	RS	Parobé
4906	4314068	RS	Passa Sete
4907	4314076	RS	Passo do Sobrado
4908	4314100	RS	Passo Fundo
4909	4314134	RS	Paulo Bento
4910	4314159	RS	Paverama
4911	4314175	RS	Pedras Altas
4912	4314209	RS	Pedro Osório
4913	4314308	RS	Pejuçara
4914	4314407	RS	Pelotas
4915	4314423	RS	Picada Café
4916	4314456	RS	Pinhal
4917	4314464	RS	Pinhal da Serra
4918	4314472	RS	Pinhal Grande
4919	4314498	RS	Pinheirinho do Vale
4920	4314506	RS	Pinheiro Machado
4921	4314555	RS	Pirapó
4922	4314605	RS	Piratini
4923	4314704	RS	Planalto
4924	4314753	RS	Poço das Antas
4925	4314779	RS	Pontão
4926	4314787	RS	Ponte Preta
4927	4314803	RS	Portão
4928	4314902	RS	Porto Alegre
4929	4315008	RS	Porto Lucena
4930	4315057	RS	Porto Mauá
4931	4315073	RS	Porto Vera Cruz
4932	4315107	RS	Porto Xavier
4933	4315131	RS	Pouso Novo
4934	4315149	RS	Presidente Lucena
4935	4315156	RS	Progresso
4936	4315172	RS	Protásio Alves
4937	4315206	RS	Putinga
4938	4315305	RS	Quaraí
4939	4315313	RS	Quatro Irmãos
4940	4315321	RS	Quevedos
4941	4315354	RS	Quinze de Novembro
4942	4315404	RS	Redentora
4943	4315453	RS	Relvado
4944	4315503	RS	Restinga Seca
4945	4315552	RS	Rio dos Índios
4946	4315602	RS	Rio Grande
4947	4315701	RS	Rio Pardo
4948	4315750	RS	Riozinho
4949	4315800	RS	Roca Sales
4950	4315909	RS	Rodeio Bonito
4951	4315958	RS	Rolador
4952	4316006	RS	Rolante
4953	4316105	RS	Ronda Alta
4954	4316204	RS	Rondinha
4955	4316303	RS	Roque Gonzales
4956	4316402	RS	Rosário do Sul
4957	4316428	RS	Sagrada Família
4958	4316436	RS	Saldanha Marinho
4959	4316451	RS	Salto do Jacuí
4960	4316477	RS	Salvador das Missões
4961	4316501	RS	Salvador do Sul
4962	4316600	RS	Sananduva
4963	4316709	RS	Santa Bárbara do Sul
4964	4316733	RS	Santa Cecília do Sul
4965	4316758	RS	Santa Clara do Sul
4966	4316808	RS	Santa Cruz do Sul
4967	4316972	RS	Santa Margarida do Sul
4968	4316907	RS	Santa Maria
4969	4316956	RS	Santa Maria do Herval
4970	4317202	RS	Santa Rosa
4971	4317251	RS	Santa Tereza
4972	4317301	RS	Santa Vitória do Palmar
4973	4317004	RS	Santana da Boa Vista
4974	4317103	RS	Sant'Ana do Livramento
4975	4317400	RS	Santiago
4976	4317509	RS	Santo Ângelo
4977	4317608	RS	Santo Antônio da Patrulha
4978	4317707	RS	Santo Antônio das Missões
4979	4317558	RS	Santo Antônio do Palma
4980	4317756	RS	Santo Antônio do Planalto
4981	4317806	RS	Santo Augusto
4982	4317905	RS	Santo Cristo
4983	4317954	RS	Santo Expedito do Sul
4984	4318002	RS	São Borja
4985	4318051	RS	São Domingos do Sul
4986	4318101	RS	São Francisco de Assis
4987	4318200	RS	São Francisco de Paula
4988	4318309	RS	São Gabriel
4989	4318408	RS	São Jerônimo
4990	4318424	RS	São João da Urtiga
4991	4318432	RS	São João do Polêsine
4992	4318440	RS	São Jorge
4993	4318457	RS	São José das Missões
4994	4318465	RS	São José do Herval
4995	4318481	RS	São José do Hortêncio
4996	4318499	RS	São José do Inhacorá
4997	4318507	RS	São José do Norte
4998	4318606	RS	São José do Ouro
4999	4318614	RS	São José do Sul
5000	4318622	RS	São José dos Ausentes
5001	4318705	RS	São Leopoldo
5002	4318804	RS	São Lourenço do Sul
5003	4318903	RS	São Luiz Gonzaga
5004	4319000	RS	São Marcos
5005	4319109	RS	São Martinho
5006	4319125	RS	São Martinho da Serra
5007	4319158	RS	São Miguel das Missões
5008	4319208	RS	São Nicolau
5009	4319307	RS	São Paulo das Missões
5010	4319356	RS	São Pedro da Serra
5011	4319364	RS	São Pedro das Missões
5012	4319372	RS	São Pedro do Butiá
5013	4319406	RS	São Pedro do Sul
5014	4319505	RS	São Sebastião do Caí
5015	4319604	RS	São Sepé
5016	4319703	RS	São Valentim
5017	4319711	RS	São Valentim do Sul
5018	4319737	RS	São Valério do Sul
5019	4319752	RS	São Vendelino
5020	4319802	RS	São Vicente do Sul
5021	4319901	RS	Sapiranga
5022	4320008	RS	Sapucaia do Sul
5023	4320107	RS	Sarandi
5024	4320206	RS	Seberi
5025	4320230	RS	Sede Nova
5026	4320263	RS	Segredo
5027	4320305	RS	Selbach
5028	4320321	RS	Senador Salgado Filho
5029	4320354	RS	Sentinela do Sul
5030	4320404	RS	Serafina Corrêa
5031	4320453	RS	Sério
5032	4320503	RS	Sertão
5033	4320552	RS	Sertão Santana
5034	4320578	RS	Sete de Setembro
5035	4320602	RS	Severiano de Almeida
5036	4320651	RS	Silveira Martins
5037	4320677	RS	Sinimbu
5038	4320701	RS	Sobradinho
5039	4320800	RS	Soledade
5040	4320859	RS	Tabaí
5041	4320909	RS	Tapejara
5042	4321006	RS	Tapera
5043	4321105	RS	Tapes
5044	4321204	RS	Taquara
5045	4321303	RS	Taquari
5046	4321329	RS	Taquaruçu do Sul
5047	4321352	RS	Tavares
5048	4321402	RS	Tenente Portela
5049	4321436	RS	Terra de Areia
5050	4321451	RS	Teutônia
5051	4321469	RS	Tio Hugo
5052	4321477	RS	Tiradentes do Sul
5053	4321493	RS	Toropi
5054	4321501	RS	Torres
5055	4321600	RS	Tramandaí
5056	4321626	RS	Travesseiro
5057	4321634	RS	Três Arroios
5058	4321667	RS	Três Cachoeiras
5059	4321709	RS	Três Coroas
5060	4321808	RS	Três de Maio
5061	4321832	RS	Três Forquilhas
5062	4321857	RS	Três Palmeiras
5063	4321907	RS	Três Passos
5064	4321956	RS	Trindade do Sul
5065	4322004	RS	Triunfo
5066	4322103	RS	Tucunduva
5067	4322152	RS	Tunas
5068	4322186	RS	Tupanci do Sul
5069	4322202	RS	Tupanciretã
5070	4322251	RS	Tupandi
5071	4322301	RS	Tuparendi
5072	4322327	RS	Turuçu
5073	4322343	RS	Ubiretama
5074	4322350	RS	União da Serra
5075	4322376	RS	Unistalda
5076	4322400	RS	Uruguaiana
5077	4322509	RS	Vacaria
5078	4322533	RS	Vale do Sol
5079	4322541	RS	Vale Real
5080	4322525	RS	Vale Verde
5081	4322558	RS	Vanini
5082	4322608	RS	Venâncio Aires
5083	4322707	RS	Vera Cruz
5084	4322806	RS	Veranópolis
5085	4322855	RS	Vespasiano Correa
5086	4322905	RS	Viadutos
5087	4323002	RS	Viamão
5088	4323101	RS	Vicente Dutra
5089	4323200	RS	Victor Graeff
5090	4323309	RS	Vila Flores
5091	4323358	RS	Vila Lângaro
5092	4323408	RS	Vila Maria
5093	4323457	RS	Vila Nova do Sul
5094	4323507	RS	Vista Alegre
5095	4323606	RS	Vista Alegre do Prata
5096	4323705	RS	Vista Gaúcha
5097	4323754	RS	Vitória das Missões
5098	4323770	RS	Westfalia
5099	4323804	RS	Xangri-lá
5100	5000203	MS	Água Clara
5101	5000252	MS	Alcinópolis
5102	5000609	MS	Amambai
5103	5000708	MS	Anastácio
5104	5000807	MS	Anaurilândia
5105	5000856	MS	Angélica
5106	5000906	MS	Antônio João
5107	5001003	MS	Aparecida do Taboado
5108	5001102	MS	Aquidauana
5109	5001243	MS	Aral Moreira
5110	5001508	MS	Bandeirantes
5111	5001904	MS	Bataguassu
5112	5002001	MS	Batayporã
5113	5002100	MS	Bela Vista
5114	5002159	MS	Bodoquena
5115	5002209	MS	Bonito
5116	5002308	MS	Brasilândia
5117	5002407	MS	Caarapó
5118	5002605	MS	Camapuã
5119	5002704	MS	Campo Grande
5120	5002803	MS	Caracol
5121	5002902	MS	Cassilândia
5122	5002951	MS	Chapadão do Sul
5123	5003108	MS	Corguinho
5124	5003157	MS	Coronel Sapucaia
5125	5003207	MS	Corumbá
5126	5003256	MS	Costa Rica
5127	5003306	MS	Coxim
5128	5003454	MS	Deodápolis
5129	5003488	MS	Dois Irmãos do Buriti
5130	5003504	MS	Douradina
5131	5003702	MS	Dourados
5132	5003751	MS	Eldorado
5133	5003801	MS	Fátima do Sul
5134	5003900	MS	Figueirão
5135	5004007	MS	Glória de Dourados
5136	5004106	MS	Guia Lopes da Laguna
5137	5004304	MS	Iguatemi
5138	5004403	MS	Inocência
5139	5004502	MS	Itaporã
5140	5004601	MS	Itaquiraí
5141	5004700	MS	Ivinhema
5142	5004809	MS	Japorã
5143	5004908	MS	Jaraguari
5144	5005004	MS	Jardim
5145	5005103	MS	Jateí
5146	5005152	MS	Juti
5147	5005202	MS	Ladário
5148	5005251	MS	Laguna Carapã
5149	5005400	MS	Maracaju
5150	5005608	MS	Miranda
5151	5005681	MS	Mundo Novo
5152	5005707	MS	Naviraí
5153	5005806	MS	Nioaque
5154	5006002	MS	Nova Alvorada do Sul
5155	5006200	MS	Nova Andradina
5156	5006259	MS	Novo Horizonte do Sul
5157	5006309	MS	Paranaíba
5158	5006358	MS	Paranhos
5159	5006408	MS	Pedro Gomes
5160	5006606	MS	Ponta Porã
5161	5006903	MS	Porto Murtinho
5162	5007109	MS	Ribas do Rio Pardo
5163	5007208	MS	Rio Brilhante
5164	5007307	MS	Rio Negro
5165	5007406	MS	Rio Verde de Mato Grosso
5166	5007505	MS	Rochedo
5167	5007554	MS	Santa Rita do Pardo
5168	5007695	MS	São Gabriel do Oeste
5169	5007802	MS	Selvíria
5170	5007703	MS	Sete Quedas
5171	5007901	MS	Sidrolândia
5172	5007935	MS	Sonora
5173	5007950	MS	Tacuru
5174	5007976	MS	Taquarussu
5175	5008008	MS	Terenos
5176	5008305	MS	Três Lagoas
5177	5008404	MS	Vicentina
5178	5100102	MT	Acorizal
5179	5100201	MT	Água Boa
5180	5100250	MT	Alta Floresta
5181	5100300	MT	Alto Araguaia
5182	5100359	MT	Alto Boa Vista
5183	5100409	MT	Alto Garças
5184	5100508	MT	Alto Paraguai
5185	5100607	MT	Alto Taquari
5186	5100805	MT	Apiacás
5187	5101001	MT	Araguaiana
5188	5101209	MT	Araguainha
5189	5101258	MT	Araputanga
5190	5101308	MT	Arenápolis
5191	5101407	MT	Aripuanã
5192	5101605	MT	Barão de Melgaço
5193	5101704	MT	Barra do Bugres
5194	5101803	MT	Barra do Garças
5195	5101852	MT	Bom Jesus do Araguaia
5196	5101902	MT	Brasnorte
5197	5102504	MT	Cáceres
5198	5102603	MT	Campinápolis
5199	5102637	MT	Campo Novo do Parecis
5200	5102678	MT	Campo Verde
5201	5102686	MT	Campos de Júlio
5202	5102694	MT	Canabrava do Norte
5203	5102702	MT	Canarana
5204	5102793	MT	Carlinda
5205	5102850	MT	Castanheira
5206	5103007	MT	Chapada dos Guimarães
5207	5103056	MT	Cláudia
5208	5103106	MT	Cocalinho
5209	5103205	MT	Colíder
5210	5103254	MT	Colniza
5211	5103304	MT	Comodoro
5212	5103353	MT	Confresa
5213	5103361	MT	Conquista D'Oeste
5214	5103379	MT	Cotriguaçu
5215	5103403	MT	Cuiabá
5216	5103437	MT	Curvelândia
5217	5103452	MT	Denise
5218	5103502	MT	Diamantino
5219	5103601	MT	Dom Aquino
5220	5103700	MT	Feliz Natal
5221	5103809	MT	Figueirópolis D'Oeste
5222	5103858	MT	Gaúcha do Norte
5223	5103908	MT	General Carneiro
5224	5103957	MT	Glória D'Oeste
5225	5104104	MT	Guarantã do Norte
5226	5104203	MT	Guiratinga
5227	5104500	MT	Indiavaí
5228	5104526	MT	Ipiranga do Norte
5229	5104542	MT	Itanhangá
5230	5104559	MT	Itaúba
5231	5104609	MT	Itiquira
5232	5104807	MT	Jaciara
5233	5104906	MT	Jangada
5234	5105002	MT	Jauru
5235	5105101	MT	Juara
5236	5105150	MT	Juína
5237	5105176	MT	Juruena
5238	5105200	MT	Juscimeira
5239	5105234	MT	Lambari D'Oeste
5240	5105259	MT	Lucas do Rio Verde
5241	5105309	MT	Luciara
5242	5105580	MT	Marcelândia
5243	5105606	MT	Matupá
5244	5105622	MT	Mirassol d'Oeste
5245	5105903	MT	Nobres
5246	5106000	MT	Nortelândia
5247	5106109	MT	Nossa Senhora do Livramento
5248	5106158	MT	Nova Bandeirantes
5249	5106208	MT	Nova Brasilândia
5250	5106216	MT	Nova Canaã do Norte
5251	5108808	MT	Nova Guarita
5252	5106182	MT	Nova Lacerda
5253	5108857	MT	Nova Marilândia
5254	5108907	MT	Nova Maringá
5255	5108956	MT	Nova Monte Verde
5256	5106224	MT	Nova Mutum
5257	5106174	MT	Nova Nazaré
5258	5106232	MT	Nova Olímpia
5259	5106190	MT	Nova Santa Helena
5260	5106240	MT	Nova Ubiratã
5261	5106257	MT	Nova Xavantina
5262	5106273	MT	Novo Horizonte do Norte
5263	5106265	MT	Novo Mundo
5264	5106315	MT	Novo Santo Antônio
5265	5106281	MT	Novo São Joaquim
5266	5106299	MT	Paranaíta
5267	5106307	MT	Paranatinga
5268	5106372	MT	Pedra Preta
5269	5106422	MT	Peixoto de Azevedo
5270	5106455	MT	Planalto da Serra
5271	5106505	MT	Poconé
5272	5106653	MT	Pontal do Araguaia
5273	5106703	MT	Ponte Branca
5274	5106752	MT	Pontes e Lacerda
5275	5106778	MT	Porto Alegre do Norte
5276	5106802	MT	Porto dos Gaúchos
5277	5106828	MT	Porto Esperidião
5278	5106851	MT	Porto Estrela
5279	5107008	MT	Poxoréo
5280	5107040	MT	Primavera do Leste
5281	5107065	MT	Querência
5282	5107156	MT	Reserva do Cabaçal
5283	5107180	MT	Ribeirão Cascalheira
5284	5107198	MT	Ribeirãozinho
5285	5107206	MT	Rio Branco
5286	5107578	MT	Rondolândia
5287	5107602	MT	Rondonópolis
5288	5107701	MT	Rosário Oeste
5289	5107750	MT	Salto do Céu
5290	5107248	MT	Santa Carmem
5291	5107743	MT	Santa Cruz do Xingu
5292	5107768	MT	Santa Rita do Trivelato
5293	5107776	MT	Santa Terezinha
5294	5107263	MT	Santo Afonso
5295	5107792	MT	Santo Antônio do Leste
5296	5107800	MT	Santo Antônio do Leverger
5297	5107859	MT	São Félix do Araguaia
5298	5107297	MT	São José do Povo
5299	5107305	MT	São José do Rio Claro
5300	5107354	MT	São José do Xingu
5301	5107107	MT	São José dos Quatro Marcos
5302	5107404	MT	São Pedro da Cipa
5303	5107875	MT	Sapezal
5304	5107883	MT	Serra Nova Dourada
5305	5107909	MT	Sinop
5306	5107925	MT	Sorriso
5307	5107941	MT	Tabaporã
5308	5107958	MT	Tangará da Serra
5309	5108006	MT	Tapurah
5310	5108055	MT	Terra Nova do Norte
5311	5108105	MT	Tesouro
5312	5108204	MT	Torixoréu
5313	5108303	MT	União do Sul
5314	5108352	MT	Vale de São Domingos
5315	5108402	MT	Várzea Grande
5316	5108501	MT	Vera
5317	5105507	MT	Vila Bela da Santíssima Trindade
5318	5108600	MT	Vila Rica
5319	5200050	GO	Abadia de Goiás
5320	5200100	GO	Abadiânia
5321	5200134	GO	Acreúna
5322	5200159	GO	Adelândia
5323	5200175	GO	Água Fria de Goiás
5324	5200209	GO	Água Limpa
5325	5200258	GO	Águas Lindas de Goiás
5326	5200308	GO	Alexânia
5327	5200506	GO	Aloândia
5328	5200555	GO	Alto Horizonte
5329	5200605	GO	Alto Paraíso de Goiás
5330	5200803	GO	Alvorada do Norte
5331	5200829	GO	Amaralina
5332	5200852	GO	Americano do Brasil
5333	5200902	GO	Amorinópolis
5334	5201108	GO	Anápolis
5335	5201207	GO	Anhanguera
5336	5201306	GO	Anicuns
5337	5201405	GO	Aparecida de Goiânia
5338	5201454	GO	Aparecida do Rio Doce
5339	5201504	GO	Aporé
5340	5201603	GO	Araçu
5341	5201702	GO	Aragarças
5342	5201801	GO	Aragoiânia
5343	5202155	GO	Araguapaz
5344	5202353	GO	Arenópolis
5345	5202502	GO	Aruanã
5346	5202601	GO	Aurilândia
5347	5202809	GO	Avelinópolis
5348	5203104	GO	Baliza
5349	5203203	GO	Barro Alto
5350	5203302	GO	Bela Vista de Goiás
5351	5203401	GO	Bom Jardim de Goiás
5352	5203500	GO	Bom Jesus de Goiás
5353	5203559	GO	Bonfinópolis
5354	5203575	GO	Bonópolis
5355	5203609	GO	Brazabrantes
5356	5203807	GO	Britânia
5357	5203906	GO	Buriti Alegre
5358	5203939	GO	Buriti de Goiás
5359	5203962	GO	Buritinópolis
5360	5204003	GO	Cabeceiras
5361	5204102	GO	Cachoeira Alta
5362	5204201	GO	Cachoeira de Goiás
5363	5204250	GO	Cachoeira Dourada
5364	5204300	GO	Caçu
5365	5204409	GO	Caiapônia
5366	5204508	GO	Caldas Novas
5367	5204557	GO	Caldazinha
5368	5204607	GO	Campestre de Goiás
5369	5204656	GO	Campinaçu
5370	5204706	GO	Campinorte
5371	5204805	GO	Campo Alegre de Goiás
5372	5204854	GO	Campo Limpo de Goiás
5373	5204904	GO	Campos Belos
5374	5204953	GO	Campos Verdes
5375	5205000	GO	Carmo do Rio Verde
5376	5205059	GO	Castelândia
5377	5205109	GO	Catalão
5378	5205208	GO	Caturaí
5379	5205307	GO	Cavalcante
5380	5205406	GO	Ceres
5381	5205455	GO	Cezarina
5382	5205471	GO	Chapadão do Céu
5383	5205497	GO	Cidade Ocidental
5384	5205513	GO	Cocalzinho de Goiás
5385	5205521	GO	Colinas do Sul
5386	5205703	GO	Córrego do Ouro
5387	5205802	GO	Corumbá de Goiás
5388	5205901	GO	Corumbaíba
5389	5206206	GO	Cristalina
5390	5206305	GO	Cristianópolis
5391	5206404	GO	Crixás
5392	5206503	GO	Cromínia
5393	5206602	GO	Cumari
5394	5206701	GO	Damianópolis
5395	5206800	GO	Damolândia
5396	5206909	GO	Davinópolis
5397	5207105	GO	Diorama
5398	5208301	GO	Divinópolis de Goiás
5399	5207253	GO	Doverlândia
5400	5207352	GO	Edealina
5401	5207402	GO	Edéia
5402	5207501	GO	Estrela do Norte
5403	5207535	GO	Faina
5404	5207600	GO	Fazenda Nova
5405	5207808	GO	Firminópolis
5406	5207907	GO	Flores de Goiás
5407	5208004	GO	Formosa
5408	5208103	GO	Formoso
5409	5208152	GO	Gameleira de Goiás
5410	5208400	GO	Goianápolis
5411	5208509	GO	Goiandira
5412	5208608	GO	Goianésia
5413	5208707	GO	Goiânia
5414	5208806	GO	Goianira
5415	5208905	GO	Goiás
5416	5209101	GO	Goiatuba
5417	5209150	GO	Gouvelândia
5418	5209200	GO	Guapó
5419	5209291	GO	Guaraíta
5420	5209408	GO	Guarani de Goiás
5421	5209457	GO	Guarinos
5422	5209606	GO	Heitoraí
5423	5209705	GO	Hidrolândia
5424	5209804	GO	Hidrolina
5425	5209903	GO	Iaciara
5426	5209937	GO	Inaciolândia
5427	5209952	GO	Indiara
5428	5210000	GO	Inhumas
5429	5210109	GO	Ipameri
5430	5210158	GO	Ipiranga de Goiás
5431	5210208	GO	Iporá
5432	5210307	GO	Israelândia
5433	5210406	GO	Itaberaí
5434	5210562	GO	Itaguari
5435	5210604	GO	Itaguaru
5436	5210802	GO	Itajá
5437	5210901	GO	Itapaci
5438	5211008	GO	Itapirapuã
5439	5211206	GO	Itapuranga
5440	5211305	GO	Itarumã
5441	5211404	GO	Itauçu
5442	5211503	GO	Itumbiara
5443	5211602	GO	Ivolândia
5444	5211701	GO	Jandaia
5445	5211800	GO	Jaraguá
5446	5211909	GO	Jataí
5447	5212006	GO	Jaupaci
5448	5212055	GO	Jesúpolis
5449	5212105	GO	Joviânia
5450	5212204	GO	Jussara
5451	5212253	GO	Lagoa Santa
5452	5212303	GO	Leopoldo de Bulhões
5453	5212501	GO	Luziânia
5454	5212600	GO	Mairipotaba
5455	5212709	GO	Mambaí
5456	5212808	GO	Mara Rosa
5457	5212907	GO	Marzagão
5458	5212956	GO	Matrinchã
5459	5213004	GO	Maurilândia
5460	5213053	GO	Mimoso de Goiás
5461	5213087	GO	Minaçu
5462	5213103	GO	Mineiros
5463	5213400	GO	Moiporá
5464	5213509	GO	Monte Alegre de Goiás
5465	5213707	GO	Montes Claros de Goiás
5466	5213756	GO	Montividiu
5467	5213772	GO	Montividiu do Norte
5468	5213806	GO	Morrinhos
5469	5213855	GO	Morro Agudo de Goiás
5470	5213905	GO	Mossâmedes
5471	5214002	GO	Mozarlândia
5472	5214051	GO	Mundo Novo
5473	5214101	GO	Mutunópolis
5474	5214408	GO	Nazário
5475	5214507	GO	Nerópolis
5476	5214606	GO	Niquelândia
5477	5214705	GO	Nova América
5478	5214804	GO	Nova Aurora
5479	5214838	GO	Nova Crixás
5480	5214861	GO	Nova Glória
5481	5214879	GO	Nova Iguaçu de Goiás
5482	5214903	GO	Nova Roma
5483	5215009	GO	Nova Veneza
5484	5215207	GO	Novo Brasil
5485	5215231	GO	Novo Gama
5486	5215256	GO	Novo Planalto
5487	5215306	GO	Orizona
5488	5215405	GO	Ouro Verde de Goiás
5489	5215504	GO	Ouvidor
5490	5215603	GO	Padre Bernardo
5491	5215652	GO	Palestina de Goiás
5492	5215702	GO	Palmeiras de Goiás
5493	5215801	GO	Palmelo
5494	5215900	GO	Palminópolis
5495	5216007	GO	Panamá
5496	5216304	GO	Paranaiguara
5497	5216403	GO	Paraúna
5498	5216452	GO	Perolândia
5499	5216809	GO	Petrolina de Goiás
5500	5216908	GO	Pilar de Goiás
5501	5217104	GO	Piracanjuba
5502	5217203	GO	Piranhas
5503	5217302	GO	Pirenópolis
5504	5217401	GO	Pires do Rio
5505	5217609	GO	Planaltina
5506	5217708	GO	Pontalina
5507	5218003	GO	Porangatu
5508	5218052	GO	Porteirão
5509	5218102	GO	Portelândia
5510	5218300	GO	Posse
5511	5218391	GO	Professor Jamil
5512	5218508	GO	Quirinópolis
5513	5218607	GO	Rialma
5514	5218706	GO	Rianápolis
5515	5218789	GO	Rio Quente
5516	5218805	GO	Rio Verde
5517	5218904	GO	Rubiataba
5518	5219001	GO	Sanclerlândia
5519	5219100	GO	Santa Bárbara de Goiás
5520	5219209	GO	Santa Cruz de Goiás
5521	5219258	GO	Santa Fé de Goiás
5522	5219308	GO	Santa Helena de Goiás
5523	5219357	GO	Santa Isabel
5524	5219407	GO	Santa Rita do Araguaia
5525	5219456	GO	Santa Rita do Novo Destino
5526	5219506	GO	Santa Rosa de Goiás
5527	5219605	GO	Santa Tereza de Goiás
5528	5219704	GO	Santa Terezinha de Goiás
5529	5219712	GO	Santo Antônio da Barra
5530	5219738	GO	Santo Antônio de Goiás
5531	5219753	GO	Santo Antônio do Descoberto
5532	5219803	GO	São Domingos
5533	5219902	GO	São Francisco de Goiás
5534	5220058	GO	São João da Paraúna
5535	5220009	GO	São João d'Aliança
5536	5220108	GO	São Luís de Montes Belos
5537	5220157	GO	São Luíz do Norte
5538	5220207	GO	São Miguel do Araguaia
5539	5220264	GO	São Miguel do Passa Quatro
5540	5220280	GO	São Patrício
5541	5220405	GO	São Simão
5542	5220454	GO	Senador Canedo
5543	5220504	GO	Serranópolis
5544	5220603	GO	Silvânia
5545	5220686	GO	Simolândia
5546	5220702	GO	Sítio d'Abadia
5547	5221007	GO	Taquaral de Goiás
5548	5221080	GO	Teresina de Goiás
5549	5221197	GO	Terezópolis de Goiás
5550	5221304	GO	Três Ranchos
5551	5221403	GO	Trindade
5552	5221452	GO	Trombas
5553	5221502	GO	Turvânia
5554	5221551	GO	Turvelândia
5555	5221577	GO	Uirapuru
5556	5221601	GO	Uruaçu
5557	5221700	GO	Uruana
5558	5221809	GO	Urutaí
5559	5221858	GO	Valparaíso de Goiás
5560	5221908	GO	Varjão
5561	5222005	GO	Vianópolis
5562	5222054	GO	Vicentinópolis
5563	5222203	GO	Vila Boa
5564	5222302	GO	Vila Propício
5565	5300108	DF	Brasília
\.


--
-- Name: cidade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cidade_id_seq', 1, false);


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id, aniversario, cpf, rg, cnpj, ie, im, razao, bairro, celular, cep, complemento, data_criacao, email, endereco, nome, telefone, tipo, cidade_id, criado_por_id, estado, numero) FROM stdin;
\.


--
-- Name: cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_seq', 1, true);


--
-- Data for Name: complemento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.complemento (id, nome, valor) FROM stdin;
\.


--
-- Data for Name: complemento_categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.complemento_categoria (complemento_id, categoria_id) FROM stdin;
\.


--
-- Name: complemento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.complemento_id_seq', 1, false);


--
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.empresa (id, cnpj, ie, im, razao, bairro, celular, cep, complemento, data_criacao, email, endereco, estado, nome, numero, telefone, cidade_id, criado_por_id) FROM stdin;
\.


--
-- Name: empresa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empresa_id_seq', 1, false);


--
-- Data for Name: observacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.observacao (id, nome) FROM stdin;
\.


--
-- Data for Name: observacao_categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.observacao_categoria (observacao_id, categoria_id) FROM stdin;
\.


--
-- Name: observacao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.observacao_id_seq', 1, false);


--
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.produto (produto_type, id, data_criacao, descricao, foto, nome, personalizado, unidade, custofatia, margemfatia, vendafatia, fatiahabilitada, custogrande, margemgrande, vendagrande, grandehabilitada, customedia, margemmedia, vendamedia, mediahabilitada, custopequeno, margempequeno, vendapequeno, pequenahabilitada, categoria_id, criado_por_id, receita, custo, margem, venda) FROM stdin;
\.


--
-- Data for Name: produto_categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.produto_categoria (id, nome) FROM stdin;
1	Cervejas
2	Doces e Sobremesas
3	Lanches
4	Pizzas
5	Poções
6	Refeições
7	Refrigerantes
8	Sucos
\.


--
-- Name: produto_categoria_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produto_categoria_id_seq', 8, true);


--
-- Name: produto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produto_id_seq', 1, false);


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id, ativo, login, nivel, bairro, celular, cep, complemento, data_criacao, email, endereco, nome, telefone, senha, ultimo_acesso, cidade_id, criado_por_id, estado, numero, aniversario, cpf, rg) FROM stdin;
\.


--
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 1, false);


--
-- Name: cidade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (id);


--
-- Name: cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- Name: complemento_categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complemento_categoria
    ADD CONSTRAINT complemento_categoria_pkey PRIMARY KEY (complemento_id, categoria_id);


--
-- Name: complemento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complemento
    ADD CONSTRAINT complemento_pkey PRIMARY KEY (id);


--
-- Name: empresa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (id);


--
-- Name: observacao_categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.observacao_categoria
    ADD CONSTRAINT observacao_categoria_pkey PRIMARY KEY (observacao_id, categoria_id);


--
-- Name: observacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.observacao
    ADD CONSTRAINT observacao_pkey PRIMARY KEY (id);


--
-- Name: produto_categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto_categoria
    ADD CONSTRAINT produto_categoria_pkey PRIMARY KEY (id);


--
-- Name: produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


--
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- Name: fk1g0s9g71vjcnj6olc3alip3u2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT fk1g0s9g71vjcnj6olc3alip3u2 FOREIGN KEY (cidade_id) REFERENCES public.cidade(id);


--
-- Name: fk2250wdyhcvpn8qmpo9vy2scd2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fk2250wdyhcvpn8qmpo9vy2scd2 FOREIGN KEY (criado_por_id) REFERENCES public.usuario(id);


--
-- Name: fk22n2f7nqd6if40fmtkeahjacs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT fk22n2f7nqd6if40fmtkeahjacs FOREIGN KEY (categoria_id) REFERENCES public.produto_categoria(id);


--
-- Name: fk44rheunl1yevdmlj08i369a3x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk44rheunl1yevdmlj08i369a3x FOREIGN KEY (cidade_id) REFERENCES public.cidade(id);


--
-- Name: fke67gw8x4o4r6oc35d1xlnb1i9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complemento_categoria
    ADD CONSTRAINT fke67gw8x4o4r6oc35d1xlnb1i9 FOREIGN KEY (complemento_id) REFERENCES public.complemento(id);


--
-- Name: fkhsdotbevqjk9retrd2nruuy2d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT fkhsdotbevqjk9retrd2nruuy2d FOREIGN KEY (criado_por_id) REFERENCES public.usuario(id);


--
-- Name: fkk4dik9g5stkgp1oohudgk5rcd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fkk4dik9g5stkgp1oohudgk5rcd FOREIGN KEY (cidade_id) REFERENCES public.cidade(id);


--
-- Name: fkl54rxqp13am7orf411qhf87ln; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complemento_categoria
    ADD CONSTRAINT fkl54rxqp13am7orf411qhf87ln FOREIGN KEY (categoria_id) REFERENCES public.produto_categoria(id);


--
-- Name: fkpybq3f9nxwfjrlpsmou78eci1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.observacao_categoria
    ADD CONSTRAINT fkpybq3f9nxwfjrlpsmou78eci1 FOREIGN KEY (categoria_id) REFERENCES public.produto_categoria(id);


--
-- Name: fkq6ljbif6mukxn5ucv9h2frcde; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.observacao_categoria
    ADD CONSTRAINT fkq6ljbif6mukxn5ucv9h2frcde FOREIGN KEY (observacao_id) REFERENCES public.observacao(id);


--
-- Name: fkqt1s5jvf3tw4lbt10v1nnwoen; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fkqt1s5jvf3tw4lbt10v1nnwoen FOREIGN KEY (criado_por_id) REFERENCES public.usuario(id);


--
-- Name: fkr12p8njnulacbbwnkaa30h6ty; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT fkr12p8njnulacbbwnkaa30h6ty FOREIGN KEY (criado_por_id) REFERENCES public.usuario(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

