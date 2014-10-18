package com.robtova.modern;

import java.util.HashMap;
import java.util.Set;

import com.jme3.app.Application;
import com.jme3.input.InputManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;

public class Input implements ActionListener {
	private HashMap<String, Boolean> keys = new HashMap<String, Boolean>(), keys_were_down = new HashMap<String, Boolean>();
	InputManager inputManager;
	
	public Input(Application app) {
		this.inputManager = app.getInputManager();
	}
	
	public boolean getKeyPressed(String s) {
		if(!keys.containsKey(s)) return false;
		return keys.get(s);
	}
	
	public boolean getKeyWasDown(String s) {
		if(!keys_were_down.containsKey(s)) return false;
		return keys_were_down.get(s);
	}
	
	public void addKey(String s, int... keys) {
    	KeyTrigger[] ks = new KeyTrigger[keys.length];
    	for(int i = 0; i < keys.length; i++) ks[i] = new KeyTrigger(keys[i]);
    	this.inputManager.addMapping(s, ks);
        this.inputManager.addListener(this, s);
        
        this.keys.put(s, false);
        this.keys_were_down.put(s, false);
    }
	
	public void addMouse(String s, int mouse) {
    	this.inputManager.addMapping(s, new MouseButtonTrigger(mouse));
        this.inputManager.addListener(this, s);
        
        this.keys.put(s, false);
        this.keys_were_down.put(s, false);
    }
	
	@Override
    public void onAction(final String name, final boolean keyPressed, final float tpf) {
    	keys.put(name, keyPressed);
    	if(!keyPressed) keys_were_down.put(name, true);
    }

	public void update() {
		Set<String> s = this.keys_were_down.keySet();
        
        for(String s1 : s) {
        	this.keys_were_down.put(s1, false);
        }
	}
}
