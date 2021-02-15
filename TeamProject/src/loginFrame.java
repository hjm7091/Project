import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import java.util.Vector;

@SuppressWarnings({ "serial", "rawtypeds", "unused", "nuchecked", "deprecation" })
public class loginFrame extends JFrame implements ActionListener, MouseListener {
	
	JPanel mainPanel;
	JLabel login_lb, id_lb, pass_lb;
	JTextField id_tf;
	JPasswordField pass_tf;
	JButton login_bt, join_bt;
	
	ImageIcon logo, main;
	JLabel logo_img, main_img;
	
	mainFrame mainFrm;
	static public String user_id;
	
	public loginFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setResizable(false);
		this.setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
		
		add(login_lb = new JLabel("로그인"));
		login_lb.setBounds(1060, 115, 120, 25);
		add(id_lb = new JLabel("ID(학번)"));
		id_lb.setBounds(1062, 160, 120, 15);
		add(id_tf = new JTextField());
		id_tf.setBounds(1062, 178, 190, 30);
		add(pass_lb = new JLabel("비밀번호"));
		pass_lb.setBounds(1062, 220, 120, 15);
		add(pass_tf = new JPasswordField());
		pass_tf.setBounds(1062, 236, 190, 30);
		add(login_bt = new JButton("로그인"));
		login_bt.setBounds(1062, 520, 190, 30);
		add(join_bt = new JButton("회원가입"));
		join_bt.setBounds(1062, 560, 190, 30);
		
		
		URL main_url = getClass().getClassLoader().getResource("knu_login.png");
		main = new ImageIcon(main_url);
		add(main_img = new JLabel(main));
		main_img.setBounds(0, 0, 1050, 700);
		URL logo_url = getClass().getClassLoader().getResource("logo2.png");
		logo = new ImageIcon(logo_url);
		add(logo_img = new JLabel(logo));
		logo_img.setBounds(1070, 10, 140, 90);
		
		
		login_bt.addActionListener(this);
		join_bt.addActionListener(this);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == login_bt) {
			int result = 0;
			String id = id_tf.getText();
			Students_data student_data = new Students_data();
			
			
			student_data.setStudent_id(id);
			student_data.setPassword(pass_tf.getText());
			result = DAO.Login(student_data);
			
			if(result == 0) {
				JOptionPane.showMessageDialog(this, "학번(ID)또는 비밀번호를 올바르게 입력하세요.");
			}
			else {
				user_id = id;
				this.dispose();
				this.mainFrm = new mainFrame();
			}
		}
		else if(e.getSource() == join_bt) {
			new joinProc();
		}
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
