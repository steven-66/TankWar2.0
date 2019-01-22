import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
enum brickType{clay,iron};
public class Wall {
	TankPanel tp;
	
	int x,y;
	ArrayList<Brick> wall=new ArrayList<>();
	int[][] wal= {{},{},
			{0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,0,0,0,1,0,1,0,0,0,1,0},
			{0,0,0,0,0,1,0,0,0,0,0,1,0},
			{0,0,0,1,0,0,0,0,0,1,0,1,0},
			{0,1,0,1,0,3,0,1,0,1,0,0,0},
			{0,0,0,1,0,3,0,1,0,1,0,3,0},
			{0,3,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,0,0,0,0,1,0,1,0},
			{0,1,0,1,0,0,0,0,0,1,0,1,0},
			{0,0,0,1,0,2,2,2,0,1,0,1,0},
			{0,0,0,1,0,2,0,2,0,1,0,1,0}
			};
	public Wall(TankPanel tp) {
		this.tp=tp;
		for (int i = 0; i < wal.length; i++) {
			for (int j = 0; j < wal[i].length; j++) {
				if (wal[i][j] == 1) {
					wall.add(new Brick(j * 100, i *50,tp, brickType.clay,false));
				} else if (wal[i][j] == 3) {
					wall.add(new Brick(j * 100, i * 50,tp,brickType.iron,false));
				}else if(wal[i][j]==2) {
					wall.add(new Brick(j * 100, i *50,tp, brickType.clay,true));
				}
			}
		}
	}
	public void draw(Graphics g) {
		for(int i=0;i<wall.size();i++) {
			wall.get(i).draw(g);
		}
	}
}
class Brick{
	int x,y;
	TankPanel tp;
	boolean isLive=true;
	private int width=100;
	private int height=50;
	private Image im;
	brickType bt;
	boolean isHome;
	public Brick(int x,int y,TankPanel tp,brickType clay,boolean isHome) {
		this.x=x;
		this.y=y;
		this.tp=tp;
		this.bt=clay;
		this.isHome=isHome;
	}
	public Rectangle getRec() {
		return new Rectangle(x,y,width,height);
	}
	public void draw(Graphics g) {
		if(!isLive && bt==brickType.clay) {
			tp.w.wall.remove(this);
			return;
		}
		if(bt==brickType.clay) {
			setImage(Toolkit.getDefaultToolkit().getImage("images/brick.png"));
		}
		else{
			setImage(Toolkit.getDefaultToolkit().getImage("images/iron.png"));
		}
		g.drawImage(this.getImage(), this.x, this.y, this.width,this.height,null);
	}
	public void setImage(Image i) {
		im=i;
	}
	public Image getImage() {
		return  im;
	}
}