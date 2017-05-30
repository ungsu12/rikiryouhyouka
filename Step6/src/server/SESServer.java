package server;
import java.io.*;
import java.net.*;

public class SESServer {
	
	public static void main(String[] args) {
		int port = 6666;	// ��Ʈ ��ȣ
		
		try{
			// ��Ʈ��ȣ�� ���������� �����Ѵ�
			ServerSocket ssocket = new ServerSocket(port);
			System.out.println("[INFO] ������ Ŭ���̾�Ʈ�� ��ٸ��� �ֽ��ϴ�");

			// ����Ͽ� Ŭ���̾�Ʈ�κ����� ������ ��ٸ���
			while(true) {
				// Ŭ���̾�Ʈ�κ����� ������ ���⸦ ��ٸ���
				Socket socket = ssocket.accept();
				
				// ����ƴٸ� ������Ʈ ��ǲ, �ƿ�ǲ ��Ʈ���� �����Ѵ�
				ObjectInputStream nois = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream noos = new ObjectOutputStream(socket.getOutputStream());

				// Runnable�� �����ϴ� SESServerTread�� �����Ͽ� �����Ѵ�
				SESServerThread thread = new SESServerThread(nois, noos);
				Thread t = new Thread(thread);
				t.start();
			}
		} catch(Exception e) {
			System.out.println("[ERR] �� �� ���� ����");
			e.printStackTrace();
		}
	}
}