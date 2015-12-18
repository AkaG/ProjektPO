package PartsOfWorld;

import com.badlogic.gdx.Input;

import PartsOfWorld.Player.Direction;
import interfaces.IAIControl;

public abstract class AIPlayer extends Player implements IAIControl {

	AIPlayer enemy;
	
	public AIPlayer(float x, float y) {
		super(x, y);
	}
	
	public boolean AIjump() {
		super.Pjump();;
		return false;
	}

	public boolean AImoveLeft() {
		super.PmoveLeft();
		return false;
	}

	public boolean AImoveRight() {
		super.PmoveRight();
		return false;
	}
	
	public boolean AIStay(){
		if(dir == Direction.RUN_LEFT)
			dir = Direction.STAY_LEFT;
		if(dir == Direction.RUN_RIGHT)
			dir = Direction.STAY_RIGHT;
		return false;
	}
	
	public boolean AIshoot() {
		super.Pshoot();
		return false;
	}

	public void AIgetEnemy(AIPlayer enemy) {
		this.enemy = enemy;

	}
	
	public abstract void AIUpdate(float delta); /* tu sobie kazdy napisze AI */  

}
