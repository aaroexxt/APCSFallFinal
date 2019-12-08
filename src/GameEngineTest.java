import java.util.concurrent.Callable;

public class GameEngineTest {
    public static void main(String[] args) {
        /*
         * DEMO 1: AMERICAN FLAG
        */
        Rectangle background = new Rectangle();
        background.setFillChar('-');
        Square stars = new Square(0, 0, 5, true);
        stars.setFillChar('o');

        Rectangle flagPart1 = new Rectangle(0, 0, 20, 1, true);
        Rectangle flagPart2 = new Rectangle(0, 2, 20, 1, true);
        Rectangle flagPart3 = new Rectangle(0, 4, 20, 1, true);
        Rectangle flagPart4 = new Rectangle(0, 6, 20, 1, true);
        Rectangle flagPart5 = new Rectangle(0, 8, 20, 1, true);
        
        //Construct a GameEngine
        GameEngine americanFlag = new GameEngine(background, flagPart1, flagPart2, flagPart3, flagPart4, flagPart5, stars);
        americanFlag.setDisplaySize(50, 25);
        RenderHandler RHFlag = new RenderHandler(americanFlag);
        RHFlag.setFPS(0); //Only render it once
        
        /*
         * DEMO 2: RECTANGLE SCROLLING
        */

        Rectangle rect = new Rectangle();
        GameEngine scrollTest = new GameEngine(rect);
        scrollTest.setDisplaySize(70,25);
        RenderHandler RHScroll = new RenderHandler(scrollTest);
        
        /*
         * SETUP DEMO LINKING
         */
        
        //Setup onEnd events so that demo runs one after another
        class OnEndEvents { //Class to store onEnd events
        	public Void EndFlag() {
        		System.out.println("RenderFlag ended");
    			RHScroll.renderFor(10000);
    			return null;
        	}
        	public Void EndScroll() {
        		System.out.println("Render ended");
				return null;
        	}
        }
        OnEndEvents endEvents = new OnEndEvents(); //instantiate
        RHFlag.setOnEnd(endEvents::EndFlag); //pass using lambda
        RHScroll.setOnEnd(endEvents::EndScroll);
        
        class OnFrameEvents { //Class to store onFrame events
        	public Void FrameFlag(int fCount) {
    			Position rectPos = rect.getPosition();
        		if (fCount < 10) {
        			rect.setPosition(rectPos.x+1, rectPos.y+1);
        		} else {
        			rect.setPosition(rectPos.x-1, rectPos.y-1);
        		}
    			return null;
        	}
        }
        OnFrameEvents frameEvents = new OnFrameEvents();
        RHScroll.setOnFrame(frameEvents::FrameFlag);
        
        //Start first demo
        RHFlag.renderFor(3000);
        

    }
}