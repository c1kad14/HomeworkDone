package done.homework.com.homeworkdone.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import done.homework.com.homeworkdone.R;

public class HomeworkConversationsRecyclerAdapter extends RecyclerView.Adapter<HomeworkConversationsRecyclerAdapter.ConversationsViewHolder> {

    private String[] dataset;

    public static class ConversationsViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ConversationsViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    public HomeworkConversationsRecyclerAdapter(String[] dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ConversationsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextView v = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.conversation_item, viewGroup, false);

        ConversationsViewHolder viewHolder = new ConversationsViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ConversationsViewHolder conversationsViewHolder, int i) {
        conversationsViewHolder.textView.setText(dataset[i]);
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }
}
