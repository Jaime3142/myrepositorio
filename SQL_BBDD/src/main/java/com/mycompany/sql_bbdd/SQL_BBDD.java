

package com.mycompany.sql_bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class SQL_BBDD {

    public static void main(String[] args) {
         String DRIVER = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bbdd_trabajo";
        String usuario = "root";
        String contra = "Med@c";
        int opcion = 0;
        
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
                 
                    String nuevoPedido = "INSERT INTO pedidos(fecha,id_cliente) VALUES(?,?,?)";
                     PreparedStatement ps3 = dbConnection.prepareStatement(nuevoPedido);
                     
                    LocalDateTime fechaHoy = LocalDateTime.now();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyy HH/mm/ss");
                    String fechaHora = fechaHoy.format(formato);
                    
                     System.out.println("Ingrese el id del cliente:");
                    int id_cliente = Integer.parseInt(sc.nextLine());
                    
                    ps3.setString(1, fechaHora);
                    ps3.setInt(2,id_cliente);
                    ps3.executeUpdate();
                    System.out.println("Pedido registrado");
                    
                     //preguntar carlos
                    
                    break;
                    
                case 4:
                    String detallesPedido = "SELECT \n" +
"    c.nombre AS cliente,\n" +
"    c.ciudad,\n" +
"    p.fecha AS fecha_pedido,\n" +
"    pr.nombre AS producto,\n" +
"    pp.cantidad,\n" +
"    pr.precio\n" +
"FROM pedidos p\n" +
"JOIN cliente c ON p.id_cliente = c.id_cliente\n" +
"JOIN pedido_producto pp ON p.id_pedido = pp.id_pedido\n" +
"JOIN producto pr ON pp.id_producto = pr.id_producto\n" +
"WHERE p.id_pedido = 3;,";
                     PreparedStatement ps4 = dbConnection.prepareStatement(detallesPedido);
                     
                     
                    break;
            }
            
          
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
