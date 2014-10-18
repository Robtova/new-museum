package com.robtova.modern.level;

import com.jme3.collision.CollisionResult;
import com.robtova.modern.Program;
import com.robtova.modern.Tesselator;
import com.robtova.modern.screen.ScreenControl;

public class BlockPlaque extends BlockPainting {

	public int dir;
	public ScreenControl screen;
	
	public BlockPlaque(ScreenControl screen, int id, int icon, int dir, float u, float v, float w, float h, float ox, float oy) {
		super(id, u, v, w, h, 0.01f, tm, ox, oy);
		this.dir = dir;
		this.setIcon(icon, 1, dir);
		this.screen = screen;
	}
	
	public void click(Program program) {
		program.addScreen(screen);
	}

	public void render(Tesselator tess, int i, int j, int k, Level level) {
		//super.render_super(tess, i, j, k, level);
		Block.wall.render(tess, i, j, k, level);
		
		if(dir == EAST) i -= 1;
		else if(dir == WEST) i += 1;
		else if(dir == NORTH) k += 1;
		else if(dir == SOUTH) k -= 1;

		float x = i;
		float y = Level.plaque_level;
		float z = k;

		if(dir == WEST || dir == EAST) {
			if(dir == EAST) {
				z += ox;
			} else {
				z -= ox + ((w / c) - 1);
			}
		}
		if(dir == NORTH || dir == SOUTH) {
			if(dir == NORTH) {
				x += ox;
			} else {
				x -= ox + ((w / c) - 1);
			}
		}
			
		if(dir == WEST) x += 0f;
		if(dir == EAST) x += 1f - d;
		if(dir == NORTH) z += 0f;
		if(dir == SOUTH) z += 1f - d;
		
		if(dir == NORTH) {
			float x1 = x + ((w / c) / 2f) - 0.5f;
			float y1 = y + ((h / c) / 2f) - 0.5f;
			float z1 = z + tm;
			tess.addVert(x1 * size, y1 * size, z1 * size, tm1 + c * (ix[1]), -tm + c * (iy[1] + 1f), 0f, 0f, 1f);
			tess.addVert(x1 * size + size, y1 * size, z1 * size, -tm + c * (ix[1] + 1f), -tm + c * (iy[1] + 1f), 0f, 0f, 1f);
			tess.addVert(x1 * size + size, y1 * size + size, z1 * size, -tm + c * (ix[1] + 1f), tm1 + c * (iy[1]), 0f, 0f, 1f);
			tess.addVert(x1 * size, y1 * size + size, z1 * size, tm1 + c * (ix[1]), tm1 + c * (iy[1]), 0f, 0f, 1f);
			render_north(tess, x, y, z);
		} else if(dir == SOUTH) {
			float x1 = x + ((w / c) / 2f) - 0.5f;
			float y1 = y + ((h / c) / 2f) - 0.5f;
			float z1 = z + d - tm;
			tess.addVert(x1 * size + size, y1 * size, z1 * size, tm1 + c * (ix[3]), -tm + c * (iy[3] + 1f), 0f, 0f, -1f);
			tess.addVert(x1 * size, y1 * size, z1 * size, -tm + c * (ix[3] + 1f), -tm + c * (iy[3] + 1f), 0f, 0f, -1f);
			tess.addVert(x1 * size, y1 * size + size, z1 * size, -tm + c * (ix[3] + 1f), tm1 + c * (iy[3]), 0f, 0f, -1f);
			tess.addVert(x1 * size + size, y1 * size + size, z1 * size, tm1 + c * (ix[3]), tm1 + c * (iy[3]), 0f, 0f, -1f);
			render_south(tess, x, y, z);
		} else if(dir == WEST) {
			float x1 = x + tm;
			float y1 = y + ((h / c) / 2f) - 0.5f;
			float z1 = z + ((w / c) / 2f) - 0.5f;
			tess.addVert(x1 * size, y1 * size, z1 * size, -tm + c * (ix[4] + 1f), -tm + c * (iy[4] + 1f), 1f, 0f, 0f);
			tess.addVert(x1 * size, y1 * size + size, z1 * size, -tm + c * (ix[4] + 1f), tm1 + c * (iy[4]), 1f, 0f, 0f);
			tess.addVert(x1 * size, y1 * size + size, z1 * size + size, tm1 + c * (ix[4]), tm1 + c * (iy[4]), 1f, 0f, 0f);
			tess.addVert(x1 * size, y1 * size, z1 * size + size, tm1 + c * (ix[4]), -tm + c * (iy[4] + 1f), 1f, 0f, 0f);
			render_west(tess, x, y, z);
		} else if(dir == EAST) {
			float x1 = x + d - tm;
			float y1 = y + ((h / c) / 2f) - 0.5f;
			float z1 = z + ((w / c) / 2f) - 0.5f;
			tess.addVert(x1 * size, y1 * size + size, z1 * size, tm1 + c * (ix[2]), tm1 + c * (iy[2]), -1f, 0f, 0f);
			tess.addVert(x1 * size, y1 * size, z1 * size, tm1 + c * (ix[2]), -tm + c * (iy[2] + 1f), -1f, 0f, 0f);
			tess.addVert(x1 * size, y1 * size, z1 * size + size, -tm + c * (ix[2] + 1f), -tm + c * (iy[2] + 1f), -1f, 0f, 0f);
			tess.addVert(x1 * size, y1 * size + size, z1 * size + size, -tm + c * (ix[2] + 1f), tm1 + c * (iy[2]), -1f, 0f, 0f);
			render_east(tess, x, y, z);
		}
	}
	
	public void onAdded(Level level, int i, int j, int k) {
		if(dir == EAST) i -= 1;
		else if(dir == WEST) i += 1;
		else if(dir == NORTH) k += 1;
		else if(dir == SOUTH) k -= 1;

		float x = i;
		float y = Level.plaque_level;
		float z = k;

		if(dir == WEST || dir == EAST) {
			if(dir == EAST) {
				z += ox;
			} else {
				z -= ox + ((w / c) - 1);
			}
		}
		if(dir == NORTH || dir == SOUTH) {
			if(dir == NORTH) {
				x += ox;
			} else {
				x -= ox + ((w / c) - 1);
			}
		}
			
		if(dir == WEST) x += 0f;
		if(dir == EAST) x += 1f - d;
		if(dir == NORTH) z += 0f;
		if(dir == SOUTH) z += 1f - d;
		
		x -= tm;
		y -= tm;
		z -= tm;
		
		if(dir == NORTH || dir == SOUTH) {
			level.collisions.add(new CollisionBox(x, y, z, w / c + 2 * tm, h / c + 2 * tm, d + 2 * tm) {
				@Override
				public void collide(Program program, CollisionResult result) {
					program.addScreen(screen);
				}
			});
		} else if(dir == WEST || dir == EAST) {
			level.collisions.add(new CollisionBox(x, y, z, d + 2 * tm, h / c + 2 * tm, w / c + 2 * tm) {
				@Override
				public void collide(Program program, CollisionResult result) {
					program.addScreen(screen);
				}
			});
		}
	}
}