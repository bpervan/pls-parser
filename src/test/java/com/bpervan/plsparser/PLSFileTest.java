package com.bpervan.plsparser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;

import static org.junit.Assert.*;

public class PLSFileTest {

    private PLSFile testFileNovaEva;
    private PLSFile testFile;

    @Before
    public void setUp() throws Exception {
        testFileNovaEva = PLSFile.fromFile(new File("NovaEva_Radio.pls"));
        testFile = PLSFile.fromFile(new File("TestFile.pls"));
    }

    @After
    public void tearDown() throws Exception {
        testFileNovaEva = null;
        testFile = null;
    }

    @Test
    public void testGetNumberOfEntries() throws Exception {
        assertEquals(1, testFileNovaEva.getNumberOfEntries());
    }

    @Test
    public void testGetVersion() throws Exception {
        assertEquals(2, testFileNovaEva.getVersion());
    }

    @Test
    public void testIterator() throws Exception {
        Iterator<PLSEntry> i = testFileNovaEva.iterator();
        PLSEntry entry;
        while(i.hasNext()){
            entry = i.next();
            assertEquals("http://orion.shoutca.st:8328/stream", entry.getLocation());
            assertEquals("Nova Eva", entry.getName());
            assertEquals(-1, entry.getLength());
        }
    }

    @Test
    public void testGetNumberOfEntriesOnTestFile() throws Exception {
        assertEquals(2, testFile.getNumberOfEntries());
    }

    @Test
    public void testGetVersionOnTestFile() throws Exception {
        assertEquals(2, testFile.getVersion());
    }

    @Test
    public void testIteratorOnTestFile() throws Exception {
        Iterator<PLSEntry> i = testFile.iterator();
        PLSEntry entry = null;
        while(i.hasNext()){
            entry = i.next();
        }
        assertEquals("TestFile.mp3", entry.getLocation());
        assertEquals("TestFile", entry.getName());
        assertEquals(312, entry.getLength());
    }
}