import java.util.ArrayList;
import java.lang.Integer;

public class Grafo {

    private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
    private String nome;

    public Grafo() {
        this.nome = "Default";
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
        for (int i = 0; i < arestas.size(); i++) {
            if ((arestas.get(i).getVerticeSaida().getValor() == valor
                    || arestas.get(i).getVerticeChegada().getValor() == valor)) {
                arestas.remove(i);
            }
        }

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

        aresta.setVerticeSaida(vertices.get(indiceSaida));
        aresta.setVerticeChegada(vertices.get(indiceChegada));

        this.arestas.add(aresta);
        System.out.println("Aresta inserida com sucesso");
    }

    public int findArestas(int valorSaida, int valorChegada) {
        // Testa existencia de uma aresta entre dois vertices
        // Permitir duas arestas iguais?
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

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
