package App.View.MenuPanels;

import App.Utils.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class AboutPanel extends JPanel {
    private final JButton backButton = new JButton("Back");
    public AboutPanel() {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        TextPanel textPanel = new TextPanel();
        textPanel.setAlignmentX(CENTER_ALIGNMENT);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        backButton.setPreferredSize(new Dimension(150, 30));
        backButton.setMinimumSize(new Dimension(120, 15));
        backButton.setMaximumSize(new Dimension(200, 50));
        backButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        //buttonPanel.add(Box.createRigidArea(new Dimension(400,0)));
        buttonPanel.add(backButton);
        add(Box.createVerticalGlue());
        add(textPanel);
        add(Box.createVerticalGlue());
        add(buttonPanel);

    }
    public void addButtonListener(ActionListener al) {
        backButton.addActionListener(al);
    }

    static class TextPanel extends JPanel {
        public TextPanel() {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            setBackground(Color.green.darker().darker());
            setMaximumSize(new Dimension(600, 450));
            setPreferredSize(new Dimension(500, 440));
            setMinimumSize(new Dimension(400, 400));
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.black);
            Font font = new Font("SansSerif", Font.BOLD, 13);
            g2.setFont(font);
            FontRenderContext frc = g2.getFontRenderContext();
            String[] textLines = text.split("\n");
            Rectangle2D bounds = font.getStringBounds(textLines[0], frc);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            int x = ViewUtils.MENU_BORDER / 4 * 3;
            for (int i = 0; i < textLines.length; ++i) {
                int y = (int)(i * (bounds.getHeight() + 5) + ViewUtils.MENU_BORDER / 4 * 3);
                g2.drawString(textLines[i], x, y);
            }
        }
    }
    private static final String text = "Я в своем познании настолько преисполнился, что я как будто бы уже\n" +
            "сто триллионов миллиардов лет проживаю на триллионах и\n" +
            "триллионах таких же планет, как эта Земля, мне этот мир абсолютно\n" +
            "понятен, и я здесь ищу только одного - покоя, умиротворения и\n" +
            "вот этой гармонии, от слияния с бесконечно вечным, от созерцания\n" +
            "великого фрактального подобия и от вот этого замечательного всеединства\n" +
            "существа, бесконечно вечного, куда ни посмотри, хоть вглубь - бесконечно\n" +
            "малое, хоть ввысь - бесконечное большое, понимаешь? А ты мне опять со\n" +
            "своим вот этим, иди суетись дальше, это твоё распределение, это\n" +
            "твой путь и твой горизонт познания и ощущения твоей природы, он\n" +
            "несоизмеримо мелок по сравнению с моим, понимаешь? Я как будто бы уже\n" +
            "давно глубокий старец, бессмертный, ну или там уже почти бессмертный,\n" +
            "который на этой планете от её самого зарождения, ещё когда только Солнце\n" +
            "только-только сформировалось как звезда, и вот это газопылевое облако,\n" +
            "вот, после взрыва, Солнца, когда оно вспыхнуло, как звезда, начало\n" +
            "формировать вот эти коацерваты, планеты, понимаешь, я на этой Земле уже\n" +
            "как будто почти пять миллиардов лет живу и знаю её вдоль и поперёк...";
}
