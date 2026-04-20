package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//esta clase es la que implementa la interfaz IClienteDAO , es la que se encarga de hacer las operaciones con la base de datos , como listar, buscar, agregar, modificar y eliminar clientes
public class ClienteDAO implements IClienteDAO {

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();

        PreparedStatement ps; //preparar la consulta SQL
        ResultSet rs; //almacenar el resultado de la consulta SQL
        Connection con = Conexion.getConexion();

        var sql = "SELECT * FROM cliente ORDER BY id"; //pedirle a la base de datos que me traiga todos los clientes ordenados por id

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        finally{
            try {
                con.close();                
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs; // variable que recibe el resultado de la consulta SQL
        Connection con = Conexion.getConexion();
        String sql = "SELECT * FROM cliente WHERE id = ?"; //consulta SQL para buscar un cliente por su id

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error al buscar cliente por id: " + e.getMessage());
        }
            finally{
                try {
                    con.close();                
                } catch (Exception e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
         return false;
    }

    @Override
    public boolean agregraCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        var sql = "INSERT INTO cliente (nombre, apellido, membresia) VALUES (?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }
         finally{
                try {
                    con.close();                
                } catch (Exception e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }

        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String sql = "UPDATE cliente SET nombre = ?, apellido = ?, membresia = ? WHERE id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar cliente: " + e.getMessage());
        }
         finally{
                try {
                    con.close();                
                } catch (Exception e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }

        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {

        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String sql = "DELETE FROM cliente WHERE id = ?";
        

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
            finally{
                    try {
                        con.close();                
                    } catch (Exception e) {
                        System.err.println("Error al cerrar la conexión: " + e.getMessage());
                    }
                }
        return false;
    }

    public static void main(String[] args) {
        
        IClienteDAO clienteDao = new zona_fit.datos.ClienteDAO();
        //los :: significa que se va a llamar al metodo println de System.out por cada cliente en la lista clientes

        //buscar cliente por id
        //var cliente1 = new Cliente(1);
        //System.out.println("Cliente antes de buscar por id: " + cliente1);
        //var encontrado = clienteDao.buscarClientePorId(cliente1);
        //if (encontrado) {
        //    System.out.println("Cliente encontrado: " + cliente1);
        //} else {
        //    System.out.println("Cliente no encontrado con id: " + cliente1.getId());
        //}

        //agregar cliente
        //var nuevoCliente = new Cliente("Guillermo", "Santos", 700);
        //var agregado = clienteDao.agregraCliente(nuevoCliente);
        //if (agregado) {
        //    System.out.println("Cliente agregado exitosamente: " + nuevoCliente);
        // }          
        // else {
        //    System.out.println("Error al agregar cliente: " + nuevoCliente);
        //}

        //modificar cliente
        //var modificarCliente = new Cliente(7, "Dafne", "Luvianos", 800);
        //var modificado = clienteDao.modificarCliente(modificarCliente);
        //if (modificado) {
        //    System.out.println("Cliente modificado exitosamente: " + modificarCliente);
        //} else {
        //    System.out.println("Error al modificar cliente: " + modificarCliente);
        //}

        //eliminar cliente
        var eliminarCliente = new Cliente(7);
        var eliminado = clienteDao.eliminarCliente(eliminarCliente);
        if(eliminado){
            System.out.println("Cliente eliminado : " + eliminarCliente);
        }else {
            System.out.println("Error al eliminar cliente: " + eliminarCliente);
        }


        //listar clientes
        System.out.println("-----------Listar clientes: -----------");
        var clientes = clienteDao.listarClientes();
        clientes.forEach(System.out::println);
    }
}
