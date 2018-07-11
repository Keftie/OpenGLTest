package engineTester;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import org.lwjgl.Version;
import org.lwjgl.opengl.GL;

import renderEngine.DisplayManager;

public class MainGameLoop {

	public static void main(String[] args) {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");
		DisplayManager.createDisplay();
		
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();
		
		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while(!glfwWindowShouldClose(DisplayManager.window)) {
			// game logic
			
			// render

			glfwSwapBuffers(DisplayManager.window); // swap the color buffers
			DisplayManager.updateDisplay();
		}
		
		DisplayManager.closeDisplay();
	}
}
