package BPP;

import java.util.ArrayList;

public class SimpelGretig extends Algoritme
{

    private ArrayList<Artikel> artikellijst;
    private ArrayList<Pakket> pakketlijst;
    private ArrayList<String> richtingArray;
    private int hoeveelsteContainer = 0;
    private Pakket huidigeContainer;

    public SimpelGretig(ArrayList<Artikel> pl)
    {
        artikellijst = new ArrayList<>();
        pakketlijst = new ArrayList<>();
        richtingArray = new ArrayList<>();

        artikellijst = pl;
    }

    public ArrayList<String> berekenVolgorde()
    {
        // Container aanmaken bij aanroepen van de functie
        maakPakket();

        // Pakketlijst array doorlopen
        for (int teller = 0; teller < artikellijst.size(); teller++)
        {

            //Pakket en hoogte ervan opslaan in een variabele
            Artikel artikel = artikellijst.get(teller);
            int hoogtePakket = artikel.getHoogte();

            // Overgebleven hoogte van de container uitrekenen
            int hoogteOverContainer = pakketlijst.get(hoeveelsteContainer - 1).getOvergeblevenHoogte();

            // Kijken of de huidige container genoeg ruimte heeft
            if (hoogtePakket <= hoogteOverContainer)
            {
                richtingArray.add("Links");
                huidigeContainer.voegPakketToe(artikel);
            }

            // Zo niet, nieuwe container maken
            else
            {
                maakPakket();
                hoogteOverContainer = pakketlijst.get(hoeveelsteContainer - 1).getOvergeblevenHoogte();
                if (hoogtePakket <= hoogteOverContainer)
                {
                    richtingArray.add("Rechts");
                    huidigeContainer.voegPakketToe(artikel);
                }

                else
                {
                    System.out.println("Containers vol!");

                }
            }

        }
        System.out.println(richtingArray.toString());
        return richtingArray;
    }

    public void maakPakket()
    {
        Pakket pakket = new Pakket();
        pakketlijst.add(pakket);
        hoeveelsteContainer++;
    }
    
    public void soutrichtingArray()
    {
        for(String s : richtingArray)
        {
            System.out.println(s);
        }
    }

}
