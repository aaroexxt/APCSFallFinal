public class Position {
    //Public ints - can access externally
    public int x;
    public int y;
    public Position() {
        x = 0;
        y = 0;
    }
    public Position(int x, int y) {
        this.x = x*2;
        this.y = y;
    }
    public void setPosition(int nX, int nY) {
    	this.x = nX*2;
    	this.y = nY;
    }

    public String toString() {
        return "X: "+x+", Y: "+y;
    }
}