package uvsq21807481;

import java.sql.*;

public class PersonnelDAOJBDC extends DAO<Personnel>{

    public PersonnelDAOJBDC() {
        String db = "jbdc:derby:dataDB\\jbdcDB;create=true";
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
        if(!checkTable("PERSONNEL", co)) {
            prepare = co.prepareStatement("CREATE TABLE Personnel (IDPersonnel int PRIMARY KEY, "
                                                                        + "lastName VARCHAR(32) NOT NULL, "
                                                                        + "firstName VARCHAR(32) NOT NULL, "
                                                                        + "birth DATE NOT NULL, "
                                                                        + "job VARCHAR(50))");
            prepare.executeUpdate();
        }

        if(!checkTable("TEL", co)) {
            prepare = co.prepareStatement("CREATE TABLE Tel (numTel INT(10) PRIMARY KEY, "
                                                                + "IDOwner INT NOT NULL, "
                                                                + "FOREIGN KEY (IDOwner) REFERENCES Personnel(IDPersonnel))");
            prepare.executeUpdate();
        }
    }

    @Override
    public Personnel create(Personnel obj){
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO personnel (ID, lastName, firstName, birth, job) VALUES (?, ?, ?, ?)");
            prepare.setString(1, Integer.toString(obj.getID()));
            prepare.setString(2, obj.getLastName());
            prepare.setString(3, obj.getFirstName());
            prepare.setString(4, obj.getBirth().toString());
            prepare.setString(5, obj.getJob());
            int result = prepare.executeUpdate();
            assert result == 1;
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        for(String tel : obj.getPhone()) {
            try {
                String query = "INSERT INTO Tel VALUES " + tel + ", " + obj.getID() + "),";
                PreparedStatement prepare = connect.prepareStatement(query);
                prepare.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    @Override
    public Personnel find(String id){

        Personnel p = null;
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM personnel WHERE IDPersonnel = ?");
            prepare.setString(1, id);
            ResultSet result = prepare.executeQuery();
            if(result.first()){
                p = new Personnel.Builder(
                        Integer.parseInt(result.getString("IDPersonnel")),
                        result.getString("lastName"),
                        result.getString("firstName"),
                        result.getString("birth"),
                        result.getString("job")).build();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public Personnel update(Personnel obj){

        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "UPDATE personnel SET lastName = ?, firstName = ?, job = ? WHERE IDPersonnel = ?");
            prepare.setString(1, obj.getLastName());
            prepare.setString(2, obj.getFirstName());
            prepare.setString(3, obj.getJob());
            prepare.setString(4, Integer.toString(obj.getID()));
            int result = prepare.executeUpdate();
            assert result == 1;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(Personnel obj){

        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE * FROM personnel WHERE IDPersonnel = ?");
            prepare.setString(1, Integer.toString(obj.getID()));
            int result = prepare.executeUpdate();
            assert result == 1;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
