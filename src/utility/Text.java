package utility;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Text {
    public static final Font REGULAR_FONT = new Font("Arial", Font.PLAIN, 16);
    public static void formatJLabel(JLabel label) {
        label.setFont(Text.REGULAR_FONT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        // label.setVerticalAlignment(SwingConstants.CENTER);
    }
    public static void formatJButton(JButton button) {
        button.setFont(Text.REGULAR_FONT);
    }
}
