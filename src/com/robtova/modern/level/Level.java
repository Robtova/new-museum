package com.robtova.modern.level;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.robtova.modern.Tesselator;
import com.robtova.modern.TesselatorRender;

public class Level implements TesselatorRender {
	private short[][][] blocks;
	private int w_width, w_height, w_depth;
	public static final int height = 9;
	public static final float m = 2.11f;
	public static float player_eye = 3.1f;
	public static float plaque_level = 2.65f;
	
	public ArrayList<CollisionBox> collisions = new ArrayList<CollisionBox>();
	
	public Level(int width, int height, int depth, BufferedImage map) {
		blocks = new short[width][height][depth];
		this.w_width = width;
		this.w_height = height;
		this.w_depth = depth;
		
		for(int i = 0; i < width; i++) {
			for(int k = 0; k < depth; k++) {
				setBlock(Block.floor, i, 0, k);
				setBlock(Block.wall, i, 10, k);
				
				if(map.getRGB(i, k) == 0xffffffff) {
					setBlock(Block.wall, i, 1, k);
					setBlock(Block.wall, i, 2, k);
					setBlock(Block.wall, i, 3, k);
					setBlock(Block.wall, i, 4, k);
					setBlock(Block.wall, i, 5, k);
					setBlock(Block.wall, i, 6, k);
					setBlock(Block.wall, i, 7, k);
					setBlock(Block.wall, i, 8, k);
					setBlock(Block.wall, i, 9, k);
				}
				
				if(map.getRGB(i, k) == 0xffE5E5E5) {
					setBlock(Block.wall, i, 6, k);
					setBlock(Block.wall, i, 7, k);
					setBlock(Block.wall, i, 8, k);
					setBlock(Block.wall, i, 9, k);
				}
				
				if(map.getRGB(i, k) == 0xffD6D6D6) {
					setBlock(Block.FE, i, 6, k);
					setBlock(Block.wall, i, 7, k);
					setBlock(Block.wall, i, 8, k);
					setBlock(Block.wall, i, 9, k);
				}

				if(map.getRGB(i, k) == 0xff696969) {
					setBlock(Block.grate, i, 0, k);
				}
				
				if(map.getRGB(i, k) == 0xffff0000) {
					setBlock(Block.light, i, 10, k);
				}
			}
		}
		
		setBlock(Block.GBCoCSD, 2, 2, 1);
		setBlock(Block.GBCoCSD_P, 3, 2, 0);

		setBlock(Block.Coffee_Mill, 7, 2, 1);
		setBlock(Block.Coffee_Mill_P, 6, 2, 0);
		
		setBlock(Block.Spiral_Movement, 11, 2, 1);
		setBlock(Block.Spiral_Movement_P, 12, 2, 0);
		
		setBlock(Block.Spanish_Landscape, 1, 1, 3);
		setBlock(Block.Spanish_Landscape_P, 0, 2, 4);
		
		setBlock(Block.Inland_Australia, 1, 1, 9);
		setBlock(Block.Inland_Australia_P, 0, 2, 6);
		
		setBlock(Block.Questioning_Children, 17, 1, 2);
		setBlock(Block.Questioning_Children_P, 18, 2, 4);

		setBlock(Block.LD54, 17, 1, 7);
		setBlock(Block.LD54_P, 18, 2, 6);
		
		setBlock(Block.Cubist_Skull, 17, 1, 22);
		setBlock(Block.Cubist_Skull_Picture, 17, 2, 21);
		
		setBlock(Block.Ball_Plane_hole_P, 16, 2, 0);
		
		setBlock(Block.Portrait, 1, 1, 16);
		setBlock(Block.Frog, 1, 1, 21);
	}
	
	public Block getBlock(int x, int y, int z) {
		if(x < 0 || x >= w_width || y < 0 || y >= w_height || z < 0 || z >= w_depth) return Block.air;
		return Block.blocks[blocks[x][y][z]];
	}
	
	public void setBlock(Block block, int x, int y, int z) {
		if(x < 0 || x >= w_width || y < 0 || y >= w_height || z < 0 || z >= w_depth) return;
		blocks[x][y][z] = block.id;
		block.onAdded(this, x, y, z);
	}
	
	@Override
	public void tesselate(Tesselator tess) {
		for(int i = 0; i < w_width; i++) {
			for(int j = 0; j < w_height; j++) {
				for(int k = 0; k < w_depth; k++) {
					this.getBlock(i, j, k).render(tess, i, j, k, this);
				}
			}
		}
	}
}
