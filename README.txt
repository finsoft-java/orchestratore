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
* scaricare e scompattare il db (vuoto) dbOrchestratore.7z (senza installarlo) dalla directory Utility/db (possiamo caricarla anche in altri posti)


-- GLASSFISH --
* scaricare e installare glassfish (il link seguente porta direttamente alla pagina download, scegliere la versione GF 4.1.2 - Full Platform)
* (https://javaee.github.io/glassfish/download) 
* creare il server glassfish in eclipse, questo crea in automatico una cartella di dominio
* *non* deployare ancora nulla nel dominio
* aggiungere i "jar" contenuti nella cartella Utility/jar nella cartella glassfish4/glassfish/domains/domain1/lib/ext


* avviare il server glassfish per creare un JDBC Connection Pool con i seguenti parametri:
* -Pool name: (noi abbiamo utilizzato orchestratore)
* -Resources type: javax.sql.DataSource
* -Database Driver Vendor: Embedded-Derby-30
* cliccare su next e andare in fondo alla nuova pagina e nella tabella "Additional Properties" impostare il seguente parametro:
*- DatabaseName: inserire il percorso assoluto del db (nel nostro caso c:\workspace\dbOrchestratore)
*- rimuovere tutte le altre voci

* a questo punto si può creare il JDBC Resources con il nome di jdbc/orchestratore, 
* selezionando alla voce poolname il nome scelto nella creazione del connection pool

