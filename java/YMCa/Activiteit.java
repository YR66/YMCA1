package YMCa;

import java.security.PublicKey;

public class Activiteit {
    public int activiteitId;
    public String naam;
    public String omschrijving;
    public Double tarief;
    public int tijdsduur;

    public String activiteitSoort;
    public String aanvang;
    public double uur;


    public void setActivities(int activiteitId, String naam, String omschrijving, Double tarief, int tijdsduur,String activiteitSoort, String tijd) {
        this.activiteitId = activiteitId;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.tarief = tarief;
        this.tijdsduur = tijdsduur;
        this.activiteitSoort = activiteitSoort;
        this.aanvang = tijd;



    }
}
