import java.util.concurrent.*;
import java.util.function.*;

/*
 * Idea: separate threading for render, generate frame so that it doesn't overload eclipse terminal
 */

public class RenderHandler {
	private GameEngine g; //GameEngine object to keep track of
	private int fps; //Render fps - 0 is "freeze frame"
	private int frameCount; //How many frames have been rendered
	private boolean customFCount = false; //Has the user specified a start frame count?
	
	private Supplier<Void> onEnd; //onEnd function - runs when render is finished. Supplier type because no args
	private boolean onEndDefined = false;
	
	private Function<Integer,Void> onFrame; //onFrame function - runs every frame, takes FrameCount as an argument
	private boolean onFrameDefined = false;
	//Create the scheduler
	final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public RenderHandler(GameEngine g) {
		this.g = g;
		this.fps = 30;
		//Didn't pass any frame or end handlers - don't schedule
		onFrameDefined = false;
		onEndDefined = false;
		//Reset frameCount
		frameCount = 0;
	}

	public void renderFor(int timeAlive) {
		//Reset frameCount
		if (!customFCount) {
			frameCount = 0;
		}
		//Run it at least once
		if (fps > 0) { //#1 - relational operator
	        Runnable renderer = new Runnable() {
	            public void run() {
	            	//Render the frame
	                g.clearTerminal();
	                System.out.println(g.render());
	                //Call onFrame if it's defined
	                if (onFrameDefined) {
	                	try {
		                	onFrame.apply(frameCount);
		                } catch (Exception e) {
		                	System.out.println("Error running onFrame callback in RenderHandler (non0fps)");
		                }
	                }
	                //Increment fCount
	                frameCount++;
	            }
	        };
	        final ScheduledFuture<?> renderHandle = scheduler.scheduleAtFixedRate(renderer, 0, 1000/fps, TimeUnit.MILLISECONDS); //Delay 0, period 1 second
	        Runnable stopRender = new Runnable() {
	            public void run() {
	            	//Stop rendering
	                renderHandle.cancel(true);
	                //Call onEnd if it's defined
	                if (onEndDefined) {
	                	try {
	                		onEnd.get();
		                } catch (Exception e) {
		                	System.out.println("Error running onEnd callback in RenderHandler (non0fps)");
		                }
	                }
	            }
	        };
	        //Schedule renderStopper
	        scheduler.schedule(stopRender, timeAlive, TimeUnit.MILLISECONDS);
	        //Shutdown hook
	        Runtime.getRuntime().addShutdownHook(new Thread() {
	            public void run() {
	                System.out.println("RENDERSTOP - Prgm exit");
	                renderHandle.cancel(true);
	            }
	        });
		} else {
			//Render only once
			g.clearTerminal();
            System.out.println(g.render());
          //Increment fCount
            frameCount++;
            //Call onFrame if it's defined
            if (onFrameDefined) {
            	try {
                	onFrame.apply(frameCount);
                } catch (Exception e) {
                	System.out.println("Error running onFrame callback in RenderHandler (0fps)");
                }
            }
            //Create onEnd runnable if it's defined
            if (onEndDefined) {
	            Runnable doOnEnd = new Runnable() {
		            public void run() {
		                try {
		                	onEnd.get();
		                } catch (Exception e) {
		                	System.out.println("Error running onEnd callback in RenderHandler (0fps)");
		                }
		            }
		        };
		        //Aaaand schedule the onEnd event so that it still fires
		        scheduler.schedule(doOnEnd, timeAlive, TimeUnit.MILLISECONDS);
            }
		}
	}
	
	/*
	 * GETTERS/SETTERS
	 */
	public void setFPS(int newFPS) {
		if (newFPS < 0) {
			newFPS = 0;
		}
		fps = newFPS;
	}
	public int getFPS() {
		return fps;
	}
	public void setOnEnd(Supplier<Void> newEnd) {
		onEnd = newEnd;
		onEndDefined = true; //Set flag so that it gets scheduled
	}
	public void setOnFrame(Function<Integer,Void> newFrame) {
		onFrame = newFrame;
		onFrameDefined = true; //Set flag so that it gets scheduled
	}
	public void setFrame(int newFCount) { //Mostly for debug
		customFCount = true;
		frameCount = newFCount;
	}
}
