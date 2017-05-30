package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import manager.SEManager;
import vo.Human;
import vo.Professor;
import vo.Staff;
import vo.Trainee;
/**
 * <pre>
 * SES(Soft Engineer School) 관리 프로그램의 업무로직을 관리하는 클래스
 * 주요 기능으로는 다음과 같다.
 * 1. 신규 관리인원 등록
 * 2. 등록인원 검색
 * 3. 등록인원 삭제
 * 4. 전체 등록인원 출력
 * </pre>
 * */
public class SESServerManager implements SEManager {
	
	/**
	 * 새로운 Human 객체를 등록한다.
	 * @param human 등록할 Professor, Trainee, Staff 클래스의 객체
	 * @return Human 객체의 등록 결과, 매개변수로 입력받은 Human 객체를 DB에 등록 성공하였으면 true, 아니면 false 
	 */
	@Override
	public boolean insertHuman(Human human){	
		boolean result = false;
		
		// 매니저로부터 Connection 객체를 얻는다
		Connection con = ConnectionManager.getConnection();
		
		try {
			// 정보 등록을 위한 쿼리문을 작성한다
			String sql = "insert into human values (?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// 작성된 쿼리문의 첫번째 물음표에 이름을 설정한다
			pstmt.setString(1, human.getName());
			// 작성된 쿼리문의 두번째 물음표에 나이를 설정한다
			pstmt.setInt(2, human.getAge());
			// 작성된 쿼리문의 세번째 물음표에 주민번호를 설정한다
			pstmt.setString(3, human.getJumin());
			
			// 작성된 쿼리문의 네번째 물음표에는 human의 타입에 따라 다르게 설정한다
			if(human instanceof Professor) {
				// '교수'라면 네번째 물음표에 타입을 교수로 설정한다
				pstmt.setString(4, "professor");
				// 쿼리문 실행
				pstmt.executeUpdate();
				
				// Human 객체를 Professor로 형 변환 
				Professor p = (Professor)human;
				// professor 테이블에 들어갈 쿼리문 작성 
				sql = "insert into professor values (?,?)";
				pstmt = con.prepareStatement(sql);
				// 첫번째 물음표엔 주민번호 설정
				pstmt.setString(1, p.getJumin());
				// 두번째 물음표엔 전공 설정
				pstmt.setString(2, p.getMajor());
				// 쿼리문 실행
				pstmt.executeUpdate();
			} else if(human instanceof Trainee) {
				// '연수생'이라면 네번째 물음표에 타입을 연수생으로 설정한다
				pstmt.setString(4, "trainee");
				// 쿼리문 실행
				pstmt.executeUpdate();
				
				// Human 객체를 Trainee로 형 변환
				Trainee t = (Trainee)human;
				// trainee 테이블에 들어갈 쿼리문 작성
				sql = "insert into trainee values (?,?)";
				pstmt = con.prepareStatement(sql);
				// 첫번째 물음표엔 주민번호 설정
				pstmt.setString(1, t.getJumin());
				// 두번째 물음표엔 학번 설정
				pstmt.setString(2, t.getStdNo());
				// 쿼리문 실행
				pstmt.executeUpdate();
			} else {
				// '운영진'이라면 네번째 물음표에 타입을 운영진으로 설정한다
				pstmt.setString(4, "staff");
				// 쿼리문 실행
				pstmt.executeUpdate();
				
				// Human 객체를 Staff로 형 변환
				Staff s = (Staff)human;
				// staff 테이블에 들어갈 쿼리문 작성
				sql = "insert into staff values (?,?)";
				pstmt = con.prepareStatement(sql);
				// 첫번째 물음표엔 주민번호 설정
				pstmt.setString(1, s.getJumin());
				// 두번째 물음표엔 부서 설정
				pstmt.setString(2, s.getField());
				// 쿼리문 실행
				pstmt.executeUpdate();
			}
			
			// 결과를 true로 설정한다
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 끝나면 Connection을 종료한다
			ConnectionManager.close(con);
		}

		return result;		
	}

	/**
	 * 등록된 Human 객체를 검색한다.
	 * @param jumin 검색할 Human의 주민번호
	 * @return Human 배열에 등록된 객체들 중 파라메터로 전달된 주민번호와 일치되는 Human 객체, 검색 결과가 없을 시 null을 반환한다.
	 */
	@Override
	public Human findHuman(String jumin){
		Human h = null;
		Connection con = null;
		try {
			// 매니저로부터 Connection 객체를 얻는다
			con = ConnectionManager.getConnection();
			
			// 정보 검색을 위한 쿼리문을 작성한다
			String sql = "select * from human where jumin = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// 첫번째 물음표에 주민번호를 설정한다
			pstmt.setString(1, jumin);
			// 쿼리문을 실행하여 결과셋을 얻는다
			ResultSet rs = pstmt.executeQuery();
			
			// 만약 결과값이 존재한다면
			if(rs.next()){
				// 결과셋으로부터 이름을 얻는다 
				String name = rs.getString("name");
				// 결과셋으로부터 나이를 얻는다
				int age = rs.getInt("age");
				// 결과셋으로부터 타입을 얻는다
				String personType = rs.getString("type");
				
				// 타입에 따라 다른 정보로 얻어온다 
				if(personType.equals("professor")){
					// '교수'라면 주민번호로 전공을 얻어온다
					sql = "select major from professor where jumin = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, jumin);
					rs = pstmt.executeQuery();
					rs.next();
					String major = rs.getString("major");
					// Professor 객체를 생성
					h = new Professor(name, age, jumin, major);
				} else if(personType.equals("trainee")) {
					// '연수생'이라면 주민번호로 학번을 얻어온다
					sql = "select stdNo from trainee where jumin = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, jumin);
					rs = pstmt.executeQuery();
					rs.next();
					String stdNo = rs.getString("stdNo");
					// Trainee 객체를 생성
					h = new Trainee(name, age, jumin, stdNo);
				} else {
					// '운영진'이라면 주민번호로 부서를 얻어온다 
					sql = "select field from staff where jumin = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, jumin);
					rs = pstmt.executeQuery();
					rs.next();
					String field = rs.getString("field");
					// Trainee 객체를 생성
					h = new Staff(name, age, jumin, field);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 끝나면 Connection을 종료한다
			ConnectionManager.close(con);
		}
		
		return h;			
	}

	/**
	 * 등록된 Human 객체를 삭제한다.
	 * @param jumin 삭제할 Human의 주민번호
	 * @return 주어진 주민번호와 일치되는 Human 객체의 삭제 결과, Human 배열에 등록된 객체들 중 파라메터로 전달된 주민번호와 일치되는 Human 객체가 발견되어 삭제를 성공하면 true를 그렇지 않으면 false를 반환
	 */
	@Override
	public boolean deleteHuman(String jumin){
		boolean result = false;
		Connection con = null;
		
		try {
			// 매니저로부터 Connection 객체를 얻는다
			con = ConnectionManager.getConnection();
			// 먼저 Human 객체 정보를 얻어온다
			Human h = findHuman(jumin);
			
			if(h instanceof Professor){
				// '교수'라면 professor 테이블에서 해당 교수를 삭제한다
				String sql = "delete from professor where jumin=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, jumin);
				pstmt.executeUpdate();
			} else if(h instanceof Trainee){
				// '연수생'이라면 trainee 테이블에서 해당 연수생을 삭제한다
				String sql = "delete from trainee where jumin=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, jumin);
				pstmt.executeUpdate();
			} else {
				// 그 이외, 즉 '운영진'이라면 staff 테이블에서 해당 운영진을 삭제한다
				String sql = "delete from staff where jumin=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, jumin);
				pstmt.executeUpdate();
			}

			// 그리고 human 테이블에서 해당하는 사람을 삭제한다
			String sql = "delete from human where jumin=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, jumin);
			pstmt.executeUpdate();
			
			// 결과값은 true
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다 끝나면 Connection을 종료한다
			ConnectionManager.close(con);
		}
		
		return result;
	}
	
	/**
	 * 전체 Human 정보를 검색해 가져온다
	 * @return human 테이블과 그 외 professor, trainee, staff 테이블로부터 가져온 정보로 생성된 Human 객체 정보 리스트
	 */
	@Override
	public ArrayList<Human> getHumanList() {
		// 결과값을 담을 리스트를 준비한다
		ArrayList<Human> humanList = new ArrayList<Human>();
		Connection con = null;
		
		try {
			// Connection 객체를 생성한다
			con = ConnectionManager.getConnection();
			
			// human과 professor의 주민번호가 일치하는 정보가 존재하면 다 가져온다
			String professorQuery = "select h.name, h.age, h.jumin, p.major from human h, professor p where h.jumin = p.jumin";
			PreparedStatement pstmt = con.prepareStatement(professorQuery);
			ResultSet rs = pstmt.executeQuery();
			// 결과셋을 반복하며 Professor 객체 정보를 모두 생성한다
			while(rs.next()){
				humanList.add(new Professor(rs.getString("name"), rs.getInt("age"), rs.getString("jumin"), rs.getString("major")));
			}
			
			// human과 trainee의 주민번호가 일치하는 정보가 존재하면 다 가져온다
			String traineeQuery = "select h.name, h.age, h.jumin, t.stdNo from human h, trainee t where h.jumin = t.jumin";
			pstmt = con.prepareStatement(traineeQuery);
			rs = pstmt.executeQuery();
			// 결과셋을 반복하며 Trainee 객체 정보를 모두 생성한다
			while(rs.next()){
				humanList.add(new Trainee(rs.getString("name"), rs.getInt("age"), rs.getString("jumin"), rs.getString("stdNo")));
			}
			
			// human과 staff의 주민번호가 일치하는 정보가 존재하면 다 가져온다
			String staffQuery = "select h.name, h.age, h.jumin, s.field from human h, staff s where h.jumin = s.jumin";
			pstmt = con.prepareStatement(staffQuery);
			rs = pstmt.executeQuery();
			// 결과셋을 반복하며 Staff 객체 정보를 모두 생성한다
			while(rs.next()){
				humanList.add(new Staff(rs.getString("name"), rs.getInt("age"), rs.getString("jumin"), rs.getString("field")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 끝나면 Connection 객체를 종료한다
			ConnectionManager.close(con);
		}
		
		return humanList;
	}

	/**
	 * 등록된 Human 객체를 갱신한다
	 * @param newData 갱신하고자 하는 데이터
	 * @return 지정된 주민번호를 가진 Human 객체의 갱신 결과, 매개변수로 입력받은 Human 객체를 DB에 갱신 성공하였으면 true, 아니면 false
	 */
	@Override
	public boolean updateHuman(Human newData) {
		boolean result = false;
		Connection con = null;
		
		try {
			// 매니저로부터 Connection 객체를 얻는다
			con = ConnectionManager.getConnection();
			// 갱신에 사용될 쿼리문을 준비한다
			String sql = "update human set name=?, age=? where jumin=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// 첫번째 물음표엔 이름을 설정한다
			pstmt.setString(1, newData.getName());
			// 두번째 물음표엔 나이를 설정한다(int)
			pstmt.setInt(2, newData.getAge());
			// 세번째 물음표엔 주민번호를 설정한다
			pstmt.setString(3, newData.getJumin());
			// 쿼리문 실행
			pstmt.executeUpdate();
			
			// 새로운 데이터의 타입에 따라 테이블 갱신도 달라진다
			if(newData instanceof Professor){
				// 갱신할 데이터가 '교수'라면
				Professor p = (Professor)newData;
				// professor 테이블에 전공을 갱신한다
				sql = "update professor set major=? where jumin=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, p.getMajor());
				pstmt.setString(2, p.getJumin());
				// 쿼리문 실행
				pstmt.executeUpdate();
			} else if(newData instanceof Trainee){
				// 갱신할 데이터가 '연수생'이라면
				Trainee t = (Trainee)newData;
				// trainee 테이블에 학번을 갱신한다
				sql = "update trainee set stdNo=? where jumin=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, t.getStdNo());
				pstmt.setString(2, t.getJumin());
				// 쿼리문 실행
				pstmt.executeUpdate();
			} else {
				// 갱신할 데이터가 '운영진'이라면
				Staff s = (Staff)newData;
				// staff 테이블에 부서를 갱신한다
				sql = "update staff set field=? where jumin=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, s.getField());
				pstmt.setString(2, s.getJumin());
				// 쿼리문 실행
				pstmt.executeUpdate();
			}
			
			// 성공하면 true
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 끝나면 Connection을 종료한다
			ConnectionManager.close(con);
		}
		
		return result;
	}
}