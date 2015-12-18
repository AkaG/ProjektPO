package PartsOfWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class KubaAI extends AIPlayer {

	public KubaAI(float x, float y) { // konstruktor musi byc
		super(x, y);
		// TODO Auto-generated constructor stu
	}

	
	@Override
	public void update(float delta)
	{
		super.update(delta);
		AIjump();
	}
	
	public void AIUpdate(float delta) { // przykladowe uzycie funkcji, nie zapomniec o ai przed funkcja
		// TODO Auto-generated method stub
		update(delta);
		AIjump();
	}
	
}
