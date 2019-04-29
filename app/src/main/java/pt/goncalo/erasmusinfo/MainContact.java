package pt.goncalo.erasmusinfo;

public class MainContact {

    public MainContact() {

    }

    public MainContact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    private String name;
    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "MainContact{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}