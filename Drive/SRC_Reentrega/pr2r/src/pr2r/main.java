package pr2r;



import java.util.HashMap;



import org.apache.commons.cli.*;

public class main {

	
	
	
	public static void main(String[] args) {
		//parseo argumentos
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
		//compruebo los argumentos y tipo de red;
		//y recupero los argumentos de creación
		HashMap<String,Object> unosArgumentos=new HashMap(); 
		//este int unosArgumentos, debo rehacerlo...
		esAleatoria=esAleatoria(cmd,unosArgumentos);
		esLibreEscala=esLibreEscala(cmd,unosArgumentos);	
		if (esAleatoria)
		{
			ClaseAleatoria.getInstance().aleatoria(Integer.valueOf((String)unosArgumentos.get("n")),
					Double.valueOf((String)unosArgumentos.get("p")));
		}
		else
			if (esLibreEscala)
			{				
				ClaseLibreEscala.getInstance().libreDeEscala(Integer.valueOf((String)unosArgumentos.get("n")),
						Integer.valueOf((String)unosArgumentos.get("m")));
			}
			else
			{
				imprimirAyuda(opciones);
				System.exit(1);				
			};		
		
	}
	
	static boolean esAleatoria(CommandLine cmd, HashMap unosArgumentos)
	{
		return comprobador(0,cmd,unosArgumentos);
	}
	
	static boolean esLibreEscala (CommandLine cmd,HashMap unosArgumentos)
	{
		return comprobador(1,cmd,unosArgumentos);
	}
	
	//cod puede ser 0 para aleatoria, 1 para libre escala
	static boolean  comprobador(int cod, CommandLine cmd, HashMap unosArgumentos)
	{
		String[][] unasLetras={ {"t","n","p"}, {"t","n","m"}};
		String[] tipo={"aleatoria","libre"};
		boolean todoBien=true;
		boolean error=false;		
		//compruebo que todas los argumentos necesarios, están
		int i=0;
		while (todoBien && i<unasLetras[cod].length)
		{
		todoBien=cmd.hasOption(unasLetras[cod][i]);
		i++;
		}
		
		//compruebo si contiene la cadena tipo	
		error=true;
		if(cmd.getOptionValue(unasLetras[cod][0])!=null)		
			error=!((cmd.getOptionValue(unasLetras[cod][0])).equals(tipo[cod]));
		
		//compruebo el valor de los argumentos sea mayor que 0
		int j=1;
		while (!error && j<unasLetras[cod].length)
		{
			Object unNumero=cmd.getOptionValue(unasLetras[cod][j]);
			error=(unNumero==null);
			unosArgumentos.put((String)unasLetras[cod][j], (Object) unNumero);			
			j++;
		}
		
				
		return todoBien && !error;	
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
			imprimirAyuda(opciones);
		}
		return cmd;
	}
	
	//http://www.javaworld.com/article/2074849/core-java/processing-command-line-arguments-in-java--case-closed.html
	//https://commons.apache.org/proper/commons-cli/usage.html
	//http://www.thinkplexx.com/blog/simple-apache-commons-cli-example-java-command-line-arguments-parsing
	static Options preparaFormatoOpciones()
	{		
		Options opciones= new Options();
		opciones.addOption("t","tipo",true,"tipo de red a generar: aleatoria  o libre");
		opciones.addOption("n","nodos",true,"número de nodos (en todas)");
		opciones.addOption("p","prob",true,"probabilidad (aleatoria) ");
		opciones.addOption("m","mgrado",true,"nodos iniciales (libre escala)");
		return opciones;
	}
	
	
	

	
	
	
}


	