package org.henrya.ronin.publishdkp.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

public class MainFrame extends JFrame {
	private JPanel contentPane;
	private JTextArea area = new JTextArea();
	
	public MainFrame() {
		/* Creates the frame */
		this.setTitle("Publish DKP");
		this.setResizable(false);
		this.area.setEditable(true);
		area.setFont(new Font("Verdana", 0, 30));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/* Creates the scroll pane that the text area is stored in */
		JScrollPane scrollPane = new JScrollPane(this.area);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 350, 482);
		contentPane.add(scrollPane);
		
		/* Creates the count button */
		JButton checkButton = new JButton("Publish");
		checkButton.setFont(new Font("Verdana", 0, 30));
		checkButton.setBounds(0, 482, 350, 96);
		checkButton.addActionListener(new ButtonListener(this));
		contentPane.add(checkButton);
	}
	
	/**
	 * Returns the text area
	 * @return A JTextArea instance
	 */
	public JTextArea getArea() {
		return this.area;
	}
}
