package zona_fit.presentacion;

import java.util.Scanner;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp() {
        var salir = false;
        var consola = new Scanner(System.in);

        // Creamos un objeto de la clase ClienteDAO para poder usar sus metodos
        IClienteDAO clienteDao = new ClienteDAO();

        while (!salir) {
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpcion(consola, opcion, clienteDao);

            } catch (Exception e) {
                System.out.println("Error al ejecutar la aplicación: " + e.getMessage());
            }
            // salto de linea
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola) {
        System.out.print(
                """
                         ###ZONA FIT APP###
                        1. Listar clientes
                        2. Buscar cliente
                        3. Agregar cliente
                        4. Modificar cliente
                        5. Eliminar cliente
                        6. Salir
                        Elige una opción: \s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpcion(Scanner consola, int opcion, IClienteDAO clienteDao) {
        var salir = false;
        switch (opcion) {
            case 1 -> { // 1 Listar clientes
                System.out.println("-----------Listar clientes: -----------");
                var clientes = clienteDao.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> { // 2 Buscar cliente
                System.out.print("Introduce el id del Cliente que deseas buscar : ");
                var idClinte = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idClinte);
                var encontrado = clienteDao.buscarClientePorId(cliente);
                if (encontrado)
                    System.out.println("Cliente encontrado: " + cliente);
                else
                    System.out.println("Cliente no encontrado con id: " + cliente);
            }
            case 3 -> { // 3 Agregar cliente
                System.out.print("-----------Agregar cliente----------");
                System.out.print("NOMRE: ");
                var nombre = consola.nextLine();
                System.out.print("APELLIDO: ");
                var apellido = consola.nextLine();
                System.out.print("MEMBRESIA: ");
                //pasamos a entero el valor de membresia que se ingresa por consola
                var membresia = Integer.parseInt(consola.nextLine());
                //creamos el objeto cliente sin el id
                var cliente = new Cliente(nombre , apellido , membresia);
                var agregado = clienteDao.agregraCliente(cliente);
                if(agregado)
                    System.out.println("Cliente agregado exitosamente: " + cliente);
                else
                    System.out.println("Error al agregar cliente: " + cliente);
            }
            case 4 -> { // 4 Modificar cliente
                System.out.println("-----------Agregar cliente----------");
                System.out.print("ID del cliente a modificar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                System.out.print("NOMRE: ");
                var nombre = consola.nextLine();    
                System.out.print("APELLIDO: ");
                var apellido = consola.nextLine();
                System.out.print("MEMBRESIA: ");
                var membresia = Integer.parseInt(consola.nextLine());

                var cliente = new Cliente(idCliente , nombre , apellido , membresia);
                var modificado = clienteDao.modificarCliente(cliente);
                if(modificado)
                    System.out.println("Cliente modificado exitosamente: " + cliente);
                else
                    System.out.println("Error al modificar cliente: " + cliente);
            }
            case 5 -> { // 5 Eliminar cliente
                System.out.println("-----------Agregar cliente----------");
                System.out.print("ID del cliente a eliminar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var eliminado = clienteDao.eliminarCliente(cliente);
                if(eliminado)
                    System.out.println("Cliente eliminado exitosamente: " + cliente);
                else
                    System.out.println("Error al eliminar cliente: " + cliente);
            }
            case 6 ->{
                System.out.println("Regrese pronto");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida" + opcion);
        }
        return salir;
    }
}
