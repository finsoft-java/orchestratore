package it.finsoft.util;

/**
 * Common wrapper to JSON responses Tutte le sottoclassi devono aggiungere un
 * oggetto. Tipicamente il nuovo campo si dovrebbe chiamare "data" (ma JAX-RS 1
 * ha una convenzione differente)
 * 
 * @author u0i8226
 * 
 */
public class CommonJsonResponse {

	public String errorCode = "0";
	public String errorMessage = "";

}
