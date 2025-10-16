public class signal {
    private int x;
    private int y;
    private char type;
    signal(int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    public String print() {
        return "type: "+type+" x: "+x+", y: "+y;
    }
    public char getType() {
        return type;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
