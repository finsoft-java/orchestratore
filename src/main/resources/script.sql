--A differenza dei soliti file SQL, qui ogni comando deve stare su una riga, e non deve terminare con ";"
--svuota le tabelle
DELETE FROM APP.CALENDARI_MILESTONE
DELETE FROM APP.DETTAGLI_EVENTO
DELETE FROM APP.EVENTI
DELETE FROM APP.CALENDARI
DELETE FROM APP.MILESTONE_MILESTONES
DELETE FROM APP.AZIONI
DELETE FROM APP.MILESTONES
DELETE FROM APP.TIPI_EVENTO
DELETE FROM APP.ENTITA
--resetta i numeratori AUTOINCREMENT delle tabelle
ALTER TABLE APP.CALENDARI_MILESTONE ALTER ID_CALENDARIO_MILESTONE RESTART WITH 1
ALTER TABLE APP.DETTAGLI_EVENTO ALTER ID_DETTAGLIO RESTART WITH 1
ALTER TABLE APP.EVENTI ALTER ID_EVENTO RESTART WITH 1
ALTER TABLE APP.CALENDARI ALTER ID_CALENDARIO RESTART WITH 1
ALTER TABLE APP.AZIONI ALTER ID_AZIONE RESTART WITH 1
ALTER TABLE APP.MILESTONES ALTER ID_MILESTONE RESTART WITH 1
ALTER TABLE APP.TIPI_EVENTO ALTER ID_TIPO_EVENTO RESTART WITH 1
ALTER TABLE APP.ENTITA ALTER ID_ENTITA RESTART WITH 1
--qui inizia il popolamento
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7RM0', 'U7RM0ESP', 'ESPOSIZIONI')
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7RM0', 'U7RM0TIT', 'TITOLI')
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7RM0', 'U7RM0ACQ', 'ACQUISIZIONI')
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7SC0', 'U7SC0_CRMS', 'CRMS')
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7SC0', 'U7SC0_BO', 'BO-FINANCE')
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7SC0', 'U7SC0_ADG', 'ADG')
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7SC0', 'U7SC0_SC/ADG', 'SC/ADG')
--aggiunta nuova entita HERMIONE
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7SC0', 'U7SC0_HER', 'HERMIONE') 
--aggiunta nuova entita EDWH CRM-FV
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7PT0', 'U7PT0_EDWH', 'EDWH FV-CRM')
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7PT1', 'U7PT1_FV', 'FV')
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7PT2', 'U7PT2_CRM', 'CRM')
--aggiunta nuova entita ORACLE
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7B20', 'U7B20_ORA', 'ORACLE')
--aggiunta nuova entita MACS2
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7M30', 'U7M30_MACS2', 'MACS2')
--aggiunta nuova entita PAG-RWA
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7TST', 'U7TST_PAG', 'MACS2')
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7TST', 'U7TST_RWA', 'MACS2')
INSERT INTO APP.ENTITA (ACRONIMO, CODICE, DESCRIZIONE) VALUES('U7TST', 'U7TST_HIST', 'MACS2')

INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('LOAD', 'CARICAMENTO')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('CERT', 'CERTIFICAZIONI')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('CHECK', 'VALIDAZIONE')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('PROC', 'ELABORAZIONE')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('SEND', 'INVIO FILE')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('ERR', 'ERRORE FILE')
--aggiunta nuovi tipi evento HERMIONE + MACS1
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('DISPARAM', 'DISPONIBILITA PARAMETRI')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('DISDATI', 'DISPONIBILITA DATI')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('CLOSE', 'CHIUSURA ARCHIVI')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('CERTUSER', 'CERTIFICAZIONE UTENTE')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('MILHER', 'MILESTONE HERMIONE')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('MILMACS1', 'MILESTONE MACS1')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('MILEDWH', 'MILESTONE EDWH FV-CRM')
--aggiunta nuovi tipi evento EDWH CRM-FV
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('RILELDWRFV', 'RILASCIO ELABORAZIONE DWR FV')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('RILFV', 'RILASCIO FV PER CERTIFICAZIONE')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('RILELDWRCRM', 'RILASCIO ELABORAZIONE DWR CRM')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('RILCRM', 'RILASCIO CRM PER CERTIFICAZIONE')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('FEDWHFVCRM', 'FORNITURA FLUSSI EDWH FV-CRM')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('REDWHFVCRM', 'RICEZIONE FLUSSI EDWH FV-CRM')
--aggiunta nuovi tipi evento ORACLE
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('RILORA', 'RILASCIO DISP ORACLE')
--aggiunta nuovi tipi evento MACS2
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('INSRETTETL', 'INS RETTIF ETL-CALCOLI')
--aggiunta nuovi tipi PAG-RWA
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('1STCALPAG', 'PRIMO CALCOLO PAG')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('CERTPAG', 'CERTIFICAZIONE PAG')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('CALRWA', 'CALCOLI RWA')
INSERT INTO APP.TIPI_EVENTO (CODICE, DESCRIZIONE) VALUES('HISTGEST', 'STORICO')

INSERT INTO APP.AZIONI (DESCRIZIONE, FILENAME, TIPO, URL) VALUES('APRI IL MONITORING', 'index.jsp', 'web app', 'http://localhost:8080/orchestratoreRADAR/')
INSERT INTO APP.AZIONI (DESCRIZIONE, FILENAME, TIPO, URL) VALUES('APRI IL MONITORING', 'testPage.jsp', 'web app', 'http://localhost:8080/orchestratoreRADAR/testPage.jsp')
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(1, 2, 'CERTESPMACS1', 'CERTIFICAZIONE ESPOSIZIONI MACS1',null)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(2, 2, 'CERTTITMACS1', 'CERTIFICAZIONE TITOLI MACS1','DESTAG TITMACS1')
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(3, 3, 'MIL3', 'MILESTONE 3','DESTAG MIL3')
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(6, 1, 'MIL4', 'MILESTONE 4',null)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(4, 2, 'MIL5','MILESTONE 5','DESTAG MIL5')
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(5, 3, 'MIL6','MILESTONE 6',null)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(6, 2, 'MIL00','MILESTONE 00','DESTAG MIL00')
--aggiunta nuove milestones HERMIONE + MACS1
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(8, 7, 'DISPTABHER', 'DISP TABELLE HERMIONE', null)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(8, 9, 'CLOSEDATIHER', 'CHIUSURA DATI HERMIONE',null)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(8, 8, 'DISPDATIHER', 'DISP DATI HERMIONE',null)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(null, null, 'ELABDATIHER', 'ELABORAZIONE DATI HERMIONE',null)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(3, 1, 'ENDCARICDATIMACS1', 'FINE CARICAMENTO DATI MACS1',null)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(3, 10, 'CERTUSRMACS1', 'CERT UTENTE MACS1',null)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(null, null, 'TAKEDATIMACS1', 'ACQUISIZIONE DATI MACS1',null)
--aggiunta nuove milestones EDWH CRM-FV
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(9, 18, 'FORNFLUSEDWH', 'FORNITURA FLUSSI EDWH FV-CRM',NULL)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(10, 14, 'RILELABDWRFV', 'RILASCIO ELAB DWR PER FV',NULL) 
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(10, 15, 'RILASCIOFV', 'RILASCIO FV PER CERT','DESTAG RILASCIO FV')
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(10, 2, 'CERTFV', 'CERTIFICAZIONE FV','DESTAG CERT FV')
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(11, 16, 'RILELABDWRCRM', 'RILASCIO ELAB DWR PER CRM',null)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(11, 17, 'RILASCIOCRM', 'RILASCIO CRM PER CERT',null)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(11, 2, 'CERTCRM', 'CERTIFICAZIONE CRM',null)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(9, 19, 'RICFLUEDWH', 'RICEZIONE FLUSSI EDWH FV-CRM',NULL)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(null, null, 'ELABACWQCRMFV', 'ELABORAZIONE ACQUISIZIONE CRM FV',null)
--aggiunta nuove milestones ORACLE
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(12, 20, 'RILASCIOORACLE', 'RILASCIO DISPONIBILITA ORACLE','DESTAG RILASCIO ORACLE')
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(null, null, 'ELABPREPROC', 'ELABORAZIONE PREPROCESSORI',null)
--aggiunta nuove milestones MACS2
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(13, 21, 'INSRETTETLCALC', 'INS RETTIF E LANCIO ETL-CALCOLI',NULL)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(null, null, 'INSRETTORACLEMACS2', 'INS RETTIF ORACLE MACS2',null)
--aggiunta nuove milestones PAG-RWA
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(14, 22, 'PRIMOCALCOLOPAG', 'PRIMO CALCOLO PAG',NULL)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(14, 23, 'CERTIFICAZIONEPAG', 'CERTIFICAZIONE PAG',NULL)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(15, 24, 'CALCOLIRWA', 'CALCOLI RWA (STD, AIRB, FIRB)',NULL)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(16, 25, 'STORICOINSTVALS_GEST', 'STORICIZZAZIONE INSTVALS_GEST',NULL)
INSERT INTO APP.MILESTONES (ID_ENTITA, ID_TIPO_EVENTO, CODICE, DESCRIZIONE, DESCRIZIONE_TAG) VALUES(null, null, 'GESTPAGRWA', 'GESTIONALE PAG RWA','DESTAG PAG RWA')

INSERT INTO APP.MILESTONE_MILESTONES (ID_MILESTONE_CHILD, ID_MILESTONE, ORDINAMENTO) VALUES(4, 1, 1),(2, 3, 2),(2, 4, 1),(3, 5, 1),(3, 6, 2)
INSERT INTO APP.MILESTONE_MILESTONES (ID_MILESTONE_CHILD, ID_MILESTONE, ORDINAMENTO) VALUES(2, 5, 1205),(7, 3, 1)
--aggiunta nuove milestone_milestones HERMIONE
--INSERT INTO APP.MILESTONE_MILESTONES (ID_MILESTONE_CHILD, ID_MILESTONE, ORDINAMENTO) VALUES(9, 8, 2),(10, 9, 1),(10, 11, 3)
INSERT INTO APP.MILESTONE_MILESTONES (ID_MILESTONE_CHILD, ID_MILESTONE, ORDINAMENTO) VALUES(8, 9, 3),(9, 10, 2) --,(10, 11, 1)
--aggiunta nuove milestone_milestones MACS1
INSERT INTO APP.MILESTONE_MILESTONES (ID_MILESTONE_CHILD, ID_MILESTONE, ORDINAMENTO) VALUES(10, 12, 3),(12, 13, 2) --,(13, 14, 1)
--aggiunta nuove milestone_milestones EDWH CRM-FV
INSERT INTO APP.MILESTONE_MILESTONES (ID_MILESTONE_CHILD, ID_MILESTONE, ORDINAMENTO) VALUES(10, 15, 9),(15, 16, 8),(16,17,7),(17,18,6)
INSERT INTO APP.MILESTONE_MILESTONES (ID_MILESTONE_CHILD, ID_MILESTONE, ORDINAMENTO) VALUES(18,19,5),(19,20,4),(20,21,3),(21,22,2) --,(22,23,1)
--aggiunta nuove milestone_milestones ORACLE
INSERT INTO APP.MILESTONE_MILESTONES (ID_MILESTONE_CHILD, ID_MILESTONE, ORDINAMENTO) VALUES(13, 24, 2) --,(24, 25, 1)
--aggiunta nuove milestone_milestones MACS2
INSERT INTO APP.MILESTONE_MILESTONES (ID_MILESTONE_CHILD, ID_MILESTONE, ORDINAMENTO) VALUES(24, 26, 2) --,(26, 27, 1)
--aggiunta nuove milestone_milestones PAG-RWA
INSERT INTO APP.MILESTONE_MILESTONES (ID_MILESTONE_CHILD, ID_MILESTONE, ORDINAMENTO) VALUES(26, 28, 5),(28, 29, 4),(29, 30, 3),(30, 31, 2) --,(31, 32, 1)

INSERT INTO APP.CALENDARI (DESCRIZIONE) VALUES('CRMS MARZO 2017'),('CRMS APRILE 2017'),('DESCRIZIONE CALENDARIO 1'),('DESCRIZIONE CALENDARIO 2')
INSERT INTO APP.CALENDARI (DESCRIZIONE) VALUES('CRMS MAGGIO 2017 Ver A (Foglie)'),('CRMS MAGGIO 2017 Ver B (Padri + TAGS)')
INSERT INTO APP.EVENTI (TSTAMP_EVENTO, TAG, ID_ENTITA, ID_TIPO_EVENTO) VALUES(current timestamp, '201703', 1, 2)
INSERT INTO APP.EVENTI (TSTAMP_EVENTO, TAG, ID_ENTITA, ID_TIPO_EVENTO) VALUES(current timestamp, '201703', 2, 2)
INSERT INTO APP.EVENTI (TSTAMP_EVENTO, TAG, ID_ENTITA, ID_TIPO_EVENTO) VALUES(current timestamp, 'TEST', 3, 3)
INSERT INTO APP.EVENTI (TSTAMP_EVENTO, TAG, ID_ENTITA, ID_TIPO_EVENTO) VALUES(current timestamp, 'TAGEVENTI1', 4, 2)
INSERT INTO APP.EVENTI (TSTAMP_EVENTO, TAG, ID_ENTITA, ID_TIPO_EVENTO) VALUES(current timestamp, 'TEST', 5, 5)
INSERT INTO APP.EVENTI (TSTAMP_EVENTO, TAG, ID_ENTITA, ID_TIPO_EVENTO) VALUES(current timestamp, 'TAGEVENTI1', 6, 1)

--aggiunta nuovi eventi acquisizione dati MACS1
INSERT INTO APP.EVENTI (TSTAMP_EVENTO, TAG, ID_ENTITA, ID_TIPO_EVENTO) VALUES(current timestamp, '201705', 3, 1),(current timestamp, '201705', 3, 10)

INSERT INTO APP.DETTAGLI_EVENTO(CHIAVE,VALORE,ID_EVENTO) VALUES('LOG','WARNING ...',3),('PROGRESS ','30%',3)
INSERT INTO APP.DETTAGLI_EVENTO(CHIAVE,VALORE,ID_EVENTO) VALUES('AVANZAMENTO','80%',2),('AVANZAMENTO','10%',1)
INSERT INTO APP.DETTAGLI_EVENTO (CHIAVE, VALORE, ID_EVENTO) VALUES('AVANZAMENTO','50%', 1)
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201703', 1, 1, 1, '2017-05-20 12:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('P201703U01', 2, 2, 2, '2017-05-30 18:00:00')
--aggiunta nuovi calendari milestone HERMIONE + MACS1 (Calendario Ver A)
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 8, 5, '2017-06-08 00:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 9, 5, '2017-06-09 00:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 10, 5, '2017-06-12 00:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 11, 5, '2017-07-14 23:59:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 12, 5, '2017-06-13 14:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 13, 5, '2017-06-15 13:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 14, 5, '2017-06-15 23:59:00')
--aggiunta nuovi calendari milestone EDWH CRM-FV (Calendario Ver A)
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 15, 5, '2017-06-13 09:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 16, 5, '2017-06-13 18:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 17, 5, '2017-06-14 09:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 18, 5, '2017-06-14 18:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 19, 5, '2017-06-14 23:59:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 20, 5, '2017-06-15 09:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 21, 5, '2017-06-15 18:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 22, 5, '2017-06-15 20:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 23, 5, '2017-06-15 23:59:00')
--aggiunta nuovi calendari milestone ORACLE + MACS2 (Calendario Ver A)
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 24, 5, '2017-06-16 13:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 25, 5, '2017-06-16 23:59:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 26, 5, '2017-06-19 13:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 27, 5, '2017-06-19 23:59:00')
--aggiunta nuovi calendari milestone PAG-RWA (Calendario Ver A)
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 28, 5, '2017-06-20 09:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 29, 5, '2017-06-27 00:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 30, 5, '2017-06-28 00:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 31, 5, '2017-06-28 00:00:00')
INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 32, 5, '2017-06-28 23:59:00')
--aggiunta nuovi calendari milestone HERMIONE + MACS1 + EDWH CRM-FV (Calendario Ver B)
--INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 11, 6, '2017-07-14 23:59:00')
--INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 14, 6, '2017-06-15 23:59:00')
--INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 23, 6, '2017-06-15 23:59:00')
--aggiunta nuovi calendari milestone ORACLE + MACS2 + PAG-RWA (Calendario Ver B)
--INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 25, 6, '2017-06-16 23:59:00')
--INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 27, 6, '2017-06-19 23:59:00')
--INSERT INTO APP.CALENDARI_MILESTONE (TAG, ID_AZIONE, ID_MILESTONE, ID_CALENDARIO, DATA_ORA_PREVISTE) VALUES('201705', null, 32, 6, '2017-06-28 23:59:00')