package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import manager.SEManager;
import vo.Human;

public class SESClientManager implements SEManager {
	private final int PORT = 6666;	// ��Ʈ ��ȣ
	
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private ArrayList<Human> list;

	/**
	 * ������
	 */
	public SESClientManager() {
		try{
			// ��Ʈ ��ȣ�� ���� ������ �����Ѵ�
			socket = new Socket("localhost", PORT);
			System.out.println("[INFO] ���� ���� ���� ����");
			
			// ��Ʈ�� ����
			is = socket.getInputStream();			
			os = socket.getOutputStream();	
			System.out.println("[INFO] ��Ʈ�� ���� ����");
			
			// ������Ʈ ��Ʈ�� ����
			oos = new ObjectOutputStream(os);
			ois = new ObjectInputStream(is);
			System.out.println("[INFO] ������Ʈ ��Ʈ�� ���� ����");
		} catch(Exception e) {
			System.out.println("[INFO] ���� ���� ������ ��Ÿ�����ϴ�");
			// e.printStackTrace();
			closeStreams();
			System.exit(0);
		}
	}
	
	/**
	 * Human ��ü�� ������ �����Ѵ�
	 * @param human ������ ������ Human ��ü
	 */
	@Override
	public boolean insertHuman(Human human) {
		boolean result = false;
		Object[] request = { "insert", human };		
		result = (Boolean) this.sendRequest(request);
		return result;
	}

	/**
	 * ������ �ֹι�ȣ�� Human ��ü�� ã�´�
	 * @param jumin ã���� �ϴ� �ֹι�ȣ
	 */
	@Override
	public Human findHuman(String jumin) {
		Human resultH = null;
		Object[] request={ "find", jumin };		
		resultH = (Human) sendRequest(request);
		return resultH;
	}

	/**
	 * �������� ������ �ֹι�ȣ�� ������ Human ��ü�� �����Ѵ�
	 * @param jumin �����ϰ��� �ϴ� �ֹι�ȣ
	 */
	@Override
	public boolean deleteHuman(String jumin) {
		Object[] request={ "delete", jumin };		
		boolean result = (Boolean) sendRequest(request);
		return result;
	}
	
	/**
	 * ��� Human ����Ʈ�� �����´�
	 * @return ��� Human ���� ����Ʈ
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Human> getHumanList() {
		Object[] request = { "getList", null };
		ArrayList<Human> list = (ArrayList<Human>) sendRequest(request);
		return list;
	}

	/**
	 * ������ �ֹι�ȣ�� ���� Human ��ü�� �����Ѵ�
	 * @param newData �����ϰ��� �ϴ� Human ��ü�� ���ο� ������ 
	 */
	@Override
	public boolean updateHuman(Human newData) {
		Object[] request={ "update", newData };
		boolean result = (Boolean) sendRequest(request);
		return result;
	}
	
	/**
	 * ������ ��û�� ������
	 * @param request ������ ���� �޽���
	 * @return �޴� �޽���
	 */
	public Object sendRequest(Object[] request){
		Object response = null;
		try{
			oos.writeObject(request);	
			response = ois.readObject();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(response);
		return response;
	}
	
	/**
	 * ���� ���� ��Ʈ������ ��� �ݴ´�.
	 */
	public void closeStreams() {
		// ��ǲ ��Ʈ���� �����ϸ� �ݴ´�
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
					
		// �ƿ�ǲ ��Ʈ���� �����ϸ� �ݴ´�
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		// ������Ʈ ��ǲ ��Ʈ���� �����ϸ� �ݴ´�
		if (ois != null) {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// ������Ʈ �ƿ�ǲ ��Ʈ���� �����ϸ� �ݴ´�
		if (oos != null) {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
