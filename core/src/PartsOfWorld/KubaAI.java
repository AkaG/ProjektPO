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
		for(Vec2f enemy : enemyPosition) {
			if(tmp == null){
				tmp = enemy;
				continue;
			}
			else{
				if(enemy.x > x+0.5 && enemy.x < x-0.5){
					if(enemy.y == y || enemy.y < y+10){
						
					if(enemy.x < x){
						if(x < tmp.x){
							if(x - enemy.x < tmp.x - x){
								tmp = enemy;
							}
						}else{
							if(x - enemy.x < x - tmp.x){
								tmp = enemy;
							}
						}
					}else{
						if(x < tmp.x){
							if(enemy.x - x < tmp.x - x){
								tmp = enemy;
							}
						}else{
							if(enemy.x - x < x - tmp.x){
								tmp = enemy;
							}
						}
					}
					}
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
			AImoveLeft();
		}
		if(nearX > x){
			AImoveRight();
		}
		
		if(nearY < y-100){
			AImoveLeft();
		}
		
		if(nearY-20 > y){
			AIjump();
		}
		
		if(counter == 0){
			AIjump();
		}
			
		AIshoot();
		counter = (counter + 1)%500;
		
	}
	
}
