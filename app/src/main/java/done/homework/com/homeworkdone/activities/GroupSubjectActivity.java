package done.homework.com.homeworkdone.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import done.homework.com.homeworkdone.Models.Group;
import done.homework.com.homeworkdone.Models.Student;
import done.homework.com.homeworkdone.Models.Subject;
import done.homework.com.homeworkdone.R;
import done.homework.com.homeworkdone.adapter.GroupSubjectRecyclerAdapter;

import java.util.*;


public class GroupSubjectActivity extends AppCompatActivity {
    private static final String TAG = "GROUP_SUBJECT";
    private static final String STUDENTS = "students";
    private static final String SUBJECTS = "subjects";

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String currentUser;
    private boolean isTeacher;
    private List<Group> groups;
    private RecyclerView conversationsRecyclerView;
    private LinearLayoutManager layoutManager;
    private GroupSubjectRecyclerAdapter groupSubjectRecyclerAdapter;
    private List<QueryDocumentSnapshot> documents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        conversationsRecyclerView = findViewById(R.id.conversations_recycler_view);
        conversationsRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        conversationsRecyclerView.setLayoutManager(layoutManager);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        //check if user is signed in
        if (firebaseUser == null) {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            currentUser = firebaseUser.getEmail();

            FirebaseFirestore.getInstance().collection("homework").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        documents = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            documents.add(document);
                            Map<String, Object> data = document.getData();
                            processData(data);

                            groupSubjectRecyclerAdapter = new GroupSubjectRecyclerAdapter(getDataToDisplay(), isTeacher);
                            conversationsRecyclerView.setAdapter(groupSubjectRecyclerAdapter);
                            Log.d(TAG, document.getId() + " => " + document.getData());
                        }
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }


                }
            });
        }


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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void processData(Map<String, Object> data) {
        groups = new ArrayList<>();
        for (String groupKey : data.keySet()) {
            Group group = new Group();
            group.setName(groupKey);
            groups.add(group);
        }

        for (Group group : groups) {
            Map<String, Object> groupData = (HashMap) (data.get(group.getName()));

            List<Student> students = new ArrayList<>();
            List<Subject> subjects = new ArrayList<>();

            Map<String, String> studentsData = (HashMap) (groupData.get(STUDENTS));
            Map<String, String> subjectsData = (HashMap) (groupData.get(SUBJECTS));

            for (String studentName : studentsData.keySet()) {
                Student student = new Student();
                student.setName(studentName);
                student.setParentEmail(studentsData.get(studentName));
                students.add(student);
            }

            for (String subjectName : subjectsData.keySet()) {
                Subject subject = new Subject();
                subject.setSubject(subjectName);
                subject.setTeacherEmail(subjectsData.get(subjectName));

                if (subject.getTeacherEmail().equals(currentUser)) {
                    isTeacher = true;
                }

                subjects.add(subject);
            }

            group.setStudents(students);
            group.setSubjects(subjects);
        }
    }

    private String[] getDataToDisplay() {
        List<String> dataToDisplay = new ArrayList<>();

        for (Group group : groups) {

            if (isTeacher) {
                for (Subject subject : group.getSubjects()) {
                    if (subject.getTeacherEmail().equals(currentUser)) {
                        dataToDisplay.add(String.format("%s: %s", group.getName(), subject.getSubject()));
                    }
                }
            } else {
                for (Student student : group.getStudents()) {
                    if (student.getParentEmail().equals(currentUser)) {
                        for (Subject subject : group.getSubjects()) {
                            dataToDisplay.add(String.format("%s: %s", group.getName(), subject.getSubject()));
                        }
                    }
                }
            }

        }

        return dataToDisplay.toArray(new String[dataToDisplay.size()]);
    }
}
