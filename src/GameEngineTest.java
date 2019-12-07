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
        RHFlag.setFPS(0);
        
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
        RHFlag.setOnEnd(new Callable<Void>() {
			public Void call() {
				System.out.println("RenderFlag ended");
				RHScroll.renderFor(5000);
				return null;
			}
		});
        RHScroll.setOnEnd(new Callable<Void>() {
			public Void call() {
				System.out.println("Render ended");
				return null;
			}
		});
        
        //Start first demo
        RHFlag.renderFor(3000);
        

    }
}