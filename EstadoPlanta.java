
package ModelView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import static ModelView.comArduino.msg;

/**
 *
 * @author julie
 */

public class EstadoPlanta {    

    public static void main(String[] args) throws IOException{
        bienvenida();
    }
    
    static void bienvenida() throws IOException {
        Scanner sc=new Scanner(System.in);
        System.out.println("""
                           Estado de planta 
                           Seleccione una acción: 
                           1.Estado actual de planta
                           2.Añadir registro de datos
                           3.Tabla de datos
                           4.Volver
                           """);
        String sel=sc.nextLine();
        
        if (sel.equals("1")||sel.equals("2")||sel.equals("3")){
            
            switch (sel){
            case "1" -> {
                //Estado actual de planta
                System.out.println("*----------***----------*");
                estadoactual();
                break;
            }
            case "2" -> {
                //registrar nuevo dato
                System.out.println("*----------***----------*");
                newdato();
                break;
            }
            case "3" -> {
                //volver al inicio
                System.out.println("*----------***----------*");
                System.out.println("Tabla de datos");
                print();
            }
            case "4" -> {
                //volver al inicio
                System.out.println("*----------***----------*");
                FlowMonitor.inicio();
                break;
            }
        }
        }else{
            System.out.println("*----------*Selección inválida*----------*");
            bienvenida();
        }
    }
    
    private static void estadoactual() {
            
        System.out.print("Estado actual de planta");
        
        //obtener datos del sensor conectado al arduino
        float sensor = Float.parseFloat(msg);

        Estado estado= new Estado();
        estado.settemperature(sensor);
        estado.getpressure();
        //estado.getcaudal();

        //System.out.print(estado);
        System.out.println("Temperatura registrada por el sensor: "+estado.gettemperature());
    }

    static void newdato() throws IOException {
        Registrardatos dat1 = new Registrardatos();
        dat1.Addatos();
    }

    private static void print() throws FileNotFoundException, IOException {
        
        // Recuperar el datos desde el archivo
        LinkedHashMap<Float, Float> tabla = new LinkedHashMap<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\julie\\OneDrive\\Documentos\\doc_uni\\Programación\\Prog java\\FlowMonitor\\PresionTemp.txt\\"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    float clave = Float.parseFloat(partes[0]);
                    float valor = Float.parseFloat(partes[1]);
                    tabla.put(clave, valor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Imprimir tabla de datos
        System.out.println("Presión  |  Temperatura");
            tabla.entrySet().forEach(entry -> {
            Float press = entry.getKey();
            Float temp = entry.getValue();

            System.out.println(press+"    =      "+temp);

        });
    }
}
    
