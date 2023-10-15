
package ModelView;

import static ModelView.comArduino.msg;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;


public class comArduino {

    /**
     *
     */
    public static String msg="";
    
    public static void main (String[] args){
        
        SerialPort sp = new SerialPort("COM3"); //puerto usado
        
        try { //Enviar información
            sp.openPort(); //abrir puerto
            
            //parámetros del puerto
            sp.setParams(SerialPort.BAUDRATE_19200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); 
            sp.addEventListener(new LecturaSerial(sp),SerialPort.MASK_RXCHAR);
            Thread.sleep(2500);
            while(true){
                System.out.println("Recibiendo");
                //sp.writeString("Arduino"); //escribir en el puerto de arduino
                Thread.sleep(1000); //wait
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

class LecturaSerial implements SerialPortEventListener{
    
    SerialPort sp;
    public LecturaSerial(SerialPort sp){
        this.sp=sp;
    }

    @Override
    public void serialEvent(SerialPortEvent spe) {
        try{
            
            msg=sp.readString(); //leer mensaje en Arduino SerialPort
            System.out.println("Temperatura: "+msg); //imprimir mensaje de arduino SerialPort
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}