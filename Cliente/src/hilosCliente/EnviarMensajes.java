package hilosCliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EnviarMensajes implements Runnable{
	
	private Socket socket;
	private Scanner sc = new Scanner(System.in);
	
	public EnviarMensajes(Socket socket) {
		
		this.socket = socket;
		
	}

	@Override
	public void run() {

		try {
			
			PrintStream salida = new PrintStream(socket.getOutputStream());
			
			boolean continuar = true;
			
			while (continuar) {
				
                System.out.print("Ingresa su mensaje: ");
                String mensaje = sc.nextLine();
                
                salida.println(mensaje);
                
                if (mensaje.equalsIgnoreCase("FIN")) {
                	
                    System.out.println("Cerrando conexión...");
                    socket.close();
                    break;
                    
                }
                
            }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
