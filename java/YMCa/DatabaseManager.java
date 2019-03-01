package YMCa;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

@RestController
public class DatabaseManager {
    private static int updated;


   /** @Autowired
    private static DataSource dataSource;

    private static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }*/
  private static Connection conn = null;

    private static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(Application.HOST_NAME, Application.USER_NAME, Application.PASSWORD);
        }
        return conn;
    }


    protected static Relatie getRelatie(int relatieId) throws SQLException {
        String sql = "SELECT * FROM relaties WHERE relatieId = ?";
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, relatieId);
            ResultSet res = stmt.executeQuery();
            Relatie rv = new Relatie();
            if (res.next()) {
                rv.relatieId=res.getInt("relatieId");
                rv.naam=res.getString("naam");
                rv.adres=res.getString("adres");
                rv.woonplaats=res.getString("woonplaats");
                rv.telefoonnummer=res.getString("telefoonnummer");
                rv.email=res.getString("email");
                rv.organisatie=res.getString("organisatie");

                return rv;
            }
            return null;
        }
    }

    protected static void setPrijsOfferte(int offerteId, int programmaId){

        try {
            PreparedStatement stmt = getConnection().prepareStatement(
                    "\n" +
                            "update offertes  set prijs=(select sum(a.tarief) from activiteiten a join programmas p\n" +
                            "on a.activiteitId= p.activiteitId\n" +
                            "where programmaId = ?) where offerteId=?; ");
            stmt.setInt(1, programmaId);
            stmt.setInt(2, offerteId);
            int updated = stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    protected static ArrayList<Activiteit> getActiviteit() throws SQLException {
        ArrayList<Activiteit> results = new ArrayList<>();
        String sql = "SELECT activiteitId, naam, omschrijving, tarief, (tijdsduur/60), activiteitSoort FROM activiteiten";
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Activiteit a = new Activiteit();
                a.activiteitId = res.getInt(1);
                a.naam = res.getString(2);
                a.omschrijving = res.getString(3);
                a.tarief = res.getDouble(4);
                a.uur= res.getDouble(5);
                a.activiteitSoort= res.getString(6);
                results.add(a);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    protected static ArrayList<Activiteit> getActiviteit(String activiteitSoort) throws SQLException {
        ArrayList<Activiteit> results = new ArrayList<>();
        String sql = "SELECT activiteitId, naam, omschrijving, tarief, tijdsduur, activiteitSoort FROM activiteiten where activiteitSoort = ?";

        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, activiteitSoort);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Activiteit a = new Activiteit();
                a.activiteitId = res.getInt(1);
                a.naam = res.getString(2);
                a.omschrijving = res.getString(3);
                a.tarief = res.getDouble(4);
                a.tijdsduur= res.getInt(5);
                a.activiteitSoort= res.getString(6);
                results.add(a);
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    protected static Activiteit getActiviteitDetail(int activiteitId) throws SQLException {
        String sql = "SELECT * FROM relaties WHERE relatieId = ?";
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, activiteitId);
            ResultSet res = stmt.executeQuery();
            Activiteit rv = new Activiteit();
            if (res.next()) {
                rv.activiteitId =res.getInt("actviteitId");
                rv.naam=res.getString("naam");
                rv.omschrijving=res.getString("omschrijving");
                rv.tarief=res.getDouble("tarief");
                rv.tijdsduur=res.getInt("tijdsduur");
                rv.activiteitSoort=res.getString("activiteitSoort");

                return rv;
            }
            return null;
        }
    }

    protected static ArrayList<Activiteit> getActiviteitDetails(int activiteitId) throws SQLException {
        ArrayList<Activiteit> results = new ArrayList<>();
        String sql = "SELECT activiteitId, naam, omschrijving, tarief, tijdsduur, activiteitSoort FROM activiteiten where activiteitId = ?";

        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, activiteitId);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Activiteit a = new Activiteit();
                a.activiteitId = res.getInt(1);
                a.naam = res.getString(2);
                a.omschrijving = res.getString(3);
                a.tarief = res.getDouble(4);
                a.tijdsduur= res.getInt(5);
                a.activiteitSoort= res.getString(6);
                results.add(a);
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    protected static ArrayList<Relatie> getRelaties() throws SQLException {
        ArrayList<Relatie> results = new ArrayList<>();
        String sql = "SELECT relatieId, naam, adres, postcode, woonplaats, telefoonnummer, email, organisatie FROM relaties";
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Relatie a = new Relatie();
                a.relatieId = res.getInt(1);
                a.naam = res.getString(2);
                a.adres = res.getString(3);
                a.postcode = res.getString(4);
                a.woonplaats=res.getString(5);
                a.telefoonnummer= res.getString(6);
                a.email= res.getString(7);
                a.organisatie= res.getString(8);
                results.add(a);
            }
        }
        return results;
    }

    protected static int setProgramma( Programma newData)throws SQLException {
        String sql = "";
        int rv = -1;
        sql = "INSERT INTO programmas (programmaId, datum, activiteitId, offerteId, tijd ) VALUES (?,?,?,?,?)";



        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {



            stmt.setInt(1, newData.programmaId);
            stmt.setDate(2, newData.datum);
            stmt.setInt(3, newData.activiteitId);
            stmt.setInt(4, newData.offerteId);
            stmt.setString(5, newData.tijd);

            stmt.execute();

            if (newData.activiteitId2 != 0){
                stmt.setInt(1, newData.programmaId);
                stmt.setDate(2, newData.datum);
                stmt.setInt(3, newData.activiteitId2);
                stmt.setInt(4, newData.offerteId);
                stmt.setString(5, newData.tijd2);
                stmt.execute();
            }

            if (newData.activiteitId3 != 0){
                stmt.setInt(1, newData.programmaId);
                stmt.setDate(2, newData.datum);
                stmt.setInt(3, newData.activiteitId3);
                stmt.setInt(4, newData.offerteId);
                stmt.setString(5, newData.tijd3);
                stmt.execute();
            }

            if (newData.activiteitId4 != 0){
                stmt.setInt(1, newData.programmaId);
                stmt.setDate(2, newData.datum);
                stmt.setInt(3, newData.activiteitId4);
                stmt.setInt(4, newData.offerteId);
                stmt.setString(5, newData.tijd4);
                stmt.execute();
            }
            if (newData.activiteitId5 != 0){
                stmt.setInt(1, newData.programmaId);
                stmt.setDate(2, newData.datum);
                stmt.setInt(3, newData.activiteitId5);
                stmt.setInt(4, newData.offerteId);
                stmt.setString(5, newData.tijd5);
                stmt.execute();
            }





            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) rv = keys.getInt(1);

        }
        return rv;
    }

    protected static ArrayList<Offerte> getOfferte() throws SQLException {
        ArrayList<Offerte> results = new ArrayList<>();

        String sql = "select * From offertes join relaties on offertes.relatieId = relaties.relatieId join programmas \n" +
        "on offertes.programmaId= programmas.programmaId\n" +
                "join activiteiten \n" +
                "on activiteiten.activiteitId = programmas.activiteitId"
                //"Where o.offerteId =?"
                ;

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {

            //stmt.setInt(1, offerteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Offerte off = new Offerte();
                int offerteId = rs.getInt("offertes.offerteId");
                off = DatabaseHelper.ContainsOfferteId(results, offerteId);
                if(off == null)
                {
                    off = new Offerte();
                    off.offerteId = rs.getInt("offertes.offerteId");
                    off.naam = rs.getString("relaties.naam");
                    off.adres = rs.getString("adres");
                    off.postcode = rs.getString("postcode");
                    off.woonplaats = rs.getString("woonplaats");
                    off.prijs = rs.getDouble("offertes.prijs");
                    off.deelnemers = rs.getInt("deelnemers");
                    off.telefoonnummer = rs.getString("telefoonnummer");
                    off.email = rs.getString("email");
                    off.organisatie = rs.getString("organisatie");
                    off.medewerker = rs.getString("medewerker");
                    off.status = rs.getString("status");
                    off.datum = rs.getDate("offertes.datum");
                    off.activiteitDatum = rs.getDate("programmas.datum");
                    Activiteit activiteit = new Activiteit();
                    activiteit.naam = rs.getString("activiteiten.naam");
                    activiteit.omschrijving = rs.getString("omschrijving");
                    activiteit.tarief = rs.getDouble("tarief");
                    off.activiteiten.add(activiteit);
                    results.add(off);
                }
                else {
                    Activiteit activiteit = new Activiteit();
                    activiteit.naam = rs.getString("activiteiten.naam");
                    activiteit.omschrijving = rs.getString("omschrijving");
                    activiteit.tarief = rs.getDouble("tarief");
                    off.activiteiten.add(activiteit);
                }
            }
            return results;
        }
    }

    protected static Offerte getOfferte(int offerteId) throws SQLException {
       Offerte off = null;
        String sql = "select offertes.offerteId, relaties.naam, adres, postcode, woonplaats, telefoonnummer, email, organisatie, tijd, activiteiten.naam, tarief, omschrijving, medewerker, status, offertes.datum, offertes.prijs, programmas.datum From offertes join relaties on offertes.relatieId = relaties.relatieId join programmas \n" +
                "on offertes.programmaId= programmas.programmaId\n" +
                "join activiteiten \n" +
                "on activiteiten.activiteitId = programmas.activiteitId\n" +
                "Where offertes.offerteId = ?\n" +
                "ORDER BY tijd ASC";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {

            stmt.setInt(1, offerteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                if(off == null)
                {
                    off = new Offerte();

                    off.offerteId = rs.getInt("offertes.offerteId");
                    off.naam = rs.getString("relaties.naam");
                    off.adres = rs.getString("adres");
                    off.postcode = rs.getString("postcode");
                    off.woonplaats = rs.getString("woonplaats");
                    off.prijs = rs.getDouble("offertes.prijs");
                    off.telefoonnummer = rs.getString("telefoonnummer");
                    off.email = rs.getString("email");
                    off.organisatie = rs.getString("organisatie");
                    off.medewerker = rs.getString("medewerker");
                    off.status = rs.getString("status");
                    off.datum = rs.getDate("offertes.datum");
                    off.activiteitDatum = rs.getDate("programmas.datum");


                    Activiteit activiteit = new Activiteit();
                    activiteit.naam = rs.getString("activiteiten.naam");
                    activiteit.omschrijving = rs.getString("omschrijving");
                    activiteit.tarief = rs.getDouble("tarief");
                    activiteit.aanvang = rs.getString("tijd");
                    off.activiteiten.add(activiteit);
                }
                else {
                    Activiteit activiteit = new Activiteit();
                    activiteit.naam = rs.getString("activiteiten.naam");
                    activiteit.omschrijving = rs.getString("omschrijving");
                    activiteit.tarief = rs.getDouble("tarief");
                    activiteit.aanvang = rs.getString("tijd");
                    off.activiteiten.add(activiteit);
                }
            }
            return off;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected static int getNumberOffertes(String status)throws SQLException {
        String sql = "SELECT count(offerteId) FROM offertes WHERE status = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, status);
            ResultSet res = stmt.executeQuery();
            int nummer = 0;
            while (res.next()) {
                nummer = res.getInt(1);
            }
            return nummer;

        }
    }

    protected static int setOfferte( Offerte newData)throws SQLException {
        String sql = "";
        int rv = -1;
        sql = "INSERT INTO offertes (offerteId, relatieId, programmaId, deelnemers, medewerker, datum, status) values (?,?,?,?,?,?,?)";



        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, newData.offerteId);
            stmt.setInt(2, newData.relatieId);
            stmt.setInt(3, newData.programmaId);
            stmt.setInt(4, newData.deelnemers);
            stmt.setString(5, newData.medewerker);
            stmt.setDate(6, newData.datum);
            stmt.setString(7, newData.status);

            stmt.execute();
            setPrijsOfferte(newData.offerteId, newData.programmaId);

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) rv = keys.getInt(1);

        }
        return rv;
    }

    protected static int updateOfferte( Offerte newData)throws SQLException {
        String sql = "";
        int rv = -1;
        sql = "update offertes set relatieId=?, programmaId=?, deelnemers=?, medewerker=?, datum=?, status=? where offerteId=?";



        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setInt(1, newData.relatieId);
            stmt.setInt(2, newData.programmaId);
            stmt.setInt(3, newData.deelnemers);
            stmt.setString(4, newData.medewerker);
            stmt.setDate(5, newData.datum);
            stmt.setString(6, newData.status);
            stmt.setInt(7, newData.offerteId);

            stmt.execute();
            setPrijsOfferte(newData.offerteId, newData.programmaId);

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) rv = keys.getInt(1);

        }
        return rv;
    }

    protected static int updateStatusOfferte( Offerte newData)throws SQLException {
        String sql = "";
        int rv = -1;
        sql = "update offertes set status=? where offerteId=?";



        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setString(1, newData.status);
            stmt.setInt(2, newData.offerteId);

            stmt.execute();


            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) rv = keys.getInt(1);

        }
        return rv;
    }

    protected static int getOffertesOnDate(String start, String end)throws SQLException{
        String sql = "SELECT count(*) FROM offertes join programmas using (programmaId) WHERE programmas.datum between '" + start + "' and '" + end + "'";
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            ResultSet res = stmt.executeQuery();
            int nummer = 0;
            while (res.next()) {
                nummer = res.getInt(1);
            }
            return nummer;
        }

    }

    protected static ArrayList<Medewerker> getMedewerker() throws SQLException {
        ArrayList<Medewerker> results = new ArrayList<>();
        String sql = "SELECT * FROM medewerkers";
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Medewerker m = new Medewerker();
                m.medewerker = res.getString(1);
                results.add(m);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    protected static ArrayList<Status> getStatus() throws SQLException {
        ArrayList<Status> results = new ArrayList<>();
        String sql = "SELECT * FROM offertestatus";
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Status s = new Status();
                s.status = res.getString(1);
                results.add(s);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }
}