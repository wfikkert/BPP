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

//    private Timer timer;
//    private Timer timer2;
//    private Timer eindTimer;
    private int tijd = 0;

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
        g.fillRect(10, 410, 605, 100);
        g.setColor(Color.black);
        g.drawRect(238, 10, 150, 400);
        g.drawRect(10, 410, 605, 100);
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

    public void artikelAnimatie(String s, Timer t, Timer t2, Timer et)
    {

        if (s.equals("naarLinks"))
        {

            t.scheduleAtFixedRate(new naarBeneden(t), tijd, 10);
            tijd = tijd + 5000;

            t2.scheduleAtFixedRate(new naarLinks(t2), tijd, 10);
            tijd = tijd + 5000;

        }
        else if (s.equals("naarRechts"))
        {

            t.scheduleAtFixedRate(new naarBeneden(t), tijd, 10);
            tijd = tijd + 5000;
            t2.scheduleAtFixedRate(new naarRechts(t2), tijd, 10);
            tijd = tijd + 5000;

        }
        et.scheduleAtFixedRate(new opnieuwBeginnen(et), tijd, 1);
        tijd = tijd + 2500;

    }

    class naarBeneden extends TimerTask
    {
        private int maxY = 400;
        
        Timer timer;
        
        public naarBeneden(Timer t)
        {
            timer = t;
        }

        public void run()
        {

            rectY = rectY + 1;
            repaint();
            if (rectY >= maxY)
            {
                timer.cancel(); //Terminate the thread
            }
        }
    }

    class naarLinks extends TimerTask
    {
        private int maxX = 30;
        
        Timer timer;
        
        public naarLinks(Timer t)
        {
            this.timer = t;
        }

        public void run()
        {

            rectX = rectX - 1;
            repaint();
            if (rectX <= maxX)
            {
                timer.cancel(); //Terminate the thread
            }
        }
    }

    class naarRechts extends TimerTask
    {
        private int maxX = 500;
        
        Timer timer;
        
        public naarRechts(Timer t)
        {
            this.timer = t;
        }

        public void run()
        {

            rectX = rectX + 1;
            repaint();
            if (rectX >= maxX)
            {
                timer.cancel(); //Terminate the thread
            }
        }
    }

    class opnieuwBeginnen extends TimerTask
    {
        Timer timer;
        
        public opnieuwBeginnen(Timer t)
        {
            this.timer = t;
        }
        
        public void run()
        {
            rectX = 268;
            rectY = 15;
            repaint();
            timer.cancel();
        }
    }
}
