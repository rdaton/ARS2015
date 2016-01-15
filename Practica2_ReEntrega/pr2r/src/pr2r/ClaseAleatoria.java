package pr2r;

import java.io.FileWriter;
import java.io.IOException;

import com.csvreader.CsvWriter;

class ClaseAleatoria {
	static final int FASES = 4;
	 static void aristasAleatoria(int nodos, double p, String fase){
		StringBuffer texto = new StringBuffer(10000000);
		texto.append("Source").append(";").append("Target").append(";").append("Type\n"); 
		String csv = "C:\\pr2\\aristasAleatoria\\aristasAleatoria_"+ nodos + "_nodos_" + fase;
		try {
			CsvWriter writer = new CsvWriter(new FileWriter(csv), ',');
		int i;
		double aux = 0;
									  //N=500  	N=1000  	N=5000
		//Subcritica: p<1/N 		 ej:0.001, 	0.0005, 	0.0001
		//Critica: p=1/N  			 ej:0.002, 	0.001,  	0.0002
		//Supercritica: p>1/N   	 ej:0.003, 	0.002,  	0.0003
		//Conectada: p>=lnN/N 		ej:>0.0124, >0.00690, 	>0.001703
		
		
        for(i=1; i<=nodos; i++){
        	 for(int j=i+1; j<=nodos; j++){
        		 switch (fase){
             	 case "Subcritica":
             		 aux = (Math.random());
             		 if(aux < p)
                    	texto.append(i).append(";").append(j).append(";").append("Undirected").append("\n");
             	 break;
             	 case "Critica":
             		 aux = (Math.random());
             		 if(aux == p)
             			texto.append(i).append(";").append(j).append(";").append("Undirected").append("\n");
             	 break;
             	 case "Supercritica":
             		 aux = (Math.random());
             		 if(aux > p)
             			texto.append(i).append(";").append(j).append(";").append("Undirected").append("\n");
             	break;
             	case "Conectada":
             		 aux = (Math.random());
             		if(aux >= Math.log(nodos)/nodos)
             			texto.append(i).append(";").append(j).append(";").append("Undirected").append("\n");
             	break;
             	}
        		 writer.write(texto.toString());
        		 writer.endRecord(); 
        		 texto.delete(0, texto.length());
             }
        	 System.out.println("Acabado el nodo " + i + " "+ fase);
        	 
        }
        System.out.println("Aristas red aleatoria " + fase + " creadas");
        if (fase == "Conectada")
        	System.out.println("Red Aleatoria completada");
        
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
		
		/****************************
		RED ALEATORIA
	 */
		
	 static void aleatoria(int nodos){
		String nombreArchivo = "C:\\pr2\\"+ nodos + "_Nodos.csv", fase = "";
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
        		ClaseAleatoria.aristasAleatoria(nodos,p, fase);
        	break;
        	case 2://Critica
        		fase = "Critica";
        		ClaseAleatoria.aristasAleatoria(nodos,p, fase);
        	break;
        	case 3://Supercritica
        		fase = "Supercritica";
        		ClaseAleatoria.aristasAleatoria(nodos,p, fase);
        	break;
        	case 4://Conectada
        		fase = "Conectada";
        		ClaseAleatoria.aristasAleatoria(nodos,p, fase);
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
		
		
	}
		
	
	

