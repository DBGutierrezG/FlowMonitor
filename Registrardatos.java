
package ModelView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julie
 */

public class Registrardatos {
    
    private static final LinkedHashMap<Float, Float> tempre = new LinkedHashMap<>();    
    //private static final HashMap<Float, Float> caupre = new HashMap<>();
    private static final Scanner sc = new Scanner(System.in);
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);
            
    public void Addatos() throws IOException{
        
        elemadd();
        
        System.out.println("""
                           Seleccione una opción:
                           1.Añadir nuevo dato
                           2.Obtener tabla de datos
                           3.Volver al menú de Estado de Planta
                           4.Inicio""");
        String sel=sc.nextLine();
        if (sel.equals("1")||sel.equals("2")||sel.equals("3")||sel.equals("4")){
            switch (sel){
                case "1" -> {
                    //registrar nuevo dato
                    System.out.println("*----------***----------*");
                    EstadoPlanta.newdato();
                }
                case "2"->{
                    //mostrar tabla de datos
                    System.out.println("*----------***----------*");
                    elemsave();
                    countDownLatch.countDown();
                    print();
                }
                case "3" -> {
                    //menú de Estado de Planta
                    System.out.println("*----------***----------*");
                    EstadoPlanta.bienvenida();
                }
                case "4" -> {
                    //volver al inicio
                    System.out.println("*----------***----------*");
                    FlowMonitor.inicio();
                }

            }    
        }else{
            System.out.println("*----------*Selección inválida*----------*");
            EstadoPlanta.bienvenida();
        }
        
    }
        
    private void elemadd() throws IOException {
        
        System.out.println("Digite dato de presión en psi:");
        float press = (Float.parseFloat(sc.nextLine()));
        System.out.println("Digite dato de temperatura en centígrados:");
        float temp = (Float.parseFloat(sc.nextLine()));
        //System.out.println("Digite dato de caudal:");
        //float caud = (Float.parseFloat(sc.nextLine()));
        
        //if (press>=0 && temp>=0 &&caud>=0){
        if (press>=0 && temp>=0){
            //llenar mapas con elementos ingresados
            //caupre.put(press, caud);
            tempre.put(press,temp);
                    
            System.out.println("Dato registrado con éxito.");
            System.out.println("");
                    
        }else{
            System.out.println("Valor inválido");
            EstadoPlanta.bienvenida();
        }
        
    }

    private void elemsave() throws IOException {
        
        // Para guardar datos de presion y temperatura en un archivo en modo append
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("PresionTemp.txt",true))) {
            
            int fil = tempre.size(); // Número de filas
            
            for (int i = 0; i < fil; i++) {
                tempre.entrySet().forEach(entry -> {
                Float press = entry.getKey();
                Float temp = entry.getValue();
                
                try {
                    writer.write(press +":"+ temp); // Tabulador para separar los elementos
                    writer.newLine(); // Nueva línea para separar las filas
                } catch (IOException ex) {
                    Logger.getLogger(Registrardatos.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            }
        
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        countDownLatch.countDown();
        
        try {
            countDownLatch.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Registro guardado");
        
}

    private void print() throws FileNotFoundException, IOException {
        
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
        
        // Imprimir tabla
        System.out.println("Presión  |  Temperatura");
        
            tabla.entrySet().forEach(entry -> {
            Float press = entry.getKey();
            Float temp = entry.getValue();

            System.out.println(press+"    =      "+temp);
                
        });
    }
            
}
   

