package david.nico.shoot.infinityspace;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.content.res.Resources;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by USER on 03/08/2016.
 */
public class Ennemi extends Vaisseau
{
    private int pointsScore;

    //region Get_Set
    public int getPointsScore()
    {
        return pointsScore;
    }
    public void setPointsScore(int nouveauScore)
    {
        pointsScore = nouveauScore;
    }

    public Context getContext(){return context;}
    public void setContext(Context nouveauContext){context = nouveauContext;}

    public Point getTailleEcran(){return tailleEcran;}
    public void setTailleEcran(Point nouveauPoint){tailleEcran = nouveauPoint;}
    //endregion

    public Ennemi()
    {
        super();
        pointsScore = 1;

        //ImageView test = new ImageView();
    }

    public Ennemi(ImageView ennemiSprite, int ennemiPV, int ennemiScore, Context ennemiContext, Point ennemiTailleEcran)
    {
        super(ennemiSprite, ennemiPV, ennemiContext);
        pointsScore = ennemiScore;
        activity = (Activity)ennemiContext;
        tailleEcran = ennemiTailleEcran;
        apparaitre();
        bouger();


    }

    public void apparaitre()
    {
        sprite = new ImageView(context);
        sprite.setImageResource(R.drawable.enemy_animation);

        layoutParams = new FrameLayout.LayoutParams(dpToPx(40), dpToPx(40));
        layoutParams.leftMargin = tailleEcran.x;
        Random rnd = new Random();
        layoutParams.topMargin = rnd.nextInt(tailleEcran.y - dpToPx(40));

        activity = (Activity)context;
        activity.addContentView(sprite, layoutParams);

        hitBox = new Rect(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + layoutParams.width, layoutParams.topMargin + layoutParams.height);

        sprite.post(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable anim = (AnimationDrawable) sprite.getDrawable();
                anim.start();
            }
        });
    }


}
