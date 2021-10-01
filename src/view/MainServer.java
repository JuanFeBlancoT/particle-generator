package view;

import processing.core.PApplet;

public class MainServer extends PApplet{

	public static void main(String[] args) {
		PApplet.main("view.MainServer");
	}
	
	//attributes
	public final int width = 800;
	public final int height = 600;
	
	public void settings() {
		size(width,height);
	}
	
	public void setup() {
	}

	public void draw() {
		background(40);
	}

}
