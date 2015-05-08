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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * Studentnummer: S1079295 Naam: Rik Lugtenberg Klas: ICTM2D Vak : JAVA
 */
public class Tekenpanel extends JPanel
{

    private int x = 15;
    private int teller = 1;

    private int rectX = 268;
    private int rectY = 15;

    private Timer timer;
    private Timer timer2;
    private Timer eindTimer;
    private int tijd = 0;
    private boolean isKlaar = false;

    private Graphics graphicsG;

    public Tekenpanel()
    {
        this.setPreferredSize(new Dimension(625, 670));

    }

    @Override
    public void paint(Graphics g)
    {
        super.paintComponent(g);
        graphicsG = g;

        int linkerBox = 10;
        int rechterBox = 50;

        g.setFont(new Font("SansSerif", Font.BOLD, 26));

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

        g.setColor(Color.red);
        g.fillRect(rectX, rectY, 90, 90);

    }

    public void artikelAnimatie(String s)
    {

        timer = new Timer();
        timer2 = new Timer();
        eindTimer = new Timer();

        if (s.equals("naarLinks"))
        {

            timer.scheduleAtFixedRate(new naarBeneden(), tijd, 1000);
            tijd = tijd + 3500;

            timer2.scheduleAtFixedRate(new naarLinks(), tijd, 1000);

        }
        else if (s.equals("naarRechts"))
        {
            
            timer.scheduleAtFixedRate(new naarBeneden(), tijd, 100);
            tijd = tijd + 3500;
            timer.scheduleAtFixedRate(new naarRechts(), 10, 100);
        }

        tijd = tijd + 2500;
//        eindTimer.scheduleAtFixedRate(new opnieuwBeginnen(), tijd, 10);

    }

    class naarBeneden extends TimerTask
    {
        private int maxY = 375;

        public void run()
        {

            isKlaar = false;
            rectY = rectY + 45;
            repaint();
            System.out.println("Naar beneden");
            System.out.println(" y is " + rectY);
            if (rectY >= maxY)
            {
                timer.cancel(); //Terminate the thread
            }
        }
    }

    class naarLinks extends TimerTask
    {
        private int maxX = 30;

        public void run()
        {

            isKlaar = false;
            rectX = rectX - 45;
            repaint();
            System.out.println("Naar links");
            System.out.println(" x is " + rectX);
            if (rectX <= maxX)
            {
                timer2.cancel(); //Terminate the thread
            }
        }
    }

    class naarRechts extends TimerTask
    {
        private int maxX = 450;

        public void run()
        {

            isKlaar = false;
            rectX = rectX + 45;
            repaint();
            System.out.println("Naar rechts");
            System.out.println(" x is " + rectX);
            if (rectX >= maxX)
            {
                timer.cancel(); //Terminate the thread
            }
        }
    }

    class opnieuwBeginnen extends TimerTask
    {

        public void run()
        {
            rectX = 268;
            rectY = 15;
            repaint();
            eindTimer.cancel();
        }
    }
}
