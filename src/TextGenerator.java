import java.util.HashMap;
public class TextGenerator {
	//Create HashMap to store font
	private HashMap<Character, String[]> font = new HashMap<>();
	
	//hashMap lookup: character, associated ascii font array
    
    /*
     * CONSTRUCTORS
     */
    
    
	public TextGenerator() {
		regenFont(); //Just regenerate the font and put it in the hashmap
	}
	
	private void regenFont() {
		font.put('0', new String[]{"   ____ ","  / __ \\"," / / / /","/ /_/ / ","\\____/  "});
		font.put('1', new String[] {"   ___","  <  /","  / / "," / /  ","/_/   "});
		font.put('2', new String[] {"   ___ ","  |__ \\","  __/ /"," / __/ ","/____/ "});
		font.put('3', new String[] {"   _____","  |__  /","   /_ < "," ___/ / ","/____/  "});
		font.put('4', new String[] {"   __ __","  / // /"," / // /_","/__  __/","  /_/   "});
		font.put('5', new String[] {"    ______","   / ____/","  /___ \\  "," ____/ /  ","/_____/   "});
		font.put('6', new String[] {"   _____","  / ___/"," / __ \\ ","/ /_/ / ","\\____/  "});
		font.put('7', new String[] {" _____","/__  /","  / / "," / /  ","/_/   "});
		font.put('8', new String[] {"   ____ ","  ( __ )"," / __  |","/ /_/ / ","\\____/  "});
		font.put('9', new String[] {"   ____ ","  / __ \\"," / /_/ /"," \\__, / ","/____/  "});
		font.put(' ', new String[] {"  ","  ","  ","  ","  "});
		font.put('a', new String[] {"  ____ _", " / __ `/", "/ /_/ / ", "\\__,_/  ", "        "});
		font.put('b', new String[] {"   / /_ ", "  / __ \\", " / /_/ /", "/_.___/ ", "        "});
		font.put('c', new String[] {"  _____", " / ___/", "/ /__  ", "\\___/  ", "       "});
		font.put('d', new String[] {"  ____/ /", " / __  / ", "/ /_/ /  ", "\\__,_/   ", "         "});
		font.put('e', new String[] {"  ___ ", " / _ \\", "/  __/", "\\___/ ", "      "});
		font.put('f', new String[] {"   / __/", "  / /_  ", " / __/  ", "/_/     ", "        "});
		font.put('g', new String[] {"   ____ _", "  / __ `/", " / /_/ / ", " \\__, /  ", "/____/   "});
		font.put('h', new String[] {"   / /_ ", "  / __ \\", " / / / /", "/_/ /_/ ", "        "});
		font.put('i', new String[] {"   (_)", "  / / ", " / /  ", "/_/   ", "      "});
		font.put('j', new String[] {"      (_)", "     / / ", "    / /  ", " __/ /   ", "/___/    "});
		font.put('k', new String[] {"   / /__", "  / //_/", " / ,<   ", "/_/|_|  ", "        "});
		font.put('l', new String[] {"   / /", "  / / ", " / /  ", "/_/   ", "      "});
		font.put('m', new String[] {"   ____ ___ ", "  / __ `__ \\", " / / / / / /", "/_/ /_/ /_/ ", "            "});
		font.put('n', new String[] {"   ____ ", "  / __ \\", " / / / /", "/_/ /_/ ", "        "});
		font.put('o', new String[] {"  ____ ", " / __ \\", "/ /_/ /", "\\____/ ", "       "});
		font.put('p', new String[] {"    ____ ", "   / __ \\", "  / /_/ /", " / .___/ ", "/_/      "});
		font.put('q', new String[] {"  ____ _", " / __ `/", "/ /_/ / ", "\\__, /  ", "  /_/   "});
		font.put('r', new String[] {"   _____", "  / ___/", " / /    ", "/_/     ", "        "});
		font.put('s', new String[] {"   _____", "  / ___/", " (__  ) ", "/____/  ", "        "});
		font.put('t', new String[] {"  / /_", " / __/", "/ /_  ", "\\__/  ", "      "});
		font.put('u', new String[] {"  __  __", " / / / /", "/ /_/ / ", "\\__,_/  ", "        "});
		font.put('v', new String[] {" _   __", "| | / /", "| |/ / ", "|___/  ", "       "});
		font.put('w', new String[] {" _      __", "| | /| / /", "| |/ |/ / ", "|__/|__/  ", "          "});
		font.put('x', new String[] {"   _  __", "  | |/_/", " _>  <  ", "/_/|_|  ", "        "});
		font.put('y', new String[] {"   __  __", "  / / / /", " / /_/ / ", " \\__, /  ", "/____/   "});
		font.put('-', new String[] {"       "," ______","/_____/","       ","       "});
		font.put('_', new String[] {"       ","       ","       "," ______","/_____/"});
		font.put('+', new String[] {"    __ "," __/ /_","/_  __/"," /_/   ","       "});
		font.put('*', new String[] {"  __/|_"," |    /","/_ __| "," |/    ","       "});
		font.put('/', new String[] {"     _/_/","   _/_/  "," _/_/    ","/_/      ","         "});
		font.put('.', new String[] {"   ","   "," _ ","(_)","   "});
	
		//System.out.println("Font generation OK, size: "+font.size());
	}
	    
	    /*
	     * Generate ASCII
	     */
	    
	    public String[] generateASCII(String text) {
	    	text = text.toLowerCase(); //Ensure text is lower case to match HashMap
	    	String[] output = new String[5];
	    	for (int i=0; i<output.length; i++) {
	    		output[i] = new String(); //Make sure output array is initialized correctly
	    	}
	    	
	    	for (int i=0; i<text.length(); i++) {
	    		char c = text.charAt(i); //What character do we want to get?
	    		if (font.containsKey(c)) { //ayy we have the key
	    			String[] asciiChar = font.get(c); //now get it from the hashmap
	    			String[] space = font.get(' '); //get space char
	    			for (int j=0; j<asciiChar.length; j++) {
	    				output[j] += asciiChar[j];
	    				output[j] += space[j]; //pad with space
	    			}
	    		} else {
	    			System.out.println("Error generating character "+c+" in generate ASCII TextGenerator");
	    		}
	    	}
	    	return output;
	    }
    
    /*
     * TOSTRING
     */
    public String toString() {
        return "Type: TextGenerator";
    }
}
