/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPP;

/**
 *
 * Studentnummer: S1079295 Naam: Rik Lugtenberg Klas: ICTM2D Vak : JAVA
 */
public class Pakket
{
    private int hoogte;
    private boolean geplaatst;
    
    public Pakket(int hoogte)
    {
        this.hoogte = hoogte;
    }

    public int getHoogte()
    {
        return hoogte;
    }
    
    public boolean isGeplaatst()
    {
        return geplaatst;
    }
    
    public void setGeplaatst(boolean b)
    {
        geplaatst = b;
    }
    
    public String toString()
    {
        return "Dit pakketje heeft een hoogte van: " + hoogte;
    }
}
