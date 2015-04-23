/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**

 Studentnummer: S1079295 Naam: Rik Lugtenberg Klas: ICTM2D Vak : JAVA
 */
public class Tekenpanel extends JPanel
{

    public Tekenpanel()
    {
        this.setPreferredSize(new Dimension(625, 670));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        int linkerBox = 10;
        int rechterBox = 50;
        g.setFont(new Font("SansSerif", Font.BOLD, 26));
        
        super.paintComponent(g);
        setBackground(Color.GRAY);
        g.setColor(Color.blue);
        g.fillRect(238, 10, 150, 400);
        g.fillRect(125, 410, 375, 50);
        g.setColor(Color.black);
        g.drawRect(238, 10, 150, 400);
        g.drawRect(125, 410, 375, 50);
        g.setColor(Color.green);
        g.fillRect(10, 530, 120, 130);
        g.fillRect(490, 530, 120, 130);
        g.setColor(Color.black);
        g.drawRect(10, 530, 120, 130);
        g.drawRect(490, 530, 120, 130);
        g.drawString(linkerBox + " %",40, 600);
        g.drawString(rechterBox + " %",520, 600);
    }

}
