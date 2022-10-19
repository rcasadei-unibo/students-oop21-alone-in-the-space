package controller.gameEngine;

import controller.eventController.EventController;
import controller.eventController.EventControllerImpl;
import controller.gameController.GameController;
import controller.gameController.GameControllerImpl;
import utilities.EnumInt;
import view.GameMap;
import view.GameMapImpl;

public class GameEngineImpl implements GameEngine {
	
	private static final long PERIOD = 100L;
	
	private GameController game;
	private GameMap map;
	private EventController event;
	
	public GameEngineImpl() {
		
	}
	
	@Override
	public void mainLoop() {
		long lastTime = System.currentTimeMillis();
		
		while(event.checkGameStatus()) {
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
		this.map = new GameMapImpl(EnumInt.HEIGHT.getValue(), EnumInt.WIDTH.getValue(), this); 
		this.game = new GameControllerImpl(this.map);
		this.event = new EventControllerImpl();
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
