import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.Instant;



public class MetGrasp {
    private int graspMax;
    private Grafo G;
    private double alfa;

    public MetGrasp(Grafo g, int graspMax, double alfa) {
        this.graspMax = graspMax;
        this.G = g;
        this.alfa = alfa;
    }
    
    //GRASP
    public void run(){
        
        
        int[] solucao = new int[G.getnVertices()];
        int[] solconstruida = new int[G.getnVertices()];
        int[] solAux = new int[G.getnVertices()];
        Instant start;
        Instant finish;
        long timeElapsed;
        //start = Instant.now(); 
        
        //finish = Instant.now();
        //long timeElapsed = Duration.between(start, finish).toNanos(); 
        
       // System.out.print(" Tempo da solucao construtiva (nanos)=> ");
        //System.out.println(timeElapsed);
        
        double bestVal = Double.MAX_VALUE;
        System.out.print(" Valor da solucao construtiva=> ");
        System.out.println(valorSolucao(solconstruida));
        
        start = Instant.now();
        for (int i = 0; i < graspMax; ++i){
        	solconstruida = construirSolucaoAleatoria(1, alfa);
            solAux = new MetVND(G, solconstruida).run();
            
            if (valorSolucao(solAux) < bestVal)
                bestVal = valorSolucao(solAux);
                solucao = solAux; 
        }
        finish = Instant.now();
        timeElapsed = Duration.between(start, finish).toMillis();
        System.out.print(" Tempo da solucao meta heuristica=> ");
        System.out.println(timeElapsed);
        System.out.print("Rota encontrada => ");
        escreverArray(solucao);
        System.out.print(" Valor da solucao => ");
        System.out.println(valorSolucao(solucao));
    }
    
    public int[] construirSolucaoAleatoria(int id, double alfa){
    	int count = 0;
    	Instant start = Instant.now(); 
    	int[] solucao = new int[G.getnVertices()];
        List<double[]> valSolucoespossiveis;
        
        
        valSolucoespossiveis = inicializarCandidatos(id);
        
        try {
            while (!valSolucoespossiveis.isEmpty()) {
                double[] aux = valSolucoespossiveis.get((int) (Math.random() * ((int) valSolucoespossiveis.size() * alfa + 1)));
                solucao[count] = (int) aux[1];
                valSolucoespossiveis.remove(aux);
                ++count;
            }
        } catch (IndexOutOfBoundsException e) {
            double[] aux = valSolucoespossiveis.get(0);
            solucao[count] = (int) aux[1];
            valSolucoespossiveis.remove(aux);
            ++count;
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toNanos(); 
        
        System.out.print(" Tempo da solucao construtiva (nanos)=> ");
        System.out.println(timeElapsed);
        return solucao;
    }
    
    private List<double[]> inicializarCandidatos(int id){
        List<double[]> custosCandidatos = new ArrayList<>();
        double aux[][] = new double[G.getnVertices()][2];
        int count = 0;
        
        for (Vertice v : G.getVertices()) {
            if (id == v.getId()) {
                aux[count][0] = 0;
                aux[count][1] = v.getId();
                ++count;
            } else {
                aux[count][0] = G.getAresta(id, v.getId()).getPeso();
                aux[count][1] = v.getId();
                ++count;
            }
        }
        
        
        java.util.Arrays.sort(aux, new java.util.Comparator<double[]>() {       
           public int compare(double[] a, double[] b) {
                return Double.compare(a[0], b[0]);
            }
        });
        
        for (double[] d: aux){
            custosCandidatos.add(d);
        }
        
        return custosCandidatos;
    }
    
    private double valorSolucao(int[] s) {
        double solucao = 0;

        for (int c = 1; c < s.length; ++c) {
            solucao += G.getAresta(s[c-1], s[c]).getPeso();
        }
        solucao += G.getAresta(s[s.length-1], s[0]).getPeso();
        
        return solucao;
    }
    
    private void escreverArray(int[] a){
        for (int i = 0; i < a.length; ++i){
            System.out.print(a[i] + " ");
        }
    }
}
