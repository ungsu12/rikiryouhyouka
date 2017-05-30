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
	private final int PORT = 6666;	// 포트 번호
	
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private ArrayList<Human> list;

	/**
	 * 생성자
	 */
	public SESClientManager() {
		try{
			// 포트 번호를 통해 소켓을 생성한다
			socket = new Socket("localhost", PORT);
			System.out.println("[INFO] 서버 소켓 생성 성공");
			
			// 스트림 생성
			is = socket.getInputStream();			
			os = socket.getOutputStream();	
			System.out.println("[INFO] 스트림 생성 성공");
			
			// 오브젝트 스트림 생성
			oos = new ObjectOutputStream(os);
			ois = new ObjectInputStream(is);
			System.out.println("[INFO] 오브젝트 스트림 생성 성공");
		} catch(Exception e) {
			System.out.println("[INFO] 접속 도중 에러가 나타났습니다");
			// e.printStackTrace();
			closeStreams();
			System.exit(0);
		}
	}
	
	/**
	 * Human 객체를 서버에 저장한다
	 * @param human 서버에 저장할 Human 객체
	 */
	@Override
	public boolean insertHuman(Human human) {
		boolean result = false;
		Object[] request = { "insert", human };		
		result = (Boolean) this.sendRequest(request);
		return result;
	}

	/**
	 * 지정된 주민번호로 Human 객체를 찾는다
	 * @param jumin 찾고자 하는 주민번호
	 */
	@Override
	public Human findHuman(String jumin) {
		Human resultH = null;
		Object[] request={ "find", jumin };		
		resultH = (Human) sendRequest(request);
		return resultH;
	}

	/**
	 * 서버에서 지정된 주민번호를 가지는 Human 객체를 삭제한다
	 * @param jumin 삭제하고자 하는 주민번호
	 */
	@Override
	public boolean deleteHuman(String jumin) {
		Object[] request={ "delete", jumin };		
		boolean result = (Boolean) sendRequest(request);
		return result;
	}
	
	/**
	 * 모든 Human 리스트를 가져온다
	 * @return 모든 Human 정보 리스트
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Human> getHumanList() {
		Object[] request = { "getList", null };
		ArrayList<Human> list = (ArrayList<Human>) sendRequest(request);
		return list;
	}

	/**
	 * 지정된 주민번호를 가진 Human 객체를 갱신한다
	 * @param newData 갱신하고자 하는 Human 객체의 새로운 데이터 
	 */
	@Override
	public boolean updateHuman(Human newData) {
		Object[] request={ "update", newData };
		boolean result = (Boolean) sendRequest(request);
		return result;
	}
	
	/**
	 * 서버로 요청을 보낸다
	 * @param request 서버로 보낼 메시지
	 * @return 받는 메시지
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
	 * 열어 놓은 스트림들을 모두 닫는다.
	 */
	public void closeStreams() {
		// 인풋 스트림이 존재하면 닫는다
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
					
		// 아웃풋 스트림이 존재하면 닫는다
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		// 오브젝트 인풋 스트림이 존재하면 닫는다
		if (ois != null) {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// 오브젝트 아웃풋 스트림이 존재하면 닫는다
		if (oos != null) {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
