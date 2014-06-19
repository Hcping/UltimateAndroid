package com.fss.common.demo;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.fss.Common.nineoldandroids.animation.Animator;
import com.fss.Common.uiModule.enhanceListView.EnhancedListView;
import com.fss.Common.uiModule.shimmer.Shimmer;
import com.fss.Common.uiModule.shimmer.ShimmerTextView;
import com.fss.Common.uiModule.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cym on 14-5-4.
 */
public class LandingFragment extends Fragment {
    View mainView;
    @InjectView(R.id.landingEnhanceListView)
    EnhancedListView landingEnhanceListView;
    List<HashMap<String, String>> enhanceList = new ArrayList<>();
    EnhancedListAdapter enhancedListAdapter;
    @InjectView(R.id.landingMallViewpager)
    ViewPager landingMallViewpager;
    CirclePageIndicator landingMallViewPagerIndicator;
    private List<View> viewpagerList = new ArrayList<View>();
    @InjectView(R.id.landingShimmerTextView)
    ShimmerTextView landingShimmerTextView;
    Shimmer shimmer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.landing_fragment, container, false);
        ButterKnife.inject(this, mainView);
        initEnhanceList();
        initViewPager();
        initShimmerTextView();
        return mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private void initEnhanceList() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "name1");
        map.put("detail", "detail1");
        map.put("subname", "sub1");
        enhanceList.add(map);
        map = new HashMap<>();
        map.put("name", "name2");
        map.put("detail", "detail2");
        map.put("subname", "sub2");
        enhanceList.add(map);
        map = new HashMap<>();
        map.put("name", "name3");
        map.put("detail", "detail3");
        map.put("subname", "sub3");
        enhanceList.add(map);
        map = new HashMap<>();
        map.put("name", "name4");
        map.put("detail", "detail4");
        map.put("subname", "sub4");
        enhanceList.add(map);
        enhancedListAdapter = new EnhancedListAdapter(enhanceList);
        landingEnhanceListView.setAdapter(enhancedListAdapter);
        landingEnhanceListView.setDismissCallback(new EnhancedListView.OnDismissCallback() {
            @Override
            public EnhancedListView.Undoable onDismiss(EnhancedListView listView, int position) {
                enhancedListAdapter.remove(position);
                return null;
            }
        });
        landingEnhanceListView.setSwipingLayout(R.id.swiping_layout);
        landingEnhanceListView.setUndoStyle(null);
        landingEnhanceListView.enableSwipeToDismiss();
        landingEnhanceListView.setSwipeDirection(EnhancedListView.SwipeDirection.START);
    }


    private void initViewPager() {
        addLandingViewPager();
        landingMallViewpager.setAdapter(new CustomViewPagerAdapter(viewpagerList));
        landingMallViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                //setCurrentDot(i);

            }
        });
        landingMallViewPagerIndicator = (CirclePageIndicator) mainView.findViewById(R.id.landingMallViewPagerIndicator);
        landingMallViewPagerIndicator.setViewPager(landingMallViewpager);
//        final float density = getResources().getDisplayMetrics().density;
//        landingMallViewPagerIndicator.setBackgroundColor(0xffffff);
//        landingMallViewPagerIndicator.setRadius(5 * density);
//        landingMallViewPagerIndicator.setPageColor(getResources().getColor(R.color.black));
//        landingMallViewPagerIndicator.setFillColor(getResources().getColor(R.color.white));
//        landingMallViewPagerIndicator.setStrokeColor(getResources().getColor(R.color.black));
//        landingMallViewPagerIndicator.setStrokeWidth(1 * 1.0f);
    }

    private void initShimmerTextView() {
        shimmer = new Shimmer();
        shimmer.setRepeatCount(10)
                .setDuration(1500)
                .setStartDelay(300)
                .setDirection(Shimmer.ANIMATION_DIRECTION_LTR)
                .setAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
        shimmer.start(landingShimmerTextView);
    }

    private class EnhancedListAdapter extends BaseAdapter {
        //private List<String> mItems = new ArrayList<String>();
        private List<HashMap<String, String>> mLists = new ArrayList<HashMap<String, String>>();

        //    private EnhancedListAdapter(List<String> mItems) {
//        this.mItems = mItems;
//    }
        private EnhancedListAdapter(List<HashMap<String, String>> mLists) {
            this.mLists = mLists;
        }

        void resetItems() {
            mLists.clear();

        }

        public void remove(int position) {
            mLists.remove(position);
            notifyDataSetChanged();
        }


        /**
         * How many items are in the data set represented by this Adapter.
         *
         * @return Count of items.
         */
        @Override
        public int getCount() {
            return mLists.size();
        }

        /**
         * Get the data item associated with the specified position in the data set.
         *
         * @param position Position of the item whose data we want within the adapter's
         *                 data set.
         * @return The data at the specified position.
         */
        @Override
        public Object getItem(int position) {
            return mLists.get(position);
        }

        /**
         * Get the row id associated with the specified position in the list.
         *
         * @param position The position of the item within the adapter's data set whose row id we want.
         * @return The id of the item at the specified position.
         */
        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_enhance, parent, false);
                // Clicking the delete icon, will read the position of the item stored in
                // the tag and delete it from the list. So we don't need to generate a new
                // onClickListener every time the content of this view changes.
                final View origView = convertView;
                convertView.findViewById(R.id.action_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // listViewHistory.delete(((ViewHolder) origView.getTag()).position);
                    }
                });

                holder = new ViewHolder();
                assert convertView != null;
                holder.mTextView = (TextView) convertView.findViewById(R.id.reacolhistextview);
                holder.mTextViewDir = (TextView) convertView.findViewById(R.id.reacolhistextviewDir);
                holder.mTextViewDetail = (TextView) convertView.findViewById(R.id.reacolhistextviewDetail);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.position = position;
            holder.mTextView.setText(mLists.get(position).get("name"));
            holder.mTextViewDir.setText(mLists.get(position).get("detail"));
            holder.mTextViewDetail.setText(mLists.get(position).get("subname"));
            //    map.put("route_id", busInfoFav.getRouteId());
//
            return convertView;
        }

        private class ViewHolder {
            TextView mTextView;
            TextView mTextViewDir;
            TextView mTextViewDetail;
            int position;
        }

    }

    private void addLandingViewPager() {
        if (viewpagerList == null)
            viewpagerList = new ArrayList<View>();
        else viewpagerList.clear();
        LayoutInflater li = LayoutInflater.from(getActivity());
        for (int i = 0; i < 3; i++) {
            View viewPagerItem = getActivity().getLayoutInflater().inflate(R.layout.landing_viewpager_item,
                    null, false);
            ViewPagerViewHolder viewHolder = new ViewPagerViewHolder(viewPagerItem);
            viewHolder.landViewPagerImage.setImageResource(R.drawable.test);
            //  viewHolder.landViewPagerInfo1.setText("hh"+i);
            // viewPagerItem.setTag(viewHolder);
            viewpagerList.add(viewPagerItem);
        }
    }

    static class ViewPagerViewHolder {
        @InjectView(R.id.landViewPagerImage)
        ImageView landViewPagerImage;
        @InjectView(R.id.landViewPagerImage1)
        ImageView landViewPagerImage1;
        @InjectView(R.id.landViewPagerName)
        TextView landViewPagerName;
        @InjectView(R.id.landViewPagerName1)
        TextView landViewPagerName1;
        @InjectView(R.id.landViewPagerInfo)
        TextView landViewPagerInfo;
        @InjectView(R.id.landViewPagerInfo1)
        TextView landViewPagerInfo1;

        public ViewPagerViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

    }

    class CustomViewPagerAdapter extends PagerAdapter {
        List<View> viewpagerList;

        public CustomViewPagerAdapter(List viewpagerList) {
            this.viewpagerList = viewpagerList;
        }

        @Override
        public int getCount() {
            return viewpagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == (o);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            if (viewpagerList != null && viewpagerList.size() > position && viewpagerList.get(position) != null)
                container.removeView(viewpagerList.get(position));

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewpagerList.get(position), 0);
            //   return super.instantiateItem(container, position);
            return viewpagerList.get(position);
        }

        @Override
        public void startUpdate(ViewGroup container) {
            super.startUpdate(container);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            super.restoreState(state, loader);
        }

        @Override
        public Parcelable saveState() {
            return super.saveState();
        }

    }
}