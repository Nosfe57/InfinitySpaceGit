package david.nico.shoot.infinityspace;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
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
    private Context context;
    private Point tailleEcran;
    FrameLayout.LayoutParams layoutParams;
    Timer timerMouvement;
    Activity activity;

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
        super(ennemiSprite, ennemiPV);
        pointsScore = ennemiScore;
        context = ennemiContext;
        tailleEcran = ennemiTailleEcran;
        apparaitre(tailleEcran);
        bouger(0f, 10);
    }

    @Override
    public void tirer(Point tailleEcran)
    {
        //Code
    }

    @Override
    public void bouger(float y, int tailleFenetre)
    {
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        if(sprite != null)
                        {
                            if (layoutParams.leftMargin > 100)
                            {
                                layoutParams.leftMargin -= 2;
                                ((ViewGroup) sprite.getParent()).removeView(sprite);
                                activity.addContentView(sprite, layoutParams);
                            }
                            else
                            {
                                timerMouvement.cancel();
                                timerMouvement.purge();
                                ((ViewGroup) sprite.getParent()).removeView(sprite);
                                sprite = null;
                            }
                        }
                    }
                });
            }
        };
        timerMouvement = new Timer();
        timerMouvement.schedule(task, 500, 2);

    }

    public void apparaitre(Point tailleEcran)
    {
        sprite = new ImageView(context);

        layoutParams = new FrameLayout.LayoutParams(80, 80);
        layoutParams.leftMargin = tailleEcran.x -(sprite.getWidth()+80);
        Random rnd = new Random();
        layoutParams.topMargin = rnd.nextInt(201);

        activity = (Activity)context;
        sprite.setImageResource(R.drawable.spaceship_enemy_start);
        activity.addContentView(sprite, layoutParams);
    }


}
