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
import org.apache.commons.cli.*;

public class main {

	
	
		
	public static void main(String[] args) {
		Options opciones=preparaFormatoOpciones();
		CommandLine cmd=parseaOpciones(opciones,args);
		boolean esAleatoria=false;
		boolean esLibreEscala=false;
		int numNodos=0;
		
		//muy improbable que la lista de argumentos devuelva null
		//pero por si acaso...
		if (cmd==null) 
		{			
			System.exit(1);
		}
		
		esAleatoria=esAleatoria(cmd);
		esLibreEscala=esLibreEscala(cmd);
		
		
		if (esAleatoria)
			ClaseAleatoria.aristasAleatoria(0, 0);
		else
			if (esLibreEscala)
				ClaseLibreEscala.libreDeEscala(0, 0);
			else
			{
				imprimirAyuda(opciones);
				System.exit(1);				
			};
		
		
		
		
	}
	
	static boolean esAleatoria(CommandLine cmd)
	{
		boolean todoBien=false;
		
		return todoBien;
	}
	
	static boolean esLibreEscala (CommandLine cmd)
	{
		boolean todoBien=false;
		
		return todoBien;
	}
	
	static void  imprimirAyuda(Options opciones)
	{
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "pr2r", opciones);
	}
	//http://www.javaworld.com/article/2074849/core-java/processing-command-line-arguments-in-java--case-closed.html
	//https://commons.apache.org/proper/commons-cli/usage.html
	static  CommandLine parseaOpciones(Options opciones, String[] args)
	{
		CommandLine cmd=null;
		CommandLineParser parser = new DefaultParser();
		try 
		{
		cmd = parser.parse(opciones, args);
		}
		catch(Exception e)
		{
			System.err.println("No he podido parsear. Razón:" + e.getMessage() );
		}
		return cmd;
	}
	
	//http://www.javaworld.com/article/2074849/core-java/processing-command-line-arguments-in-java--case-closed.html
	//https://commons.apache.org/proper/commons-cli/usage.html
	static Options preparaFormatoOpciones()
	{
		Options opciones= new Options();
		opciones.addOption("t",true,"tipo de red a generar: aleatoria  o libre");
		opciones.addOption("n",true,"número de nodos (en todas)");
		opciones.addOption("p",true,"probabilidad (aleatoria) ");
		opciones.addOption("m",true,"nodos iniciales (libre escala)");
		return opciones;
	}
	/*
	public static void menu(){
		
		System.out.println("1 - Aleatoria");
		System.out.println("2 - Libre de escala");
		System.out.println("3 - Salir");
		System.out.print ("Elige la opcion: ");
	}
	*/
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


	