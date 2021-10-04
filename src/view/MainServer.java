package view;

import java.util.ArrayList;

import model.ParticleGroup;
import processing.core.PApplet;

public class MainServer extends PApplet{

	public static void main(String[] args) {
		PApplet.main("view.MainServer");
	}
	
	//attributes
	public final int WIDTH = 800;
	public final int HEIGHT = 600;
	ArrayList<ParticleGroup> pgs = new ArrayList<>();
	
	public void settings() {
		size(WIDTH,HEIGHT);
	}
	
	public void setup() {
		rectMode(CENTER);
		ParticleGroup pg1 = new ParticleGroup("group1", 10, 400, 300, 1, this, WIDTH, HEIGHT);
		ParticleGroup pg2 = new ParticleGroup("group2", 7, 200, 500, 2, this, WIDTH, HEIGHT);
		ParticleGroup pg3 = new ParticleGroup("group3", 3, 100, 200, 3, this, WIDTH, HEIGHT);
		pgs.add(pg1);
		pgs.add(pg2);
		pgs.add(pg3);
	}

	public void draw() {
		background(40);
		for (int i = 0; i < pgs.size(); i++) {
			pgs.get(i).drawParticleGroup();
		}
		mouseHover();
	}
	
	private void mouseHover() {
		
		for (int i = 0; i < pgs.size(); i++) {
			for (int j = 0; j < pgs.get(i).getParticles().size(); j++) {
				if(mouseX > pgs.get(i).getParticles().get(j).getRealPosX()-pgs.get(i).getParticles().get(j).SIZE
						&& mouseX < pgs.get(i).getParticles().get(j).getRealPosX()+pgs.get(i).getParticles().get(j).SIZE 
						&& mouseY > pgs.get(i).getParticles().get(j).getRealPosY()-pgs.get(i).getParticles().get(j).SIZE
						&& mouseY < pgs.get(i).getParticles().get(j).getRealPosY()+pgs.get(i).getParticles().get(j).SIZE ) {
					pgs.get(i).getParticles().get(j).setCanMove(false);
				}else {
					pgs.get(i).getParticles().get(j).setCanMove(true);
				}
			}
		}
		/*for (int i = 0; i < pg.getParticles().size(); i++) {
			if(mouseX > pg.getParticles().get(i).getRealPosX()-pg.getParticles().get(i).SIZE
					&& mouseX < pg.getParticles().get(i).getRealPosX()+pg.getParticles().get(i).SIZE 
					&& mouseY > pg.getParticles().get(i).getRealPosY()-pg.getParticles().get(i).SIZE
					&& mouseY < pg.getParticles().get(i).getRealPosY()+pg.getParticles().get(i).SIZE ) {
				pg.getParticles().get(i).setCanMove(false);
				
			}else {
				pg.getParticles().get(i).setCanMove(true);
			}
		}*/
	}

}
