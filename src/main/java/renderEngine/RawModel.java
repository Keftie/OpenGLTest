package renderEngine;

public class RawModel {

    private int vaoID;
    private int vertexCount;

    public RawModel(int vaoID, int vertexCount) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public String toString() {
        return "RawModel[vaoID=" + vaoID + ",vertexCount=" + vertexCount + "]";
    }
}
