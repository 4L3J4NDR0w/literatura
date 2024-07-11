package com.literatura.literatura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeMuerte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    // Constructor vacío requerido por JPA
    Autor() {}

    public Autor(DatosAutores datosAutores) {
        this.nombre = limpiarNombreAutor(datosAutores.nombre());
        this.fechaDeNacimiento = datosAutores.fechaDeNacimiento();
        this.fechaDeMuerte = datosAutores.fechaDeMuerte();
    }

    private String limpiarNombreAutor(String nombreAutor) {
        if (nombreAutor.contains(",")) {
            String[] partes = nombreAutor.split(",");
            return partes[1].trim() + " " + partes[0].trim();
        }
        return nombreAutor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(Integer fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", fechaDeMuerte=" + fechaDeMuerte
                ;
    }
}
