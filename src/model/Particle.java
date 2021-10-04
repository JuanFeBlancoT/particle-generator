package model;

import processing.core.PApplet;

public class Particle {

	public final int SIZE = 7;
	private int particleSpeed;
	private float particleDirection;
	private float backupAngle;
	private boolean canMove;
	private int type;
	private String name;
	private int r,g,b;
	
	private int posX, posY;
	private double realPosX, realPosY;
	private double changeY;
	private double changeX;
	private int posXOrigin, posYOrigin;
	private int base;
	private int mx, my;
	
	private PApplet app;
	
	public Particle(int s, float dir, int t, int px, int py, PApplet app, int maxW, int maxH, String name) {
		canMove = true;
		particleDirection = dir;
		particleSpeed = s;
		type = t;
		posXOrigin = px;
		posYOrigin = py;
		
		realPosX = px;
		realPosY = py;
		this.app = app;
		mx = maxW;
		my = maxH;
		this.name = name;

		calculateAngles();
		
		calculateColor();
	}
	
	private void calculateColor() {
		switch (type) {
		case 1:
			r = 100;
			g = 100;
			b = 250;
			break;
		case 2:
			r = 40;
			g = 230;
			b = 40;
			break;
		case 3:
			r = 230;
			g = 40;
			b = 40;
			break;
		}				
	}

	public void drawParticle() {
		app.fill(r,g,b);
		app.pushMatrix();
		app.translate(posXOrigin, posYOrigin);
		app.rotate(app.radians(particleDirection));
		app.circle(posX, posY, SIZE);
		app.popMatrix();
		
		moveParticle();
	}
	
	public void moveParticle() {
		
		if(canMove) {
			if((realPosX > mx || realPosY > my || realPosX < 0 || realPosY < 0 )) {
				bounceParticle();
			}
			posX+= particleSpeed;
			
			//
			if( particleDirection > 0 && particleDirection < 90) {
				realPosX+= changeX*particleSpeed;
				realPosY+= changeY*particleSpeed;
			}else if( particleDirection > 90 && particleDirection < 180) {
				realPosX-= changeX*particleSpeed;
				realPosY+= changeY*particleSpeed;
			}else if( particleDirection > 180 && particleDirection < 270) {
				realPosX-= changeX*particleSpeed;
				realPosY-= changeY*particleSpeed;
			}else if( particleDirection > 270 && particleDirection < 360) {
				realPosX+= changeX*particleSpeed;
				realPosY-= changeY*particleSpeed;
			}
		}else {
			app.textSize(12);
			app.text(name, (int)realPosX-2*name.length(), (int)realPosY+30);
		}		
	}
	
	private void bounceParticle() {
		
		int option = 0;
		if((realPosX > mx && particleDirection > 0 && particleDirection < 90) ||
				(realPosY > my && particleDirection > 90 && particleDirection < 180) ||
				(realPosX < 0 && particleDirection > 180 && particleDirection < 270)||
				(realPosY < 0 && particleDirection > 270 && particleDirection < 360)) {
			float n = 180-(2*(particleDirection%90));
			float pd = (n+particleDirection)%360;
			particleDirection = pd;
			
		}else if((realPosY > my && particleDirection > 0 && particleDirection < 90) ||
				(realPosX < 0 && particleDirection > 90 && particleDirection < 180) ||
				(realPosY < 0 && particleDirection > 180 && particleDirection < 270)||
				(realPosX> mx && particleDirection > 270 && particleDirection < 360)) {
			int a = (int) (particleDirection/90);
			particleDirection = ((360-(particleDirection%90))+(90*a))%360;
		}
		
		if(realPosX > mx){
			realPosX = mx-particleSpeed;
		}
		if(realPosY > my) {
			realPosY = my-particleSpeed;
		}
		if(realPosX < 0){
			realPosX = particleSpeed;
		}
		if(realPosY < 0) {
			realPosY = particleSpeed;
		}
		posXOrigin = (int) realPosX;
		posYOrigin = (int) realPosY;
		
		posX = 0;
		posY = 0;		
	}

	public void calculateAngles() {
		
		if( particleDirection > 0 && particleDirection < 90) {
			base = mx-posXOrigin;
		}else if( particleDirection > 90 && particleDirection < 180) {
			base = my-posYOrigin;
		}else if( particleDirection > 180 && particleDirection < 270) {
			base = posXOrigin;
		}else if( particleDirection > 270 && particleDirection < 360) {
			base = posYOrigin;
		}
		
		backupAngle = particleDirection%90;		
		
		double inDegrees = backupAngle;
		double inRadians = Math.toRadians(inDegrees);
		double tan = Math.tan(inRadians);

		double y = tan*base;
		
		double d = Math.sqrt((Math.pow(base, 2))+(Math.pow(y, 2)));
		
		if( (particleDirection > 0 && particleDirection < 90) || ( particleDirection > 180 && particleDirection < 270)) {
			changeY = y/d;
			changeX = base/d;
		}else if( (particleDirection > 90 && particleDirection < 180) || ( particleDirection > 270 && particleDirection < 360)) {
			changeX = y/d;
			changeY = base/d;
		}
	}
	
	//Getter and Setters

	public double getRealPosX() {
		return realPosX;
	}

	public double getRealPosY() {
		return realPosY;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}
	
	
}
