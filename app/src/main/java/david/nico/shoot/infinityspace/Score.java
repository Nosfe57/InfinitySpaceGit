package david.nico.shoot.infinityspace;

import android.content.res.Resources;

/**
 * Created by USER on 03/08/2016.
 */
public class Score
{
    private int id;
    private String pseudo;
    private int valeur;

    //region Get
    public int getId(){return id;}
    public String getPseudo(){return pseudo;}
    public int getValeur(){return valeur;}
    //endregion

    public Score()
    {
        pseudo = Resources.getSystem().getString(R.string.defaultUser);
        valeur = 0;
    }

    public Score(int scoreId, String scorePseudo, int scoreValeur)
    {
        id = scoreId;
        pseudo = scorePseudo;
        valeur = scoreValeur;
    }
}
