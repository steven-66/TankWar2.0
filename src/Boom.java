import java.awt.Color;
import java.awt.Graphics;

public class Boom {
	TankPanel tp;
	int x,y;//coordinates
	 boolean isLive=true;
	int[] diameter={2,4,8,16,8,4,2};
	int step;
	public Boom(int x, int y,TankPanel tp) {
		this.x=x;
		this.y=y;
		this.tp=tp;
	}
	public void draw(Graphics g) {
		if(!isLive) {
			tp.booms.remove(this);
			return;
		}
		if(step>=diameter.length) {
			isLive=false;
			return;
		}
		Color c=g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, diameter[step], diameter[step]);
		g.setColor(c);
		step++;
	}
}
