package com.froggygame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static TextureAtlas atlas;
	public static TextureRegion frog;
	public static TextureRegion background;
	public static TextureRegion car;
	public static TextureRegion road;

	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("images.atlas"));
		car = atlas.findRegion("frog_minimal");
		road = atlas.findRegion("background");
	}

	public static void dispose() {
		atlas.dispose();
	}
}
