package done.homework.com.homeworkdone.messages;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

public class HomeworkMessageWatcher implements TextWatcher {
    private Button sendMessageButton;
    private Button setDateButton;

    public HomeworkMessageWatcher(Button sendMessageButton, Button setDateButton) {
        this.sendMessageButton = sendMessageButton;
        this.setDateButton = setDateButton;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().trim().length() > 0 && !setDateButton.getText().equals("Date")) {
            sendMessageButton.setEnabled(true);
        } else {
            sendMessageButton.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
