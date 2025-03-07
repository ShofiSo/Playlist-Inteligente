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