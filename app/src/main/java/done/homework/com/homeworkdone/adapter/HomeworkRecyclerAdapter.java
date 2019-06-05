package done.homework.com.homeworkdone.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import done.homework.com.homeworkdone.R;
import done.homework.com.homeworkdone.Models.Homework;
import done.homework.com.homeworkdone.messages.MessageViewHolder;

public class HomeworkRecyclerAdapter extends FirebaseRecyclerAdapter<Homework, MessageViewHolder> {

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
    protected void onBindViewHolder(@NonNull final MessageViewHolder holder, int position, @NonNull Homework message) {
        if (message.getText() != null) {
            holder.getMessageTextView().setText(message.getText());
            holder.getMessageTextView().setVisibility(TextView.VISIBLE);
            holder.getMessageImageView().setVisibility(ImageView.GONE);
        } else if (message.getImageUrl() != null) {
            String imageUrl = message.getImageUrl();
            if (imageUrl.startsWith("gs://")) {
                StorageReference storageReference = FirebaseStorage.getInstance()
                        .getReferenceFromUrl(imageUrl);
                storageReference.getDownloadUrl().addOnCompleteListener(
                        new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    String downloadUrl = task.getResult().toString();
                                    Glide.with(holder.getMessageImageView().getContext())
                                            .load(downloadUrl)
                                            .into(holder.getMessageImageView());
                                } else {
                                    Log.w(TAG, "Getting download url was not successful.",
                                            task.getException());
                                }
                            }
                        });
            } else {
                Glide.with(holder.getMessageImageView().getContext())
                        .load(message.getImageUrl())
                        .into(holder.getMessageImageView());
            }
            holder.getMessageImageView().setVisibility(ImageView.VISIBLE);
            holder.getMessageTextView().setVisibility(TextView.GONE);
        }

        holder.getMessengerTextView().setText(message.getText());

    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new MessageViewHolder(inflater.inflate(R.layout.item_message, viewGroup, false));

    }
}
