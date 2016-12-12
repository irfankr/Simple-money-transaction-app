package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import dao.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.io.File;
@Controller
@SessionAttributes("client_session")
public class MainController {

	/*
	 * Metoda koja prikazuje naslovnicu ClientUsera (tj. Login page) kada se pozove
	 * "http://localhost:8080/SSMTK_T3_ClientUser/"
	 */
	@RequestMapping(value = "/")
	public ModelAndView Login(HttpServletResponse response) throws IOException {
		// Metoda kreira ModelAndView nad home.jsp stranicom
		ModelAndView modelandview = new ModelAndView("login");

		// U ModelAndView se prosljeđuje prazan objekat client
		ClientUser client = new ClientUser();
		modelandview.addObject("client", client);

		return modelandview;
	}

	/*
	 * Metoda se poziva kada se otvori link
	 * http://localhost:8080/SSMTK_T3_ClientUser/login/
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletResponse response)
			throws IOException {
		ModelAndView model = new ModelAndView("login");
		ClientUser client = new ClientUser();
		model.addObject("client", client);
		return model;
	}

	/*
	 * Metoda koja prihvata POST request prilikom popunjavanja Login forme
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("client") ClientUser client) {

		ModelAndView model = null;
		LoginHandler lH = new LoginHandler();

		try {
			// Preuzimanje unešenog userid-ja i passworda
			String Username = client.getUsername();
			String Password = client.getPassword();
			// Provjera na bazi da li postoji korisnik i da li su tačni Username
			// i Password
			boolean isValidUser = lH.checkLogin(Username, Password);
			if (isValidUser) {
				System.out.println("User Login Successful");

				// Redirect na stranicu home.jsp
				model = new ModelAndView("home");
				
				//Definiramo klijenta koji ce biti vracen
				ClientUser logged_client = lH.getLogin(Username);
				
				//Postavljamo clienta i u sesijsku varijablu
				model.addObject("client_session", logged_client);
			} else {
				System.out.println("Else: " + isValidUser);

				// Redirect na stranicu Login.jsp
				model = new ModelAndView("login");
				model.addObject("client", client);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/*
	 * Metoda se poziva kada se otvori link
	 * http://localhost:8080/SSMTK_T3_ClientUser/home/
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletResponse response, HttpSession session)
			throws IOException {
		//Definiramo koja ce se stranica prikazati
		ModelAndView model = new ModelAndView("home");
		
		//Uzimamo klijenta iz sesije
		ClientUser client = (ClientUser)session.getAttribute("client_session");
		
		//Provjera da li postoji klijent koji je ulogovan
		if(client.getId() == 0){
			// Redirect na stranicu Login.jsp
			ClientUser client_return = new ClientUser();
			model = new ModelAndView("login");
			model.addObject("client", client_return);
		}
		
		return model;
	}

	@RequestMapping(value = "/Transakcije")
	public ModelAndView Transakcije(HttpServletResponse response, HttpSession session)
			throws IOException {
		//Uzimamo klijenta iz sesije
		ClientUser client = (ClientUser)session.getAttribute("client_session");

		//Pozivamo REST servis
		String Service_URI = "http://localhost:8080/SSMTK_T3_Server_Service/rest/Server_Service/GetClientTransactions/" + client.getId();
		ClientConfig config = new DefaultClientConfig();
		Client client_rest = Client.create(config);
		WebResource service = client_rest.resource(Service_URI);
		String service_response = service.accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(service_response);

		//Postavljamo vrijednost dobijenog odgovora u response
		JSON_response json_response = new JSON_response();
		json_response.setResponse(service_response);

		// Metoda kreira ModelAndView
		ModelAndView modelandview = new ModelAndView("transakcije");

		//Vracamo klijenta u view da prikazemo koliko novca ima
		modelandview.addObject("clientuser", client);
		modelandview.addObject("json_odgovor", json_response);
		
		return modelandview;
	}

	@RequestMapping(value = "/ProvjeraKorisnika")
	public ModelAndView ProvjeraKorisnika(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		
		//Uzimamo klijenta iz sesije
		ClientUser client = (ClientUser)session.getAttribute("client_session");
		
		//Pozivamo REST servis
		String Service_URI = "http://localhost:8080/SSMTK_T3_Server_Service/rest/Server_Service/ReturnClientStatus/" + client.getId();
		ClientConfig config = new DefaultClientConfig();
		Client client_rest = Client.create(config);
		WebResource service = client_rest.resource(Service_URI);
		String service_response = service.accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(service_response);
		
		//Postavljamo vrijednost dobijenog odgovora u response
		JSON_response json_response = new JSON_response();
		json_response.setResponse(service_response);

		// Metoda kreira ModelAndView nad home.jsp stranicom
		ModelAndView modelandview = new ModelAndView("provjerakorisnika");
		
		//Vracamo klijenta u view da prikazemo koliko novca ima
		modelandview.addObject("clientuser", client);
		modelandview.addObject("json_odgovor", json_response);

		return modelandview;
	}

	@RequestMapping(value = "/ProvjeraTransakcije")
	public ModelAndView ProvjeraTransakcije(HttpServletResponse response, @ModelAttribute("transakcijaprovjera") Transaction transaction, HttpSession session)
			throws IOException {

		// Metoda kreira ModelAndView nad home.jsp stranicom
		ModelAndView modelandview = new ModelAndView("provjeratransakcije");

		return modelandview;
	}
		@RequestMapping("/provjeritransakciju")
		public ModelAndView provjeritransakciju(@ModelAttribute("transakcijaprovjera") Transaction transaction, HttpSession session) {
			// Metoda kreira ModelAndView stranice gdje ce se ipisati status
			ModelAndView modelandview = new ModelAndView("statustransakcije");
			
			//Uzimamo klijenta iz sesije
			ClientUser client = (ClientUser)session.getAttribute("client_session");
			
			//Pozivamo REST servis
			String Service_URI = "http://localhost:8080/SSMTK_T3_Server_Service/rest/Server_Service/ReturnTransactionStatus/" + transaction.getCode();
			ClientConfig config = new DefaultClientConfig();
			Client client_rest = Client.create(config);
			WebResource service = client_rest.resource(Service_URI);
			String service_response = service.accept(MediaType.APPLICATION_JSON).get(String.class);
			System.out.println(service_response);
			
			//Postavljamo vrijednost dobijenog odgovora u response
			JSON_response json_response = new JSON_response();
			json_response.setResponse(service_response);
			
			//Vracamo klijenta u view da prikazemo koliko novca ima
			modelandview.addObject("clientuser", client);
			modelandview.addObject("json_odgovor", json_response);
			
			return modelandview;
		}
		
		
		@RequestMapping(value = "/IzvrsiTransakciju")
		public ModelAndView IzvrsiTransakciju(HttpServletResponse response, @ModelAttribute("Transaction") Transaction transaction)
				throws IOException {

			// Metoda kreira ModelAndView nad home.jsp stranicom
			ModelAndView modelandview = new ModelAndView("izvrsitransakciju");

			return modelandview;
		}
			@RequestMapping("/izvrsitransakcijuakcija")
			public ModelAndView izvrsitransakcijuakcija(@ModelAttribute("Transaction") Transaction transaction, HttpSession session) {
				// Metoda kreira ModelAndView stranice gdje ce se ipisati status
				ModelAndView modelandview = new ModelAndView("transakcijazahtjevposlan");
				
				//Uzimamo klijenta iz sesije
				ClientUser client = (ClientUser)session.getAttribute("client_session");
				
				//Podesavamo da je client izvrsilac transakcije
				transaction.setUser_id(client.getId());
				
				//Formiramo URI na koji saljemo zahjev (u zavisnosti je li uplata, isplata ili transakcija)
				int novac_slanje_rest = (int)transaction.getMoney();
				String Service_URI = "";
				if(transaction.getDescription().equals("Transfer")){
					Service_URI = "http://localhost:8080/SSMTK_T3_Server_Service/rest/Server_Service/MakeTransaction/"+transaction.getUser_id()+"/"+novac_slanje_rest+"/Transfer/" + transaction.getReciever_User_Id();
				} else {
					Service_URI = "http://localhost:8080/SSMTK_T3_Server_Service/rest/Server_Service/MakeTransaction/"+transaction.getUser_id()+"/"+novac_slanje_rest+"/"+transaction.getDescription();
				}
				System.out.println(Service_URI);
				//Saljemo zahtjev servisu za izvrsenje transakcije
				//Pozivamo REST servis
				ClientConfig config = new DefaultClientConfig();
				Client client_rest = Client.create(config);
				WebResource service = client_rest.resource(Service_URI);
				String service_response = service.accept(MediaType.APPLICATION_JSON).get(String.class);
				System.out.println(service_response);

				//Vracamo klijenta u view da prikazemo koliko novca ima
				modelandview.addObject("clientuser", client);
				
				System.out.println("User id: " + transaction.getUser_id());
				System.out.println("Description: " + transaction.getDescription());
				System.out.println("Reciever User Id: " + transaction.getReciever_User_Id());
				System.out.println("Money: " + transaction.getMoney());

				return modelandview;
			}

}
