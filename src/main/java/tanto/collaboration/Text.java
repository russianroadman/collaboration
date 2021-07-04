package tanto.collaboration;

import com.fasterxml.jackson.annotation.JsonView;

public class Text {

    @JsonView
    String text;

    public Text() {}

    public Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
