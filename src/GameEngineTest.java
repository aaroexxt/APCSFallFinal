public class GameEngineTest {
    public static void main(String[] args) {
        /*
         * DEMO 1: AMERICAN FLAG
         * This demo shows how compositing multiple shapes into a single GameEngine object can result in complex or cool graphics (or you could use a BitMap)
        */
        Rectangle flagBackground = new Rectangle();
        flagBackground.setFillChar('-');
        Square stars = new Square(0, 0, 5, true);
        stars.setFillChar('o');

        Rectangle flagPart1 = new Rectangle(0, 0, 20, 1, true);
        Rectangle flagPart2 = new Rectangle(0, 2, 20, 1, true);
        Rectangle flagPart3 = new Rectangle(0, 4, 20, 1, true);
        Rectangle flagPart4 = new Rectangle(0, 6, 20, 1, true);
        Rectangle flagPart5 = new Rectangle(0, 8, 20, 1, true);
        
        //Construct a GameEngine
        GameEngine americanFlag = new GameEngine(flagBackground, flagPart1, flagPart2, flagPart3, flagPart4, flagPart5, stars);
        americanFlag.setDisplaySize(50, 25);
        RenderHandler RHFlag = new RenderHandler(americanFlag);
        RHFlag.setFPS(3); //Only render it once
        
        /*
         * DEMO 2: RECTANGLE SCROLLING
         * This demo shows how powerful the tools are in GameEngine to control objects on a frame-by-frame basis
        */

        Rectangle rect = new Rectangle();
        GameEngine scrollTest = new GameEngine(rect);
        scrollTest.setDisplaySize(70,25);
        RenderHandler RHScroll = new RenderHandler(scrollTest);
        
        /*
         * DEMO 3: SIMPLE ANIMATIONS
         */
        BitMap canoe = new BitMap();
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
        canoe.setStringTable(canoeF1);
        
        Rectangle water = new Rectangle(0,4,35,1,true);
        water.setFillChar('~');
        GameEngine canoeTest = new GameEngine(water, canoe);
        canoeTest.setDisplaySize(70, 25);
        RenderHandler RHCanoe = new RenderHandler(canoeTest);
        RHCanoe.setFPS(5);
        
        /*
         * DEMO 4: SIMPLE SCENE ANIMATION
         */
//        BitMap player = new BitMap();
//        String 
//        BitMap background = new BitMap();
//        String[] hoopAndCourt = {
//        		"         -----------",
//        		"         |         |",
//        		"         |   ----  |",
//        		"         |   |  |  |",
//        		"         |   ----  |",
//        		"         ----   ----",
//        		"             |  |",
//        		"             |  |",
//        		"             |  |",
//        		"             |  |",
//        		"             |  |",
//        		"----------------------------"
//        };
//        background.setStringTable(hoopAndCourt);
//        GameEngine sceneTest = new GameEngine(background, player);
        
        /*
         * DEMO 5: COMPLEX SCENE ANIMATION
         */
        
        
        
        /*
         * SETUP DEMO LINKING
         */
        
        //Setup onEnd events so that demo runs one after another
        class OnEndEvents { //Class to store onEnd events
        	public Void EndFlag() {
        		System.out.println("RenderFlag ended");
    			RHScroll.renderFor(5000);
    			return null;
        	}
        	public Void EndScroll() {
        		System.out.println("RenderScroll ended");
    			RHCanoe.renderFor(5000);
    			return null;
        	}
        	public Void EndCanoe() {
        		System.out.println("Demo finished");
				return null;
        	}
        }
        OnEndEvents endEvents = new OnEndEvents(); //instantiate
        RHFlag.setOnEnd(endEvents::EndFlag); //pass using lambda
        RHScroll.setOnEnd(endEvents::EndScroll);
        RHCanoe.setOnEnd(endEvents::EndCanoe);
        
        class OnFrameEvents { //Class to store onFrame events
        	public Void FrameScroll(int fCount) {
    			Position rectPos = rect.getPosition();
    			System.out.println("FrameCount:"+fCount);
        		while (fCount > 20) {
        			fCount-=20;
        		}
    			if (fCount <= 10) {
        			rect.setPosition(rectPos.x+1, rectPos.y+1);
        		} else {
        			rect.setPosition(rectPos.x-1, rectPos.y-1);
        		}
    			return null;
        	}
        	public Void FrameCanoe(int fCount) {
        		if (fCount%3 == 0) {
        			canoe.setStringTable(canoeF1);
        		} else {
        			canoe.setStringTable(canoeF2);
        		}
        		Position canoePos = canoe.getPosition();
        		canoe.setPosition((canoePos.x > 35) ? 0 : canoePos.x+3, canoePos.y);
        		return null;
        	}
        }
        OnFrameEvents frameEvents = new OnFrameEvents();
        RHScroll.setOnFrame(frameEvents::FrameScroll);
        RHCanoe.setOnFrame(frameEvents::FrameCanoe);
        
        //Start first demo
        RHFlag.renderFor(2000);
        //RHCanoe.renderFor(5000);
        

    }
}