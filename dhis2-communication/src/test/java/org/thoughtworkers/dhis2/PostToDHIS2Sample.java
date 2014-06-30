package org.thoughtworkers.dhis2;

import com.google.common.io.Files;
import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.File;

import static com.google.common.base.Charsets.UTF_8;
import static java.lang.System.out;
import static javax.ws.rs.client.ClientBuilder.newClient;
import static javax.ws.rs.client.Invocation.Builder;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

public class PostToDHIS2Sample {
    @Test
    public void testPostDataItem() throws Exception {
        /*
            curl -u wy:Password1 -d @condom.json -H "Content-Type:application/json" \
            "http://localhost:8080/dhis2/api/dataValueSets"
        */
        Client client = newClient().register(new HttpBasicAuthFilter("wy", "Password1"));
        Builder request = client.target("http://localhost:8080/dhis2/api/dataValueSets").request(APPLICATION_JSON_TYPE);

        String jsonPayLoad = Files.toString(new File("condom.json"), UTF_8);
        Response response = request.post(Entity.entity(jsonPayLoad, APPLICATION_JSON));

        out.println(response.readEntity(String.class));
    }
}
