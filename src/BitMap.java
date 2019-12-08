public class BitMap {
    //Basic parameters; height width
    private int width;
    private int height;

    //charTable and StringTable for rendering
    private char[][] charTableInternal;
    private String[] stringTableInternal;

    //Positional coordinates on the screen with (0, 0) at top left
    private Position position = new Position();

    /*
    * CONSTRUCTORS
    */
    public BitMap() {
        width = 40;
        height = 10;

        //charTableInternal = new char[height][width]; //Not needed right now because of no collision detection
        //regenCharTable(); //Not needed right now because of no collision detection

        stringTableInternal = new String[1];
        stringTableInternal[0] = "BitMap empty";
    }

    public BitMap(int x, int y, String[] BMP) {
    	this.height = BMP.length;
    	//Find max width
    	this.width = BMP[0].length();
    	for (int i=1; i<BMP.length; i++) {
    		int bmpRowLen = BMP[i].length();
    		if (bmpRowLen > this.width) {
    			this.width = bmpRowLen;
    		}
    	}

        //charTableInternal = new char[height][width]; //Not needed right now because of no collision detection
        //regenCharTable(); //Not needed right now because of no collision detection

        stringTableInternal = BMP;

        position.setPosition(x,y); //update position onscreen
    }

    /*
    * Getters/Setters
    */

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /*
    * CharTable implementation (mesh geometry)
    */

    public char[][] regenCharTable() {
        char[][] charTable = new char[height][width];
        for (int i=0; i<stringTableInternal.length; i++) { //Essentially just make clone of StringTable
        	for (int j=0; j<stringTableInternal[i].length(); i++) {
        		charTable[i][j] = stringTableInternal[i].charAt(j); //Take a single char at a time (cast)
        	}
        }
        charTableInternal = charTable;
        return charTable; //and return
    }

    public char[][] getCharTable() {
        return charTableInternal;
    }
    
    public void setStringTable(String[] BMP) {
    	this.height = BMP.length;
    	//Find max width
    	this.width = BMP[0].length();
    	for (int i=1; i<BMP.length; i++) {
    		int bmpRowLen = BMP[i].length();
    		if (bmpRowLen > this.width) {
    			this.width = bmpRowLen;
    		}
    	}
    	
        stringTableInternal = BMP;
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
        return "Type: BitMap, width: "+width+", height: "+height+", position: "+position;
    }
}