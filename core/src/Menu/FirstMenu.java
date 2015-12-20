package Menu;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class FirstMenu extends AbstractMenu {
	
	private TextButton startButton, buttonExit;
	
	public FirstMenu(Stage stage) {
		super();
		
		//tworzenie przyciskow
		startButton = new TextButton("Start Game", textButtonStyle);
		startButton.pad(20);
		buttonExit = new TextButton("EXIT", textButtonStyle);
		buttonExit.pad(20);
		
		//dodawanie obiektow do tabeli
		table.add(startButton);
		table.add().row();
		table.add(buttonExit);
		
		//dodawanie tabeli to sceny
		stage.addActor(table);
	}
	
	public Table getTable(){
		return table;
	}
}
