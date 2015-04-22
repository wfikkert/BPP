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
    private int hoogte;
    private int breedte;
    private ArrayList <Pakket> inhoudContainer;
    
    public Container(int hoogte, int breedte)
    {
        this.hoogte = hoogte;
        this.breedte = breedte;
    }

    public int getHoogte()
    {
        return hoogte;
    }

    public int getBreedte()
    {
        return breedte;
    }

    public ArrayList<Pakket> getInhoudContainer()
    {
        return inhoudContainer;
    }
    
    public boolean ruimteOver()
    {
        return false;
    }
    
    public void voegToeAanInhoud()
    {
        
    }
    

}
