
package umg.modelo;

public class Camionero {
    private String dni;
    private String nombre;
    private String telefono;
    private String direccion;
    private String poblacion;
    private double salario;

    public Camionero() {}

    public Camionero(String dni, String nombre, String telefono, String direccion, String poblacion, double salario) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.salario = salario;
    }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getPoblacion() { return poblacion; }
    public void setPoblacion(String poblacion) { this.poblacion = poblacion; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}/**
 *
 * @author Miguel yeilin
 */
 
    

