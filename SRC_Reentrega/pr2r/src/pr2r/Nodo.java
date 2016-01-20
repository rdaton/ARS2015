package pr2r;

public class Nodo {

	private int grado=0;
	private int idNodo;
	
	
	public int getGrado() {
		return grado;
	}
	public void setGrado(int grado) {
		this.grado = grado;
	}
	public int getIdNodo() {
		return idNodo;
	}
	public void setIdNodo(int idNodo) {
		this.idNodo = idNodo;
	}
	public void aumentarGrado() {
		this.grado++;
	}
	
}
