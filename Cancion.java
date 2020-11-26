package TpObjetos1;

public class Cancion {
	public Cancion(String titulo, int duracion) {
        this.setDuracion(duracion);
        this.setTitulo(titulo);
    }

    public Cancion(String titulo, int duracion, String artista) {
        this(titulo, duracion);
        this.setArtista(artista);
    }

    public Cancion(String titulo, int duracion, String artista, String album) {
        this(titulo, duracion, artista);
        this.setAlbum(album);
    }

	

	private String titulo;
	private String album;
    private String artista;
    private Integer duracion;
    public String getPorTipo(String tipo) {//METODO QUE DEVUELVE TITULO, DURACION, O ALBUM
        switch (tipo) {
            case "TITULO":
                String titulo = this.getTitulo();
                return titulo == null ? "" : titulo;

            case "DURACION":
                String duracion = this.getDuracion().toString();
                return duracion == null ? "" : duracion;
        
            case "ARTISTA":
                String artista = this.getArtista();
                return artista == null ? "" : artista;
            
            case "ALBUM":
                String album = this.getAlbum();
                return album == null ? "" : album;

            default:
                return "ERRORDETIPOS";
        }
    }
    public String getTitulo() {
        return this.titulo;
    }

    public Integer getDuracion() {
        return this.duracion;
    }

    public String getAlbum() {
        return this.album;
    }

    public String getArtista() {
        return this.artista;
    }

    private void setTitulo(String titulo) {
        if (this.duracion != null) this.titulo = titulo;
    }

    private void setDuracion(int duracion) {
        if (duracion > 0) {
            this.duracion = duracion;
        } else {
            // 
            System.out.println("La duración no puede ser negativa o cero");
        }
    }

    private void setAlbum(String album) {
        if (this.duracion != null) this.album = album;
    }

    private void setArtista(String artista) {
        if (this.duracion != null) this.artista = artista;
    }
}
