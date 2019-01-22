import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.List;
import java.util.Random;

public class EnemyTank extends Tank {
	private Random random=new Random();
	private int timer=10;
	public EnemyTank(int x, int y, boolean isEnemy, TankPanel tp) {
		super(x, y, isEnemy, tp);
	}
	public void draw(Graphics g) {
		if(!isLive) {
			tp.enemy.remove(this);
			return;
		}
		TankMove();
		switch(barrelDir) {
		case Left:
			setTankImage(Toolkit.getDefaultToolkit().getImage("images/left1.png"));
			break;
		case Right:
			setTankImage(Toolkit.getDefaultToolkit().getImage("images/right1.png"));
			break;
		case Up:
			setTankImage(Toolkit.getDefaultToolkit().getImage("images/up1.png"));
			break;
		case Down:
			setTankImage(Toolkit.getDefaultToolkit().getImage("images/down1.png"));
			break;
		case Stay:
			break;
		}
		g.drawImage(this.getTankImage(), this.getX(), this.getY(), this.getWidth(),this.getHeight(),null);
	}
	@Override
	public void TankMove() {
		if(dir!=Direction.Stay)
			barrelDir=dir;
			Direction[] d=Direction.values();
			Direction changeDir=d[random.nextInt(d.length)];
			if(dir!=changeDir && timer==0) {
				fire();
				dir=changeDir;
				timer=10;
			}
			else if (dir==changeDir && timer!=0) {
				super.TankMove();
				timer--;
			}
	}
	public boolean notCollided(List<EnemyTank> enemy) {
		for(EnemyTank t:enemy) {
			if(this.getRec().intersects(t.getRec()))return false;
		}
		return true;
	}
}