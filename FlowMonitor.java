
package ModelView;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author julie
 */
public class FlowMonitor {
    
    public static void main(String[] args) throws IOException {
        inicio();
    }

    static void inicio() throws IOException {
        Scanner sc=new Scanner(System.in);
        System.out.println("""
                           Bienvenido
                           Seleccione una opción:
                           1.Estado de planta
                           2.Ayuda
                           3.Contáctenos
                           4.Salir
                           """);
        String sel=sc.nextLine();
        
        switch (sel){
            case "1" -> {
                System.out.println("*----------***----------*");
                //ir a estado de planta
                EstadoPlanta.bienvenida();
            }
            case "2" -> {
                System.out.println("*----------***----------*");
                //ir a ayuda
                //help();
            }
            case "3" -> {
                System.out.println("*----------***----------*");
                //información de contcto
                //contact();
            }
            case "4"->{
                System.out.println();
                exit();
            }
        }
    }

    private static void exit() {
        System.out.println("*----------*Saliendo*----------*");
        System.exit(0);
    }

    private static void help() {
        System.out.println("Ayuda");
        System.out.println("*----------*Servicio para el usuario*----------*");
    }

    private static void contact() {
        System.out.println("Contáctenos");
        System.out.println("*----------*Redes sociales*----------*");
    }
}
