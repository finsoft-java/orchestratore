NOTE DI PROGETTO

WAS: Glassfish
DB: derby, versione embedded, configurato in Glassfish come DataSource JTA
Maven
Persistence framework: JPA + Hibernate 5
EJB
JAX-RS

plugin eclipse consigliati:
* egit (per github)
* dbeaver (per accederere al DB)
* glassfish tools (connettore al server glassfish)


== INSTALLAZIONE ==
-- DERBY --
* scompattare il db (vuoto) dbOrchestratore.7z dalla directory Utility/db, in una cartella a piacere
  (non è necessario installare derby server, perchè utilizziamo la versione embedded)


-- GLASSFISH --
* scaricare e installare glassfish (il link seguente porta direttamente alla pagina download, scegliere la versione GF 4.1.2 - Full Platform)
* (https://javaee.github.io/glassfish/download) 

* Installazione di default in C:\glassfish4
* Operazioni da eseguire da Eclipse (Menu Windows -> Show view -> Servers)
* Cliccare il link "No servers are available ...." o in alternativa tasto dx, new, server
* Nella finestra "Define new server" selezionare Glassfish, lasciare "localhost" come hostname, immettere un server name (di solito si lascia quello di default),
* cliccare su next.
* Nella pagina seguente ci verrà richiesto il path dell'installazione (esempio: C:\glassfish4\glassfish\domains\domain1), mettere la spunta su:
* "use JAR archive for deployment" e cliccare su next.
* Nella pagina seguente cliccare su finish  (*non* deployare ancora nulla nel dominio). L'aggiunta del progetto in glassfish (per il deployment) 
* la si farà successivamente alla configurazione dei jdbc.

*** AGGIUNTA FILE NECESSARI PER IL JDBC ***
* aggiungere i "jar" contenuti nella cartella Utility/jar nella cartella c:\glassfish4\glassfish\domains\domain1\lib\ext

*** CONFIGURAZIONE JDBC ***
* Al termine dei passaggi precedenti avviare il server glassfish (se non si è avviato in automatico), aprire un browser 
* ed inserire l'indirizzo "http://localhost:4848/common/index.jsf" per accedere alla console di amministrazione ed impostare i JDBC 

* per creare un JDBC Connection Pool con i seguenti parametri:
* -Pool name: (noi abbiamo utilizzato orchestratore)
* -Resources type: javax.sql.DataSource
* -Database Driver Vendor: Embedded-Derby-30
* cliccare su next e andare in fondo alla nuova pagina e nella tabella "Additional Properties" impostare il seguente parametro:
*- DatabaseName: inserire il percorso assoluto del db (nel nostro caso c:\workspace\dbOrchestratore)
*- rimuovere tutte le altre voci

* a questo punto si può creare il JDBC Resources con il nome di jdbc/orchestratore, 
* selezionando alla voce poolname il nome scelto nella creazione del connection pool

