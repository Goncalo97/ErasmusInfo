package pt.goncalo.erasmusinfo;

public class MainCollege {

    public MainCollege() {

    }

    public MainCollege(String name, String country, String location) {
        this.name = name;
        this.country = country;
        this.location = location;
    }

    private String name;
    private String country;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "MainCollege{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}


