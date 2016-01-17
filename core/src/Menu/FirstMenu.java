package Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import game.api.GameScreen;
import game.api.MyGame;
import game.api.MyGame.GameMode;

public class FirstMenu extends AbstractMenu {
	
	private TextButton startButton, startButton2, startButton3, buttonExit;
	private MyGame myGame;
	private TextField textField1;
	
	public FirstMenu(Stage stage, MyGame game) {
		super();
		
		myGame = game;
		//tworzenie przyciskow
		startButton = new TextButton("PLAYER VS PLAYER", textButtonStyle);
		startButton.pad(20);
		startButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				
				MyGame.mode = GameMode.PLAYER_VS_PLAYER;
				myGame.setScreen(new GameScreen(myGame));
			}
		});
		
		startButton2 = new TextButton("PLAYER VS CPU", textButtonStyle);
		startButton2.pad(20);
		startButton2.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				
				MyGame.mode = GameMode.PLAYER_VS_CPU;
				myGame.setScreen(new GameScreen(myGame));
			}
		});
		
		startButton3 = new TextButton("CPU VS CPU", textButtonStyle);
		startButton3.pad(20);
		startButton3.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				
				MyGame.mode = GameMode.CPU_VS_CPU;
				myGame.setScreen(new GameScreen(myGame));
			}
		});
		
		buttonExit = new TextButton("EXIT", textButtonStyle);
		buttonExit.pad(20);
		buttonExit.setWidth(500);
		buttonExit.addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				
				Gdx.app.exit();
			}
			
		});
		
		textField1 = new TextField("Winner: " + myGame.winnerName, new Skin(Gdx.files.internal("uiskin.json")));
		textField1.setSize(500, 200);
		
		//dodawanie obiektow do tabeli
		table.add(startButton).width(200);
		table.add().row();
		table.add(startButton2).width(200);
		table.add().row();
		table.add(startButton3).width(200);
		table.add().row();
		table.add(buttonExit).width(200);
		table.add().row();
		table.add(textField1).width(200);
		
		//dodawanie tabeli to sceny
		stage.addActor(table);
	}
	
	public Table getTable(){
		return table;
	}
}
