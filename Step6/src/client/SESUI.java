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
		// 제목 지정
		setTitle("◀Soft Engineer School Manager▶");
		// 창 닫을시 프로그램 종료하도록 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 사이즈 설정
		setBounds(100, 100, 625, 300);
		
		// 콘텐츠 패널 생성
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// 프로그램 타이틀 생성
		lbl_title = new JLabel("Soft Engineer School Manager");
		lbl_title.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lbl_title.setOpaque(true);
		lbl_title.setBackground(Color.ORANGE);
		lbl_title.setFont(new Font("굴림", Font.BOLD, 26));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbl_title, BorderLayout.NORTH);
		
		// 왼쪽 패널 생성 1
		p_west = new JPanel();
		contentPane.add(p_west, BorderLayout.WEST);
		p_west.setLayout(new GridLayout(6, 1, 0, 0));
		
		// 왼쪽 패널 생성 2
		p_west_1 = new JPanel();
		p_west.add(p_west_1);
		
		// 이름 레이블 생성
		lbl_name = new JLabel("이름");
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setPreferredSize(new Dimension(60, 15));
		p_west_1.add(lbl_name);
		
		// 이름 텍스트 필드 생성
		tf_name = new JTextField();
		p_west_1.add(tf_name);
		tf_name.setColumns(10);
		
		// 왼쪽 패널 생성 2
		p_west_2 = new JPanel();
		p_west.add(p_west_2);
		
		// 나이 레이블 생성
		lbl_age = new JLabel("나이");
		lbl_age.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_age.setPreferredSize(new Dimension(60, 15));
		p_west_2.add(lbl_age);
		
		// 나이 텍스트 필드 생성
		tf_age = new JTextField();
		p_west_2.add(tf_age);
		tf_age.setColumns(10);
		
		// 왼쪽 패널 생성 4
		p_west_3 = new JPanel();
		p_west.add(p_west_3);
		
		// 주민번호 레이블 생성
		lbl_jumin = new JLabel("주민번호");
		lbl_jumin.setPreferredSize(new Dimension(60, 15));
		lbl_jumin.setHorizontalAlignment(SwingConstants.CENTER);
		p_west_3.add(lbl_jumin);
		
		// 주민번호 텍스트 필드 생성
		tf_jumin = new JTextField();
		p_west_3.add(tf_jumin);
		tf_jumin.setColumns(10);
		
		// 왼쪽 패널 생성 5
		p_west_4 = new JPanel();
		p_west.add(p_west_4);
		
		// 전공 레이블 생성
		lbl_major = new JLabel("전공");
		lbl_major.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_major.setPreferredSize(new Dimension(60, 15));
		p_west_4.add(lbl_major);
		
		// 전공 텍스트 필드 생성
		tf_major = new JTextField();
		p_west_4.add(tf_major);
		tf_major.setColumns(10);
		
		// 왼쪽 패널 생성 6
		p_west_5 = new JPanel();
		p_west.add(p_west_5);
		
		// 학생번호 레이블 생성
		lbl_stdNo = new JLabel("학번");
		lbl_stdNo.setPreferredSize(new Dimension(60, 15));
		lbl_stdNo.setHorizontalAlignment(SwingConstants.CENTER);
		p_west_5.add(lbl_stdNo);
		
		// 학생번호 텍스트 필드 생성
		tf_stdNo = new JTextField();
		p_west_5.add(tf_stdNo);
		tf_stdNo.setColumns(10);
		
		// 왼쪽 패널 생성 7
		p_west_6 = new JPanel();
		p_west.add(p_west_6);
		
		// 부서 레이블 생성
		lbl_field = new JLabel("부서");
		lbl_field.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_field.setPreferredSize(new Dimension(60, 15));
		p_west_6.add(lbl_field);
		
		// 부서 텍스트 필드 생성
		tf_field = new JTextField();
		p_west_6.add(tf_field);
		tf_field.setColumns(10);
		
		// 스크롤패널 생성 _ 중앙에 배치
		sp_center = new JScrollPane();
		sp_center.setBorder(new TitledBorder(null, "등록정보", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		contentPane.add(sp_center, BorderLayout.CENTER);
		
		// Human 객체를 보여줄 리스트 생성
		li_humanList = new JList();
		li_humanList.addMouseListener(new MouseHandler());
		sp_center.setViewportView(li_humanList);
		
		// 아래쪽 패널 생성
		p_south = new JPanel();
		p_south.setForeground(Color.LIGHT_GRAY);
		contentPane.add(p_south, BorderLayout.SOUTH);
		
		// 콤보박스 생성
		cb_humanSelect = new JComboBox();
		cb_humanSelect.setOpaque(false);
		cb_humanSelect.setBackground(Color.LIGHT_GRAY);
		cb_humanSelect.addActionListener(this);
		cb_humanSelect.setModel(new DefaultComboBoxModel(new String[] {"대상선택", "교수", "연수생", "운영진"}));
		p_south.add(cb_humanSelect);
		
		// 등록 버튼 생성
		btn_insert = new JButton("등록");
		btn_insert.addActionListener(this);
		p_south.add(btn_insert);
		
		// 수정 버튼 생성
		btn_modify = new JButton("수정");
		btn_modify.addActionListener(this);
		p_south.add(btn_modify);
		
		// 검색 버튼 생성
		btn_search = new JButton("검색");
		btn_search.addActionListener(this);
		p_south.add(btn_search);
		
		// 삭제 버튼 생성
		btn_delete = new JButton("삭제");
		btn_delete.addActionListener(this);
		p_south.add(btn_delete);
		
		// 확인 버튼 생성
		btn_ok = new JButton("확인");
		btn_ok.addActionListener(this);
		p_south.add(btn_ok);
		
		// 취소 버튼 생성
		btn_cancel = new JButton("취소");
		btn_cancel.addActionListener(this);
		p_south.add(btn_cancel);
		
		// 버튼 초기화
		initButton(true);
		
		// 텍스트필드 초기화
		initField("all", false);
		
		// 리스트 보여주기
		showList();
		
		// 화면 보이도록 설정
		setVisible(true);
	}
	
	/**
	 * 리스트를 보여준다
	 */
	public void showList() {
		ArrayList<Human> list = manager.getHumanList();
		li_humanList.setListData(list.toArray());
	}
	
	/**
	 * 텍스트 필드들의 타입에 따라 활성화 여부를 설정한다
	 * @param type 초기화 타입
	 * @param status 활성화 상태
	 */
	public void initField(String type, boolean status){
		// 이름 텍스트 필드의 활성화 설정
		tf_name.setEditable(status);
		// 나이 텍스트 필드의 활성화 설정
		tf_age.setEditable(status);
		// 주민번호 텍스트 필드의 활성화 설정
		tf_jumin.setEditable(status);
		
		// 교수일 경우 전공 텍스트 필드의 활성화 여부 설정
		if (type.equals("all") || type.equals("교수")) {
			tf_major.setEditable(status);
		}
		
		// 연수생일 경우 전공 텍스트 필드의 활성화 여부 설정
		if (type.equals("all") || type.equals("연수생")) {
			tf_stdNo.setEditable(status);
		}
		
		// 운영진일 경우 전공 텍스트 필드의 활성화 여부 설정
		if (type.equals("all") || type.equals("운영진")) {
			tf_field.setEditable(status);
		}
	}
	
	/**
	 * 버튼들의 활성화 여부를 설정한다
	 * @param status 활성화 상태
	 */
	public void initButton(boolean status){
		btn_insert.setEnabled(status);	// 등록
		btn_modify.setEnabled(status);	// 수정
		btn_delete.setEnabled(status);	// 삭제
		btn_search.setEnabled(status);	// 검색
		btn_ok.setEnabled(!status);		// 확인
		btn_cancel.setEnabled(!status);	// 취소
	}
	
	/**
	 * 텍스트 필드들의 텍스트를 빈 문자열로 갱신한다
	 */
	public void clearTextField(){
		tf_name.setText("");	// 이름
		tf_age.setText("");		// 나이
		tf_jumin.setText("");	// 주민번호
		tf_major.setText("");	// 전공
		tf_stdNo.setText("");	// 학생번호
		tf_field.setText("");	// 부서
	}

	/**
	 * 이벤트를 처리하는 메소드
	 * @param e 액션이벤트
	 */
	public void actionPerformed(ActionEvent e) {
		// 이벤트로부터 이벤트를 발생시킨 소스를 구한다
		Object source = e.getSource();
		
		if(source == btn_insert){
			// 등록 버튼의 경우 콤보박스로부터 선택된 아이템을 가져온다
			selectedHuman = (String)cb_humanSelect.getSelectedItem();
			
			if(selectedHuman.equals("대상선택")){
				// 만약 콤보박스에 선택되어진 아이템이 대상선택이라면 에러 메시지를 띄운다
				JOptionPane.showMessageDialog(this, "등록할 대상을 먼저 선택하세요!");
			} else {
				// 제대로 선택되어 있다면 텍스트 필드들을 빈 문자열로 교체하고
				clearTextField();
				// 버튼들의 활성화 상태를 false로 하며
				initButton(false);
				// 선택된 아이템에게 필요한 텍스트필드만 true로 설정한다
				initField(selectedHuman, true);
				preAction = "등록";
			}
		} else if(source == btn_modify) {
			// 수정 버튼을 선택한 경우 현재 필드에 있는 Human 객체 정보가 null인지 체크한다
			if(selectedValue == null){
				// null이라면 에러 메시지를 띄운다
				JOptionPane.showMessageDialog(this, "수정할 데이터를 먼저 선택해주세요!");
			} else {
				// null이 아니라면 선택된 객체 정보가 어느 타입인지에 따라 selectedHuman의 값을 설정한다
				if(selectedValue instanceof Professor) selectedHuman ="교수";
				if(selectedValue instanceof Trainee) selectedHuman ="연수생";
				if(selectedValue instanceof Staff) selectedHuman ="운영진";
				
				// 선택된 아이템에게 필요한 텍스트필드만 true로 설정한다 
				initField(selectedHuman, true);
				// 텍스트 필드 중 키에 해당하는 주민번호는 수정하면 안 되므로 비활성화
				tf_jumin.setEditable(false);
				// 다른 버튼들은 비활성화한다
				initButton(false);
				// Human 객체 정보들을 표현하는 List는 비활성화한다
				li_humanList.setEnabled(false);
				// 확인 버튼을 누를 때 구분 지을 수 있도록 '수정'을 했다는 사실을 기록한다
				preAction = "수정";
			}
		} else if(source == btn_search) {
			// 검색 버튼을 선택한 경우 텍스트필드들을 초기화한다
			clearTextField();
			// 검색을 위해 주민번호란만 활성화한다
			tf_jumin.setEditable(true);
			// 다른 버튼들은 비활성화한다
			initButton(false);
			// 확인 버튼을 누를 때  구분 지을 수 있도록 '검색'을 했다는 사실을 기록한다
			preAction = "검색";
		} else if(source == btn_delete) {
			// 삭제 버튼을 눌렀을 경우 선택된 Human 객체 정보가 있는지 확인한다
			if(selectedValue == null){
				// null이라면 에러 메시지를 띄운다
				JOptionPane.showMessageDialog(this, "삭제할 데이터를 먼저 선택해주세요!");
			} else {
				// null이 아니라면 주민번호를 가지고 온다
				String jumin = tf_jumin.getText();
				// 매니저를 통해 삭제 기능을 실행한다. 이는 서버를 통해 삭제를 시행하고 결과 여부를 반환한다.
				boolean result = manager.deleteHuman(jumin);
				// 리스트를 다시 갱신한다
				showList();
				// 텍스트 필드를 초기화한다
				clearTextField();
			}
		} else if(source == btn_ok) {
			// 확인 버튼을 눌렀을 경우
			Human h = null;
			// 이름 텍스트필드로부터 이름을 가져온다
			String name = tf_name.getText();
			// 나이 텍스트필드에 값이 적혀져 있다면 int형으로 변환한다
			int age = tf_age.getText().trim().length() > 0 ? Integer.parseInt(tf_age.getText()) : 0;
			// 주민번호 텍스트필드로부터 주민번호를 가져온다
			String jumin = tf_jumin.getText();
			
			// 확인 버튼을 누르기 이전 동작이 무엇인지 체크한다
			switch(preAction){
				case "등록": 
					// 등록이었다면 콤보박스에서 선택된 항목에 따라 Human 객체를 생성한다
					if(selectedHuman.equals("교수")) {		
						String major = tf_major.getText();
						h = new Professor(name, age, jumin, major);
					} else if(selectedHuman.equals("연수생")) {
						String stdNo = tf_stdNo.getText();
						h = new Trainee(name, age, jumin, stdNo);
					} else if(selectedHuman.equals("운영진")) {
						String field = tf_field.getText();
						h = new Staff(name, age, jumin, field);
					}
					// 매니저를 통해 Human 객체 등록을 시행한다
					boolean result = manager.insertHuman(h);
					if(!result){
						// 등록 결과가 없다면 이미 등록된 주민번호
						JOptionPane.showMessageDialog(this, "이미 등록된 주민번호가 존재합니다.");
						clearTextField();
					}
					break;
				case "수정":		
					/* 
					 * 수정이었다면 현재 선택되어 있는 Value가 어떤 타입인지 보고 
					 * 각자에 맞는 텍스트필드로부터 값을 가져와 수정된 객체를 생성한다
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
					
					// 매니저를 통해 새로 생성한 Human 객체를 갱신한다 
					manager.updateHuman(newHuman);
					break;
				case "검색":
					// 검색이었다면 텍스트필드에 있는 주민번호를 가지고 매니저를 통해 검색을 시도한다
					Human foundHuman = manager.findHuman(jumin);
					
					if(foundHuman == null){
						// 검색된 결과값이 없다면 에러메시지를 띄운다
						JOptionPane.showMessageDialog(this, "검색 결과가 없습니다.");
						clearTextField();
					} else {
						// 아니라면 결과값을 텍스트필드에 세팅한다
						setTextFieldValue(foundHuman);
					}
					break;
			} //switch
			
			// Human 객체 정보를 담고 있는 리스트를 활성화한다
			li_humanList.setEnabled(true);
			// 텍스트 필드들을 초기화한다
			initField("all", false);
			// 버튼들을 활성화 상태로 변경한다
			initButton(true);
			// 전체 리스트를 다시 갱신한다
			showList();
		} else if(source == btn_cancel){
			// 취소였다면 텍스트 필드를 전부 초기화하고
			clearTextField();
			// 텍스트필드들을 전부 비활성화하고
			initField("all", false);
			// 버튼들을 활성화 상태로 변경한다
			initButton(true);
			// Human 객체 정보를 담고 있는 리스트를 활성화한다
			li_humanList.setEnabled(true);
			// 선택되어 있는 Human 객체는 null로 변경한다
			selectedValue = null;
		}
	}
	
	/**
	 * 텍스트 필드에 Human 객체의 정보를 설정한다
	 * @param h 설정할 Human 객체 정보
	 */
	private void setTextFieldValue(Human h){
		// 이름 설정
		tf_name.setText(h.getName());
		
		// 나이 설정
		tf_age.setText(Integer.toString(h.getAge()));
		
		// 주민번호 설정
		tf_jumin.setText(h.getJumin());
		
		// Human 객체의 본래 형태에 따라 다른 텍스트필드에 값을 설정
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
	 * 내부 클래스 
	 */
	private class MouseHandler extends MouseAdapter {
		
		/**
		 * 마우스 클릭 이벤트를 처리한다
		 * @param me 마우스 이벤트
		 */
		public void mouseClicked(MouseEvent me){
			// 현재 Human 객체 정보를 담고 있는 리스트에서 선택된 값을 가져온다
			selectedValue = (Human)li_humanList.getSelectedValue();
			// 텍스트 필드들을 초기화하고
			clearTextField();
			// 선택된 객체를 텍스트필드에 설정한다
			setTextFieldValue(selectedValue);
		}
	}
	
	/**
	 * 클라이언트 실행을 위한 main
	 * @param args 실행시 입력받는 문자열들
	 */
	public static void main(String args[]){
		new SESUI();
	}
	
}
