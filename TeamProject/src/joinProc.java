import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings({ "serial", "rawtypeds", "unused", "nuchecked", "deprecation" })
public class joinProc extends JFrame implements ActionListener, MouseListener {
	
	JLabel join_lb, id_lb, pass_lb;
	JTextField id_tf, pass_tf;
	JButton join_bt;
	
	public joinProc() {
		super("ȸ������");
		setSize(530, 300);
		setResizable(false);
		this.setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
		
		
		add(join_lb = new JLabel("ȸ������"));
		join_lb.setBounds(235, 20, 120, 25);
		add(id_lb = new JLabel("�й�"));
		id_lb.setBounds(150, 70, 120, 15);
		add(id_tf = new JTextField());
		id_tf.setBounds(220, 70, 120, 25);
		add(pass_lb = new JLabel("��й�ȣ"));
		pass_lb.setBounds(150, 110, 120, 15);
		add(pass_tf = new JTextField());
		pass_tf.setBounds(220, 110, 120, 25);
		add(join_bt = new JButton("ȸ������"));
		join_bt.setBounds(210, 200, 120, 30);
		
		join_bt.addActionListener(this);
		
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
		int result = 0;
		Students_data student_data = new Students_data();
		
		student_data.setStudent_id(id_tf.getText());
		student_data.setPassword(pass_tf.getText());
		result = DAO.insertStudent(student_data);
		
		if(result == 0) {
			JOptionPane.showMessageDialog(this, "���Կ� �����Ͽ����ϴ�.");
		} else {
			JOptionPane.showMessageDialog(this, "���� �Ǿ����ϴ�.");
			DAO.createKNU_id(student_data);
			this.dispose();
		}
		
	}

}
