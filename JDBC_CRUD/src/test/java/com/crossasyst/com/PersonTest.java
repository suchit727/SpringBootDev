package com.crossasyst.com;

import junit.framework.TestCase;
import org.junit.Test;

public class PersonTest extends TestCase {
    @Test
    public void testHelloPrint() {
        Person p=new Person();
        assertEquals("Hello",p.helloPrint());
    }
}