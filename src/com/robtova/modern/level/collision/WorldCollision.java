package com.robtova.modern.level.collision;

import com.jme3.collision.CollisionResult;
import com.robtova.modern.Program;
import com.robtova.modern.level.CollisionBox;

public class WorldCollision extends NodeCollision {
	
	public WorldCollision() {
		super(null);
	}
	
	public void collide(Program program, CollisionResult result) {
		for(int i = 0; i < program.level.collisions.size(); i++) {
			CollisionBox box = program.level.collisions.get(i);
			if(box.intersect(result.getContactPoint().x, result.getContactPoint().y, result.getContactPoint().z)) {
				box.collide(program, result);
			}
		}
	}
}
