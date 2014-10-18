package com.robtova.modern.level;

import com.robtova.modern.Tesselator;

public class BlockWall extends Block {

	public BlockWall(int id, int ico) {
		super(id, ico);
	}
	
	private static float[] white = {1f,1f,1f,1f}, shade = {0.95f, 0.95f, 0.95f, 1f};
	
	public void render(Tesselator tess, int i, int j, int k, Level level) {
		if(icon[0] < 0) return;

		boolean xm = !level.getBlock(i - 1, j, k).solid, xp = !level.getBlock(i + 1, j, k).solid;
		boolean ym = !level.getBlock(i, j - 1, k).solid, yp = !level.getBlock(i, j + 1, k).solid;
		boolean zm = !level.getBlock(i, j, k - 1).solid, zp = !level.getBlock(i, j, k + 1).solid;
		
		float size1 = size;
		
		float x = i;
		float y = j;
		float z = k;
		
		boolean a1 = level.getBlock(i - 1, j + 1, k).solid, a2 = level.getBlock(i - 1, j - 1, k).solid;
		boolean a3 = level.getBlock(i - 1, j, k + 1).solid, a4 = level.getBlock(i - 1, j, k - 1).solid;
		boolean a5 = level.getBlock(i + 1, j + 1, k).solid, a6 = level.getBlock(i + 1, j - 1, k).solid;
		boolean a7 = level.getBlock(i + 1, j, k + 1).solid, a8 = level.getBlock(i + 1, j, k - 1).solid;
		boolean a9 = level.getBlock(i, j + 1, k + 1).solid, a10 = level.getBlock(i, j - 1, k - 1).solid;
		boolean a11 = level.getBlock(i, j - 1, k + 1).solid, a12 = level.getBlock(i, j + 1, k - 1).solid;
		
		if(yp) {
			/*Top*/
			tess.addVert(x * size + size1, y * size + size1, z * size + size1, -tm + c * (ix[0] + 1f), -tm + c * (iy[0] + 1f), 0f, 1f, 0f, a9 || a5? shade : white);
			tess.addVert(x * size + size1, y * size + size1, z * size, -tm + c * (ix[0] + 1f), tm1 + c * (iy[0]), 0f, 1f, 0f, a12 || a5? shade : white);
			tess.addVert(x * size, y * size + size1, z * size, tm1 + c * (ix[0]), tm1 + c * (iy[0]), 0f, 1f, 0f, a12 || a1? shade : white);
			tess.addVert(x * size, y * size + size1, z * size + size1, tm1 + c * (ix[0]), -tm + c * (iy[0] + 1f), 0f, 1f, 0f, a9 || a1? shade : white);
		}
		
		if(ym) {
			/*Bottom*/
			tess.addVert(x * size + size1, y * size, z * size, tm1 + c * (ix[5]), -tm + c * (iy[5] + 1f), 0f, -1f, 0f, a10 || a6? shade : white);
			tess.addVert(x * size + size1, y * size, z * size + size1, tm1 + c * (ix[5]), tm1 + c * (iy[5]), 0f, -1f, 0f, a11 || a6? shade : white);
			tess.addVert(x * size, y * size, z * size + size1, -tm + c * (ix[5] + 1f), tm1 + c * (iy[5]), 0f, -1f, 0f, a11 || a2? shade : white);
			tess.addVert(x * size, y * size, z * size, -tm + c * (ix[5] + 1f), -tm + c * (iy[5] + 1f), 0f, -1f, 0f, a10 || a2? shade : white);
		}

		if(zm) {
			/*South*/
			tess.addVert(x * size + size1, y * size, z * size, tm1 + c * (ix[3]), -tm + c * (iy[3] + 1f), 0f, 0f, -1f, a10 || a8? shade : white);
			tess.addVert(x * size, y * size, z * size, -tm + c * (ix[3] + 1f), -tm + c * (iy[3] + 1f), 0f, 0f, -1f, a10 || a4? shade : white);
			tess.addVert(x * size, y * size + size1, z * size, -tm + c * (ix[3] + 1f), tm1 + c * (iy[3]), 0f, 0f, -1f, a12 || a4? shade : white);
			tess.addVert(x * size + size1, y * size + size1, z * size, tm1 + c * (ix[3]), tm1 + c * (iy[3]), 0f, 0f, -1f, a12 || a8? shade : white);
		}
			
		if(xp) {
			/*West*/
			tess.addVert(x * size + size1, y * size, z * size, -tm + c * (ix[4] + 1f), -tm + c * (iy[4] + 1f), 1f, 0f, 0f, a6 || a8? shade : white);
			tess.addVert(x * size + size1, y * size + size1, z * size, -tm + c * (ix[4] + 1f), tm1 + c * (iy[4]), 1f, 0f, 0f, a5 || a8? shade : white);
			tess.addVert(x * size + size1, y * size + size1, z * size + size1, tm1 + c * (ix[4]), tm1 + c * (iy[4]), 1f, 0f, 0f, a5 || a7? shade : white);
			tess.addVert(x * size + size1, y * size, z * size + size1, tm1 + c * (ix[4]), -tm + c * (iy[4] + 1f), 1f, 0f, 0f, a6 || a7? shade : white);
		}
		
		if(xm) {
			/*East*/
			tess.addVert(x * size, y * size + size1, z * size, tm1 + c * (ix[2]), tm1 + c * (iy[2]), -1f, 0f, 0f, a1 || a4? shade : white);
			tess.addVert(x * size, y * size, z * size, tm1 + c * (ix[2]), -tm + c * (iy[2] + 1f), -1f, 0f, 0f, a2 || a4? shade : white);
			tess.addVert(x * size, y * size, z * size + size1, -tm + c * (ix[2] + 1f), -tm + c * (iy[2] + 1f), -1f, 0f, 0f, a2 || a3? shade : white);
			tess.addVert(x * size, y * size + size1, z * size + size1, -tm + c * (ix[2] + 1f), tm1 + c * (iy[2]), -1f, 0f, 0f, a1 || a3? shade : white);
		}
		
		if(zp) {
			/*North*/
			tess.addVert(x * size, y * size, z * size + size1, tm1 + c * (ix[1]), -tm + c * (iy[1] + 1f), 0f, 0f, 1f, a11 || a3? shade : white);
			tess.addVert(x * size + size1, y * size, z * size + size1, -tm + c * (ix[1] + 1f), -tm + c * (iy[1] + 1f), 0f, 0f, 1f, a11 || a7? shade : white);
			tess.addVert(x * size + size1, y * size + size1, z * size + size1, -tm + c * (ix[1] + 1f), tm1 + c * (iy[1]), 0f, 0f, 1f, a9 || a7? shade : white);
			tess.addVert(x * size, y * size + size1, z * size + size1, tm1 + c * (ix[1]), tm1 + c * (iy[1]), 0f, 0f, 1f, a9 || a3? shade : white);
		}
	}
}