//  NO MODIFICAR NOMBRE DE ESTE PAQUETE
package evalNestor;

public class Alumno {
	// Poned vuestros apellidos, nombre y UO; sustituyendolos en las asignaciones de debajo...
	String nombre="German", // Primera en may�sculas y si es compuesto, sin espacios
			apellido1="Diaz", // Primera en may�sculas y si es compuesto, sin espacios
			apellido2="Gonzalez", // Primera en may�sculas y si es compuesto, sin espacios
			uo="UO258472"; // El UO en may�sculas
	
	public String getNombreFicheroAlumno(){
		return apellido1+apellido2+nombre+"-"+uo;
	}

	public String email() {
		return uo+"@uniovi.es";
	}
	
}
