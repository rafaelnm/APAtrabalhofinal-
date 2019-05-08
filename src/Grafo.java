



import java.util.ArrayList;
import java.util.List;


public class Grafo {
    private int nV;
    private List<Vertice> vertices, candidatos;
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
    
    private void inicializarCandidatos(){
        this.candidatos = new ArrayList<>();
        
        for (int i = 0; i < nV; ++i){
            this.candidatos.add(new Vertice(i+1));
        }
    }
    
    //Vizinho mais proximo
    public int[] getSolucaoInicial(int identificador){
        int[] solucaoInicial = new int[nV];
        int contador = 0;
        float bestPeso = Float.MAX_VALUE;
        Vertice melhorVertice = null;
                
        inicializarCandidatos();
        
        
        while(!candidatos.isEmpty()){
            bestPeso = Float.MAX_VALUE;
            for (Vertice c: candidatos){
                if (identificador == c.getId()){
                    bestPeso = 0;
                    melhorVertice = c;
                } else {
                    if (getAresta(identificador, c.getId()).getPeso() < bestPeso) {
                        bestPeso = getAresta(identificador, c.getId()).getPeso();
                        melhorVertice = c;
                    }
                }

                
            }
            solucaoInicial[contador] = melhorVertice.getId();
            identificador = solucaoInicial[contador];
            ++contador;
            candidatos.remove(melhorVertice);
        }
        return solucaoInicial;
    }
}
