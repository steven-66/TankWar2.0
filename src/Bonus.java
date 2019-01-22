import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

enum bonusType{SuperMissile, IronBrick, None}
public class Bonus  {
	int x,y;//coordinates of bonus
	private TankPanel tp;
	private final static int width=80;
	private final static int length=80;
	private final static int SPEED=5;
	boolean isExisting=true;
	Random rand=new Random();
	Image bonus=null;
	int stepX=20;
	int stepY=20;
	private bonusType bt=bonusType.None;
	public Bonus(TankPanel tp) {
		this.tp=tp;
		x=(int) (Math.random()*900);
		y=(int) (Math.random()*900);
	}
	public bonusType getBt() {
		return bt;
	}
	public void setBt(bonusType bt) {
		this.bt = bt;
	}
	public Rectangle getRec() {
		return new Rectangle(x,y,width,length);
	}
	public void draw(Graphics g) {
		generateBonus();
		move();
		if(bt==bonusType.None) {return;}
		if(!isExisting) {return;}
		if(bt==bonusType.SuperMissile) {
			bonus=Toolkit.getDefaultToolkit().getImage("images/supermissile.png");
		}
		else if(bt==bonusType.IronBrick) {
			bonus=Toolkit.getDefaultToolkit().getImage("images/ironfy.png");
		}
		g.drawImage(bonus, x, y, width, length, null);
	}
	public void generateBonus(){
		if(bt!=bonusType.None) {return;}
		int random=rand.nextInt(200);
		if(random==1) {
			bt=bonusType.SuperMissile;
		}
		else if (random==2) {
			bt=bonusType.IronBrick;
		}
		else {
			bt=bonusType.None;
		}
	}
	public void move() {
		if(stepX==0)stepX=20;
		if(stepY==0)stepY=20;
		int random1=rand.nextInt(10)*SPEED;
		int random2=rand.nextInt(10)*(-1)*SPEED;
		if(x+random2>=0 && stepX>=10) {
			x+=random2;
			stepX--;
		}
		else if(x+random1<=TankFrame.width-width && stepX>0) {
			x+=random1;
			stepX--;
		}
		if(y+random2>=0 && stepY>=10) {
			y+=random2;
			stepY--;
		}
		else if(y+random1<=TankFrame.length-length && stepY>0)
			y+=random1;
			stepY--;
	}
}
