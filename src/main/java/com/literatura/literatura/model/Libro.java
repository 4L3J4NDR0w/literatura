package com.literatura.literatura.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Optional;
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Integer numeroDeDescargas;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private List<String> idiomas;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Autor autor;

    // Constructor vacío requerido por JPA
    Libro() {}

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
        this.idiomas = datosLibros.idiomas();

        // Asignar el autor
        if (datosLibros.autores() != null && !datosLibros.autores().isEmpty()) {
            DatosAutores datosAutor = datosLibros.autores().get(0);
            this.autor = new Autor(datosAutor);
        }
    }


    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", numeroDeDescargas=" + numeroDeDescargas +
                ", idiomas=" + idiomas +
                ", autor=" + autor
                ;
    }
}
