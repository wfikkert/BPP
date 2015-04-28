/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPP;

import java.util.ArrayList;

/**
 *
 * Studentnummer: S1079295 Naam: Rik Lugtenberg Klas: ICTM2D Vak : JAVA
 */
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

    public ArrayList<Artikel> getInhoudContainer()
    {
        return inhoudPakket;
    }

    public int getOvergeblevenHoogte()
    {
        int overgeblevenHoogte = hoogte;

        for (Artikel p : inhoudPakket)
        {
            overgeblevenHoogte = overgeblevenHoogte - p.getHoogte();
        }
        return overgeblevenHoogte;
    }

    public void voegPakketToe(Artikel p)
    {
        inhoudPakket.add(p);

    }

    public void verwijder(Artikel p)
    {
        inhoudPakket.remove(p);
    }

    public String toString()
    {
        return "Deze container heeft een overgebleven hoogte van " + getOvergeblevenHoogte();
    }

    public int getNummer()
    {
        return nummerPakket;
    }
}
