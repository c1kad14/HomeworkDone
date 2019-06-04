package done.homework.com.homeworkdone.messages;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

public class MessageWatcher implements TextWatcher {
    private Button sendMessageButton;

    public MessageWatcher(Button sendMessageButton) {
        this.sendMessageButton = sendMessageButton;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().trim().length() > 0) {
            sendMessageButton.setEnabled(true);
        } else {
            sendMessageButton.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
