package org.doogle.gql;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.core.Document;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static io.smallrye.graphql.client.core.Document.document;
import static io.smallrye.graphql.client.core.Field.field;
import static io.smallrye.graphql.client.core.Operation.operation;

@Path("/")
public class StarWarsResource {
    @Inject
    StarWarsClientApi typesafeClient;
    @Inject
    @GraphQLClient("star-wars-dynamic")
    DynamicGraphQLClient dynamicClient;

    @GET
    @Path("/typesafe")
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public List<Film> getAllFilmsUsingTypesafeClient() {
        return typesafeClient.allFilms().getFilms();
    }

    @GET
    @Path("/dynamic")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Film> getAllFilmsUsingDynamicClient() throws Exception {
        Document query = document(operation(field("allFilms", field("films", field("title"), field("planetConnection", field("planets", field("name")))))));
        Response response = dynamicClient.executeSync(query);
        return response.getObject(FilmConnection.class, "allFilms").getFilms();
    }
}