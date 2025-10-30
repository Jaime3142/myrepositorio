package com.medac.TiendaDb_Jaime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TiendaDbJaimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaDbJaimeApplication.class, args);
		 String DRIVER = "org.h2.Driver";
        String url = "jdbc:h2:file:~/data/demo";
        String usuario = "sa";
        String contra = "password";
        int opcion = 0;
        
       
            
            
            
            while(opcion!=6){
                 try{
          Class.forName(DRIVER);
            Connection dbConnection = DriverManager.getConnection(url, usuario, contra);  
            System.out.println("Menu de opciones");
            System.out.println("1.-Crear cliente");
            System.out.println("2.-Crear producto");
            System.out.println("3.-Registrar nuevo pedido");
            System.out.println("4.-Consultar detalles pedido");
            System.out.println("5.-Eliminar un producto");
            System.out.println("6.-Salir");
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Elige una opcion");
            opcion= Integer.parseInt(sc.nextLine());
            
            
            switch(opcion){
                case 1:
                  String nuevoCliente = "INSERT INTO cliente(nombre,edad,ciudad) VALUES(?,?,?)";
                    PreparedStatement ps = dbConnection.prepareStatement(nuevoCliente);
                    
                    System.out.println("Nombre del cliente:");
                    String nombre = sc.nextLine();
                    System.out.println("Edad del cliente:");
                    int edad = Integer.parseInt(sc.nextLine());
                    System.out.println("Ciudad del cliente:");
                    String ciudad = sc.nextLine(); 
                    
                    ps.setString(1, nombre);
                    ps.setInt(2, edad);
                    ps.setString(3, ciudad);
                    ps.executeUpdate();
                    System.out.println("Cliente registrado");
                    break;
                    
                case 2:
                    
                       String nuevoProducto = "INSERT INTO producto(nombre,precio,stock) VALUES(?,?,?)";
                    PreparedStatement ps2 = dbConnection.prepareStatement(nuevoProducto);
                    
                    System.out.println("Nombre del prodcuto:");
                    String nombreProducto = sc.nextLine();
                    System.out.println("Precio del producto:");
                    int precio = Integer.parseInt(sc.nextLine());
                    System.out.println("Stock del producto:");
                    float stock = Float.parseFloat(sc.nextLine());
                    
                    ps2.setString(1, nombreProducto);
                    ps2.setInt(2, precio);
                    ps2.setFloat(3, stock);
                    ps2.executeUpdate();
                    System.out.println("Producto registrado");
                    
                    
                    break;
                    
                case 3:
                 
                     try {
                   
                    String sqlPedido = "INSERT INTO pedidos (fecha, id_cliente) VALUES (?, ?)";
                    PreparedStatement psPedido = dbConnection.prepareStatement(sqlPedido, PreparedStatement.RETURN_GENERATED_KEYS);

                    // Obtener la fecha actual formateada
                    LocalDateTime fechaActual = LocalDateTime.now();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String fechaHora = fechaActual.format(formato);

                    // Solicitar el id del cliente
                    System.out.print("Introduce el ID del cliente: ");
                    int id_cliente = Integer.parseInt(sc.nextLine());

                    // Asignar parámetros e insertar el pedido
                    psPedido.setString(1, fechaHora);
                    psPedido.setInt(2, id_cliente);
                    psPedido.executeUpdate();

                    // Obtener el ID generado del pedido
                    ResultSet rs = psPedido.getGeneratedKeys();
                    int id_pedido = 0;
                    if (rs.next()) {
                        id_pedido = rs.getInt(1);
                    }

                    System.out.println("Pedido creado con ID: " + id_pedido);

                 
                    boolean continuar = true;
                    while (continuar) {
                        System.out.print("Introduce el ID del producto: ");
                        int id_producto = Integer.parseInt(sc.nextLine());

                        System.out.print("Introduce la cantidad: ");
                        int cantidad = Integer.parseInt(sc.nextLine());

                   
                        String sqlPedidoProducto = "INSERT INTO pedido_producto (id_pedido, id_producto, cantidad) VALUES (?, ?, ?)";
                        PreparedStatement psPedidoProd = dbConnection.prepareStatement(sqlPedidoProducto);
                        psPedidoProd.setInt(1, id_pedido);
                        psPedidoProd.setInt(2, id_producto);
                        psPedidoProd.setInt(3, cantidad);
                        psPedidoProd.executeUpdate();

                     
                        String sqlActualizarStock = "UPDATE producto SET stock = stock - ? WHERE id_producto = ?";
                        PreparedStatement psStock = dbConnection.prepareStatement(sqlActualizarStock);
                        psStock.setInt(1, cantidad);
                        psStock.setInt(2, id_producto);
                        psStock.executeUpdate();

                        System.out.println("Producto añadido al pedido y stock actualizado.");

                       
                        System.out.print("¿Deseas añadir otro producto al pedido? (s/n): ");
                        String respuesta = sc.nextLine();
                        if (!respuesta.equalsIgnoreCase("s")) {
                            continuar = false;
                        }
                    }

                    System.out.println("Pedido registrado correctamente.");

                } catch (SQLException e) {
                    System.out.println("Error al registrar el pedido: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error inesperado: " + e.getMessage());
                }
                    
                    break;
                    
                case 4:
                    String detallesPedido = "SELECT " +
    "    c.nombre AS nombre, " +
    "    c.ciudad AS ciudad, " +
    "    p.fecha AS fecha_pedido, " +
    "    pr.nombre AS producto, " +
    "    pp.cantidad, " +
    "    pr.precio " +
    "FROM pedidos p " +
    "JOIN cliente c ON p.id_cliente = c.id_cliente " +
    "JOIN pedido_producto pp ON p.id_pedido = pp.id_pedido " +
    "JOIN producto pr ON pp.id_producto = pr.id_producto " +
    "WHERE p.id_pedido = ?";
                  
                       
                     PreparedStatement ps6 = dbConnection.prepareStatement(detallesPedido);
                     System.out.println("Id del pedido:");
                     int IdPedido = Integer.parseInt(sc.nextLine());
                    ps6.setInt(1, IdPedido);
                    ResultSet rsPedido = ps6.executeQuery();
                   
                     
                     while (rsPedido.next()) {
                         System.out.println("Nombre " + rsPedido.getString("nombre"));
                         System.out.println("Ciudad " + rsPedido.getString("ciudad"));
                         System.out.println("Fecha " + rsPedido.getString("fecha_pedido"));
}

                     
                     
                     
                    break;
                    
                case 5:
                    String eliminadbrProducto = "DELETE FROM pedido_producto WHERE id_pedido = ? AND id_producto = ?";
                    PreparedStatement ps5 = dbConnection.prepareStatement(eliminadbrProducto);
                     System.out.print("ID del pedido: ");
            int idPedido = Integer.parseInt(sc.nextLine());
            System.out.print("ID del producto: ");
            int idProducto = Integer.parseInt(sc.nextLine());
    ps5.setInt(1, idPedido);
    ps5.setInt(2, idProducto);
    int borrados = ps5.executeUpdate();
    System.out.println(borrados + " registro eliminado");
                    break;
            }
            
          
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        } 
            }
	}

}
