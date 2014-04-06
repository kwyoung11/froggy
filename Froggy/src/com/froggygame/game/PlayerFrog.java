package com.froggygame.game;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerFrog extends Actor {
	private final FroggyGame froggyGame;
	private final Rectangle bounds = new Rectangle();
	private int lane;
	private boolean jumping;

	public PlayerFrog(FroggyGame froggyGame) {
		this.froggyGame = froggyGame;
		setWidth(160);
		setHeight(85);
		lane = 1;
		setPosition(100, froggyGame.lane1 - getHeight()/2);
		setColor(Color.YELLOW);
	}

	@Override
	public void act(float delta){
		super.act(delta);
		updateBounds();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
		batch.draw(Assets.car, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}

	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public void tryMoveUp() {
		if ((getActions().size == 0) && (lane != 2)) moveToLane(lane+1);
	}

	public void tryMoveDown() {
		if ((getActions().size == 0) && (lane != 0)) moveToLane(lane-1);
	}

	private void moveToLane(int lane) {
		this.lane = lane;

		switch (lane) {
			case 0:
				addAction(moveTo(getX(), froggyGame.lane0 - getHeight()/2, 0.5f));
				break;
			case 1:
				addAction(moveTo(getX(), froggyGame.lane1 - getHeight()/2, 0.5f));
				break;
			case 2:
				addAction(moveTo(getX(), froggyGame.lane2 - getHeight()/2, 0.5f));
				break;
		}
	}

	public void tryJump() {
		if ((getActions().size == 0) && (!jumping)) jump();
	}

	private void jump() {
		jumping = true;
		addAction(sequence(moveTo(getX(), froggyGame.lane2 - getHeight() / 2, 0.5f),
				           moveTo(getX(), froggyGame.lane1 - getHeight() / 2, 0.5f)));
		jumping = false;
	}

	public Rectangle getBounds() {
		return bounds;
	}
}
