package uvsq21807481;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestPersonnelDAO {

    @Test
    public void testCreate(){
        Personnel p = new Personnel.Builder("lastName", "firstName", "job").build();
        PersonnelDAO pdao = new PersonnelDAO();
        Personnel p2 = pdao.create(p);
        assertEquals("lastName", p2.getLastName());
    }

    @Test
    public void testFind(){
        Personnel p = new Personnel.Builder("lastName", "firstName", "job").build();
        PersonnelDAO pdao = new PersonnelDAO();
        Personnel p2 = pdao.find("lastName");
        assertEquals("lastName", p2.getLastName());
    }

    @Test
    public void testUpdate(){
        Personnel p = new Personnel.Builder("lastName", "firstName", "job").build();
        PersonnelDAO pdao = new PersonnelDAO();
        Personnel p2 = new Personnel.Builder("lastName2", "firstName2", "job2").build();
        p = pdao.update(p2);
        assertEquals("lastName2", p.getLastName());
    }
}
