package com.robtova.modern;

import com.jme3.asset.AssetManager;
import com.jme3.texture.Texture;

public class ResourceFiles {
	public static Texture blocks;
	public static Texture block_normals;
	public static Texture base;
	
	public static void load(AssetManager assetManager) {
		blocks = loadImage(assetManager, "blocks.png");
		block_normals = loadImage(assetManager, "blocks_N.png");
		base = loadImage(assetManager, "base.png");
	}
	
	public static Texture loadImage(AssetManager assetManager, String name) {
		Texture t = assetManager.loadTexture(name);
		if(t == null) return null;
		t.setMagFilter(Texture.MagFilter.Bilinear);
		t.setMinFilter(Texture.MinFilter.BilinearNoMipMaps);
		t.setAnisotropicFilter(2);
		return t;
	}
}
