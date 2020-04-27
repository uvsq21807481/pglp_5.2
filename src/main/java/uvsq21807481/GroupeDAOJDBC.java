package uvsq21807481;

import java.sql.*;

public class GroupeDAOJDBC extends DAO<Groupe>{

    public GroupeDAOJDBC() {
        String db = "jdbc:derby:dataDB\\jdbcDB;create=true";
        try {
            connect = DriverManager.getConnection(db);
        }
        catch (SQLException e) {
            connect = null;
            e.printStackTrace();
        }
    }

    private static boolean checkTable(String name, Connection co) throws SQLException {
        DatabaseMetaData dbmd = co.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, name.toUpperCase(), null);
        return rs.next();
    }

    public static void createTable(Connection co) throws SQLException {
        PreparedStatement prepare;
        if(!checkTable("GROUPE", co)) {
            prepare = co.prepareStatement("CREATE TABLE Groupe (IDGroup INT PRIMARY KEY, "
                    + "name VARCHAR(32) NOT NULL)");
            prepare.executeUpdate();
        }

        if(!checkTable("MEMBER", co)) {
            prepare = co.prepareStatement("CREATE TABLE Membre (IDGroup INT NOT NULL, "
                    + "IDMember INT NOT NULL, "
                    + "PRIMARY KEY (IDGroup, IDMember), "
                    + "FOREIGN KEY (IDGroup) REFERENCES Groupe(IDGroup), "
                    + "FOREIGN KEY (IDMember) REFERENCES Personnel(IDPersonnel))");
            prepare.executeUpdate();
        }
    }

    @Override
    public Groupe create(Groupe obj){
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO Groupe (id, name) VALUES (?, ?)");
            prepare.setString(1, Integer.toString(obj.getID()));
            prepare.setString(2, obj.getName());
            int result = prepare.executeUpdate();
            assert result == 1;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Groupe find(String id){

        Groupe g = null;
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM groupe WHERE IDGroup = ?");
            prepare.setString(1, id);
            ResultSet result = prepare.executeQuery();
            if(result.first()){
                g = new Groupe(result.getString("name"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return g;
    }

    @Override
    public Groupe update(Groupe obj){

        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "UPDATE groupe SET name = ? WHERE IDGroup = ?");
            prepare.setString(1, obj.getName());
            prepare.setString(2, Integer.toString(obj.getID()));
            int result = prepare.executeUpdate();
            assert result == 1;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(Groupe obj){

        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE * FROM groupe WHERE IDGroup = ?");
            prepare.setString(1, Integer.toString(obj.getID()));
            int result = prepare.executeUpdate();
            assert result == 1;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
