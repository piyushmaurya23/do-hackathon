package model;

/**
 * Created by soumya singh on 11/26/2016.
 */
public class droplet {
    public String getDroplet_name() {
        return droplet_name;
    }

    public void setDroplet_name(String droplet_name) {
        this.droplet_name = droplet_name;
    }

    String droplet_name;

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    String memory;

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    String ip_address;

    public String getDisk_space() {
        return disk_space;
    }

    public void setDisk_space(String disk_space) {
        this.disk_space = disk_space;
    }

    String disk_space;
}
