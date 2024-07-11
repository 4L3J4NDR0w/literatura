package com.literatura.literatura.principal;

import com.literatura.literatura.model.Autor;
import com.literatura.literatura.model.DatosLibros;
import com.literatura.literatura.model.DatosResult;
import com.literatura.literatura.model.Libro;
import com.literatura.literatura.repository.LibroRepository;
import com.literatura.literatura.service.ConsumoAPI;
import com.literatura.literatura.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    Scanner teclado = new Scanner(System.in);
    public String url = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepository repository;


    public Principal(LibroRepository repository) {
        this.repository = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Buscar episodioslibros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idiomas
                                  
                    0 - Salir
                    """;
            System.out.println(menu);

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnAnio();
                    break;
                case 5:
                    listarLibrosPorIdiomas();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    //mi metodo para enviarle una url a mi consumoapi
    //que transforma el json en una clase
    //y filtro el primer libro de esa clase como mi record DatosLibros
    private DatosLibros getDatosLibros() {
        System.out.println("Escribe el titulo del libro que deseas buscar");
        var libroBuscado = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(url + "?search=" + libroBuscado.replace(" ", "%20"));
        DatosResult datos = conversor.obtenerDatos(json, DatosResult.class);
        DatosLibros datosLibros = datos.libros().get(0);
        System.out.println("datos libros  "+datosLibros);


        return datosLibros;
    }
    //mi metodo para guardar los libros con toda su informacion en una base de datos
    private void buscarLibro() {
        DatosLibros datos = getDatosLibros();
        Libro existingLibro = repository.findByTitulo(datos.titulo());

        if (existingLibro != null) {
            System.out.println("Error: El libro ya está registrado en la base de datos.");
            System.out.println(existingLibro);
            return; // Salir del método si el libro ya existe
        }

        Libro libro = new Libro(datos);
        System.out.println(libro);
        repository.save(libro);
        System.out.println("Libro guardado correctamente.");
        System.out.println(libro);
    }


    private void listarLibrosRegistrados() {
        System.out.println("Libros registrados en la base de datos: ");
        List<Libro> librosRegistrados =repository.librosRegistrados();
        librosRegistrados.forEach(l->{
            System.out.println("Titulo: " + l.getTitulo() + '\n' +
                    "Autor: " + l.getAutor().getNombre() + '\n' +
                    "Idioma: " + l.getIdiomas().get(0) + '\n' +
                    "Numero de Descargas: " + l.getNumeroDeDescargas() + '\n' +
            "***********************************************************************") ;
        });
    }

    private  void listarAutoresRegistrados() {
        // Lógica para listar todos los autores registrados
        System.out.println("Autores registrados en la base de datos");
        List<Autor>autoresRegistrados = repository.autoresRegistrados();
        autoresRegistrados.forEach(a->{
            System.out.println("Nombre: " + a.getNombre() + '\n' +
                    "Fecha de Nacimiento: " + a.getFechaDeNacimiento() + '\n' +
                    "Fecha de su muerte: " + a.getFechaDeMuerte() + '\n' +
                    "***********************************************************************") ;
        });
    }

    private  void listarAutoresVivosEnAnio() {
        // Lógica para listar autores vivos en un determinado año
        System.out.println("Escriba el año en el que desea buscar");
        var añoBuscado = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autoresPorFecha = repository.buscarAutoresVivosEnFecha(añoBuscado);
        autoresPorFecha .forEach(a->{
            System.out.println("Nombre: " + a.getNombre() + '\n' +
                    "Fecha de Nacimiento: " + a.getFechaDeNacimiento() + '\n' +
                    "Fecha de su muerte: " + a.getFechaDeMuerte() + '\n' +
                    "***********************************************************************") ;
        });

    }

    private void listarLibrosPorIdiomas() {
        // Lógica para listar libros por idiomas
        System.out.println("""
                escriba el idioma que desea filtrar 
                por ahora solo esta
                en""");
        var idioma = teclado.nextLine();
        List<Libro> librosFiltrados = repository.filtrarPorIdioma(idioma);
        librosFiltrados.forEach(l->{
            System.out.println("Titulo: " + l.getTitulo() + '\n' +
                    "Autor: " + l.getAutor().getNombre() + '\n' +
                    "Idioma: " + l.getIdiomas().get(0) + '\n' +
                    "Numero de Descargas: " + l.getNumeroDeDescargas() + '\n' +
                    "***********************************************************************") ;
        });
    }

}
