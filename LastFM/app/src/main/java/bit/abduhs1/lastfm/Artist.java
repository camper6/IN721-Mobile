package bit.abduhs1.lastfm;

/**
 * Created by Sakinah on 14/04/2016.
 */
public class Artist {
    String name;
    String string;

    public Artist(String name, String string) {
        this.name = name;
        this.string = string;
    }

    public String getSecondArgument() {
        return string;
    }

    @Override
    public String toString() {
        return name;
    }
}
