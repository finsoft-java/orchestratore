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
* scaricare (da dove?) derby (senza installarlo)
* scaricare (da dove?) e installare glassfish
* creare il server glassfish in eclipse, questo crea in automatico una cartella di dominio
* *non* deployare ancora nulla nel dominio
* aggiungere derby.jar nella cartella del dominio, sottocartella /lib (corretto?)
* avviare il server glassfish per creare un datasource (per favore cambiamogli JNDI, testDB proprio no)

