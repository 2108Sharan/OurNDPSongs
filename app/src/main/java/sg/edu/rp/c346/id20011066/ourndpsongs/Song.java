package sg.edu.rp.c346.id20011066.ourndpsongs;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int years;
    private int stars;

    public Song(int id, String title, String singers, int years, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.years = years;
        this.stars = stars;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getSingers() {
        return singers;
    }
    public int getYears() {
        return years;
    }
    public int getStars() {
        return stars;
    }

    @NonNull
    @Override
    public String toString() {

        return title + "\n" + singers + "-" + years + "\n" + toStars();
    }
    public String toStars() {
        String repeated = new String(new char[stars]).replace("\0", "* ");
        return repeated;
    }
}

