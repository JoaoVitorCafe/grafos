import java.util.ArrayList;
import java.lang.Integer;

public class Grafo {

    private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
    private String nome;
    private boolean direcionado;

    public Grafo(Boolean isDirecionado) {
        this.nome = "Default";
        this.direcionado = isDirecionado;
    }

    public boolean isDirecionado(){
        return this.direcionado;
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
            arestas.get(i).show(this);
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
        if(findAresta(valorSaida, valorChegada) != -1){
            System.out.println("Não é permitido a criação de uma arestas iguais");
            return;
        }

        aresta.setVerticeSaida(this.vertices.get(indiceSaida));
        aresta.setVerticeChegada(this.vertices.get(indiceChegada));


        this.arestas.add(aresta);
        System.out.println("Aresta inserida com sucesso");
    }

    public int findAresta(int valorSaida, int valorChegada) {
        // Testa existencia de uma aresta entre dois vertices
        
        if(!this.isDirecionado()){
            for (int i = 0; i < arestas.size(); i++) {
                // se não for direcionado "tanto faz" os vertice serem entradas ou saidas;
                
                // compara se o valor de saida é igual ao valor de saida e o valor de chegada é igual ao valor de chegada
                if ((arestas.get(i).getVerticeSaida().getValor() == valorSaida)
                        && (arestas.get(i).getVerticeChegada().getValor() == valorChegada)) {
                    return i;
                }

                // compara se o valor de chegada é igual ao valor de saida e o valor de saida é igual ao valor de chegada
                if ((arestas.get(i).getVerticeChegada().getValor() == valorSaida)
                        && (arestas.get(i).getVerticeSaida().getValor() == valorChegada)) {
                    return i;
                }
            }
        
            return -1;
        }

        // procura os valores de saida e entrada equivalentes.
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
        int indiceAresta = this.findAresta(valorSaida, valorChegada);
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
        
        // se for não for direcionado ele pega de todas.
        // se for ele só pega as aresta em que ele é o valor de chegada.

        // pegar as arestas que possui o vertice como saida
        if(!this.isDirecionado()){
            for(int i = 0 ; i < vertices.size() ; i++){
                indiceAresta = this.findAresta(valor, vertices.get(i).getValor());
                if(indiceAresta != -1){
                    arestasAdjacentes.add(this.arestas.get(indiceAresta));
                }
            }
        }
            
        // pegar as arestas que possui o vertice como chegada
        for(int i = 0 ; i < vertices.size() ; i++){
            indiceAresta = this.findAresta(vertices.get(i).getValor() , valor );
            if(indiceAresta != -1){
                arestasAdjacentes.add(this.arestas.get(indiceAresta));
            }
        }

        // pegar os vertices que sejam diferentes do passado como valor
        for(int i = 0 ; i < arestasAdjacentes.size() ; i++){
            // pega os vertices de chegada(adjacentes) das arestas
            if(!this.isDirecionado()){
               if(arestasAdjacentes.get(i).getVerticeChegada().getValor() != valor){
                    if(!verticesAdjacentes.contains(arestasAdjacentes.get(i).getVerticeChegada())){
                        verticesAdjacentes.add(arestasAdjacentes.get(i).getVerticeChegada());
                    }
                }
            }

            // pega os vertices de saida(adjacentes) das arestas
            if(arestasAdjacentes.get(i).getVerticeSaida().getValor() != valor){
                if(!verticesAdjacentes.contains(arestasAdjacentes.get(i).getVerticeSaida())){
                    verticesAdjacentes.add(arestasAdjacentes.get(i).getVerticeSaida());
                }
            }
        }

        return verticesAdjacentes;
    }

    public ArrayList<Vertice> getChegadas(Vertice vertice){
        // pega os vertices de chegada ligada ao vertice pressupondo que o vertice seja o vertice de saida

        ArrayList<Aresta> arestasAdjacentes = new ArrayList<Aresta>();
        ArrayList<Vertice> verticesAdjacentes = new ArrayList<Vertice>();

        int indiceAresta;

        // pegar as arestas que possui o vertice como saida
        for(int i = 0 ; i < vertices.size() ; i++){
            indiceAresta = this.findAresta(vertice.getValor() , vertices.get(i).getValor());
            if(indiceAresta != -1){
                arestasAdjacentes.add(this.arestas.get(indiceAresta));
            }
        }

         // pegar os vertices que sejam diferentes do passado como valor
         for(int i = 0 ; i < arestasAdjacentes.size() ; i++){
            // pega os vertices de chegada(adjacentes) das arestas
               if(arestasAdjacentes.get(i).getVerticeChegada().getValor() != vertice.getValor()){
                    verticesAdjacentes.add(arestasAdjacentes.get(i).getVerticeChegada());
                }
        }

        return verticesAdjacentes;
    }

    public void matrizAdjacencias(){
        int indiceAresta;
        
        System.out.println("--------------Matriz de Adjacencias-----------");
        System.out.println("\n"); 

        System.out.print("    ");
        
        for(int i = 0 ; i < this.vertices.size() ; i++){
            System.out.print(" " + vertices.get(i).getValor()+"|");
        }
        System.out.println("\n");
        

        for(int i = 0 ; i < this.vertices.size() ; i++){
            System.out.print(" " + vertices.get(i).getValor() + "| ");
            for(int j = 0 ; j < this.vertices.size() ; j++){
                indiceAresta = this.findAresta(vertices.get(i).getValor(), vertices.get(j).getValor());
                
                if(indiceAresta == -1){
                    indiceAresta = this.findAresta(vertices.get(j).getValor(), vertices.get(i).getValor());
                }
                
                if(indiceAresta != -1){
                    System.out.print(" " + this.arestas.get(indiceAresta).getPeso()+"|");
                } else {
                    System.out.print(" " + 0 +"|"); 
                }
                
            }
 
            System.out.println("\n"); 
        }
        System.out.println("\n"); 
        System.out.println("--------------Matriz de Adjacencias-----------");
        System.out.println("\n"); 
    }
    
    public ArrayList<Vertice> search(Vertice vertice  , ArrayList<Vertice> verticesConexos){
        verticesConexos.add(vertice);
        ArrayList<Vertice> verticesAdjacentes = new ArrayList<Vertice>();
        verticesAdjacentes = getAdjacentes(vertice.getValor());
        for(int i = 0 ; i < verticesAdjacentes.size() ; i++){
            if(!verticesConexos.contains(verticesAdjacentes.get(i))){
                verticesConexos = this.search(verticesAdjacentes.get(i), verticesConexos);
            }
        }
        return verticesConexos;
    }

    public ArrayList<Vertice> searchDirecionado(Vertice vertice  , ArrayList<Vertice> verticesConexos){
        // compara verificar os vertices de chegada
        verticesConexos.add(vertice);
        ArrayList<Vertice> verticesAdjacentes = new ArrayList<Vertice>();
        verticesAdjacentes = getChegadas(vertice);
        for(int i = 0 ; i < verticesAdjacentes.size() ; i++){
            if(!verticesConexos.contains(verticesAdjacentes.get(i))){
                verticesConexos = this.searchDirecionado(verticesAdjacentes.get(i), verticesConexos);
            }
        }
        return verticesConexos;
    }

    public boolean isConexo(){
        ArrayList<Vertice> verticesConexos = new ArrayList<Vertice>();
        ArrayList<Vertice> verticesConexosDirecionados = new ArrayList<Vertice>();

        if(this.vertices.size() == 0){
            System.out.println("O grafo não possui vertices");
            return false;
        }

        Vertice verticeInit = this.vertices.get(0);
        
        ArrayList<Vertice> conexos = this.search(verticeInit , verticesConexos);
        
        if(this.direcionado){
            ArrayList<Vertice> conexosChegada = this.searchDirecionado(verticeInit , verticesConexosDirecionados);
            System.out.println("Size conexos = " + conexos.size() + " Size conexos chegada = "+ conexosChegada.size() + " Size vertices = " + this.vertices.size());
            if((conexos.size() == this.vertices.size())  &&  (conexosChegada.size() == this.vertices.size())){
                return true;
            } else {
                return false;
            }
        }

        // System.out.println("Size " + conexos.size());
        // for(int i = 0 ; i < conexos.size() ; i++){
        //     System.out.println(conexos.get(i).getValor());
        // }
        
        if(conexos.size() == this.vertices.size()){
            return true;
        } else {
            return false;
        }
      }

      public boolean euler(){
        int impares = 0;
        if(this.isConexo()){
            for(int i = 0 ; i < this.vertices.size() ; i++){
                int grauVertice = this.getGrau(this.vertices.get(i).getValor()); 
                if(grauVertice % 2 != 0){
                    impares++;
                }
            }

            if(impares == 0){
                return true;
            }

            if(impares == 2){
                return true;
            }
        }

        return false;
      }
}
