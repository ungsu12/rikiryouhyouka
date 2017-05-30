package manager;
import java.util.ArrayList;

import vo.Human;


public interface SEManager {
	
	/**
	 * 새로운 Human 객체를 등록한다. 이미 등록된 주민번호가 존재하면 등록 결과로 false를 반환하고, 등록에 성공하면 true를 반환한다.
	 * @param human 등록할 Professor, Trainee, Staff 클래스의 객체
	 * @return 등록 성공시 true를, 실패시 false를 반환한다.
	 * */
	public boolean insertHuman(Human human);

	/**
	 * 등록된 Human 객체를 검색한다.
	 * @param jumin 검색할 Human의 주민번호
	 * @return Human 배열에 등록된 객체들 중 파라메터로 전달된 주민번호와 일치되는 Human 객체, 검색 결과가 없을 시 null을 반환한다.
	 * */
	public Human findHuman(String jumin);

	/**
	 * 등록된 Human 객체를 삭제한다.
	 * @param jumin 삭제할 Human의 주민번호
	 * @return 주어진 주민번호와 일치되는 Human 객체의 삭제 결과, Human 배열에 등록된 객체들 중 파라메터로 전달된 주민번호와 일치되는 Human 객체가 발견되어 삭제를 성공하면 true를 그렇지 않으면 false를 반환
	 * */
	public boolean deleteHuman(String jumin);
	
	/**
	 * 등록된 Human 객체를 수정한다.
	 * @param newData 수정할 데이터를 가지고 있는 새로운 Human 객체 
	 * @return 수정 성공시 true를, 실패시 false를 반환한다.
	 * */
	public boolean updateHuman(Human newData);
		
	/**
	 * 등록된 모든 Human 정보를 조회한다.
	 * @return 등록된 모든 Human 객체를 저장한 ArrayList 객체
	 * */
	public ArrayList<Human> getHumanList();

}
