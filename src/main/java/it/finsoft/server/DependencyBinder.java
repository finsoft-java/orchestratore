package it.finsoft.server;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.jboss.logging.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import it.finsoft.manager.*;

/**
 * HK2-specifice feature for configuring injections. In a better EE environment
 * it should not be needed. By default, HK2 does *not* scan existing services.
 * We use Reflections for doing this. If you prefer, you can manually bind
 * classes you want.
 *
 */
public class DependencyBinder extends AbstractBinder {

	public final static boolean AUTOSCAN = true; // Manual configuration is faster
												 // than autoscan

	public final static String MANAGERS_PACKAGE = "it.finsoft.manager"; // Without
																		// filters
																		// it's
																		// slow
	public static Logger LOG = Logger.getLogger(DependencyBinder.class);
	
	@Override
	protected void configure() {

		LOG.info("Configuring injection bindings...");

		if (AUTOSCAN) {
			List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
			classLoadersList.add(ClasspathHelper.contextClassLoader());
			classLoadersList.add(ClasspathHelper.staticClassLoader());

			Reflections reflections = new Reflections(
					new ConfigurationBuilder().setScanners(new SubTypesScanner(false), new ResourcesScanner())
							.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
							.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(MANAGERS_PACKAGE))));

			Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

			for (Class<?> type : classes)
				bind(type).to(type);

		} else {
			bind(CalendarioManager.class).to(CalendarioManager.class);
			bind(CalendarioSemaforoManager.class).to(CalendarioSemaforoManager.class);
			bind(DettaglioEventoManager.class).to(DettaglioEventoManager.class);
			bind(EntitaManager.class).to(EntitaManager.class);
			bind(EventoManager.class).to(EventoManager.class);
			bind(MilestoneManager.class).to(MilestoneManager.class);
			bind(SemaforoManager.class).to(SemaforoManager.class);
			bind(SemaforoMilestoneManager.class).to(SemaforoMilestoneManager.class);
			bind(TipoEventoManager.class).to(TipoEventoManager.class);
		}

		bindFactory(EntityManagerFactoryHK2.class).to(EntityManager.class);

		// REM
		// request scope binding
		// bind(MyInjectablePerRequest.class)
		// .to(MyInjectablePerRequest.class)
		// .in(RequestScope.class);
		//
		// singleton binding
		// bind(MyInjectableSingleton.class).in(Singleton.class);
		//
		// singleton instance binding
		// bind(new MyInjectableSingleton()).to(MyInjectableSingleton.class);
	}

}