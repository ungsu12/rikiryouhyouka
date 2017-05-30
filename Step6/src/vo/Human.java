package vo;
import java.io.*;
/**
 * <pre>
 * SES(Soft Engineer School) ���� ���α׷��� �����ο�(Professor, Trainee, Staff) Ŭ������ ���� Ŭ����
 * �����ο� Ŭ�������� ���� �Ӽ��� �̸�, ����, �ֹι�ȣ�� ��� ������ ���´�.
 * </pre>
 * */
public class Human implements Serializable{

	private String name;  //�����ο� �̸�
	private int age;	  //�����ο� ����
	private String jumin; //�����ο� �ֹι�ȣ
	
	/**
	 * �־��� �̸�, ����, �ֹι�ȣ ������ ������ ���ο� Human ��ü�� �����Ѵ�.
	 * @param name �������� �̸�
	 * @param age �������� ����
	 * @param jumin �������� �ֹι�ȣ
	 * */
	public Human(){}
	public Human(String name, int age, String jumin) {
		this.name = name;
		this.age = age;
		this.jumin = jumin;
	}

	/**
	 * �̸��� ��ȸ�Ѵ�.
	 * @return Human ��ü�� ������ �ִ� �̸�
	 * */
	public String getName() {
		return name;
	}

	/**
	 * ���ο� �̸����� �����Ѵ�.
	 * @param name �����ϰ��� �ϴ� ���ο� �̸�
	 * */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ���̸� ��ȸ�Ѵ�.
	 * @return Human ��ü�� ������ �ִ� ����
	 * */
	public int getAge() {
		return age;
	}

	/**
	 * ���ο� ���̷� �����Ѵ�.
	 * @param age �����ϰ��� �ϴ� ���ο� ����
	 * */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * �ֹι�ȣ�� ��ȸ�Ѵ�.
	 * @return Human ��ü�� ������ �ִ� �ֹι�ȣ
	 * */
	public String getJumin() {
		return jumin;
	}

	/**
	 * ���ο� �ֹι�ȣ�� �����Ѵ�.
	 * @param jumin �����ϰ��� �ϴ� ���ο� �ֹι�ȣ
	 * */
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	
	/**
	 * Human ��ü�� ������ �ִ� �̸�, ����, �ֹι�ȣ ������ ����Ѵ�.
	 * */
	public void showInfo(){
		System.out.printf("�̸�: %s, ����: %d, �ֹι�ȣ: %s", name, age, jumin);
	}
	
}
