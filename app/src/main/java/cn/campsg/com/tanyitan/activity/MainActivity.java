package cn.campsg.com.tanyitan.activity;

import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.os.AsyncTaskCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;
import java.util.List;

import cn.campsg.com.tanyitan.R;
import cn.campsg.com.tanyitan.adapter.CardAdapter;
import cn.campsg.com.tanyitan.entity.Card;
import cn.campsg.com.tanyitan.entity.CardViewHolder;
import cn.campsg.com.tanyitan.view.SwipeFlingAdapterView;

public class MainActivity extends SlidingFragmentActivity implements SwipeFlingAdapterView.onFlingListener,
        SwipeFlingAdapterView.OnItemClickListener{

    private int cardWidth;
    private int cardHeight;

    private SwipeFlingAdapterView swipeView;
    private CardAdapter adapter;
    private ImageView img_home_left,img_home_right;
    private FloatingActionButton btnNo,btnYes,btnDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化滑动菜单
        initSlidingMenu();
        // 初始化卡片
        initView();
        // 加载卡片数据
        loadData();

    }

    private void initView() {

        btnNo = (FloatingActionButton) findViewById(R.id.btnNo);
        btnYes = (FloatingActionButton) findViewById(R.id.btnYes);
        btnDetail = (FloatingActionButton) findViewById(R.id.btnDetail);
        img_home_right = (ImageView) findViewById(R.id.home_head_right);
        img_home_left = (ImageView) findViewById(R.id.home_head_left);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        cardWidth = (int) (dm.widthPixels - (2 * 8 * density));
        cardHeight = (int) (dm.heightPixels - (250 * density));

        swipeView = (SwipeFlingAdapterView) findViewById(R.id.swipe_view);
        swipeView.setFlingListener(this);
        swipeView.setOnItemClickListener(this);

        adapter = new CardAdapter(cardWidth, cardHeight);
        swipeView.setAdapter(adapter);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeView.getTopCardListener().selectLeft();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeView.getTopCardListener().selectRight();
            }
        });
        img_home_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSlidingMenu().showSecondaryMenu();
            }
        });
        img_home_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSlidingMenu().showMenu();
            }
        });
    }

    /**
     * 初始化滑动菜单
     */
    private void initSlidingMenu(){
        // 设置滑动菜单的属性值
        getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        getSlidingMenu().setShadowWidthRes(R.dimen.shadow_width);
        getSlidingMenu().setShadowDrawable(R.drawable.shadow);
        getSlidingMenu().setBehindOffsetRes(R.dimen.slidingmenu_offset);
        getSlidingMenu().setFadeDegree(0.35f);

        // 设置主界面的视图
        setContentView(R.layout.activity_main);

        // 设置滑动菜单的左视图界面
        setBehindContentView(R.layout.menu_frame);
//        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new SampleListFragment()).commit();

        // 设置滑动菜单的右视图界面
        getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
        getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
//        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame_two, new SampleListFragment()).commit();
    }

    @Override
    public void onItemClicked(MotionEvent event, View v, Object dataObject) {
        if (v.getTag() instanceof CardViewHolder) {
            int x = (int) event.getRawX();
            int y = (int) event.getRawY();
            CardViewHolder viewHolder = (CardViewHolder) v.getTag();
            View viewPic = viewHolder.portraitView;
            Rect outRect = new Rect();
            viewPic.getGlobalVisibleRect(outRect);
            if (outRect.contains(x, y)) {
                Toast.makeText(this, "click 大图",Toast.LENGTH_SHORT).show();
            } else {
                outRect.setEmpty();
//                viewPic = viewHolder.collectView;
                viewPic.getGlobalVisibleRect(outRect);
                if (outRect.contains(x, y)) {
//                    Toast.makeText(this, "click文本",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void removeFirstObjectInAdapter() {
        adapter.remove(0);
    }

    @Override
    public void onLeftCardExit(Object dataObject) {
        Toast.makeText(this, "swipe left card",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightCardExit(Object dataObject) {
        Toast.makeText(this, "swipe right card",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        if (itemsInAdapter == 1) {
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
                ArrayList<Card> cards = new ArrayList<>(10);
                Card card;
                for (int i = 0; i < 10; i++) {
                    card = new Card();
                    card.mainpic = getResources().getDrawable(R.drawable.eagle, null);
                    cards.add(card);
                }
                return cards;
            }

            @Override
            protected void onPostExecute(List<Card> cards) {
                super.onPostExecute(cards);
                adapter.addAll(cards);
            }
        });
    }

}
