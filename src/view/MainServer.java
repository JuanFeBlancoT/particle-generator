package view;

import model.ParticleGroup;
import processing.core.PApplet;

public class MainServer extends PApplet{

	public static void main(String[] args) {
		PApplet.main("view.MainServer");
	}
	
	//attributes
	public final int WIDTH = 800;
	public final int HEIGHT = 600;
	ParticleGroup pg;
	
	public void settings() {
		size(WIDTH,HEIGHT);
	}
	
	public void setup() {
		rectMode(CENTER);
		pg = new ParticleGroup("a", 4, 400, 300, 1, this, WIDTH, HEIGHT);
	}

	public void draw() {
		background(40);
		pg.drawParticleGroup();
		/*rect(780, 300, 50, 5);
		rect(780, 370, 50, 2);*/
	}

}
