package done.homework.com.homeworkdone.messages;

import android.support.annotation.NonNull;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import done.homework.com.homeworkdone.Models.Homework;

public class MessageSnapshotParser implements SnapshotParser<Homework> {

    @NonNull
    @Override
    public Homework parseSnapshot(@NonNull DataSnapshot snapshot) {
        Homework message = snapshot.getValue(Homework.class);
        if (message != null) {
            message.setId(snapshot.getKey());
        }
        return message;
    }
}
