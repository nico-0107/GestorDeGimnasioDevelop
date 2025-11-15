/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ARIAN BEJAR
 */
public class Estilo {
    public static void botonModerno(JButton btn, Color colorFondo) {

        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(true);
        btn.setBackground(colorFondo);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(colorFondo.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(colorFondo);
            }
        });
    }
    
    public static void textFieldModerno(JTextField txt) {

    txt.setOpaque(false);
    txt.setBorder(BorderFactory.createEmptyBorder());
    txt.setForeground(Color.WHITE);
    txt.setCaretColor(Color.WHITE);

    txt.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            txt.setOpaque(true);
            txt.setBackground(Color.WHITE);
            txt.setForeground(Color.BLACK);
            txt.setCaretColor(Color.BLACK);
            txt.repaint();
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (txt.getText().trim().isEmpty()) {
                txt.setOpaque(false);
                txt.setForeground(Color.WHITE);
                txt.setCaretColor(Color.WHITE);
            } else {
                txt.setOpaque(true);
                txt.setBackground(Color.WHITE);
                txt.setForeground(Color.BLACK);
                txt.setCaretColor(Color.BLACK);
            }
            txt.repaint();
        }
    });
}

    
    public static void tablaHeaderModerno(JTable table) {

    JTableHeader header = table.getTableHeader();

    header.setPreferredSize(new Dimension(header.getWidth(), 32));

    header.setDefaultRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            lbl.setOpaque(true);
            lbl.setBackground(new Color(45, 45, 45));
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            lbl.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,
                    new Color(70, 70, 70)));

            return lbl;
        }
    });
}

    
}
