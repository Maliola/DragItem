package com.mh.test.dragitem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import com.mh.test.dragitem.adapter.RecyclerListAdapter;
import com.mh.test.dragitem.adapter.TestListAdapter;
import com.mh.test.dragitem.helper.OnStartDragListener;
import com.mh.test.dragitem.helper.SimpleItemTouchHelperCallback;

/**
 * Created by 17-06-027 on 2017/8/9.
 */

public class TestActivity extends AppCompatActivity implements OnStartDragListener {
    Button button;
    RecyclerView recyclerView;
    int state=0;
    private ItemTouchHelper mItemTouchHelper;
    SimpleItemTouchHelperCallback callback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        button= (Button) findViewById(R.id.button);
        recyclerView= (RecyclerView) findViewById(R.id.recycler);
        TestListAdapter adapter = new TestListAdapter(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }
    public void Click1(View view){
        if(state==0){
            button.setText("可移动");
            callback.setDragEnable(true);
            state=1;
        }else{
            button.setText("不可移动");
            callback.setDragEnable(false);
            state=0;
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
