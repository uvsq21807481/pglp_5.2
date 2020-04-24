package uvsq21807481;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestGroupe {

    @Test
    public void testGroupe(){
        Personnel p = new Personnel.Builder("lastName", "firstName", "job").build();
        Groupe g = new Groupe("groupeTest");
        g.addPersonnel(p);
        assertEquals("lastName", g.getGroupe().get(0).getLastName());
    }
}
