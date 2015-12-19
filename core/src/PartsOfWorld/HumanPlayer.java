/**
 * 
 */
package PartsOfWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import PartsOfWorld.Player.Direction;

/**
 * @author Jakub
 *
 */

public class HumanPlayer extends Player implements InputProcessor {

	public HumanPlayer(float x, float y, Player.TypeOfGun gun) {
		super(x, y, gun);
		initTextures();
		// TODO Auto-generated constructor stub
		
	}
	
	public void initTextures() {
		// TODO Auto-generated method stub
		 //INICJALIZACJA TEXTUR I ANIMACJI
        textureAtlas = new TextureAtlas(Gdx.files.internal("PlayerSprites/player.atlas"));
		GoAnimation = new Animation(0.1f, (textureAtlas.findRegion("1")), (textureAtlas.findRegion("2")),
				(textureAtlas.findRegion("3")));
		StayAnimation = new Animation(0.1f, (textureAtlas.findRegions("4")));
		JumpAnimation = new Animation(0.5f, (textureAtlas.findRegion("8")));
	}
	

	@Override
	public boolean keyDown(int keycode) {
		
		if(keycode == Input.Keys.LEFT){
			super.PmoveLeft();
		}
		else if(keycode == Input.Keys.RIGHT){
			super.PmoveRight();
		}
		
		if(keycode == Input.Keys.UP){
			super.Pjump();
		}
		
		if(keycode == Input.Keys.ENTER){
			super.Pshoot();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(dir != Direction.RUN_RIGHT && keycode == Input.Keys.LEFT)
			dir = Direction.STAY_LEFT;
		if(dir != Direction.RUN_LEFT && keycode == Input.Keys.RIGHT)
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
