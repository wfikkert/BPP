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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * Studentnummer: S1079295 Naam: Rik Lugtenberg Klas: ICTM2D Vak : JAVA
 */
public class Tekenpanel extends JPanel
{

    private ArrayList<ArrayList<String>> actiePerArtikel;
    private int x = 15;
    private int teller = 1;
    private Graphics g;
    private boolean run;

    public Tekenpanel()
    {
        this.setPreferredSize(new Dimension(625, 670));
        actiePerArtikel = new ArrayList<>();

    }

    @Override
    public void paintComponent(Graphics g)
    {
        this.g = g;

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
        g.drawString(linkerBox + " %", 40, 600);
        g.drawString(rechterBox + " %", 520, 600);

        if (teller > 1)
        {
            g.clearRect(268, x - 1, 90, 90);
            System.out.println("Clear rect: 258, " + (x - 1) + ", 90, 90");
            g.setColor(Color.blue);
            g.fillRect(268, x - 1, 90, 90);
            System.out.println("Fill rect blue: 258, " + (x - 1) + ", 90, 90");
        }
        g.setColor(Color.red);
        g.fillRect(268, x, 90, 90);
        System.out.println("Fill rect red: 258, " + (x) + ", 90, 90");

    }

    public void test()
    {
        run = true;
        while (run)
        {
            for (int iHoofdArray = 0; iHoofdArray < actiePerArtikel.size(); iHoofdArray++)
            {
                for (int iSubArray = 0; iSubArray < actiePerArtikel.get(iHoofdArray).size(); iSubArray++)
                {
                    teller = 0;
                    if (actiePerArtikel.get(iHoofdArray).get(iSubArray).equals("naarLinks") || actiePerArtikel.get(iHoofdArray).get(iSubArray).equals("naarRechts"))
                    {
                        while (teller <= 100)
                        {
                            x++;

                            try
                            {
                                Thread.sleep(1000);

                            }
                            catch (InterruptedException ex)
                            {
                                //do stuff    
                            }
                            revalidate();
                            repaint();
                            System.out.println("REPAINT");
                            teller++;
                        }
                    }

                }
            }
            run = false;

        }

    }

    public void voegArrayToe(ArrayList<ArrayList<String>> a)
    {
        actiePerArtikel = a;
    }

}
