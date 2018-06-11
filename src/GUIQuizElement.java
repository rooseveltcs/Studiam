import javax.swing.*;
import java.awt.*;

public class GUIQuizElement extends JPanel {

    public GUIQuizElement(QuizElement element, int percent) {
        addKeyListener(Main.mainKeyListener());
        setOpaque(false);

        setBackground(new Color(200, 200, 200, 200));
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 0, 5, 0),
                BorderFactory.createLineBorder(Main.PURPLE)));
        int total = 0;
        for (QuizTerm term : element) {
            if ((Math.random() * 100) >= percent) {
                JTextField field = StudiamFactory.newStudiamTextField("", 15, BorderFactory.createLineBorder(Color.BLACK));
                field.setPreferredSize(new Dimension(term.toString().length() * 15, 20));
                add(field);
            } else {
                JLabel label = StudiamFactory.newStudiamLabel(term.toString(), 15, BorderFactory.createLineBorder(Color.BLACK));
                add(label);
            }
            total += term.toString().length() * 15;
        }
        setPreferredSize(new Dimension(total, 40));
        setMaximumSize(new Dimension(total, 40));
        setMinimumSize(new Dimension(total, 40));

    }


    @Override //method to stop dumb visual glitch
    protected void paintComponent(Graphics g) {
        g.setColor( getBackground() );
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

}
