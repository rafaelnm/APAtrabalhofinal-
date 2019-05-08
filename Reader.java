


import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Scanner;


public class Reader {
    private int nVertices;
    private ArrayList<Aresta> arestas;
    private ArrayList<Vertice> vertices;
    private String nomeArquivo;

    public Reader(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();
        leArquivo();
    }
    
    private void leArquivo() {
      
        Scanner scanner;
        String temp;
        int i = 0;
        int j = i+1;

        try {
            FileReader r = new FileReader(nomeArquivo);

            
            scanner = new Scanner(r);
            
            nVertices = Integer.valueOf(scanner.next());
            
            for (int c = 0; c < nVertices; ++c){
                vertices.add(new Vertice(c+1));
            }
            
            for (int c = 0; c < nVertices; ++c){
                arestas.add(new Aresta(vertices.get(c),vertices.get(c), 0));
            }

            while (scanner.hasNext()) {
                temp = scanner.next();
                if (temp.equalsIgnoreCase("0")) {
                    ++i;
                    j = i + 1;
                } else {
                    arestas.add(new Aresta(vertices.get(i-1), vertices.get(j-1), Float.valueOf(temp)));
                    ++j;
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Erro na leitura do arquivo.");
        }
    }

    public int getnVertices() {
        return nVertices;
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }
    
    public ArrayList<Vertice> getVertices(){
        return vertices;
    }
}
