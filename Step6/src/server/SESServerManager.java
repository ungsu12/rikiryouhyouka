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
 * SES(Soft Engineer School) ���� ���α׷��� ���������� �����ϴ� Ŭ����
 * �ֿ� ������δ� ������ ����.
 * 1. �ű� �����ο� ���
 * 2. ����ο� �˻�
 * 3. ����ο� ����
 * 4. ��ü ����ο� ���
 * </pre>
 * */
public class SESServerManager implements SEManager {
	
	/**
	 * ���ο� Human ��ü�� ����Ѵ�.
	 * @param human ����� Professor, Trainee, Staff Ŭ������ ��ü
	 * @return Human ��ü�� ��� ���, �Ű������� �Է¹��� Human ��ü�� DB�� ��� �����Ͽ����� true, �ƴϸ� false 
	 */
	@Override
	public boolean insertHuman(Human human){	
		boolean result = false;
		
		// �Ŵ����κ��� Connection ��ü�� ��´�
		Connection con = ConnectionManager.getConnection();
		
		try {
			// ���� ����� ���� �������� �ۼ��Ѵ�
			String sql = "insert into human values (?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// �ۼ��� �������� ù��° ����ǥ�� �̸��� �����Ѵ�
			pstmt.setString(1, human.getName());
			// �ۼ��� �������� �ι�° ����ǥ�� ���̸� �����Ѵ�
			pstmt.setInt(2, human.getAge());
			// �ۼ��� �������� ����° ����ǥ�� �ֹι�ȣ�� �����Ѵ�
			pstmt.setString(3, human.getJumin());
			
			// �ۼ��� �������� �׹�° ����ǥ���� human�� Ÿ�Կ� ���� �ٸ��� �����Ѵ�
			if(human instanceof Professor) {
				// '����'��� �׹�° ����ǥ�� Ÿ���� ������ �����Ѵ�
				pstmt.setString(4, "professor");
				// ������ ����
				pstmt.executeUpdate();
				
				// Human ��ü�� Professor�� �� ��ȯ 
				Professor p = (Professor)human;
				// professor ���̺� �� ������ �ۼ� 
				sql = "insert into professor values (?,?)";
				pstmt = con.prepareStatement(sql);
				// ù��° ����ǥ�� �ֹι�ȣ ����
				pstmt.setString(1, p.getJumin());
				// �ι�° ����ǥ�� ���� ����
				pstmt.setString(2, p.getMajor());
				// ������ ����
				pstmt.executeUpdate();
			} else if(human instanceof Trainee) {
				// '������'�̶�� �׹�° ����ǥ�� Ÿ���� ���������� �����Ѵ�
				pstmt.setString(4, "trainee");
				// ������ ����
				pstmt.executeUpdate();
				
				// Human ��ü�� Trainee�� �� ��ȯ
				Trainee t = (Trainee)human;
				// trainee ���̺� �� ������ �ۼ�
				sql = "insert into trainee values (?,?)";
				pstmt = con.prepareStatement(sql);
				// ù��° ����ǥ�� �ֹι�ȣ ����
				pstmt.setString(1, t.getJumin());
				// �ι�° ����ǥ�� �й� ����
				pstmt.setString(2, t.getStdNo());
				// ������ ����
				pstmt.executeUpdate();
			} else {
				// '���'�̶�� �׹�° ����ǥ�� Ÿ���� ������� �����Ѵ�
				pstmt.setString(4, "staff");
				// ������ ����
				pstmt.executeUpdate();
				
				// Human ��ü�� Staff�� �� ��ȯ
				Staff s = (Staff)human;
				// staff ���̺� �� ������ �ۼ�
				sql = "insert into staff values (?,?)";
				pstmt = con.prepareStatement(sql);
				// ù��° ����ǥ�� �ֹι�ȣ ����
				pstmt.setString(1, s.getJumin());
				// �ι�° ����ǥ�� �μ� ����
				pstmt.setString(2, s.getField());
				// ������ ����
				pstmt.executeUpdate();
			}
			
			// ����� true�� �����Ѵ�
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// ������ Connection�� �����Ѵ�
			ConnectionManager.close(con);
		}

		return result;		
	}

	/**
	 * ��ϵ� Human ��ü�� �˻��Ѵ�.
	 * @param jumin �˻��� Human�� �ֹι�ȣ
	 * @return Human �迭�� ��ϵ� ��ü�� �� �Ķ���ͷ� ���޵� �ֹι�ȣ�� ��ġ�Ǵ� Human ��ü, �˻� ����� ���� �� null�� ��ȯ�Ѵ�.
	 */
	@Override
	public Human findHuman(String jumin){
		Human h = null;
		Connection con = null;
		try {
			// �Ŵ����κ��� Connection ��ü�� ��´�
			con = ConnectionManager.getConnection();
			
			// ���� �˻��� ���� �������� �ۼ��Ѵ�
			String sql = "select * from human where jumin = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// ù��° ����ǥ�� �ֹι�ȣ�� �����Ѵ�
			pstmt.setString(1, jumin);
			// �������� �����Ͽ� ������� ��´�
			ResultSet rs = pstmt.executeQuery();
			
			// ���� ������� �����Ѵٸ�
			if(rs.next()){
				// ��������κ��� �̸��� ��´� 
				String name = rs.getString("name");
				// ��������κ��� ���̸� ��´�
				int age = rs.getInt("age");
				// ��������κ��� Ÿ���� ��´�
				String personType = rs.getString("type");
				
				// Ÿ�Կ� ���� �ٸ� ������ ���´� 
				if(personType.equals("professor")){
					// '����'��� �ֹι�ȣ�� ������ ���´�
					sql = "select major from professor where jumin = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, jumin);
					rs = pstmt.executeQuery();
					rs.next();
					String major = rs.getString("major");
					// Professor ��ü�� ����
					h = new Professor(name, age, jumin, major);
				} else if(personType.equals("trainee")) {
					// '������'�̶�� �ֹι�ȣ�� �й��� ���´�
					sql = "select stdNo from trainee where jumin = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, jumin);
					rs = pstmt.executeQuery();
					rs.next();
					String stdNo = rs.getString("stdNo");
					// Trainee ��ü�� ����
					h = new Trainee(name, age, jumin, stdNo);
				} else {
					// '���'�̶�� �ֹι�ȣ�� �μ��� ���´� 
					sql = "select field from staff where jumin = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, jumin);
					rs = pstmt.executeQuery();
					rs.next();
					String field = rs.getString("field");
					// Trainee ��ü�� ����
					h = new Staff(name, age, jumin, field);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// ������ Connection�� �����Ѵ�
			ConnectionManager.close(con);
		}
		
		return h;			
	}

	/**
	 * ��ϵ� Human ��ü�� �����Ѵ�.
	 * @param jumin ������ Human�� �ֹι�ȣ
	 * @return �־��� �ֹι�ȣ�� ��ġ�Ǵ� Human ��ü�� ���� ���, Human �迭�� ��ϵ� ��ü�� �� �Ķ���ͷ� ���޵� �ֹι�ȣ�� ��ġ�Ǵ� Human ��ü�� �߰ߵǾ� ������ �����ϸ� true�� �׷��� ������ false�� ��ȯ
	 */
	@Override
	public boolean deleteHuman(String jumin){
		boolean result = false;
		Connection con = null;
		
		try {
			// �Ŵ����κ��� Connection ��ü�� ��´�
			con = ConnectionManager.getConnection();
			// ���� Human ��ü ������ ���´�
			Human h = findHuman(jumin);
			
			if(h instanceof Professor){
				// '����'��� professor ���̺��� �ش� ������ �����Ѵ�
				String sql = "delete from professor where jumin=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, jumin);
				pstmt.executeUpdate();
			} else if(h instanceof Trainee){
				// '������'�̶�� trainee ���̺��� �ش� �������� �����Ѵ�
				String sql = "delete from trainee where jumin=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, jumin);
				pstmt.executeUpdate();
			} else {
				// �� �̿�, �� '���'�̶�� staff ���̺��� �ش� ����� �����Ѵ�
				String sql = "delete from staff where jumin=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, jumin);
				pstmt.executeUpdate();
			}

			// �׸��� human ���̺��� �ش��ϴ� ����� �����Ѵ�
			String sql = "delete from human where jumin=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, jumin);
			pstmt.executeUpdate();
			
			// ������� true
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �� ������ Connection�� �����Ѵ�
			ConnectionManager.close(con);
		}
		
		return result;
	}
	
	/**
	 * ��ü Human ������ �˻��� �����´�
	 * @return human ���̺�� �� �� professor, trainee, staff ���̺�κ��� ������ ������ ������ Human ��ü ���� ����Ʈ
	 */
	@Override
	public ArrayList<Human> getHumanList() {
		// ������� ���� ����Ʈ�� �غ��Ѵ�
		ArrayList<Human> humanList = new ArrayList<Human>();
		Connection con = null;
		
		try {
			// Connection ��ü�� �����Ѵ�
			con = ConnectionManager.getConnection();
			
			// human�� professor�� �ֹι�ȣ�� ��ġ�ϴ� ������ �����ϸ� �� �����´�
			String professorQuery = "select h.name, h.age, h.jumin, p.major from human h, professor p where h.jumin = p.jumin";
			PreparedStatement pstmt = con.prepareStatement(professorQuery);
			ResultSet rs = pstmt.executeQuery();
			// ������� �ݺ��ϸ� Professor ��ü ������ ��� �����Ѵ�
			while(rs.next()){
				humanList.add(new Professor(rs.getString("name"), rs.getInt("age"), rs.getString("jumin"), rs.getString("major")));
			}
			
			// human�� trainee�� �ֹι�ȣ�� ��ġ�ϴ� ������ �����ϸ� �� �����´�
			String traineeQuery = "select h.name, h.age, h.jumin, t.stdNo from human h, trainee t where h.jumin = t.jumin";
			pstmt = con.prepareStatement(traineeQuery);
			rs = pstmt.executeQuery();
			// ������� �ݺ��ϸ� Trainee ��ü ������ ��� �����Ѵ�
			while(rs.next()){
				humanList.add(new Trainee(rs.getString("name"), rs.getInt("age"), rs.getString("jumin"), rs.getString("stdNo")));
			}
			
			// human�� staff�� �ֹι�ȣ�� ��ġ�ϴ� ������ �����ϸ� �� �����´�
			String staffQuery = "select h.name, h.age, h.jumin, s.field from human h, staff s where h.jumin = s.jumin";
			pstmt = con.prepareStatement(staffQuery);
			rs = pstmt.executeQuery();
			// ������� �ݺ��ϸ� Staff ��ü ������ ��� �����Ѵ�
			while(rs.next()){
				humanList.add(new Staff(rs.getString("name"), rs.getInt("age"), rs.getString("jumin"), rs.getString("field")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// ������ Connection ��ü�� �����Ѵ�
			ConnectionManager.close(con);
		}
		
		return humanList;
	}

	/**
	 * ��ϵ� Human ��ü�� �����Ѵ�
	 * @param newData �����ϰ��� �ϴ� ������
	 * @return ������ �ֹι�ȣ�� ���� Human ��ü�� ���� ���, �Ű������� �Է¹��� Human ��ü�� DB�� ���� �����Ͽ����� true, �ƴϸ� false
	 */
	@Override
	public boolean updateHuman(Human newData) {
		boolean result = false;
		Connection con = null;
		
		try {
			// �Ŵ����κ��� Connection ��ü�� ��´�
			con = ConnectionManager.getConnection();
			// ���ſ� ���� �������� �غ��Ѵ�
			String sql = "update human set name=?, age=? where jumin=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// ù��° ����ǥ�� �̸��� �����Ѵ�
			pstmt.setString(1, newData.getName());
			// �ι�° ����ǥ�� ���̸� �����Ѵ�(int)
			pstmt.setInt(2, newData.getAge());
			// ����° ����ǥ�� �ֹι�ȣ�� �����Ѵ�
			pstmt.setString(3, newData.getJumin());
			// ������ ����
			pstmt.executeUpdate();
			
			// ���ο� �������� Ÿ�Կ� ���� ���̺� ���ŵ� �޶�����
			if(newData instanceof Professor){
				// ������ �����Ͱ� '����'���
				Professor p = (Professor)newData;
				// professor ���̺� ������ �����Ѵ�
				sql = "update professor set major=? where jumin=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, p.getMajor());
				pstmt.setString(2, p.getJumin());
				// ������ ����
				pstmt.executeUpdate();
			} else if(newData instanceof Trainee){
				// ������ �����Ͱ� '������'�̶��
				Trainee t = (Trainee)newData;
				// trainee ���̺� �й��� �����Ѵ�
				sql = "update trainee set stdNo=? where jumin=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, t.getStdNo());
				pstmt.setString(2, t.getJumin());
				// ������ ����
				pstmt.executeUpdate();
			} else {
				// ������ �����Ͱ� '���'�̶��
				Staff s = (Staff)newData;
				// staff ���̺� �μ��� �����Ѵ�
				sql = "update staff set field=? where jumin=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, s.getField());
				pstmt.setString(2, s.getJumin());
				// ������ ����
				pstmt.executeUpdate();
			}
			
			// �����ϸ� true
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// ������ Connection�� �����Ѵ�
			ConnectionManager.close(con);
		}
		
		return result;
	}
}