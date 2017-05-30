package manager;
import java.util.ArrayList;

import vo.Human;


public interface SEManager {
	
	/**
	 * ���ο� Human ��ü�� ����Ѵ�. �̹� ��ϵ� �ֹι�ȣ�� �����ϸ� ��� ����� false�� ��ȯ�ϰ�, ��Ͽ� �����ϸ� true�� ��ȯ�Ѵ�.
	 * @param human ����� Professor, Trainee, Staff Ŭ������ ��ü
	 * @return ��� ������ true��, ���н� false�� ��ȯ�Ѵ�.
	 * */
	public boolean insertHuman(Human human);

	/**
	 * ��ϵ� Human ��ü�� �˻��Ѵ�.
	 * @param jumin �˻��� Human�� �ֹι�ȣ
	 * @return Human �迭�� ��ϵ� ��ü�� �� �Ķ���ͷ� ���޵� �ֹι�ȣ�� ��ġ�Ǵ� Human ��ü, �˻� ����� ���� �� null�� ��ȯ�Ѵ�.
	 * */
	public Human findHuman(String jumin);

	/**
	 * ��ϵ� Human ��ü�� �����Ѵ�.
	 * @param jumin ������ Human�� �ֹι�ȣ
	 * @return �־��� �ֹι�ȣ�� ��ġ�Ǵ� Human ��ü�� ���� ���, Human �迭�� ��ϵ� ��ü�� �� �Ķ���ͷ� ���޵� �ֹι�ȣ�� ��ġ�Ǵ� Human ��ü�� �߰ߵǾ� ������ �����ϸ� true�� �׷��� ������ false�� ��ȯ
	 * */
	public boolean deleteHuman(String jumin);
	
	/**
	 * ��ϵ� Human ��ü�� �����Ѵ�.
	 * @param newData ������ �����͸� ������ �ִ� ���ο� Human ��ü 
	 * @return ���� ������ true��, ���н� false�� ��ȯ�Ѵ�.
	 * */
	public boolean updateHuman(Human newData);
		
	/**
	 * ��ϵ� ��� Human ������ ��ȸ�Ѵ�.
	 * @return ��ϵ� ��� Human ��ü�� ������ ArrayList ��ü
	 * */
	public ArrayList<Human> getHumanList();

}
