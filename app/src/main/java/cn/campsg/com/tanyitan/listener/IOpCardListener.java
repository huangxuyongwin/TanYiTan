package cn.campsg.com.tanyitan.listener;

/**
 * Created by admin on 2016/7/4.
 */
public interface IOpCardListener {
    void removeFirstObjectInAdapter();
    void onLeftCardExit(Object dataObject);
    void onRightCardExit(Object dataObject);
    void onAdapterAboutToEmpty(int itemsInAdapter);
    void onScroll(float progress, float scrollXProgress);
}