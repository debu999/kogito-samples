package org.doogle.gql;

import io.smallrye.graphql.client.typesafe.api.GraphQLClientApi;

@GraphQLClientApi(configKey = "star-wars-typesafe")
public interface StarWarsClientApi {

    FilmConnection allFilms();

}