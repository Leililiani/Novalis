package org.henrya.ronin.publishdkp.frames;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Represents the frame that shows up after the button is pressed
 * @author Henry Anderson
 */
public class ResultFrame extends JFrame {
	private JPanel contentPane;
	private JTextArea area = new JTextArea();
	
	/**
	 * Constructs the frame
	 */
	public ResultFrame() {
		this.setTitle("Total Points");
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		area.setFont(new Font("Verdana", 0, 23));
		JScrollPane scrollPane = new JScrollPane(this.area);
		scrollPane.setBounds(0, 10, 995, 555);
		contentPane.add(scrollPane);

	}
	
	
	public JTextArea getArea()
	{
		return this.area;
	}
}
