package com.test.notes;

import android.content.ClipData;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeToDelete extends ItemTouchHelper.SimpleCallback {
    private ListAdapter mAdapter;
    public SwipeToDelete(ListAdapter adapter)
    {
        super(0,ItemTouchHelper.RIGHT);
        Log.d("GESTURE","SWIPPED TO DELETE");
        mAdapter=adapter;
    }
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Log.d("GESTURE","SWIPE RIGHT TOUCH HELPER");
    }
    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder)
    {
        return 0.8f;
    }
}
