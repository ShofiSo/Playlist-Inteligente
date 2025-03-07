import java.util.*;

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
    private ArrayList <Cancion> canciones;

    public Playlist(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    public void agregarCancion(Cancion cancion) {
        canciones.add(cancion);
    }

    public void eliminarCancion(String titulo) {
        canciones.removeIf(c -> c.getTitulo().equalsIgnoreCase(titulo));
    }

    public Cancion reproducirSiguiente() {
        return canciones.isEmpty() ? null : canciones.remove(0);
    }

    public void ordenarPorDuracion() {
        canciones.sort(Comparator.comparingDouble(Cancion::getDuracion));
    }

    public void ordenarPorArtista() {
        canciones.sort(Comparator.comparing(Cancion::getArtista));
    }

    public void reproducirAleatorio() {
        Collections.shuffle(canciones);
    }

    public void mostrarPlaylist() {
        System.out.println("Playlist: " + nombre);
        for (Cancion c : canciones) {
            System.out.println(c);
        }
    }
}