/*Crie uma classe para servir como controladora do jogo. Essa classe deve possuir os 
seguintes atributos:Uma String representando a palavra secreta, um inteiro numTentativas representando quantas tentativas ainda restam ao jogador,
um vetor de boolean com tantas posições quanto o tamanho da palavra secreta, indicando quais posições já foram reveladas e ainda não foram reveladas,
uma lista de strings contento as tentativas, que podem ser de apenas uma letra ou de uma palavra inteira, outros atributos que julgar necessário, como indicadores
de vitória/ fim de jogo, por exemplo.

Além desses atributos, a classe deve possuir os seguintes métodos: Construtor, getters e setter que achar necessário, um método para realizar uma tentativa 
de letra, que deve receber uma letra e atualizar os atributos relevantes no fim da tentativa, um método para realizar uma tentativa de palavra, que deve receber
uma String e atualizar os atributos relevante no fim da tentativa. Outros métodos que julgar necessários.
*/

package com.mycompany.jogodaforca;

import java.util.ArrayList;
import java.util.List;

public class ControladoraDoJogo {

    private String palavraSecreta;
    private int numTentativas;
    private boolean[] letrasReveladas;
    private List <String> tentativas;
    private boolean vitoria;
    private boolean jogoEncerrado;

    public ControladoraDoJogo(String palavraSecreta, int numTentativas){
        this.palavraSecreta = palavraSecreta.toLowerCase(); //Salva a palavra secreta na variável da classe
        this.numTentativas = numTentativas; //Guarda o número de tentativas permitidas no atributo this.numTentativas
        this.vitoria = false; //Marca que o jogador ainda não venceu o jogo
        this.jogoEncerrado = false; //O jogo ainda esta em andamento 
        this.letrasReveladas = new boolean[palavraSecreta.length()]; //cria um vetor do tipo boolean 
        this.tentativas = new ArrayList<>(); //Cria uma lista vazia onde vão ser guardadas todas as tentativasdo jogador
    }
    
    public String getPalavraSecreta() {
        return palavraSecreta;
    }

    public int getNumTentativas() {
        return numTentativas;
    }

    public boolean[] getLetrasReveladas() {
        return letrasReveladas;
    }

    public List<String> getTentativas() {
        return tentativas;
    }

    public boolean isVitoria() {
        return vitoria;
    }

    public boolean isJogoEncerrado() {
        return jogoEncerrado;
    } 
    
    public String getPalavraParcial(){
        StringBuilder sb = new StringBuilder(); //Cria um StringBuilder para ir montando a string final
        for(int i = 0; i < palavraSecreta.length(); i++){
            if(letrasReveladas[i]){ //Verifica se a letra da posição i já foi revelada
                sb.append(palavraSecreta.charAt(i)); //Se foi revelada (true), adiciona a letra o StringBuilder
            }else{
                sb.append("_"); //Se não foi revelada, adiciona um "_"
            }
            sb.append("");
        }
        
        return sb.toString().trim(); //Fecha o for e sb.toString() transforma o conteúdo do StringBuilder em uma String e .trim() tira o espaço extra que sobrou no final    
    }
    
    public void tentarLetra(char letra){
        letra = Character.toLowerCase(letra);
        tentativas.add(String.valueOf(letra));
        boolean acertou = false;
        
        for(int i = 0; i < palavraSecreta.length(); i++){
            if(palavraSecreta.charAt(i) == letra && !letrasReveladas[i]){
            letrasReveladas[i] = true;
            acertou = true;
                
            }
        }
        
        if(!acertou){
            numTentativas--;
        
    }
        
        verificarFimDeJogo();
    
    }
    
    public void tentarPalavra(String tentativa){
        tentativa = tentativa.toLowerCase();
        tentativas.add(tentativa);
        if(tentativa.equals(palavraSecreta)){
            for(int i = 0; i < letrasReveladas.length; i++){
                letrasReveladas[i] = true;
            }
            vitoria = true;
        }else{
            numTentativas--;
        }
        
        verificarFimDeJogo();
    }

    private void verificarFimDeJogo(){
        boolean todasReveladas = true;
        for(boolean revelada : letrasReveladas){
            if(!revelada){
                todasReveladas = false;
                break;
            }
        }
        
        if(todasReveladas){
            vitoria = true;
            jogoEncerrado = true;
        }else if(numTentativas <= 0){
            jogoEncerrado = true;
        }
    }    
}