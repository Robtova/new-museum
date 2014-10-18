package com.robtova.modern.level;

import com.jme3.collision.CollisionResult;
import com.jme3.math.Vector3f;
import com.robtova.modern.Program;

public abstract class CollisionBox {
	
	public float x, y, z, w, h, d;
	
	public CollisionBox(float x, float y, float z, float w, float h, float d) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.h = h;
		this.d = d;
	}
	
	public abstract void collide(Program program, CollisionResult result);
	
	public boolean intersect(float i, float j, float k) {
		return !(i <= x || i >= x + w || j <= y || j >= y + h || k <= z || k >= z + d);
	}
}
