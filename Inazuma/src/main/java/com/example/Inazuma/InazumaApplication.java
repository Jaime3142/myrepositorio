package com.example.Inazuma;

import java.util.List;
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
            System.out.println("6.- Filtrar por tipo de tecnica");
            System.out.println("7.-Salir");
            System.out.println("Elige una opcion");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    try {
                        Jugador j = new Jugador();
                        System.out.println("Nombre del jugador");
                        j.setNombre(sc.nextLine());
                        System.out.println("Posicion del jugador");
                        j.setPosicion(sc.nextLine());
                        s.beginTransaction();
                        s.persist(j);
                        s.getTransaction().commit();
                        System.out.println("Se ha creado el jugador");
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try {

                        System.out.print("ID del jugador que quieres leer: ");
                        int leer = Integer.parseInt(sc.nextLine());

                        s.beginTransaction();

                        Jugador j2 = s.get(Jugador.class, leer);

                        s.getTransaction().commit();

                        if (j2 != null)
                            System.out.println("Jugador: " + j2);
                        else
                            System.out.println("No existe jugador con ID " + leer);

                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                    break;

                case 3:
                    try {
                        System.out.print("ID del jugador que quieres actualizar: ");
                        int actu = Integer.parseInt(sc.nextLine());

                        s.beginTransaction();

                        Jugador j = s.get(Jugador.class, actu);

                        if (j != null) {

                            System.out.print("Nuevo nombre: ");
                            j.setNombre(sc.nextLine());

                            System.out.print("Nueva posición: ");
                            j.setPosicion(sc.nextLine());

                            s.update(j);
                            s.getTransaction().commit();

                            System.out.println("Jugador actualizado: " + j);
                        } else {
                            s.getTransaction().rollback(); // No existe, rollback
                            System.out.println("No existe jugador con ID " + actu);
                        }

                    } catch (Exception e) {
                        if (s.getTransaction().isActive()) {
                            s.getTransaction().rollback();
                        }
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    try {
                        System.out.print("ID del jugador a eliminar: ");
                        int borrar = Integer.parseInt(sc.nextLine());

                        s.beginTransaction();

                        Jugador j = s.get(Jugador.class, borrar);

                        if (j != null) {
                            s.delete(j);
                            s.getTransaction().commit();
                            System.out.println("Jugador eliminado: " + j);
                        } else {
                            s.getTransaction().rollback();
                            System.out.println("No existe jugador con ID " + borrar);
                        }

                    } catch (Exception e) {
                        if (s.getTransaction().isActive()) {
                            s.getTransaction().rollback(); // rollback
                        }
                        e.printStackTrace();
                    }

                    break;

                    case 5:
                      // LISTA NO TIPADA (igual que tu ejemplo Order)
        List resultados = s.createQuery(
                "FROM Jugador j ORDER BY j.id ASC"
            )
            .getResultList();

        // RECORRIDO EXACTAMENTE COMO TU MODELO
        for (Object obj : resultados) {
            Jugador jug = (Jugador) obj;  // CAST MANUAL

            System.out.println(
                "Jugador ID: " + jug.getIdJugador() +
                " - Nombre: " + jug.getNombre() +
                " - Posición: " + jug.getPosicion() 
                
               
            );
        }
                    break;

                    case 6:
                       System.out.print("Introduce el nombre del equipo: ");
        String equipoBuscado = sc.nextLine();

        // LISTA NO TIPADA, COMO TU EJEMPLO DE Order
        List filtro = s.createQuery(
                "FROM Jugador j WHERE j.equipo = :eq ORDER BY j.nombre ASC"
            )
            .setParameter("eq", equipoBuscado)
            .getResultList();

        // RECORRIDO EXACTO COMO TU MODELO
        for (Object obj : filtro) {
            Jugador jug = (Jugador) obj;   // CAST MANUAL

            System.out.println(
                "Jugador ID: " + jug.getIdJugador() +
                " - Nombre: " + jug.getNombre() +
                " - Posición: " + jug.getPosicion() +
                " - Equipo: " + jug.getIdEquipo()
            );
        }
        break;
        }
                 

               
                    
                   
            }

        

        // Cerrar recursos
        s.close();
        sf.close();
        sc.close();
    }
}
