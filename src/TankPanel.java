import java.awt.Color;
import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
public class TankPanel extends JPanel implements Runnable {
	TankFrame tf;
	MyTank mtank=new MyTank(800,1000,false,this,0);
	MyTank mCamp=new MyTank(610,1150,false,this,1);
	List<EnemyTank> enemy=new ArrayList<EnemyTank>();
	List<Missile> missiles=new ArrayList<>();
	Bonus bonus=new Bonus(this);
	 List<Boom> booms=new ArrayList<>();
	 Wall w=new Wall(this);
	 Image offScreen =null;
	 public TankPanel(TankFrame tf) {
		 this.tf=tf;
	 }
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color c=g.getColor();//set backgroud color to black
		g.setColor(Color.black);
		g.fillRect(0, 0, TankFrame.width, TankFrame.length);
		g.setColor(c);
		if(enemy.isEmpty()) {mtank.produceEnemy();}
		mtank.draw(g); //draw my tank
		mCamp.draw(g);
		w.draw(g);
		bonus.draw(g);
		for(int i=0;i<missiles.size();i++) {
			Missile m=missiles.get(i);
			for(int j=0;j<enemy.size();j++) {
				m.hitTank(enemy.get(j));
				m.hitWall();
			}
			if(m.hitTank(mtank))mtank=new MyTank(800,1000,false,this,0);//revival of mytank
			m.draw(g);
		}
		for(int j=0;j<enemy.size();j++) {
			enemy.get(j).draw(g);
		
		for(int i=0;i<booms.size();i++) {
			booms.get(i).draw(g);
		}
		}
	}
	@Override
	public void update(Graphics g) {
		if(offScreen==null) {
			offScreen=this.createImage(TankFrame.width, TankFrame.length);
		}
		Graphics gOffScreen=offScreen.getGraphics();
		Color c=gOffScreen.getColor();
		gOffScreen.fillRect(0, 0, TankFrame.width, TankFrame.length);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreen, 0, 0, this);
	}
	@Override
	public void run() {
		while(true) {
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
 