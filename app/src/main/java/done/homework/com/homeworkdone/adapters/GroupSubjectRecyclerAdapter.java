package done.homework.com.homeworkdone.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import done.homework.com.homeworkdone.R;
import done.homework.com.homeworkdone.activities.HomeworkActivity;

public class GroupSubjectRecyclerAdapter extends RecyclerView.Adapter<GroupSubjectRecyclerAdapter.ConversationsViewHolder> {

    private final boolean isTeacher;
    private String[] dataset;

    public static class ConversationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final boolean isTeacher;
        public TextView textView;

        public ConversationsViewHolder(TextView v, boolean isTeacher) {
            super(v);
            this.isTeacher = isTeacher;
            v.setOnClickListener(this);
            textView = v;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), HomeworkActivity.class);
            Bundle b = new Bundle();
            b.putString("group_subject", textView.getText().toString());
            b.putBoolean("isTeacher", isTeacher);
            intent.putExtras(b); //Put your id to your next Intent
            view.getContext().startActivity(intent);

        }
    }

    public GroupSubjectRecyclerAdapter(String[] dataset, boolean isTeacher) {
        this.dataset = dataset;
        this.isTeacher = isTeacher;
    }

    @NonNull
    @Override
    public ConversationsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextView v = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.conversation_item, viewGroup, false);

        ConversationsViewHolder viewHolder = new ConversationsViewHolder(v, isTeacher);
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
