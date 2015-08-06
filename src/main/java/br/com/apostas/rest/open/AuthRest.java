/*package br.com.apostas.rest.open;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import br.com.apostas.dto.Autenticador;
import br.com.apostas.dto.Payload;
import br.com.apostas.dto.Autenticador.Provider;
import br.com.apostas.misc.AuthUtils;
import br.com.apostas.misc.Token;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthRest {
	
	private final Autenticador atutenticador;
	private final Client client;
	
	public AuthRest(final Client client, final Autenticador atutenticador) {
		this.client = client;
		this.atutenticador = atutenticador;
	}

	//private static final String CLIENT_ID_GOOGLE = "136404311847-c1d3bo8alovde2lf6ijg42potlncqlo5.apps.googleusercontent.com";
	//private static final String CLIENT_SECRET_GOOGLE = "aMkysyPtYkjGvRrJwFS4gbHU";
	//private static final String REDIRECT_URI_KEY = "http://localhost:9000";
	
	public static final String CLIENT_ID_KEY = "client_id", 
			REDIRECT_URI_KEY = "redirect_uri",
			CLIENT_SECRET = "client_secret", 
			CODE_KEY = "code", 
			GRANT_TYPE_KEY = "grant_type",
			AUTH_CODE = "authorization_code",
			AUTH_URI = "auth_uri",
			TOKEN_URI = "token_uri",
			AUTH_PROVIDER = "auth_provider_x509_cert_url",
			REDIRECT_URIS = "redirect_uris",
			JAVASCRIPT_ORIGINS = "javascript_origins";
	public static final String AUTH_HEADER_KEY = "Authorization";
	public static final ObjectMapper MAPPER = new ObjectMapper();
	public static final String CONFLICT_MSG = "There is already a %s account that belongs to you",
			NOT_FOUND_MSG = "User not found",
			LOGING_ERROR_MSG = "Wrong email and/or password",
			UNLINK_ERROR_MSG = "Could not unlink %s account because it is your only sign-in method";

	@POST
	@Path("google")
	public Response loginGoogle(
			@Valid final Payload payload,
			@Context final HttpServletRequest request) throws ParseException,
			JsonParseException, JsonMappingException, IOException, JOSEException {

		final String accessTokenUrl = "https://accounts.google.com/o/oauth2/token";
		final String peopleApiUrl = "https://www.googleapis.com/plus/v1/people/me/openIdConnect";

		Response response;

		final MultivaluedMap<String, String> accessData = new MultivaluedHashMap<String, String>();
		
		if (payload.getClientId() == null){
			payload.setClientId("136404311847-c1d3bo8alovde2lf6ijg42potlncqlo5.apps.googleusercontent.com");
		}
		
		if (payload.getRedirectUri() == null){
			payload.setRedirectUri("http://localhost:9000");
		}
		
		if (atutenticador.getGoogle() == null){
			atutenticador.setGoogle("aMkysyPtYkjGvRrJwFS4gbHU");
		}
		
		if (payload.getCode() == null){
			payload.setCode("4/CSqw-dzfJh8jw97QTVHKmtCVPBrpuIzXIf1faTcw7Zk");
		}
		
		//accessData.add(AUTH_URI, "https://accounts.google.com/o/oauth2/auth");
		//accessData.add(TOKEN_URI, "https://accounts.google.com/o/oauth2/token");
		//accessData.add(AUTH_PROVIDER, "https://www.googleapis.com/oauth2/v1/certs");
		//accessData.add(CLIENT_SECRET, atutenticador.getGoogle());
		//accessData.add(REDIRECT_URIS, "http://localhost:9000/");
		//accessData.add(JAVASCRIPT_ORIGINS, "http://localhost:9000/");
		
		accessData.add(CLIENT_ID_KEY, payload.getClientId());
		accessData.add(REDIRECT_URI_KEY, payload.getRedirectUri());
		accessData.add(CLIENT_SECRET, atutenticador.getGoogle());
		accessData.add(CODE_KEY, payload.getCode());
		accessData.add(GRANT_TYPE_KEY, AUTH_CODE);
		
		WebTarget a = client.target(accessTokenUrl);
		
		Builder b = a.request();
		
		response = b.post(Entity.form(accessData));
		
		accessData.clear();

		final String accessToken = (String) getResponseEntity(response).get("access_token");

		response = client.target(peopleApiUrl).request("text/plain").header(AUTH_HEADER_KEY,
				String.format("Bearer %s", accessToken)).get();

		final Map<String, Object> userInfo = getResponseEntity(response);

		// Step 3. Process the authenticated the user.
		return processUser(request, Provider.GOOGLE, userInfo.get("sub")
				.toString(), userInfo.get("name").toString());
	}

	private Map<String, Object> getResponseEntity(final Response response)
			throws JsonParseException, JsonMappingException, IOException {
		
		return MAPPER.readValue(response.readEntity(String.class),
				new TypeReference<Map<String, Object>>() {
				});
	}

	private Response processUser(final HttpServletRequest request,
			final Provider provider, final String id, final String displayName)
			throws ParseException, JOSEException {
		
		//final Optional<User> user = dao.findByProvider(provider, id);

		// Step 3a. If user is already signed in then link accounts.
		//User userToSave;
		//final String authHeader = request.getHeader(AuthUtils.AUTH_HEADER_KEY);
		//if (StringUtils.isNotBlank(authHeader)) {
			//if (user.isPresent()) {
				//return Response
						//.status(Status.CONFLICT)
						//.entity(new ErrorMessage(String.format(CONFLICT_MSG,
								//provider.capitalize()))).build();
			//}

			//final String subject = AuthUtils.getSubject(authHeader);
			//final Optional<User> foundUser = dao.findById(Long
					//.parseLong(subject));
			//if (!foundUser.isPresent()) {
				//return Response.status(Status.NOT_FOUND)
						//.entity(new ErrorMessage(NOT_FOUND_MSG)).build();
			//}

			//userToSave = foundUser.get();
			//userToSave.setProviderId(provider, id);
			//if (userToSave.getDisplayName() == null) {
				//userToSave.setDisplayName(displayName);
			//}
			//userToSave = dao.save(userToSave);
		//} else {
			// Step 3b. Create a new user account or return an existing one.
			//if (user.isPresent()) {
				//userToSave = user.get();
			//} else {
				//userToSave = new User();
				//userToSave.setProviderId(provider, id);
				//userToSave.setDisplayName(displayName);
				//userToSave = dao.save(userToSave);
			//}
		//}
		
		long testeGetOidUser = 165165;

		final Token token = AuthUtils.createToken(request.getRemoteHost(), testeGetOidUser);
		return Response.ok().entity(token).build();
	}

}*/