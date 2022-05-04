package org.doogle.gql.server;

import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.core.Document;
import io.smallrye.graphql.client.core.OperationType;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;
import org.doogle.gql.FilmConnection;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;

import javax.inject.Inject;
import javax.json.JsonObject;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static io.smallrye.graphql.client.core.Argument.arg;
import static io.smallrye.graphql.client.core.Argument.args;
import static io.smallrye.graphql.client.core.Document.document;
import static io.smallrye.graphql.client.core.Field.field;
import static io.smallrye.graphql.client.core.InputObject.inputObject;
import static io.smallrye.graphql.client.core.InputObjectField.prop;
import static io.smallrye.graphql.client.core.Operation.operation;
import static org.doogle.gql.server.LightSaber.BLUE;

@GraphQLApi
public class FilmClientResource {

    @Inject
    @GraphQLClient("local")
    DynamicGraphQLClient client;

    @Mutation("Hero")
    public Hero createHero(Hero hero) throws ExecutionException, InterruptedException {
        Document document = document(
                operation(
                        OperationType.MUTATION,
                        "addHero",
                        field(
                                "createHero",
                                args(arg(
                                        "hero",
                                        inputObject(
                                                prop("name", hero.name),
                                                prop("surname", hero.surname),
                                                prop("lightSaber", hero.lightSaber),
                                                prop("height", hero.height),
                                                prop("mass", hero.mass),
                                                prop("darkSide", hero.darkSide),
                                                prop("episodeIds", hero.episodeIds),
                                                prop("maleFlag", hero.maleFlag)
                                        )
                                )),
                        field("name"),
                        field("surname"),
                        field("lightSaber"),
                        field("height"),
                        field("mass"),
                        field("darkSide"),
                        field("episodeIds"),
                        field("maleFlag")
                )
        ));


        return client.executeSync(document).getObject(Hero.class,"createHero");
    }
}