package com.robtova.modern.level.collision;

import com.jme3.collision.CollisionResult;
import com.jme3.scene.Node;
import com.robtova.modern.Program;
import com.robtova.modern.screen.ScreenControl;

public class StandCollision extends NodeCollision {

	ScreenControl control;
	
	public StandCollision(ScreenControl control, Node node) {
		super(node);
		this.control = control;
	}
	
	public void collide(Program program, CollisionResult result) {
		program.addScreen(control);
	}
}
