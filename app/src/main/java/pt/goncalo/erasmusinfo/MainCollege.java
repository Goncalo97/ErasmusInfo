package pt.goncalo.erasmusinfo;
8
public class MainCollege {

    public MainCollege() {

    }

    public MainCollege(String code, String name, String ects, String discEqual, String score) {
        this.code = code;
        this.name = name;
        this.ects = ects;
        this.discEqual = discEqual;
        this.score = score;
    }

    private String code;
    private String name;
    private String ects;
    private String discEqual;
    private String score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEcts() {
        return ects;
    }

    public void setEcts(String ects) {
        this.ects = ects;
    }

    public String getDiscEqual() {
        return discEqual;
    }

    public void setDiscEqual(String discEqual) {
        this.discEqual = discEqual;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "MainCollege{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", ects='" + ects + '\'' +
                ", discEqual='" + discEqual + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}


