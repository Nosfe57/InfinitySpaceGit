package david.nico.shoot.infinityspace;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nico on 08/08/2016.
 */
public abstract class ObjetEnMouvement
{
    protected ImageView sprite;
    protected FrameLayout.LayoutParams layoutParams;
    protected Timer timerMouvement;
    protected Activity activity;
    protected Point tailleEcran;
    protected int vitesse; //Nombre négatif pour les ennemis et positif pour le joueur
    protected Rect hitBox;

    //Constructeur
    protected ObjetEnMouvement() { vitesse = -1;}


    public void bouger()
    {
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                activity.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (sprite != null)
                        {
                            if (layoutParams.leftMargin > 0 && layoutParams.leftMargin < 2000)
                            {
                                layoutParams.leftMargin += vitesse;
                                sprite.setLayoutParams(layoutParams);
                                hitBox.offsetTo(layoutParams.leftMargin, layoutParams.topMargin);
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
        timerMouvement.schedule(task, 0, 10);
    }

    public boolean testColision()
    {


        return false;
    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}