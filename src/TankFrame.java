import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

public class TankFrame extends JFrame {
	public final static int width=1280;
	public final static int length=1280;
	TankPanel tp=new TankPanel(this);
	Image offScreen=null;
	public void launchFrame() {
		add(tp);
		new Thread(tp).start();
		this.setSize(width,length);
		this.setLocation(500, 100);
		this.setVisible(true);
		this.setTitle("Tank War");
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				tp.mtank.keyPressed(e);
			}
			public void keyReleased(KeyEvent e) {
				tp.mtank.keyReleased(e);
			}
		});
	}
	public static void main(String[] args) {
		new TankFrame().launchFrame();
	}
}
