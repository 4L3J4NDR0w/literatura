package com.literatura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.beans.Transient;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutores(@JsonAlias("name")String nombre,
                           @JsonAlias("birth_year") Integer fechaDeNacimiento,
                           @JsonAlias("death_year") Integer fechaDeMuerte){
}

