package com.spf.wealth.utils;

public class Ip {

    protected int hash;

    protected String supply;

    protected String ip;

    protected int port = 808;

    protected long expire = -1;

    protected long errors = 0;

    public Ip() {
        super();
    }

    public Ip(String supply, String ip, int port, long expire, long errors) {
        super();
        this.supply = supply;
        this.ip = ip;
        this.port = port;
        this.expire = expire;
        this.errors = errors;
        this.hash = (this.ip + this.port).hashCode();
    }

    public synchronized void error() {
        this.errors += 1;
    }

    public String key() {
        return this.ip + ":" + this.port;
    }

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
        this.hash = (this.ip + this.port).hashCode();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
        this.hash = (this.ip + this.port).hashCode();
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public long getErrors() {
        return errors;
    }

    public void setErrors(long errors) {
        this.errors = errors;
    }

    @Override
    public Ip clone() {
        return new Ip(supply, ip, port, expire, errors);
    }

    @Override
    public int hashCode() {
        return (this.ip + this.port).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ip))
            return false;

        Ip _ip = (Ip) obj;
        return (this.ip + this.port).equals(_ip.ip + _ip.port);
    }

    @Override
    public String toString() {
        return this.ip + ":" + this.port;
    }


}
