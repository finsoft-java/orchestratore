package it.finsoft.server;

import java.io.File;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.Properties;

import org.apache.derby.jdbc.BasicEmbeddedDataSource40;
import org.eclipse.jetty.jndi.factories.MailSessionReference;
import org.eclipse.jetty.plus.jndi.EnvEntry;
import org.eclipse.jetty.plus.jndi.Resource;
import org.eclipse.jetty.plus.jndi.Transaction;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

	// FIXME Questi sono parametri che andrebbero altrove (jetty.xml o
	// jetty-env.xml) però questi file al momento non vengono letti

	public static final Integer PORT = 8080;
	public static final String CONTEXT = "/orchestratoreRADAR";
	public static final String WARFILENAME = "./target/orchestratoreRADAR.war";
	public static final String RESOURCEBASE = "./src/main/webapp/";

	public static void main(String[] args) throws Exception {

		// Create the server
		Server jettyServer = new Server(PORT);

		ClassList classList = ClassList.setServerDefault(jettyServer);
		classList.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration");

		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath(CONTEXT);
		// webapp.setResourceBase("src/main/webapp");

		webapp.setResourceBase(RESOURCEBASE);
		webapp.setDefaultsDescriptor("META-INF/jetty/webdefault.xml");
		webapp.setOverrideDescriptor(RESOURCEBASE + "/WEB-INF/web.xml");
		jettyServer.setHandler(webapp);

		// Set the ContainerIncludeJarPattern so that jetty examines these
		// container-path jars for tlds, web-fragments etc.
		// If you omit the jar that contains the jstl .tlds, the jsp engine will
		// scan for them instead.
		webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
				".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$");

		/*
		 * // Register new transaction manager in JNDI // At runtime, the webapp
		 * accesses this as java:comp/UserTransaction //Transaction
		 * transactionMgr = new Transaction(new com.acme.MockUserTransaction());
		 * 
		 * // Define an env entry with Server scope. // At runtime, the webapp
		 * accesses this as java:comp/env/woggle // This is equivalent to
		 * putting an env-entry in web.xml: // <env-entry> //
		 * <env-entry-name>woggle</env-entry-name> //
		 * <env-entry-type>java.lang.Integer</env-entry-type> //
		 * <env-entry-value>4000</env-entry-value> // </env-entry> EnvEntry
		 * woggle = new EnvEntry(server, "woggle", new Integer(4000), false);
		 * 
		 * // Define an env entry with webapp scope. // At runtime, the webapp
		 * accesses this as java:comp/env/wiggle // This is equivalent to
		 * putting a web.xml entry in web.xml: // <env-entry> //
		 * <env-entry-name>wiggle</env-entry-name> //
		 * <env-entry-value>100</env-entry-value> //
		 * <env-entry-type>java.lang.Double</env-entry-type> // </env-entry> //
		 * Note that the last arg of "true" means that this definition for //
		 * "wiggle" would override an entry of the // same name in web.xml
		 * EnvEntry wiggle = new EnvEntry(webapp, "wiggle", new Double(100),
		 * true);
		 * 
		 * // Register a reference to a mail service scoped to the webapp. //
		 * This must be linked to the webapp by an entry in web.xml: //
		 * <resource-ref> // <res-ref-name>mail/Session</res-ref-name> //
		 * <res-type>javax.mail.Session</res-type> //
		 * <res-auth>Container</res-auth> // </resource-ref> // At runtime the
		 * webapp accesses this as java:comp/env/mail/Session
		 * MailSessionReference mailref = new MailSessionReference();
		 * mailref.setUser("CHANGE-ME"); mailref.setPassword("CHANGE-ME");
		 * Properties props = new Properties(); props.put("mail.smtp.auth",
		 * "false"); props.put("mail.smtp.host", "CHANGE-ME");
		 * props.put("mail.from", "CHANGE-ME"); props.put("mail.debug",
		 * "false"); mailref.setProperties(props); Resource xxxmail = new
		 * Resource(webapp, "mail/Session", mailref);
		 */

		// Register a mock DataSource scoped to the webapp
		// This must be linked to the webapp via an entry in web.xml:
		// <resource-ref>
		// <res-ref-name>jdbc/mydatasource</res-ref-name>
		// <res-type>javax.sql.DataSource</res-type>
		// <res-auth>Container</res-auth>
		// </resource-ref>
		// At runtime the webapp accesses this as
		// java:comp/env/jdbc/mydatasource //
		// Resource mydatasource = new Resource(webapp, "jdbc/orchestratore",
		// new BasicEmbeddedDataSource40()); //FIXME

		jettyServer.start();
		// per debug: jettyServer.dumpStdErr();
		jettyServer.join();
	}
}
