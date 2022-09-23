package controller;

public class GameEngineImpl implements GameEngine {
	
	private static final long PERIOD = 100L;
	
	public GameEngineImpl() {
		
	}
	
	@Override
	public void mainLoop() {
		long lastTime = System.currentTimeMillis();
		
		while(true) {
			long current = System.currentTimeMillis();
			int elapsed = (int) (current - lastTime);
			
			processInput();
			update(elapsed);
			render();
			
			try {
                waitForNextFrame(current);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

            lastTime = current;
		}
	}

	@Override
	public void processInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int elapsed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initGame() {
		// TODO Auto-generated method stub

	}
	
	protected void waitForNextFrame(final long current) {
        long dt = System.currentTimeMillis() - current;

        if (dt < PERIOD) {
            try {
                Thread.sleep(PERIOD - dt);
            } catch (IllegalArgumentException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
