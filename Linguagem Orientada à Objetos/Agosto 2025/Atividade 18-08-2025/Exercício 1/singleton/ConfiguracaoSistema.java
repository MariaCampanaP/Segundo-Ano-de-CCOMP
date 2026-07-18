package singleton;

import java.awt.Font;
import java.util.List;
import java.util.ArrayList;

public class ConfiguracaoSistema{
    
    private static ConfiguracaoSistema instancia;
    
    private Font fonte;
    private List<FontObserver> observers;
    
    private ConfiguracaoSistema(){
        fonte = new Font("Arial", Font.PLAIN, 24);
        observers = new ArrayList<>();
    }
    
    public static ConfiguracaoSistema getInstancia(){
        if(instancia == null){
            instancia = new ConfiguracaoSistema();
        }
        return instancia;
    }
    
    public void adicionarObserver(FontObserver observer){
        observers.add(observer);
    }
    
    private void atualizarTodos(){
        for(FontObserver obs : observers){
            obs.atualizar(fonte);
        }
    }
    
    public void textoItalico(){
        fonte = fonte.deriveFont(Font.ITALIC);
        atualizarTodos();
    }
    
    public void textoNegrito(){
        fonte = fonte.deriveFont(Font.BOLD);
        atualizarTodos();
    }
    
    public Font getFonte(){
        return fonte;
    }
}
