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
import javax.swing.JFrame;


public class Tekenpanel extends JPanel
{
    private Scherm scherm;
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


    private ArrayList<TimerTask> timers;

    public Tekenpanel(Scherm scherm)
    {
        this.scherm = scherm;
        this.setPreferredSize(new Dimension(625, 670));
        pakketLinks = new Pakket();
        pakketRechts = new Pakket();
        timers = new ArrayList<>();

    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        setBackground(Color.gray);
        g.setColor(Color.blue);
        g.fillRect(238, 10, 150, 400);
        g.fillRect(10, 410, 605, 100);
        g.setColor(Color.black);
        g.drawRect(238, 10, 150, 400);
        g.drawRect(10, 410, 605, 100);

        // Als boolean 'linksVol' true is, wordt het pakket rood
        if (linksVol)
        {
            g.setColor(Color.red);
            g.fillRect(10, 530, 120, 120);
            g.setColor(Color.black);
            g.setFont(new Font("SansSerif", Font.BOLD, 26));
            g.drawString("LEGEN!", 25, 600);
        }
        
        // Zo niet; dan groen pakket tonen met overgebleven hoogte
        else
        {
            g.setColor(Color.green);
            g.fillRect(10, 530, 120, 120);
            g.setColor(Color.black);
            g.setFont(new Font("SansSerif", Font.BOLD, 10));
            g.drawString("Overgebleven hoogte:", 15, 550);
            g.setFont(new Font("SansSerif", Font.BOLD, 75));
            int getalLinks = pakketLinks.getOvergeblevenHoogte();
            g.drawString(getalLinks + "", 20, 625);
        }

        // Hetzelfde voor het rechterpakket
        if (rechtsVol)
        {
            g.setColor(Color.red);
            g.fillRect(490, 530, 120, 120);
            g.setColor(Color.black);
            g.setFont(new Font("SansSerif", Font.BOLD, 26));
            g.drawString("LEGEN!", 500, 600);
        }
        else
        {
            g.setColor(Color.green);
            g.fillRect(490, 530, 120, 120);
            g.setColor(Color.black);
            g.setFont(new Font("SansSerif", Font.BOLD, 10));
            g.drawString("Overgebleven hoogte:", 495, 550);

            g.setFont(new Font("SansSerif", Font.BOLD, 75));
            int getalRechts = pakketRechts.getOvergeblevenHoogte();
            g.drawString(getalRechts + "", 510, 625);
        }

        g.setColor(Color.black);
        g.drawRect(10, 530, 120, 120);
        g.drawRect(490, 530, 120, 120);

        // Het artikel wat in de doos moet, met daarin de artikelgrootte
        if (artikelHoogte != 0)
        {
            g.setColor(Color.red);
            g.fillRect(rectX, rectY, 90, 90);
            g.setColor(Color.black);
            g.setFont(new Font("SansSerif", Font.BOLD, 10));
            g.drawString("Artikelhoogte:" , rectX+2, rectY + 10);
            g.setFont(new Font("SansSerif", Font.BOLD, 75));
            g.drawString(artikelHoogte + "", rectX + 5, rectY + 80);
        }

    }

    // artikelAnimatie wordt aangeroepen vanuit het Scherm
    public void artikelAnimatie(String s, Timer t, Timer t2, Timer t3, Timer et, Artikel a)
    {
        if (s.equals("naarLinks"))
        {

            t.scheduleAtFixedRate(new naarBeneden(t, a), tijd, 2);
            tijd = tijd + 1000;

            t2.scheduleAtFixedRate(new naarLinks(t2, a), tijd, 2);
            tijd = tijd + 1000;

            et.scheduleAtFixedRate(new opnieuwBeginnen(et), tijd, 1);
            tijd = tijd + 500;

        }
        else if (s.equals("naarRechts"))
        {

            t.scheduleAtFixedRate(new naarBeneden(t, a), tijd, 2);
            tijd = tijd + 1000;
            t2.scheduleAtFixedRate(new naarRechts(t2, a), tijd, 2);
            tijd = tijd + 1000;

            et.scheduleAtFixedRate(new opnieuwBeginnen(et), tijd, 1);
            tijd = tijd + 500;

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

    // Hier worden alle timers bijgehouden die aangemaakt worden
    public ArrayList<TimerTask> getTimers()
    {
        return timers;
    }
    
    // Hieronder alle timers als inner-classes die gekoppeld kunnen worden
    class naarBeneden extends TimerTask
    {
        private int maxY = 400;
        private Timer timer;
        private Artikel artikel;

        public naarBeneden(Timer t, Artikel a)
        {
            timer = t;
            this.artikel = a;
            timers.add(this);
        }

        public void run()
        {

            rectY = rectY + 1;
            artikelHoogte = artikel.getHoogte();
            repaint();
            if (rectY >= maxY)
            {
                timers.remove(this);
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
            timers.add(this);

        }

        public void run()
        {

            rectX = rectX - 1;
            repaint();
            if (rectX <= maxX)
            {
                timers.remove(this);
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
            timers.add(this);
        }

        public void run()
        {

            rectX = rectX + 1;
            repaint();
            if (rectX >= maxX)
            {
                timers.remove(this);
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
            timers.add(this);

        }

        public void run()
        {
            rectX = 268;
            rectY = 15;
            artikelHoogte = 0;
            repaint();
            timers.remove(this);
            timer.cancel();
            if (timers.size() == 0)
            {
                scherm.enableStartButton();
                scherm.disableStopButton();
            }
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
            timers.add(this);

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
                timers.remove(this);
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
            timers.add(this);

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
                timers.remove(this);
                timer.cancel();
            }
        }
    }
}
