package done.homework.com.homeworkdone.messages;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import done.homework.com.homeworkdone.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    private TextView messageTextView;
    private TextView messengerTextView;
    private CircleImageView messengerImageView;

    public MessageViewHolder(View v) {
        super(v);
        messageTextView = itemView.findViewById(R.id.messageTextView);
        messengerTextView = itemView.findViewById(R.id.messengerTextView);
        messengerImageView = itemView.findViewById(R.id.messengerImageView);
    }

    public TextView getMessageTextView() {
        return messageTextView;
    }

    public void setMessageTextView(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    public TextView getMessengerTextView() {
        return messengerTextView;
    }

    public void setMessengerTextView(TextView messengerTextView) {
        this.messengerTextView = messengerTextView;
    }

    public CircleImageView getMessengerImageView() {
        return messengerImageView;
    }

    public void setMessengerImageView(CircleImageView messengerImageView) {
        this.messengerImageView = messengerImageView;
    }
}
