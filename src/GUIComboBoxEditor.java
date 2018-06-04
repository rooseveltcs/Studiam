import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUIComboBoxEditor implements ComboBoxEditor {

    JTextArea textArea;

    public GUIComboBoxEditor() {
        textArea = new JTextArea();
        textArea.setSelectionColor(Main.LESS_PURPLE);
        textArea.setBorder(BorderFactory.createLoweredBevelBorder());
    }

    @Override
    public Component getEditorComponent() {
        return textArea;
    }

    @Override
    public void setItem(Object anObject) {
        if (anObject instanceof String) {
            textArea.setText((String) anObject);
        }

    }

    @Override
    public Object getItem() {
        return textArea.getText();
    }

    @Override
    public void selectAll() {
        textArea.selectAll();
    }

    @Override
    public void addActionListener(ActionListener l) {
    }

    @Override
    public void removeActionListener(ActionListener l) {
    }
}