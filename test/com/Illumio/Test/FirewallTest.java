package com.Illumio.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.Illumio.Firewall;
import java.io.IOException;
import java.net.UnknownHostException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FirewallTest {

    Firewall firewall;

    @Before
    public void setUp() throws IOException {
        firewall = new Firewall("test.csv");
    }

    @Test
    // Testing Inbound tcp with valid port number and valid IP - Matches Rule 1
    public void test1() throws UnknownHostException {
        assertTrue(firewall.accept_packet("inbound", "tcp", 80, "192.168.1.2"));
    }
    
    @Test
    // Testing Inbound tcp with valid port number and invalid IP - Doesnt match
    public void test2() throws UnknownHostException {
        assertFalse(firewall.accept_packet("inbound", "tcp", 80, "192.168.1.1"));
    }
    
    @Test
    // Testing Inbound tcp with invalid port number and valid IP - Doesnt match
    public void test3() throws UnknownHostException {
        assertFalse(firewall.accept_packet("inbound", "tcp", 83, "192.168.1.2"));
    }
    
    @Test
    // Testing outbound tcp with port number in range and valid IP - Matches Rule 2
    public void test4() throws UnknownHostException {
        assertTrue(firewall.accept_packet("outbound", "tcp", 11111, "192.168.10.11"));
    }

    @Test
    // Testing outbound tcp with port number in range and invalid IP - Doesnt match
    public void test5() throws UnknownHostException {
        assertFalse(firewall.accept_packet("outbound", "tcp", 11111, "192.168.10.1"));
    }
    
    @Test
 // Testing outbound tcp with invalid port number and valid IP - Doesnt match
    public void test6() throws UnknownHostException {
        assertFalse(firewall.accept_packet("outbound", "tcp", 20001, "192.168.10.11"));
    }
    
    @Test
    // Testing Inbound udp with valid port number and valid IP - Matches Rule 3
    public void test7() throws UnknownHostException {
        assertTrue(firewall.accept_packet("inbound", "udp", 53, "192.168.1.2"));
    }
    
    @Test
    // Testing Inbound udp with valid port number and invalid IP - Doesnt match
    public void test8() throws UnknownHostException {
        assertFalse(firewall.accept_packet("inbound", "udp", 53, "192.168.0.1"));
    }
    
    @Test
    // Testing Inbound udp with invalid port number and valid IP - Doesnt match
    public void test9() throws UnknownHostException {
        assertFalse(firewall.accept_packet("inbound", "udp", 83, "192.168.1.2"));
    }
    
    @Test
    // Testing outbound udp with port number in range and valid IP - Matches Rule 4
    public void test10() throws UnknownHostException {
        assertTrue(firewall.accept_packet("outbound", "udp", 1111, "52.12.48.92"));
    }

    @Test
    // Testing outbound udp with port number in range and invalid IP - Doesnt match
    public void test11() throws UnknownHostException {
        assertFalse(firewall.accept_packet("outbound", "udp", 1111, "52.12.48.90"));
    }
    
    @Test
 // Testing outbound udp with invalid port number and valid IP - Doesnt match
    public void test12() throws UnknownHostException {
        assertFalse(firewall.accept_packet("outbound", "udp", 20001, "52.12.48.92"));
    }
}
