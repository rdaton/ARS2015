package pr2r;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

class ClaseLibreEscala {

	private static ClaseLibreEscala instance=new ClaseLibreEscala();
	FileWriter fwriter3=null;
	File fichero=null;
	File carpeta=null;
	String nombreCarpeta = "LibreEscala";
	String nombreArchivo4=null;
	


	 private ClaseLibreEscala()
	 {
		 //creación de carpeta		
		 carpeta=new File(nombreCarpeta);
	   	 carpeta.mkdir();	   		
	     //fin creación carpeta
					
	 }
	 
	 
	 static ClaseLibreEscala getInstance()
		{
		return instance;
		}
	 /****************************
		RED LIBRE DE ESCALA
		
	  */
	 
	
	 
	 void aristasLibreDeEscala(int nodos,int m)
	{
		String texto = "Source;Target;Type\n";
       int i,aux;
       int k, t = 0, contadorLista = 0, p = 0, nodo1, nodo2, nodoAleatLista;
       Nodo nodo= new Nodo();
       Vector auxiliar = new Vector(2);
       Vector listaAristas[] = new Vector[1000000];
		Nodo listaNodos[]= new Nodo[nodos];
		boolean conectado = false;
		for (int x=0; x<nodos; x++){
			listaNodos[x] = new Nodo();
			listaNodos[x].setIdNodo(x+1);
		}
       //Generar aristas
       
       //los 4 o 5(en funcion de m) primeros nodos entre si
		for(k=1; k<=(m+1); k++){
			for(int l=k+1; l<=m+1; l++){
				texto += k+";"+l+";"+"Undirected"+"\n";
				Vector arista = new Vector(2);
				arista.addElement(listaNodos[k-1].getIdNodo());
				arista.addElement(listaNodos[l-1].getIdNodo());
				listaAristas[contadorLista] = arista;
				contadorLista++;
				listaNodos[k-1].aumentarGrado();
				listaNodos[l-1].aumentarGrado();
			}
		}
		if (m == 3) k=4;//sale con valor 5 del for
		else k = 5;
		int T =nodos-(m + 1);
		double probabilidad;
		double probCumpla;
		int nuevoNodo= m+1;
		//resto de aristas
		System.out.println("Procesados nodos iniciales con " + m + " enlaces");
		System.out.println("Procesando resto de nodos...");
		while (t < T){
			k++;//para todos los nuevos nodos que se tienen que a�adir	
			//genero m enlaces 
				for (int n = 0; n < m; n++){	
					aux = (int) (Math.random()*k + 1); //posible nodo al que conectarse, numero entre 1 y 5 en la primera iteracion
			
					//Cojo un indice (nodo) aleatorio de todos los que llevo excepto el nuevo que entra
					nodoAleatLista = (int) (Math.random()*k);
					while (!conectado){						
						//me quedo con su probabilidad
						probabilidad=((double)listaNodos[nodoAleatLista].getGrado()/(double)contadorLista);
						probCumpla=Math.random();
						if(probCumpla<=probabilidad){
							Vector arista = new Vector(2);
							arista.addElement(k);
							arista.addElement(aux);
							listaAristas[contadorLista] = arista;
							contadorLista++;
							p = 0;
							texto += k+";"+aux+";"+"Undirected"+"\n";
							nuevoNodo++;
							conectado = true;
					//		System.out.println("Vuelta " + contadorLista);  
						}
						else
							nodoAleatLista = (int) (Math.random()*k);	
					}
					
					conectado = false;	
				}
			t++;
		}

       try{
    	nombreArchivo4 = "aristasLDE_" + nodos + "_nodos_" + m + "_enlaces.csv";
   		fichero= new File(carpeta,nombreArchivo4);   		
        fwriter3 = new FileWriter(fichero);
        fwriter3.write(texto);
        fwriter3.flush();
        fwriter3.close();
           
       }catch (IOException e){

       }
       System.out.println("Libre de escala "+ nodos + " nodos y "+ m + " enlaces acabada");	
	}
	
	 void generarNodos(int nodos, int id)
	 {
		//path de fichero		
		 nombreArchivo4=nodos + "_Nodos"+"_id"+id+".csv";
		try{
		fichero= new File(carpeta,nombreArchivo4);   		
		fwriter3 = new FileWriter(fichero);			
		}catch (IOException f){
		f.printStackTrace();
		return;
		}	 	
		//fin path de fichero
		String texto = "id;nombre\n";
		int i,aux;
		for(i=1; i<=nodos; i++){
    	 texto += i+";"+"nodo: "+i+"\n";
		}
	    try{
	        
	        fwriter3.write(texto);
	        fwriter3.flush();
	        fwriter3.close();
	        
	    }catch (IOException e){}
	    
	    System.out.println("Nodos de red Libre Escala creados");
	 //fin escritura nodos
	 } 
	 
	 
	 
	void libreDeEscala (int nodos, int m)
	{
		generarNodos(nodos,m);
		aristasLibreDeEscala(nodos,m);		
	}
	
}