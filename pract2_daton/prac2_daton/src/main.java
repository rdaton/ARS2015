
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Vector;

public class main {

	static final int FASES = 4;
	static final int M = 3;
	//static final int T = 3;

	public static void main(String[] args) {
		int opcion;
		Scanner s = new Scanner(System.in);
		
		menu();
		opcion = s.nextInt();
		while (opcion != 3){
			procesarOpcion(opcion);
			menu();
			opcion = s.nextInt();
		}
		
	}
	
	public static void menu(){
		
		System.out.println("1 - Aleatoria");
		System.out.println("2 - Libre de escala");
		System.out.println("3 - Salir");
		System.out.print ("Elige la opcion: ");
	}
	
	public static void procesarOpcion(int opcion){
		int nodos, t;
		Scanner s = new Scanner(System.in);
		
		switch (opcion){
		case 1:
			System.out.println("Numero de nodos:");
			nodos = s.nextInt();
			aleatoria(nodos);
		break;
		case 2:
			System.out.println("Numero de nodos:");
			nodos = s.nextInt();
			t = nodos - M +1;
			libreDeEscala(nodos); //hay que hacerlo dos veces, para M=3 y para M=4
		break;
		}
	}
	
	/****************************
		RED ALEATORIA
	 */
	private static void aleatoria(int nodos){
		String nombreArchivo = "nodosAleatoria.csv", fase = "";
		double p;
		
		//Generar nodos
        String texto = "id;nombre\n";
        int i,aux;
        for(i=1; i<=nodos; i++){
    		texto += i+";"+"nodo: "+i+"\n";
    	}
	    try{
	        FileWriter fwriter = new FileWriter(nombreArchivo);
	        fwriter.write(texto);
	        fwriter.flush();
	        fwriter.close();
	        
	    }catch (IOException e){}
	    
	    System.out.println("Nodos de red aleatoria creados");
	  //Generar aristas para cada fase	
	    
	    p = ajustarP(nodos);
	    
        for (int j = 1; j <= FASES; j++){
        	switch (j){
        	case 1://Subcritica
        		fase = "Subcritica";
        		aristasAleatoria(nodos,p, fase);
        	break;
        	case 2://Critica
        		fase = "Critica";
        		aristasAleatoria(nodos,p, fase);
        	break;
        	case 3://Supercritica
        		fase = "Supercritica";
        		aristasAleatoria(nodos,p, fase);
        	break;
        	case 4://Conectada
        		fase = "Conectada";
        		aristasAleatoria(nodos,p, fase);
        	break;
        	}
        }  
	}
	
	private static double ajustarP (int nodos){
		double p = 0;
		
		switch (nodos){
		case 500:
			p = 0.002;
		break;
		case 1000:
			p = 0.001;
		break;
		case 5000:
			p = 0.0002;
		break;
		}
		
		return p;
	}
	
	private static void aristasAleatoria(int nodos, double p, String fase){
		String texto = "Source;Target;Type\n", nombreCarpeta = "aristas" + fase;
		File carpeta = new File(nombreCarpeta);
		carpeta.mkdir();
		String nombreArchivo2 = carpeta.getAbsolutePath() + "\\aristas_" + nodos + "_nodos.csv";
		int i=0;
		int j=0;
		double aux = 0;
									  //N=500  	N=1000  	N=5000
		//Subcritica: p<1/N 		 ej:0.001, 	0.0005, 	0.0001
		//Critica: p=1/N  			 ej:0.002, 	0.001,  	0.0002
		//Supercritica: p>1/N   	 ej:0.003, 	0.002,  	0.0003
		//Conectada: p>=lnN/N 		ej:>0.0124, >0.00690, 	>0.001703
		
		double [] cadenaRandoms= new double[5000*5000];
		int r=0; int s=0;
		switch(fase){
		case "Subcritica":
			for(r=1; r<=5000*5000; r++){
				if (nodos == 500) {
				j=i%nodos;
				cadenaRandoms[r]=(int) (Math.random()*2+1)+10000;
				}
				else if (nodos == 1000) {
					cadenaRandoms[r]=(int) ((Math.random())/100);
				}
				else 
					cadenaRandoms[r]=(int)(Math.random())*100;
				
			}; break;
		case "Critica":
		{
			for(r=1; r<=5000*5000; r++){				
				cadenaRandoms[r]=(int) ((Math.random())*100);
			}
		}	break;
		
		
		
		
		}
		
		
        for(i=1; i<=5000*5000; i++){
        	 	 
        		 //Habria que hacer un metodo que calcule el aleatorio en funcion de los nodos
        		 switch (fase){
             	 case "Subcritica":
             		aux=cadenaRandoms[i];
             		if(aux < p)
                    	texto += i+";"+j+";"+"Undirected"+"\n";
             	 break;
             	 case "Critica":
             		double aux3= (p*100);
             		double aux4 =  ((Math.random())*100);
             		
             		if(aux3 == aux4)
                    	texto += i+";"+j+";"+"Undirected"+"\n";
             	 break;
             	 case "Supercritica":
             		if (nodos == 500)
            			 aux = (Math.random()*2+1)/1000;
            		 else if (nodos == 1000)
            			aux = (Math.random())/100;
            		 else aux = (Math.random()*2+1)+10000;
             		if(aux > p)
                    	texto += i+";"+j+";"+"Undirected"+"\n";
             	break;
             	case "Conectada":
             		if (nodos == 500)
            			 aux = (Math.random())/10;
            		 else if (nodos == 1000)
            			aux = (Math.random())/100;
            		 else aux = (Math.random())/100;
             		if(aux >= Math.log(nodos)/nodos)
                    	texto += i+";"+j+";"+"Undirected"+"\n";
             	break;
             	}
             
        }
        try{
            FileWriter fwriter2 = new FileWriter(nombreArchivo2);
            fwriter2.write(texto);
            fwriter2.flush();
            fwriter2.close();
            
        }catch (IOException e){
            
        }
        System.out.println("Aristas red aleatoria " + fase + " creadas");
        if (fase == "Conectada")
        	System.out.println("Red Aleatoria completada");
	}
	
	 /****************************
		RED LIBRE DE ESCALA
		Utilizar� el mismo csv de nodos generado por la aleatoria
	  */
	private static void libreDeEscala(int nodos){
		String nombreCarpeta = "aristasLibreEscala";
		File carpeta = new File(nombreCarpeta);
		carpeta.mkdir();
		String nombreArchivo4 = carpeta.getAbsolutePath() + "\\aristas_" + nodos + "_nodos.csv";
		String texto = "Source;Target;Type\n";
        int i,aux;
        int k, t = 0, contadorLista = 0, p = 0, nodo1, nodo2;
        Nodo nodo= new Nodo();
        Vector auxiliar = new Vector(2);
        Vector listaAristas[] = new Vector[10000];
		Nodo listaNodos[]= new Nodo[nodos];
		for (int x=0; x<nodos; x++){
			listaNodos[x] = new Nodo();
			listaNodos[x].setIdNodo(x+1);
		}
        //Generar aristas
        
        //los 4 primeros nodos entre si
		for(k=1; k<=(M+1); k++){
			for(int l=k+1; l<=M+1; l++){
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
		k=4;//sale con valor 5 del for
		int T =nodos-(M+ 1);
		double probabilidad;
		double probCumpla;
		int nuevoNodo= M+1;
		//resto de aristas
		while (t < T){
			//for (int m = 5; m <= N; m++){
			k++;//para todos los nuevos nodos que se tienen que a�adir	
			//genero m enlaces (3)
				for (int n = 0; n < M; n++){	
					aux = (int) (Math.random()*k + 1); //posible nodo al que conectarse, numero entre 1 y 5 en la primera iteracion
					while (aux == k) // para evitar que un nodo este conectado consigo mismo
						aux = (int) (Math.random()*k + 1);
					//recorro la lista de aristas para ver si ya est� esa arista(tupla)
					while ((p < contadorLista)){
						nodo1 =(int)(listaAristas[p].elementAt(0));
						nodo2 =(int)(listaAristas[p].elementAt(1));
						if (((k == nodo1) && (aux == nodo2)) || ((k == nodo2) && (aux == nodo1))){
							aux = (int) (Math.random()*k+1);
							while (aux == k) // para evitar que un nodo este conectado consigo mismo
								aux = (int) (Math.random()*k + 1);
							p = 0;
						}
						else{
							p++;	
						}
					}
					probabilidad=((double)listaNodos[aux-1].getGrado()/(double)contadorLista);
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
					}
				}
			t++;
		}

        try{
            FileWriter fwriter3 = new FileWriter(nombreArchivo4);
           // fwriter.write('\ufeff'); //si no se escribe esto, excel no muestra bien el texto con tildes
            fwriter3.write(texto);
            fwriter3.flush();
            fwriter3.close();
            
        }catch (IOException e){

        }
        System.out.println("Libre de escala acabada");	
	}
}


	
