package vo;

/**
 * <pre>
 * SES(Soft Engineer School) ���� ���α׷��� �����ο� �� ������ ���� ������ �����ϴ� Ŭ����
 * Human Ŭ������ ����ϰ� ������, Professor ��ü�� �߰����� ������ ������ ��� ������ ���´�.
 * </pre>
 * */
public class Professor extends Human{
	private String major; //������ ��������

	/**
	 * �־��� �̸�, ����, �ֹι�ȣ, �������� ������ ������ ���ο� Professor ��ü�� �����Ѵ�.
	 * @param name ������ �̸�
	 * @param age ������ ����
	 * @param jumin ������ �ֹι�ȣ
	 * @param major ������ ��������
	 * */
	public Professor(String name, int age, String jumin, String major) {
		super(name, age, jumin);
		this.major = major;
	}

	/**
	 * ���������� ��ȸ�Ѵ�.
	 * @return Professor ��ü�� ������ �ִ� ��������
	 * */
	public String getMajor() {
		return major;
	}

	/**
	 * ���ο� ������������ �����Ѵ�.
	 * @param major �����ϰ��� �ϴ� ���ο� ��������
	 * */
	public void setMajor(String major) {
		this.major = major;
	}
	
	@Override
	/**
	 * Professor ��ü�� ������ �ִ� �̸�, ����, �ֹι�ȣ, �������� ������ ����Ѵ�. 
	 * */
	public void showInfo(){
		super.showInfo();
		System.out.printf(", ����: %s%n", major);
	}
	
}
