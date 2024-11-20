import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HiloSexChat implements Runnable{
	private Thread hilo;
	private static List<Socket> listSocket = new ArrayList<Socket>();
	private Socket socketCliente;
	
	public HiloSexChat(Socket socketAlCliente) {
		String nombre = "";
		try {
			InputStreamReader entrada = new InputStreamReader(socketAlCliente.getInputStream());
			BufferedReader br = new BufferedReader(entrada);
			nombre = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		hilo = new Thread(this, nombre);
		listSocket.add(socketAlCliente);
		this.socketCliente = socketAlCliente;
		hilo.start();
	}

	@Override
	public void run() {
		System.out.println("Se ha establecido la conexin con "+hilo.getName());
		
		PrintStream salida = null;
		InputStreamReader entrada = null;
		BufferedReader entradaBuffer = null;
		
		try {
			String texto = "";
			boolean continuar = true;
			
			while(continuar) {
			
				entrada = new InputStreamReader(socketCliente.getInputStream());
				entradaBuffer = new BufferedReader(entrada);
				
				texto = entradaBuffer.readLine();
				
				for(Socket cliente:listSocket) {
					if(cliente != socketCliente) {
						salida = new PrintStream(cliente.getOutputStream());
						salida.println(texto);
					}
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
}
