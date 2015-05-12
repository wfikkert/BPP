package BPP;

import java.util.ArrayList;

public class AlmostWorst extends Algoritme
{

    private Scherm scherm;
    private ArrayList<Integer> resultaten;
    private ArrayList<Artikel> artikellijst;
    private ArrayList<Pakket> actievePakketten;
    private ArrayList<Pakket> vollePakketten;
    private ArrayList<ArrayList<String>> actiePerArtikel;
    
    public AlmostWorst(ArrayList<Artikel> pl, Scherm scherm)
    {
        artikellijst = new ArrayList<>();
        actievePakketten = new ArrayList<>();
        vollePakketten = new ArrayList<>();
        actiePerArtikel = new ArrayList<>();
        
        this.scherm = scherm;

        // Twee nieuwe pakketten maken
        actievePakketten.add(new Pakket());
        actievePakketten.add(new Pakket());

        artikellijst = pl;

    }

    public void vul()
    {
        ArrayList<String> actiesVanDitArtikel;
        for (Artikel a : artikellijst)
        {
            actiesVanDitArtikel = new ArrayList<>();
            
            if (actievePakketten.get(0).getOvergeblevenHoogte() >= actievePakketten.get(1).getOvergeblevenHoogte())
            {
                if (actievePakketten.get(0).getOvergeblevenHoogte() >= a.getHoogte())
                {
                    // Wanneer artikel in de linker container past -> plaatsen
                    actievePakketten.get(0).voegArtikelToe(a);
                    actiesVanDitArtikel.add("naarLinks");
                }
                else
                {
                    // Linker container had meer ruimte over dan de rechter,
                    // door bovenstaande if-statement weet je dat hij niet in 
                    // de linker container pastte, dus past hij ook niet in de rechter.
                    // In het rechter pakket is volst, dus die vervang je.
                    vollePakketten.add(actievePakketten.get(1));
                    actievePakketten.set(1, new Pakket());
                    actiesVanDitArtikel.add("nieuwRechts");
                    
                    
                    // Je weet nu dat de rechtercontainer nieuw is, dus kan het pakket in de
                    // rechter container.
                    actievePakketten.get(1).voegArtikelToe(a);
                    actiesVanDitArtikel.add("naarRechts");
                }
            }
            else
            {
                if (actievePakketten.get(1).getOvergeblevenHoogte() >= a.getHoogte())
                {
                    // Zelfde geldt als er meer ruimte over is in de rechter container.
                    actievePakketten.get(1).voegArtikelToe(a);
                    actiesVanDitArtikel.add("naarRechts");
                }
                else
                {
                    vollePakketten.add(actievePakketten.get(0));
                    actievePakketten.set(0, new Pakket());
                    actiesVanDitArtikel.add("nieuwLinks");
                    actievePakketten.get(0).voegArtikelToe(a);
                    actiesVanDitArtikel.add("naarLinks");
                }
            }
            actiePerArtikel.add(actiesVanDitArtikel);
            
        }
        resultaatNaarModel();
    }

    // Printen van alle volle pakketten en actieve pakketten
    public void printResultaat()
    {
        System.out.println("Volle pakketten");
        for (Pakket p : vollePakketten)
        {
            System.out.println("-----------------------------------------------");
            p.printInhoud();
            System.out.println("-----------------------------------------------");
        }
        System.out.println("Actieve pakketten");
        for (Pakket p : actievePakketten)
        {
            System.out.println("-----------------------------------------------");
            p.printInhoud();
            System.out.println("-----------------------------------------------");
        }
    }

    // Resultaat naar het tabel in het scherm
    public void resultaatNaarModel()
    {
        int teller = 0;
        int som = 0;
        for (Pakket p : vollePakketten)
        {
            int percentage = (10 - p.getOvergeblevenHoogte()) * 100 / 10;
            teller++;
            som = som + percentage;
        }
        for (Pakket p : actievePakketten)
        {
            if (p.getOvergeblevenHoogte() != 10)
            {
                int percentage = (10 - p.getOvergeblevenHoogte()) * 100 / 10;
                teller++;
                som = som + percentage;
            }
        }
        int gemiddelde = som / teller;

        scherm.addResultaten("Almost Worst", teller, artikellijst.size(), gemiddelde);
    }
    
    // Returnen van actiePerArtikel
    public ArrayList<ArrayList<String>> getActiePerArtikel()
    {
        return actiePerArtikel;
    }

}
