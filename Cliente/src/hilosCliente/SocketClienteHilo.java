package hilosCliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClienteHilo {
	
	public static final int PUERTO = 6969;
	public static final String IP_SERVER = "192.168.137.237";
	public static InetSocketAddress direccionServidor;
	public static Socket socketAlServidor;
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println("              [CLIENTE]             ");
		System.out.println("------------------------------------");

		direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
		
		try {
			
			//Establecer conexion con servidor
			
			establecerConexion();

			//Entrada al servidor
			InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
			BufferedReader entradaBuffer = new BufferedReader(entrada);
			
			//Salida al servidor
			
			PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
			
			//Usuario a introducir	
			
			System.out.println("Introduzca el nickname de usuario deseado");
			String nickname = sc.nextLine();
			salida.println(nickname);		
			
			boolean continuar = true;
				
			Thread hiloEnvio = new Thread(new EnviarMensajes(socketAlServidor));
            hiloEnvio.start();
			
			String respuestaClientes = entradaBuffer.readLine();
							
			System.out.println(respuestaClientes);										

			socketAlServidor.close();
			
		} catch (UnknownHostException e) {
			System.err.println("CLIENTE: No encuentro el servidor en la dirección" + IP_SERVER);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("CLIENTE: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("CLIENTE: Error -> " + e);
			e.printStackTrace();
		}
		
		System.out.println("CLIENTE: Fin del programa");
	}
	
	public static void establecerConexion() throws Exception {
		
		System.out.println("CLIENTE: Esperando a que el servidor acepte la conexión");
		
		socketAlServidor = new Socket();
		socketAlServidor.connect(direccionServidor);
		
		System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER + 
				" por el puerto " + PUERTO);
		
	}
	
}
