package interfaces;

import PartsOfWorld.AIPlayer;

public interface IAIControl {
	
	public boolean AIjump();
	public boolean AImoveLeft();
	public boolean AImoveRight();
	public boolean AIshoot();
	
	public void AIgetEnemy(AIPlayer enemy);
}
