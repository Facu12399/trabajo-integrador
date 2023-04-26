import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Principal {

	public static void main(String[] args) {
		
		int opcion=0;
		int opcionmuestra=0;
		int opciondelete=0;
		int opcionhistoria=0;
		
		Scanner entrada = new Scanner(System.in);
		
		Conexion conexion = new Conexion();
		
		int DniPac, MatMed, NumHC, DniPacHC, MatMedHC, i, j;
		String NomPac, DomPac, NomMed, EspMed,DiagHC;
		
		
		System.out.println("------------------------------------------------------------");
			
		do{
			System.out.println("Bienvenido al sistema de historia clinica. ¿Que desea hacer?");
			System.out.println("------------------------------------------------------------");
			System.out.println("1. Insertar Datos");
			System.out.println("2. Mostrar Datos");
			System.out.println("3. Eliminar Datos");
			System.out.println("4. Salir");
			System.out.println("------------------------------------------------------------");
			System.out.println("Ingrese una opción: ");
			opcion = entrada.nextInt();
			switch(opcion) {
			case 1:
				Date HCFec = new Date();
				
				System.out.println("¿A cuantos pacientes desea realizarle la historia clinica?");
				j=entrada.nextInt();
				
				for(i=0;i<j;i++) {
				System.out.println("Ingrese el DNI del paciente N°" + (i+1) + ":");
				DniPac=entrada.nextInt();
				
				entrada.nextLine();
				
				System.out.println("Ingrese el nombre del paciente N°" + (i+1) + ":");
				NomPac=entrada.nextLine();
				
				System.out.println("Ingrese el domicilio del paciente N°" + (i+1) + ":");
				DomPac=entrada.nextLine();
				
				Pacientes paciente = new Pacientes(DniPac,NomPac,DomPac);
				
				System.out.println("-------------------------------------------------");
				System.out.println("Datos Personales");
				System.out.println("Dni: " + paciente.DniPac);
				System.out.println("Nombre: " + paciente.NomPac);
				System.out.println("Domicilio: " + paciente.DomPac);
				System.out.println("-------------------------------------------------");
				
				System.out.println("Ingrese la matricula del médico:");
				MatMed=entrada.nextInt();
				
				entrada.nextLine();
				
				System.out.println("Ingrese el nombre del médico:");
				NomMed=entrada.nextLine();
				
				System.out.println("Ingrese la especialidad del médico:");
				EspMed=entrada.nextLine();
				
				Medicos medico = new Medicos(DniPac, NomPac, DomPac, 
						MatMed, NomMed, EspMed);
				
				System.out.println("-------------------------------------------------");
				System.out.println("Datos del médico");
				System.out.println("Matricula: " + medico.MatMed);
				System.out.println("Nombre: " + medico.NomMed);
				System.out.println("Especialidad: " + medico.EspMed);
				System.out.println("-------------------------------------------------");
				
				System.out.println("Ingrese el número de la Historia Clinica:");
				NumHC=entrada.nextInt();
				
				entrada.nextLine();
				
				System.out.println("Ingrese el diagnostico hecho por el médico:");
				DiagHC=entrada.nextLine();
				
				HistorialMedico historial = new HistorialMedico(DniPac, NomPac, DomPac, MatMed,
						NomMed, EspMed, NumHC, HCFec, DniPacHC=DniPac, MatMedHC=MatMed, DiagHC);
				
				System.out.println("-------------------------------------------------");
				System.out.println("Historia Clínica");
				System.out.println("Numero de historia clinica: " + historial.NumHC);
				System.out.println("Fecha en la que fue realizada: " + historial.FormatearFecha());
				System.out.println("DNI del paciente: " + historial.DniPacHC);
				System.out.println("Matricula del medico: " + historial.MatMedHC);
				System.out.println("Diagnostico del medico: " + historial.DiagHC);
				System.out.println("-------------------------------------------------");
				
				
				
				try {
					PreparedStatement ps = null;
					Connection cn = null;
					Statement stm = null;
					ResultSet rs = null;
		   
					cn = conexion.conectar();
					ps = cn.prepareStatement("insert into pacientes(DniPac, NomPac, DomPac) values (?, ?, ?)");
					ps.setInt(1, DniPac);
			        ps.setString(2, NomPac);
			        ps.setString(3, DomPac);
			        ps.executeUpdate();
					ps = cn.prepareStatement("insert into medicos(MatMed, NomMed, EspMed) values (?, ?, ?)");
					ps.setInt(1, MatMed);
			        ps.setString(2, NomMed);
			        ps.setString(3, EspMed);
			        ps.executeUpdate();
					ps = cn.prepareStatement("insert into historialmedico(NumHC, FecHC, DniPacHC, MatMedHC, DiagHC) values (?, ?, ?, ?, ?)");
					ps.setInt(1, NumHC);
			        ps.setString(2, historial.FormatearFecha());
			        ps.setInt(3, DniPacHC);
			        ps.setInt(4, MatMedHC);
			        ps.setString(5, DiagHC);
			        ps.executeUpdate();
			        System.out.println("Los nuevos datos se agregaron a la tabla!");
			    } catch (Exception e) {  
			      e.printStackTrace();
			      System.out.println("Error en la inserción de datos!");
			    }		
				
				}
			break;
			case 2:
				Connection cn = null;
				Statement stm = null;
				ResultSet rs = null;
				
				do {
					System.out.println("¿Que tabla desea que le muestre?");
					System.out.println("------------------------------------------------------------");
					System.out.println("1. Pacientes");
					System.out.println("2. Medicos");
					System.out.println("3. Historial Medico");
					System.out.println("4. Volver al menú principal");
					System.out.println("------------------------------------------------------------");
					opcionmuestra=entrada.nextInt();
					switch(opcionmuestra){
					case 1:
						try {
							cn = conexion.conectar();
							stm = cn.createStatement();
							rs = stm.executeQuery("select * from pacientes");
							System.out.println("DniPac   -   NomPac    -    DomPac");
							
							while(rs.next()) {
								int Dni = rs.getInt(1);
								String Nom = rs.getString(2);
								String Dom = rs.getString(3);
								
								System.out.println(Dni + " - " + Nom + " - " + Dom);
							}
							
						} catch (SQLException e) {
							System.out.println("Algo ha fallado");
						}
					break;
					case 2:
						try {
							cn = conexion.conectar();
							stm = cn.createStatement();
							rs = stm.executeQuery("select * from medicos");
							System.out.println("MatMed   -   NomMed    -    EspMed");
							
							while(rs.next()) {
								int Mat = rs.getInt(1);
								String NM = rs.getString(2);
								String Esp = rs.getString(3);
								
								System.out.println(Mat + " - " + NM + " - " + Esp);
							}
							
						} catch (SQLException e) {
							System.out.println("Algo ha fallado");
						}
					break;
					case 3:
						try {
							cn = conexion.conectar();
							stm = cn.createStatement();
							rs = stm.executeQuery("select * from historialmedico");
							System.out.println("NumHC - FecHC - DniPacHC - MatMedHC - DiagMed");
							
							while(rs.next()) {
								int HC = rs.getInt(1);
								String Fec = rs.getString(2);
								int DniP = rs.getInt(3);
								int MatM = rs.getInt(4);
								String Diag = rs.getString(5);
								
								System.out.println(HC + " - " + Fec + " - " + DniP + " - " + MatM + " - " + Diag);
							}
							
						} catch (SQLException e) {
							System.out.println("Algo ha fallado");
						}
					break;
					case 4:
						System.out.println("------------------------------------------------------");
					break;
					default:
						System.out.println("Opcion incorrecta");
					}
					break;
			}while(opcion!=4);	
			break;
			case 3:
				do {
					System.out.println("---------------------------------------------------------");
					System.out.println("¿De que tabla desea eliminar elementos?");
					System.out.println("1. Pacientes");
					System.out.println("2. Medicos");
					System.out.println("3. Historial Medico");
					System.out.println("4. Volver al menu principal");
					System.out.println("ATENCION: Tenga en cuenta que si desea eliminar la tabla"
							+ " Pacientes o Medicos tiene que eliminar primero la clave foranea "
							+ "vinculada a la tabla Historial Medico ");
					System.out.println("---------------------------------------------------------");
					opciondelete=entrada.nextInt();
					
					switch(opciondelete){
					
					case 1:
						System.out.println("Ingrese el Dni del paciente a eliminar:");
					    int Dni = entrada.nextInt();  
					    try {
					    	String consulta="DELETE FROM pacientes WHERE DniPac = '"+Dni+"'";
					 
					    	Class.forName("com.mysql.jdbc.Driver");     
					        cn=conexion.conectar();    
					        stm=cn.createStatement();
					        stm.execute(consulta);   
					        System.out.println("El registro se elimino!!");
					    } catch (Exception e) {  
					      e.printStackTrace();
					      System.out.println("Error en el borrado del registro!!");
					    }
					break;
					case 2:
					System.out.println("Ingrese la matricula del medico a eliminar:");
				    int Mat = entrada.nextInt();  
				    try {
				    	String consulta="DELETE FROM medicos WHERE MatMed = '"+Mat+"'";
				 
				    	Class.forName("com.mysql.jdbc.Driver");     
				        cn=conexion.conectar();    
				        stm=cn.createStatement();
				        stm.execute(consulta);   
				        System.out.println("El registro se elimino!!");
				    } catch (Exception e) {  
				      e.printStackTrace();
				      System.out.println("Error en el borrado del registro!!");
				    }
				    break;
					case 3:
						do {
							System.out.println("¿Que registro desea eliminar de la historia clinica?");
							System.out.println("1. Dni de Paciente");
							System.out.println("2. Matricula de Medico");
							System.out.println("3. Numero de Historia clinica");
							System.out.println("4. Volver al menu Eliminar datos");
							System.out.println("---------------------------------------------------------");
							opcionhistoria=entrada.nextInt();
							switch(opcionhistoria) {
							case 1:
								System.out.println("Ingrese el número de historia clinica a eliminar");
							    int DniFC = entrada.nextInt();  
							    try {
							    	String consulta="DELETE FROM historialmedico WHERE DniPacHC = '"+DniFC+"'";
							     
							        cn=conexion.conectar();    
							        stm=cn.createStatement();
							        stm.execute(consulta);   
							        System.out.println("El registro se elimino!!");
							    } catch (Exception e) {  
							      e.printStackTrace();
							      System.out.println("Error en el borrado del registro!!");
							    }
							break;
							case 2:
								System.out.println("Ingrese el número de historia clinica a eliminar");
							    int MaMeHC = entrada.nextInt();  
							    try {
							    	String consulta="DELETE FROM historialmedico WHERE MatMedHC = '"+MaMeHC+"'";
							     
							        cn=conexion.conectar();    
							        stm=cn.createStatement();
							        stm.execute(consulta);   
							        System.out.println("El registro se elimino!!");
							    } catch (Exception e) {  
							      e.printStackTrace();
							      System.out.println("Error en el borrado del registro!!");
							    }
							break;
							case 3:
								System.out.println("Ingrese el número de historia clinica a eliminar");
							    int NHC = entrada.nextInt();  
							    try {
							    	String consulta="DELETE FROM historialmedico WHERE NumHC = '"+NHC+"'";
							    
							        cn=conexion.conectar();    
							        stm=cn.createStatement();
							        stm.execute(consulta);   
							        System.out.println("El registro se elimino!!");
							    } catch (Exception e) {  
							      e.printStackTrace();
							      System.out.println("Error en el borrado del registro!!");
							    }
							break;
							case 4:
								System.out.println("------------------------------------------------------");
							break;
							default:
								System.out.println("Opción incorrecta");
							break;
							}
						}while(opcionhistoria!=4);
				}
				}while(opciondelete!=4);
			break;
			case 4:
				System.out.println("El programa ha finalizado");
			break;
			default:
				System.out.println("La opción es incorrecta");
			break;
			}
			
		}while(opcion!=4);

	}

}
