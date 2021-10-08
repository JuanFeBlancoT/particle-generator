package co.domi.clientparticlegenerator.model;

public class ParticleGroup {
    private String name;
    private int number;
    private int originalPosX;
    private int originalPosY;
    private int type;
    private int mx;
    private int my;

    public ParticleGroup() {
    }

    public ParticleGroup(String name, int number, int originalPosX, int originalPosY, int type, int mx, int my) {
        this.name = name;
        this.number = number;
        this.originalPosX = originalPosX;
        this.originalPosY = originalPosY;
        this.type = type;
        this.mx = mx;
        this.my = my;
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
