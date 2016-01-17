package PartsOfWorld;

import com.badlogic.gdx.math.Rectangle;

public class MovingObject extends Rectangle{

	protected float xSpeed;
	protected float ySpeed;
	
	public float getxSpeed() {
		return xSpeed;
	}
	public void setxSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}
	public float getySpeed() {
		return ySpeed;
	}
	public void setySpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}
	
}
