package BPP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class TekenpanelVE extends JPanel
{
    private ArrayList<Pakket> recordPakketLijst;

    public TekenpanelVE()
    {
        this.setPreferredSize(new Dimension(625, 670));
        recordPakketLijst = new ArrayList<>();

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.gray);

        int hoogteEnBreedtePakket = 100;
        int beginXPakket = 10;
        int beginYPakket = 10;

        for (Pakket p : recordPakketLijst)
        {
            int artikelY = beginYPakket + 100;
            g.setColor(Color.black);
            g.drawRect(beginXPakket, beginYPakket, hoogteEnBreedtePakket, hoogteEnBreedtePakket);

            for (Artikel a : p.getInhoudPakket())
            {
                System.out.println("teken");
                int artikelHoogte = a.getHoogte() * 10;
                g.setColor(kleurenGenerator());

                g.fillRect(beginXPakket, (artikelY - artikelHoogte), hoogteEnBreedtePakket, artikelY);
                artikelY = artikelY - artikelHoogte;
            }

            beginXPakket = beginXPakket + hoogteEnBreedtePakket + 10;
            if (beginXPakket >= 500)
            {
                beginXPakket = 10;
                beginYPakket = beginYPakket + hoogteEnBreedtePakket + 10;
            }
        }

    }

    public Color kleurenGenerator()
    {
        ArrayList<Color> kleuren;
        kleuren = new ArrayList<>();

        kleuren.add(Color.RED);
        kleuren.add(Color.WHITE);
        kleuren.add(Color.GREEN);
        kleuren.add(Color.YELLOW);
        kleuren.add(Color.BLUE);
        kleuren.add(Color.PINK);

        Random random = new Random();
        Color randomColor = kleuren.get(random.nextInt(kleuren.size() - 1));
        return randomColor;
    }

    public void artikelAnimatie(ArrayList<Pakket> recordPakketLijst)
    {
        this.recordPakketLijst = recordPakketLijst;
        repaint();
    }
}
