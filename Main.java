import java.text.BreakIterator;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Grafo grafo = new Grafo();
        boolean isAtivo = true; 

        while (isAtivo) {
            System.out.println("0 - Sair do programa");
            System.out.println("1 - Iniciar o grafo");
            System.out.println("2 - Adicionar vertice");
            System.out.println("3 - Adicionar arestas");
            System.out.println("4 - Testar existencia de uma aresta entre dois vertices");
            System.out.println("5 - Removar um vertice");
            System.out.println("6 - Removar uma aresta");
            System.out.println("7 - Verificar vertices adjacentes");
            System.out.println("8 - Verificar o grau de vertice");
            System.out.println("9 - Obter grau minimo");
            System.out.println("10 - Obter grau máximo");
            System.out.println("11 - Obter grau médio");
            System.out.println("12 - Verificar se o grafo é conexo");
            System.out.println("13 - Ver matriz de adjacencias");
            
            System.out.println();
            System.out.print("Arestas => ");
            grafo.showArestas();

            System.out.println("\n");

            System.out.print("Vertices => ");
            grafo.showVertices();
            System.out.println("\n");

            System.out.print("Escolha uma opção : ");
            int option = input.nextInt();
            System.out.println();

            System.out.print("\033[H\033[2J");
            System.out.flush();

            switch (option) {
                case 1:
                    grafo.init();
                    System.out.println("Grafo iniciado com sucesso!");
                    break;
                case 2:
                    System.out.print("Qual o valor do vertice deseja adicionar? ");
                    int valor = input.nextInt();
                    grafo.addVertice(valor);
                    break;
                case 3:
                    System.out.print("Qual o nome da aresta? ");
                    String nomeAresta = input.next();
                    System.out.print("Qual o peso da aresta? ");
                    int peso = input.nextInt();
                    System.out.print("Qual o vertice de saida? ");
                    int valorSaida = input.nextInt();
                    System.out.print("Qual o vertice de chegada? ");
                    int valorChegada = input.nextInt();
                    grafo.addArestas(nomeAresta, peso, valorSaida, valorChegada);
                    break;
                case 4:
                    System.out.print("Qual o vertice de saida? ");
                    valorSaida = input.nextInt();
                    System.out.print("Qual o vertice de chegada?");
                    valorChegada = input.nextInt();
                    int indiceAresta = grafo.findArestas(valorSaida, valorChegada);
                    if (indiceAresta != -1) {
                        System.out.println("Aresta existe ");
                    } else {
                        System.out.println("Aresta não existe ");
                    }
                    break;
                case 5:
                    System.out.print("Qual o valor do vertice que deseja remover? ");
                    valor = input.nextInt();
                    grafo.removeVertice(valor);
                    break;
                case 6:
                    System.out.print("Qual o vertice de saida da aresta que deseja remover? ");
                    valorSaida = input.nextInt();
                    System.out.print("Qual o vertice de chegada da aresta que deseja remover? ");
                    valorChegada = input.nextInt();
                    grafo.removeArestas(valorSaida, valorChegada);
                    break;
                case 7:
                    ArrayList<Vertice> verticesAdjacentes;
                    System.out.print("Qual o vertice que deseja ver os adjacentes? ");
                    valor = input.nextInt();
                    verticesAdjacentes = grafo.getAdjacentes(valor);
                    System.out.print("vertices adjacentes = ");
                    for(int i = 0 ; i < verticesAdjacentes.size() ; i++){
                        System.out.print(" " + verticesAdjacentes.get(i).getValor() + "");
                    }
                    System.out.println();
                    break;
                case 8:
                    System.out.print("Qual o valor do vertice que deseja verificar o grau? ");
                    valor = input.nextInt();
                    int grau = grafo.getGrau(valor);
                    System.out.println("O grau do vertice " + valor + " = " + grau);
                    break;
                case 9:
                    grafo.minGrau();
                    break;
                case 10:
                    grafo.maxGrau();
                    break;
                case 11:
                    float grauMedio = grafo.medioGrau();
                    System.out.println("O grau do médio do grafo é: " + grauMedio);
                    break;
                case 12:
                    // Verificar se grafo é conexo
                    break;
                case 13:
                    grafo.matrizAdjacencias();
                    break;
                case 14:
                    // Caminho de euler
                    break;
                default:
                    isAtivo = false;
            }

        }

    }
}

// Limpa tela;

