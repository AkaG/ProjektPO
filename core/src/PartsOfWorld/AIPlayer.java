package PartsOfWorld;

import com.badlogic.gdx.Input;

import interfaces.IAIControl;

public abstract class AIPlayer extends Player implements IAIControl {

	AIPlayer enemy;
	
	public AIPlayer(float x, float y) {
		super(x, y);
	}
	
	public boolean AIjump() {
		super.jump();
		return false;
	}

	public boolean AImoveLeft() {
		super.keyDown(Input.Keys.LEFT);
		return false;
	}

	public boolean AImoveRight() {
		super.keyDown(Input.Keys.RIGHT);
		return false;
	}
	
	public boolean AIStay(){
		super.keyUp(Input.Keys.LEFT);
		super.keyUp(Input.Keys.RIGHT);
		return false;
	}
	
	public boolean AIshoot() {
		super.shoot();
		return false;
	}

	public void AIgetEnemy(AIPlayer enemy) {
		this.enemy = enemy;

	}
	
	public abstract void AIUpdate(); /* tu sobie kazdy napisze AI */  

}
