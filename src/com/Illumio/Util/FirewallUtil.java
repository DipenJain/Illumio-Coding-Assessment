package com.Illumio.Util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class FirewallUtil {

    /**
     * Returns ipAddress in long, given a string
     * (Reference: https://stackoverflow.com/questions/4256438/calculate-whether-an-ip-address-is-in-a-specified-range-in-java)
     *
     * @param ipAddress ipAddress in String
     * @return ipAddress in long
     * @throws UnknownHostException
     */
    public static long ipToLong(String ipAddress) throws UnknownHostException {
        InetAddress ip = InetAddress.getByName(ipAddress);
        byte[] octets = ip.getAddress();
        long result = 0;
        for (byte octet : octets) {
            result <<= 8;
            result |= octet & 0xff;
        }
        return result;
    }
}