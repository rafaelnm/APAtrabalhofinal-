


public class Main {

    public static void main(String[] args) {
        
    	
    	Reader leitor = new Reader("bier127.txt");
        
        
        Grafo G = new Grafo(leitor.getnVertices(), leitor.getVertices(), leitor.getArestas());

        int iteracoesGrasp = 2;
        
        MetGrasp grasp = new MetGrasp(G, iteracoesGrasp);
        
        
        //LACO PARA INICIALIZAR GRASP 10 VEZES COM N(iteracoesGrasp) ITERACOES)
        for (int i = 0; i < 10; ++i) grasp.run();
        
    }
    
}
