public class Vertex {
    private int id;
    private int weight;
    private int[] prereq;

    public Vertex(int jobNum, int jobWeight) {
        id = jobNum;
        weight = jobWeight;
    }

    public int getJobNumber( ) {
        return id;
    }

    public int getJobWeight( ) {
        return weight;
    }

    public void addPreReq(int[] requirements) {
        prereq = requirements;
    }

    public int[] getPrereq( ) {
        return prereq;
    }
}
