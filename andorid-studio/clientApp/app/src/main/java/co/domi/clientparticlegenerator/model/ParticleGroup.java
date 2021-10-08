package co.domi.clientparticlegenerator.model;

import java.util.ArrayList;

public class ParticleGroup {
    private String name;
    private int number;
    private int originalPosX;
    private int originalPosY;
    private int type;
    private int mx;
    private int my;
    private ArrayList<Particle> particles = new ArrayList<>();

    public ParticleGroup() {
    }

    public ParticleGroup(String name, int num, int posX, int posY, int t, int mx, int my) {
        this.name = name;
        this.number = num;
        this.originalPosX = posX;
        this.originalPosY = posY;
        this.type = t;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getOriginalPosX() {
        return originalPosX;
    }

    public void setOriginalPosX(int originalPosX) {
        this.originalPosX = originalPosX;
    }

    public int getOriginalPosY() {
        return originalPosY;
    }

    public void setOriginalPosY(int originalPosY) {
        this.originalPosY = originalPosY;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMx() {
        return mx;
    }

    public void setMx(int mx) {
        this.mx = mx;
    }

    public int getMy() {
        return my;
    }

    public void setMy(int my) {
        this.my = my;
    }
}
