public class Square {
    //Basic parameters; height and length as well as what character to fill with
    private int sideLength;
    private char fillChar = 'a';
    private boolean filled;

    //charTable and StringTable for rendering
    private char[][] charTableInternal;
    private String[] stringTableInternal;

    //Positional coordinates on the screen with (0, 0) at top left
    private Position position = new Position();

    /*
    * CONSTRUCTORS
    */
    public Square() {
        sideLength = 10;
        filled = true;

        //charTableInternal = new char[height][width]; //Not needed right now because of no collision detection
        //regenCharTable(); //Not needed right now because of no collision detection

        stringTableInternal = new String[sideLength];
        regenStringTable();
    }

    public Square(int x, int y, int sideLength, boolean filled) {
        this.sideLength = sideLength;
        this.filled = filled;

        //charTableInternal = new char[height][width]; //Not needed right now because of no collision detection
        //regenCharTable(); //Not needed right now because of no collision detection

        stringTableInternal = new String[sideLength];
        regenStringTable();

        position.setPosition(x,y); //update position onscreen
    }

    /*
    * Getters/Setters
    */

    public int getSideLength() {
        return sideLength;
    }
    public void setSideLength(int newSL) {
        sideLength = newSL;
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

    /*
    * StringTable/CharTable implementation (mesh geometry)
    */

    public char[][] regenCharTable() {
        char[][] charTable = new char[sideLength][sideLength];
        for (int i=0; i<sideLength; i++) {
            for (int j=0; j<sideLength; j++) {
                if (filled) {
                    charTable[i][j] = fillChar;
                } else {
                    if ((i == 0 || i == sideLength) && (j == 0 || j == sideLength)) {
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
        String[] stringTable = new String[sideLength];
        for (int i=0; i<sideLength; i++) {
            stringTable[i] = "";
            for (int j=0; j<sideLength*2; j++) {
                if (filled) {
                    stringTable[i] += fillChar;
                } else {
                    if ((i == 0 || i == sideLength) && (j == 0 || j == sideLength)) {
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
        return "Type: Square, sideLength: "+sideLength+", fillChar: "+fillChar+", isFilled: "+filled;
    }
}