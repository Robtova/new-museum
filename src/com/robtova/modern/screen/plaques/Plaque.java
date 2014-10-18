package com.robtova.modern.screen.plaques;

import com.robtova.modern.screen.ScreenControl;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;

public class Plaque extends ScreenControl {

	public String artist, born, title, year, medium, script;
	public int w, h;
	
	public Plaque() {
		super("plaque");
	}

	@Override
	public void onLoad(Nifty nifty, Screen screen) {
		Element panel = screen.findElementByName("panel");
		panel.setConstraintHeight(new SizeValue(h + "px"));
		panel.setConstraintWidth(new SizeValue(w + "px"));
		
		Element panel2 = screen.findElementByName("panel2");
		panel2.setConstraintHeight(new SizeValue((h - 80) + "px"));
		panel2.setConstraintWidth(new SizeValue((w - 60) + "px"));
		
		screen.findElementByName("line").setConstraintWidth(new SizeValue("100%"));
		screen.findElementByName("line2").setConstraintWidth(new SizeValue("100%"));
		
		screen.findElementByName("artist").getRenderer(TextRenderer.class).setText(artist);
		screen.findElementByName("born").getRenderer(TextRenderer.class).setText(born);
		
		screen.findElementByName("title").getRenderer(TextRenderer.class).setText(title);
		screen.findElementByName("year").getRenderer(TextRenderer.class).setText(year);
		screen.findElementByName("medium").getRenderer(TextRenderer.class).setText(medium);
		
		screen.findElementByName("script").getRenderer(TextRenderer.class).setText(script);
		
		screen.findElementByName("artist").setConstraintWidth(new SizeValue("100%"));
		screen.findElementByName("born").setConstraintWidth(new SizeValue("100%"));
		screen.findElementByName("title").setConstraintWidth(new SizeValue("100%"));
		screen.findElementByName("year").setConstraintWidth(new SizeValue("100%"));
		screen.findElementByName("medium").setConstraintWidth(new SizeValue("100%"));
		screen.findElementByName("script").setConstraintWidth(new SizeValue("100%"));
	}
}
