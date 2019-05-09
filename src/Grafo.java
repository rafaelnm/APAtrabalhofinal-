import java.util.List;


public class Grafo {
    private int nV;
    private List<Vertice> vertices;
    private List<Aresta> arestas;

    public Grafo(int nV, List<Vertice> vertices, List<Aresta> arestas) {
        this.nV = nV;
        this.vertices = vertices;
        this.arestas = arestas;
    }

    public int getnVertices() {
        return nV;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }
    
    public Vertice getVertice(int identificador){
        for (Vertice c: vertices){
            if (c.getId() == identificador){
                return c;
            }
        }
        return null;
    }
        
    public Aresta getAresta(int v1, int v2){

        for (Aresta a: arestas){
            if (((a.getV1().getId() == v1) && (a.getV2().getId() == v2))
                    || ((a.getV2().getId() == v1) && (a.getV1().getId() == v2))){
                return a;
            }
        }
        return null;
    }
}
