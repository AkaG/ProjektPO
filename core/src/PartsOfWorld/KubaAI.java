package PartsOfWorld;

public final class KubaAI extends AIPlayer {

	public KubaAI(float x, float y) { // konstruktor musi byc
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void AIUpdate(float delta) { // przykladowe uzycie funkcji, nie zapomniec o ai przed funkcja
		// TODO Auto-generated method stub
		update(delta);
		AIjump();
	}
}
