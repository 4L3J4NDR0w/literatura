package com.literatura.literatura.repository;

import com.literatura.literatura.model.Autor;
import com.literatura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Libro findByTitulo(String titulo);
    //listas libros registrados
    @Query("SELECT l FROM Libro l")
    List<Libro>librosRegistrados();

    //listrar autores registrados
    @Query("SELECT a FROM Autor a")
    List<Autor>autoresRegistrados();

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :fecha AND (a.fechaDeMuerte > :fecha OR a.fechaDeMuerte IS NULL)")
    List<Autor> buscarAutoresVivosEnFecha(Integer fecha);

    @Query("SELECT l FROM Libro l JOIN l.idiomas idioma WHERE idioma = :idioma")
    List<Libro> filtrarPorIdioma(@Param("idioma") String idioma);
}
