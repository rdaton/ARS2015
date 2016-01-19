package pr2r;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class ClaseAleatoria {
	
	private static ClaseAleatoria instance=new ClaseAleatoria();
	
	
	FileWriter fwriter3=null;
	File fichero=null;
	File carpeta=null;
	String nombreCarpeta = "Aleatoria";
	String nombreArchivo4=null;
	
	 private ClaseAleatoria()
	 {
		 //creación de carpeta		
		 carpeta=new File(nombreCarpeta);
	   	 carpeta.mkdir();	   		
	     //fin creación carpeta
					
	 }
	
	 static ClaseAleatoria getInstance()
		{
		return instance;
		}
	 
	 
	 void  aristasAleatoria(int nodos, double p){
	
		StringBuffer texto = new StringBuffer();
		texto.append("Source").append(";").append("Target").append(";").append("Type\n");
		
		//path de fichero		
		nombreArchivo4="aristasAleatoria_"+ nodos + "_nodos_"+ p+".csv";
		try{
		fichero= new File(carpeta,nombreArchivo4);   		
        fwriter3 = new FileWriter(fichero);
	
	 	}catch (IOException f){
	 		f.printStackTrace();
	 	return;
	 	}	 	
        //fin path de fichero
		
		
		if (fwriter3==null) return;
		try {
			
		int i;
		double aux = 0;
		
        for(i=1; i<=nodos; i++){
        	 for(int j=i+1; j<=nodos; j++)
        	 {
        		aux = (Math.random());
             	if(aux < p)
                texto.append(i).append(";").append(j).append(";").append("Undirected").append("\n");
             	fwriter3.write(texto.toString());        		  
        		texto.delete(0, texto.length());
             }
        	 System.out.println("Acabado el nodo " + i + " "+ p);        	 
        }
        
        System.out.println("Aristas red aleatoria " + p + " creadas");
        
        	System.out.println("Red Aleatoria completada");
        
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
		
	 
	 void generarNodos(int nodos)
	 {
		//path de fichero		
		 nombreArchivo4=nodos + "_Nodos.csv";
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
	 
	 
		
		
	   void aleatoria(int nodos, double p){		
		//genero Nodos
		 generarNodos(nodos);
	    //genero Aristas
	    aristasAleatoria(nodos,p);
          
	}
	
			
	}
		
	
	

