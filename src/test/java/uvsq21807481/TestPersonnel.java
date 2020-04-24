package uvsq21807481;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPersonnel {

    @Test
    public void testBuilder(){
        Personnel p = new Personnel.Builder("lastName", "firstName", "job").build();
        assertEquals("lastName", p.getLastName());
    }

    @Test
    public void testPhone(){
        Personnel.Builder b = new Personnel.Builder("lastName", "firstName", "job");
        b.addPhone("test");
        Personnel p = b.build();
        assertEquals("test", p.getPhone().get(0));
    }
}
