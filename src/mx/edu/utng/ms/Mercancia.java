package mx.edu.utng.ms;

public class Mercancia {
	
	private int id;
	private int impuesto;
	
	public Mercancia(int id, int impuesto) {
		super();
		this.id = id;
		this.impuesto = impuesto;
	}
	
	public Mercancia(){
		this(0,0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(int impuesto) {
		this.impuesto = impuesto;
	}

	@Override
	public String toString() {
		return "Mercancia [id=" + id + ", impuesto=" + impuesto + "]";
	}

	
	

}
