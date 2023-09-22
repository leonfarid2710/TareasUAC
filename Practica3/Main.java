public class Main {
    
    public static void main(String args[]){
        Alumno alumno = new Alumno();
        String telefono = alumno.getTutor().getTelefono().toString();
        System.out.println(telefono);
    }
}
