package org.doogle.gql;

import lombok.Data;

@Data
public class Film {

    private String title;

    private PlanetConnection planetConnection;
}