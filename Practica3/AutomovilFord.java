public class AutomovilFord {
    int velocidad;
    String color;
    public String modelo;
    Motor motor;
    public static final String marca = "Ford";

    public AutomovilFord(String modelo, String color) {
        this.modelo = modelo;
        this.color = color;
        velocidad = 0;
        motor = new Motor();
    }

    public AutomovilFord() {
        this("EcoSport", "Azul");
    }

    public void encenderMotor() {
        motor.setEncendido(true);
        System.out.println("Motor encendido");
    }

    public void apagarMotor() {
        motor.setEncendido(false);
        System.out.println("Motor apagado");
    }

    public void acelerar() {
        if (motor.isEncendido()) {
            motor.revolucionar(); // Llama al método revolucionar() en Motor
            velocidad = (motor.getRevoluciones() / 100);
            System.out.println("Vamos a " + velocidad + " kph");
        } else {
            System.out.println("El motor está apagado!");
        }
    }

    public void frenar(int cantidad) {
        motor.setRevoluciones(cantidad); // Llama al setter de revoluciones en Motor
        if (velocidad < 0) velocidad = 0;
        System.out.println("Vamos a " + velocidad + " kph");
    }

    public int getVelocidad() {
        return velocidad;
    }
}
