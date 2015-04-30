package BPP;

import java.util.ArrayList;

public class VolledigeEnummeratie
{
    private Scherm scherm;
    private ArrayList<Artikel> artikellijst;
    private ArrayList<Pakket> pakketlijst;
    private ArrayList<Pakket> vollePakketten;
    private ArrayList<Pakket> recordPakketLijst;
    private int record;
    private int mogelijkheid = 0;
    private boolean besteGevonden;

    public VolledigeEnummeratie(ArrayList<Artikel> pl, Scherm scherm)
    {
        artikellijst = new ArrayList<>();
        pakketlijst = new ArrayList<>();
        recordPakketLijst = new ArrayList<>();
        vollePakketten = new ArrayList<>();

        artikellijst = pl;
        this.scherm = scherm;

        for (Artikel a : artikellijst)
        {
            pakketlijst.add(new Pakket());
        }

        record = artikellijst.size() + 1;

    }

    public void vul()
    {
        // Record bekijken
        if (allesGeplaatst())
        {
            // Mogelijkheid teller
            mogelijkheid++;

            // Kijken hoeveel pakketten er gevuld zijn met deze mogelijkheid
            int gevuldePakketten = aantalGevuld();

            // Lijn aan het begin van een container
            System.out.println("______________________________________________________");
            System.out.println("Combinatie: " + mogelijkheid);
            System.out.println("______________________________________________________");
            System.out.println("Record aantal pakketten: " + record);
            System.out.println(" Huidig aantal gevulde pakketten: " + gevuldePakketten);

            // Checken of het een record is
            checkRecord(gevuldePakketten);

            // Stippellijn onder record en huidig gevulde pakketten
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");

            for (Pakket p : recordPakketLijst)
            {
                System.out.println("PAKKETTEN: " + p.getNummer());
                System.out.println("Inhoud");
                int artikelnr = 1;
                for (Artikel a : p.getInhoudPakket())
                {
                    System.out.println("Artikel " + artikelnr + " hoogte: " + a.getHoogte());
                    artikelnr++;
                }
            }
        }

        for (Artikel a : artikellijst)
        {
            if (!a.isGeplaatst())
            {
                for (Pakket p : pakketlijst)
                {

                    if (p.getOvergeblevenHoogte() >= a.getHoogte())
                    {

                        p.voegArtikelToe(a);
                        a.setGeplaatst(true);
                        vul();

                        if (a.getHoogte() < 10)
                        {
                            p.verwijder(a);
                            a.setGeplaatst(false);
                        }
                    }

                }

//                for (Container c : artikellijst)
//                {
//                    
//                    if (c.getOvergeblevenHoogte() >= p.getHoogte())
//                    {
//
//                        c.voegPakketToe(p);
//                        p.setGeplaatst(true);
//                        vul();
//                        
//                        
//                        c.verwijder(p);
//                        p.setGeplaatst(false);
//                    }
//                    
//                }
            }

        }

    }

    public boolean allesGeplaatst()
    {
        boolean b = true;
        for (Artikel a : artikellijst)
        {
            if (!a.isGeplaatst())
            {
                b = false;
            }
        }
        return b;
    }

    public int aantalGevuld()
    {
        int gevuldePakketten = 0;
        for (Pakket p : pakketlijst)
        {
            if (p.getOvergeblevenHoogte() < p.getHoogte())
            {
                gevuldePakketten++;
            }
        }

        return gevuldePakketten;

    }

    public void checkRecord(int i)
    {
        // Check of er een record is, zo ja welke
        if (i < record)
        {
            record = i;
            recordPakketLijst.addAll(pakketlijst);

            resultaatNaarModel();
            System.out.println("Nieuw Record, inhoud per pakket");
            for (Pakket p : recordPakketLijst)
            {
                System.out.println("Pakket: " + p.getNummer());
                System.out.println("Inhoud");
                int artikelnr = 1;
                for (Artikel a : p.getInhoudPakket())
                {
                    System.out.println("Pakket " + artikelnr + " hoogte: " + a.getHoogte());
                    artikelnr++;
                }
            }
        }

    }

    public void resultaatNaarModel()
    {
        int teller = 0;
        int som = 0;
        for (Pakket p : recordPakketLijst)
        {
            if (p.getOvergeblevenHoogte() < p.getHoogte())
            {
                int percentage = (10 - p.getOvergeblevenHoogte()) * 100 / 10;
                teller++;
                som = som + percentage;
            }
        }

        int gemiddelde = som / teller;

        scherm.addResultaten("Volledige Enumeratie", teller, artikellijst.size(), gemiddelde);
    }
}
