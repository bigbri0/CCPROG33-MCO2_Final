package com.eldenrouge.util.custom;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ProgressBar extends JProgressBar {


    public ProgressBar() {
        super();
        setOpaque(false);
        setStringPainted(true);
        setBorder(new EmptyBorder(2, 2, 2, 2));
        setBackground(null);
        setFont(new Font("Arial", Font.BOLD, 14));
    }

    public ProgressBar(int min, int max) {
        super(min, max);
        setOpaque(false);
        setStringPainted(true);
        setBorder(new EmptyBorder(2,2,2,2));
        setBackground(null);
        setFont(new Font("Arial", Font.BOLD, 14));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();

        int percentComplete = getValue();
        int barLength = (int) (percentComplete * w / getMaximum());

        g2d.setColor(UIHelper.colorOnPrimaryContainer);
        g2d.fillRoundRect(0, 0, w, h, 50, 50);

        g2d.setColor(UIHelper.colorOnSecondary);
        g2d.fillRoundRect(2, 2, w - 4, h - 4, 49, 49);

        Shape mask = new RoundRectangle2D.Float(2, 2, w-4, h-4, 50, 50);
        g2d.setClip(mask);
        
        g2d.setColor(UIHelper.colorOnSecondaryContainer);
        g2d.fillRoundRect(0, 0, barLength - 2, h , 0, 0);
        

        if (isStringPainted()) {
            String text = getString();
            if (text != null) {
                int stringX = 15;
                int stringY = (h - g2d.getFontMetrics().getHeight()) / 2 + g2d.getFontMetrics().getAscent();
                g2d.setFont(new Font("Mantinia", 1, 17));
                g2d.setColor(UIHelper.colorOnPrimaryContainer);
                g2d.drawString(text, stringX, stringY + 2);

                Shape mak = new RoundRectangle2D.Float(1, 1, barLength - 2, h - 3, 15, 15);
                g2d.setClip(mak);
                g2d.setColor(UIHelper.colorOnSecondary);
                g2d.drawString(text, stringX, stringY + 2);

            }
        }

    }

    public void setTextColor(Color color) {
        setForeground(color);
        repaint();
    }
}