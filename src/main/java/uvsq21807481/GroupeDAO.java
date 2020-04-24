package uvsq21807481;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupeDAO extends DAO<Groupe>{

    @Override
    public Groupe create(Groupe obj){
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO groupe (name) VALUES (?)");
            prepare.setString(1, obj.getName());
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
                    "SELECT * FROM groupe WHERE name = ?");
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
                    "UPDATE groupe SET name = ? WHERE name = ?");
            prepare.setString(1, obj.getName());
            prepare.setString(2, "name");
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
                    "DELETE * FROM groupe WHERE lastName = ?");
            prepare.setString(1, obj.getName());
            int result = prepare.executeUpdate();
            assert result == 1;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
