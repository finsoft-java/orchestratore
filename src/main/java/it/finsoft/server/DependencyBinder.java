package it.finsoft.server;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import it.finsoft.manager.*;

/**
 * Jetty-specifice feature for configuring injections. In a better EE
 * environment it should not be needed.
 *
 */
public class DependencyBinder extends AbstractBinder {

	@Override
	protected void configure() {

		// SHOUDN'T BE AUTOMATIC ???
		System.err.println("CONFIGURING BINDING - SHOUDN'T BE AUTOMATIC ?!?");
		
<<<<<<< Upstream, based on branch 'jetty' of https://github.com/finsoft-java/orchestratore.git
		bind(CalendarioManager.class).to(CalendarioManager.class);
		bind(CalendarioSemaforoManager.class).to(CalendarioSemaforoManager.class);
		bind(DettaglioEventoManager.class).to(DettaglioEventoManager.class);
		bind(EntitaManager.class).to(EntitaManager.class);
		bind(EventoManager.class).to(EventoManager.class);
		bind(MilestoneManager.class).to(MilestoneManager.class);
		bind(SemaforoManager.class).to(SemaforoManager.class);
		bind(SemaforoMilestoneManager.class).to(SemaforoMilestoneManager.class);
		bind(TipoEventoManager.class).to(TipoEventoManager.class);
=======
		bind(EventoManager.class).to(EventoManager.class);
		bind(CalendarioManager.class).to(CalendarioManager.class);
		bind(CalendarioSemaforoManager.class).to(CalendarioSemaforoManager.class);
		bind(DettaglioEventoManager.class).to(DettaglioEventoManager.class);
		bind(SemaforoManager.class).to(SemaforoManager.class);
		bind(TipoEventoManager.class).to(TipoEventoManager.class);
		bind(MilestoneManager.class).to(MilestoneManager.class);
>>>>>>> cdf0c3c dependency injection

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