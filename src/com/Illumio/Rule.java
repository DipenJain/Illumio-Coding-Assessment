package com.Illumio;

public class Rule {
    private String direction;
    private String protocol;
    private int port;
    private long ipAddress;

    public Rule(String direction, String protocol, int port, long ipAddress)
    {
        this.direction = direction;
        this.protocol = protocol;
        this.port = port;
        this.ipAddress = ipAddress;
    }

    public String getDirection()
    {
        return direction;
    }
    public String getProtocol()
    {
        return protocol;
    }
    public int getPort()
    {
        return port;
    }
    public long getIpAddress()
    {
        return ipAddress;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
        result = prime * result + (int)(ipAddress ^ (ipAddress>>>32));
        result = prime * result + port;
        result = prime * result + ((protocol == null) ? 0 : protocol.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return this.direction +  ", " + this.protocol + ", " + Integer.toString(this.port) + ", " + Long.toString(this.ipAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return port == rule.port &&
                ipAddress == rule.ipAddress &&
                direction.equals(rule.direction) &&
                protocol.equals(rule.protocol);
    }
}