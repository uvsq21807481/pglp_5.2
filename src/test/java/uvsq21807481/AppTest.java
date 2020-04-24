package uvsq21807481;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void writeTest(){
        Personnel p = new Personnel.Builder("lastName", "firstName", "job").build();
        Groupe g = new Groupe("groupeTest");
        g.addPersonnel(p);

        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("/home/user/IdeaProjects/pglp_5.1/serial")))){
            out.writeObject(g);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void readTest(){
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("/home/user/IdeaProjects/pglp_5.1/serial")))){
            Groupe g = (Groupe)in.readObject();
            for(Personnel p : g.getGroupe()){
                System.out.println(p.getLastName());
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
