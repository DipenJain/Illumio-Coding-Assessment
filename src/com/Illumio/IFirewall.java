package com.Illumio;

public interface IFirewall {
	/**
     * Should return true if the given packet is valid
     *
     * @param direction indicates direction of traffic
     * @param protocol indicates packet protocol
     * @param port indicates packet port number
     * @param ip_address indicates packet ip
     * @return true if valid
     */
    boolean accept_packet(String direction,String protocol, int port, String ip_address);
}
