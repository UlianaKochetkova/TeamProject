package science;

import com.example.TeamProject.Application;

public class Token {

    private String value;
    private TokenTag tag;

    public Token(String value) {
        this.value = value;
        this.tag = Application.nlpManager.keyWordsCollector.getToken(value);
    }

    public TokenTag getTag() {
        return tag;
    }
}
