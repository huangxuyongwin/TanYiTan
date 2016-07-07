package cn.campsg.com.tanyitan.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collection;

import cn.campsg.com.tanyitan.R;
import cn.campsg.com.tanyitan.entity.Card;
import cn.campsg.com.tanyitan.entity.CardViewHolder;

/**
 * Created by admin on 2016/7/4.
 */
public class CardAdapter extends BaseAdapter{

        ArrayList<Card> cards;
        int cardWidth;
        int cardHeight;

        public CardAdapter(int cardWidth, int cardHeight) {
            this.cardHeight = cardHeight;
            this.cardWidth = cardWidth;
            cards = new ArrayList<>();
        }

        public void addAll(Collection<Card> collection) {
            if (isEmpty()) {
                cards.addAll(collection);
                notifyDataSetChanged();
            } else {
                cards.addAll(collection);
            }
        }

        public void clear() {
            cards.clear();
            notifyDataSetChanged();
        }

        public boolean isEmpty() {
            return cards.isEmpty();
        }

        public void remove(int index) {
            if (index > -1 && index < cards.size()) {
                cards.remove(index);
                notifyDataSetChanged();
            }
        }


        @Override
        public int getCount() {
            return cards.size();
        }

        @Override
        public Card getItem(int position) {
            if(cards==null ||cards.size()==0) return null;
            return cards.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // TODO: getView
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Card card = getItem(position);
            if (convertView == null)
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_new_item, parent, false);
            CardViewHolder holder = new CardViewHolder();
            convertView.setTag(holder);
            convertView.getLayoutParams().width = cardWidth;
            holder.portraitView = (SimpleDraweeView) convertView.findViewById(R.id.portrait);
            holder.portraitView.getLayoutParams().height = cardHeight;
//            holder.nameView = (TextView) convertView.findViewById(R.id.name);
//
            holder.portraitView.setBackground(card.mainpic);

//            holder.nameView.setText(String.format("%s", talent.nickname));

//            final CharSequence no = "暂无";

            return convertView;
        }

}
