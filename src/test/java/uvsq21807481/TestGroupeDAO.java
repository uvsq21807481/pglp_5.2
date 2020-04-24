package uvsq21807481;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGroupeDAO {

    @Test
    public void testCreate(){
        Groupe g = new Groupe("name");
        GroupeDAO gdao = new GroupeDAO();
        Groupe g2 = gdao.create(g);
        assertEquals("name", g.getName());
    }

    @Test
    public void testFind(){
        Groupe g = new Groupe("name");
        GroupeDAO gdao = new GroupeDAO();
        Groupe g2 = gdao.find("name");
        assertEquals("name", g2.getName());
    }

    @Test
    public void testUpdate(){
        Groupe g = new Groupe("name");
        GroupeDAO gdao = new GroupeDAO();
        Groupe g2 = new Groupe("name2");
        g = gdao.update(g2);
        assertEquals("name2", g.getName());
    }
}
