/**
 * Created by Branimir on 31.1.2015..
 */
public class PLSEntry {
    private String location;
    private String name;
    private int length;

    public PLSEntry(String location, String name, int length){
        this.length = length;
        this.name = name;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
