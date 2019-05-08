


import java.util.Arrays;



public class MetVND {
    private Grafo grafo;
    private int[] solConstruida;

    public MetVND(Grafo G, int[] solucaoConstruida) {
        this.grafo = G;
        this.solConstruida = solucaoConstruida;
    }
    
    public int[] run(){
        int[] melhorVizinho = this.solConstruida;
        int[] sTemp;
        int r = 2;
        int k = 1;
        
        while (k <= r){
            if (k == 1){
                sTemp = explorarSwap(melhorVizinho);
            }else{
                sTemp = explorarDoisOpt(melhorVizinho);
            }
            
            if (valorSol(sTemp) < valorSol(melhorVizinho)){
                melhorVizinho = sTemp;
                k = 1;
            }else{
                ++k;
            }
        }
       
        return melhorVizinho;
    }
    
    
  //FUNCAO OBJTV
    public double valorSol(int[] s) {
        double solucao = 0;

       
        for (int c = 1; c < s.length; ++c) {
            solucao += grafo.getAresta(s[c-1], s[c]).getPeso();
        }
        solucao += grafo.getAresta(s[s.length-1], s[0]).getPeso();
        
        return solucao;
    }
    //EXPLORAR VIZINHOS
    private int[] explorarSwap(int[] s){
        int[] bestV = s;
        int[] sAux;
        double melhorSolucao = valorSol(s);
        
        for (int c = 0; c < s.length; ++c){
            for (int k = c+1; k < s.length; ++k){
                sAux = swap(c, k, s);
              
                if (valorSol(sAux) < melhorSolucao){

                    melhorSolucao = valorSol(sAux);
                    bestV = sAux;
                }
            }
        }
        
        return bestV;
    }
    
    private int[] explorarDoisOpt(int[] s){
        int[] bestViz = s;
        int[] sAux;
        double bestSol = valorSol(s);
       
        
        for (int c = 0; c < s.length; ++c){
            for (int k = c+1; k < s.length; ++k){
                sAux = doisOpt(c, k, s);
                
                if (valorSol(sAux) < bestSol){
                    bestSol = valorSol(sAux);
                    bestViz = sAux;
                }
            }
        }
        
        return bestViz;
    }
    
    
    //SWAP 2 VERTICES
    private int[] swap(int i, int j, int[] s){
        int[] arrayAux = s.clone();
        int aux = arrayAux[i];
        arrayAux[i] = arrayAux[j];
        arrayAux[j] = aux;
        
        return arrayAux;
    }
    
    //2-OPT
    private int[] doisOpt(int i, int j, int[] s){
        int[] subArray;
        int[] sAux = s.clone();
        int count = 0;
        
        subArray = Arrays.copyOfRange(sAux, i, j+1);
       
        inverterArray(subArray);
        
        
        for (int k = i; k <= j; ++k){
            sAux[k] = subArray[count];
            ++count;
        }
        return sAux;
    }
    
  
    
    private void inverterArray(int[] a){
        int count = 0;
        
        int[] aux = a.clone();
        for (int i = a.length-1; i >= 0; --i){
            a[i] = aux[count];
            ++count;
        }
    }
    
    
}
