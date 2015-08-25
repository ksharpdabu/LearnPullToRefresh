package info.dabu.learnpulltorefresh;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


   private PullToRefreshListView lv;
    private ArrayAdapter<String> mAdapter;
    private List<String>  list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (PullToRefreshListView) findViewById(R.id.mylv);


        list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");



        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);

        lv.setAdapter(mAdapter);


//        设置下拉刷新事件

        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new AsyncTask<Void,Void,Void>(){


                    @Override
                    protected Void doInBackground(Void... params) {

//                        用延时来模拟网络加载
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {

//                       下拉后在ListView中增加一条数据
                       mAdapter.add("齐天大圣");

//                        刷新完成
                        lv.onRefreshComplete();
                    }




                }.execute();





            }
        });


        
    }





}
