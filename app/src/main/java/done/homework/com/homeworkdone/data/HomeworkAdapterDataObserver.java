package done.homework.com.homeworkdone.data;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import done.homework.com.homeworkdone.models.Homework;
import done.homework.com.homeworkdone.messages.HomeworkMessageViewHolder;

public class HomeworkAdapterDataObserver extends RecyclerView.AdapterDataObserver {

    private final LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter<Homework, HomeworkMessageViewHolder> firebaseAdapter;
    private RecyclerView messageRecyclerView;

    public HomeworkAdapterDataObserver(FirebaseRecyclerAdapter<Homework, HomeworkMessageViewHolder> firebaseAdapter,
                                       LinearLayoutManager linearLayoutManager,
                                       RecyclerView messageRecyclerView) {
        this.firebaseAdapter = firebaseAdapter;
        this.linearLayoutManager = linearLayoutManager;
        this.messageRecyclerView = messageRecyclerView;
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        super.onItemRangeInserted(positionStart, itemCount);
        int messageCount = firebaseAdapter.getItemCount();
        int lastVisiblePosition =
                linearLayoutManager.findLastCompletelyVisibleItemPosition();

        // scroll to the bottom of the list to show the most recent homework
        if (lastVisiblePosition == -1 ||
                (positionStart >= (messageCount - 1) &&
                        lastVisiblePosition == (positionStart - 1))) {
            messageRecyclerView.scrollToPosition(positionStart);
        }
    }
}
