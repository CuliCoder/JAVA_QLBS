/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

/**
 *
 * @author ASUS
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author ASUS
 */
public class ButtonRadius extends JButton {

    private boolean shouldDrawBorder = true;

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }
//
//    public Color getColorOver() {
//        return colorOver;
//    }
//
//    public void setColorOver(Color colorOver) {
//        this.colorOver = colorOver;
//    }
//
//    public Color getColorClick() {
//        return colorClick;
//    }
//
//    public void setColorClick(Color colorClick) {
//        this.colorClick = colorClick;
//    }

//    public Color getBorderColor() {
//        return borderColor;
//    }
//    public void setBorderColor(Color borderColor) {
//        this.borderColor = borderColor;
//    }
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public ButtonRadius() {
        //  Init Color

        setColor(new Color(250,232,189));
        colorOver = new Color(250,241,192);
        colorClick = new Color(246,227,183);
//        borderColor = new Color(30, 136, 56);
        setHorizontalAlignment(JButton.CENTER);
        setVerticalAlignment(JButton.CENTER);
        setContentAreaFilled(false);
        
        //  Add event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(colorOver);
                over = true;
            }
//

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(color);
                over = false;

            }

            @Override
            public void mousePressed(MouseEvent me) {
                setBackground(colorClick);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    setBackground(colorOver);
                } else {
                    setBackground(color);
                }
            }
        });

    }
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick = new Color(255, 255, 204);
    private final Color borderColor = new Color(251, 233, 189);
    private int radius = 0;


    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //  Paint Border
        if (shouldDrawBorder) {
            g2.setColor(borderColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        }
        g2.setColor(getBackground());
//          Border set 2 Pix
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(grphcs);
    }

    public void setShouldDrawBorder(boolean shouldDrawBorder) {
        this.shouldDrawBorder = shouldDrawBorder;
        repaint(); // Cập nhật lại nút sau khi thay đổi giá trị shouldDrawBorder
    }
}
