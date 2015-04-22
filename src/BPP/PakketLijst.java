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
public class PakketLijst
{
    private ArrayList <Pakket> lijst;
    
    public PakketLijst()
    {
        lijst = new ArrayList<>();
    }
    
    public void voegToe(Pakket pakketje)
    {
        lijst.add(pakketje);
    }

    public ArrayList<Pakket> getLijst()
    {
        return lijst;
    }
    
    public void printLijst()
    {

    }
            

}
