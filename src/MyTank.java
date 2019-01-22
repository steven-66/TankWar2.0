import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class MyTank extends Tank {
	private int tankType;
	private Bonus bonus=new Bonus(tp);
	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
	public MyTank(int x, int y, boolean isEnemy,TankPanel tp,int tankType) {
		super(x, y, isEnemy,tp);
		this.tankType=tankType;
	}
//	@Override
	public void draw(Graphics g) {
		if(isLive==false) {
			return;
		}
		if(tankType==0) {
			switch(barrelDir) {
			case Left:
				setTankImage(Toolkit.getDefaultToolkit().getImage("images/left.png"));
				break;
			case Right:
				setTankImage(Toolkit.getDefaultToolkit().getImage("images/right.png"));
				break;
			case Up:
				setTankImage(Toolkit.getDefaultToolkit().getImage("images/up.png"));
				break;
			case Down:
				setTankImage(Toolkit.getDefaultToolkit().getImage("images/down.png"));
				break;
			case Stay:
				break;
			}
		}
		else if(tankType==1) {
			setTankImage(Toolkit.getDefaultToolkit().getImage("images/camp.png"));
		}
		g.drawImage(this.getTankImage(), this.getX(), this.getY(), this.getWidth(),this.getHeight(),null);
	}

	/*
	 * press key to control tank
	 * */
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			dir=Direction.Left;
			break;
		case KeyEvent.VK_RIGHT:
			dir=Direction.Right;
			break;
		case KeyEvent.VK_UP:
			dir=Direction.Up;
			break;
		case KeyEvent.VK_DOWN:
			dir=Direction.Down;
			break;
		}
		if(dir!=Direction.Stay)
			barrelDir=dir;
		TankMove();
	}
	public void keyReleased(KeyEvent e) {
		int code=e.getKeyCode();
		if(code==KeyEvent.VK_A) {
			if((bonus.getBt()==bonusType.SuperMissile)) {
				superFire();
			}
			else{
				fire();
			}
		}
		else if(code==KeyEvent.VK_LEFT || code==KeyEvent.VK_RIGHT || code==KeyEvent.VK_UP || code==KeyEvent.VK_DOWN) {
			dir=Direction.Stay;
		}
	}
	public void superFire() {
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
		tp.missiles.add(new Missile(missileX,missileY,Direction.Down,tp,isEnemy));
		tp.missiles.add(new Missile(missileX,missileY,Direction.Up,tp,isEnemy));
		tp.missiles.add(new Missile(missileX,missileY,Direction.Left,tp,isEnemy));
		tp.missiles.add(new Missile(missileX,missileY,Direction.Right,tp,isEnemy));
	}
	private boolean getBonus() { //pick up the bonus
		if(this.getRec().intersects(tp.bonus.getRec())){
			this.bonus.setBt(tp.bonus.getBt());
			tp.bonus.isExisting=false;
			tp.bonus=new Bonus(tp);
			if(bonus.getBt()==bonusType.IronBrick) {
				for(Brick b:tp.w.wall) {
					if(b.isHome) {
						b.bt=brickType.iron;
					}
				}
			}
			return true;
		}
		return false;
	}
	public void produceEnemy() {
		int count=0;
		while(count<=10) {
			EnemyTank e=new EnemyTank((int)(Math.random()*900),(int)(Math.random()*900),true,tp);
			if(e.notCollided(tp.enemy)&&e.notCollideWall(tp.w)) {
				tp.enemy.add(e);
				count++;
			}
		}
	}
	@Override
	public void TankMove() {
		getBonus();
		super.TankMove();
	}
}
