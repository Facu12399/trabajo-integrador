
public class Medicos extends Pacientes {
	int MatMed;
	String NomMed;
	String EspMed;
	public Medicos(int DniPac, String NomPac, String DomPac, 
			int MatMed, String NomMed, String EspMed) {
		
		super(DniPac, NomPac, DomPac);
		this.MatMed=MatMed;
		this.NomMed=NomMed;
		this.EspMed=EspMed;
	}
}
