package com.Illumio;

import com.Illumio.Util.FirewallUtil;
import java.io.*;
import java.net.UnknownHostException;
import java.util.*;

public class Firewall implements IFirewall {

    private Set<Rule> rules;
    public Firewall(String path)
    {
        rules = new HashSet<>();
        readInput(path);
    }

    /**
     * Returns true if the given packet is valid
     *
     * @param direction indicates direction of traffic
     * @param protocol indicates packet protocol
     * @param port indicates packet port number
     * @param ip_address indicates packet ip
     * @return true if valid
     */

    @Override
    public boolean accept_packet(String direction, String protocol, int port, String ip_address) {
        Rule testRule = null;
        try {
            testRule = new Rule(direction, protocol, port, FirewallUtil.ipToLong(ip_address));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return rules.contains(testRule);
    }


    /**
     * Reads the file from the given path and populates the hash set
     * @param path file path
     */
    private void readInput(String path)
    {
        String rule;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((rule = br.readLine()) != null) {
                String[] params = rule.split(",");
                populateRuleSet(params);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Computes the hash of the rule and add's it to the set
     *
     * @param ruleParams rule params
     * @throws UnknownHostException
     */
    private void populateRuleSet(String[] ruleParams) throws UnknownHostException{

        String [] portRange = ruleParams[2].split("-");
        int minPort = Integer.parseInt(portRange[0]);
        int maxPort, portRangeDiff = 0;
        if (portRange.length > 1) {
            maxPort = Integer.parseInt(portRange[1]);
            portRangeDiff =  maxPort - minPort;
        }

        String [] ipAddressRange = ruleParams[3].split("-");
        long startRange = FirewallUtil.ipToLong(ipAddressRange[0]);
        long endRange, ipRangeDiff = 0;
        if (ipAddressRange.length > 1) {
            endRange = FirewallUtil.ipToLong(ipAddressRange[1]);
            ipRangeDiff =  endRange - startRange;
        }

        if (portRangeDiff == 0 && ipRangeDiff == 0) {
            Rule currRule = new Rule(ruleParams[0], ruleParams[1], minPort, startRange);
            rules.add(currRule);
        } else if (portRangeDiff > 0 && ipRangeDiff > 0) {
            for (int i = 0; i <= portRangeDiff ; i++) {
                for (int j = 0; j <= ipRangeDiff; j++) {
                    Rule currRule = new Rule(ruleParams[0], ruleParams[1], minPort + i, startRange + j);
                    rules.add(currRule);
                }
            }
        } else if (portRangeDiff > 0) {
            for (int i = 0; i <= portRangeDiff ; i++) {
                Rule currRule = new Rule(ruleParams[0], ruleParams[1], minPort + i, startRange);
                rules.add(currRule);
            }
        } else {
            for (int i = 0; i <= ipRangeDiff ; i++) {
                Rule currRule = new Rule(ruleParams[0], ruleParams[1], minPort, startRange + i);
                rules.add(currRule);
            }
        }
    }
//    Main function to test it
//    public static void main(String[] args)
//    {
//    	Firewall obj = new Firewall("test.csv");
//    	System.out.println(obj.accept_packet("inbound", "tcp", 80, "192.168.1.2"));
//    }
}