package model;

import processing.core.PApplet;

public class Particle {

	public final int SIZE = 4;
	private int particleSpeed;
	private float particleDirection;
	private float backupAngle;
	private boolean canMove;
	private int type;
	
	private int posX, posY;
	private double realPosX, realPosY;
	private double changeY;
	private double changeX;
	private int posXOrigin, posYOrigin;
	private int base;
	private int mx, my;
	
	private PApplet app;
	
	public Particle(int s, float dir, int t, int px, int py, PApplet app, int maxW, int maxH) {
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

		System.out.println("***"+realPosX+","+realPosY);
		calculateAngles();
		
	}
	
	public void drawParticle() {
		app.pushMatrix();
		app.translate(posXOrigin, posYOrigin);
		app.rotate(app.radians(particleDirection));
		app.square(posX, posY, SIZE);
		app.popMatrix();
		
		moveParticle();
	}
	
	public void moveParticle() {
		
		if(realPosX > 800 || realPosY > 600 || realPosX < 0 || realPosY < 0 ) {
			System.out.println(realPosX+","+ realPosY+":    "+posX);
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
		
		System.out.println("base: "+base);
		
		double y = tan*base;
		System.out.println(y);
		
		double d = Math.sqrt((Math.pow(base, 2))+(Math.pow(y, 2)));
		System.out.println("d: "+d);
		
		/*changeY = y/d;
		changeX = base/d;
		 */
		
		if( (particleDirection > 0 && particleDirection < 90) || ( particleDirection > 180 && particleDirection < 270)) {
			changeY = y/d;
			changeX = base/d;
		}else if( (particleDirection > 90 && particleDirection < 180) || ( particleDirection > 270 && particleDirection < 360)) {
			changeX = y/d;
			changeY = base/d;
		}
		
		
		System.out.println("changeY: "+changeY);
		System.out.println("changeX: "+changeX);
	}
}
