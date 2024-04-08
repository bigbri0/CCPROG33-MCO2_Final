package util.custom;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;



public class TextField extends JTextField  {
	private float location;

    private int state = 0;
    private int corner = 50;


    public TextField() {
        setOpaque(false);
        setSelectionColor(UIHelper.colorOutline);
        setForeground(UIHelper.colorOnPrimaryContainer);
        setBorder(new EmptyBorder(10, 12, 12, 12));
        setFont(new Font("Mantinia", 1, 25));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                if (state != 2) {
                    setState(1);
                }
            }

            @Override
            public void mouseExited(MouseEvent me) {
                if (state != 2) {
                    setState(0);
                }
            }
        });

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                setState(2);
            }

            @Override
            public void focusLost(FocusEvent fe) {
                setState(0);
            }
        });
    }

    private void setState(int state) {
        this.state = state;
        repaint();
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g = (Graphics2D) grphcs;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);        

        if (state == 2) {
            g.setColor(UIHelper.colorSecondary);
            g.fillRoundRect(0, 0, getWidth(), getHeight(), corner, corner);

            g.setColor(UIHelper.colorSecondaryContainer);
            g.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, corner - 1, corner - 1);

        } else if (state == 1) {
            g.setColor(UIHelper.colorOutline);
            g.fillRoundRect(0, 0, getWidth(), getHeight(), corner, corner);

            g.setColor(UIHelper.colorOnSecondary);
            g.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, corner - 1, corner - 1);
        } else {
            g.setColor(UIHelper.colorSecondaryContainer);
            g.fillRoundRect(0, 0, getWidth(), getHeight(), corner, corner);
            
        }

        super.paint(grphcs);
        
        g.dispose();
    }

    @Override
    public void setText(String string) {
        if (!getText().equals(string)) {
        	if (string.equals(""))
        		location = 1f - location;
        	else
        		location = 1;
        }
        super.setText(string);
    }
}
