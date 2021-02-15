import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;

@SuppressWarnings({ "serial", "rawtypeds", "unused", "nuchecked", "deprecation" })
public class insertPanel extends JPanel implements ActionListener, MouseListener {
	
	JLabel course_name_lb, course_id_lb;
	JTextField course_name_tf, course_id_tf;
	Vector v, v2;
	Vector cols;
	DefaultTableModel model, model2;
	JTable jTable, jTable2;
	JScrollPane pane;
	JButton insert_bt, name_search_bt, id_search_bt;
	
	String id;
	
	public insertPanel() {
		setSize(1280, 720);
		setLayout(null);
		setVisible(true);
		
		add(course_name_lb = new JLabel("교과목명"));
		course_name_lb.setBounds(120, 45, 120, 25);
		add(course_name_tf = new JTextField());
		course_name_tf.setBounds(200, 45, 150, 30);
		add(course_id_lb = new JLabel("교과번호"));
		course_id_lb.setBounds(120, 95, 120, 25);
		add(course_id_tf = new JTextField());
		course_id_tf.setBounds(200, 95, 150, 30);
		add(name_search_bt = new JButton("검색"));
		name_search_bt.setBounds(400,45,120,35);
		add(id_search_bt = new JButton("검색"));
		id_search_bt.setBounds(400,95,120,35);
		
		
		createTable(v);
		createTable2(v2);
		
		
		add(insert_bt = new JButton("추가"));
		insert_bt.setBounds(1100, 560, 150, 30);
		
		
		name_search_bt.addActionListener(this);
		id_search_bt.addActionListener(this);
		insert_bt.addActionListener(this);
	}
	
	public Vector getColumn() {
		Vector col = new Vector();
		col.add("번호");
		col.add("구분");
		col.add("교과번호");
		col.add("교과명");
		col.add("영문교과명");
		col.add("학점및시수");
		col.add("교수명");
		col.add("강의실");
		col.add("강의시간");
		
		return col;
	}
	
	public Vector getColumn2() {
		Vector col = new Vector();
		col.add("구분");
		col.add("교과번호");
		col.add("교과명");
		col.add("영문교과명");
		col.add("학점및시수");
		col.add("교수명");
		col.add("강의실");
		col.add("강의시간");
		
		return col;
	}
	
	public void createTable(Vector v) {
		cols = getColumn();
		
		model = new DefaultTableModel(v, cols) {
			public boolean isCellEditable(int raw, int column) {
				return false;
			}
		};
		
		jTable = new JTable(model);
		jTable.getTableHeader().setReorderingAllowed(false);
		jTable.getTableHeader().setResizingAllowed(false);
		jTable.getColumn("번호").setPreferredWidth(40);
		jTable.getColumn("구분").setPreferredWidth(100);
		jTable.getColumn("교과번호").setPreferredWidth(100);
		jTable.getColumn("교과명").setPreferredWidth(180);
		jTable.getColumn("영문교과명").setPreferredWidth(180);
		jTable.getColumn("학점및시수").setPreferredWidth(100);
		jTable.getColumn("교수명").setPreferredWidth(100);
		jTable.getColumn("강의실").setPreferredWidth(100);
		jTable.getColumn("강의시간").setPreferredWidth(100);
		pane = new JScrollPane(jTable);
		
		jTable.addMouseListener(this);
		
		add(pane);
		pane.setBounds(100, 150, 1000, 300);
		pane.setVisible(true);
		
	}
	
	public void createTable2(Vector v2) {
		cols = getColumn2();
		
		model2 = new DefaultTableModel(v2, cols) {
			public boolean isCellEditable(int raw, int column) {
				return false;
			}
		};
		
		jTable2 = new JTable(model2);
		jTable2.getTableHeader().setReorderingAllowed(false);
		jTable2.getTableHeader().setResizingAllowed(false);
		jTable2.getColumn("구분").setPreferredWidth(100);
		jTable2.getColumn("교과번호").setPreferredWidth(100);
		jTable2.getColumn("교과명").setPreferredWidth(180);
		jTable2.getColumn("영문교과명").setPreferredWidth(180);
		jTable2.getColumn("학점및시수").setPreferredWidth(100);
		jTable2.getColumn("교수명").setPreferredWidth(100);
		jTable2.getColumn("강의실").setPreferredWidth(100);
		jTable2.getColumn("강의시간").setPreferredWidth(100);
		pane = new JScrollPane(jTable2);
		
		
		add(pane);
		pane.setBounds(100, 550, 960, 41);
		pane.setVisible(true);
	}
	
	public void tableRefresh(Vector v) {
		DefaultTableModel model = new DefaultTableModel(v, getColumn()) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		jTable.setModel(model);
		jTable.getTableHeader().setReorderingAllowed(false);
		jTable.getTableHeader().setResizingAllowed(false);
		jTable.getColumn("번호").setPreferredWidth(40);
		jTable.getColumn("구분").setPreferredWidth(100);
		jTable.getColumn("교과번호").setPreferredWidth(100);
		jTable.getColumn("교과명").setPreferredWidth(180);
		jTable.getColumn("영문교과명").setPreferredWidth(180);
		jTable.getColumn("학점및시수").setPreferredWidth(100);
		jTable.getColumn("교수명").setPreferredWidth(100);
		jTable.getColumn("강의실").setPreferredWidth(100);
		jTable.getColumn("강의시간").setPreferredWidth(100);
		
	}
	
	public void tableRefresh2(Vector v) {
		DefaultTableModel model = new DefaultTableModel(v, getColumn2()) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		jTable2.setModel(model);
		jTable2.getTableHeader().setReorderingAllowed(false);
		jTable2.getTableHeader().setResizingAllowed(false);
		jTable2.getColumn("구분").setPreferredWidth(100);
		jTable2.getColumn("교과번호").setPreferredWidth(100);
		jTable2.getColumn("교과명").setPreferredWidth(180);
		jTable2.getColumn("영문교과명").setPreferredWidth(180);
		jTable2.getColumn("학점및시수").setPreferredWidth(100);
		jTable2.getColumn("교수명").setPreferredWidth(100);
		jTable2.getColumn("강의실").setPreferredWidth(100);
		jTable2.getColumn("강의시간").setPreferredWidth(100);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Vector temp = new Vector();
		Vector v2 = new Vector();
		
		int row = jTable.getSelectedRow();
		
		String classification = (String) jTable.getValueAt(row, 1);
		id = (String) jTable.getValueAt(row, 2);
		String course_name = (String) jTable.getValueAt(row, 3);
		String course_name_eng = (String) jTable.getValueAt(row, 4);
		String credit = (String) jTable.getValueAt(row, 5);
		String professor_name = (String) jTable.getValueAt(row, 6);
		String classroom = (String) jTable.getValueAt(row, 7);
		String time = (String) jTable.getValueAt(row, 8);
		
		
		temp.add(classification);
		temp.add(id);
		temp.add(course_name);
		temp.add(course_name_eng);
		temp.add(credit);
		temp.add(professor_name);
		temp.add(classroom);
		temp.add(time);
		
		v2.add(temp);
		tableRefresh2(v2);
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
		
		if(e.getSource() == id_search_bt) {
			String course_id;
			
			course_id = course_id_tf.getText();
			
			
			v = DAO.search_id(course_id);
			tableRefresh(v);	
			
			
			
		}
		
		if(e.getSource() == name_search_bt) {
			String course_name;
			
			course_name = course_name_tf.getText();
			
			
			v = DAO.search_name(course_name);
			tableRefresh(v);	
			
			
			
		}
		
		if(e.getSource() == insert_bt) {
			int result = DAO.insertCourse(id);
			
			if(result == 0) {
				JOptionPane.showMessageDialog(this, "입력에 실패했습니다..");
			}
			else {
				JOptionPane.showMessageDialog(this, "입력되었습니다.");
			}
		}
		
	}

}
