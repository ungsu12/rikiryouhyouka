package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import vo.Human;

/**
 * SES ������ �������� ȯ���� �����ϱ� ���� ������ Ŭ����
 * SESServer���� ������� ������ �̷������ SESServerThread ��ü�� �����Ͽ�
 * run() �޼��忡�� ObjectInputStream �� ObjectOutputStream�� �̿��Ͽ� Ŭ���̾�Ʈ�� �������� ����� �����Ѵ�.
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
				// ������Ʈ ��Ʈ�����κ��� ������Ʈ�� �о����
				Object[] readObejects= (Object[]) nois.readObject();
				
				String caseString = (String) readObejects[0];	// ��ɾ�
				Object caseObject = readObejects[1];			// ������Ʈ
				
				switch(caseString) {
					case "insert":	
						System.out.println("[INFO] ��ɾ� 'insert' ����");
						boolean result = sm.insertHuman((Human)caseObject);
						noos.writeObject(result);												
						break;
						
					case "find":
						System.out.println("[INFO] ��ɾ� 'find' ����");
						Human fHuman = sm.findHuman((String)caseObject);
						noos.writeObject(fHuman);											
						break;
						
					case "delete":	
						System.out.println("[INFO] ��ɾ� 'delete' ����");
						noos.writeObject(sm.deleteHuman((String)caseObject));											
						break;
					
					case "update":	
						System.out.println("[INFO] ��ɾ� 'update' ����");
						noos.writeObject(sm.updateHuman((Human)caseObject));											
						break;
						
					case "getList":	
						System.out.println("[INFO] ��ɾ� 'getList' ����");
						ArrayList<Human> list = sm.getHumanList();
						
						noos.writeObject(list);
						
						break;
					default:
						System.out.println("[ERR] �� �� ���� ��ɾ�");
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
