package Modelo;

public class Empleado extends Persona{
    private String idEmpleado;
    private String cargo;
    private double salario;

    public Empleado(String idEmpleado,String DNI, String nombres, String apellidos, String cargo, double salario) {
        super(DNI, nombres, apellidos);
        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

   
}
