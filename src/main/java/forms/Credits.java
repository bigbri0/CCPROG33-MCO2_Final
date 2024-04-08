package forms;

import java.awt.*;
import javax.swing.*;
import util.custom.Button;
import util.custom.UIHelper;
/**
 * This class represents the Credits screen of the EldenRouge game.
 * It displays the developers and special thanks.
 * @Authors Roj Friginal, Brian Santamaria

 */
public class Credits {
    /**
     * The View class is responsible for the visual representation of the Credits screen.
     */
    public static class View extends JPanel {
        private JLabel titleLbl;
        private JLabel subtitleLbl;

        private JPanel contentPnl;
        private JLabel htmlLbl;
        private JPanel headerPnl;

        private Button backBtn;
        /**
         * Constructor for the View class.
         * It initializes the components of the Credits screen.
         */
        public View() {
            initComponents();
        }
        /**
         * This method initializes the components of the Credits screen.
         */
        private void initComponents() {
            setBackground(UIHelper.colorBackground);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            headerPnl = new JPanel(new GridBagLayout());
            headerPnl.setOpaque(false);
            add(headerPnl, gbc);

            {
                GridBagConstraints gbcHdr = new GridBagConstraints();
                gbcHdr.gridx = 0;
                gbcHdr.gridy = 0;
                gbcHdr.weightx = 0;
                gbcHdr.gridheight = 2;
                gbcHdr.insets.set(10, 40, 10, 40);

                backBtn = new Button("Back") {
                    @Override
                    protected void paintComponent(Graphics grphcs) {
                        super.paintComponent(grphcs);

                        Graphics2D g = (Graphics2D) grphcs;
                        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
                        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                        int[] x = { 35, 15, 35 };
                        int[] y = { 15, 25, 35 };
                        g.setColor(UIHelper.colorOnPrimaryContainer);
                        g.fillPolygon(x, y, 3);

                    }
                };
                backBtn.setPreferredSize(new Dimension(115, 50));
                backBtn.setHorizontalAlignment(JLabel.RIGHT);
                backBtn.setFont(new Font("Mantinia", Font.BOLD, 16));
                headerPnl.add(backBtn, gbcHdr);

                gbcHdr.gridy = 0;
                gbcHdr.gridx = 1;
                gbcHdr.gridheight = 1;
                gbcHdr.weightx = 1;

                gbcHdr.insets.set(25, 0, 0, 0);
                titleLbl = new JLabel("Elden rougE");
                titleLbl.setFont(new Font("Mantinia", Font.BOLD, 75));
                titleLbl.setForeground(UIHelper.colorTertiary);
                headerPnl.add(titleLbl, gbcHdr);

                gbcHdr.insets.set(-40, 0, 15, 0);
                gbcHdr.gridy++;
                subtitleLbl = new JLabel("credits");
                subtitleLbl.setFont(new Font("Mantinia", Font.BOLD, 28));
                subtitleLbl.setForeground(UIHelper.colorPrimary);
                headerPnl.add(subtitleLbl, gbcHdr);

                gbcHdr.gridx++;
                gbcHdr.gridy = 0;
                gbcHdr.weightx = 0;
                gbcHdr.gridheight = 2;
                gbcHdr.insets.set(10, 40, 10, 40);
                JLabel emptyLbl = new JLabel("");
                emptyLbl.setPreferredSize(new Dimension(115, 50));
                headerPnl.add(emptyLbl, gbcHdr);


                backBtn.addActionListener(e -> getFrame().setView("GameLobby"));

            }

            gbc.insets.set(-30, 0, 0, 0);
            gbc.weighty = 1;
            gbc.gridy++;
            contentPnl = new JPanel();
            contentPnl.setOpaque(false);
            contentPnl.setLayout(new GridBagLayout());
            add(contentPnl, gbc);


            String dev1 = "Roj Aleck Friginal", dev2 = "Brian Santamaria";
            String lib1 = "Sir AJ Cubillas", lib2 = "Jan Stanek";

            GridBagConstraints gbc2 = new GridBagConstraints();
            gbc2.gridy = 0;
            gbc2.gridwidth = 1;
            gbc2.weightx = 1;
            gbc2.fill = GridBagConstraints.HORIZONTAL;
            gbc2.anchor = GridBagConstraints.CENTER;
            gbc2.insets.set(0, 0, 0, 0);
            htmlLbl = new JLabel(
                "<html><center><p style='border-bottom: 2px solid #69dbae; color: #69dbae;'><b>Developed by:</b></p><p>%s</p><p>%s</p><br><br><p style='border-bottom: 2px solid #69dbae; color: #69dbae;'><b>Special Thanks:</b></p><p>%s</p><p>%s</p></center></html>"
                .formatted(dev1, dev2, lib1, lib2));
            htmlLbl.setFont(new Font("Mantinia", Font.PLAIN, 25));
            htmlLbl.setForeground(UIHelper.colorTertiary);
            contentPnl.add(htmlLbl, gbc2);
        }
        /**
         * This method returns the MainMenu View that is the ancestor of this View.
         *
         * @return the MainMenu View that is the ancestor of this View
         */
        public MainMenu.View getFrame() {
            return (MainMenu.View) SwingUtilities.getWindowAncestor(this);
        }
    }
}
