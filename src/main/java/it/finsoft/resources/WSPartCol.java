package it.finsoft.resources;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("PartCol")
@Produces({ MediaType.TEXT_PLAIN })
public class WSPartCol {

	@GET
	public String get(@QueryParam("anno") String anno, @QueryParam("mese") String mese, @QueryParam("giro") String giro,
			@QueryParam("lettera") String lettera) {

		// TODO
		// es. P2017051LS
		// Ma devo accedere alla funzione GET_PART_COL di U7B20...
		return "TODO";
	}
}
