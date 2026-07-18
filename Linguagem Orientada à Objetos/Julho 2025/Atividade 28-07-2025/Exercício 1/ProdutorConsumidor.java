package id.produtor.consumidor;

public class ProdutorConsumidor {

    public static void main(String[] args) throws InterruptedException {
        double tempoInicial = System.currentTimeMillis();
        
        Fila filaNS = new FilaNaosincronizada();
        Fila filaS = new FilaSincronizada();

        Thread produtora = new Thread(new Produtor(filaS));

        Consumidor consumidor = new Consumidor(filaS);
        Thread consumidora = new Thread(consumidor);
        produtora.start();
        consumidora.start();
        produtora.join();
        consumidora.join();
        System.out.println("Soma final: " + consumidor.getSoma());

        double tempoFinal = System.currentTimeMillis();
        System.out.println("Tempo de execucao: " + (tempoFinal - tempoInicial));
    }
}

/*1 - Observações sobre o tempo de execução:
10.000 elementos: média cerca de 50ms
1.000 elementos: média cerca de 32ms
100 elementos: média cerca de 25ms
1 elemento: média cerca de 20ms
Conclusões finais: o tempo de execução aumenta exponencialmente conforme o aumento do número de elementos
*/

/*2 - Observações ao alterar o número de elementos das classes
A) Quando a classe produtora produz apenas 500 elementos, e a consumidora consome 10.000, após os 500 elementos
forem consumidos, a thread aguarda até que outro elemento seja adicionado para consumi-lo, logo, a thread espera indefinidamente

B) Quando a classe produtora produz 500 elementos, e a consumidora consome 250, a thread tambem espera indefinidamente
*/