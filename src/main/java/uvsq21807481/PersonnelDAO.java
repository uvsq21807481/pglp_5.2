package uvsq21807481;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonnelDAO extends DAO<Personnel>{

    @Override
    public Personnel create(Personnel obj){
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO personnel (IDPersonnel, lastName, firstName, birth, job) VALUES (?, ?, ?, ?, ?)");
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
                    "UPDATE personnel SET lastName = ?, firstName = ?, birth = ?, job = ? WHERE IDPersonnel = ?");
            prepare.setString(1, obj.getLastName());
            prepare.setString(2, obj.getFirstName());
            prepare.setString(3, obj.getBirth().toString());
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
