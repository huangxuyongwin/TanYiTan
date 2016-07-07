package cn.campsg.com.tanyitan.listener;

import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;

import java.util.ArrayList;
import java.util.List;

import cn.campsg.com.tanyitan.adapter.CardAdapter;
import cn.campsg.com.tanyitan.entity.Card;

/**
 * Created by admin on 2016/7/4.
 */
public class OpCardListener implements IOpCardListener {

    private CardAdapter adapter = null;

    public OpCardListener(CardAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    public void removeFirstObjectInAdapter() {
        adapter.remove(0);
    }

    @Override
    public void onLeftCardExit(Object dataObject) {
//        Toast.makeText(this, "swipe left card", Toast.LENGTH_SHORT),show();
    }

    @Override
    public void onRightCardExit(Object dataObject) {
//        AppToast.show(this, "swipe right card");
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        if (itemsInAdapter == 3) {
            loadData();
        }
    }

    @Override
    public void onScroll(float progress, float scrollXProgress) {
    }

    private void loadData() {
        AsyncTaskCompat.executeParallel(new AsyncTask<Void, Void, List<Card>>() {
            @Override
            protected List<Card> doInBackground(Void... params) {
                ArrayList<Card> list = new ArrayList<>(10);
                Card card;
                for (int i = 0; i < 10; i++) {
                    card = new Card();
                    list.add(card);
                }
                return list;
            }

            @Override
            protected void onPostExecute(List<Card> list) {
                super.onPostExecute(list);
                adapter.addAll(list);
            }
        });
    }
}
