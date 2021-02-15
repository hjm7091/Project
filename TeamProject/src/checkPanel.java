import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.Vector;

@SuppressWarnings({ "serial", "rawtypeds", "unused", "nuchecked", "deprecation" })
public class checkPanel extends JPanel implements ActionListener, MouseListener {
	
	JLabel title_lb, basic_lb, balanced_lb, special_lb, college_lb, essential_lb, choice_lb;
	JPanel pane;
	JScrollPane scrollpane, basic_pane, balanced_pane, special_pane, college_pane, essential_pane, choice_pane;
	Vector basic_v, balanced_v, special_v, college_v, essential_v, choice_v;
	Vector basic_cols, balanced_cols, special_cols, college_cols, essential_cols, choice_cols;
	DefaultTableModel basic_model, balanced_model, special_model, college_model, essential_model, choice_model;
	JTable basic_table, balanced_table, special_table, college_table, essential_table, choice_table;
	JButton refresh_bt;
	
	JLabel basic_result, balanced_result, munsong, Igwa, special_result, college_result1, college_result2, essential_result, choice_result;
	
	public checkPanel() {
		setSize(1280, 720);
		setLayout(null);
		setVisible(true);
		
		add(title_lb = new JLabel("수강현황"));
		title_lb.setFont(new Font("Serif", Font.BOLD, 32));
		title_lb.setBounds(45,20,200,40);
		
		add(refresh_bt = new JButton("새로고침"));
		refresh_bt.setBounds(1000,35,120,30);
		refresh_bt.addActionListener(this);
		
		scrollpane = new JScrollPane();
		scrollpane.getVerticalScrollBar().setUnitIncrement(16);     //스크롤바 속도
		add(scrollpane);
		scrollpane.setBounds(10, 100, 1250, 550);
		
		pane = new JPanel();     //스크롤패널 안에 들어가며 테이블들을 담는 패널
		pane.setLayout(null);
		pane.setPreferredSize(new Dimension(1240, 1500));
		pane.setVisible(true);
		scrollpane.setViewportView(pane);
		
		
		//기초교양
		pane.add(basic_lb = new JLabel("기초교양"));
		basic_lb.setBounds(80, 30, 120, 25);
		
		pane.add(basic_result = new JLabel(""));
		basic_result.setBounds(90, 200, 500, 25);
		this.basicTable();
		
		
		//균형교양
		pane.add(balanced_lb = new JLabel("균형교양"));
		balanced_lb.setBounds(80, 250, 120, 25);
		
		pane.add(balanced_result = new JLabel(""));
		balanced_result.setBounds(90, 420, 500, 25);
		pane.add(munsong = new JLabel(""));
		munsong.setBounds(90, 420, 500, 25);
		pane.add(Igwa = new JLabel(""));
		Igwa.setBounds(90, 450, 500, 25);
		this.balancedTable();
		
		
		
		//특화교양
		pane.add(special_lb = new JLabel("특화교양"));
		special_lb.setBounds(80, 500, 120, 25);
		
		pane.add(special_result = new JLabel(""));
		special_result.setBounds(90, 670, 500, 25);
		this.specialTalbe();
		
		
		
		//대학별교양
		pane.add(college_lb = new JLabel("대학별교양"));
		college_lb.setBounds(80, 720, 120, 25);
		
		pane.add(college_result1 = new JLabel(""));
		college_result1.setBounds(90, 890, 500, 25);
		pane.add(college_result2 = new JLabel(""));
		college_result2.setBounds(90, 920, 500, 25);
		this.collegeTable();
		
		
		
		//전공필수
		pane.add(essential_lb = new JLabel("전공필수"));
		essential_lb.setBounds(80, 940, 120, 25);
		
		pane.add(essential_result = new JLabel(""));
		essential_result.setBounds(90, 1110, 500, 25);
		this.essentialTable();
		
		
		
		//전공선택
		pane.add(choice_lb = new JLabel("전공선택"));
		choice_lb.setBounds(80, 1160, 120, 25);
		
		pane.add(choice_result = new JLabel(""));
		choice_result.setBounds(90, 1330, 500, 25);
		this.choiceTable();
		
	}
	
	public Vector getColumn() {
		Vector col = new Vector();
		col.add("구분");
		col.add("교과번호");
		col.add("교과명");
		col.add("영문교과명");
		col.add("학점및시수");
		col.add("교수명");
		col.add("강의실");
		col.add("강의시간");
		col.add("수강여부");
		
		return col;
	}
	
	public void basicTable() {
		basic_v = DAO.getBasicCourseList();
		basic_cols = getColumn();
		
		basic_model = new DefaultTableModel(basic_v, basic_cols) {
			public boolean isCellEditable(int raw, int column) {
				return false;
			}
		};
		basic_table = new JTable(basic_model);
		basic_table.getTableHeader().setReorderingAllowed(false);
		basic_table.getTableHeader().setResizingAllowed(false);
		basic_table.getColumn("구분").setPreferredWidth(100);
		basic_table.getColumn("교과번호").setPreferredWidth(100);
		basic_table.getColumn("교과명").setPreferredWidth(180);
		basic_table.getColumn("영문교과명").setPreferredWidth(180);
		basic_table.getColumn("학점및시수").setPreferredWidth(100);
		basic_table.getColumn("교수명").setPreferredWidth(100);
		basic_table.getColumn("강의실").setPreferredWidth(100);
		basic_table.getColumn("강의시간").setPreferredWidth(100);
		basic_table.getColumn("수강여부").setPreferredWidth(60);
		basic_pane = new JScrollPane(basic_table);
		basic_pane.setBounds(90, 60, 1020, 130); basic_pane.setVisible(true);
		pane.add(basic_pane);
		
		int check_basic = DAO.checkBasic();
		if(check_basic == 1) {
			basic_result.setText("☆모든 기초교양 과목을 이수사셨습니다.☆");
		}
		else {
			basic_result.setText("☆이수하지 않은 기초교양 과목이 있습니다.☆");
		}
	}
	
	public void balancedTable() {
		balanced_v = DAO.getBalancedCourseList();
		balanced_cols = getColumn();
		
		balanced_model = new DefaultTableModel(balanced_v, balanced_cols) {
			public boolean isCellEditable(int raw, int column) {
				return false;
			}
		};
		balanced_table = new JTable(balanced_model);
		balanced_table.getTableHeader().setReorderingAllowed(false);
		balanced_table.getTableHeader().setResizingAllowed(false);
		balanced_table.getColumn("구분").setPreferredWidth(100);
		balanced_table.getColumn("교과번호").setPreferredWidth(100);
		balanced_table.getColumn("교과명").setPreferredWidth(180);
		balanced_table.getColumn("영문교과명").setPreferredWidth(180);
		balanced_table.getColumn("학점및시수").setPreferredWidth(100);
		balanced_table.getColumn("교수명").setPreferredWidth(100);
		balanced_table.getColumn("강의실").setPreferredWidth(100);
		balanced_table.getColumn("강의시간").setPreferredWidth(100);
		balanced_table.getColumn("수강여부").setPreferredWidth(60);
		balanced_pane = new JScrollPane(balanced_table);
		balanced_pane.setBounds(90, 280, 1020, 130); balanced_pane.setVisible(true);
		pane.add(balanced_pane);
		
		int result = 0;
		int b1 = DAO.checkBalanced1();
		int b2 = DAO.checkBalanced2();
		int b3 = DAO.checkBalanced3();
		int b4 = DAO.checkBalanced4();
		int b5 = DAO.checkBalanced5();
		int b6 = DAO.checkBalanced6();
		
		if(2 > b1+b2+b3) {
			if(b1 == 1) {
				munsong.setText("☆역사와철학 또는 사회와문화 분야의 수업을 들으셔야 합니다.☆");
			}else if(b2 ==1) {
				munsong.setText("☆언어와문학 또는 사회와문화 분야의 수업을 들으셔야 합니다.☆");
			}else if(b3 == 1) {
				munsong.setText("☆언어와문학 또는 역사와철학 분야의 수업을 들으셔야 합니다.☆");
			}
		}
		else if(2 <= b1+b2+b3) {
			result += 1;
		}
		
		if(2 > b4+b5+b6) {
			if(b4 == 1) {
				Igwa.setText("☆수리적사고 또는 예술과건강 분야의 수업을 들으셔야 합니다.☆");
			}else if(b5 ==1) {
				Igwa.setText("☆과학과기술 또는 예술과건강 분야의 수업을 들으셔야 합니다.☆");
			}else if(b6 == 1) {
				Igwa.setText("☆과학과기술 또는 수리적사고 분야의 수업을 들으셔야 합니다.☆");
			}
		}
		else if(2 <= b4+b5+b6) {
			result += 1;
		}
		
		if(result == 2) {
			munsong.setText("");
			Igwa.setText("");
			balanced_result.setText("☆균형교양 과목을 모두 이수하셨습니다.☆");
		}
		
	}
	
	public void specialTalbe() {
		special_v = DAO.getSpecialCourseList();
		special_cols = getColumn();
		
		special_model = new DefaultTableModel(special_v, special_cols) {
			public boolean isCellEditable(int raw, int column) {
				return false;
			}
		};
		special_table = new JTable(special_model);
		special_table.getTableHeader().setReorderingAllowed(false);
		special_table.getTableHeader().setResizingAllowed(false);
		special_table.getColumn("구분").setPreferredWidth(100);
		special_table.getColumn("교과번호").setPreferredWidth(100);
		special_table.getColumn("교과명").setPreferredWidth(180);
		special_table.getColumn("영문교과명").setPreferredWidth(180);
		special_table.getColumn("학점및시수").setPreferredWidth(100);
		special_table.getColumn("교수명").setPreferredWidth(100);
		special_table.getColumn("강의실").setPreferredWidth(100);
		special_table.getColumn("강의시간").setPreferredWidth(100);
		special_table.getColumn("수강여부").setPreferredWidth(60);
		special_pane = new JScrollPane(special_table);
		special_pane.setBounds(90, 530, 1020, 130); special_pane.setVisible(true);
		pane.add(special_pane);
		
		int check_special = DAO.checkSpecial();
		if(check_special == 1) {
			special_result.setText("☆특화교양 이수를 완료 하였습니다..☆");
		}
		else {
			special_result.setText("☆특화교양을 1학점 이상 이수하셔야 합니다.☆");
		}
	}
	
	public void collegeTable() {
		college_v = DAO.getCollegeCourseList();
		college_cols = getColumn();
		
		college_model = new DefaultTableModel(college_v, college_cols) {
			public boolean isCellEditable(int raw, int column) {
				return false;
			}
		};
		college_table = new JTable(college_model);
		college_table.getTableHeader().setReorderingAllowed(false);
		college_table.getTableHeader().setResizingAllowed(false);
		college_table.getColumn("구분").setPreferredWidth(100);
		college_table.getColumn("교과번호").setPreferredWidth(100);
		college_table.getColumn("교과명").setPreferredWidth(180);
		college_table.getColumn("영문교과명").setPreferredWidth(180);
		college_table.getColumn("학점및시수").setPreferredWidth(100);
		college_table.getColumn("교수명").setPreferredWidth(100);
		college_table.getColumn("강의실").setPreferredWidth(100);
		college_table.getColumn("강의시간").setPreferredWidth(100);
		college_table.getColumn("수강여부").setPreferredWidth(60);
		college_pane = new JScrollPane(college_table);
		college_pane.setBounds(90, 750, 1020, 130); college_pane.setVisible(true);
		pane.add(college_pane);
		
		int check_college1 = DAO.checkCollege1();
		if(check_college1 == 4) {
			college_result1.setText("☆모든 필수 대학별교양 과목을 이수하셨습니다.☆");
		}
		else {
			college_result1.setText("☆이수하지 않은  필수 대학별교양 과목이 있습니다.☆");
		}
		int check_college2 = DAO.checkCollege2();
		if(check_college2 == 2) {
			college_result2.setText("☆모든 선택 대학별교양 과목을 이수하셨습니다.☆");
		}
		else {
			college_result2.setText("☆이수하지 않은  선택 대학별교양 과목이 있습니다.☆");
		}
		if(check_college1 == 4 && check_college2 == 2) {
			college_result1.setText("☆모든 대학별교양 과목을 이수하셨습니다.☆");
			college_result2.setText("");
		}
		
	}
	
	public void essentialTable() {
		essential_v = DAO.getEssentialCourseList();
		essential_cols = getColumn();
		
		essential_model = new DefaultTableModel(essential_v, essential_cols) {
			public boolean isCellEditable(int raw, int column) {
				return false;
			}
		};
		essential_table = new JTable(essential_model);
		essential_table.getTableHeader().setReorderingAllowed(false);
		essential_table.getTableHeader().setResizingAllowed(false);
		essential_table.getColumn("구분").setPreferredWidth(100);
		essential_table.getColumn("교과번호").setPreferredWidth(100);
		essential_table.getColumn("교과명").setPreferredWidth(180);
		essential_table.getColumn("영문교과명").setPreferredWidth(180);
		essential_table.getColumn("학점및시수").setPreferredWidth(100);
		essential_table.getColumn("교수명").setPreferredWidth(100);
		essential_table.getColumn("강의실").setPreferredWidth(100);
		essential_table.getColumn("강의시간").setPreferredWidth(100);
		essential_table.getColumn("수강여부").setPreferredWidth(60);
		essential_pane = new JScrollPane(essential_table);
		essential_pane.setBounds(90, 970, 1020, 130); essential_pane.setVisible(true);
		pane.add(essential_pane);
		
		int check_essential = DAO.checkEssential();
		if(check_essential == 4) {
			essential_result.setText("☆모든 전공필수 과목을 이수하셨습니다.☆");
		}
		else {
			essential_result.setText("☆이수하지 않은  선택 전공필수 과목이 있습니다☆");
		}
		
	}
	
	public void choiceTable() {
		choice_v = DAO.getChoiceCourseList();
		choice_cols = getColumn();
		
		choice_model = new DefaultTableModel(choice_v, choice_cols) {
			public boolean isCellEditable(int raw, int column) {
				return false;
			}
		};
		choice_table = new JTable(choice_model);
		choice_table.getTableHeader().setReorderingAllowed(false);
		choice_table.getTableHeader().setResizingAllowed(false);
		choice_table.getColumn("구분").setPreferredWidth(100);
		choice_table.getColumn("교과번호").setPreferredWidth(100);
		choice_table.getColumn("교과명").setPreferredWidth(180);
		choice_table.getColumn("영문교과명").setPreferredWidth(180);
		choice_table.getColumn("학점및시수").setPreferredWidth(100);
		choice_table.getColumn("교수명").setPreferredWidth(100);
		choice_table.getColumn("강의실").setPreferredWidth(100);
		choice_table.getColumn("강의시간").setPreferredWidth(100);
		choice_table.getColumn("수강여부").setPreferredWidth(60);
		choice_pane = new JScrollPane(choice_table);
		choice_pane.setBounds(90, 1190, 1020, 130); choice_pane.setVisible(true);
		pane.add(choice_pane);
		
		int check_choice = DAO.checkChoice();
		if(check_choice >= 30) {
			choice_result.setText("☆전공선택 과목을 모두 이수하셨습니다.☆");
		}
		else {
			choice_result.setText("☆전공선택과목 " + (30-check_choice) + " 학점이 부족합니다.☆");
		}
		
	}
	
	public void basictableRefresh() {
		
		basic_v = DAO.getBasicCourseList();
		DefaultTableModel model = new DefaultTableModel(basic_v, getColumn()) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		basic_table.setModel(model);
		basic_table.getTableHeader().setReorderingAllowed(false);
		basic_table.getTableHeader().setResizingAllowed(false);
		basic_table.getColumn("구분").setPreferredWidth(100);
		basic_table.getColumn("교과번호").setPreferredWidth(100);
		basic_table.getColumn("교과명").setPreferredWidth(180);
		basic_table.getColumn("영문교과명").setPreferredWidth(180);
		basic_table.getColumn("학점및시수").setPreferredWidth(100);
		basic_table.getColumn("교수명").setPreferredWidth(100);
		basic_table.getColumn("강의실").setPreferredWidth(100);
		basic_table.getColumn("강의시간").setPreferredWidth(100);
		basic_table.getColumn("수강여부").setPreferredWidth(60);
		
		
		int check_basic = DAO.checkBasic();
		if(check_basic == 1) {
			basic_result.setText("☆모든 기초교양 과목을 이수하셨습니다.☆");
		}
		else {
			basic_result.setText("☆이수하지 않은 기초교양 과목이 있습니다.☆");
		}
	}
		
		
	public void balancedtableRefresh() {
		balanced_v = DAO.getBalancedCourseList();
		DefaultTableModel model = new DefaultTableModel(balanced_v, getColumn()) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		balanced_table.setModel(model);
		balanced_table.getTableHeader().setReorderingAllowed(false);
		balanced_table.getTableHeader().setResizingAllowed(false);
		balanced_table.getColumn("구분").setPreferredWidth(100);
		balanced_table.getColumn("교과번호").setPreferredWidth(100);
		balanced_table.getColumn("교과명").setPreferredWidth(180);
		balanced_table.getColumn("영문교과명").setPreferredWidth(180);
		balanced_table.getColumn("학점및시수").setPreferredWidth(100);
		balanced_table.getColumn("교수명").setPreferredWidth(100);
		balanced_table.getColumn("강의실").setPreferredWidth(100);
		balanced_table.getColumn("강의시간").setPreferredWidth(100);
		balanced_table.getColumn("수강여부").setPreferredWidth(60);
		
		
		int result = 0;
		int b1 = DAO.checkBalanced1();
		int b2 = DAO.checkBalanced2();
		int b3 = DAO.checkBalanced3();
		int b4 = DAO.checkBalanced4();
		int b5 = DAO.checkBalanced5();
		int b6 = DAO.checkBalanced6();
		
		if(2 > b1+b2+b3) {
			if(b1 == 1) {
				munsong.setText("☆역사와철학 또는 사회와문화 분야의 수업을 들으셔야 합니다.☆");
			}else if(b2 ==1) {
				munsong.setText("☆언어와문학 또는 사회와문화 분야의 수업을 들으셔야 합니다.☆");
			}else if(b3 == 1) {
				munsong.setText("☆언어와문학 또는 역사와철학 분야의 수업을 들으셔야 합니다.☆");
			}
		}
		else if(2 <= b1+b2+b3) {
			result += 1;
		}
		
		if(2 > b4+b6+b6) {
			if(b1 == 4) {
				Igwa.setText("☆수리적사고 또는 예술과건강 분야의 수업을 들으셔야 합니다.☆");
			}else if(b2 ==5) {
				Igwa.setText("☆과학과기술 또는 예술과건강 분야의 수업을 들으셔야 합니다.☆");
			}else if(b3 == 6) {
				Igwa.setText("☆과학과기술 또는 수리적사고 분야의 수업을 들으셔야 합니다.☆");
			}
		}
		else if(2 <= b4+b5+b6) {
			result += 1;
		}
		
		if(result == 2) {
			munsong.setText("");
			Igwa.setText("");
			balanced_result.setText("☆균형교양 과목을 모두 이수하셨습니다.☆");
		}
	}
		
	
	public void specialtableRefresh() {
		special_v = DAO.getSpecialCourseList();
		DefaultTableModel model = new DefaultTableModel(special_v, getColumn()) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		special_table.setModel(model);
		special_table.getTableHeader().setReorderingAllowed(false);
		special_table.getTableHeader().setResizingAllowed(false);
		special_table.getColumn("구분").setPreferredWidth(100);
		special_table.getColumn("교과번호").setPreferredWidth(100);
		special_table.getColumn("교과명").setPreferredWidth(180);
		special_table.getColumn("영문교과명").setPreferredWidth(180);
		special_table.getColumn("학점및시수").setPreferredWidth(100);
		special_table.getColumn("교수명").setPreferredWidth(100);
		special_table.getColumn("강의실").setPreferredWidth(100);
		special_table.getColumn("강의시간").setPreferredWidth(100);
		special_table.getColumn("수강여부").setPreferredWidth(60);
		
		
		int check_special = DAO.checkSpecial();
		if(check_special == 1) {
			special_result.setText("☆특화교양 이수를 완료 하였습니다..☆");
		}
		else {
			special_result.setText("☆특화교양을 1학점 이상 이수하셔야 합니다.☆");
		}
	}
	
	public void collegetableRefresh() {
		college_v = DAO.getCollegeCourseList();
		DefaultTableModel college_model = new DefaultTableModel(college_v, getColumn()) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		college_table.setModel(college_model);
		college_table.getTableHeader().setReorderingAllowed(false);
		college_table.getTableHeader().setResizingAllowed(false);
		college_table.getColumn("구분").setPreferredWidth(100);
		college_table.getColumn("교과번호").setPreferredWidth(100);
		college_table.getColumn("교과명").setPreferredWidth(180);
		college_table.getColumn("영문교과명").setPreferredWidth(180);
		college_table.getColumn("학점및시수").setPreferredWidth(100);
		college_table.getColumn("교수명").setPreferredWidth(100);
		college_table.getColumn("강의실").setPreferredWidth(100);
		college_table.getColumn("강의시간").setPreferredWidth(100);
		college_table.getColumn("수강여부").setPreferredWidth(60);
		
		int check_college1 = DAO.checkCollege1();
		if(check_college1 == 4) {
			college_result1.setText("☆모든 필수 대학별교양 과목을 이수하셨습니다.☆");
		}
		else {
			college_result1.setText("☆이수하지 않은  필수 대학별교양 과목이 있습니다.☆");
		}
		int check_college2 = DAO.checkCollege2();
		if(check_college2 == 2) {
			college_result2.setText("☆모든 선택 대학별교양 과목을 이수하셨습니다.☆");
		}
		else {
			college_result2.setText("☆이수하지 않은  선택 대학별교양 과목이 있습니다.☆");
		}
		if(check_college1 == 4 && check_college2 == 2) {
			college_result1.setText("☆모든 대학별교양 과목을 이수하셨습니다.☆");
			college_result2.setText("");
		}
	}
	
	
	public void essentialtableRefresh() {
		essential_v = DAO.getEssentialCourseList();
		DefaultTableModel model = new DefaultTableModel(essential_v, getColumn()) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		essential_table.setModel(model);
		essential_table.getTableHeader().setReorderingAllowed(false);
		essential_table.getTableHeader().setResizingAllowed(false);
		essential_table.getColumn("구분").setPreferredWidth(100);
		essential_table.getColumn("교과번호").setPreferredWidth(100);
		essential_table.getColumn("교과명").setPreferredWidth(180);
		essential_table.getColumn("영문교과명").setPreferredWidth(180);
		essential_table.getColumn("학점및시수").setPreferredWidth(100);
		essential_table.getColumn("교수명").setPreferredWidth(100);
		essential_table.getColumn("강의실").setPreferredWidth(100);
		essential_table.getColumn("강의시간").setPreferredWidth(100);
		essential_table.getColumn("수강여부").setPreferredWidth(60);
		
		int check_essential = DAO.checkEssential();
		if(check_essential == 4) {
			essential_result.setText("☆모든 전공필수 과목을 이수하셨습니다.☆");
		}
		else {
			essential_result.setText("☆이수하지 않은  선택 전공필수 과목이 있습니다☆");
		}
	}
	
	
	public void choicetableRefresh() {
		choice_v = DAO.getChoiceCourseList();
		DefaultTableModel model = new DefaultTableModel(choice_v, getColumn()) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		choice_table.setModel(model);
		choice_table.getTableHeader().setReorderingAllowed(false);
		choice_table.getTableHeader().setResizingAllowed(false);
		choice_table.getColumn("구분").setPreferredWidth(100);
		choice_table.getColumn("교과번호").setPreferredWidth(100);
		choice_table.getColumn("교과명").setPreferredWidth(180);
		choice_table.getColumn("영문교과명").setPreferredWidth(180);
		choice_table.getColumn("학점및시수").setPreferredWidth(100);
		choice_table.getColumn("교수명").setPreferredWidth(100);
		choice_table.getColumn("강의실").setPreferredWidth(100);
		choice_table.getColumn("강의시간").setPreferredWidth(100);
		choice_table.getColumn("수강여부").setPreferredWidth(60);
		
		
		int check_choice = DAO.checkChoice();
		if(check_choice >= 30) {
			choice_result.setText("☆전공선택 과목을 모두 이수하셨습니다.☆");
		}
		else {
			choice_result.setText("☆전공선택과목 " + (30-check_choice) + " 학점이 부족합니다.☆");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == refresh_bt) {
			
			basictableRefresh();	
			balancedtableRefresh();	
			specialtableRefresh();	
			collegetableRefresh();	
			essentialtableRefresh();	
			choicetableRefresh();	
			
		}
		
	}

}
