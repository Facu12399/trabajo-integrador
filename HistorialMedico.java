import java.text.SimpleDateFormat;
import java.util.*;

public class HistorialMedico extends Medicos {
	int NumHC;
	Date Fecha;
	int DniPacHC;
	int MatMedHC;
	String DiagHC;
	Date HCFec;
	
	public HistorialMedico(int DniPac, String NomPac, String DomPac, 
			int MatMed, String NomMed, String EspMed, int NumHC, Date HCFec, int DniPacHC,
			int MatMedHC, String DiagHC) {
		
		super(DniPac, NomPac, DomPac, MatMed, NomMed, EspMed);
		this.NumHC=NumHC;
		this.HCFec=HCFec;
		this.DniPacHC=DniPacHC;
		this.MatMedHC=MatMedHC;
		this.DiagHC=DiagHC;
	}
	
	public String FormatearFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String FormateoFecha = sdf.format(HCFec);
		return FormateoFecha;
	}
}
