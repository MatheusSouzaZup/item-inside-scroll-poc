package com.lerning.zup.iteminsidescrollpoc;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZUP on 05/03/2018.
 */

public class TestFragment extends Fragment {

    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.imageView)
    ImageView firstItem;
    @BindView(R.id.imageView2)
    ImageView secondItem;
    @BindView(R.id.imageView3)
    ImageView thirdItem;
    @BindView(R.id.imageView4)
    ImageView forthItem;
    @BindView(R.id.button)
    Button mButton;

    private List<View> mList;
    public static TestFragment newInstance() {

        Bundle args = new Bundle();

        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        scrollListner();
    }

    private void init() {
        mList = new ArrayList<>();
        mList.add(firstItem);
        mList.add(secondItem);
        mList.add(thirdItem);
        mList.add(forthItem);
    }

    private void scrollListner() {
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                for(View v : mList) {
                    if(isViewVisible(v)) {
                        if(v.getId() == forthItem.getId()) {
                            mButton.setVisibility(View.GONE);
                        }
                        else {
                            mButton.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });
    }

    private boolean isViewVisible(View view) {
        Rect scrollBounds = new Rect();
        mScrollView.getDrawingRect(scrollBounds);

        float top = view.getY();
        float bottom = top + view.getHeight();

        if (scrollBounds.top <= top && scrollBounds.bottom >= bottom) {
            return true;
        } else {
            return false;
        }
    }
}
