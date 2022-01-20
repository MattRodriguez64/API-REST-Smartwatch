package app;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import lombok.extern.log4j.Log4j2;

@ApplicationPath("api")
@Log4j2
public class RestAPI extends ResourceConfig {

	public RestAPI() {
		packages("resource");
	}

}

