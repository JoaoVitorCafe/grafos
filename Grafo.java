import java.util.ArrayList;
import java.lang.Integer;

public class Grafo {

    private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
    private String nome;

    public Grafo() {
        this.nome = "Default";
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void init(){
        this.addVertice(1);
        this.addVertice(2);
        this.addVertice(3);
        this.addVertice(4);
        this.addArestas("a", 1, 1, 2);
        this.addArestas("b", 3, 3, 1);
        this.addArestas("c", 3, 1, 4);
        this.addArestas("d", 5, 4, 2);
        this.addArestas("e", 2, 3, 4);
    }

    public void showVertices() {
        // printa linearmente os vertices
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print("| " + vertices.get(i).getValor() + " | ");
        }
    }

    public void showArestas() {
        // printa linearmente as arestas
        for (int i = 0; i < arestas.size(); i++) {
            arestas.get(i).show();
        }
    }

    public ArrayList<Vertice> getVertices() {
        // Pegar vertices
        return this.vertices;
    }

    public void addVertice(int valor) {
        // Não é permitido vertices iguais
        // Adicionar vertices se ele não existe ainda
        if (this.findVertice(valor) != -1) {
            System.out.println("Vertice já existe");
            return;
        }

        Vertice vertice = new Vertice(valor);
        this.vertices.add(vertice);
        System.out.println("Vertice inserido com sucesso");
    }

    public int findVertice(int valor) {
        // Busca um vertice
        // Caso o vertice existir retorna o indice e caso ele n exista retorna -1
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getValor() == valor) {
                return i;
            }
        }
        return -1;
    }

    public void removeVertice(int valor) {
        // Remover vertices

        // Remoção das arestas ligadas ao vertice
        
          // Remove a aresta em que o vertice é o valor de saida ou chegada 
        for(int i = 0 ; i < vertices.size() ; i++){
            this.removeArestas(valor, this.vertices.get(i).getValor());
            this.removeArestas(this.vertices.get(i).getValor(), valor);
        }
        
        // for (int i = 0; i < arestas.size(); i++) {
        //     if (((arestas.get(i).getVerticeSaida().getValor() == valor) || (arestas.get(i).getVerticeChegada().getValor() == valor))) {
        //         arestas.remove(i);
        //     }
        // }

        // Remoção do vertice
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getValor() == valor) {
                vertices.remove(i);
                System.out.println("Vertice removido");
            }
        }
    }

    public ArrayList<Aresta> getArestas() {
        // Retorna as arestas
        return this.arestas;
    }

    public void addArestas(String nome, int peso, int valorSaida, int valorChegada) {
        // Adiciona uma aresta
        Aresta aresta = new Aresta(nome, peso);

        int indiceSaida = this.findVertice(valorSaida);

        // Verifica se um vertice de saida da aresta existe
        if (indiceSaida == -1) {
            System.out.println("Vertice de saida não  existe");
            return;
        }

        int indiceChegada = this.findVertice(valorChegada);

        // Verifica se um vertice de chegada da aresta existe
        if (indiceChegada == -1) {
            System.out.println("Vertice de chegada não  existe");
            return;
        }

        if (vertices.get(indiceSaida) == vertices.get(indiceChegada)) {
            System.out.println("Não é permitido a criação de uma aresta entre um único vertice");
            return;
        }

        // Não permite a criação de arestas iguais
        if(findArestas(valorSaida, valorChegada) != -1){
            System.out.println("Não é permitido a criação de uma arestas iguais");
            return;
        }

        aresta.setVerticeSaida(this.vertices.get(indiceSaida));
        aresta.setVerticeChegada(this.vertices.get(indiceChegada));


        this.arestas.add(aresta);
        System.out.println("Aresta inserida com sucesso");
    }

    public int findArestas(int valorSaida, int valorChegada) {
        // Testa existencia de uma aresta entre dois vertices
        for (int i = 0; i < arestas.size(); i++) {
            if (arestas.get(i).getVerticeSaida().getValor() == valorSaida
                    && arestas.get(i).getVerticeChegada().getValor() == valorChegada) {
                return i;
            }
        }
        return -1;
    }

    public void removeArestas(int valorSaida, int valorChegada) {
        // Remover arestas
        int indiceAresta = this.findArestas(valorSaida, valorChegada);
        if (indiceAresta != -1) {
            arestas.remove(indiceAresta);
            System.out.println("Aresta removida");
        }
    }

    public int getGrau(int valor) {
        int grau = 0;
        // Retorna -1 se não existe
        if (this.findVertice(valor) == -1) {
            return -1;
        }

        for (int i = 0; i < arestas.size(); i++) {
            if (arestas.get(i).getVerticeSaida().getValor() == valor
                    || arestas.get(i).getVerticeChegada().getValor() == valor) {
                grau++;
            }
        }
        return grau;
    }

    public int maxGrau() {
        // calcula o grau maximo
        if (vertices.size() == 0) {
            System.out.println("Não existem vertices no grafo");
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int indexVertice = 0;

        for (int i = 0; i < vertices.size(); i++) {
            int valorVertice = vertices.get(i).getValor();
            if (this.getGrau(valorVertice) >= max) {
                max = this.getGrau(valorVertice);
                indexVertice = i;
            }
        }

        System.out.println(
                "O vertice de maior grau é o vertice " + vertices.get(indexVertice).getValor() + " com grau " + max);
        return indexVertice;
    }

    public int minGrau() {
        // calcula o grau minimo
        if (vertices.size() == 0) {
            System.out.println("Não existem vertices no grafo");
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int indexVertice = 0;

        for (int i = 0; i < vertices.size(); i++) {
            int valorVertice = vertices.get(i).getValor();
            if (this.getGrau(valorVertice) <= min) {
                min = this.getGrau(valorVertice);
                indexVertice = i;
            }
        }

        System.out.println(
                "O vertice de menor grau é o vertice " + vertices.get(indexVertice).getValor() + " com grau " + min);
        return indexVertice;
    }

    public float medioGrau() {
        // calcula o grau medio
        int tamanho = vertices.size();

        if (tamanho == 0) {
            System.out.println("Não existem vertices no grafo");
            return 0;
        }

        float soma = 0;
        for (int i = 0; i < vertices.size(); i++) {
            int valorVertice = vertices.get(i).getValor();
            soma += this.getGrau(valorVertice);
        }

        return soma / (float) tamanho;
    }

    public ArrayList<Vertice> getAdjacentes(int valor){
        
        ArrayList<Aresta> arestasAdjacentes = new ArrayList<Aresta>();
        ArrayList<Vertice> verticesAdjacentes = new ArrayList<Vertice>();
        
        int indiceAresta;
        
        // pegar as arestas que possui o vertice como saida
        for(int i = 0 ; i < vertices.size() ; i++){
            indiceAresta = this.findArestas(valor, vertices.get(i).getValor());
            if(indiceAresta != -1){
                arestasAdjacentes.add(this.arestas.get(indiceAresta));
            }
        }

        // pegar as arestas que possui o vertice  chegada
        for(int i = 0 ; i < vertices.size() ; i++){
            indiceAresta = this.findArestas(vertices.get(i).getValor() , valor );
            if(indiceAresta != -1){
                arestasAdjacentes.add(this.arestas.get(indiceAresta));
            }
        }

        // pegar os vertices que sejam diferentes do passado como valor
        for(int i = 0 ; i < arestasAdjacentes.size() ; i++){
            if(arestasAdjacentes.get(i).getVerticeChegada().getValor() != valor){
                verticesAdjacentes.add(arestasAdjacentes.get(i).getVerticeChegada());
            }

            if(arestasAdjacentes.get(i).getVerticeSaida().getValor() != valor){
                verticesAdjacentes.add(arestasAdjacentes.get(i).getVerticeSaida());
            }
        }

        return verticesAdjacentes;
    }


}
