package science;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Message {

    private List<Token> tokens;
    private MessageTags messageTags;

    public Message(String messageText) {
        this.tokens = tokensListFromString(messageText);
        messageTags = new MessageTags();
        this.countMessageTags();
    }

    private List<Token> tokensListFromString(String text) {
        return Arrays.stream(text.split(" "))
                .map(Token::new)
                .collect(Collectors.toList());
    }

    private void countMessageTags() {
        this.tokens.stream()
                .map(Token::getTag)
                .forEach(tokenTag -> messageTags.addRate(tokenTag));
    }


    public String getMessageTags() {
        return messageTags.toString();
    }
}
