import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

@SuppressWarnings({ "serial", "rawtypeds", "unused", "nuchecked", "deprecation" })
public class mainFrame extends JFrame {
	private JTabbedPane mainPanel;
	private insertPanel insertPan;
	private checkPanel checkPan;
	
	public mainFrame() {
		insertPan = new insertPanel();
		checkPan = new checkPanel();
		
		mainPanel = new JTabbedPane();
		mainPanel.addTab("입력", insertPan);
		mainPanel.addTab("조회", checkPan);
		
		add(mainPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setResizable(false);
		this.setLocationRelativeTo(null);
		setVisible(true);
	}

}
