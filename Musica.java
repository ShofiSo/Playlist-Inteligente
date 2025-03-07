import java.util.*;
import java.util.Scanner;

class Cancion {
    private String titulo;
    private String artista;
    private double duracion;

    public Cancion(String titulo, String artista, double duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
    }

    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public double getDuracion() { return duracion; }

    @Override
    public String toString() {
        return "<---Titulo: " + titulo   + "   Artista: " + artista + "   Duración: " + duracion + " Min---->";

    }
}

class Playlist {
    private String nombre;
    private List<Cancion> canciones;
    private Comparator<Cancion> criterioOrden;

    public Playlist(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
        this.criterioOrden = null; // Sin orden específico al inicio
    }

    public void agregarCancion(Cancion cancion) {
        canciones.add(cancion);
    }

    public void eliminarCancion(String titulo) {
        Iterator<Cancion> iterator = canciones.iterator();
        while (iterator.hasNext()) {
            Cancion c = iterator.next();
            if (c.getTitulo().equalsIgnoreCase(titulo)) {
                iterator.remove();
                System.out.println("Canción eliminada: \n" + c);
                return;
            }
        }
        System.out.println("No se encontró la canción: " + titulo);
    }

    public Cancion reproducirSiguiente() {
        return canciones.isEmpty() ? null : canciones.remove(0);
    }

    public void ordenarPorDuracion() {
        criterioOrden = Comparator.comparingDouble(Cancion::getDuracion);
        canciones.sort(criterioOrden);
        System.out.println("Playlist ordenada por duración.");
    }

    public void ordenarPorArtista() {
        criterioOrden = Comparator.comparing(Cancion::getArtista);
        canciones.sort(criterioOrden);
        System.out.println("Playlist ordenada por artista.");
    }

    public void mostrarPlaylist() {
        System.out.println("Playlist: " + nombre);
        if (canciones.isEmpty()) {
            System.out.println("La playlist está vacía.");
            return;
        }
        if (criterioOrden != null) {
            canciones.sort(criterioOrden);
        }
        for (Cancion c : canciones) {
            System.out.println(canciones);
        }
    }
}

public class Musica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Playlist miPlaylist = new Playlist("Mis Favoritas");

        int opcion;
        do {
            System.out.println("---Menú Playlist---");
            System.out.println("1. Agregar Canción");
            System.out.println("2. Eliminar Canción");
            System.out.println("3. Reproducir Siguiente Canción");
            System.out.println("4. Ordenar por Duración");
            System.out.println("5. Ordenar por Artista");
            System.out.println("6. Mostrar Playlist");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese artista: ");
                    String artista = scanner.nextLine();
                    System.out.print("Ingrese duración (minutos): ");
                    double duracion = scanner.nextDouble();
                    scanner.nextLine(); 
                    miPlaylist.agregarCancion(new Cancion(titulo, artista, duracion));
                    System.out.println("Canción agregada: \n" + titulo + "\n" + artista + "\n" + duracion + " Min");
                    break;
                case 2:
                    System.out.print("Ingrese el título de la canción a eliminar: ");
                    String tituloEliminar = scanner.nextLine();
                    miPlaylist.eliminarCancion(tituloEliminar);
                    break;
                case 3:
                    Cancion siguiente = miPlaylist.reproducirSiguiente();
                    System.out.println(siguiente != null ? "Reproduciendo: \n" + siguiente : "No hay más canciones en la playlist.");
                    break;
                case 4:
                    miPlaylist.ordenarPorDuracion();
                    break;
                case 5:
                    miPlaylist.ordenarPorArtista();
                    break;
                case 6:
                    miPlaylist.mostrarPlaylist();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 7);

        scanner.close();
    }
}

