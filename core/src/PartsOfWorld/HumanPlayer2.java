package PartsOfWorld;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import PartsOfWorld.Player.Direction;

public final class HumanPlayer2 extends Player implements InputProcessor {

	public HumanPlayer2(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean keyDown(int keycode) {

		if(keycode == Input.Keys.A){
			super.PmoveLeft();
		}
		else if(keycode == Input.Keys.D){
			super.PmoveRight();
		}
		
		if(keycode == Input.Keys.W){
			super.Pjump();
		}
		
		if(keycode == Input.Keys.SPACE){
			super.Pshoot();
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		if(dir != Direction.RUN_RIGHT && keycode == Input.Keys.A)
			dir = Direction.STAY_LEFT;
		if(dir != Direction.RUN_LEFT && keycode == Input.Keys.D)
			dir = Direction.STAY_RIGHT;
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
