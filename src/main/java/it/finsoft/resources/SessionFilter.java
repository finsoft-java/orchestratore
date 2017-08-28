package it.finsoft.resources;

import it.finsoft.entity.Settings;
import it.finsoft.manager.SettingsManager;
import it.finsoft.util.SessionBean;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;

/**
 * Il login è gestito da SWA2. Noi comunque dobbiamo gestire le autorizzazioni.
 * 
 * @author u0i8226
 * 
 */
@WebFilter(urlPatterns = { "*.html", "*.htm", "*.xhtml", "*.jsp" })
public class SessionFilter implements Filter {

	@Inject
	SessionBean sessionBean;
	@Inject
	SettingsManager settingsManager;

	public final static Logger LOG = Logger.getLogger(SessionFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {

		if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {

			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			// String contextPath = request.getContextPath();

			// here sessionBean != null

			if (sessionBean.getWsPath() == null) {
				Settings stg = settingsManager.find();
				if (stg != null && stg.getWsPath() != null)
					sessionBean.setWsPath(stg.getWsPath());
				else
					sessionBean.setWsPath(""); // così evito di ripetere la
												// query tutte le volte
			}

			if (sessionBean.getUserName() == null) {
				loadRequestInBean(sessionBean, request, false);
				
				//TODO swa2
			}

			chain.doFilter(request, response); // Continue chain

		} else {
			// should not pass here
			chain.doFilter(req, resp); // Just continue chain
		}
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	/**
	 * Aggiunge ad un bean le properties che arrivano dalla request (SWA)
	 * 
	 * @param useLocalSWA
	 *            se true, anzichè richiamare SWA verrà usato un XML locale
	 */
	public void loadRequestInBean(SessionBean bean, HttpServletRequest request, boolean useLocalSWA) {

		LOG.info("==========>INIZIO SWA/1<==========");

		String profileId = request.getHeader("profileId");
		bean.setProfileId(profileId);
		LOG.info("profileID ==> " + profileId);

		String userId = request.getHeader("userId");
		LOG.info("userId ==> " + request.getHeader("userId"));
		String utente = "";
		if (userId == null) {
			userId = "syssede\\U0I1234";
			utente = "U0I1234";
		} else {
			String[] parts = userId.split("\\\\");
			utente = parts[1];
		}
		bean.setUserName(utente);
		LOG.info("utente ==> " + utente);

		String svcUrl = request.getHeader("ProfileServicesUrl"); // ex svcUrl
		if (svcUrl == null) {
			// POUN
			svcUrl = "http://sswp20.syssede.systest.sanpaoloimi.com:8024/scriptSwp20/swp2/";
		}
		bean.setSvcUrl(svcUrl);
		LOG.info("svcURL ==> " + svcUrl);

	}
}