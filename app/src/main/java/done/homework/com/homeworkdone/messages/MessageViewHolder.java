package done.homework.com.homeworkdone.messages;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import done.homework.com.homeworkdone.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    private TextView messageTextView;
    private ImageView messageImageView;
    private TextView messengerTextView;
    private CircleImageView messengerImageView;

    public MessageViewHolder(View v) {
        super(v);
        messageTextView = itemView.findViewById(R.id.messageTextView);
        messageImageView = itemView.findViewById(R.id.messageImageView);
        messengerTextView = itemView.findViewById(R.id.messengerTextView);
        messengerImageView = itemView.findViewById(R.id.messengerImageView);
    }

    public TextView getMessageTextView() {
        return messageTextView;
    }

    public void setMessageTextView(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    public ImageView getMessageImageView() {
        return messageImageView;
    }

    public void setMessageImageView(ImageView messageImageView) {
        this.messageImageView = messageImageView;
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
