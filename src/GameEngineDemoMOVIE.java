import java.util.HashMap;

//Another idea for implementation of a simpler hashmap layout
/*public class Frame {
	public String[] sprite;
	public boolean showHide;
	public Position position;
}
and then
HashMap<Integer, Frame> background;
*/

public class GameEngineDemoMOVIE {

	public static void main(String[] args) {
		//Create textGenerator
		TextGenerator tg = new TextGenerator();
		
		//Create HashMaps to hold position, show, dialogue, sprite information
		HashMap<Integer,String[]> backgroundSprites = new HashMap<>(); //Hashmap for background sprites - frame, string arr of sprite
		HashMap<Integer,Boolean> backgroundShowHide = new HashMap<>(); //Hashmap for background show/hide - frame, boolean
		HashMap<Integer, Position> backgroundPositions = new HashMap<>(); //Hashmap for background positions - frame, Position
		
		HashMap<Integer,String[]> stickmanSprites = new HashMap<>(); //Hashmap for stickman sprites - frame, string arr of sprite
		HashMap<Integer,Boolean> stickmanShowHide = new HashMap<>(); //Hashmap for stickman show/hide - frame, boolean
		HashMap<Integer, Position> stickmanPositions = new HashMap<>(); //Hashmap for stickman positions - frame, Position
		
		HashMap<Integer,String> narratorDialogue = new HashMap<>(); //Hashmap for narrator dialogue - frame, string
		HashMap<Integer,Boolean> narratorShowHide = new HashMap<>(); //Hashmap for narrator show/hide - frame, boolean
		HashMap<Integer, Position> narratorPositions = new HashMap<>(); //Hashmap for narrator positions - frame, Position
		
		//Create bitmap objects to store information
		BitMap narrator = new BitMap();
		BitMap stickman = new BitMap();
		BitMap background = new BitMap();
		
		//Create gameEngine and renderHandler objects to actually render/display graphics
		GameEngine movie = new GameEngine(background, stickman, narrator); //render order: background, stickman, narrator
		movie.setDisplaySize(165, 25);
		RenderHandler RHmovie = new RenderHandler(movie);
		
		//OnEnd and OnFrame events - check hashmaps and update
		class OnEndEvents {
			public Void EndMovie() {
				System.out.println("d-emo render finished");
				System.out.println("Thanks for watching, hope you enjoyed :)");
				System.out.println("By AAron");
				return null;
			}
		}
		OnEndEvents endEvents = new OnEndEvents();
		RHmovie.setOnEnd(endEvents::EndMovie);
		
		class OnFrameEvents {
			public Void FrameMovie(int fCount) {
				//Check for any frame related events
				//Dialogue - narrator only
				if (narratorDialogue.containsKey(fCount)) {
					String newDialogue = narratorDialogue.get(fCount);
					String[] newText = tg.generateASCII(newDialogue);
					narrator.setStringTable(newText);
				}
				//Sprite updating - stickman and background only
				if (stickmanSprites.containsKey(fCount)) {
					stickman.setStringTable(stickmanSprites.get(fCount));
				}
				if (backgroundSprites.containsKey(fCount)) {
					background.setStringTable(backgroundSprites.get(fCount));
				}
				//Position - all 3
				if (narratorPositions.containsKey(fCount)) {
					Position newPos = narratorPositions.get(fCount);
					narrator.setPosition(newPos.x, newPos.y);
				}
				if (stickmanPositions.containsKey(fCount)) {
					Position newPos = stickmanPositions.get(fCount);
					stickman.setPosition(newPos.x, newPos.y);
				}
				if (backgroundPositions.containsKey(fCount)) {
					Position newPos = backgroundPositions.get(fCount);
					background.setPosition(newPos.x, newPos.y);
				}
				//Show-hide - all 3
				if (narratorShowHide.containsKey(fCount)) {
					if(narratorShowHide.get(fCount)) {
						narrator.show();
					} else {
						narrator.hide();
					}
				}
				if (stickmanShowHide.containsKey(fCount)) {
					if(stickmanShowHide.get(fCount)) {
						stickman.show();
					} else {
						stickman.hide();
					}
				}
				if (backgroundShowHide.containsKey(fCount)) {
					if(backgroundShowHide.get(fCount)) {
						background.show();
					} else {
						background.hide();
					}
				}
				return null;
			}
		}
		OnFrameEvents frameEvents = new OnFrameEvents();
		RHmovie.setOnFrame(frameEvents::FrameMovie);
		
		
		/*
		 * 
		 * ACTUAL SCENE DEFINITION
		 * 
		 */
		
		//Story part 1: intro scene
		narratorShowHide.put(0,true); //show
		stickmanShowHide.put(0, false);
		backgroundShowHide.put(0,false);	
		
		narratorPositions.put(0, new Position(0, 0));
		narratorDialogue.put(0, "StickMan Movie V1");
		//Frame 30: Change title
		narratorDialogue.put(100, "By Aaron Becker");
		//Frame 60: Change title
		narratorDialogue.put(200, "For APCS");
		narratorShowHide.put(270, false);
		
		//Story part 2: stickman introduces himself
		narratorShowHide.put(300, true);
		narratorDialogue.put(300, "Hi.");
		stickmanShowHide.put(300, true);
		stickmanPositions.put(300, new Position(15, 11));
		String[] stickWave1 = {
				"\\O",
				" |\\",
				"/ \\"
		};
		String[] stickWave2 = {
				"|O",
				" |\\",
				"/ \\"
		};
		stickmanSprites.put(300, stickWave1);
		
		backgroundShowHide.put(300,true);
		backgroundPositions.put(300, new Position(0,6));
		backgroundSprites.put(300, new String[] {
				"   //////-------------------------------------------------\\\\\\\\\\\\",
						"   ||  |                 Stickman Premiere                 |  ||", 
						"   ||  |                                                   |  ||", 
						"   ||\\\\\\\\-------------------------------------------------////||", 
						"   ||                                                         ||",
						"   ||                                                         ||", 
						" __||__                                                     __||__", 
						"|______|___________________________________________________|______|"
		});
		
		stickmanSprites.put(330, stickWave2);
		stickmanSprites.put(360, stickWave1);
		stickmanSprites.put(390, stickWave2);
		stickmanSprites.put(420, stickWave1);
		stickmanSprites.put(450, stickWave2);
		stickmanSprites.put(480, stickWave1);
		stickmanSprites.put(510, stickWave2);
		stickmanSprites.put(540, stickWave1);
		stickmanSprites.put(570, stickWave2);
		narratorDialogue.put(360, "I'm stickman.");
		narratorDialogue.put(430, "Nice to meet you.");
		narratorDialogue.put(500, "Let me tell you");
		narratorDialogue.put(530, "about some of");
		narratorDialogue.put(560, "my adventures.");
		
		//Story part 3: Stickman goes canoeing
		backgroundShowHide.put(600, false);
		stickmanShowHide.put(600, false);
		narratorDialogue.put(610,"Once upon a time");
		
		narratorDialogue.put(700,"a stormy day");
		narratorPositions.put(700, new Position(0,20));
		backgroundPositions.put(700, new Position(0,0));
		backgroundSprites.put(700,new String[] {
				" _(    )   __",
			    "  (___(__)",
			    "    /_ /_ ",
			    "     /_ / ",
			    "      //   ",
			    "      /",
			    "",
			    "",
			    "",
			    "",
			    "",
			    "",
			    "",
			    "",
			    "",
			    "",
			    "",
			    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
		});
		backgroundShowHide.put(700,true);
		
		narratorDialogue.put(740,"clear ocean");
		narratorDialogue.put(820, "Except STICKMAN!");
		narratorDialogue.put(850,"and his friend");
		String[] canoeF1 = {
        		"       o,    o__        ",
        		"      </     [\\\\/       ",
        		"   (`-/-------/----')   ",
        		"     @       @          "
        };
        String[] canoeF2 = {
        		"      o_/|   o_.",
        		"       [\\\\_|   [\\\\_\\\\",
        		"   (`----|-------\\\\-')",
        		"         @        @"
        };
        stickmanPositions.put(820,new Position(0,15));
        stickmanShowHide.put(820, true);
        stickmanSprites.put(820,canoeF1);
        stickmanSprites.put(850,canoeF2);
        stickmanSprites.put(880,canoeF1);
        stickmanSprites.put(910,canoeF2);
        stickmanSprites.put(940,canoeF1);
        stickmanSprites.put(970,canoeF2);
        stickmanSprites.put(1000,canoeF1);
        stickmanSprites.put(1030,canoeF2);
        stickmanSprites.put(1060,canoeF1);
        stickmanSprites.put(1090,canoeF2);
        stickmanSprites.put(1120,canoeF1);
        stickmanSprites.put(1150,canoeF2);
        stickmanPositions.put(850,new Position(3,15));
        stickmanPositions.put(880,new Position(6,15));
        stickmanPositions.put(910,new Position(9,15));
        stickmanPositions.put(940,new Position(11,15));
        stickmanPositions.put(970,new Position(14,15));
        stickmanPositions.put(1000,new Position(17,15));
        stickmanPositions.put(1030,new Position(20,15));
        stickmanPositions.put(1060,new Position(23,15));
        stickmanPositions.put(1090,new Position(26,15));
        stickmanPositions.put(1120,new Position(29,15));
        stickmanPositions.put(1150,new Position(32,15));
        
        //Story part 4: America
        stickmanShowHide.put(1180, false);
        backgroundShowHide.put(1180, false);
        narratorPositions.put(1180,new Position(0,0));
        narratorDialogue.put(1180, "They canoed...");
        narratorDialogue.put(1230, "And they canoed...");
        narratorDialogue.put(1290, "to the edge");
        narratorDialogue.put(1330, "to AMERICA");
        backgroundShowHide.put(1360, true);
        backgroundPositions.put(1360,new Position(10, 6));
        backgroundSprites.put(1360, new String[] {
        		"oooooooooo******************************",      
        		"oooooooooo------------------------------",      
        		"oooooooooo******************************",      
        		"oooooooooo------------------------------",    
        		"oooooooooo******************************",  
        		"----------------------------------------", 
        		"****************************************", 
        		"----------------------------------------", 
        		"****************************************", 
        		"----------------------------------------",
        });
        
        //Story part 5: stickman goes biking
        backgroundShowHide.put(1430, false);
        narratorDialogue.put(1430, "Stickman looked");
        narratorDialogue.put(1500, "and found a bike");
		
        stickmanSprites.put(1500, new String[] {
        	   "    __o",
               " _ |/<_",
               "(_)| (_)"
        });
        stickmanShowHide.put(1500, true);
        int counter = 0;
        for (int i=1500; i<1700; i+=3) {
        	stickmanPositions.put(i, new Position(1*counter, 6));
        	counter++;
        }
        
        narratorPositions.put(1700, new Position(0, 16));
        narratorDialogue.put(1700, "passing mountains");
        
        backgroundPositions.put(1700, new Position(0,0));
        backgroundShowHide.put(1700, true);
        backgroundSprites.put(1700, new String[] {
        		"                                                 *******", 
        		"                                 ~             *---*******", 
        		"                                ~             *-----*******", 
        		"                         ~                   *-------*******", 
        		"                        __      _   _!__     *-------*******", 
        		"                   _   /  \\_  _/ \\  |::| ___ **-----********   ~", 
        		"                 _/ \\_/^    \\/   ^\\/|::|\\|:|  **---*****/^\\_", 
        		"              /\\/  ^ /  ^    / ^ ___|::|_|:|_/\\_******/  ^  \\", 
        		"             /  \\  _/ ^ ^   /    |::|--|:|---|  \\__/  ^     ^\\___", 
        		"           _/_^  \\/  ^    _/ ^   |::|::|:|-::| ^ /_  ^    ^  ^   \\_", 
        		"          /   \\^ /    /\\ /       |::|--|:|:--|  /  \\        ^      \\", 
        		"         /     \\/    /  /        |::|::|:|:-:| / ^  \\  ^      ^     \\", 
        		"   _Q   / _Q  _Q_Q  / _Q    _Q   |::|::|:|:::|/    ^ \\   _Q      ^", 
        		"  /_\\)   /_\\)/_/\\\\)  /_\\)  /_\\)  |::|::|:|:::|          /_\\)", 
        		"_O|/O___O|/O_OO|/O__O|/O__O|/O__________________________O|/O_______________", 
        		"///////////////////////////////////////////////////////////////////////////"
        });
        counter = 0;
        for (int i=1700; i<1800; i+=3) {
        	stickmanPositions.put(i, new Position(1*counter, 12));
        	counter++;
        }

        
        //Story part 6: Stickman goes to the gym
        backgroundShowHide.put(1800, false);
        stickmanShowHide.put(1800, false);
        narratorPositions.put(1800, new Position(0,0));
        narratorDialogue.put(1800, "and then a gym");
        narratorDialogue.put(1860, "He decided to get");
        narratorDialogue.put(1900, "swol");
        
        String[] lifting = {
			"           5            5           5           5             5O--,---,--O", 
			"           5            5           5   ._O_.   5O--=-O-=--O  5   \\ O /",
			"    _._    5            5  ,_O_,    5O--<-+->--O5    '-'      5    - -",
			"   / O \\  5   ,-O-,    5O--(---)--O5      X    5      v      5      -", 
			"   \\| |/  5O--=---=--O 5    >'>    5     / \\  5      / )    5      / \\", 
			"O--+=-=+--O5   2\"2     5    - -    5    -   -  5    ~  z     5    =   ="
        };
        counter = 1900;
        for (int i=0; i<=5; i++) {
        	String liftingFrame[] = new String[lifting.length];
        	for (int j=0; j<lifting.length; j++) {
        		liftingFrame[j] = lifting[j].split("5")[i]; //#7 - string method
        	}
        	stickmanSprites.put(counter, liftingFrame);
        	counter+=25;
        }
        stickmanShowHide.put(1900, true);
        stickmanPositions.put(1900, new Position(0, 6));
        
        
        //Story part 7: closing
        stickmanShowHide.put(2100,false);
        narratorPositions.put(2100, new Position(0,0));
        narratorDialogue.put(2100, "And that is");
        narratorDialogue.put(2130, "the end of");
        narratorDialogue.put(2160, "Stickman Movie v1");
        narratorDialogue.put(2230, "Hope you enjoyed");
        
        backgroundSprites.put(2260, new String[] {
        	"     .-\"\"\"\"\"\"-.", 
        	"   .'          '.",
        	"  /   O    -=-   \\", 
        	" :                :", 
        	" |                |", 
        	" : ',          ,' :", 
        	"  \\  '-......-'  /", 
        	"   '.          .'",
        	"     '-......-'"
        });
        backgroundShowHide.put(2260, true);
        backgroundPositions.put(2260, new Position(20, 6));
        
        //Complete the requirements by using while loops
        counter = 0;
        int innerCounter = 0;
        do { //#4 - do while
        	if (stickmanSprites.containsKey(counter)) {
        		String[] sprite = stickmanSprites.get(counter);
        		innerCounter = 0;
        		System.out.println("Stickman sprite found on Frame#: "+counter);
        		while (innerCounter < sprite.length) {
        			System.out.println(sprite[innerCounter]);
        			innerCounter++;
        		}
        	}
        	if (backgroundSprites.containsKey(counter)) {
        		String[] sprite = backgroundSprites.get(counter);
        		innerCounter = 0;
        		System.out.println("Background sprite found on Frame#: "+counter);
        		while (innerCounter < sprite.length) { //#4 - regular while
        			System.out.println(sprite[innerCounter]);
        			innerCounter++;
        		}
        	}
        	if (narratorDialogue.containsKey(counter)) {
        		String dialogue = narratorDialogue.get(counter);
        		System.out.println("Narrator dialogue found on Frame#: "+counter);
        		System.out.println(dialogue);
        	}
        	counter++;
        } while (counter < 2260);
        
        System.out.println("\n\nRender engine shapes to render:\n"+movie);
		
		//RHmovie.setFrame(2100);
		RHmovie.renderFor(80000);

	}

}
