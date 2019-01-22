import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

public class Tank {
	protected int x; //initial coordinates of tank
	protected int y;
	TankPanel tp;
	protected static int width=80;
	protected static int height=80;
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	protected static final int SPEED =20;
	enum Direction{Right,Left,Up,Down,Stay};
	protected Direction dir;//current direction
	protected Direction barrelDir;//direction for barrel
	protected boolean isEnemy;//current tank is enemy or not
	private Image tankImage;
	protected boolean isLive=true;
	public Tank(int x,int y,boolean isEnemy) {
		this.x=x;
		this.y=y;
		this.isEnemy=isEnemy;
		dir=Direction.Stay;
		barrelDir=Direction.Up;
	}
	public Tank(int x,int y,boolean isEnemy,TankPanel tp) {
		this(x,y,isEnemy);
		this.tp=tp;
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
	public boolean isEnemy() {
		return isEnemy;
	}
	public void setEnemy(boolean isEnemy) {
		this.isEnemy = isEnemy;
	}
	public Image getTankImage() {
		return tankImage;
	}
	public void setTankImage(Image tankImage) {
		this.tankImage = tankImage;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void draw(Graphics g) {
		
	}
	public Rectangle getRec(){
		return new Rectangle(x,y,Tank.width,Tank.height);
	}
	public Rectangle getNextRec() {
		switch(dir) {
		case Up:
			return new Rectangle(x,y-SPEED,width,height);
		case Down:
			return new Rectangle(x,y+SPEED,width,height);
		case Left:
			return new Rectangle(x-SPEED,y,width,height);
		case Right:
			return new Rectangle(x+SPEED,y,width,height);
		default:
			return new Rectangle(x,y,width,height);
		}
	}
	protected void fire() {
		if(isLive) {
			int missileX=0;
			int missileY=0;
			switch(barrelDir) {
			case Up:
				missileX=this.x+width/2;
				missileY=this.y;
				break;
			case Down:
				missileX=this.x+width/2;
				missileY=this.y+height;
				break;
			case Left:
				missileX=this.x;
				missileY=this.y+height/2;
				break;
			case Right:
				missileX=this.x+width;
				missileY=this.y+height/2;
				break;
			default:
				break;
			}
			tp.missiles.add(new Missile(missileX,missileY,barrelDir,tp,isEnemy));
		}
	}
	public boolean notCollided(Tank another) {
		if(this.getNextRec().intersects(another.getRec())) {
			return false;
		}
		return true;
	}
	public boolean notCollideWall(Wall w) {
		boolean flag=true;
		for(Brick b : w.wall) {
			if(this.isLive && this.getNextRec().intersects(b.getRec())) {
				flag=false;
			}
		}
		return flag;
	}
	public  void TankMove() {
		if(!notCollideWall(tp.w)) {return;}
		for(Tank t:tp.enemy) {
			if(this==t) {continue;}
			if(!notCollided(t)) {return;}
		}
		switch(dir) {
		case Left:
			if(x-Tank.SPEED>=0)
				x-=Tank.SPEED;
			break;
		case Right:
			if(x+Tank.SPEED<=TankFrame.width-Tank.width)
				x+=Tank.SPEED;
			break;
		case Up:
			if(y-Tank.SPEED>=0)
				y-=Tank.SPEED;
			break;
		case Down:
			if(y+Tank.SPEED<=TankFrame.length-Tank.height*2)
			y+=Tank.SPEED;
			break;
		default:
			break;
		}
	}
	public void setLive(boolean b) {
		isLive=b;
	}
}
