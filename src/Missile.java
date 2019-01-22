import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Missile {
	private int x,y; //coordinates of missile
	private final static int SPEED=40;
	private static final int Width = 10;
	private static final int Length = 10;
	private Tank.Direction dir;
	private boolean fromAlly;
	private boolean isLive=true;
	private TankPanel tp;
	Image missiles=Toolkit.getDefaultToolkit().getImage("images/missile.png");
	public Missile(int x ,int y, Tank.Direction dir,TankPanel tp,Boolean fromAlly) {
		this.x=x-Width/2;
		this.y=y-Length/2;
		this.dir=dir;
		this.tp=tp;
		this.fromAlly=fromAlly;
	}
	/*
	 * get corresponding area of 
	 * */
	public Rectangle getRec() {
		return new Rectangle(x,y,Width,Length);
	}
	public void draw(Graphics g) {
		if(!isLive) {
			tp.missiles.remove(this);
			return;
		}
		g.drawImage(missiles, x, y, Width, Length, null);
		missileMove();
	}
	public boolean hitTank(Tank t) {
		if(fromAlly && t.isEnemy) {return false;}//from enemy to enemy
		if(!fromAlly && !t.isEnemy) {return false;}//from mytank to mytank
		if(getRec().intersects(t.getRec()) && t.isLive ) {
			t.setLive(false);
			tp.booms.add(new Boom(x,y,tp));
			this.isLive=false;
			return true;
		}
		return false;
	}
	public boolean hitWall() {
		for(int i=0;i<tp.w.wall.size();i++) {
			Brick b=tp.w.wall.get(i);
			if(getRec().intersects(b.getRec())) {
				b.isLive=false;
				this.isLive=false;
				return true;
			}
		}
		return false;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	private void missileMove() {
		
		if(isLive==false) {
			tp.missiles.remove(this);
			return;
		}
		switch(dir) {
		case Right:
			x+=SPEED;
			break;
		case Left:
			x-=SPEED;
			break;
		case Up:
			y-=SPEED;
			break;
		case Down:
			y+=SPEED;
			break;
		case Stay:
			
			break;
		}
		if(x<0 || y<0 || x>TankFrame.width || y>TankFrame.length) {
			isLive=false;
		}
	}
}
