package com.robtova.modern;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

public class Player {
	
	private boolean cursor_shown;
    private Vector3f direction = new Vector3f(), up = new Vector3f();
    private final Vector3f walkDirection = new Vector3f();
    private CharacterControl player;
    
    Camera camera;
    Program app;
    
    public Player(Program app) {
    	this.camera = app.getCamera();
    	this.app = app;
    	
    	CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(0.45f, 3.5f, 1);
        player = new CharacterControl(capsuleShape, 0.05f);
        player.setJumpSpeed(15f);
        player.setFallSpeed(20f);
        player.setGravity(20f);
        player.setMaxSlope(200f);
        player.setPhysicsLocation(new Vector3f(4f, 4f, 6f));
        
        app.bulletAppState.getPhysicsSpace().add(player);
    }
	
	public void update(Program program, float delta) {
		if(program.screen_control.getId().equals("end")) {
		Vector3f camDir = camera.getDirection().clone().setY(0f).normalize().multLocal(0.6f);
        Vector3f camLeft = camera.getLeft().clone().setY(0f).normalize().multLocal(0.4f);
        this.walkDirection.set(0, 0, 0);
        
        if (Program.input.getKeyPressed("Lefts")) {
            this.walkDirection.addLocal(camLeft);
        }
        if (Program.input.getKeyPressed("Rights")) {
            this.walkDirection.addLocal(camLeft.negate());
        }
        if (Program.input.getKeyPressed("Ups")) {
            this.walkDirection.addLocal(camDir);
        }
        if (Program.input.getKeyPressed("Downs")) {
            this.walkDirection.addLocal(camDir.negate());
        }
        if (Program.input.getKeyPressed("Jumps")) {
            Player.this.player.jump();
        } 
        if(Program.input.getKeyPressed("Esc") && !cursor_shown) {
        	app.getInputManager().setCursorVisible(true);
        	app.getInputManager().setMouseCursor(null);
        	camera.getDirection(direction);
        	camera.getUp(up);
        	cursor_shown = true;
        } else if(!Program.input.getKeyPressed("Esc") && cursor_shown) {
        	app.getInputManager().setCursorVisible(false);
    		cursor_shown = false;
        }
		}
		
        this.player.setWalkDirection(this.walkDirection.mult(0.15f * delta));
        camera.setLocation(player.getPhysicsLocation());
        if(cursor_shown) camera.lookAtDirection(direction, up);
	}
}
