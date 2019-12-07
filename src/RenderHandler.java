import java.util.concurrent.*;

public class RenderHandler {
	private GameEngine g;
	private int fps;
	
	private Callable<Void> onEnd;
	private boolean onEndDefined = false;
	
	private Callable<Void> onFrame;
	private boolean onFrameDefined = false;
	final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public RenderHandler(GameEngine g) {
		this.g = g;
		this.fps = 30;
		//Didn't pass any frame or end handlers - don't schedule
		onFrameDefined = false;
		onEndDefined = false;
	}

	public void renderFor(int timeAlive) {
		//Run it at least once
		if (fps > 0) {
	        Runnable renderer = new Runnable() {
	            public void run() {
	            	//Render the frame
	                g.clearTerminal();
	                System.out.println(g.render());
	                //Call onFrame if it's defined
	                if (onFrameDefined) {
	                	try {
		                	onFrame.call();
		                } catch (Exception e) {
		                	System.out.println("Error running onFrame callback in RenderHandler (non0fps)");
		                }
	                }
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
		                	onEnd.call();
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
            //Call onFrame if it's defined
            if (onFrameDefined) {
            	try {
                	onFrame.call();
                } catch (Exception e) {
                	System.out.println("Error running onFrame callback in RenderHandler (0fps)");
                }
            }
            //Create onEnd runnable if it's defined
            if (onEndDefined) {
	            Runnable doOnEnd = new Runnable() {
		            public void run() {
		                try {
		                	onEnd.call();
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
		fps = newFPS;
	}
	public int getFPS() {
		return fps;
	}
	public void setOnEnd(Callable<Void> newEnd) {
		onEnd = newEnd;
		onEndDefined = true; //Set flag so that it gets scheduled
	}
	public void setOnFrame(Callable<Void> newFrame) {
		onFrame = newFrame;
		onFrameDefined = true; //Set flag so that it gets scheduled
	}
}
