package ado.rigby.com.do_hackathon.Classes;

/**
 * Created by Roopesh on 26-11-2016.
 */
public class FullSelection {
    private String slug,size,region;
    private boolean ipv,pvtnw,bckup;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isPvtnw() {
        return pvtnw;
    }

    public void setPvtnw(boolean pvtnw) {
        this.pvtnw = pvtnw;
    }

    public boolean isIpv() {
        return ipv;
    }

    public void setIpv(boolean ipv) {
        this.ipv = ipv;
    }

    public boolean isBckup() {
        return bckup;
    }

    public void setBckup(boolean bckup) {
        this.bckup = bckup;
    }
}
