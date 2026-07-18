package observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class TextObserver implements ActionListener {

    private JTextField textField;

    public TextObserver(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textField.setText(e.getActionCommand());

    }

}
