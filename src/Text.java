import java.util.HashMap;
public class Text {
	//Create HashMap to store font
	private HashMap<String, String[]> font = new HashMap<>();
	//Current text being displayed
	private String currentText;
	
	//Width and height
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
    
    
	public Text() {
		regenFont();
		String[] BMP = generateASCII("err"); //Generate what is essentially a BitMap
		currentText = "err";
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
	
	public Text(int x, int y, String text) {
		regenFont(); //Generate hashmap with fonts
		currentText = text;
		String[] BMP = generateASCII(text); //Generate what is essentially a BitMap
		
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

        position.setPosition(x,y); //update position onscreen
	}
	
	private void regenFont() {
		font.put("0", new String[]{"   ____ ","  / __ \\"," / / / /","/ /_/ / ","\\____/  "});
		font.put("1", new String[] {"   ___","  <  /","  / / "," / /  ","/_/   "});
		font.put("2", new String[] {"   ___ ","  |__ \\","  __/ /"," / __/ ","/____/ "});
		font.put("3", new String[] {"   _____","  |__  /","   /_ < "," ___/ / ","/____/  "});
		font.put("4", new String[] {"   __ __","  / // /"," / // /_","/__  __/","  /_/   "});
		font.put("5", new String[] {"    ______","   / ____/","  /___ \\  "," ____/ /  ","/_____/   "});
		font.put("6", new String[] {"   _____","  / ___/"," / __ \\ ","/ /_/ / ","\\____/  "});
		font.put("7", new String[] {" _____","/__  /","  / / "," / /  ","/_/   "});
		font.put("8", new String[] {"   ____ ","  ( __ )"," / __  |","/ /_/ / ","\\____/  "});
		font.put("9", new String[] {"   ____ ","  / __ \\"," / /_/ /"," \\__, / ","/____/  "});
		font.put(" ", new String[] {"  ","  ","  ","  ","  "});
		font.put("a", new String[] {"  ____ _", " / __ \\`/", "/ /_/ / ", "\\__,_/  ", "        "});
		font.put("b", new String[] {"   / /_ ", "  / __ \\", " / /_/ /", "/_.___/ ", "        "});
		font.put("c", new String[] {"  _____", " / ___/", "/ /__  ", "\\___/  ", "       "});
		font.put("d", new String[] {"  ____/ /", " / __  / ", "/ /_/ /  ", "\\__,_/   ", "         "});
		font.put("e", new String[] {"  ___ ", " / _ \\", "/  __/", "\\___/ ", "      "});
		font.put("f", new String[] {"   / __/", "  / /_  ", " / __/  ", "/_/     ", "        "});
		font.put("g", new String[] {"   ____ _", "  / __ \\`/", " / /_/ / ", " \\__, /  ", "/____/   "});
		font.put("h", new String[] {"   / /_ ", "  / __ \\", " / / / /", "/_/ /_/ ", "        "});
		font.put("i", new String[] {"   (_)", "  / / ", " / /  ", "/_/   ", "      "});
		font.put("j", new String[] {"      (_)", "     / / ", "    / /  ", " __/ /   ", "/___/    "});
		font.put("k", new String[] {"   / /__", "  / //_/", " / ,<   ", "/_/|_|  ", "        "});
		font.put("l", new String[] {"   / /", "  / / ", " / /  ", "/_/   ", "      "});
		font.put("m", new String[] {"   ____ ___ ", "  / __ \\`__ \\", " / / / / / /", "/_/ /_/ /_/ ", "            "});
		font.put("n", new String[] {"   ____ ", "  / __ \\", " / / / /", "/_/ /_/ ", "        "});
		font.put("o", new String[] {"  ____ ", " / __ \\", "/ /_/ /", "\\____/ ", "       "});
		font.put("p", new String[] {"    ____ ", "   / __ \\", "  / /_/ /", " / .___/ ", "/_/      "});
		font.put("q", new String[] {"  ____ _", " / __ \\`/", "/ /_/ / ", "\\__, /  ", "  /_/   "});
		font.put("r", new String[] {"   _____", "  / ___/", " / /    ", "/_/     ", "        "});
		font.put("s", new String[] {"   _____", "  / ___/", " (__  ) ", "/____/  ", "        "});
		font.put("t", new String[] {"  / /_", " / __/", "/ /_  ", "\\__/  ", "      "});
		font.put("u", new String[] {"  __  __", " / / / /", "/ /_/ / ", "\\__,_/  ", "        "});
		font.put("v", new String[] {" _   __", "| | / /", "| |/ / ", "|___/  ", "       "});
		font.put("w", new String[] {" _      __", "| | /| / /", "| |/ |/ / ", "|__/|__/  ", "          "});
		font.put("x", new String[] {"   _  __", "  | |/_/", " _>  <  ", "/_/|_|  ", "        "});
		font.put("y", new String[] {"   __  __", "  / / / /", " / /_/ / ", " \\__, /  ", "/____/   "});
		font.put("-", new String[] {"       "," ______","/_____/","       ","       "});
		font.put("_", new String[] {"       ","       ","       "," ______","/_____/"});
		font.put("+", new String[] {"    __ "," __/ /_","/_  __/"," /_/   ","       "});
		font.put("*", new String[] {"  __/|_"," |    /","/_ __| "," |/    ","       "});
		font.put("/", new String[] {"     _/_/","   _/_/  "," _/_/    ","/_/      ","         "});
		font.put(".", new String[] {"   ","   "," _ ","(_)","   "});
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
	    
	    public void setText(String text) {
	    	String[] BMP = generateASCII(text); //Generate what is essentially a BitMap
			currentText = text;
	    	
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
	    
	    public String getText() {
	    	return currentText;
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
        return "Type: Text, width: "+width+", height: "+height+", position: "+position;
    }
}
