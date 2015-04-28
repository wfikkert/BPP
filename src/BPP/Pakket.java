package BPP;

import java.util.ArrayList;

public class Pakket
{
    private int nummerPakket;
    private int hoogte;
    private ArrayList<Artikel> inhoudPakket;

    public Pakket()
    {
        inhoudPakket = new ArrayList<>();
        hoogte = 10;
    }
    
    public Pakket(int i)
    {
        this();
        nummerPakket = i;
        
    }

    public int getHoogte()
    {
        return hoogte;
    }

    public ArrayList<Artikel> getInhoudPakket()
    {
        return inhoudPakket;
    }

    public int getOvergeblevenHoogte()
    {
        int overgeblevenHoogte = hoogte;

        for (Artikel a : inhoudPakket)
        {
            overgeblevenHoogte = overgeblevenHoogte - a.getHoogte();
        }
        return overgeblevenHoogte;
    }

    public void voegArtikelToe(Artikel a)
    {
        inhoudPakket.add(a);

    }

    public void verwijder(Artikel a)
    {
        inhoudPakket.remove(a);
    }

    public String toString()
    {
        return "Dit pakket heeft een overgebleven hoogte van " + getOvergeblevenHoogte();
    }

    public int getNummer()
    {
        return nummerPakket;
    }
}
