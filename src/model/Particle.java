package model;

public class Particle {

	private int particleSpeed;
	private int particleDirection;
	private boolean canMove;
	private int type;
	
	public Particle(int s, int dir, int t) {
		canMove = true;
		particleDirection = dir;
		particleSpeed = s;
		type = t;
	}
	
	public void drawParticle() {
		
	}
}
