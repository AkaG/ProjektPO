package interfaces;

import com.badlogic.gdx.math.Vector2;

public interface IPlayerControls {

	public void jump();
	public void moveLeft();
	public void moveRight();
	public void shootAtAngle();
	public void shootAtPosition(Vector2 position);
	
}