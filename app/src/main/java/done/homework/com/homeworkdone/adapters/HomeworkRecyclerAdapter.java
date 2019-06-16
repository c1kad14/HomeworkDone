package done.homework.com.homeworkdone.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import done.homework.com.homeworkdone.models.Homework;
import done.homework.com.homeworkdone.R;
import done.homework.com.homeworkdone.messages.HomeworkMessageViewHolder;

public class HomeworkRecyclerAdapter extends FirebaseRecyclerAdapter<Homework, HomeworkMessageViewHolder> {

    private static final String TAG = "HOMEWORK_RECYCLER_ADAPTER";

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public HomeworkRecyclerAdapter(@NonNull FirebaseRecyclerOptions<Homework> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final HomeworkMessageViewHolder holder, int position, @NonNull Homework message) {
        if (message.getText() != null) {
            holder.getMessageTextView().setText(message.getText());
            holder.getMessageTextView().setVisibility(TextView.VISIBLE);
            holder.getMessengerTextView().setText(String.format("by %s, on %s", message.getSentBy(), message.getDate()));
        }
    }

    @NonNull
    @Override
    public HomeworkMessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new HomeworkMessageViewHolder(inflater.inflate(R.layout.item_message, viewGroup, false));

    }
}
