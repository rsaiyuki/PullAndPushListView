package com.android.studio.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;


public class MainActivity extends ActionBarActivity implements PullAndPushListView.OnDropDownListener{

    private PullAndPushListView listview;
    String[] ss = new String[] {
            "a", "b", "c", "d", "e", "b", "c"};
    ArrayAdapter a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (PullAndPushListView) findViewById(R.id.mylist);
        a = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, ss);
        listview.setAdapter(a);
        listview.setOnDropDownListener(this);
        listview.onComplete(0);
//        listview.onComplete(20);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    handler1.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onLoadMore() {
        ss = new String[] {
                "a", "b", "c", "d", "e", "b", "c", "d", "e", "b", "c", "d", "e"};
//        a.addAll(ss);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    handler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            a.notifyDataSetChanged();
            listview.onComplete(0);

        }
    };
    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            a.notifyDataSetChanged();
            listview.onComplete(21);

        }
    };
}
