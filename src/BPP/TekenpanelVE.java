package BPP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
        int pakketteller = 0;

        for (int iArray = 0; iArray < recordPakketLijst.size(); iArray++)
        {
            if (iArray < 30)
            {
                Pakket p = recordPakketLijst.get(iArray);
                if (p.getOvergeblevenHoogte() < p.getHoogte())
                {
                    int beginYArtikel = (beginYPakket + hoogteEnBreedtePakket);

                    for (int iSubArray = 0; iSubArray < p.getInhoudPakket().size(); iSubArray++)
                    {
                        Artikel a = p.getInhoudPakket().get(iSubArray);
                        int artikelHoogte = a.getHoogte() * 10;
                        beginYArtikel = beginYArtikel - artikelHoogte;

                        g.setColor(kleurenGenerator());
                        g.fillRect(beginXPakket, beginYArtikel, hoogteEnBreedtePakket, artikelHoogte);

                        g.setColor(Color.BLACK);
                        g.drawLine(beginXPakket, beginYArtikel, (beginXPakket + hoogteEnBreedtePakket), beginYArtikel);
                    }
                    g.setColor(Color.black);
                    g.drawRect(beginXPakket, beginYPakket, hoogteEnBreedtePakket, hoogteEnBreedtePakket);

                    int lijnX = beginXPakket;
                    int lijnY = beginYPakket;
                    for (int i = 0; i <= 10; i++)
                    {
                        g.drawLine(lijnX - 5, lijnY, (lijnX + 5), lijnY);
                        lijnY += 10;

                    }

                    g.setFont(new Font("SansSerif", Font.BOLD, 10));
                    g.drawString("" + (pakketteller + 1), beginXPakket + 5, beginYPakket + 10);

                    beginXPakket = beginXPakket + hoogteEnBreedtePakket + 10;

                    if (beginXPakket >= 500)
                    {
                        beginXPakket = 10;
                        beginYPakket = beginYPakket + hoogteEnBreedtePakket + 10;
                    }

                }
            }
            pakketteller++;
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
        this.recordPakketLijst.addAll(recordPakketLijst);
        repaint();
    }
}
