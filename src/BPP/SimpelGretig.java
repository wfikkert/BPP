package BPP;

import java.util.ArrayList;

public class SimpelGretig extends Algoritme
{

    private Scherm scherm;
    private ArrayList<Integer> resultaten;
    private ArrayList<Artikel> artikellijst;
    private ArrayList<Pakket> actievePakketten;
    private ArrayList<Pakket> vollePakketten;
    private ArrayList<ArrayList<String>> actiePerArtikel;
    

    public SimpelGretig(ArrayList<Artikel> pl, Scherm scherm)
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

            if (actievePakketten.get(0).getOvergeblevenHoogte() >= a.getHoogte())
            {
                // Wanneer artikel in de linker container past -> plaatsen
                actievePakketten.get(0).voegArtikelToe(a);
                actiesVanDitArtikel.add("naarLinks");

            }
            else
            {
                if (actievePakketten.get(1).getOvergeblevenHoogte() >= a.getHoogte())
                {
                    actievePakketten.get(1).voegArtikelToe(a);
                    actiesVanDitArtikel.add("naarRechts");
                }
                else
                {
                    int overgeblevenRuimteEen = actievePakketten.get(0).getOvergeblevenHoogte();
                    int overgeblevenRuimteTwee = actievePakketten.get(1).getOvergeblevenHoogte();

                    if (overgeblevenRuimteEen < overgeblevenRuimteTwee)
                    {
                        vollePakketten.add(actievePakketten.get(0));

                        actievePakketten.set(0, new Pakket()); 

                        actiesVanDitArtikel.add("nieuwLinks");
                    }
                    else
                    {
                        vollePakketten.add(actievePakketten.get(1));

                        actievePakketten.set(1, new Pakket());

                        actiesVanDitArtikel.add("nieuwRechts");
                    }

                    if (actievePakketten.get(0).getOvergeblevenHoogte() >= a.getHoogte())
                    {
                        actievePakketten.get(0).voegArtikelToe(a);
                        actiesVanDitArtikel.add("naarLinks");
                    }
                    else if (actievePakketten.get(1).getOvergeblevenHoogte() >= a.getHoogte())
                    {
                        actievePakketten.get(1).voegArtikelToe(a);
                        actiesVanDitArtikel.add("naarRechts");
                    }
                }

            }
            actiePerArtikel.add(actiesVanDitArtikel);
        }
        resultaatNaarModel();
    }

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

        scherm.addResultaten("Simpel Gretig", teller, artikellijst.size(), gemiddelde);
    }

    public ArrayList<ArrayList<String>> getActiePerArtikel()
    {
        return actiePerArtikel;
    }

    public ArrayList<Pakket> getActievePakketten()
    {
        return actievePakketten;
    }

    public ArrayList<Pakket> getVollePakketten()
    {
        return vollePakketten;
    }
    
    
    
}
