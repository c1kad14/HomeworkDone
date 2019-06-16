package done.homework.com.homeworkdone.messages;

import android.support.annotation.NonNull;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import done.homework.com.homeworkdone.models.Homework;

public class HomeworkMessageSnapshotParser implements SnapshotParser<Homework> {

    @NonNull
    @Override
    public Homework parseSnapshot(@NonNull DataSnapshot snapshot) {
        Homework homework = snapshot.getValue(Homework.class);
        if (homework != null) {
            homework.setId(snapshot.getKey());
        }
        return homework;
    }
}
