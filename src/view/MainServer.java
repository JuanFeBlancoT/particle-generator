package view;

import java.util.ArrayList;

import model.ParticleGroup;
import processing.core.PApplet;
import com.google.gson.Gson;

public class MainServer extends PApplet{

	public static void main(String[] args) {
		PApplet.main("view.MainServer");
	}
	
	//attributes
	public final int WIDTH = 800;
	public final int HEIGHT = 600;
	
	//relations
	ArrayList<ParticleGroup> pgs; 
	Communication coms;
	Gson json;
	
	public void settings() {
		size(WIDTH,HEIGHT);
	}
	
	public void setup() {
		rectMode(CENTER);
		pgs = new ArrayList<>();
		json = new Gson();
		coms = new Communication(this);
		coms.start();
		
	}

	public void draw() {
		background(40);
		for (int i = 0; i < pgs.size(); i++) {
			pgs.get(i).drawParticleGroup();
		}
		mouseHover();
		deleteAll();
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
	}
	
	public void deleteAll() {
			pgs.clear();		
	}
	
	/*public void createParticleGroup(String name, int num, int posXo, int posYo, int type) {
		ParticleGroup pgx = new ParticleGroup(name, num, posXo, posYo, type, this, WIDTH, HEIGHT);
		pgs.add(pgx);
	}*/

	public void notifyMessage(String message) {
		ParticleGroup pgx = json.fromJson(message, ParticleGroup.class);
		pgs.add(pgx);
	}

}
