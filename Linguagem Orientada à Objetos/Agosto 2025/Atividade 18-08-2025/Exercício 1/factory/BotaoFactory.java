package factory;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class BotaoFactory {

    public static JButton criarBotao(TipoBotao tipo) {
        JButton botao = new JButton("OK");
        switch (tipo) {
            case BOTAO_VERDE:
                botao.setBackground(Color.GREEN);
                break;
            case BOTAO_AZUL:
                botao.setBackground(Color.CYAN);
                break;
            case BOTAO_COM_BORDA:
                botao.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 5));
                break;
        }
        return botao;
    }

}
