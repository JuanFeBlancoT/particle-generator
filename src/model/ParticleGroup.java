package model;

import java.util.ArrayList;

public class ParticleGroup {
	
	private String name;
	private int number;
	private int originalPosX;
	private int originalPosY;
	private int type;
	private ArrayList<Particle> particles;
	
	public ParticleGroup(String n, int num, int posX, int posY, int t) {
		name = n;
		number = num;
		originalPosX = posX;
		originalPosY = posY;
		type = t;
		
		particles = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			//add particles
		}
	}
}
