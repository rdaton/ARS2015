package pr2r;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Vector;
import com.csvreader.CsvWriter;

public class main {

	

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
		int nodos, t, m;
		Scanner s = new Scanner(System.in);
		
		switch (opcion){
		case 1:
			System.out.println("Numero de nodos:");
			nodos = s.nextInt();
			ClaseAleatoria.aleatoria(nodos);
		break;
		case 2:
			System.out.println("Numero de nodos:");
			nodos = s.nextInt();
			m=3;
			ClaseLibreEscala.libreDeEscala(nodos,m); 
			ClaseLibreEscala.libreDeEscala(nodos,m+1);
		break;
		}
	}
	

	
	
	
}


	
