package work3;

/**
 *
 * @author Claire, Esther & Orann
 */
public class Position {
    private int x;
    private int y;

    /**
     * Constructor with the abscissa and the ordonate
     *
     * @param x
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor with a position
     *
     * @param p
     */
    public Position(Position p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Getter
     *
     * @return the abscissa of the position
     */
    public int getX() {
        return x;
    }

    ;
    
    /**
     * Getter
     * @return the ordinate of the position
     */
    public int getY() {
        return y;
    }
}
