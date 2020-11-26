package TpObjetos1;
import static org.junit.Assert.*;




import org.junit.Test;

import junit.framework.Assert;



public class TestPlayList {
	
	   
	@Test 
	public void testcrearPlayLis() {
		PlayList a = new PlayList();
		a.agregarCancion(new Cancion("Hola",5));
		assertEquals(1, a.consultarCantidadDeCancionesCargadas());
		
	}
	public void verificaQueSeCreaPlayListConTamañoYcancion() {
        PlayList playlist = new PlayList(1000);

        assertEquals(0, playlist.consultarCantidadDeCancionesCargadas());
    }
	@Test
	 public void verificaQueSePuedaCargarUnaCancion() {
		    PlayList playlistDefault= new PlayList();
		    Cancion cancionTest=new Cancion("holis",6);
	        playlistDefault.agregarCancion(cancionTest);

	        assertEquals("holis", playlistDefault.consultarTituloDeCancion(1));
	    }
	@Test
	public void verificareliminacion() {
		PlayList playlistDefault= new PlayList();
	    Cancion cancionTest=new Cancion("holis",6, "");
        playlistDefault.agregarCancion(cancionTest);
        playlistDefault.eliminarporTituloYArtista("holis", "");
        assertEquals(0, playlistDefault.consultarCantidadDeCancionesCargadas());
		
	}
  //  @Test
	/*public void testreproduccion() {
		PlayList p = new PlayList();
		p.agregarCancion(new Cancion("procura",5,"artista"));
		
	//	p.reproducirPlaylist("TITULO");
		assertEquals("Reproduciendo procura de artista",p.reproducirPlaylist("TITULO"));
		
	
	}*/

	
	@Test
	public void verificarDuracionTotal() {
		PlayList p = new PlayList();
		p.agregarCancion(new Cancion("hola",50,"artista1"));
		p.agregarCancion(new Cancion("chau",1,"artista1"));
		p.agregarCancion(new Cancion("bye",8,"tres"));
		p.agregarCancion(new Cancion("torero",12,"tres"));
		assertEquals(71,p.consultarDuracionTotal());
		
	}
	
	
	  
	 
	
}