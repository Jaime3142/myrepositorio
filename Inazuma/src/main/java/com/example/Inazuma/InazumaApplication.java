package com.example.Inazuma;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InazumaApplication {

    public static void main(String[] args) {
        int opcion = 0;

        // Configuración de Hibernate
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();

        Scanner sc = new Scanner(System.in);

        while (opcion != 7) {
            
                System.out.println("Menu de opciones");
                System.out.println("1.-Crear jugador");
				System.out.println("2.-Leer jugador");
				System.out.println("3.-Actualizar jugador");
				System.out.println("4.-Eliminar jugador");
				System.out.println("5.-Listar todos los jugadores");
				System.out.println("6.- ");
                System.out.println("7.-Salir");
                System.out.println("Elige una opcion");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
					try{
						Jugador j = new Jugador();
                        System.out.println("Nombre del jugador");
                        j.setNombre(sc.nextLine());
                        System.out.println("Posicion del jugador");
                        j.setPosicion(sc.nextLine());

                        s.beginTransaction();
                        s.persist(j);
                        s.getTransaction().commit();
                        System.out.println("Se ha creado el jugador");
                        break;
					}catch(Exception e){
						e.printStackTrace();
					}
                        

                    case 2:
					try {

						
					} catch (Exception e) {
						e.printStackTrace();
					}

					break;

                    default:
                        System.out.println("Opción inválida");
                        break;
                }
           
        }

        // Cerrar recursos
        s.close();
        sf.close();
        sc.close();
    }
}
