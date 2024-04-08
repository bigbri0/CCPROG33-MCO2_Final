package com.eldenrouge.util.custom;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Button extends JButton {
    private int state = 0;
    private int corner = 50;

    public Button(String text) {
        setText(text);
        load();
    }

    public Button() {
        load();
    }

    private void load() {
        setOpaque(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setForeground(UIHelper.colorOnPrimaryContainer);
        
        setBorder(new EmptyBorder(7, 20, 10, 20));
        setFont(new Font("Mantinia", 1, 25));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setState(1);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setState(0);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                setState(2);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                setState(1);
            }
        });
    }

    private void setState(int state) {
        this.state = state;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g = (Graphics2D) grphcs.create();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        if (!isEnabled()) {
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRoundRect(0, 0, getWidth(), getHeight(), corner, corner);
        } else {
                
            if (state == 1) {
                g.setColor(UIHelper.colorOutline);
                g.fillRoundRect(0, 0, getWidth(), getHeight(), corner, corner);

                g.setColor(UIHelper.colorSecondaryContainer);
                g.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, corner-1, corner-1);
            
            } else if (state == 2) {
                g.setColor(UIHelper.colorSecondaryContainer);
                g.fillRoundRect(0, 0, getWidth(), getHeight(), corner, corner);
            
                g.setColor(UIHelper.colorOnSecondary);
                g.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, corner - 1, corner - 1);

            } else {
             
                g.setColor(UIHelper.colorSecondaryContainer);
                g.fillRoundRect(0, 0, getWidth() , getHeight(), corner, corner);
            }

        }

        g.dispose();
        super.paintComponent(grphcs);
    }
}
