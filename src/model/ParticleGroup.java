package model;

import java.util.ArrayList;

public class ParticleGroup {
	
	private String name;
	private int number;
	private int originalPosX;
	private int originalPosY;
	private int type;
	private int mx, my;
	private ArrayList<Particle> particles = new ArrayList<>();
	
	public ParticleGroup(String n, int num, int posX, int posY, int t,int mx, int my) {
		name = n;
		number = num;
		originalPosX = posX;
		originalPosY = posY;
		type = t;
		this.mx = mx;
		this.my = my;
		
		for (int i = 0; i < number; i++) {
			//calculate direction in radians
			float dir = (float) Math.random()*359;
			int speed = (int) (Math.random()*7)+1;
			//add particles
			Particle particleX = new Particle(speed, dir, type, originalPosX, originalPosY, mx, my, name);
			particles.add(particleX);
		}
	}

	//Getters and Setters
	public ArrayList<Particle> getParticles() {
		return particles;
	}

	public void setParticles(ArrayList<Particle> particles) {
		this.particles = particles;
	}
	
}
