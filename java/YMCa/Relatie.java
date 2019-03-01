package YMCa;

public class Relatie {
    public int relatieId;
    public String naam;
    public String adres;
    public String postcode;
    public String woonplaats;
    public String telefoonnummer;
    public String email;
    public String organisatie;

    public void setRelatie(String naam, String adres, String postcode, String woonplaats, String telefoonnummer, String email, String organisatie){
      //  this.relatieId= relatieId;
        this.naam =naam;
        this.adres= adres;
        this.postcode= postcode;
        this.woonplaats=woonplaats;
        this.telefoonnummer= telefoonnummer;
        this.email=email;
        this.organisatie=organisatie;
    }

    public String getEmail() {
        return email;
    }
}
