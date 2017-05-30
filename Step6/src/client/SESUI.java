package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import manager.SEManager;
import vo.Human;
import vo.Professor;
import vo.Staff;
import vo.Trainee;

public class SESUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lbl_title;
	private JPanel p_west;
	private JPanel p_west_1;
	private JPanel p_west_2;
	private JPanel p_west_3;
	private JPanel p_west_4;
	private JLabel lbl_name;
	private JTextField tf_name;
	private JLabel lbl_age;
	private JTextField tf_age;
	private JLabel lbl_jumin;
	private JTextField tf_jumin;
	private JLabel lbl_major;
	private JTextField tf_major;
	private JPanel p_west_5;
	private JPanel p_west_6;
	private JLabel lbl_stdNo;
	private JTextField tf_stdNo;
	private JLabel lbl_field;
	private JTextField tf_field;
	private JScrollPane sp_center;
	private JList li_humanList;
	private JPanel p_south;
	private JComboBox cb_humanSelect;
	private JButton btn_insert;
	private JButton btn_modify;
	private JButton btn_delete;
	private JButton btn_search;
	private JButton btn_ok;
	private JButton btn_cancel;
	private String preAction;
	private String selectedHuman;
	private Human selectedValue;
	private SEManager manager = new SESClientManager();


	/**
	 * Create the frame.
	 */
	public SESUI() {
		// ���� ����
		setTitle("��Soft Engineer School Manager��");
		// â ������ ���α׷� �����ϵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ������ ����
		setBounds(100, 100, 625, 300);
		
		// ������ �г� ����
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// ���α׷� Ÿ��Ʋ ����
		lbl_title = new JLabel("Soft Engineer School Manager");
		lbl_title.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lbl_title.setOpaque(true);
		lbl_title.setBackground(Color.ORANGE);
		lbl_title.setFont(new Font("����", Font.BOLD, 26));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbl_title, BorderLayout.NORTH);
		
		// ���� �г� ���� 1
		p_west = new JPanel();
		contentPane.add(p_west, BorderLayout.WEST);
		p_west.setLayout(new GridLayout(6, 1, 0, 0));
		
		// ���� �г� ���� 2
		p_west_1 = new JPanel();
		p_west.add(p_west_1);
		
		// �̸� ���̺� ����
		lbl_name = new JLabel("�̸�");
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setPreferredSize(new Dimension(60, 15));
		p_west_1.add(lbl_name);
		
		// �̸� �ؽ�Ʈ �ʵ� ����
		tf_name = new JTextField();
		p_west_1.add(tf_name);
		tf_name.setColumns(10);
		
		// ���� �г� ���� 2
		p_west_2 = new JPanel();
		p_west.add(p_west_2);
		
		// ���� ���̺� ����
		lbl_age = new JLabel("����");
		lbl_age.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_age.setPreferredSize(new Dimension(60, 15));
		p_west_2.add(lbl_age);
		
		// ���� �ؽ�Ʈ �ʵ� ����
		tf_age = new JTextField();
		p_west_2.add(tf_age);
		tf_age.setColumns(10);
		
		// ���� �г� ���� 4
		p_west_3 = new JPanel();
		p_west.add(p_west_3);
		
		// �ֹι�ȣ ���̺� ����
		lbl_jumin = new JLabel("�ֹι�ȣ");
		lbl_jumin.setPreferredSize(new Dimension(60, 15));
		lbl_jumin.setHorizontalAlignment(SwingConstants.CENTER);
		p_west_3.add(lbl_jumin);
		
		// �ֹι�ȣ �ؽ�Ʈ �ʵ� ����
		tf_jumin = new JTextField();
		p_west_3.add(tf_jumin);
		tf_jumin.setColumns(10);
		
		// ���� �г� ���� 5
		p_west_4 = new JPanel();
		p_west.add(p_west_4);
		
		// ���� ���̺� ����
		lbl_major = new JLabel("����");
		lbl_major.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_major.setPreferredSize(new Dimension(60, 15));
		p_west_4.add(lbl_major);
		
		// ���� �ؽ�Ʈ �ʵ� ����
		tf_major = new JTextField();
		p_west_4.add(tf_major);
		tf_major.setColumns(10);
		
		// ���� �г� ���� 6
		p_west_5 = new JPanel();
		p_west.add(p_west_5);
		
		// �л���ȣ ���̺� ����
		lbl_stdNo = new JLabel("�й�");
		lbl_stdNo.setPreferredSize(new Dimension(60, 15));
		lbl_stdNo.setHorizontalAlignment(SwingConstants.CENTER);
		p_west_5.add(lbl_stdNo);
		
		// �л���ȣ �ؽ�Ʈ �ʵ� ����
		tf_stdNo = new JTextField();
		p_west_5.add(tf_stdNo);
		tf_stdNo.setColumns(10);
		
		// ���� �г� ���� 7
		p_west_6 = new JPanel();
		p_west.add(p_west_6);
		
		// �μ� ���̺� ����
		lbl_field = new JLabel("�μ�");
		lbl_field.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_field.setPreferredSize(new Dimension(60, 15));
		p_west_6.add(lbl_field);
		
		// �μ� �ؽ�Ʈ �ʵ� ����
		tf_field = new JTextField();
		p_west_6.add(tf_field);
		tf_field.setColumns(10);
		
		// ��ũ���г� ���� _ �߾ӿ� ��ġ
		sp_center = new JScrollPane();
		sp_center.setBorder(new TitledBorder(null, "�������", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		contentPane.add(sp_center, BorderLayout.CENTER);
		
		// Human ��ü�� ������ ����Ʈ ����
		li_humanList = new JList();
		li_humanList.addMouseListener(new MouseHandler());
		sp_center.setViewportView(li_humanList);
		
		// �Ʒ��� �г� ����
		p_south = new JPanel();
		p_south.setForeground(Color.LIGHT_GRAY);
		contentPane.add(p_south, BorderLayout.SOUTH);
		
		// �޺��ڽ� ����
		cb_humanSelect = new JComboBox();
		cb_humanSelect.setOpaque(false);
		cb_humanSelect.setBackground(Color.LIGHT_GRAY);
		cb_humanSelect.addActionListener(this);
		cb_humanSelect.setModel(new DefaultComboBoxModel(new String[] {"�����", "����", "������", "���"}));
		p_south.add(cb_humanSelect);
		
		// ��� ��ư ����
		btn_insert = new JButton("���");
		btn_insert.addActionListener(this);
		p_south.add(btn_insert);
		
		// ���� ��ư ����
		btn_modify = new JButton("����");
		btn_modify.addActionListener(this);
		p_south.add(btn_modify);
		
		// �˻� ��ư ����
		btn_search = new JButton("�˻�");
		btn_search.addActionListener(this);
		p_south.add(btn_search);
		
		// ���� ��ư ����
		btn_delete = new JButton("����");
		btn_delete.addActionListener(this);
		p_south.add(btn_delete);
		
		// Ȯ�� ��ư ����
		btn_ok = new JButton("Ȯ��");
		btn_ok.addActionListener(this);
		p_south.add(btn_ok);
		
		// ��� ��ư ����
		btn_cancel = new JButton("���");
		btn_cancel.addActionListener(this);
		p_south.add(btn_cancel);
		
		// ��ư �ʱ�ȭ
		initButton(true);
		
		// �ؽ�Ʈ�ʵ� �ʱ�ȭ
		initField("all", false);
		
		// ����Ʈ �����ֱ�
		showList();
		
		// ȭ�� ���̵��� ����
		setVisible(true);
	}
	
	/**
	 * ����Ʈ�� �����ش�
	 */
	public void showList() {
		ArrayList<Human> list = manager.getHumanList();
		li_humanList.setListData(list.toArray());
	}
	
	/**
	 * �ؽ�Ʈ �ʵ���� Ÿ�Կ� ���� Ȱ��ȭ ���θ� �����Ѵ�
	 * @param type �ʱ�ȭ Ÿ��
	 * @param status Ȱ��ȭ ����
	 */
	public void initField(String type, boolean status){
		// �̸� �ؽ�Ʈ �ʵ��� Ȱ��ȭ ����
		tf_name.setEditable(status);
		// ���� �ؽ�Ʈ �ʵ��� Ȱ��ȭ ����
		tf_age.setEditable(status);
		// �ֹι�ȣ �ؽ�Ʈ �ʵ��� Ȱ��ȭ ����
		tf_jumin.setEditable(status);
		
		// ������ ��� ���� �ؽ�Ʈ �ʵ��� Ȱ��ȭ ���� ����
		if (type.equals("all") || type.equals("����")) {
			tf_major.setEditable(status);
		}
		
		// �������� ��� ���� �ؽ�Ʈ �ʵ��� Ȱ��ȭ ���� ����
		if (type.equals("all") || type.equals("������")) {
			tf_stdNo.setEditable(status);
		}
		
		// ����� ��� ���� �ؽ�Ʈ �ʵ��� Ȱ��ȭ ���� ����
		if (type.equals("all") || type.equals("���")) {
			tf_field.setEditable(status);
		}
	}
	
	/**
	 * ��ư���� Ȱ��ȭ ���θ� �����Ѵ�
	 * @param status Ȱ��ȭ ����
	 */
	public void initButton(boolean status){
		btn_insert.setEnabled(status);	// ���
		btn_modify.setEnabled(status);	// ����
		btn_delete.setEnabled(status);	// ����
		btn_search.setEnabled(status);	// �˻�
		btn_ok.setEnabled(!status);		// Ȯ��
		btn_cancel.setEnabled(!status);	// ���
	}
	
	/**
	 * �ؽ�Ʈ �ʵ���� �ؽ�Ʈ�� �� ���ڿ��� �����Ѵ�
	 */
	public void clearTextField(){
		tf_name.setText("");	// �̸�
		tf_age.setText("");		// ����
		tf_jumin.setText("");	// �ֹι�ȣ
		tf_major.setText("");	// ����
		tf_stdNo.setText("");	// �л���ȣ
		tf_field.setText("");	// �μ�
	}

	/**
	 * �̺�Ʈ�� ó���ϴ� �޼ҵ�
	 * @param e �׼��̺�Ʈ
	 */
	public void actionPerformed(ActionEvent e) {
		// �̺�Ʈ�κ��� �̺�Ʈ�� �߻���Ų �ҽ��� ���Ѵ�
		Object source = e.getSource();
		
		if(source == btn_insert){
			// ��� ��ư�� ��� �޺��ڽ��κ��� ���õ� �������� �����´�
			selectedHuman = (String)cb_humanSelect.getSelectedItem();
			
			if(selectedHuman.equals("�����")){
				// ���� �޺��ڽ��� ���õǾ��� �������� ������̶�� ���� �޽����� ����
				JOptionPane.showMessageDialog(this, "����� ����� ���� �����ϼ���!");
			} else {
				// ����� ���õǾ� �ִٸ� �ؽ�Ʈ �ʵ���� �� ���ڿ��� ��ü�ϰ�
				clearTextField();
				// ��ư���� Ȱ��ȭ ���¸� false�� �ϸ�
				initButton(false);
				// ���õ� �����ۿ��� �ʿ��� �ؽ�Ʈ�ʵ常 true�� �����Ѵ�
				initField(selectedHuman, true);
				preAction = "���";
			}
		} else if(source == btn_modify) {
			// ���� ��ư�� ������ ��� ���� �ʵ忡 �ִ� Human ��ü ������ null���� üũ�Ѵ�
			if(selectedValue == null){
				// null�̶�� ���� �޽����� ����
				JOptionPane.showMessageDialog(this, "������ �����͸� ���� �������ּ���!");
			} else {
				// null�� �ƴ϶�� ���õ� ��ü ������ ��� Ÿ�������� ���� selectedHuman�� ���� �����Ѵ�
				if(selectedValue instanceof Professor) selectedHuman ="����";
				if(selectedValue instanceof Trainee) selectedHuman ="������";
				if(selectedValue instanceof Staff) selectedHuman ="���";
				
				// ���õ� �����ۿ��� �ʿ��� �ؽ�Ʈ�ʵ常 true�� �����Ѵ� 
				initField(selectedHuman, true);
				// �ؽ�Ʈ �ʵ� �� Ű�� �ش��ϴ� �ֹι�ȣ�� �����ϸ� �� �ǹǷ� ��Ȱ��ȭ
				tf_jumin.setEditable(false);
				// �ٸ� ��ư���� ��Ȱ��ȭ�Ѵ�
				initButton(false);
				// Human ��ü �������� ǥ���ϴ� List�� ��Ȱ��ȭ�Ѵ�
				li_humanList.setEnabled(false);
				// Ȯ�� ��ư�� ���� �� ���� ���� �� �ֵ��� '����'�� �ߴٴ� ����� ����Ѵ�
				preAction = "����";
			}
		} else if(source == btn_search) {
			// �˻� ��ư�� ������ ��� �ؽ�Ʈ�ʵ���� �ʱ�ȭ�Ѵ�
			clearTextField();
			// �˻��� ���� �ֹι�ȣ���� Ȱ��ȭ�Ѵ�
			tf_jumin.setEditable(true);
			// �ٸ� ��ư���� ��Ȱ��ȭ�Ѵ�
			initButton(false);
			// Ȯ�� ��ư�� ���� ��  ���� ���� �� �ֵ��� '�˻�'�� �ߴٴ� ����� ����Ѵ�
			preAction = "�˻�";
		} else if(source == btn_delete) {
			// ���� ��ư�� ������ ��� ���õ� Human ��ü ������ �ִ��� Ȯ���Ѵ�
			if(selectedValue == null){
				// null�̶�� ���� �޽����� ����
				JOptionPane.showMessageDialog(this, "������ �����͸� ���� �������ּ���!");
			} else {
				// null�� �ƴ϶�� �ֹι�ȣ�� ������ �´�
				String jumin = tf_jumin.getText();
				// �Ŵ����� ���� ���� ����� �����Ѵ�. �̴� ������ ���� ������ �����ϰ� ��� ���θ� ��ȯ�Ѵ�.
				boolean result = manager.deleteHuman(jumin);
				// ����Ʈ�� �ٽ� �����Ѵ�
				showList();
				// �ؽ�Ʈ �ʵ带 �ʱ�ȭ�Ѵ�
				clearTextField();
			}
		} else if(source == btn_ok) {
			// Ȯ�� ��ư�� ������ ���
			Human h = null;
			// �̸� �ؽ�Ʈ�ʵ�κ��� �̸��� �����´�
			String name = tf_name.getText();
			// ���� �ؽ�Ʈ�ʵ忡 ���� ������ �ִٸ� int������ ��ȯ�Ѵ�
			int age = tf_age.getText().trim().length() > 0 ? Integer.parseInt(tf_age.getText()) : 0;
			// �ֹι�ȣ �ؽ�Ʈ�ʵ�κ��� �ֹι�ȣ�� �����´�
			String jumin = tf_jumin.getText();
			
			// Ȯ�� ��ư�� ������ ���� ������ �������� üũ�Ѵ�
			switch(preAction){
				case "���": 
					// ����̾��ٸ� �޺��ڽ����� ���õ� �׸� ���� Human ��ü�� �����Ѵ�
					if(selectedHuman.equals("����")) {		
						String major = tf_major.getText();
						h = new Professor(name, age, jumin, major);
					} else if(selectedHuman.equals("������")) {
						String stdNo = tf_stdNo.getText();
						h = new Trainee(name, age, jumin, stdNo);
					} else if(selectedHuman.equals("���")) {
						String field = tf_field.getText();
						h = new Staff(name, age, jumin, field);
					}
					// �Ŵ����� ���� Human ��ü ����� �����Ѵ�
					boolean result = manager.insertHuman(h);
					if(!result){
						// ��� ����� ���ٸ� �̹� ��ϵ� �ֹι�ȣ
						JOptionPane.showMessageDialog(this, "�̹� ��ϵ� �ֹι�ȣ�� �����մϴ�.");
						clearTextField();
					}
					break;
				case "����":		
					/* 
					 * �����̾��ٸ� ���� ���õǾ� �ִ� Value�� � Ÿ������ ���� 
					 * ���ڿ� �´� �ؽ�Ʈ�ʵ�κ��� ���� ������ ������ ��ü�� �����Ѵ�
					 */
					Human newHuman = null;
					if(selectedValue instanceof Professor){
						String newMajor = tf_major.getText();
						newHuman = new Professor(name, age, jumin, newMajor);
					} else if(selectedValue instanceof Trainee){
						String newStdNo = tf_stdNo.getText();
						newHuman = new Trainee(name, age, jumin, newStdNo);
					} else {
						String newField = tf_field.getText();
						newHuman = new Staff(name, age, jumin, newField);
					}
					
					// �Ŵ����� ���� ���� ������ Human ��ü�� �����Ѵ� 
					manager.updateHuman(newHuman);
					break;
				case "�˻�":
					// �˻��̾��ٸ� �ؽ�Ʈ�ʵ忡 �ִ� �ֹι�ȣ�� ������ �Ŵ����� ���� �˻��� �õ��Ѵ�
					Human foundHuman = manager.findHuman(jumin);
					
					if(foundHuman == null){
						// �˻��� ������� ���ٸ� �����޽����� ����
						JOptionPane.showMessageDialog(this, "�˻� ����� �����ϴ�.");
						clearTextField();
					} else {
						// �ƴ϶�� ������� �ؽ�Ʈ�ʵ忡 �����Ѵ�
						setTextFieldValue(foundHuman);
					}
					break;
			} //switch
			
			// Human ��ü ������ ��� �ִ� ����Ʈ�� Ȱ��ȭ�Ѵ�
			li_humanList.setEnabled(true);
			// �ؽ�Ʈ �ʵ���� �ʱ�ȭ�Ѵ�
			initField("all", false);
			// ��ư���� Ȱ��ȭ ���·� �����Ѵ�
			initButton(true);
			// ��ü ����Ʈ�� �ٽ� �����Ѵ�
			showList();
		} else if(source == btn_cancel){
			// ��ҿ��ٸ� �ؽ�Ʈ �ʵ带 ���� �ʱ�ȭ�ϰ�
			clearTextField();
			// �ؽ�Ʈ�ʵ���� ���� ��Ȱ��ȭ�ϰ�
			initField("all", false);
			// ��ư���� Ȱ��ȭ ���·� �����Ѵ�
			initButton(true);
			// Human ��ü ������ ��� �ִ� ����Ʈ�� Ȱ��ȭ�Ѵ�
			li_humanList.setEnabled(true);
			// ���õǾ� �ִ� Human ��ü�� null�� �����Ѵ�
			selectedValue = null;
		}
	}
	
	/**
	 * �ؽ�Ʈ �ʵ忡 Human ��ü�� ������ �����Ѵ�
	 * @param h ������ Human ��ü ����
	 */
	private void setTextFieldValue(Human h){
		// �̸� ����
		tf_name.setText(h.getName());
		
		// ���� ����
		tf_age.setText(Integer.toString(h.getAge()));
		
		// �ֹι�ȣ ����
		tf_jumin.setText(h.getJumin());
		
		// Human ��ü�� ���� ���¿� ���� �ٸ� �ؽ�Ʈ�ʵ忡 ���� ����
		if(h instanceof Professor){
			Professor p = (Professor)h;
			tf_major.setText(p.getMajor());
		} else if(h instanceof Trainee){
			Trainee t = (Trainee)h;
			tf_stdNo.setText(t.getStdNo());
		} else {
			Staff s = (Staff)h;
			tf_field.setText(s.getField());
		}
	}
	
	/**
	 * ���� Ŭ���� 
	 */
	private class MouseHandler extends MouseAdapter {
		
		/**
		 * ���콺 Ŭ�� �̺�Ʈ�� ó���Ѵ�
		 * @param me ���콺 �̺�Ʈ
		 */
		public void mouseClicked(MouseEvent me){
			// ���� Human ��ü ������ ��� �ִ� ����Ʈ���� ���õ� ���� �����´�
			selectedValue = (Human)li_humanList.getSelectedValue();
			// �ؽ�Ʈ �ʵ���� �ʱ�ȭ�ϰ�
			clearTextField();
			// ���õ� ��ü�� �ؽ�Ʈ�ʵ忡 �����Ѵ�
			setTextFieldValue(selectedValue);
		}
	}
	
	/**
	 * Ŭ���̾�Ʈ ������ ���� main
	 * @param args ����� �Է¹޴� ���ڿ���
	 */
	public static void main(String args[]){
		new SESUI();
	}
	
}
