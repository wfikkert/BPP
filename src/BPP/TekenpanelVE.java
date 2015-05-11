package BPP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
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

        int hoogteEnBreedtePakket = 120;
        int beginXPakket = 10;
        int beginYPakket = 10;

        for (Pakket p : recordPakketLijst)
        {
            g.setColor(Color.green);
            g.fillRect(beginXPakket, beginYPakket, hoogteEnBreedtePakket, hoogteEnBreedtePakket);

            
            
            
            
            beginXPakket = beginXPakket + hoogteEnBreedtePakket + 10;
            if (beginXPakket >= 500)
            {
                beginXPakket = 10;
                beginYPakket = beginYPakket + hoogteEnBreedtePakket + 10;
            }
        }

    }

    public void artikelAnimatie(ArrayList<Pakket> recordPakketLijst)
    {
        this.recordPakketLijst = recordPakketLijst;
        repaint();
    }
}
