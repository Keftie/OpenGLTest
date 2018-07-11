package engineTester;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.apache.log4j.Logger;
import org.lwjgl.opengl.GL;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop {

    private static final Logger log = Logger.getLogger(MainGameLoop.class);

	public static void main(String[] args) {
		log.info("Started MainGameLoop...");
		DisplayManager.createDisplay();
		
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();

        // OpenGL expects vertices to be defined counter clockwise by default
        float[] vertices = {
                // Left bottom triangle
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                // Right top triangle
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f
        };

        RawModel model = loader.loadToVAO(vertices);
		
		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while(!glfwWindowShouldClose(DisplayManager.window)) {
		    renderer.prepare();

			// game logic
			
			// render
            renderer.render(model);

			DisplayManager.updateDisplay();
		}

		loader.cleanUp();
		DisplayManager.closeDisplay();
		log.info("Closing MainGameLoop...");
	}
}
