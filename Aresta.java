public class Aresta {
    
    private Vertice verticeSaida;
    private Vertice verticeChegada;
    private String nome;
    private int peso;
  
    public Aresta(String nome , int peso){
        this.nome = nome;
        this.peso = peso;
    }

    public Vertice getVerticeSaida() {
        return this.verticeSaida;
    }

    public void setVerticeSaida(Vertice verticeSaida) {
        this.verticeSaida = verticeSaida;
    }

    public Vertice getVerticeChegada() {
        return this.verticeChegada;
    }

    public void setVerticeChegada(Vertice verticeChegada) {
        this.verticeChegada = verticeChegada;
    }

    public void show(Grafo grafo) {
        if(grafo.isDirecionado()){
            System.out.print("| "+this.getVerticeSaida().getValor() + " --> " + this.getVerticeChegada().getValor()+" | ");
        } else {
            System.out.print("| "+this.getVerticeSaida().getValor() + " --- " + this.getVerticeChegada().getValor()+" | ");
        }
    }
    
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    


}
