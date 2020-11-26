package TpObjetos1;
import java.util.Arrays;
import java.util.Random;

public class PlayList {
	public PlayList() {
		this(1000);//crea la lista con 1000 posiciones
	}
	public PlayList(int cantidad) {//constructor donde pide la cantidad de canciones
		if (cantidad <= 1) {//si la cantidad es menor que 1 crea la playlist con 10
			cantidad= 10;
		} else if (cantidad > 10000) {//si la cantidad supera las 10000 crea una list con 10000

			cantidad = 10000;
		}
		this.lista = new Cancion[cantidad];

		this.ordenadasPorArtista = new int[cantidad];
		Arrays.fill(ordenadasPorArtista, Integer.MAX_VALUE);
		this.setEstaOrdenadaPorTipo("ARTISTA", true);

		this.ordenadasPorTitulo = new int[cantidad];
		Arrays.fill(ordenadasPorTitulo, Integer.MAX_VALUE);
		this.setEstaOrdenadaPorTipo("TITULO", true);

	}//AL NO USAR MANEJO DE EXCEPCIONES TUVE QUE CORREGIR LAS EXCEPCIONES DE TIPO
	//ARRAYINDEXOUT Y NULLPOINTER
	public boolean agregarCancion(Cancion cancion) {//agregado de canciones
		if (cancion == null) return false;//no agrega nada
		if (this.lista.length == this.getcontador()) {//verifica que no se quiera cargar mas de lo que se puede
			System.out.println("NO HAY ESPACIO");
			return false;
		} else {
			if (cancion.getPorTipo("ARTISTA") == null || 
					(this.contadorcanciones > 0 &&
							this.OrdenadaxArtista &&
							cancion.getPorTipo("ARTISTA").compareToIgnoreCase(this.lista[this.contadorcanciones -1].getPorTipo("ARTISTA")) < 0)
					) this.setEstaOrdenadaPorTipo("ARTISTA", false);
			this.ordenadasPorArtista[this.getcontador()] = this.contadorcanciones;

			if (this.contadorcanciones > 0 &&
					this.ordenadaxtitu &&
					cancion.getPorTipo("TITULO").compareToIgnoreCase(this.lista[this.contadorcanciones -1].getPorTipo("TITULO")) < 0
					) this.setEstaOrdenadaPorTipo("TITULO", false);
			this.ordenadasPorTitulo[this.getcontador()] = this.contadorcanciones;

			this.lista[this.getcontador()] = cancion;
			this.cancionAgregada();//USO EL MEtODO CANCION AGREGADA PARA SUMAR AL CONTADOR
			return true;
		}

	}
	private void cancionAgregada() {
		this.contadorcanciones ++;

	}
	private void cancionEliminada() {
		this.contadorcanciones --;
	}
	private void setEstaOrdenadaPorTipo(String tipo, boolean valor) {
		switch (tipo) {
		case "ARTISTA":
			this.OrdenadaxArtista = valor;

		case "TITULO":
			this.ordenadaxtitu = valor;

		default:
		}

	}
	private boolean getEstaOrdenadaPorTipo(String tipo) {
		switch (tipo) {
		case "ARTISTA":
			return this.OrdenadaxArtista;

		case "TITULO":
			return this.ordenadaxtitu;

		default:
			return false;
		}
	}

	private int getcontador() {
		return this.contadorcanciones;
	}

	//Simula la reproducción de la playlist por el modo que querramos titulo/artista/oaleatorio
	public boolean reproducirPlaylist(String modo) {

		if (getcontador() == 0) {
			System.out.println("Playlist vacia");
			return false;
		} else {
			switch (modo) {
			case "artista":
				if (!this.OrdenadaxArtista) this.burbujeo(this.ordenadasPorArtista, "ARTISTA");
				this.reproducir(this.ordenadasPorArtista);
				break;

			case "titulo":
				if (!this.ordenadaxtitu) this.burbujeo(this.ordenadasPorTitulo, "TITULO");
				this.reproducir(this.ordenadasPorTitulo);
				break;

			case "aleatorio":
				int[] arrayDeAleatorios = this.aleatorios(new Random(), new int[this.getcontador()], 0);
				this.reproducir(arrayDeAleatorios);
				break;
			}
		}
		return true;
	}
	private int[] aleatorios(Random generador, int[] vector, int indice) {//utilizo la clase random
		if (indice == this.getcontador()) return vector;

		int numeroAleatorio = generador.nextInt(this.getcontador());
		boolean encontrado = false;

		for (int i = 0; i < indice && !encontrado; i++) {
			if (vector[i] == numeroAleatorio) encontrado = true;
		}

		if (encontrado) {
			this.aleatorios(generador, vector, indice);
		} else {
			vector[indice] = numeroAleatorio;
			this.aleatorios(generador, vector, indice +1);
		}

		return vector;
	}
	//METODOS DE ORDENAMIENTO
	private int[] burbujeo(int[] posi, String tipo) {

		if (!this.getEstaOrdenadaPorTipo(tipo)) {


			for (int i = 0; i < this.getcontador(); i++) {
				for (int j = 0; j < this.getcontador() -i -1; j++) {

					if (this.lista[posi[j]].getPorTipo(tipo).compareToIgnoreCase(this.lista[posi[j +1]].getPorTipo(tipo)) > 0) {
						int temporaria = posi[j];
						posi[j] = posi[j +1];
						posi[j +1] = temporaria;
					}
				}
			}
		}

		return posi;
	}
	
	//Imprime los datos de cada Cancion de la lista según un array de posiciones que se le pase
	private void reproducir(int[] array) {
		if (array == null) {
			for (int posicion = 0; posicion < this.contadorcanciones; posicion++) {
				System.out.println("Reproduciendo " + this.lista[posicion].getPorTipo("TITULO") +
						(((this.lista[posicion].getPorTipo("ARTISTA").compareTo("") == 0) ? "" : " de " + this.lista[posicion].getPorTipo("ARTISTA"))  ));
			}
		} else {
			for (int posicion = 0; posicion < this.contadorcanciones; posicion++) {
				System.out.println("Reproduciendo " + this.lista[array[posicion]].getPorTipo("TITULO") +
						(((this.lista[array[posicion]].getPorTipo("ARTISTA").compareTo("") == 0) ? "" : " de " + this.lista[array[posicion]].getPorTipo("ARTISTA"))  ));
			}
		}
	}
	public String consultarTituloDeCancion(int posicion) {
		if (secargolacancion(posicion))     
			return this.lista[posicion -1].getTitulo();
		return "no se cargo la cancion";

	}
	private boolean secargolacancion(int posicion) {
		return (posicion -1 <= this.getcontador() && this.lista[posicion -1] != null);
	}
	public int consultarxtitu(String titulo){//Consultamos la duracion de una cancion por su titulo
		//int posi=this.buscarcancionxtitu(titulo);//cambiamos este metodo que no consideraba las canciones 
		//con mismo titulo
		//System.out.println(""+this.lista[posi].getDuracion());
		int duracioncancion = 0;
		for (int i = 0; i < this.getcontador(); i++){
			if (this.lista[i].getPorTipo("TITULO").compareToIgnoreCase(titulo) == 0)
				duracioncancion += this.lista[i].getDuracion();
		}
	
		if (duracioncancion == 0) return -1;
		return duracioncancion;
	}
	public int consultarDuracionTotal(){
		int duracionTotal = 0;
		for (int i = 0; i < this.getcontador(); i++){
			duracionTotal += this.lista[i].getDuracion();
		}

		return duracionTotal;
	}
	public int consultarCantidadDeCancionesCargadas() {
		return this.getcontador();
	}

	private boolean ExisteCancion(int posicion) {
		return (posicion -1 <= this.getcontador() && this.lista[posicion -1] != null);
	}
	//Se puede quitar una canción buscándola previamente por alguno de los datos que la compone.  
	public void eliminarPortitulo(String titulo) {
		for (int i = 0; i < lista.length; i++) {
			if (lista[i]!=null) {
				if (lista[i].getTitulo().equalsIgnoreCase(titulo)) {
					lista[i]=new Cancion("cancion eliminada",0);
					this.cancionEliminada();
				}
			}


		}
	}
	public boolean eliminarPorposi(int posi) {//Remueve la canción ubicada en una posición 
		if (this.ExisteCancion(posi)) {
			for (int i = posi -1; i < this.getcontador(); i++) {
				this.lista[i] = this.lista[i+1];
			}
			this.cancionEliminada();
			System.out.println("Borrada exitosamente");
			return true;
		} else {

			return false;
		}

	}//Se puede quitar una canción buscándola previamente por alguno de los datos que la compone.  
	public boolean eliminarporTituloYArtista(String titulo, String artista) {
		if (titulo == null) titulo = "";
		if (artista == null) artista = "";

		for (int i = 0; i < this.getcontador(); i++) {
			if (this.lista[i].getPorTipo("TITULO").compareToIgnoreCase(titulo) == 0) {
				if (this.lista[i].getPorTipo("ARTISTA").compareToIgnoreCase(artista) == 0) {
					return this.eliminarPorposi(i+1);
				}
			}
		}
		System.out.println("La canción buscada no se encontraba en la playlist");
		return false;
	}
	public String consultarMenorCancion() {//devolvemos el numero de la cancion mas corta

		int duracionMasCorta = Integer.MAX_VALUE;
		int posicionMasCorta = 0;
		for (int i = 0; i < getcontador(); i ++) {
			if (this.lista[i].getDuracion() < duracionMasCorta) {
				duracionMasCorta = this.lista[i].getDuracion();
				posicionMasCorta=i;
			}
		}

		return " la cancion mas cortaes "+ lista[posicionMasCorta].getPorTipo("TITULO");
	}

	public String consultarLarga() {
		int duracionMasLarga = Integer.MIN_VALUE;
		int posicionMasLarga = 0;
		for (int i = 0; i < getcontador(); i ++) {
			if (this.lista[i].getDuracion() > duracionMasLarga) {
				duracionMasLarga = this.lista[i].getDuracion();
				posicionMasLarga = i ;
			}
		}

		return "La cancion mas larga es:"+lista[posicionMasLarga].getPorTipo("TITULO");
	}
	public void mostrarPlaylistOrdenadaPorTitulo() {
        if (!this.ordenadaxtitu) {
            this.burbujeo(this.ordenadasPorTitulo, "TITULO");
        }

        System.out.println("La lista ordenada por título es:");
        for (int i = 0; i < this.ordenadasPorTitulo.length && this.ordenadasPorTitulo[i] != Integer.MAX_VALUE; i++) {
            System.out.println("\t" + (i +1) + ". " + this.lista[this.ordenadasPorTitulo[i]].getTitulo() + ".");
        }
    }
	

	//ATRIBUTOS
	private Cancion[] lista;
	private int contadorcanciones;
	private int[] ordenadasPorArtista;
	private int[] ordenadasPorTitulo;
	private boolean OrdenadaxArtista;
	private boolean ordenadaxtitu;
}