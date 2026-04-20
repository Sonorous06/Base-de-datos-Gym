package zona_fit.datos;

import zona_fit.dominio.Cliente;
import java.util.List;

//la interfaz es un contrato , cualquier clase que implemente esta interfaz debe implementar todos los metodos que se definen en la interfaz , en este caso los metodos para listar, buscar, agregar, modificar y eliminar clientes
public interface IClienteDAO {

    //retorna una lista de clientes , se puede usar para mostrar todos los clientes en la base de datos
    List<Cliente> listarClientes();

    //busca un cliente por su id , si lo encuentra retorna true
    boolean buscarClientePorId(Cliente cliente);

    //inserta un cliente en la base de datos , si lo inserta retorna true
    boolean agregraCliente(Cliente cliente);

    //modifica un cliente en la base de datos , si lo modifica retorna true
    boolean modificarCliente(Cliente cliente);

    //elimina un cliente en la base de datos , si lo elimina retorna true
    boolean eliminarCliente(Cliente cliente);

}
