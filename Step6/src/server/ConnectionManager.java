package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";	// JDBC driver ��Ű����
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";	// URL
	private static final String dbid = "hr";	// �����ͺ��̽� ID
	private static final String dbpw = "hr";	// �����ͺ��̽� ��й�ȣ
	

	// JDBC ����̹� �ε�
	static {
		try{
			// ���ڿ��� Ŭ������ ã�´�
			Class.forName(driver); 
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// �����ڸ� private���� �����Ѵ�
	// ����: �� �ϳ��� ��ü�� �����ϰ� �ٸ� � �̵� �� Ŭ������ ��� ������ �� ���� ���� ����
	private ConnectionManager() {}
		
	/**
	 * ����̹� �Ŵ����κ��� Connection�� �����Ͽ� ��ȯ�Ѵ�
	 * @return Connection ��ü
	 */
	public static Connection getConnection() {
		Connection con = null;
		try {
			// ����̹� �Ŵ����� �̿��Ͽ� ������ �õ��Ѵ�
			con = DriverManager.getConnection(url, dbid, dbpw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * Connection ������ �����Ѵ� 
	 * @param con Connection ��ü
	 */
	public static void close(Connection con) {
		try {
			// �Ű������� �Է¹��� Connection ��ü�� null�� �ƴ϶�� �����Ѵ�
			if (con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
