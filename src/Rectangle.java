public class Rectangle {
    //Basic parameters; height width and length as well as what character to fill with
    private int width;
    private int height;
    private char fillChar = '*';
    private boolean filled;

    //charTable and StringTable for rendering
    private char[][] charTableInternal;
    private String[] stringTableInternal; //#12

    //Positional coordinates on the screen with (0, 0) at top left
    private Position position = new Position();
    
    //Show/hide?
    private boolean isVisible = true;

    /*
    * CONSTRUCTORS
    */
    public Rectangle() {
        width = 40;
        height = 10;
        filled = true;
        isVisible = true;

        //charTableInternal = new char[height][width]; //Not needed right now because of no collision detection
        //regenCharTable(); //Not needed right now because of no collision detection

        stringTableInternal = new String[height];
        regenStringTable();
    }

    public Rectangle(int x, int y, int width, int height, boolean filled) {
        this.width = width*2; //because height is x2 in java, multiply width by 2
        this.height = height;
        this.filled = filled;
        isVisible = true;

        //charTableInternal = new char[height][width]; //Not needed right now because of no collision detection
        //regenCharTable(); //Not needed right now because of no collision detection

        stringTableInternal = new String[height];
        regenStringTable();

        position.setPosition(x,y); //update position onscreen
    }

    /*
    * Getters/Setters
    */

    public int getWidth() { //#10a
        return width;
    }
    public void setWidth(int newWidth) { //#10b
        width = newWidth;
        regenStringTable();
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int newHeight) {
        height = newHeight;
        regenStringTable();
    }

    public char getFillChar() {
        return fillChar;
    }
    public void setFillChar(char newChar) {
        fillChar = newChar;
        regenStringTable();
    }

    public boolean getFilled() {
        return filled;
    }
    public void setFilled(boolean isFilled) {
        filled = isFilled;
        regenStringTable();
    }
    
    public boolean getVisible() {
    	return isVisible;
    }
    
    public void show() {
    	isVisible = true;
    }
    
    public void hide() {
    	isVisible = false;
    }

    /*
    * StringTable/CharTable implementation (mesh geometry)
    */

    public char[][] regenCharTable() {
        char[][] charTable = new char[height][width];
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if (filled) {
                    charTable[i][j] = fillChar;
                } else {
                    if ((i == 0 || i == height) && (j == 0 || j ==width)) {
                        charTable[i][j] = fillChar;
                    } else {
                        charTable[i][j] = ' '; //otherwise fill with space
                    }
                }
            }
        }
        charTableInternal = charTable; //set internal char table to what we just generated
        return charTable; //and return
    }

    public char[][] getCharTable() {
        return charTableInternal;
    }

    public String[] regenStringTable() {
        String[] stringTable = new String[height];
        for (int i=0; i<height; i++) {
            stringTable[i] = "";
            for (int j=0; j<width; j++) {
                if (filled) {
                    stringTable[i] += fillChar;
                } else {
                    if ((i == 0 || i == height) && (j == 0 || j == width)) {
                        stringTable[i] += fillChar;
                    } else {
                        stringTable[i] += " "; //otherwise fill with space
                    }
                }
            }
        }
        stringTableInternal = stringTable;
        return stringTable;
    }

    public String[] getStringTable() {
        return stringTableInternal;
    }

    /*
     * GetPosition
    */

    public Position getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }
    
    /*
     * TOSTRING
     */
    public String toString() {
        return "Type: Rectangle, width: "+width+", height: "+height+", fillChar: "+fillChar+", isFilled: "+filled+", position: "+position;
    }
}