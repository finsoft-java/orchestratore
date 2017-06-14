# NOTE DI PROGETTO

* WAS: Glassfish
* DB: derby, versione embedded, configurato in Glassfish come DataSource JTA
* Maven
* Persistence framework: JPA + Hibernate 5
* EJB
* JAX-RS

plugin eclipse consigliati:
* egit (per github)
* dbeaver (per accederere al DB)
* FileSync (per aggiornare le risorse statiche senza dover fare redeploy)

Se si usa il server Glassfish *non* embedded: 
* glassfish tools (connettore al server glassfish)

## Installazione: nuova versione (con Glassfish embedded)
Non serve installazione.

Avviare il server con "Run as application" e scegliendo la classe principale it.finsoft.server.EmbeddedGlassfish.

L'applicazione risponde all'indirizzo http://localhost:8080/orchestratoreRADAR

Se invece si vuole avviare con Glassfish server *non* embedded, si vedano le vecchie istruzioni riportate
al fondo di queste note.

## Creazione delle tabelle
Attualmente, le tabelle vengono cancellate e ricreate automaticamente a ogni avvio.
Per evitarlo, rimuovere l'opzione "drop-and-create" nel file persistence.xml.
N.B. in certi casi il processo puo' fallire, e occorre farlo manualmente usando dbeaver. 

## Popolamento delle tabelle con dati default
Attualmente viene eseguito il file script.sql ad ogni avvio.
Per evitarlo, rimuovere l'opzione relativa nel file persistence.xml.
In qualunque momento si puo' rieseguire questo script visitando l'indirizzo:
http://localhost:8080/orchestratoreRADAR/ws/reset

## Utilizzo degli script .BAT/shell per richiamare i webservice
Per utilizzare il WGET scaricare il file "vwget-2.4-wget-1.11.4-bin.zip" dal seguente url:
https://sites.google.com/site/visualwget/a-download-manager-gui-based-on-wget-for-windows.
Estrarre il contenuto in una directory a piacere (esempio c:\Progetti\Wget)

Editare lo script "WS_Collector.bat" inserendo nel set WGET= 
il percorso in cui abbiamo scompattato il file precedente c:\Progetti\WGet\ aggiungendo wget.exe.
Inserire anche l'url che si vuole raggiungere nel set ENDPOINT= (es: http://localhost:8080/orchestratoreRADAR/ws/collector)
Successivamente editare il file "WS_Collector_example_wget.bat" e modificare (se necessario) il percorso dopo "cd" con il 
percorso della cartella dove risiede il file .bat che va eseguito (c:\Progetti\Script)
editare la riga che inizia per "call" con i parametri corretti 
es: call "WS_Collector.bat" U7SC0_BO VALIDAZIONE ParamWGetUltimoTest

Linux: per ambiente Linux i file sono: wgetCollector.sh, wgetCollectorCALL.sh, wgetPolling.sh e wgetPollingCALL.sh
i rispettivi file CALL vengono richiamati da quelli semplici passando i parametri (rimane la limitazione a 9 parametri),
prima di usarli cambiare il path dell'ENDPOINT poiche' non e' localhost ma un indirizzo
Se fosse necessario rendere gli script eseguibili da console linux
	chmod 755 *percorso/nomescript*.sh; chmod +x *percorso/nomescript*.sh
Tramite la riga 'source "percorso/nomescript.sh"' inserita all'inizio dello script 
e' possibile dare un percorso fisso per lo scriptCALL

## Installazione: vecchia versione (con Glassfish server)


### Derby
* scompattare il db (vuoto) dbOrchestratore.7z dalla directory Utility/db, es. in c:\miopercorso\dbOrchestratore
  (non e' necessario installare derby server, perche' utilizziamo la versione embedded)

### Glassfih
* scaricare e installare glassfish. Il link seguente porta direttamente alla pagina download,
  scegliere la versione GF 4.1.2 - Full Platform :
  https://javaee.github.io/glassfish/download
  Installazione di default in C:\glassfish4
* creare il server glassfish in eclipse, questo crea in automatico una cartella di dominio:
  glassfish4/glassfish/domains/domain1
* *non* deployare ancora nulla nel dominio
* Installazione di default in C:\glassfish4
* Operazioni da eseguire da Eclipse (Menu Windows -> Show view -> Servers)
* Tasto dx, new, server
* Nella finestra "Define new server" selezionare Glassfish, lasciare "localhost" come hostname, immettere un server name (di solito si lascia quello di default),
* cliccare su next.
* Nella pagina seguente ci verra' richiesto il path dell'installazione (esempio: C:\glassfish4\glassfish\domains\domain1), mettere la spunta su:
* "use JAR archive for deployment" e cliccare su next.
* Consigliato lasciare la password di admin vuota!!!
* Nella pagina seguente cliccare su finish  (*non* deployare ancora nulla nel dominio). L'aggiunta del progetto in glassfish (per il deployment) 
* la si fara' successivamente alla configurazione dei Data source.

### Correzione versioni librerie
Occorre copiare/sostituire alcune librerie di glassfish, che hanno una versione incompatibile con la nostra:
* Copiare jboss-logging-3.3.0.Final.jar (dalla cartella Utiliy/jar) in c:\glassfish4\glassfish\module
* Copiare org.eclipse.persistence.moxy.jar (dalla cartella Utiliy/jar) in c:\glassfish4\glassfish\module

### File necessari per il datasource Derby
* Copiare i "derby*.jar" contenuti nella cartella Utility/jar nella cartella c:\glassfish4\glassfish\domains\domain1\lib\ext

### Configurazione datasource Derby
* Avviare (o riavviare) il server glassfish da Eclipse 
* Aprire un browser e inserire l'indirizzo http://localhost:4848/ per accedere alla console di amministrazione
* menu' JDBC -> JDBC Connection Pool -> New:
  * Pool name: orchestratore
  * Resources type: javax.sql.DataSource
  * Database Driver Vendor: Embedded-Derby-30
  * cliccare su "next" e andare in fondo alla nuova pagina e nella tabella "Additional Properties" impostare il seguente parametro:
    * DatabaseName: inserire il percorso assoluto del db (es. c:\miopercorso\dbOrchestratore)
    * rimuovere tutte le altre voci
* menu' JDBC -> JDBC Resources -> New
  *  JNDI name: jdbc/orchestratore 
  *  poolname: orchestratore

###Avvio dell'applicazione
Avviare da Eclipse con "Run on server..."

