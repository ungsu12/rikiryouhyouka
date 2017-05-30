package vo;

/**
 * <pre>
 * SES(Soft Engineer School) ���� ���α׷��� �����ο� �� �������� ���� ������ �����ϴ� Ŭ����
 * Human Ŭ������ ����ϰ� ������, Trainee ��ü�� �߰����� ������ �й��� ��� ������ ���´�.
 * </pre>
 * */
public class Trainee extends Human {
	private String stdNo; //������ �й�

	/**
	 * �־��� �̸�, ����, �ֹι�ȣ, �й� ������ ������ ���ο� Trainee ��ü�� �����Ѵ�.
	 * @param name �������� �̸�
	 * @param age �������� ����
	 * @param jumin �������� �ֹι�ȣ
	 * @param stdNo �������� �й�
	 * */
	public Trainee(String name, int age, String jumin, String stdNo) {
		super(name, age, jumin);
		this.stdNo = stdNo;
	}

	/**
	 * �й��� ��ȸ�Ѵ�.
	 * @return Trainee ��ü�� ������ �ִ� �й�
	 * */
	public String getStdNo() {
		return stdNo;
	}

	/**
	 * ���ο� �й����� �����Ѵ�.
	 * @param stdNo �����ϰ��� �ϴ� ���ο� �й�
	 * */
	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	@Override
	/**
	 * Trainee ��ü�� ������ �ִ� �̸�, ����, �ֹι�ȣ, �й� ������ ����Ѵ�.
	 * */
	public void showInfo() {
		super.showInfo();
		System.out.printf(", �й�: %s%n", stdNo);
	}
	
	
}
