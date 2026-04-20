package zona_fit.dominio;

import java.util.Objects;

public class Cliente {

    //atributos de la cklase cliente , son privados para que nadie pueda acceder a ellos directamente , se acceden a traves de los metodos get y set
    private int id;
    private String nombre;
    private String apellido;
    private int membresia;
    
    //esta vacion es para poder crear un cliente sin tener que pasarle ningun parametro, se puede usar para crear un cliente y luego setearle los atributos con los metodos set
    public Cliente(){}

    //sirve para buscar o eliminar un cliente por su id,
    public Cliente(int id){
        this.id = id;
    }

    //crea un cliente , sin id pq ese se genera en la base de datos
    public Cliente (String nombre, String apellido, int membresia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.membresia = membresia;
    }

    //constructor completo
    public Cliente(int id, String nombre, String apellido, int membresia){
        //si llamamos a otro cosntructor debe ir primero
        this(nombre, apellido, membresia);
        this.id = id;
    }

    //get es LEER
    public int getId() {
        return id;
    }

    //set es Modificar o ESCRIBIR
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public int getMembresia() {
        return membresia;
    }

    public void setMembresia(int membresia) {
        this.membresia = membresia;
    }

    //toString se genera para mostrar el cliente de una forma mas legible, en este caso se muestra el id, nombre, apellido y membresia del cliente
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", membresia=" + membresia +
                '}';
    }

    //Permite comparar dos objetos Cliente para ver si son iguales. Esto es útil para operaciones como eliminar o modificar un cliente, donde necesitas identificarlo de manera única.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        return id == cliente.id && membresia == cliente.membresia && nombre.equals(cliente.nombre) && apellido.equals(cliente.apellido);
    }

    //Genera un código hash para el objeto Cliente, que es consistente con el método equals. Esto es importante si decides usar objetos Cliente en estructuras de datos como HashSet o HashMap, donde se necesita un hashCode para organizar los objetos.
    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, membresia);
    }
}
