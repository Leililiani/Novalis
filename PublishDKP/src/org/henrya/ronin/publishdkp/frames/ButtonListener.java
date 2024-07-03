package org.henrya.ronin.publishdkp.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

import org.henrya.configj.ConfigFile;
import org.henrya.httpicnic.http.HttpConnectionException;
import org.henrya.ronin.publishdkp.Player;
import org.henrya.ronin.publishdkp.WebDKPSession;

/**
 * A class that listens for when the button is pressed
 * @author Henry Anderson
 * 
 */
public class ButtonListener implements ActionListener {
	private MainFrame mainFrame;
	
	/**
	 * Constructs the instance from the main frame
	 * @param mainFrame
	 */
	public ButtonListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * Called when the button is pressed
	 * It opens a new console type window, logs into WebDKP, and adds the points line by line
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		final ResultFrame frame = new ResultFrame();
		final JTextArea pane = frame.getArea();
		frame.setVisible(true);
		String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

		new Thread() {
			public void run() {
				pane.append("Loading configuration file...");
				ConfigFile config = new ConfigFile("config", "txt");
				if(config.getString("url") == null || config.getString("username") == null || config.getString("password") == null) {
					config.set("url", "https://webdkp.com/dkp/example");
					config.set("username", "username123");
					config.set("password", "password123");
					config.save();
					pane.append("Configuration file is invalid\n");
				}
				else {
					String url = config.getString("url");
					String username = config.getString("username");
					String password = config.getString("password");	
					pane.append("Logging into " + url + "\n");
					WebDKPSession session = new WebDKPSession(url, username, password);
					
					try {
						if(!session.login()) {
							pane.append("Invalid username or password!\n");
						}
						else {
							pane.append("Loading players... This can take a few minutes.\n");
							session.loadPlayers();
							for(String line : mainFrame.getArea().getText().split("\n")) {
								try {
									line = line.toLowerCase();
									String[] separated = line.split(", ");
									String name = separated[0];
									int points = Integer.parseInt(separated[1]);
									if(session.playerExists(name)) {
										Player player = session.getPlayer(name);
										pane.append("Awarding \"" + name + "\" with " + points + " points. New total is: " + (player.getPoints() + points+"\n"));
										session.addPoints(player, points, date + " Count");
									}
									else {
										pane.append("Creating \"" + name + "\" and awarding " + points + " points...\n");
										session.createPlayer(name, points);
									}
								} catch(Exception e) {
									e.printStackTrace();
									pane.append(e.getClass().getName() + ": " + e.getMessage() + " on line: " + line+"\n");
								}
							}
							pane.append("Finished!\n");
						} 
					}catch(HttpConnectionException e) {
						e.printStackTrace();
						pane.append(e.getClass().getName() + ": " + e.getMessage()+"\n");
					}
				}
			}
		}.start();
	}
}
