package done.homework.com.homeworkdone.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import done.homework.com.homeworkdone.R;
import done.homework.com.homeworkdone.adapter.HomeworkConversationsRecyclerAdapter;


public class HomeworkConversationsActivity extends AppCompatActivity {

    private RecyclerView conversationsRecyclerView;
    private LinearLayoutManager layoutManager;
    private HomeworkConversationsRecyclerAdapter homeworkConversationsRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        conversationsRecyclerView = findViewById(R.id.conversations_recycler_view);
        conversationsRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        conversationsRecyclerView.setLayoutManager(layoutManager);

        String [] data = new String[] {"Geo", "Bio", "Art"};
        homeworkConversationsRecyclerAdapter = new HomeworkConversationsRecyclerAdapter(data);
        conversationsRecyclerView.setAdapter(homeworkConversationsRecyclerAdapter);

    }
}
