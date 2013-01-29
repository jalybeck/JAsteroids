package fi.cebylfwk.manager;

import fi.cebylfwk.Resource;

import java.io.IOException;

import java.nio.ByteBuffer;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ResourceManagerTest {
    public ResourceManagerTest() {
    }
    
    private class ResourceManagerMock extends ResourceManager {
        public ResourceManagerMock() {
            super();
        }
    }
    
    private class ResourceMock implements Resource {
        String name;
        
        public ResourceMock(String name)  {
            this.name = name;
        }

        @Override
        public ByteBuffer getResourceData() {
            return null;
        }

        @Override
        public void release() {
        }
        
        public String toString() {
            return "ResourceMock";
        }
    }
    
    private ResourceManager mgr;
    private final String res1Key = "Kuva1";
    private final String res2Key = "Ääni2";
    
    @Before
    public void setUp() throws Exception {
        mgr = new ResourceManagerMock();
    }

    @After
    public void tearDown() throws Exception {
        mgr = null;
    }

    @Test
    public void testSetAndGetResource() {

        mgr.setResource(res1Key, new ResourceMock(res1Key));
        mgr.setResource(res2Key, new ResourceMock(res2Key));
        
        ResourceMock res1 = null,res2 = null;
        try {
            res2 = (ResourceManagerTest.ResourceMock)mgr.getResource(res2Key);
            res1 = (ResourceManagerTest.ResourceMock)mgr.getResource(res1Key);
        } catch (IOException e) {
            fail(e.getMessage());
        }
        
        assertEquals("Res1.name != \"Kuva1\" ","Kuva1",res1.name);
        assertEquals("Res2.name != \"Ääni2\" ","Ääni2",res2.name);
    }

    @Test
    public void testRemoveResource() {
        mgr.setResource(res2Key, new ResourceMock(res2Key));
        mgr.removeResource(res2Key);
        
        ResourceMock res2 = null;
        try {
            //Should be null when no items exists
            res2 = (ResourceManagerTest.ResourceMock)mgr.getResource(res2Key);
        } catch (IOException e) {
            fail(e.getMessage());
        }
        
        assertNull("res2 is not null!",res2);
    }

    @Test
    public void testGetResourceCount() {
        mgr.setResource("Test1", new ResourceMock("Test1"));
        mgr.setResource("Test1", new ResourceMock("Test112414"));
        mgr.setResource("Test2", new ResourceMock("Test2"));
        mgr.setResource("Test3", new ResourceMock("Test3"));
        
        //Resource count should 3
        assertEquals("getResourceCount() != 3",3,mgr.getResourceCount());
    }
    
    @Test
    public void testClear() {
        mgr.setResource("Test1", new ResourceMock("Test1"));
        mgr.setResource("Test1", new ResourceMock("Test112414"));
        mgr.setResource("Test2", new ResourceMock("Test2"));
        mgr.setResource("Test3", new ResourceMock("Test3"));
        
        mgr.clear();
        
        assertEquals("getResourceCount() != 0",0,mgr.getResourceCount());
    }

}
