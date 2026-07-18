package singleton;

import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingUtilities;

public class ComponentObserver implements FontObserver {
    private Component component;
    
    public ComponentObserver(Component component){
        this.component = component;
    }
    
    @Override
    public void atualizar(Font font){
        SwingUtilities.invokeLater(() -> component.setFont(font));
    }
    
}
