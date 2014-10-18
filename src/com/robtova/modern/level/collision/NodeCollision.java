package com.robtova.modern.level.collision;

import com.jme3.collision.CollisionResult;
import com.jme3.scene.Node;
import com.robtova.modern.Program;

public class NodeCollision {
	public Node node;
	
	public NodeCollision(Node node) {
		this.node = node;
	}
	
	public void collide(Program program, CollisionResult result) {
		program.Text(node.getName());
	}
}
