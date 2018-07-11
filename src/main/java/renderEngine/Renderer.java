package renderEngine;

import org.apache.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Renderer {

    private static final Logger log = Logger.getLogger(Renderer.class);

    /**
     * Tell OpenGL to render the game. Will be runned once every frame
     */
    public void prepare() {
        log.debug("Preparing renderer...");
        GLFW.glfwSwapBuffers(DisplayManager.window);
        GL11.glClearColor(1, 0, 0, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    /**
     * Render the Model to the screen
     */
    public void render(RawModel model) {
        log.debug("Rendering model " + model);
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
}
