package PartsOfWorld;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.sun.javafx.geom.Vec2f;

import PartsOfWorld.Player.Direction;
import interfaces.IAIControl;

public abstract class AIPlayer extends Player implements IAIControl {

	AIPlayer enemy;
	ArrayList<Vec2f> enemyPosition;
	ArrayList<Vec2f> bulletsPositions;
			
	public AIPlayer(float x, float y, Player.TypeOfGun gun) {
		super(x, y,gun);
<<<<<<< HEAD
		enemyPosition = new ArrayList<Vec2f>();
=======
		
		enemyPosition = new ArrayList<Vec2f>();
		bulletsPositions = new ArrayList<Vec2f>();
>>>>>>> bb8a3de6918a2c37f74c04000643e7ad82bf7f15
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
	
	public void update(float delta)
	{
		super.update(delta);
		
		AIUpdate(delta);
	}
	
	public void updateEnemyPosition(ArrayList<Vec2f> otherPos){
		enemyPosition = otherPos;
	}
	
	public void updateEnemyBulletsPositions(ArrayList<Vec2f> bulletsPos)
	{
		this.bulletsPositions = bulletsPos;
	}
	
	public abstract void AIUpdate(float delta); /* tu sobie kazdy napisze AI */  

}
