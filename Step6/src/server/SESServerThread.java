package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import vo.Human;

/**
 * SES 서버의 다중접속 환경을 구현하기 위한 스레드 클래스
 * SESServer에서 사용자의 접속이 이루어지면 SESServerThread 객체를 생성하여
 * run() 메서드에서 ObjectInputStream 와 ObjectOutputStream을 이용하여 클라이언트와 독립적인 통신을 수행한다.
 * */
public class SESServerThread implements Runnable {
	
	private SESServerManager sm = new SESServerManager();	
	private ObjectInputStream nois;
	private ObjectOutputStream noos;
	private boolean exit = false;

	public SESServerThread(ObjectInputStream nois, ObjectOutputStream noos) {
		this.nois =  nois;
		this.noos = noos;
	}

	@Override
	public void run() {
		while(!exit){	
			try{
				// 오브젝트 스트림으로부터 오브젝트를 읽어들임
				Object[] readObejects= (Object[]) nois.readObject();
				
				String caseString = (String) readObejects[0];	// 명령어
				Object caseObject = readObejects[1];			// 오브젝트
				
				switch(caseString) {
					case "insert":	
						System.out.println("[INFO] 명령어 'insert' 실행");
						boolean result = sm.insertHuman((Human)caseObject);
						noos.writeObject(result);												
						break;
						
					case "find":
						System.out.println("[INFO] 명령어 'find' 실행");
						Human fHuman = sm.findHuman((String)caseObject);
						noos.writeObject(fHuman);											
						break;
						
					case "delete":	
						System.out.println("[INFO] 명령어 'delete' 실행");
						noos.writeObject(sm.deleteHuman((String)caseObject));											
						break;
					
					case "update":	
						System.out.println("[INFO] 명령어 'update' 실행");
						noos.writeObject(sm.updateHuman((Human)caseObject));											
						break;
						
					case "getList":	
						System.out.println("[INFO] 명령어 'getList' 실행");
						ArrayList<Human> list = sm.getHumanList();
						
						noos.writeObject(list);
						
						break;
					default:
						System.out.println("[ERR] 알 수 없는 명령어");
				}
			} catch(IOException ioe) {
				exit = true;
				System.out.println(ioe.getMessage());
			} catch(ClassNotFoundException cce) {
				exit = true;
				System.out.println(cce.getMessage());
			}
		}//while
	}
}
