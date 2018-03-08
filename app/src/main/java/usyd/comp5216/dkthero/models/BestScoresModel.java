package usyd.comp5216.dkthero.models;


public class BestScoresModel {

    private int id;
    private String category;
    private String score;

    public BestScoresModel() {
    }

    public BestScoresModel(int id, String category, String score) {
        this.id = id;
        this.category = category;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
