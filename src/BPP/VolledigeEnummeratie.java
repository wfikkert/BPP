package BPP;

import java.util.ArrayList;

public class VolledigeEnummeratie
{
    private ArrayList<Artikel> artikellijst;
    private ArrayList<Pakket> pakketlijst;
    private ArrayList<Pakket> vollePakketten;
    private int record;
    private ArrayList<Pakket> recordPakketLijst;
    private int mogelijkheid = 0;
    private boolean besteGevonden;

    public VolledigeEnummeratie(ArrayList<Artikel> pl)
    {
        artikellijst = new ArrayList<>();
        pakketlijst = new ArrayList<>();
        recordPakketLijst = new ArrayList<>();
        vollePakketten = new ArrayList<>();

        artikellijst = pl;

        for (Artikel p : artikellijst)
        {
            pakketlijst.add(new Pakket());
        }
        record = artikellijst.size();

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
                for (Artikel a : p.getInhoudContainer())
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
                int i = 0;
                while (i < artikellijst.size() && i != -1)
                {
                    Pakket p = pakketlijst.get(i);
                    if (p.getOvergeblevenHoogte() >= a.getHoogte())
                    {
                        p.voegPakketToe(a);
                        a.setGeplaatst(true);
                        vul();

                        
                        p.verwijder(a);
                        a.setGeplaatst(false);
                        i++;

                    }
                    else
                    {
                        
                        System.out.println("Pakketten vol!");
                        
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
            recordPakketLijst = pakketlijst;
            System.out.println("Nieuw Record, inhoud per pakket");
            for (Pakket p : recordPakketLijst)
            {
                System.out.println("Pakket: " + p.getNummer());
                System.out.println("Inhoud");
                int artikelnr = 1;
                for (Artikel a : p.getInhoudContainer())
                {
                    System.out.println("Pakket " + artikelnr + " hoogte: " + a.getHoogte());
                    artikelnr++;
                }
            }
        }

    }
}
