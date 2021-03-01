package requests;

import java.util.ArrayList;
import java.util.List;

public class CreateGameRequest {

     Integer gameID;
     String title;
     Integer releaseYear;
     String category;
     List<Integer> platforms = new ArrayList<>();

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Integer> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Integer> platforms) {
        this.platforms = platforms;
    }
}
