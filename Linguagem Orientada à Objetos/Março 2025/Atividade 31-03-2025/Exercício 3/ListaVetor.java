package br.edu.unespar.lista.vetor;

public class ListaVetor {

    private int[] elementos;

    //Deve sempre apontar para a primeira posição vazia do vetor
    private int indiceAtual;

    public ListaVetor() {
        elementos = new int[10];
        indiceAtual = 0;
    }

    public void inserir(int elemento) {
        if (indiceAtual >= elementos.length) {
            int[] novosElementos = new int[elementos.length * 2];
            for (int i = 0; i < elementos.length; i++) {
                novosElementos[i] = elementos[i];
            }
            elementos = novosElementos;
        }
        elementos[indiceAtual] = elemento;
        indiceAtual++;
    }

    /*
    Implemente este método. O método deve receber um inteiro elemento e retornar
    o índice da primeira ocorrência do elemento no vetor, ou -1 caso o elemento
    não esteja presente no vetor.
     */
    public int buscar(int elemento) {
        for(int i = 0; i < this.elementos.length; i++){
                if(this.elementos[i] == elemento){
                    return i;
            }
        }
        return -1;
    }

    /*
    Implemente este método. O método deve receber um inteiro elemento como parâmtetro.
    Caso o elemento pertença ao vetor, remover a primeira ocorrência deste e retornar 
    true. Caso contrário, retornar false.
    
    Observação: após remover o elemento do vetor, você deve mover todos os elementos
    à sua direita uma posição para a esquerda, para que o vetor não fique com um
    "buraco". O valor do atributo indiceAtual também deve ser atualizado.
     */
    public boolean remover(int elemento) {
        for(int i = 0; i < this.elementos.length; i++){
            if(this.elementos[i] == elemento){
                for(int j = i; j < this.indiceAtual; j++){
                    this.elementos[j] = this.elementos[j+1];
                 }
                this.indiceAtual--;
                return true;
            }
        }
        return false;
    }

    /*
    Implemente este método. O método deve retornar uma String que representa a 
    lista de inteiros. Uma sugestão é percorrer a lista concatenando cada elemento
    da lista em uma string inicialmente vazia, com algum tipo de separador, como
    vírgula ou espaço.
     */
    public String toString() {
        String lista = "Vetor: ";
        for(int i = 0; i < this.elementos.length; i++){
            if(i != elementos.length-1){
                lista = lista + elementos[i] + ',';
            }else{
                lista = lista + elementos[i] + '.';
            }
        }
        return lista;
    }

}
