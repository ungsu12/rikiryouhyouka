package server;
import java.io.*;
import java.net.*;

public class SESServer {
	
	public static void main(String[] args) {
		int port = 6666;	// 포트 번호
		
		try{
			// 포트번호로 서버소켓을 생성한다
			ServerSocket ssocket = new ServerSocket(port);
			System.out.println("[INFO] 서버가 클라이언트를 기다리고 있습니다");

			// 계속하여 클라이언트로부터의 연결을 기다린다
			while(true) {
				// 클라이언트로부터의 연결을 오기를 기다린다
				Socket socket = ssocket.accept();
				
				// 연결됐다면 오브젝트 인풋, 아웃풋 스트림을 생성한다
				ObjectInputStream nois = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream noos = new ObjectOutputStream(socket.getOutputStream());

				// Runnable을 구현하는 SESServerTread를 생성하여 실행한다
				SESServerThread thread = new SESServerThread(nois, noos);
				Thread t = new Thread(thread);
				t.start();
			}
		} catch(Exception e) {
			System.out.println("[ERR] 알 수 없는 에러");
			e.printStackTrace();
		}
	}
}