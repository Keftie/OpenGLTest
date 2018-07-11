package renderEngine;

import org.apache.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    private static final Logger log = Logger.getLogger(Loader.class);

    private List<Integer> vaos = new ArrayList<>();
    private List<Integer> vbos = new ArrayList<>();

    /**
     * Load the vertices in a VAO and return the Model
     * @param postitions the vertices that make up the triangles
     */
    public RawModel loadToVAO(float[] postitions) {
        log.info("Creating model...");
        int vaoID = createVAO();
        log.debug("VAO created: " + vaoID);
        storeDataInAttributeList(0, postitions);
        unbindVAO();
        return new RawModel(vaoID, postitions.length / 3);
    }

    /**
     * Removes all VAO's and VBO's
     */
    public void cleanUp() {
        log.info("Cleaning up VAO's...");
        for(int vao : vaos) {
            GL30.glDeleteVertexArrays(vao);
        }
        log.info("Cleaning up VBO's...");
        for(int vbo : vbos) {
            GL15.glDeleteBuffers(vbo);
        }
        log.info("Cleanup DONE!");
    }

    /**
     * Create a new VAO
     * @return The VAO ID
     */
    private int createVAO() {
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    /**
     * Create a VBO and put the data in it
     * @param attributeNumber The position of where the data should be stored
     * @param data The data to store in the VBO
     */
    private void storeDataInAttributeList(int attributeNumber, float[] data) {
        log.info("Creating VBO...");
        int vboID = GL15.glGenBuffers();
        log.debug("VBO created: " + vboID);
        vbos.add(vboID);
        log.info("Storing data in VBO...");
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        log.debug("Store data in VBO DONE!");
    }

    /**
     * Unbind the VAO after you're done with it
     */
    private void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    /**
     * Store the data in a FloatBuffer
     * @param data The data to store in a FloatBuffer
     * @return The FloatBuffer with the data stored
     */
    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
