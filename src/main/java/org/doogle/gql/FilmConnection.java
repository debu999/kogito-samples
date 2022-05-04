package org.doogle.gql;

import lombok.Data;

import java.util.List;

@Data
public class FilmConnection {

    private List<Film> films;
}