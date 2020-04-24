package uvsq21807481;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonnelDAO extends DAO<Personnel>{

    @Override
    public Personnel create(Personnel obj){
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO personnel (nom, prenom, fonction) VALUES (?, ?, ?)");
            prepare.setString(1, obj.getLastName());
            prepare.setString(2, obj.getFirstName());
            prepare.setString(3, obj.getJob());
            int result = prepare.executeUpdate();
            assert result == 1;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Personnel find(String id){

        Personnel p = null;
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM personnel WHERE lastName = ?");
            prepare.setString(1, id);
            ResultSet result = prepare.executeQuery();
            if(result.first()){
                p = new Personnel.Builder(
                        result.getString("lastName"),
                        result.getString("firstName"),
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
                    "UPDATE personnel SET lastName = ?, firstName = ?, job = ? WHERE lastName = ?");
            prepare.setString(1, obj.getLastName());
            prepare.setString(2, obj.getFirstName());
            prepare.setString(3, obj.getJob());
            prepare.setString(4, "lastName");
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
                    "DELETE * FROM personnel WHERE lastName = ?");
            prepare.setString(1, obj.getLastName());
            int result = prepare.executeUpdate();
            assert result == 1;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
