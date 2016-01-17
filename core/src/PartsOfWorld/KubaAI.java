package PartsOfWorld;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.sun.javafx.geom.Vec2f;
import com.sun.org.apache.xalan.internal.utils.ConfigurationError;

public final class KubaAI extends AIPlayer {

	public KubaAI(float x, float y, Player.TypeOfGun gun) { 
		super(x, y, gun);
		initTextures();
		name = "Kuba";
		
	}
	
	public void initTextures() {
		// TODO Auto-generated method stub
		 //INICJALIZACJA TEXTUR I ANIMACJI
        textureAtlas = new TextureAtlas(Gdx.files.internal("PlayerSprites/player3.atlas"));
		GoAnimation = new Animation(0.1f, (textureAtlas.findRegion("1")), (textureAtlas.findRegion("2")),
				(textureAtlas.findRegion("3")));
		StayAnimation = new Animation(0.1f, (textureAtlas.findRegions("4")));
		JumpAnimation = new Animation(0.5f, (textureAtlas.findRegion("8")));
	}
	
	private Vec2f findNearestEnemy(){
		Vec2f tmp = null;
		float minDist = 0;
		for(Vec2f enemy : enemyPosition) {
			if(tmp == null){
				tmp = enemy;
				if(enemy.x > x){
					minDist = enemy.x - x;
				}else{
					minDist = x - enemy.x;
				}
				continue;
			}
			else{
				float dist = 0;
				if(enemy.x > x){
					dist = enemy.x - x;
				}else{
					dist = x - enemy.x;
				}
				if(dist < minDist){
					minDist = dist;
					tmp = enemy;
				}
			}
		}
		return tmp;
	}
	
	private float nearX, nearY; 
	private int counter;
	
	public void AIUpdate(float delta) { 
		if(counter % 20 == 0){
			Vec2f tmp = findNearestEnemy();
			if(tmp != null){
				nearX = tmp.x;
				nearY = tmp.y;
			}
		}
		
		if(nearX < x){
			if(x - nearX > 30)
				AImoveLeft();
			else 
				AIStay();
		}
		if(nearX > x){
			if(nearX - x > 30)
				AImoveRight();
			else 
				AIStay();
		}
		
		if(nearY < y-100){
			AImoveLeft();
		}
		
		if(nearY-70 > y){
			AImoveRight();
			AIjump();
		}
		
		if(nearY-20 > y){
			AIjump();
		}
		
		if(counter == 0 && y < 50){
			AIjump();
		}
			
		AIshoot();
		counter = (counter + 1)%500;
		
	}
	
}