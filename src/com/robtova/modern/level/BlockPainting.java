package com.robtova.modern.level;

import com.robtova.modern.Tesselator;

public class BlockPainting extends Block {
		
	public float w, h, d, p;
	public float u, v, ox, oy;
		
	public BlockPainting(int id, float u, float v, float w, float h, float d, float p, float ox, float oy) {
		super(id, wall.icon[0]);
		this.w = w;
		this.h = h;
		this.d = d;
		setSolid(false);
		this.u = u;
		this.v = v;
		this.p = p;
		this.ox = ox;
		this.oy = oy;
	}
	
	public void render_super(Tesselator tess, int i, int j, int k, Level level) {
		super.render(tess, i, j, k, level);
	}

	public void render(Tesselator tess, int i, int j, int k, Level level) {
		boolean west = level.getBlock(i - 1, j, k).solid, east = level.getBlock(i + 1, j, k).solid;
		boolean north = level.getBlock(i, j, k - 1).solid, south = level.getBlock(i, j, k + 1).solid;
			
		float x = i;
		float y = Level.player_eye + oy;
		float z = k;

		if(west || east) {
			if(east) {
				z += ox;
			} else {
				z -= ox + ((w / c) - 1);
			}
		}
		if(north || south) {
			if(north) {
				x += ox;
			} else {
				x -= ox + ((w / c) - 1);
			}
		}
			
		if(west) x += 0f;
		if(east) x += 1f - d;
		if(north) z += 0f;
		if(south) z += 1f - d;
		
		if(north) {
			render_north(tess, x, y, z);
		} else if(south) {
			render_south(tess, x, y, z);
		} else if(west) {
			render_west(tess, x, y, z);
		} else if(east) {
			render_east(tess, x, y, z);
		}
	}
		
	public void render_north(Tesselator tess, float x, float y, float z) {
		float w1 = w / c;
		float h1 = h / c;
		float d1 = d;

		/*West*/
		tess.addVert(x * size + w1, y * size, z * size, tm1 + u + (w - p), -tm + v + h, 1f, 0f, 0f);
		tess.addVert(x * size + w1, y * size + h1, z * size, tm1 + u + (w - p), tm1 + v, 1f, 0f, 0f);
		tess.addVert(x * size + w1, y * size + h1, z * size + d1, -tm + u + w, tm1 + v, 1f, 0f, 0f);
		tess.addVert(x * size + w1, y * size, z * size + d1, -tm + u + w, -tm + v + h, 1f, 0f, 0f);
		
		/*East*/
		tess.addVert(x * size, y * size + h1, z * size, -tm + u + p, tm1 + v, -1f, 0f, 0f);
		tess.addVert(x * size, y * size, z * size, -tm + u + p, -tm + v + h, -1f, 0f, 0f);
		tess.addVert(x * size, y * size, z * size + d1, tm1 + u, -tm + v + h, -1f, 0f, 0f);
		tess.addVert(x * size, y * size + h1, z * size + d1, tm1 + u, tm1 + v, -1f, 0f, 0f);

		/*North*/
		tess.addVert(x * size, y * size, z * size + d1, tm1 + u, -tm + v + h, 0f, 0f, 1f);
		tess.addVert(x * size + w1, y * size, z * size + d1, -tm + u + w, -tm + v + h, 0f, 0f, 1f);
		tess.addVert(x * size + w1, y * size + h1, z * size + d1, -tm + u + w, tm1 + v, 0f, 0f, 1f);
		tess.addVert(x * size, y * size + h1, z * size + d1, tm1 + u, tm1 + v, 0f, 0f, 1f);

		/*Top*/
		tess.addVert(x * size + w1, y * size + h1, z * size + d1, -tm + u + w, tm1 + v, 0f, 1f, 0f);
		tess.addVert(x * size + w1, y * size + h1, z * size, -tm + u + w, -tm + v + p, 0f, 1f, 0f);
		tess.addVert(x * size, y * size + h1, z * size, tm1 + u, -tm + v + p, 0f, 1f, 0f);
		tess.addVert(x * size, y * size + h1, z * size + d1, tm1 + u, tm1 + v, 0f, 1f, 0f);

		/*Bottom*/
		tess.addVert(x * size + w1, y * size, z * size, -tm + u + w, tm1 + v + (h - p), 0f, -1f, 0f);
		tess.addVert(x * size + w1, y * size, z * size + d1, -tm + u + w, -tm + v + h, 0f, -1f, 0f);
		tess.addVert(x * size, y * size, z * size + d1, tm1 + u, -tm + v + h, 0f, -1f, 0f);
		tess.addVert(x * size, y * size, z * size, tm1 + u, tm1 + v + (h - p), 0f, -1f, 0f);
	}
		
	public void render_south(Tesselator tess, float x, float y, float z) {
		float w1 = w / c;
		float h1 = h / c;
		float d1 = d;

		/*West*/
		tess.addVert(x * size + w1, y * size, z * size, tm1 + u, -tm + v + h, 1f, 0f, 0f);
		tess.addVert(x * size + w1, y * size + h1, z * size, tm1 + u, tm1 + v, 1f, 0f, 0f);
		tess.addVert(x * size + w1, y * size + h1, z * size + d1, -tm + u + p, tm1 + v, 1f, 0f, 0f);
		tess.addVert(x * size + w1, y * size, z * size + d1, -tm + u + p, -tm + v + h, 1f, 0f, 0f);

		/*East*/
		tess.addVert(x * size, y * size + h1, z * size, -tm + u + w, tm1 + v, -1f, 0f, 0f);
		tess.addVert(x * size, y * size, z * size, -tm + u + w, -tm + v + h, -1f, 0f, 0f);
		tess.addVert(x * size, y * size, z * size + d1, tm1 + u + (w - p), -tm + v + h, -1f, 0f, 0f);
		tess.addVert(x * size, y * size + h1, z * size + d1, tm1 + u + (w - p), tm1 + v, -1f, 0f, 0f);

		/*South*/
		tess.addVert(x * size + w1, y * size, z * size, tm1 + u, -tm + v + h, 0f, 0f, -1f);
		tess.addVert(x * size, y * size, z * size, -tm + u + w, -tm + v + h, 0f, 0f, -1f);
		tess.addVert(x * size, y * size + h1, z * size, -tm + u + w, tm1 + v, 0f, 0f, -1f);
		tess.addVert(x * size + w1, y * size + h1, z * size, tm1 + u, tm1 + v, 0f, 0f, -1f);

		/*Top*/
		tess.addVert(x * size + w1, y * size + h1, z * size + d1, tm1 + u, -tm + v + p, 0f, 1f, 0f);
		tess.addVert(x * size + w1, y * size + h1, z * size, tm1 + u, tm1 + v, 0f, 1f, 0f);
		tess.addVert(x * size, y * size + h1, z * size, -tm + u + w, tm1 + v, 0f, 1f, 0f);
		tess.addVert(x * size, y * size + h1, z * size + d1, -tm + u + w, -tm + v + p, 0f, 1f, 0f);

		/*Bottom*/
		tess.addVert(x * size + w1, y * size, z * size, tm1 + u, -tm + v + h, 0f, -1f, 0f);
		tess.addVert(x * size + w1, y * size, z * size + d1, tm1 + u, tm1 + v + (h - p), 0f, -1f, 0f);
		tess.addVert(x * size, y * size, z * size + d1, -tm + u + w, tm1 + v + (h - p), 0f, -1f, 0f);
		tess.addVert(x * size, y * size, z * size, -tm + u + w, -tm + v + h, 0f, -1f, 0f);
	}
		
	public void render_west(Tesselator tess, float x, float y, float z) {
		float w1 = d;
		float h1 = h / c;
		float d1 = w / c;

		/*South*/
		tess.addVert(x * size + w1, y * size, z * size, -tm + u + w, -tm + v + h, 0f, 0f, -1f);
		tess.addVert(x * size, y * size, z * size, tm1 + u + (w - p), -tm + v + h, 0f, 0f, -1f);
		tess.addVert(x * size, y * size + h1, z * size, tm1 + u + (w - p), tm1 + v, 0f, 0f, -1f);
		tess.addVert(x * size + w1, y * size + h1, z * size, -tm + u + w, tm1 + v, 0f, 0f, -1f);

		/*West*/
		tess.addVert(x * size + w1, y * size, z * size, -tm + u + w, -tm + v + h, 1f, 0f, 0f);
		tess.addVert(x * size + w1, y * size + h1, z * size, -tm + u + w, tm1 + v, 1f, 0f, 0f);
		tess.addVert(x * size + w1, y * size + h1, z * size + d1, tm1 + u, tm1 + v, 1f, 0f, 0f);
		tess.addVert(x * size + w1, y * size, z * size + d1, tm1 + u, -tm + v + h, 1f, 0f, 0f);

		/*North*/
		tess.addVert(x * size, y * size, z * size + d1, -tm + u + p, -tm + v + h, 0f, 0f, 1f);
		tess.addVert(x * size + w1, y * size, z * size + d1, tm1 + u, -tm + v + h, 0f, 0f, 1f);
		tess.addVert(x * size + w1, y * size + h1, z * size + d1, tm1 + u, tm1 + v, 0f, 0f, 1f);
		tess.addVert(x * size, y * size + h1, z * size + d1, -tm + u + p, tm1 + v, 0f, 0f, 1f);

		/*Top*/
		tess.addVert(x * size + w1, y * size + h1, z * size + d1, tm1 + u, tm1 + v, 0f, 1f, 0f);
		tess.addVert(x * size + w1, y * size + h1, z * size, -tm + u + w, tm1 + v, 0f, 1f, 0f);
		tess.addVert(x * size, y * size + h1, z * size, -tm + u + w, -tm + v + p, 0f, 1f, 0f);
		tess.addVert(x * size, y * size + h1, z * size + d1, tm1 + u, -tm + v + p, 0f, 1f, 0f);

		/*Bottom*/
		tess.addVert(x * size + w1, y * size, z * size, -tm + u + w, -tm + v + h, 0f, -1f, 0f);
		tess.addVert(x * size + w1, y * size, z * size + d1, tm1 + u, -tm + v + h, 0f, -1f, 0f);
		tess.addVert(x * size, y * size, z * size + d1, tm1 + u, tm1 + v + (h - p), 0f, -1f, 0f);
		tess.addVert(x * size, y * size, z * size, -tm + u + w, tm1 + v + (h - p), 0f, -1f, 0f);
	}
		
	public void render_east(Tesselator tess, float x, float y, float z) {
		float w1 = d;
		float h1 = h / c;
		float d1 = w / c;

		/*South*/
		tess.addVert(x * size + w1, y * size, z * size, -tm + u + p, -tm + v + h, 0f, 0f, -1f);
		tess.addVert(x * size, y * size, z * size, tm1 + u, -tm + v + h, 0f, 0f, -1f);
		tess.addVert(x * size, y * size + h1, z * size, tm1 + u, tm1 + v, 0f, 0f, -1f);
		tess.addVert(x * size + w1, y * size + h1, z * size, -tm + u + p, tm1 + v, 0f, 0f, -1f);

		/*East*/
		tess.addVert(x * size, y * size + h1, z * size, tm1 + u, tm1 + v, -1f, 0f, 0f);
		tess.addVert(x * size, y * size, z * size, tm1 + u, -tm + v + h, -1f, 0f, 0f);
		tess.addVert(x * size, y * size, z * size + d1, -tm + u + w, -tm + v + h, -1f, 0f, 0f);
		tess.addVert(x * size, y * size + h1, z * size + d1, -tm + u + w, tm1 + v, -1f, 0f, 0f);
			
		/*North*/
		tess.addVert(x * size, y * size, z * size + d1, -tm + u + w, -tm + v + h, 0f, 0f, 1f);
		tess.addVert(x * size + w1, y * size, z * size + d1, tm1 + u + (w - p), -tm + v + h, 0f, 0f, 1f);
		tess.addVert(x * size + w1, y * size + h1, z * size + d1, tm1 + u + (w - p), tm1 + v, 0f, 0f, 1f);
		tess.addVert(x * size, y * size + h1, z * size + d1, -tm + u + w, tm1 + v, 0f, 0f, 1f);

		/*Top*/
		tess.addVert(x * size + w1, y * size + h1, z * size + d1, -tm + u + w, -tm + v + p, 0f, 1f, 0f);
		tess.addVert(x * size + w1, y * size + h1, z * size, tm1 + u, -tm + v + p, 0f, 1f, 0f);
		tess.addVert(x * size, y * size + h1, z * size, tm1 + u, tm1 + v, 0f, 1f, 0f);
		tess.addVert(x * size, y * size + h1, z * size + d1, -tm + u + w, tm1 + v, 0f, 1f, 0f);

		/*Bottom*/
		tess.addVert(x * size + w1, y * size, z * size, tm1 + u, tm1 + v + (h - p), 0f, -1f, 0f);
		tess.addVert(x * size + w1, y * size, z * size + d1, -tm + u + w, tm1 + v + (h - p), 0f, -1f, 0f);
		tess.addVert(x * size, y * size, z * size + d1, -tm + u + w, -tm + v + h, 0f, -1f, 0f);
		tess.addVert(x * size, y * size, z * size, tm1 + u, -tm + v + h, 0f, -1f, 0f);
	}
}