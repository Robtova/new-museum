package com.robtova.modern.screen;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;

public class ScreenControl {
	
	private String id;
	
	public ScreenControl(String screen) {
		this.id = screen;
	}
	
	public String getId() {
		return id;
	}

	public void onLoad(Nifty nifty, Screen screen) {
		
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
