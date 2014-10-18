package com.robtova.modern.level;

import com.robtova.modern.Program;
import com.robtova.modern.Tesselator;
import com.robtova.modern.screen.plaques.PlaqueBallPlaneHole;
import com.robtova.modern.screen.plaques.PlaqueChurch;
import com.robtova.modern.screen.plaques.PlaqueCoffeeMill;
import com.robtova.modern.screen.plaques.PlaqueInland_A;
import com.robtova.modern.screen.plaques.PlaqueLecture;
import com.robtova.modern.screen.plaques.PlaqueQC;
import com.robtova.modern.screen.plaques.PlaqueSpanishHills;
import com.robtova.modern.screen.plaques.PlaqueSpiralMovement;

public class Block {

	public static Block[] blocks = new Block[256];
	public static final int NORTH = 1, SOUTH = 3, EAST = 2, WEST = 4, TOP = 0, BOTTOM = 5;
	
	public static float size = 1f;
	public static float img_size = 8192f;
	public static float section_size = 512f;
	public static int a = (int) (img_size / section_size);
	public static float c = 1f / (float) a;
	public static float tm = 1f / img_size, tm1 = 1f / img_size;

	public static Block air = new Block(0, -1).setSolid(false);
	public static Block floor = new Block(1, 0);
	public static Block wall = new BlockWall(2, 1);
	public static Block FE = new Block(5, 5).setIcon(1, 5);
	public static Block light = new Block(12, 2);
	public static Block grate = new Block(21, 8);
	
	// Paintings
	public static Block GBCoCSD = new BlockPainting(3, 7618f / img_size, 6972f / img_size, 407f / img_size, 482f / img_size, 0.05f, 11f / img_size, 55f / section_size, -241f / section_size);
	public static Block Coffee_Mill = new BlockPainting(6, 4199f / img_size, 7700f / img_size, 176f / img_size, 490f / img_size, 0.05f, 10f / img_size, 76f / section_size, -245f / section_size);
	public static Block Spiral_Movement = new BlockPainting(8, 7126f / img_size, 6964f / img_size, 490f / img_size, 490f / img_size, 0.1f, 10f / img_size, 0f / section_size, -245f / section_size);
	public static Block Spanish_Landscape = new BlockPainting(10, 4998f / img_size, 7520f / img_size, 784f / img_size, 670f / img_size, 0.05f, 3f / img_size, 120f / section_size, -335f / section_size);
	public static Block Inland_Australia = new BlockPainting(13, 5784f / img_size, 7081f / img_size, 1340f / img_size, 1109f / img_size, 0.1f, 17f / img_size, 98f / section_size, -554f / section_size);
	public static Block Questioning_Children = new BlockPainting(15, 4377f / img_size, 7289f / img_size, 619f / img_size, 901f / img_size, 0.1f, 8f / img_size, 207f / section_size, -450f / section_size);
	public static Block LD54 = new BlockPainting(17, 7126f / img_size, 7456f / img_size, 1064f / img_size, 734f / img_size, 0.05f, 12f / img_size, 150f / section_size, -367f / section_size);
	public static Block Cubist_Skull = new BlockPainting(19, 2849f / img_size, 7550f / img_size, 909f / img_size, 640f / img_size, 0.02f, 12f / img_size, 57.5f / section_size, -320f / section_size);
	public static Block Cubist_Skull_Picture = new BlockPainting(20,  2508f / img_size, 7946f / img_size, 339f / img_size, 244f / img_size, 0.02f, 5f / img_size, 86.5f / section_size, -122f / section_size);
	public static Block Portrait = new BlockPainting(23,  2071f / img_size, 7639f / img_size, 435f / img_size, 551f / img_size, 0.07f, 5f / img_size, 38.5f / section_size, -300f / section_size);
	public static Block Frog = new BlockPainting(24,  1518f / img_size, 7749f / img_size, 551f / img_size, 441f / img_size, 0.04f, 5f / img_size, 236.5f / section_size, -260f / section_size);
	
	// Plaques
	public static Block GBCoCSD_P = new BlockPlaque(new PlaqueChurch(), 4, 6, NORTH, 3939f / img_size, 7588f / img_size, 210f / img_size, 300f / img_size, 151f / section_size, 290f / section_size);
	public static Block Coffee_Mill_P = new BlockPlaque(new PlaqueCoffeeMill(), 7, 6, NORTH, 3760f / img_size, 7890f / img_size, 210f / img_size, 300f / img_size, 151f / section_size, 106f / section_size);
	public static Block Spiral_Movement_P = new BlockPlaque(new PlaqueSpiralMovement(), 9, 6, NORTH, 3939f / img_size, 7286f / img_size, 210f / img_size, 300f / img_size, 151f / section_size, 106f / section_size);
	public static Block Spanish_Landscape_P = new BlockPlaque(new PlaqueSpanishHills(), 11, 3, WEST, 5299f / img_size, 7218f / img_size, 390f / img_size, 300f / img_size, 61f / section_size, 106f / section_size);
	public static Block Inland_Australia_P = new BlockPlaque(new PlaqueInland_A(), 14, 7, WEST, 4151f / img_size, 7398f / img_size, 224f / img_size, 300f / img_size, 142f / section_size, 106f / section_size);
	public static Block Questioning_Children_P = new BlockPlaque(new PlaqueQC(), 16, 7, EAST, 3973f / img_size, 7890f / img_size, 224f / img_size, 300f / img_size, 142f / section_size, 106f / section_size);
	public static Block LD54_P = new BlockPlaque(new PlaqueLecture(), 18, 4, EAST, 4998f / img_size, 7221f / img_size, 268f / img_size, 147f / img_size, 122f / section_size, 183f / section_size);
	public static Block Ball_Plane_hole_P = new BlockPlaque(new PlaqueBallPlaneHole(), 22, 6, NORTH, 3727f / img_size, 7248f / img_size, 210f / img_size, 300f / img_size, 151f / section_size, 106f / section_size);
	
	public short id;
	public int[] icon, ix, iy;

	public boolean solid = true;

	public Block(int id, int ico) {
		this.id = (short) id;
		if(blocks[id] != null) throw new RuntimeException("Duplicate block ids.");
		blocks[id] = this;
		setIcon(ico);
	}
	
	public Block setSolid(boolean b) {
		this.solid = b;
		return this;
	}
	
	public Block setIcon(int ico) {
		this.icon = new int[] {ico, ico, ico, ico, ico, ico};
		iy = new int[] {icon[0] / a, icon[1] / a, icon[2] / a, icon[3] / a, icon[4] / a, icon[5] / a};
		ix = new int[] {icon[0] - (iy[0] * a), icon[1] - (iy[1] * a), icon[2] - (iy[2] * a), icon[3] - (iy[3] * a), icon[4] - (iy[4] * a), icon[5] - (iy[5] * a)};
		return this;
	}
	
	public Block setIcon(int ico, int ico2) {
		this.icon = new int[] {ico, ico2, ico2, ico2, ico2, ico};
		iy = new int[] {icon[0] / a, icon[1] / a, icon[2] / a, icon[3] / a, icon[4] / a, icon[5] / a};
		ix = new int[] {icon[0] - (iy[0] * a), icon[1] - (iy[1] * a), icon[2] - (iy[2] * a), icon[3] - (iy[3] * a), icon[4] - (iy[4] * a), icon[5] - (iy[5] * a)};
		return this;
	}
	
	public Block setIcon(int ico, int ico2, int b) {
		this.icon = new int[] {ico2, ico2, ico2, ico2, ico2, ico2};
		this.icon[b] = ico;
		iy = new int[] {icon[0] / a, icon[1] / a, icon[2] / a, icon[3] / a, icon[4] / a, icon[5] / a};
		ix = new int[] {icon[0] - (iy[0] * a), icon[1] - (iy[1] * a), icon[2] - (iy[2] * a), icon[3] - (iy[3] * a), icon[4] - (iy[4] * a), icon[5] - (iy[5] * a)};
		return this;
	}
	
	public Block setIcon(int ico, int ico2, int ico3, int ico4, int ico5, int ico6) {
		this.icon = new int[] {ico, ico2, ico3, ico4, ico5, ico6};
		iy = new int[] {icon[0] / a, icon[1] / a, icon[2] / a, icon[3] / a, icon[4] / a, icon[5] / a};
		ix = new int[] {icon[0] - (iy[0] * a), icon[1] - (iy[1] * a), icon[2] - (iy[2] * a), icon[3] - (iy[3] * a), icon[4] - (iy[4] * a), icon[5] - (iy[5] * a)};
		return this;
	}

	public void render(Tesselator tess, int i, int j, int k, Level level) {
		if(icon[0] < 0) return;

		boolean xm = !level.getBlock(i - 1, j, k).solid, xp = !level.getBlock(i + 1, j, k).solid;
		boolean ym = !level.getBlock(i, j - 1, k).solid, yp = !level.getBlock(i, j + 1, k).solid;
		boolean zm = !level.getBlock(i, j, k - 1).solid, zp = !level.getBlock(i, j, k + 1).solid;
		
		float size1 = size;
		
		float x = i;
		float y = j;
		float z = k;

		if(zm) {
			/*South*/
			tess.addVert(x * size + size1, y * size, z * size, tm1 + c * (ix[3]), -tm + c * (iy[3] + 1f), 0f, 0f, -1f);
			tess.addVert(x * size, y * size, z * size, -tm + c * (ix[3] + 1f), -tm + c * (iy[3] + 1f), 0f, 0f, -1f);
			tess.addVert(x * size, y * size + size1, z * size, -tm + c * (ix[3] + 1f), tm1 + c * (iy[3]), 0f, 0f, -1f);
			tess.addVert(x * size + size1, y * size + size1, z * size, tm1 + c * (ix[3]), tm1 + c * (iy[3]), 0f, 0f, -1f);
		}
			
		if(xp) {
			/*West*/
			tess.addVert(x * size + size1, y * size, z * size, -tm + c * (ix[4] + 1f), -tm + c * (iy[4] + 1f), 1f, 0f, 0f);
			tess.addVert(x * size + size1, y * size + size1, z * size, -tm + c * (ix[4] + 1f), tm1 + c * (iy[4]), 1f, 0f, 0f);
			tess.addVert(x * size + size1, y * size + size1, z * size + size1, tm1 + c * (ix[4]), tm1 + c * (iy[4]), 1f, 0f, 0f);
			tess.addVert(x * size + size1, y * size, z * size + size1, tm1 + c * (ix[4]), -tm + c * (iy[4] + 1f), 1f, 0f, 0f);
		}
		
		if(xm) {
			/*East*/
			tess.addVert(x * size, y * size + size1, z * size, tm1 + c * (ix[2]), tm1 + c * (iy[2]), -1f, 0f, 0f);
			tess.addVert(x * size, y * size, z * size, tm1 + c * (ix[2]), -tm + c * (iy[2] + 1f), -1f, 0f, 0f);
			tess.addVert(x * size, y * size, z * size + size1, -tm + c * (ix[2] + 1f), -tm + c * (iy[2] + 1f), -1f, 0f, 0f);
			tess.addVert(x * size, y * size + size1, z * size + size1, -tm + c * (ix[2] + 1f), tm1 + c * (iy[2]), -1f, 0f, 0f);
		}
		
		if(zp) {
			/*North*/
			tess.addVert(x * size, y * size, z * size + size1, tm1 + c * (ix[1]), -tm + c * (iy[1] + 1f), 0f, 0f, 1f);
			tess.addVert(x * size + size1, y * size, z * size + size1, -tm + c * (ix[1] + 1f), -tm + c * (iy[1] + 1f), 0f, 0f, 1f);
			tess.addVert(x * size + size1, y * size + size1, z * size + size1, -tm + c * (ix[1] + 1f), tm1 + c * (iy[1]), 0f, 0f, 1f);
			tess.addVert(x * size, y * size + size1, z * size + size1, tm1 + c * (ix[1]), tm1 + c * (iy[1]), 0f, 0f, 1f);
		}
		
		if(yp) {
			/*Top*/
			tess.addVert(x * size + size1, y * size + size1, z * size + size1, -tm + c * (ix[0] + 1f), -tm + c * (iy[0] + 1f), 0f, 1f, 0f);
			tess.addVert(x * size + size1, y * size + size1, z * size, -tm + c * (ix[0] + 1f), tm1 + c * (iy[0]), 0f, 1f, 0f);
			tess.addVert(x * size, y * size + size1, z * size, tm1 + c * (ix[0]), tm1 + c * (iy[0]), 0f, 1f, 0f);
			tess.addVert(x * size, y * size + size1, z * size + size1, tm1 + c * (ix[0]), -tm + c * (iy[0] + 1f), 0f, 1f, 0f);
		}
		
		if(ym) {
			/*Bottom*/
			tess.addVert(x * size + size1, y * size, z * size, tm1 + c * (ix[5]), -tm + c * (iy[5] + 1f), 0f, -1f, 0f);
			tess.addVert(x * size + size1, y * size, z * size + size1, tm1 + c * (ix[5]), tm1 + c * (iy[5]), 0f, -1f, 0f);
			tess.addVert(x * size, y * size, z * size + size1, -tm + c * (ix[5] + 1f), tm1 + c * (iy[5]), 0f, -1f, 0f);
			tess.addVert(x * size, y * size, z * size, -tm + c * (ix[5] + 1f), -tm + c * (iy[5] + 1f), 0f, -1f, 0f);
		}
	}
	
	public void onAdded(Level level, int x, int y, int z) {
	}
	
	public void click(Program program) {
	}
}
