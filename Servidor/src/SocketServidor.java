import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidor {
	public static final int PUERTO = 6969;
	
	public static void main(String[] args) {
		System.out.println("################");
		System.out.println("##  SERVIDOR  ##");
		System.out.println("################");
		
		int cont = 0;
		
		try(ServerSocket server = new ServerSocket()) {
			InetSocketAddress direction = new InetSocketAddress(PUERTO);
			server.bind(direction);
			
			while(true) {
				Socket socketAlCliente = server.accept();
				System.out.println("SERVIDOR -> Petición número "+(++cont));
				new HiloSexChat(socketAlCliente);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
