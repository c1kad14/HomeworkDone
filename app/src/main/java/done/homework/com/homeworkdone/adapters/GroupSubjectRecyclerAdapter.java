package done.homework.com.homeworkdone.adapters;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import done.homework.com.homeworkdone.R;
import done.homework.com.homeworkdone.activities.HomeworkActivity;

public class GroupSubjectRecyclerAdapter extends RecyclerView.Adapter<GroupSubjectRecyclerAdapter.ConversationsViewHolder> {

    private final boolean isTeacher;
    private String[] dataset;

    public static class ConversationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final boolean isTeacher;
        private TextView textView;
        private CircleImageView imageView;

        public ConversationsViewHolder(LinearLayout linearLayout, boolean isTeacher) {
            super(linearLayout);
            this.isTeacher = isTeacher;
            this.textView = (TextView) linearLayout.getChildAt(1);
            this.imageView = (CircleImageView) linearLayout.getChildAt(0);
            this.textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), HomeworkActivity.class);
            Bundle b = new Bundle();
            b.putString("group_subject", textView.getText().toString());
            b.putBoolean("isTeacher", isTeacher);
            intent.putExtras(b);
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
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.conversation_item, viewGroup, false);

        return new ConversationsViewHolder(linearLayout, isTeacher);
    }

    @Override
    public void onBindViewHolder(@NonNull final ConversationsViewHolder conversationsViewHolder, int i) {
        conversationsViewHolder.textView.setText(dataset[i]);
        conversationsViewHolder.imageView.setImageBitmap(getImageBitmap(conversationsViewHolder.imageView.getContext().getApplicationContext().getResources()
                , dataset[i]));
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    private Bitmap getImageBitmap(Resources resources, String text) {
        if (text.toLowerCase().contains("art")) {
            return BitmapFactory.decodeResource(resources, R.drawable.art);
        } else if (text.toLowerCase().contains("geo")) {
            return BitmapFactory.decodeResource(resources, R.drawable.earth);
        } else if (text.toLowerCase().contains("bio")) {
            return BitmapFactory.decodeResource(resources, R.drawable.biology);
        } else if (text.toLowerCase().contains("math")) {
            return BitmapFactory.decodeResource(resources, R.drawable.mathematics);
        } else if(text.toLowerCase().contains("eng")) {
            return BitmapFactory.decodeResource(resources, R.drawable.eng);
        }
        return null;
    }

}
