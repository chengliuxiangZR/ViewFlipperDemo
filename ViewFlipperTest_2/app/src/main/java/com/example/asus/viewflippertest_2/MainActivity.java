package com.example.asus.viewflippertest_2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper vflp_help;
    private Context mContext;
    private int[] resId={R.drawable.p5,R.drawable.p6,R.drawable.p7,R.drawable.p8};
    private final static int MIN_MOVE=200;
    private MyGestureListener myGesturaListener;
    private GestureDetector mDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=MainActivity.this;
        myGesturaListener=new MyGestureListener();
        mDetector=new GestureDetector(this,myGesturaListener);
        vflp_help=(ViewFlipper) findViewById(R.id.vflp_help);
        for(int i=0;i<resId.length;i++){
            vflp_help.addView(getImageView(resId[i]));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX()-e2.getX()>MIN_MOVE){
                vflp_help.setInAnimation(mContext,R.anim.right_in);
                vflp_help.setOutAnimation(mContext,R.anim.right_out);
                vflp_help.showNext();
            }else if(e2.getX()-e1.getX()>MIN_MOVE){
                vflp_help.setInAnimation(mContext,R.anim.left_in);
                vflp_help.setOutAnimation(mContext,R.anim.left_out);
                vflp_help.showPrevious();
            }
            return true;
        }
    }
    private ImageView getImageView(int resId){
        ImageView img=new ImageView(this);
        img.setBackgroundResource(resId);
        return img;
    }
}
