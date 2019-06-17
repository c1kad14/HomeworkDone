package done.homework.com.homeworkdone.messages;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;

public class HomeworkMessageWatcher implements TextWatcher {
    private Button sendMessageButton;
    private Button setDateButton;
    private TextView textView;

    public HomeworkMessageWatcher(Button sendMessageButton, Button setDateButton, TextView textView) {
        this.sendMessageButton = sendMessageButton;
        this.setDateButton = setDateButton;
        this.textView = textView;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!setDateButton.getText().equals("Date") && textView.getText().length() > 0) {
            sendMessageButton.setEnabled(true);
        } else {
            sendMessageButton.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
