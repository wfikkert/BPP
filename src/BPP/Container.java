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
public class Container
{
    private int nummerContainer;
    private int hoogte;
    private ArrayList<Pakket> inhoudContainer;

    public Container()
    {
        inhoudContainer = new ArrayList<>();
        hoogte = 10;
    }
    
    public Container(int i)
    {
        this();
        nummerContainer = i;
        
    }

    public int getHoogte()
    {
        return hoogte;
    }

    public ArrayList<Pakket> getInhoudContainer()
    {
        return inhoudContainer;
    }

    public int getOvergeblevenHoogte()
    {
        int overgeblevenHoogte = hoogte;

        for (Pakket p : inhoudContainer)
        {
            overgeblevenHoogte = overgeblevenHoogte - p.getHoogte();
        }
        return overgeblevenHoogte;
    }

    public void voegPakketToe(Pakket p)
    {
        inhoudContainer.add(p);

    }

    public void verwijder(Pakket p)
    {
        inhoudContainer.remove(p);
    }

    public String toString()
    {
        return "Deze container heeft een overgebleven hoogte van " + getOvergeblevenHoogte();
    }

    public int getNummer()
    {
        return nummerContainer;
    }
}
