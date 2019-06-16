package done.homework.com.homeworkdone.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import done.homework.com.homeworkdone.dialogs.DateDialog;
import done.homework.com.homeworkdone.models.Homework;
import done.homework.com.homeworkdone.R;
import done.homework.com.homeworkdone.data.HomeworkAdapterDataObserver;
import done.homework.com.homeworkdone.adapters.HomeworkRecyclerAdapter;
import done.homework.com.homeworkdone.messages.HomeworkMessageSnapshotParser;
import done.homework.com.homeworkdone.messages.HomeworkMessageViewHolder;
import done.homework.com.homeworkdone.messages.HomeworkMessageWatcher;

import java.util.Calendar;

public class HomeworkActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "HOMEWORK";
    private static final String EMPTY = "";
    private String subject_group = "messages";
    private boolean isTeacher = false;

    private Button sendMessageButton;
    public Button selectDateButton;
    private RecyclerView messageRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private EditText messageEditText;
    private String currentUser;

    // Firebase instance variables
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference firebaseDatabaseReference;
    private FirebaseRecyclerAdapter<Homework, HomeworkMessageViewHolder>
            firebaseHomeworkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_homeworks);

        messageRecyclerView = findViewById(R.id.messageRecyclerView);
        messageEditText = findViewById(R.id.messageEditText);
        sendMessageButton = findViewById(R.id.sendButton);
        selectDateButton = findViewById(R.id.selectDateTimeButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        currentUser = firebaseUser.getEmail();

        firebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            subject_group = bundle.getString("group_subject");
            isTeacher = bundle.getBoolean("isTeacher");
        }

        if (!isTeacher) {
            findViewById(R.id.linearLayout).setVisibility(View.GONE);
        }

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        FirebaseRecyclerOptions<Homework> options =
                new FirebaseRecyclerOptions.Builder<Homework>()
                        .setQuery(firebaseDatabaseReference.child(subject_group), new HomeworkMessageSnapshotParser())
                        .build();

        firebaseHomeworkAdapter = new HomeworkRecyclerAdapter(options);
        firebaseHomeworkAdapter.registerAdapterDataObserver(new HomeworkAdapterDataObserver(firebaseHomeworkAdapter, linearLayoutManager, messageRecyclerView));

        messageRecyclerView.setLayoutManager(linearLayoutManager);
        messageRecyclerView.setAdapter(firebaseHomeworkAdapter);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                String time = calendar.getTime().toString();
                Homework message = new
                        Homework(String.format("Homework for %s: %s", selectDateButton.getText(),
                        messageEditText.getText().toString()), time, currentUser);
                firebaseDatabaseReference.child(subject_group).push().setValue(message);
                messageEditText.setText(EMPTY);
                selectDateButton.setText("Date");
            }
        });

        messageEditText.addTextChangedListener(new HomeworkMessageWatcher(sendMessageButton, selectDateButton));
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in.
        // TODO: Add code to check if user is signed in.
    }

    @Override
    public void onPause() {
        firebaseHomeworkAdapter.stopListening();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        firebaseHomeworkAdapter.startListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                firebaseAuth.signOut();
                currentUser = null;
                startActivity(new Intent(this, SignInActivity.class));
                finish();
                return true;
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, GroupSubjectActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void setDate(View view) {
        DialogFragment dialog = new DateDialog();
        dialog.show(getSupportFragmentManager(), "showDate");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        selectDateButton.setText(new StringBuilder().append(dayOfMonth).append("/")
                .append(month).append("/").append(year).toString());
    }
}
