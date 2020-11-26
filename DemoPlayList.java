package TpObjetos1;

public class DemoPlayList {

	public static void main(String[] args) {
		PlayList p = new PlayList();
		
			
		
		p.agregarCancion(new Cancion("hola",50,"artista1"));
		p.agregarCancion(new Cancion("chau",1,"artista1"));
		p.agregarCancion(new Cancion("bye",8,"tres"));
		p.agregarCancion(new Cancion("torero",12,"tres"));
		p.mostrarPlaylistOrdenadaPorTitulo();
	    System.out.println("La duracion de la cancion bye es:"+p.consultarxtitu("bye"));
		System.out.println("La duracion de bye es:"+""+p.consultarxtitu("bye"));
	    System.out.println("---------");
		p.reproducirPlaylist("titulo");
		System.out.println("------------");
		p.reproducirPlaylist("artista");
		System.out.println("-----------");
		p.reproducirPlaylist("aleatorio");
		
		System.out.println("-------");
		//System.out.println("duracion de hola:"+p.consultarxtitu("hola"));
		System.out.println("la cantidad de canciones es:"+p.consultarCantidadDeCancionesCargadas());
		//p.eliminarporTituloYArtista("chau", "artista1");
		
		
		System.out.println("la cantidad de canciones es:"+p.consultarCantidadDeCancionesCargadas());
		System.out.println(p.consultarMenorCancion());
		System.out.println(p.consultarLarga());
		System.out.println("La duracion total de la PlayList es:\n");
		System.out.println(p.consultarDuracionTotal());
	    

}}
