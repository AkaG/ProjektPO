package PartsOfWorld;

import java.util.Random;

import com.badlogic.gdx.Gdx;

public class AdamAI extends AIPlayer{

	Random random;
	private float timer = 0;
	
	public AdamAI(float x, float y) {
		super(x, y);
		
		random = new Random();
		// TODO Auto-generated constructor stub
	}
	
	public void update(float delta)
	{
		super.update(delta);
		
		AIUpdate(delta);
	}

	@Override
	public void AIUpdate(float delta) {
		// TODO Auto-generated method stub
		timer += delta;
		if(timer >= 1)
		{
		randomMove();
		timer -=1 ;
		}
		
		AIshoot();
		
		if(this.x >= Gdx.graphics.getWidth() - this.width) AImoveLeft();
		else if(this.x <= 0) AImoveRight();
	}

	public void randomMove()
	{
		int temp = random.nextInt(3);
		switch(temp)
		{
		case 0: this.AImoveRight();
		break;
		case 1: this.AImoveLeft();
		break;
		case 2: this.AIjump();
		break;
		}
	}
	
}
