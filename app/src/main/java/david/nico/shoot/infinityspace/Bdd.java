package david.nico.shoot.infinityspace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by USER on 03/08/2016.
 */
public class Bdd extends SQLiteOpenHelper
{
    private static final String DB_NAME = "nombreMystere";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "SCORES";
    private static final String SCORES_KEY_ID = "TABLENAME_ID";
    private static final String SCORES_KEY_PSEUDO = "TABLENAME_PSEUDO";
    private static final String SCORES_KEY_SCORE = "TABLENAME_SCORE";

    public Bdd(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "("
                + SCORES_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SCORES_KEY_PSEUDO + " TEXT,"
                + SCORES_KEY_SCORE + " INT DEFAULT 0"
                +")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int insertScore(String pseudo, int score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ajout = new ContentValues();

        ajout.put(SCORES_KEY_PSEUDO, pseudo);
        ajout.put(SCORES_KEY_SCORE, score);

        Long retourRequete = db.insert(TABLE_NAME, null, ajout);
        db.close();

        int nouvelID = retourRequete.intValue();
        return nouvelID;
    }

    public ArrayList<Score> getScores()
    {
        ArrayList<Score> listeScores = new ArrayList<>();

        String requete = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + SCORES_KEY_SCORE + " DESC LIMIT 10";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curseur = db.rawQuery(requete, null);

        while (curseur.moveToNext())
        {
            Score score = new Score(curseur.getInt(0), curseur.getString(1), curseur.getInt(2));
            listeScores.add(score);
        }
        curseur.close();
        db.close();

        return listeScores;
    }

    public void clearScores()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        db.close();
    }
}