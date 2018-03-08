package usyd.comp5216.dkthero.models;


import java.util.Date;
import java.util.Random;

public class QuizModel {

    private String quizCategory;
    private int quizImage;
    private String quizText;
    private String quizAnswers[];
    private int trueAnswerIndex;

    public QuizModel() {
    }

    public QuizModel(String quizCategory, int quizImage, String quizText, String[] quizAnswers, int trueAnswerIndex) {
        this.quizCategory = quizCategory;
        this.quizImage = quizImage;
        this.quizText = quizText;
        this.quizAnswers = quizAnswers;
        this.trueAnswerIndex = trueAnswerIndex;
    }

    public QuizModel(String quizCategory, String quizText, String[] quizAnswers, int trueAnswerIndex) {
        this.quizCategory = quizCategory;
        this.quizText = quizText;
        this.quizAnswers = quizAnswers;
        this.trueAnswerIndex = trueAnswerIndex;
    }

    public String getQuizCategory() {
        return quizCategory;
    }

    public void setQuizCategory(String quizCategory) {
        this.quizCategory = quizCategory;
    }

    public int getQuizImage() {
        return quizImage;
    }

    public void setQuizImage(int quizImage) {
        this.quizImage = quizImage;
    }

    public String getQuizText() {
        return quizText;
    }

    public void setQuizText(String quizText) {
        this.quizText = quizText;
    }

    public String[] getQuizAnswers() {
        return quizAnswers;
    }

    public void setQuizAnswers(String[] quizAnswers) {
        this.quizAnswers = quizAnswers;
    }

    public int getTrueAnswerIndex() {
        return trueAnswerIndex;
    }

    public void setTrueAnswerIndex(int trueAnswerIndex) {
        this.trueAnswerIndex = trueAnswerIndex;
    }

    public void shuffle(){
        String trueAnswer = quizAnswers[trueAnswerIndex];
        Date dt=new Date();
        Random random=new Random(dt.getSeconds());
        int len=quizAnswers.length;
        for(int i=0; i<len; i++) {
            int pos=(int)(random.nextDouble()*(len-i+1)+i)-1;
            String temp=quizAnswers[i];
            quizAnswers[i]=quizAnswers[pos];
            quizAnswers[pos]=temp;
        }
        for (int i=0; i<quizAnswers.length; i++) {
            if (quizAnswers[i].equals(trueAnswer)) {
                trueAnswerIndex = i;
            }
        }
    }
}
