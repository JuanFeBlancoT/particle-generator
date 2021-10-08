package view;

import java.util.ArrayList;
import java.util.Iterator;

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
		coms = new Communication(this);
		coms.start();
		/*ParticleGroup pgx = new ParticleGroup("AAA", 1, 200, 300, 2, WIDTH, HEIGHT);
		pgs.add(pgx);*/
	}

	public void draw() {
		background(40);
				
		for (int i = 0; i < pgs.size(); i++) {
			for (int j = 0; j < pgs.get(i).getParticles().size(); j++) {
				
				fill(pgs.get(i).getParticles().get(j).getR(), pgs.get(i).getParticles().get(j).getG(), pgs.get(i).getParticles().get(j).getB());
				pushMatrix();
				translate(pgs.get(i).getParticles().get(j).getPosXOrigin(), pgs.get(i).getParticles().get(j).getPosYOrigin());
				rotate(radians(pgs.get(i).getParticles().get(j).getParticleDirection()));
				circle(pgs.get(i).getParticles().get(j).getPosX(), pgs.get(i).getParticles().get(j).getPosY(), pgs.get(i).getParticles().get(j).SIZE);
				popMatrix();
				pgs.get(i).getParticles().get(j).moveParticle();
				
				if(!pgs.get(i).getParticles().get(j).isCanMove()) {
					textSize(12);
					text(pgs.get(i).getParticles().get(j).getName(), (int) pgs.get(i).getParticles().get(j).getRealPosX()-2*pgs.get(i).getParticles().get(j).getName().length(), (int)pgs.get(i).getParticles().get(j).getRealPosY()+30);
				}
			}
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
	}
	
	public void deleteAll() {
			pgs.clear();		
	}

	public void notifyMessage(String message) {
		Gson json = new Gson();
		ParticleGroup pgx = json.fromJson(message, ParticleGroup.class);
	}

}
