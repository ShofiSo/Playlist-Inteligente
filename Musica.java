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

    public String toString() {
        return "[" + titulo + " - " + artista + " (" + duracion + " min)]";
    }
}

class Playlist {
    private String nombre;
    private Queue<Cancion> canciones;

    public Playlist(String nombre) {
        this.nombre = nombre;
        this.canciones = new LinkedList<>();
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
                System.out.println("Canción eliminada: " + c);
                return;
            }
        }
        System.out.println("No se encontró la canción: " + titulo);
    }

    public Cancion reproducirSiguiente() {
        return canciones.poll();
    }

    public void ordenarPorDuracion() {
        List<Cancion> lista = new ArrayList<>(canciones);
        lista.sort(Comparator.comparingDouble(Cancion::getDuracion));
        canciones.clear();
        canciones.addAll(lista);
        System.out.println("Playlist ordenada por duración.");
    }

    public void ordenarPorArtista() {
        List<Cancion> lista = new ArrayList<>(canciones);
        lista.sort(Comparator.comparing(Cancion::getArtista));
        canciones.clear();
        canciones.addAll(lista);
        System.out.println("Playlist ordenada por artista.");
    }

    public void mostrarPlaylist() {
        System.out.println("Playlist: " + nombre);
        for (Cancion c : canciones) {
            System.out.println(c);
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
                    System.out.print("Ingrese título de la canción: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese artista: ");
                    String artista = scanner.nextLine();
                    System.out.print("Ingrese duración (En minutos): ");
                    double duracion = scanner.nextDouble();
                    miPlaylist.agregarCancion(new Cancion(titulo, artista, duracion));
                    break;
                case 2:
                    System.out.print("Ingrese el título de la canción a eliminar: ");
                    String tituloEliminar = scanner.nextLine();
                    miPlaylist.eliminarCancion(tituloEliminar);
                    break;
                case 3:
                    Cancion siguiente = miPlaylist.reproducirSiguiente();
                    System.out.println(siguiente != null ? "Reproduciendo: " + siguiente : "No hay más canciones en la playlist.");
                    break;
                case 4:
                    miPlaylist.ordenarPorDuracion();
                    System.out.println("Playlist ordenada por duración.");
                    break;
                case 5:
                    miPlaylist.ordenarPorArtista();
                    System.out.println("Playlist ordenada por artista.");
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