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

    private boolean linksVol;
    private Pakket pakketLinks;
    private Pakket pakketRechts;
    private boolean rechtsVol;

    private int artikelHoogte;

    private int tijd = 0;

    private Graphics graphicsG;

    public Tekenpanel()
    {
        this.setPreferredSize(new Dimension(625, 670));
        pakketLinks = new Pakket();
        pakketRechts = new Pakket();

    }

    @Override
    public void paint(Graphics g)
    {
        super.paintComponent(g);
        graphicsG = g;

        g.setFont(new Font("SansSerif", Font.BOLD, 26));

        setBackground(Color.GRAY);
        g.setColor(Color.blue);
        g.fillRect(238, 10, 150, 400);
        g.fillRect(10, 410, 605, 100);
        g.setColor(Color.black);
        g.drawRect(238, 10, 150, 400);
        g.drawRect(10, 410, 605, 100);

        if (linksVol)
        {
            g.setColor(Color.RED);
            g.fillRect(10, 530, 120, 130);
            g.setColor(Color.black);
            g.drawString("LEGEN!", 25, 600);
        }
        else
        {
            g.setColor(Color.green);
            g.fillRect(10, 530, 120, 130);
            g.setColor(Color.black);
            int getalLinks = pakketLinks.getOvergeblevenHoogte();
            g.drawString(getalLinks + "", 50, 610);
        }

        if (rechtsVol)
        {
            g.setColor(Color.RED);
            g.fillRect(490, 530, 120, 130);
            g.setColor(Color.black);
            g.drawString("LEGEN!", 500, 600);
        }
        else
        {
            g.setColor(Color.green);
            g.fillRect(490, 530, 120, 130);
            g.setColor(Color.black);
            int getalRechts = pakketRechts.getOvergeblevenHoogte();
            g.drawString(getalRechts + "", 535, 610);
        }

        g.setColor(Color.black);
        g.drawRect(10, 530, 120, 130);
        g.drawRect(490, 530, 120, 130);

        g.setColor(Color.red);
        g.fillRect(rectX, rectY, 90, 90);
        
        g.setColor(Color.black);
        if(artikelHoogte != 0)
        {
            g.drawString(artikelHoogte + "", rectX + 35, rectY + 50);
        }

    }

    public void artikelAnimatie(String s, Timer t, Timer t2, Timer t3, Timer et, Artikel a)
    {

        if (s.equals("naarLinks"))
        {

            t.scheduleAtFixedRate(new naarBeneden(t, a), tijd, 10);
            tijd = tijd + 4000;

            t2.scheduleAtFixedRate(new naarLinks(t2, a), tijd, 10);
            tijd = tijd + 3000;

            et.scheduleAtFixedRate(new opnieuwBeginnen(et), tijd, 1);
            tijd = tijd + 2500;

        }
        else if (s.equals("naarRechts"))
        {

            t.scheduleAtFixedRate(new naarBeneden(t, a), tijd, 10);
            tijd = tijd + 4000;
            t2.scheduleAtFixedRate(new naarRechts(t2, a), tijd, 10);
            tijd = tijd + 3000;

            et.scheduleAtFixedRate(new opnieuwBeginnen(et), tijd, 1);
            tijd = tijd + 1500;

        }
        else if (s.equals("nieuwLinks"))
        {
            t.scheduleAtFixedRate(new nieuwLinks(t3, a), tijd, 1000);
            tijd = tijd + 500;

        }
        else if (s.equals("nieuwRechts"))
        {
            t.scheduleAtFixedRate(new nieuwRechts(t3, a), tijd, 1000);
            tijd = tijd + 500;

        }

    }

    class naarBeneden extends TimerTask
    {
        private int maxY = 400;
        private Timer timer;
        private Artikel artikel;

        public naarBeneden(Timer t, Artikel a)
        {
            timer = t;
            this.artikel = a;
        }

        public void run()
        {

            rectY = rectY + 1;
            artikelHoogte = artikel.getHoogte();
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
        private Timer timer;
        private Artikel artikel;

        public naarLinks(Timer t, Artikel a)
        {
            this.timer = t;
            this.artikel = a;
        }

        public void run()
        {

            rectX = rectX - 1;
            repaint();
            if (rectX <= maxX)
            {
                pakketLinks.voegArtikelToe(artikel);
                timer.cancel(); //Terminate the thread
            }
        }
    }

    class naarRechts extends TimerTask
    {
        private int maxX = 500;
        private Timer timer;
        private Artikel artikel;

        public naarRechts(Timer t, Artikel a)
        {
            this.timer = t;
            this.artikel = a;
        }

        public void run()
        {

            rectX = rectX + 1;
            repaint();
            if (rectX >= maxX)
            {
                pakketRechts.voegArtikelToe(artikel);
                timer.cancel(); //Terminate the thread
            }
        }
    }

    class opnieuwBeginnen extends TimerTask
    {
        private Timer timer;

        public opnieuwBeginnen(Timer t)
        {
            this.timer = t;

        }

        public void run()
        {
            rectX = 268;
            rectY = 15;
            artikelHoogte = 0;
            repaint();
            timer.cancel();
        }
    }

    class nieuwLinks extends TimerTask
    {
        private Timer timer;
        private int teller = 0;
        private Artikel artikel;

        public nieuwLinks(Timer t, Artikel a)
        {
            this.timer = t;
            this.artikel = a;
        }

        public void run()
        {
            linksVol = true;
            artikelHoogte = artikel.getHoogte();
            teller++;
            repaint();

            if (teller > 1)
            {
                linksVol = false;
                pakketLinks = new Pakket();
                repaint();
                timer.cancel();
            }
        }
    }

    class nieuwRechts extends TimerTask
    {
        private Timer timer;
        private int teller = 0;
        private Artikel artikel;

        public nieuwRechts(Timer t, Artikel a)
        {
            this.timer = t;
            this.artikel = a;
        }

        public void run()
        {
            rechtsVol = true;
            artikelHoogte = artikel.getHoogte();
            teller++;
            repaint();

            if (teller > 1)
            {
                rechtsVol = false;
                pakketRechts = new Pakket();
                repaint();
                timer.cancel();
            }
        }
    }
}
