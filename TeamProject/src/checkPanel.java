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
		
		add(title_lb = new JLabel("������Ȳ"));
		title_lb.setFont(new Font("Serif", Font.BOLD, 32));
		title_lb.setBounds(45,20,200,40);
		
		add(refresh_bt = new JButton("���ΰ�ħ"));
		refresh_bt.setBounds(1000,35,120,30);
		refresh_bt.addActionListener(this);
		
		scrollpane = new JScrollPane();
		scrollpane.getVerticalScrollBar().setUnitIncrement(16);     //��ũ�ѹ� �ӵ�
		add(scrollpane);
		scrollpane.setBounds(10, 100, 1250, 550);
		
		pane = new JPanel();     //��ũ���г� �ȿ� ���� ���̺���� ��� �г�
		pane.setLayout(null);
		pane.setPreferredSize(new Dimension(1240, 1500));
		pane.setVisible(true);
		scrollpane.setViewportView(pane);
		
		
		//���ʱ���
		pane.add(basic_lb = new JLabel("���ʱ���"));
		basic_lb.setBounds(80, 30, 120, 25);
		
		pane.add(basic_result = new JLabel(""));
		basic_result.setBounds(90, 200, 500, 25);
		this.basicTable();
		
		
		//��������
		pane.add(balanced_lb = new JLabel("��������"));
		balanced_lb.setBounds(80, 250, 120, 25);
		
		pane.add(balanced_result = new JLabel(""));
		balanced_result.setBounds(90, 420, 500, 25);
		pane.add(munsong = new JLabel(""));
		munsong.setBounds(90, 420, 500, 25);
		pane.add(Igwa = new JLabel(""));
		Igwa.setBounds(90, 450, 500, 25);
		this.balancedTable();
		
		
		
		//Ưȭ����
		pane.add(special_lb = new JLabel("Ưȭ����"));
		special_lb.setBounds(80, 500, 120, 25);
		
		pane.add(special_result = new JLabel(""));
		special_result.setBounds(90, 670, 500, 25);
		this.specialTalbe();
		
		
		
		//���к�����
		pane.add(college_lb = new JLabel("���к�����"));
		college_lb.setBounds(80, 720, 120, 25);
		
		pane.add(college_result1 = new JLabel(""));
		college_result1.setBounds(90, 890, 500, 25);
		pane.add(college_result2 = new JLabel(""));
		college_result2.setBounds(90, 920, 500, 25);
		this.collegeTable();
		
		
		
		//�����ʼ�
		pane.add(essential_lb = new JLabel("�����ʼ�"));
		essential_lb.setBounds(80, 940, 120, 25);
		
		pane.add(essential_result = new JLabel(""));
		essential_result.setBounds(90, 1110, 500, 25);
		this.essentialTable();
		
		
		
		//��������
		pane.add(choice_lb = new JLabel("��������"));
		choice_lb.setBounds(80, 1160, 120, 25);
		
		pane.add(choice_result = new JLabel(""));
		choice_result.setBounds(90, 1330, 500, 25);
		this.choiceTable();
		
	}
	
	public Vector getColumn() {
		Vector col = new Vector();
		col.add("����");
		col.add("������ȣ");
		col.add("������");
		col.add("����������");
		col.add("�����׽ü�");
		col.add("������");
		col.add("���ǽ�");
		col.add("���ǽð�");
		col.add("��������");
		
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
		basic_table.getColumn("����").setPreferredWidth(100);
		basic_table.getColumn("������ȣ").setPreferredWidth(100);
		basic_table.getColumn("������").setPreferredWidth(180);
		basic_table.getColumn("����������").setPreferredWidth(180);
		basic_table.getColumn("�����׽ü�").setPreferredWidth(100);
		basic_table.getColumn("������").setPreferredWidth(100);
		basic_table.getColumn("���ǽ�").setPreferredWidth(100);
		basic_table.getColumn("���ǽð�").setPreferredWidth(100);
		basic_table.getColumn("��������").setPreferredWidth(60);
		basic_pane = new JScrollPane(basic_table);
		basic_pane.setBounds(90, 60, 1020, 130); basic_pane.setVisible(true);
		pane.add(basic_pane);
		
		int check_basic = DAO.checkBasic();
		if(check_basic == 1) {
			basic_result.setText("�ٸ�� ���ʱ��� ������ �̼���̽��ϴ�.��");
		}
		else {
			basic_result.setText("���̼����� ���� ���ʱ��� ������ �ֽ��ϴ�.��");
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
		balanced_table.getColumn("����").setPreferredWidth(100);
		balanced_table.getColumn("������ȣ").setPreferredWidth(100);
		balanced_table.getColumn("������").setPreferredWidth(180);
		balanced_table.getColumn("����������").setPreferredWidth(180);
		balanced_table.getColumn("�����׽ü�").setPreferredWidth(100);
		balanced_table.getColumn("������").setPreferredWidth(100);
		balanced_table.getColumn("���ǽ�").setPreferredWidth(100);
		balanced_table.getColumn("���ǽð�").setPreferredWidth(100);
		balanced_table.getColumn("��������").setPreferredWidth(60);
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
				munsong.setText("�ٿ����ö�� �Ǵ� ��ȸ�͹�ȭ �о��� ������ �����ž� �մϴ�.��");
			}else if(b2 ==1) {
				munsong.setText("�پ��͹��� �Ǵ� ��ȸ�͹�ȭ �о��� ������ �����ž� �մϴ�.��");
			}else if(b3 == 1) {
				munsong.setText("�پ��͹��� �Ǵ� �����ö�� �о��� ������ �����ž� �մϴ�.��");
			}
		}
		else if(2 <= b1+b2+b3) {
			result += 1;
		}
		
		if(2 > b4+b5+b6) {
			if(b4 == 1) {
				Igwa.setText("�ټ�������� �Ǵ� �������ǰ� �о��� ������ �����ž� �մϴ�.��");
			}else if(b5 ==1) {
				Igwa.setText("�ٰ��а���� �Ǵ� �������ǰ� �о��� ������ �����ž� �մϴ�.��");
			}else if(b6 == 1) {
				Igwa.setText("�ٰ��а���� �Ǵ� ��������� �о��� ������ �����ž� �մϴ�.��");
			}
		}
		else if(2 <= b4+b5+b6) {
			result += 1;
		}
		
		if(result == 2) {
			munsong.setText("");
			Igwa.setText("");
			balanced_result.setText("�ٱ������� ������ ��� �̼��ϼ̽��ϴ�.��");
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
		special_table.getColumn("����").setPreferredWidth(100);
		special_table.getColumn("������ȣ").setPreferredWidth(100);
		special_table.getColumn("������").setPreferredWidth(180);
		special_table.getColumn("����������").setPreferredWidth(180);
		special_table.getColumn("�����׽ü�").setPreferredWidth(100);
		special_table.getColumn("������").setPreferredWidth(100);
		special_table.getColumn("���ǽ�").setPreferredWidth(100);
		special_table.getColumn("���ǽð�").setPreferredWidth(100);
		special_table.getColumn("��������").setPreferredWidth(60);
		special_pane = new JScrollPane(special_table);
		special_pane.setBounds(90, 530, 1020, 130); special_pane.setVisible(true);
		pane.add(special_pane);
		
		int check_special = DAO.checkSpecial();
		if(check_special == 1) {
			special_result.setText("��Ưȭ���� �̼��� �Ϸ� �Ͽ����ϴ�..��");
		}
		else {
			special_result.setText("��Ưȭ������ 1���� �̻� �̼��ϼž� �մϴ�.��");
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
		college_table.getColumn("����").setPreferredWidth(100);
		college_table.getColumn("������ȣ").setPreferredWidth(100);
		college_table.getColumn("������").setPreferredWidth(180);
		college_table.getColumn("����������").setPreferredWidth(180);
		college_table.getColumn("�����׽ü�").setPreferredWidth(100);
		college_table.getColumn("������").setPreferredWidth(100);
		college_table.getColumn("���ǽ�").setPreferredWidth(100);
		college_table.getColumn("���ǽð�").setPreferredWidth(100);
		college_table.getColumn("��������").setPreferredWidth(60);
		college_pane = new JScrollPane(college_table);
		college_pane.setBounds(90, 750, 1020, 130); college_pane.setVisible(true);
		pane.add(college_pane);
		
		int check_college1 = DAO.checkCollege1();
		if(check_college1 == 4) {
			college_result1.setText("�ٸ�� �ʼ� ���к����� ������ �̼��ϼ̽��ϴ�.��");
		}
		else {
			college_result1.setText("���̼����� ����  �ʼ� ���к����� ������ �ֽ��ϴ�.��");
		}
		int check_college2 = DAO.checkCollege2();
		if(check_college2 == 2) {
			college_result2.setText("�ٸ�� ���� ���к����� ������ �̼��ϼ̽��ϴ�.��");
		}
		else {
			college_result2.setText("���̼����� ����  ���� ���к����� ������ �ֽ��ϴ�.��");
		}
		if(check_college1 == 4 && check_college2 == 2) {
			college_result1.setText("�ٸ�� ���к����� ������ �̼��ϼ̽��ϴ�.��");
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
		essential_table.getColumn("����").setPreferredWidth(100);
		essential_table.getColumn("������ȣ").setPreferredWidth(100);
		essential_table.getColumn("������").setPreferredWidth(180);
		essential_table.getColumn("����������").setPreferredWidth(180);
		essential_table.getColumn("�����׽ü�").setPreferredWidth(100);
		essential_table.getColumn("������").setPreferredWidth(100);
		essential_table.getColumn("���ǽ�").setPreferredWidth(100);
		essential_table.getColumn("���ǽð�").setPreferredWidth(100);
		essential_table.getColumn("��������").setPreferredWidth(60);
		essential_pane = new JScrollPane(essential_table);
		essential_pane.setBounds(90, 970, 1020, 130); essential_pane.setVisible(true);
		pane.add(essential_pane);
		
		int check_essential = DAO.checkEssential();
		if(check_essential == 4) {
			essential_result.setText("�ٸ�� �����ʼ� ������ �̼��ϼ̽��ϴ�.��");
		}
		else {
			essential_result.setText("���̼����� ����  ���� �����ʼ� ������ �ֽ��ϴ١�");
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
		choice_table.getColumn("����").setPreferredWidth(100);
		choice_table.getColumn("������ȣ").setPreferredWidth(100);
		choice_table.getColumn("������").setPreferredWidth(180);
		choice_table.getColumn("����������").setPreferredWidth(180);
		choice_table.getColumn("�����׽ü�").setPreferredWidth(100);
		choice_table.getColumn("������").setPreferredWidth(100);
		choice_table.getColumn("���ǽ�").setPreferredWidth(100);
		choice_table.getColumn("���ǽð�").setPreferredWidth(100);
		choice_table.getColumn("��������").setPreferredWidth(60);
		choice_pane = new JScrollPane(choice_table);
		choice_pane.setBounds(90, 1190, 1020, 130); choice_pane.setVisible(true);
		pane.add(choice_pane);
		
		int check_choice = DAO.checkChoice();
		if(check_choice >= 30) {
			choice_result.setText("���������� ������ ��� �̼��ϼ̽��ϴ�.��");
		}
		else {
			choice_result.setText("���������ð��� " + (30-check_choice) + " ������ �����մϴ�.��");
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
		basic_table.getColumn("����").setPreferredWidth(100);
		basic_table.getColumn("������ȣ").setPreferredWidth(100);
		basic_table.getColumn("������").setPreferredWidth(180);
		basic_table.getColumn("����������").setPreferredWidth(180);
		basic_table.getColumn("�����׽ü�").setPreferredWidth(100);
		basic_table.getColumn("������").setPreferredWidth(100);
		basic_table.getColumn("���ǽ�").setPreferredWidth(100);
		basic_table.getColumn("���ǽð�").setPreferredWidth(100);
		basic_table.getColumn("��������").setPreferredWidth(60);
		
		
		int check_basic = DAO.checkBasic();
		if(check_basic == 1) {
			basic_result.setText("�ٸ�� ���ʱ��� ������ �̼��ϼ̽��ϴ�.��");
		}
		else {
			basic_result.setText("���̼����� ���� ���ʱ��� ������ �ֽ��ϴ�.��");
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
		balanced_table.getColumn("����").setPreferredWidth(100);
		balanced_table.getColumn("������ȣ").setPreferredWidth(100);
		balanced_table.getColumn("������").setPreferredWidth(180);
		balanced_table.getColumn("����������").setPreferredWidth(180);
		balanced_table.getColumn("�����׽ü�").setPreferredWidth(100);
		balanced_table.getColumn("������").setPreferredWidth(100);
		balanced_table.getColumn("���ǽ�").setPreferredWidth(100);
		balanced_table.getColumn("���ǽð�").setPreferredWidth(100);
		balanced_table.getColumn("��������").setPreferredWidth(60);
		
		
		int result = 0;
		int b1 = DAO.checkBalanced1();
		int b2 = DAO.checkBalanced2();
		int b3 = DAO.checkBalanced3();
		int b4 = DAO.checkBalanced4();
		int b5 = DAO.checkBalanced5();
		int b6 = DAO.checkBalanced6();
		
		if(2 > b1+b2+b3) {
			if(b1 == 1) {
				munsong.setText("�ٿ����ö�� �Ǵ� ��ȸ�͹�ȭ �о��� ������ �����ž� �մϴ�.��");
			}else if(b2 ==1) {
				munsong.setText("�پ��͹��� �Ǵ� ��ȸ�͹�ȭ �о��� ������ �����ž� �մϴ�.��");
			}else if(b3 == 1) {
				munsong.setText("�پ��͹��� �Ǵ� �����ö�� �о��� ������ �����ž� �մϴ�.��");
			}
		}
		else if(2 <= b1+b2+b3) {
			result += 1;
		}
		
		if(2 > b4+b6+b6) {
			if(b1 == 4) {
				Igwa.setText("�ټ�������� �Ǵ� �������ǰ� �о��� ������ �����ž� �մϴ�.��");
			}else if(b2 ==5) {
				Igwa.setText("�ٰ��а���� �Ǵ� �������ǰ� �о��� ������ �����ž� �մϴ�.��");
			}else if(b3 == 6) {
				Igwa.setText("�ٰ��а���� �Ǵ� ��������� �о��� ������ �����ž� �մϴ�.��");
			}
		}
		else if(2 <= b4+b5+b6) {
			result += 1;
		}
		
		if(result == 2) {
			munsong.setText("");
			Igwa.setText("");
			balanced_result.setText("�ٱ������� ������ ��� �̼��ϼ̽��ϴ�.��");
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
		special_table.getColumn("����").setPreferredWidth(100);
		special_table.getColumn("������ȣ").setPreferredWidth(100);
		special_table.getColumn("������").setPreferredWidth(180);
		special_table.getColumn("����������").setPreferredWidth(180);
		special_table.getColumn("�����׽ü�").setPreferredWidth(100);
		special_table.getColumn("������").setPreferredWidth(100);
		special_table.getColumn("���ǽ�").setPreferredWidth(100);
		special_table.getColumn("���ǽð�").setPreferredWidth(100);
		special_table.getColumn("��������").setPreferredWidth(60);
		
		
		int check_special = DAO.checkSpecial();
		if(check_special == 1) {
			special_result.setText("��Ưȭ���� �̼��� �Ϸ� �Ͽ����ϴ�..��");
		}
		else {
			special_result.setText("��Ưȭ������ 1���� �̻� �̼��ϼž� �մϴ�.��");
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
		college_table.getColumn("����").setPreferredWidth(100);
		college_table.getColumn("������ȣ").setPreferredWidth(100);
		college_table.getColumn("������").setPreferredWidth(180);
		college_table.getColumn("����������").setPreferredWidth(180);
		college_table.getColumn("�����׽ü�").setPreferredWidth(100);
		college_table.getColumn("������").setPreferredWidth(100);
		college_table.getColumn("���ǽ�").setPreferredWidth(100);
		college_table.getColumn("���ǽð�").setPreferredWidth(100);
		college_table.getColumn("��������").setPreferredWidth(60);
		
		int check_college1 = DAO.checkCollege1();
		if(check_college1 == 4) {
			college_result1.setText("�ٸ�� �ʼ� ���к����� ������ �̼��ϼ̽��ϴ�.��");
		}
		else {
			college_result1.setText("���̼����� ����  �ʼ� ���к����� ������ �ֽ��ϴ�.��");
		}
		int check_college2 = DAO.checkCollege2();
		if(check_college2 == 2) {
			college_result2.setText("�ٸ�� ���� ���к����� ������ �̼��ϼ̽��ϴ�.��");
		}
		else {
			college_result2.setText("���̼����� ����  ���� ���к����� ������ �ֽ��ϴ�.��");
		}
		if(check_college1 == 4 && check_college2 == 2) {
			college_result1.setText("�ٸ�� ���к����� ������ �̼��ϼ̽��ϴ�.��");
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
		essential_table.getColumn("����").setPreferredWidth(100);
		essential_table.getColumn("������ȣ").setPreferredWidth(100);
		essential_table.getColumn("������").setPreferredWidth(180);
		essential_table.getColumn("����������").setPreferredWidth(180);
		essential_table.getColumn("�����׽ü�").setPreferredWidth(100);
		essential_table.getColumn("������").setPreferredWidth(100);
		essential_table.getColumn("���ǽ�").setPreferredWidth(100);
		essential_table.getColumn("���ǽð�").setPreferredWidth(100);
		essential_table.getColumn("��������").setPreferredWidth(60);
		
		int check_essential = DAO.checkEssential();
		if(check_essential == 4) {
			essential_result.setText("�ٸ�� �����ʼ� ������ �̼��ϼ̽��ϴ�.��");
		}
		else {
			essential_result.setText("���̼����� ����  ���� �����ʼ� ������ �ֽ��ϴ١�");
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
		choice_table.getColumn("����").setPreferredWidth(100);
		choice_table.getColumn("������ȣ").setPreferredWidth(100);
		choice_table.getColumn("������").setPreferredWidth(180);
		choice_table.getColumn("����������").setPreferredWidth(180);
		choice_table.getColumn("�����׽ü�").setPreferredWidth(100);
		choice_table.getColumn("������").setPreferredWidth(100);
		choice_table.getColumn("���ǽ�").setPreferredWidth(100);
		choice_table.getColumn("���ǽð�").setPreferredWidth(100);
		choice_table.getColumn("��������").setPreferredWidth(60);
		
		
		int check_choice = DAO.checkChoice();
		if(check_choice >= 30) {
			choice_result.setText("���������� ������ ��� �̼��ϼ̽��ϴ�.��");
		}
		else {
			choice_result.setText("���������ð��� " + (30-check_choice) + " ������ �����մϴ�.��");
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
