package science;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс сообщения представляющий собой список всех его токенов и тэгов
 */
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

    /**
     * Считает все тэги сообщения
     */
    private void countMessageTags() {
        this.tokens.stream()
                .map(Token::getTag)
                .forEach(tokenTag -> messageTags.addRate(tokenTag));
    }


    public MessageTags getMessageTags() {
        return messageTags;
    }

    public List<Tag> getListMessageTags(int count) {
        return messageTags.getTopTags(count);
    }
}
