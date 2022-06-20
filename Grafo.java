import java.util.ArrayList;

public class Grafo {
    
private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
private String nome;

    public Grafo(String nome) {
        // Inicir um grafo
        this.nome = nome;  
    }
   
    public ArrayList<Vertice> getVertices() {
        // Pegar vertices
        return this.vertices;
    }

    public void addVertice(Vertice vertice) {
        // Adicionar vertices
        this.vertices.add(vertice);
    }

    public void removeVertice(int valor) {
        // Remover vertices
        /*OBS remover a arestas ligada ao vertice também*/
        for(int i = 0 ; i < vertices.size() ; i++) {
            if(vertices.get(i).getValor() == valor) {
                vertices.remove(i);
            }
        }  
    }

    public ArrayList<Aresta> getArestas() {
        // Retorna as arestas
        return this.arestas;
    }

    public void addArestas(Aresta aresta) {
        // Adiciona uma aresta
        this.arestas.add(aresta);
    }

    public void removeArestas(int valor1 , int valor2) {
        // Remover arestas
        for(int i = 0 ; i < arestas.size() ; i++) {
            if(arestas.get(i).getVertice1().getValor() == valor1 && arestas.get(i).getVertice2().getValor() == valor2) {
               arestas.remove(i);
            }
        }  
    }

    public Aresta findArestas(int valor1 , int valor2) {
        // Testa existencia de uma aresta entre dois vertices
        for(int i = 0 ; i < arestas.size() ; i++) {
            if(arestas.get(i).getVertice1().getValor() == valor1 && arestas.get(i).getVertice2().getValor() == valor2) {
              return arestas.get(i);
            }
        }

        return new Aresta(null);
    }

    public int getGrau(int valor) {
        int grau = 0;
        for(int i = 0 ; i < arestas.size() ; i++) {
            if(arestas.get(i).getVertice1().getValor() == valor && arestas.get(i).getVertice2().getValor() == valor  ){
               grau +=2;
            } 
            else if (arestas.get(i).getVertice1().getValor() == valor || arestas.get(i).getVertice2().getValor() == valor){
               grau++;
            }
        }
        
        return grau;
    }

    public Vertice maxGrau() {
        int max = 0;
        int indexVertice = 0;
        for(int i = 0 ; i < vertices.size() ; i++) {
            int valorVertice = vertices.get(i).getValor() ;
            if(this.getGrau(valorVertice) > max) {
                max = this.getGrau(valorVertice);
                indexVertice = i;
            }
        }
        
        return vertices.get(indexVertice);
    }





    public String getNome() {
        return this.nome;
    }

    public void setValor(String nome) {
        this.nome = nome;
    }
}

 
