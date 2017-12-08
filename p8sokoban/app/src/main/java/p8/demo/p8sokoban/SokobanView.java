package p8.demo.p8sokoban;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

//Coordonnées: y : 444.38544 x : 262.39258
import java.util.Timer;
import java.util.TimerTask;

import static p8.demo.p8sokoban.p8_Sokoban.isclickedson;
import static p8.demo.p8sokoban.p8_Sokoban.timer;
//import static p8.demo.p8sokoban.Coloris.timer;

/* On prend le nombre aléatoire d'un nombre défini de boutons */
//public class ColorisView extends SurfaceView implements SurfaceHolder.Callback, Runnable, View.OnTouchListener {
public class SokobanView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    
    // Declaration des images
    private Bitmap 		vide;
    
    private Bitmap 		rouge_blanc_blanc;
    private Bitmap 		blanc_blanc_blanc;
    private Bitmap 		bleu_blanc_blanc;
    private Bitmap 		jaune_blanc_blanc;
    private Bitmap 		vert_blanc_blanc;
    private Bitmap 		rouge_vert_blanc;
    private Bitmap 		vert_vert_vert;
    private Bitmap 		rouge_rouge_rouge;
    private Bitmap 		blanc_blanc_rouge;
    private Bitmap 		rouge_blanc_blanch;
    private Bitmap 		blanc_blanc_blanch;
    private Bitmap 		jaune_blanc_blanch;
    private Bitmap 		vert_blanc_blanch;
    private Bitmap 		rouge_vert_blanch;
    private Bitmap 		vert_vert_verth;
    private Bitmap 		rouge_rouge_rougeh;
    private Bitmap 		rouge_blanc_blancb;
    private Bitmap 		jaune_blanc_blancb;
    private Bitmap 		vert_blanc_blancb;
    private Bitmap 		rouge_vert_blancb;
    private Bitmap      bleu_blanc_blanch;
    private Bitmap      bleu_blanc_blancb;
    private Bitmap 		blanc_blanc_bleu;
    private Bitmap 		blanc_blanc_jaune;
    private Bitmap 		blanc_blanc_vert;
    private Bitmap 		blanc_vert_rouge;
    
    
    private Bitmap 		rouge_bmp;
    private Bitmap 		blanc_bmp;
    private Bitmap 		bleu_bmp;
    private Bitmap 		jaune_bmp;
    private Bitmap 		vert_bmp;
    
    static int NB_TRIPLET = 15;
    
    // Déclaration des objets Ressources et Context permettant d'accéder aux ressources de notre application et de les charger
    private Resources 	mRes;
    private Context 	mContext;


    MediaPlayer mp = MediaPlayer.create(getContext().getApplicationContext(), R.raw.comp);

    int score = 0;
    int nbtriplet = 0;
    int nbtriplet2 = 0;
    int nbtriplet3 = 0;
    int triplet[] = new int[NB_TRIPLET+20];
    int triplet2[] = new int[NB_TRIPLET+20];
    int triplet3[] = new int[NB_TRIPLET+20];
    int nbclick[] = new int[NB_TRIPLET+20];
    int nbclick2[] = new int[NB_TRIPLET+20];
    int nbclick3[] = new int[NB_TRIPLET+20];
    String ctriplet[] = new String[NB_TRIPLET];
    int postriplet[] = new int[NB_TRIPLET+20];
    int postriplet2[] = new int[NB_TRIPLET+20];
    int postriplet3[] = new int[NB_TRIPLET+20];
    // tableau modelisant la carte du jeu
    int[][] carte;
    
    // Ancres pour pouvoir centrer la carte du jeu
    int        carteTopAnchor;                   // coordonnées en Y du point d'ancrage de notre carte
    int        carteLeftAnchor;                  // coordonnées en X du point d'ancrage de notre carte
    
    // Taille de la carte
    static final int    carteWidth    = 10; // 10
    static final int    carteHeight   = 10; // 10
    
    static final int    carteTileSize = 20;
    
    Canvas canvas = null;
    
    // Constantes modélisant les différentes types de cases
    static final int    CST_vide      = 4;
    
    int [][] ref    = {
    {CST_vide, CST_vide, CST_vide,CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide},
    {CST_vide, CST_vide, CST_vide,CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide},
    {CST_vide, CST_vide, CST_vide,CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide},
    {CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide},
    {CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide},
    {CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide},
    {CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide},
    {CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide},
    {CST_vide, CST_vide, CST_vide,CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide},
    {CST_vide, CST_vide, CST_vide,CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide, CST_vide}
    };
    
    // Position de reference des triplets dans la carte:
    int [][] refTriplet   = {
    {0, 0},
    {0, 0},
    {0, 0},
    {0, 0},
    {0, 0},
    {0, 0},
    {0, 0},
    {0, 0},
    {0, 0},
    {0, 0}
    };
    
    /* Compteur et max pour animer les zones d'arrivee des diamants */
    int currentStepZone = 0;
    int maxStepZone     = 4;
    
    // Thread utiliser pour animer les zones de depot des diamants
    private     boolean in = true;
    private     Thread  cv_thread;
    SurfaceHolder holder;
    
    Paint paint;
    Paint paintTimer;
    Paint paintScore;
    int affichescore = 0;
    boolean endSearch = true;
    boolean endSearch2 = true;
    boolean endSearch3 = true;
    
    static final int rouge_num   = 5;
    static final int blanc_num   = 6;
    static final int bleu_num    = 7;
    static final int jaune_num   = 8;
    static final int vert_num    = 9;
    
    // Etapes du triplet 1 : selectionner / 2 : dessiner
    int selection_triplet1 = 0;
    int selection_triplet2 = 0;
    int selection_triplet3 = 0;
    int selection_triplet4 = 0;
    int selection_triplet5 = 0;
    int selection_triplet6 = 0;
    int selection_triplet7 = 0;
    int selection_triplet8 = 0;
    int selection_triplet9 = 0;
    int selection_triplet10 = 0;
    
    boolean initialDown;
    
    String line;
    String line2;
    String line3;
    
    /**
     * The constructor called from the main JetBoy activity
     *
     * @param context
     * @param attrs
     */
    public SokobanView(Context context, AttributeSet attrs) {
        super(context, attrs);

        
        // Permet d'ecouter les surfaceChanged, surfaceCreated, surfaceDestroyed
        holder = getHolder();
        holder.addCallback(this);
        
        // chargement des images
        mContext	        = context;
        mRes 		        = mContext.getResources();
        
        vide 		        = BitmapFactory.decodeResource(mRes, R.drawable.vide);
        
        rouge_blanc_blanc   = BitmapFactory.decodeResource(mRes, R.drawable.rouge_blanc_blanc);
        blanc_blanc_blanc   = BitmapFactory.decodeResource(mRes, R.drawable.blanc_blanc_blanc);
        bleu_blanc_blanc    = BitmapFactory.decodeResource(mRes, R.drawable.bleu_blanc_blanc);
        jaune_blanc_blanc   = BitmapFactory.decodeResource(mRes, R.drawable.jaune_blanc_blanc);
        vert_blanc_blanc    = BitmapFactory.decodeResource(mRes, R.drawable.vert_blanc_blanc);
        rouge_vert_blanc    = BitmapFactory.decodeResource(mRes, R.drawable.rouge_vert_blanc);
        vert_vert_vert      = BitmapFactory.decodeResource(mRes, R.drawable.vert_vert_vert);
        rouge_rouge_rouge   = BitmapFactory.decodeResource(mRes, R.drawable.rouge_rouge_rouge);
        blanc_blanc_rouge   = BitmapFactory.decodeResource(mRes, R.drawable.blanc_blanc_rouge);
        blanc_blanc_bleu    = BitmapFactory.decodeResource(mRes, R.drawable.blanc_blanc_bleu);
        blanc_blanc_jaune   = BitmapFactory.decodeResource(mRes, R.drawable.blanc_blanc_jaune);
        blanc_blanc_vert    = BitmapFactory.decodeResource(mRes, R.drawable.blanc_blanc_vert);
        blanc_vert_rouge    = BitmapFactory.decodeResource(mRes, R.drawable.blanc_vert_rouge);
        bleu_blanc_blanch   = BitmapFactory.decodeResource(mRes, R.drawable.bleu_blanc_blanch);
        bleu_blanc_blancb   = BitmapFactory.decodeResource(mRes, R.drawable.bleu_blanc_blancb);
        rouge_blanc_blanch   = BitmapFactory.decodeResource(mRes, R.drawable.rouge_blanc_blanch);
        blanc_blanc_blanch   = BitmapFactory.decodeResource(mRes, R.drawable.blanc_blanc_blanch);
        jaune_blanc_blanch   = BitmapFactory.decodeResource(mRes, R.drawable.jaune_blanc_blanch);
        vert_blanc_blanch    = BitmapFactory.decodeResource(mRes, R.drawable.vert_blanc_blanch);
        rouge_vert_blanch    = BitmapFactory.decodeResource(mRes, R.drawable.rouge_vert_blanch);
        vert_vert_verth      = BitmapFactory.decodeResource(mRes, R.drawable.vert_vert_verth);
        rouge_rouge_rougeh   = BitmapFactory.decodeResource(mRes, R.drawable.rouge_rouge_rougeh);
        rouge_blanc_blancb   = BitmapFactory.decodeResource(mRes, R.drawable.rouge_blanc_blancb);
        jaune_blanc_blancb   = BitmapFactory.decodeResource(mRes, R.drawable.jaune_blanc_blancb);
        vert_blanc_blancb    = BitmapFactory.decodeResource(mRes, R.drawable.vert_blanc_blancb);
        rouge_vert_blancb    = BitmapFactory.decodeResource(mRes, R.drawable.rouge_vert_blancb);


        
        //rouge_blanc_blanc.setOnTouchListener(this);
        
        rouge_bmp           = BitmapFactory.decodeResource(mRes, R.drawable.rouge);
        blanc_bmp           = BitmapFactory.decodeResource(mRes, R.drawable.blanc);
        bleu_bmp            = BitmapFactory.decodeResource(mRes, R.drawable.bleu);
        jaune_bmp           = BitmapFactory.decodeResource(mRes, R.drawable.jaune);
        vert_bmp            = BitmapFactory.decodeResource(mRes, R.drawable.vert);
        
        // initialisation des parametres du jeu
        initparameters();
        
        // creation du thread
        cv_thread   = new Thread(this);
        // prise de focus pour gestion des touches
        setFocusable(true);
    }
    
    public void searchText() {
        if(endSearch) {
            line = "";
            InputStream is = this.getResources().openRawResource(R.raw.data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            Random random = new Random();
            int myNumber = random.nextInt(6 - 0 + 1) + 0;
            
            endSearch = false;
            
            try {
                for (int i=0; i < myNumber; i++ ) {
                    reader.readLine();
                }
                line = reader.readLine();
            } catch (java.io.IOException e) {
                
            }
            
            Log.i("TEST", line);
            Log.d("", "" + myNumber);
        }
        if(endSearch2) {
            line2 = "";
            InputStream is = this.getResources().openRawResource(R.raw.data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            Random random = new Random();
            int myNumber = random.nextInt(6 - 0 + 1) + 0;

            endSearch2 = false;

            try {
                for (int i=0; i < myNumber; i++ ) {
                    reader.readLine();
                }
                line2 = reader.readLine();
            } catch (java.io.IOException e) {

            }

            //Log.i("TEST", line);
            //Log.d("", "" + myNumber);
        }
        if(endSearch3) {
            line3 = "";
            InputStream is = this.getResources().openRawResource(R.raw.data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            Random random = new Random();
            int myNumber = random.nextInt(6 - 0 + 1) + 0;

            endSearch3 = false;

            try {
                for (int i=0; i < myNumber; i++ ) {
                    reader.readLine();
                }
                line3 = reader.readLine();
            } catch (java.io.IOException e) {

            }

            //Log.i("TEST", line);
            //Log.d("", "" + myNumber);
        }
    }
    
    // initialisation du jeu
    public void initparameters() {
        paint = new Paint();
        paint.setColor(0xff0000);
        
        paint.setDither(true);
        paint.setColor(0xFFFFFF00);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
        paint.setTextAlign(Paint.Align.LEFT);
        carte           = new int[carteHeight][carteWidth];
        carteTopAnchor  = (getHeight()- carteHeight*carteTileSize)/2;
        carteLeftAnchor = (getWidth()- carteWidth*carteTileSize)/2;
        
        paintTimer = new Paint();
        paintTimer.setColor(Color.WHITE);
        paintTimer.setStrokeJoin(Paint.Join.ROUND);
        paintTimer.setStrokeCap(Paint.Cap.ROUND);
        paintTimer.setStrokeWidth(2);
        paintTimer.setTextAlign(Paint.Align.LEFT);
        paintTimer.setTextSize(16f);
        
        paintScore = new Paint();
        paintScore.setColor(Color.WHITE);
        paintScore.setStrokeJoin(Paint.Join.ROUND);
        paintScore.setStrokeCap(Paint.Cap.ROUND);
        paintScore.setStrokeWidth(2);
        paintScore.setTextAlign(Paint.Align.LEFT);
        paintScore.setTextSize(16f);
        
        if ((cv_thread!=null) && (!cv_thread.isAlive())) {
            cv_thread.start();
            Log.e("-FCT-", "cv_thread.start()");
        }
    }
    
    private void paintInterface(Canvas canvas, String data,String data2,String data3) {
        Log.i("PAINT INTERFACE 2", data);
        int decal2 = 70;
        int decal3 = -70;

        // if(nbtriplet == 0) {
        
        
        if (triplet[nbtriplet] == 0 || triplet[nbtriplet] == 1) {
            Log.i("PAINT INTERFACE 2", "triplet[nbtriplet] == 0 ");
            if (data.equals("rouge_blanc_blanc")) {
                canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
            }
            
            if (data.equals("blanc_blanc_blanc")) {
                canvas.drawBitmap(blanc_blanc_blanc, (getWidth() - blanc_blanc_blanc.getWidth()) / 2, getHeight() - blanc_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
            }
            if (data.equals("bleu_blanc_blanc")) {
                canvas.drawBitmap(bleu_blanc_blanc, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
            }
            if (data.equals("jaune_blanc_blanc")) {
                canvas.drawBitmap(jaune_blanc_blanc, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
            }
            if (data.equals("vert_blanc_blanc")) {
                canvas.drawBitmap(vert_blanc_blanc, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
            }
            if (data.equals("rouge_vert_blanc")) {
                canvas.drawBitmap(rouge_vert_blanc, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (data.equals("vert_vert_vert")) {
                canvas.drawBitmap(vert_vert_vert, (getWidth() - vert_vert_vert.getWidth()) / 2, getHeight() - vert_vert_vert.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
            }
            if (data.equals("rouge_rouge_rouge")) {
                canvas.drawBitmap(rouge_rouge_rouge, (getWidth() - rouge_rouge_rouge.getWidth()) / 2, getHeight() - rouge_rouge_rouge.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
            }
            if (line.equals("blanc_blanc_rouge")) {
                canvas.drawBitmap(blanc_blanc_rouge, (getWidth() - blanc_blanc_rouge.getWidth()) / 2, getHeight() - blanc_blanc_rouge.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
            }
            
            if (line.equals("blanc_blanc_bleu")) {
                canvas.drawBitmap(blanc_blanc_bleu, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
            }
            if (line.equals("blanc_blanc_jaune")) {
                canvas.drawBitmap(blanc_blanc_jaune, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
            }
            if (line.equals("blanc_blanc_vert")) {
                canvas.drawBitmap(blanc_blanc_vert, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
            }
            if (line.equals("blanc_vert_rouge")) {
                canvas.drawBitmap(blanc_vert_rouge, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line.equals("bleu_blanc_blanch")) {
                canvas.drawBitmap(bleu_blanc_blanch, (getWidth() - bleu_blanc_blanch.getWidth()) / 2, getHeight() - bleu_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line.equals("bleu_blanc_blancb")) {
                canvas.drawBitmap(bleu_blanc_blancb, (getWidth() - bleu_blanc_blancb.getWidth()) / 2, getHeight() - bleu_blanc_blancb.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line.equals("rouge_blanc_blanch")) {
                canvas.drawBitmap(rouge_blanc_blanch, (getWidth() - rouge_blanc_blanch.getWidth()) / 2, getHeight() - rouge_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line.equals("rouge_blanc_blancb")) {
                canvas.drawBitmap(rouge_blanc_blancb, (getWidth() - rouge_blanc_blancb.getWidth()) / 2, getHeight() - rouge_blanc_blancb.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line.equals("blanc_blanc_blanch")) {
                canvas.drawBitmap(blanc_blanc_blanch, (getWidth() - blanc_blanc_blanch.getWidth()) / 2, getHeight() - blanc_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line.equals("jaune_blanc_blanch")) {
                canvas.drawBitmap(jaune_blanc_blanch, (getWidth() - jaune_blanc_blanch.getWidth()) / 2, getHeight() - jaune_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line.equals("jaune_blanc_blancb")) {
                canvas.drawBitmap(jaune_blanc_blancb, (getWidth() - jaune_blanc_blancb.getWidth()) / 2, getHeight() - jaune_blanc_blancb.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line.equals("vert_blanc_blanch")) {
                canvas.drawBitmap(vert_blanc_blanch, (getWidth() - vert_blanc_blanch.getWidth()) / 2, getHeight() - vert_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line.equals("vert_blanc_blancb")) {
                canvas.drawBitmap(vert_blanc_blancb, (getWidth() - vert_blanc_blancb.getWidth()) / 2, getHeight() - vert_blanc_blancb.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line.equals("vert_vert_verth")) {
                canvas.drawBitmap(vert_vert_verth, (getWidth() - vert_vert_verth.getWidth()) / 2, getHeight() - vert_vert_verth.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line.equals("rouge_vert_blanch")) {
                canvas.drawBitmap(rouge_vert_blanch, (getWidth() - rouge_vert_blanch.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line.equals("rouge_vert_blancb")) {
                canvas.drawBitmap(rouge_vert_blancb, (getWidth() - rouge_vert_blancb.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line.equals("rouge_rouge_rougeh")) {
                canvas.drawBitmap(rouge_rouge_rougeh, (getWidth() - rouge_rouge_rougeh.getWidth()) / 2, getHeight() - rouge_rouge_rougeh.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            
            
            
        }
        if (triplet2[nbtriplet2] == 0 || triplet2[nbtriplet2] == 1) {
            Log.i("PAINT INTERFACE 2", "triplet[nbtriplet] == 0 ");
            if (data2.equals("rouge_blanc_blanc")) {
                canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2 +decal2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
            }

            if (data2.equals("blanc_blanc_blanc")) {
                canvas.drawBitmap(blanc_blanc_blanc, (getWidth() - blanc_blanc_blanc.getWidth()) / 2  +decal2 , getHeight() - blanc_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
            }
            if (data2.equals("bleu_blanc_blanc")) {
                canvas.drawBitmap(bleu_blanc_blanc, (getWidth() - bleu_blanc_blanc.getWidth()) / 2 +decal2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
            }
            if (data2.equals("jaune_blanc_blanc")) {
                canvas.drawBitmap(jaune_blanc_blanc, (getWidth() - jaune_blanc_blanc.getWidth()) / 2 +decal2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
            }
            if (data2.equals("vert_blanc_blanc")) {
                canvas.drawBitmap(vert_blanc_blanc, (getWidth() - vert_blanc_blanc.getWidth()) / 2 +decal2, getHeight() - vert_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
            }
            if (data2.equals("rouge_vert_blanc")) {
                canvas.drawBitmap(rouge_vert_blanc, (getWidth() - rouge_vert_blanc.getWidth()) / 2 +decal2, getHeight() - rouge_vert_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (data2.equals("vert_vert_vert")) {
                canvas.drawBitmap(vert_vert_vert, (getWidth() - vert_vert_vert.getWidth()) / 2 +decal2 , getHeight() - vert_vert_vert.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
            }
            if (data2.equals("rouge_rouge_rouge")) {
                canvas.drawBitmap(rouge_rouge_rouge, (getWidth() - rouge_rouge_rouge.getWidth()) / 2 +decal2, getHeight() - rouge_rouge_rouge.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
            }
            if (line2.equals("blanc_blanc_rouge")) {
                canvas.drawBitmap(blanc_blanc_rouge, (getWidth() - blanc_blanc_rouge.getWidth()) / 2 +decal2, getHeight() - blanc_blanc_rouge.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
            }

            if (line2.equals("blanc_blanc_bleu")) {
                canvas.drawBitmap(blanc_blanc_bleu, (getWidth() - bleu_blanc_blanc.getWidth()) / 2 +decal2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
            }
            if (line2.equals("blanc_blanc_jaune")) {
                canvas.drawBitmap(blanc_blanc_jaune, (getWidth() - jaune_blanc_blanc.getWidth()) / 2 +decal2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
            }
            if (line2.equals("blanc_blanc_vert")) {
                canvas.drawBitmap(blanc_blanc_vert, (getWidth() - vert_blanc_blanc.getWidth()) / 2 +decal2, getHeight() - vert_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
            }
            if (line2.equals("blanc_vert_rouge")) {
                canvas.drawBitmap(blanc_vert_rouge, (getWidth() - rouge_vert_blanc.getWidth()) / 2 +decal2, getHeight() - rouge_vert_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line2.equals("bleu_blanc_blanch")) {
                canvas.drawBitmap(bleu_blanc_blanch, (getWidth() - bleu_blanc_blanch.getWidth()) / 2+decal2, getHeight() - bleu_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line2.equals("bleu_blanc_blancb")) {
                canvas.drawBitmap(bleu_blanc_blancb, (getWidth() - bleu_blanc_blancb.getWidth()) / 2+decal2, getHeight() - bleu_blanc_blancb.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line2.equals("rouge_blanc_blanch")) {
                canvas.drawBitmap(rouge_blanc_blanch, (getWidth() - rouge_blanc_blanch.getWidth()) / 2+decal2, getHeight() - rouge_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line2.equals("rouge_blanc_blancb")) {
                canvas.drawBitmap(rouge_blanc_blancb, (getWidth() - rouge_blanc_blancb.getWidth()) / 2+decal2, getHeight() - rouge_blanc_blancb.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line2.equals("blanc_blanc_blanch")) {
                canvas.drawBitmap(blanc_blanc_blanch, (getWidth() - blanc_blanc_blanch.getWidth()) / 2+decal2, getHeight() - blanc_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line2.equals("jaune_blanc_blanch")) {
                canvas.drawBitmap(jaune_blanc_blanch, (getWidth() - jaune_blanc_blanch.getWidth()) / 2+decal2, getHeight() - jaune_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line2.equals("jaune_blanc_blancb")) {
                canvas.drawBitmap(jaune_blanc_blancb, (getWidth() - jaune_blanc_blancb.getWidth()) / 2+decal2, getHeight() - jaune_blanc_blancb.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line2.equals("vert_blanc_blanch")) {
                canvas.drawBitmap(vert_blanc_blanch, (getWidth() - vert_blanc_blanch.getWidth()) / 2+decal2, getHeight() - vert_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line2.equals("vert_blanc_blancb")) {
                canvas.drawBitmap(vert_blanc_blancb, (getWidth() - vert_blanc_blancb.getWidth()) / 2+decal2, getHeight() - vert_blanc_blancb.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line2.equals("vert_vert_verth")) {
                canvas.drawBitmap(vert_vert_verth, (getWidth() - vert_vert_verth.getWidth()) / 2+decal2, getHeight() - vert_vert_verth.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line2.equals("rouge_vert_blanch")) {
                canvas.drawBitmap(rouge_vert_blanch, (getWidth() - rouge_vert_blanch.getWidth()) / 2+decal2, getHeight() - rouge_vert_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line2.equals("rouge_vert_blancb")) {
                canvas.drawBitmap(rouge_vert_blancb, (getWidth() - rouge_vert_blancb.getWidth()) / 2+decal2, getHeight() - rouge_vert_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line2.equals("rouge_rouge_rougeh")) {
                canvas.drawBitmap(rouge_rouge_rougeh, (getWidth() - rouge_rouge_rougeh.getWidth()) / 2+decal2, getHeight() - rouge_rouge_rougeh.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }



        }
        if (triplet3[nbtriplet3] == 0 || triplet3[nbtriplet3] == 1) {
            Log.i("PAINT INTERFACE 2", "triplet[nbtriplet] == 0 ");
            if (data3.equals("rouge_blanc_blanc")) {
                canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2 +decal3, getHeight() - rouge_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
            }

            if (data3.equals("blanc_blanc_blanc")) {
                canvas.drawBitmap(blanc_blanc_blanc, (getWidth() - blanc_blanc_blanc.getWidth()) / 2 +decal3, getHeight() - blanc_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
            }
            if (data3.equals("bleu_blanc_blanc")) {
                canvas.drawBitmap(bleu_blanc_blanc, (getWidth() - bleu_blanc_blanc.getWidth()) / 2+decal3, getHeight() - bleu_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
            }
            if (data3.equals("jaune_blanc_blanc")) {
                canvas.drawBitmap(jaune_blanc_blanc, (getWidth() - jaune_blanc_blanc.getWidth()) / 2+decal3, getHeight() - jaune_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
            }
            if (data3.equals("vert_blanc_blanc")) {
                canvas.drawBitmap(vert_blanc_blanc, (getWidth() - vert_blanc_blanc.getWidth()) / 2+decal3, getHeight() - vert_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
            }
            if (data3.equals("rouge_vert_blanc")) {
                canvas.drawBitmap(rouge_vert_blanc, (getWidth() - rouge_vert_blanc.getWidth()) / 2+decal3, getHeight() - rouge_vert_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (data3.equals("vert_vert_vert")) {
                canvas.drawBitmap(vert_vert_vert, (getWidth() - vert_vert_vert.getWidth()) / 2+decal3, getHeight() - vert_vert_vert.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
            }
            if (data3.equals("rouge_rouge_rouge")) {
                canvas.drawBitmap(rouge_rouge_rouge, (getWidth() - rouge_rouge_rouge.getWidth()) / 2+decal3, getHeight() - rouge_rouge_rouge.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
            }
            if (line3.equals("blanc_blanc_rouge")) {
                canvas.drawBitmap(blanc_blanc_rouge, (getWidth() - blanc_blanc_rouge.getWidth()) / 2+decal3, getHeight() - blanc_blanc_rouge.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
            }

            if (line3.equals("blanc_blanc_bleu")) {
                canvas.drawBitmap(blanc_blanc_bleu, (getWidth() - bleu_blanc_blanc.getWidth()) / 2+decal3, getHeight() - bleu_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
            }
            if (line3.equals("blanc_blanc_jaune")) {
                canvas.drawBitmap(blanc_blanc_jaune, (getWidth() - jaune_blanc_blanc.getWidth()) / 2+decal3, getHeight() - jaune_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
            }
            if (line3.equals("blanc_blanc_vert")) {
                canvas.drawBitmap(blanc_blanc_vert, (getWidth() - vert_blanc_blanc.getWidth()) / 2+decal3, getHeight() - vert_blanc_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
            }
            if (line3.equals("blanc_vert_rouge")) {
                canvas.drawBitmap(blanc_vert_rouge, (getWidth() - rouge_vert_blanc.getWidth()) / 2+decal3, getHeight() - rouge_vert_blanc.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line3.equals("bleu_blanc_blanch")) {
                canvas.drawBitmap(bleu_blanc_blanch, (getWidth() - bleu_blanc_blanch.getWidth()) / 2+decal3, getHeight() - bleu_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line3.equals("bleu_blanc_blancb")) {
                canvas.drawBitmap(bleu_blanc_blancb, (getWidth() - bleu_blanc_blancb.getWidth()) / 2+decal3, getHeight() - bleu_blanc_blancb.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line3.equals("rouge_blanc_blanch")) {
                canvas.drawBitmap(rouge_blanc_blanch, (getWidth() - rouge_blanc_blanch.getWidth()) / 2+decal3, getHeight() - rouge_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line3.equals("rouge_blanc_blancb")) {
                canvas.drawBitmap(rouge_blanc_blancb, (getWidth() - rouge_blanc_blancb.getWidth()) / 2+decal3, getHeight() - rouge_blanc_blancb.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line3.equals("blanc_blanc_blanch")) {
                canvas.drawBitmap(blanc_blanc_blanch, (getWidth() - blanc_blanc_blanch.getWidth()) / 2+decal3, getHeight() - blanc_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line3.equals("jaune_blanc_blanch")) {
                canvas.drawBitmap(jaune_blanc_blanch, (getWidth() - jaune_blanc_blanch.getWidth()) / 2+decal3, getHeight() - jaune_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line3.equals("jaune_blanc_blancb")) {
                canvas.drawBitmap(jaune_blanc_blancb, (getWidth() - jaune_blanc_blancb.getWidth()) / 2+decal3, getHeight() - jaune_blanc_blancb.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line3.equals("vert_blanc_blanch")) {
                canvas.drawBitmap(vert_blanc_blanch, (getWidth() - vert_blanc_blanch.getWidth()) / 2+decal3, getHeight() - vert_blanc_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line3.equals("vert_blanc_blancb")) {
                canvas.drawBitmap(vert_blanc_blancb, (getWidth() - vert_blanc_blancb.getWidth()) / 2+decal3, getHeight() - vert_blanc_blancb.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line3.equals("vert_vert_verth")) {
                canvas.drawBitmap(vert_vert_verth, (getWidth() - vert_vert_verth.getWidth()) / 2+decal3, getHeight() - vert_vert_verth.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line3.equals("rouge_vert_blanch")) {
                canvas.drawBitmap(rouge_vert_blanch, (getWidth() - rouge_vert_blanch.getWidth()) / 2+decal3, getHeight() - rouge_vert_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line3.equals("rouge_vert_blancb")) {
                canvas.drawBitmap(rouge_vert_blancb, (getWidth() - rouge_vert_blancb.getWidth()) / 2+decal3, getHeight() - rouge_vert_blanch.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }
            if (line3.equals("rouge_rouge_rougeh")) {
                canvas.drawBitmap(rouge_rouge_rougeh, (getWidth() - rouge_rouge_rougeh.getWidth()) / 2+decal3, getHeight() - rouge_rouge_rougeh.getHeight(), null);
                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
            }



        }
        //  }
        //if(selection_triplet1 == 2 && (selection_triplet2 == 0 || selection_triplet2 == 1))
        if(nbtriplet<NB_TRIPLET) {
            if (triplet[nbtriplet] == 2 && triplet[nbtriplet + 1] == 0 || triplet[nbtriplet + 1] == 1) {
                Log.i("PAINT INTERFACE 2", "triplet[nbtriplet] == 0 ");
                if (data.equals("rouge_blanc_blanc")) {
                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                }
                
                if (data.equals("blanc_blanc_blanc")) {
                    canvas.drawBitmap(blanc_blanc_blanc, (getWidth() - blanc_blanc_blanc.getWidth()) / 2, getHeight() - blanc_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                }
                if (data.equals("bleu_blanc_blanc")) {
                    canvas.drawBitmap(bleu_blanc_blanc, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                }
                if (data.equals("jaune_blanc_blanc")) {
                    canvas.drawBitmap(jaune_blanc_blanc, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                }
                if (data.equals("vert_blanc_blanc")) {
                    canvas.drawBitmap(vert_blanc_blanc, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                }
                if (data.equals("rouge_vert_blanc")) {
                    canvas.drawBitmap(rouge_vert_blanc, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (data.equals("vert_vert_vert")) {
                    canvas.drawBitmap(vert_vert_vert, (getWidth() - vert_vert_vert.getWidth()) / 2, getHeight() - vert_vert_vert.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                }
                if (data.equals("rouge_rouge_rouge")) {
                    canvas.drawBitmap(rouge_rouge_rouge, (getWidth() - rouge_rouge_rouge.getWidth()) / 2, getHeight() - rouge_rouge_rouge.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                }
                if (line.equals("blanc_blanc_rouge")) {
                    canvas.drawBitmap(blanc_blanc_rouge, (getWidth() - blanc_blanc_rouge.getWidth()) / 2, getHeight() - blanc_blanc_rouge.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                }
                
                if (line.equals("blanc_blanc_bleu")) {
                    canvas.drawBitmap(blanc_blanc_bleu, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                }
                if (line.equals("blanc_blanc_jaune")) {
                    canvas.drawBitmap(blanc_blanc_jaune, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                }
                if (line.equals("blanc_blanc_vert")) {
                    canvas.drawBitmap(blanc_blanc_vert, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                }
                if (line.equals("blanc_vert_rouge")) {
                    canvas.drawBitmap(blanc_vert_rouge, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line.equals("bleu_blanc_blanch")) {
                    canvas.drawBitmap(bleu_blanc_blanch, (getWidth() - bleu_blanc_blanch.getWidth()) / 2, getHeight() - bleu_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line.equals("bleu_blanc_blancb")) {
                    canvas.drawBitmap(bleu_blanc_blancb, (getWidth() - bleu_blanc_blancb.getWidth()) / 2, getHeight() - bleu_blanc_blancb.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line.equals("rouge_blanc_blanch")) {
                    canvas.drawBitmap(rouge_blanc_blanch, (getWidth() - rouge_blanc_blanch.getWidth()) / 2, getHeight() - rouge_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line.equals("rouge_blanc_blancb")) {
                    canvas.drawBitmap(rouge_blanc_blancb, (getWidth() - rouge_blanc_blancb.getWidth()) / 2, getHeight() - rouge_blanc_blancb.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line.equals("blanc_blanc_blanch")) {
                    canvas.drawBitmap(blanc_blanc_blanch, (getWidth() - blanc_blanc_blanch.getWidth()) / 2, getHeight() - blanc_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line.equals("jaune_blanc_blanch")) {
                    canvas.drawBitmap(jaune_blanc_blanch, (getWidth() - jaune_blanc_blanch.getWidth()) / 2, getHeight() - jaune_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line.equals("jaune_blanc_blancb")) {
                    canvas.drawBitmap(jaune_blanc_blancb, (getWidth() - jaune_blanc_blancb.getWidth()) / 2, getHeight() - jaune_blanc_blancb.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line.equals("vert_blanc_blanch")) {
                    canvas.drawBitmap(vert_blanc_blanch, (getWidth() - vert_blanc_blanch.getWidth()) / 2, getHeight() - vert_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line.equals("vert_blanc_blancb")) {
                    canvas.drawBitmap(vert_blanc_blancb, (getWidth() - vert_blanc_blancb.getWidth()) / 2, getHeight() - vert_blanc_blancb.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line.equals("vert_vert_verth")) {
                    canvas.drawBitmap(vert_vert_verth, (getWidth() - vert_vert_verth.getWidth()) / 2, getHeight() - vert_vert_verth.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line.equals("rouge_vert_blanch")) {
                    canvas.drawBitmap(rouge_vert_blanch, (getWidth() - rouge_vert_blanch.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line.equals("rouge_vert_blancb")) {
                    canvas.drawBitmap(rouge_vert_blancb, (getWidth() - rouge_vert_blancb.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line.equals("rouge_rouge_rougeh")) {
                    canvas.drawBitmap(rouge_rouge_rougeh, (getWidth() - rouge_rouge_rougeh.getWidth()) / 2, getHeight() - rouge_rouge_rougeh.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
            }
            if (triplet2[nbtriplet2] == 2 && triplet2[nbtriplet2 + 1] == 0 || triplet2[nbtriplet2 + 1] == 1) {
                Log.i("PAINT INTERFACE 2", "triplet[nbtriplet] == 0 ");
                if (data2.equals("rouge_blanc_blanc")) {
                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2 +decal2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                }

                if (data2.equals("blanc_blanc_blanc")) {
                    canvas.drawBitmap(blanc_blanc_blanc, (getWidth() - blanc_blanc_blanc.getWidth()) / 2+decal2, getHeight() - blanc_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                }
                if (data2.equals("bleu_blanc_blanc")) {
                    canvas.drawBitmap(bleu_blanc_blanc, (getWidth() - bleu_blanc_blanc.getWidth()) / 2+decal2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                }
                if (data2.equals("jaune_blanc_blanc")) {
                    canvas.drawBitmap(jaune_blanc_blanc, (getWidth() - jaune_blanc_blanc.getWidth()) / 2+decal2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                }
                if (data2.equals("vert_blanc_blanc")) {
                    canvas.drawBitmap(vert_blanc_blanc, (getWidth() - vert_blanc_blanc.getWidth()) / 2+decal2, getHeight() - vert_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                }
                if (data2.equals("rouge_vert_blanc")) {
                    canvas.drawBitmap(rouge_vert_blanc, (getWidth() - rouge_vert_blanc.getWidth()) / 2+decal2, getHeight() - rouge_vert_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (data2.equals("vert_vert_vert")) {
                    canvas.drawBitmap(vert_vert_vert, (getWidth() - vert_vert_vert.getWidth()) / 2+decal2, getHeight() - vert_vert_vert.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                }
                if (data2.equals("rouge_rouge_rouge")) {
                    canvas.drawBitmap(rouge_rouge_rouge, (getWidth() - rouge_rouge_rouge.getWidth()) / 2+decal2, getHeight() - rouge_rouge_rouge.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                }
                if (line2.equals("blanc_blanc_rouge")) {
                    canvas.drawBitmap(blanc_blanc_rouge, (getWidth() - blanc_blanc_rouge.getWidth()) / 2+decal2, getHeight() - blanc_blanc_rouge.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                }

                if (line2.equals("blanc_blanc_bleu")) {
                    canvas.drawBitmap(blanc_blanc_bleu, (getWidth() - bleu_blanc_blanc.getWidth()) / 2+decal2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                }
                if (line2.equals("blanc_blanc_jaune")) {
                    canvas.drawBitmap(blanc_blanc_jaune, (getWidth() - jaune_blanc_blanc.getWidth()) / 2+decal2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                }
                if (line2.equals("blanc_blanc_vert")) {
                    canvas.drawBitmap(blanc_blanc_vert, (getWidth() - vert_blanc_blanc.getWidth()) / 2+decal2, getHeight() - vert_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                }
                if (line2.equals("blanc_vert_rouge")) {
                    canvas.drawBitmap(blanc_vert_rouge, (getWidth() - rouge_vert_blanc.getWidth()) / 2+decal2, getHeight() - rouge_vert_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line2.equals("bleu_blanc_blanch")) {
                    canvas.drawBitmap(bleu_blanc_blanch, (getWidth() - bleu_blanc_blanch.getWidth()) / 2+decal2, getHeight() - bleu_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line2.equals("bleu_blanc_blancb")) {
                    canvas.drawBitmap(bleu_blanc_blancb, (getWidth() - bleu_blanc_blancb.getWidth()) / 2+decal2, getHeight() - bleu_blanc_blancb.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line2.equals("rouge_blanc_blanch")) {
                    canvas.drawBitmap(rouge_blanc_blanch, (getWidth() - rouge_blanc_blanch.getWidth()) / 2+decal2, getHeight() - rouge_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line2.equals("rouge_blanc_blancb")) {
                    canvas.drawBitmap(rouge_blanc_blancb, (getWidth() - rouge_blanc_blancb.getWidth()) / 2+decal2, getHeight() - rouge_blanc_blancb.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line2.equals("blanc_blanc_blanch")) {
                    canvas.drawBitmap(blanc_blanc_blanch, (getWidth() - blanc_blanc_blanch.getWidth()) / 2+decal2, getHeight() - blanc_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line2.equals("jaune_blanc_blanch")) {
                    canvas.drawBitmap(jaune_blanc_blanch, (getWidth() - jaune_blanc_blanch.getWidth()) / 2+decal2, getHeight() - jaune_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line2.equals("jaune_blanc_blancb")) {
                    canvas.drawBitmap(jaune_blanc_blancb, (getWidth() - jaune_blanc_blancb.getWidth()) / 2+decal2, getHeight() - jaune_blanc_blancb.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line2.equals("vert_blanc_blanch")) {
                    canvas.drawBitmap(vert_blanc_blanch, (getWidth() - vert_blanc_blanch.getWidth()) / 2+decal2, getHeight() - vert_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line2.equals("vert_blanc_blancb")) {
                    canvas.drawBitmap(vert_blanc_blancb, (getWidth() - vert_blanc_blancb.getWidth()) / 2+decal2, getHeight() - vert_blanc_blancb.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line2.equals("vert_vert_verth")) {
                    canvas.drawBitmap(vert_vert_verth, (getWidth() - vert_vert_verth.getWidth()) / 2+decal2, getHeight() - vert_vert_verth.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line2.equals("rouge_vert_blanch")) {
                    canvas.drawBitmap(rouge_vert_blanch, (getWidth() - rouge_vert_blanch.getWidth()) / 2+decal2, getHeight() - rouge_vert_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line2.equals("rouge_vert_blancb")) {
                    canvas.drawBitmap(rouge_vert_blancb, (getWidth() - rouge_vert_blancb.getWidth()) / 2+decal2, getHeight() - rouge_vert_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line2.equals("rouge_rouge_rougeh")) {
                    canvas.drawBitmap(rouge_rouge_rougeh, (getWidth() - rouge_rouge_rougeh.getWidth()) / 2+decal2, getHeight() - rouge_rouge_rougeh.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
            }
            if (triplet3[nbtriplet3] == 2 && triplet3[nbtriplet3 + 1] == 0 || triplet3[nbtriplet3 + 1] == 1) {
                Log.i("PAINT INTERFACE 2", "triplet[nbtriplet] == 0 ");
                if (data3.equals("rouge_blanc_blanc")) {
                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2+decal3, getHeight() - rouge_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                }

                if (data3.equals("blanc_blanc_blanc")) {
                    canvas.drawBitmap(blanc_blanc_blanc, (getWidth() - blanc_blanc_blanc.getWidth()) / 2+decal3, getHeight() - blanc_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                }
                if (data3.equals("bleu_blanc_blanc")) {
                    canvas.drawBitmap(bleu_blanc_blanc, (getWidth() - bleu_blanc_blanc.getWidth()) / 2+decal3, getHeight() - bleu_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                }
                if (data3.equals("jaune_blanc_blanc")) {
                    canvas.drawBitmap(jaune_blanc_blanc, (getWidth() - jaune_blanc_blanc.getWidth()) / 2+decal3, getHeight() - jaune_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                }
                if (data3.equals("vert_blanc_blanc")) {
                    canvas.drawBitmap(vert_blanc_blanc, (getWidth() - vert_blanc_blanc.getWidth()) / 2+decal3, getHeight() - vert_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                }
                if (data3.equals("rouge_vert_blanc")) {
                    canvas.drawBitmap(rouge_vert_blanc, (getWidth() - rouge_vert_blanc.getWidth()) / 2+decal3, getHeight() - rouge_vert_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (data3.equals("vert_vert_vert")) {
                    canvas.drawBitmap(vert_vert_vert, (getWidth() - vert_vert_vert.getWidth()) / 2+decal3, getHeight() - vert_vert_vert.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                }
                if (data3.equals("rouge_rouge_rouge")) {
                    canvas.drawBitmap(rouge_rouge_rouge, (getWidth() - rouge_rouge_rouge.getWidth()) / 2+decal3, getHeight() - rouge_rouge_rouge.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                }
                if (line3.equals("blanc_blanc_rouge")) {
                    canvas.drawBitmap(blanc_blanc_rouge, (getWidth() - blanc_blanc_rouge.getWidth()) / 2+decal3, getHeight() - blanc_blanc_rouge.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                }

                if (line3.equals("blanc_blanc_bleu")) {
                    canvas.drawBitmap(blanc_blanc_bleu, (getWidth() - bleu_blanc_blanc.getWidth()) / 2+decal3, getHeight() - bleu_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                }
                if (line3.equals("blanc_blanc_jaune")) {
                    canvas.drawBitmap(blanc_blanc_jaune, (getWidth() - jaune_blanc_blanc.getWidth()) / 2+decal3, getHeight() - jaune_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                }
                if (line3.equals("blanc_blanc_vert")) {
                    canvas.drawBitmap(blanc_blanc_vert, (getWidth() - vert_blanc_blanc.getWidth()) / 2+decal3, getHeight() - vert_blanc_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                }
                if (line3.equals("blanc_vert_rouge")) {
                    canvas.drawBitmap(blanc_vert_rouge, (getWidth() - rouge_vert_blanc.getWidth()) / 2+decal3, getHeight() - rouge_vert_blanc.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line3.equals("bleu_blanc_blanch")) {
                    canvas.drawBitmap(bleu_blanc_blanch, (getWidth() - bleu_blanc_blanch.getWidth()) / 2+decal3, getHeight() - bleu_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line3.equals("bleu_blanc_blancb")) {
                    canvas.drawBitmap(bleu_blanc_blancb, (getWidth() - bleu_blanc_blancb.getWidth()) / 2+decal3, getHeight() - bleu_blanc_blancb.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line3.equals("rouge_blanc_blanch")) {
                    canvas.drawBitmap(rouge_blanc_blanch, (getWidth() - rouge_blanc_blanch.getWidth()) / 2+decal3, getHeight() - rouge_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line3.equals("rouge_blanc_blancb")) {
                    canvas.drawBitmap(rouge_blanc_blancb, (getWidth() - rouge_blanc_blancb.getWidth()) / 2+decal3, getHeight() - rouge_blanc_blancb.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line3.equals("blanc_blanc_blanch")) {
                    canvas.drawBitmap(blanc_blanc_blanch, (getWidth() - blanc_blanc_blanch.getWidth()) / 2+decal3, getHeight() - blanc_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line3.equals("jaune_blanc_blanch")) {
                    canvas.drawBitmap(jaune_blanc_blanch, (getWidth() - jaune_blanc_blanch.getWidth()) / 2+decal3, getHeight() - jaune_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line3.equals("jaune_blanc_blancb")) {
                    canvas.drawBitmap(jaune_blanc_blancb, (getWidth() - jaune_blanc_blancb.getWidth()) / 2+decal3, getHeight() - jaune_blanc_blancb.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line3.equals("vert_blanc_blanch")) {
                    canvas.drawBitmap(vert_blanc_blanch, (getWidth() - vert_blanc_blanch.getWidth()) / 2+decal3, getHeight() - vert_blanc_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line3.equals("vert_blanc_blancb")) {
                    canvas.drawBitmap(vert_blanc_blancb, (getWidth() - vert_blanc_blancb.getWidth()) / 2+decal3, getHeight() - vert_blanc_blancb.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line3.equals("vert_vert_verth")) {
                    canvas.drawBitmap(vert_vert_verth, (getWidth() - vert_vert_verth.getWidth()) / 2+decal3, getHeight() - vert_vert_verth.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line3.equals("rouge_vert_blanch")) {
                    canvas.drawBitmap(rouge_vert_blanch, (getWidth() - rouge_vert_blanch.getWidth()) / 2+decal3, getHeight() - rouge_vert_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line3.equals("rouge_vert_blancb")) {
                    canvas.drawBitmap(rouge_vert_blancb, (getWidth() - rouge_vert_blancb.getWidth()) / 2+decal3, getHeight() - rouge_vert_blanch.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
                if (line3.equals("rouge_rouge_rougeh")) {
                    canvas.drawBitmap(rouge_rouge_rougeh, (getWidth() - rouge_rouge_rougeh.getWidth()) / 2+decal3, getHeight() - rouge_rouge_rougeh.getHeight(), null);
                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                }
            }
        }
        
        
        
    }
    
    /*
     private void paintInterface2(Canvas canvas, int posX, int posY) {
     if(selection_triplet1 == 0 || selection_triplet1 == 1) canvas.drawBitmap(rouge_blanc_blanc, posX, posY, null);
     }*/
    
    private void paintcarte(Canvas canvas) {
        for (int i=0; i< carteHeight; i++) {
            for (int j=0; j< carteWidth; j++) {
                switch (carte[i][j]) {
                    case CST_vide:
                        //canvas.drawBitmap(vide, carteLeftAnchor + j * carteTileSize, carteTopAnchor + i * carteTileSize, null);
                        break;
                    case rouge_num:
                        canvas.drawBitmap(rouge_bmp, carteLeftAnchor + j * carteTileSize, carteTopAnchor + i * carteTileSize, null);
                        break;
                    case blanc_num:
                        canvas.drawBitmap(blanc_bmp, carteLeftAnchor + j * carteTileSize, carteTopAnchor + i * carteTileSize, null);
                        break;
                    case bleu_num:
                        canvas.drawBitmap(bleu_bmp, carteLeftAnchor + j * carteTileSize, carteTopAnchor + i * carteTileSize, null);
                        break;
                    case jaune_num:
                        canvas.drawBitmap(jaune_bmp, carteLeftAnchor + j * carteTileSize, carteTopAnchor + i * carteTileSize, null);
                        break;
                    case vert_num:
                        canvas.drawBitmap(vert_bmp, carteLeftAnchor + j * carteTileSize, carteTopAnchor + i * carteTileSize, null);
                        break;
                }
            }
        }
    }
    
    /* test pour gerer le cliquer-glisser
     private void nDraw2(Canvas canvas, int posInterfaceX, int posInterfaceY) {
     //   paintInterface2(canvas, posInterfaceX, posInterfaceY);
     }*/
    
    // Dessin du jeu (fond uni, en fonction du jeu gagne ou pas dessin du plateau et du joueur des diamants et des fleches)
    private void nDraw(Canvas canvas, int posInterfaceX, int posInterfaceY) {
        Log.i("NDRAW", "appel de Ndraw");
        canvas.drawRGB(10, 10, 10);
        
        searchText();
        
        // Interface
        paintInterface(canvas, line,line2,line3);
        
        canvas.drawText("Timer : " + timer, carteLeftAnchor, carteTopAnchor-15, paintTimer);
        
        
        int realscore = score-64;
        
        //canvas.drawBitmap(vert_bmp, carteLeftAnchor+130, carteTopAnchor+330, null);
        canvas.drawText("Score : " + realscore, carteLeftAnchor, carteTopAnchor-50, paintScore);
        
        
        canvas.drawText("----------------------------", carteLeftAnchor-5, carteTopAnchor-1, paintScore);
        //canvas.drawText("I", carteLeftAnchor+150, carteTopAnchor, paintScore);
        //canvas.drawText("", carteLeftAnchor, carteTopAnchor+175, paintScore);
        canvas.drawText("----------------------------",carteLeftAnchor-5, carteTopAnchor+175, paintScore);
        
        
        paintcarte(canvas);
        //paintInterface(canvas);
        int total = 8;
        // Efface les triplets / quadruplets de la même couleur
        for (int i=0; i< total; i++) {
            for (int j=0; j< total; j++) {
                //Log.i("POSITION", "i : " + i + "j : " + j);
                
                // Horizontalement
                if(j<total-4) {
                    if (carte[i][j]!=CST_vide && carte[i][j] == carte[i][j + 1] && carte[i][j] == carte[i][j + 2] && carte[i][j] == carte[i][j + 3] && carte[i][j] == carte[i][j + 4]) {
                        carte[i][j]     = CST_vide;
                        carte[i][j + 1] = CST_vide;
                        carte[i][j + 2] = CST_vide;
                        carte[i][j + 3] = CST_vide;
                        carte[i][j + 4] = CST_vide;
                        score += 5;
                    }
                }
                
                if(j<total-3) {
                    if (carte[i][j]!=CST_vide && carte[i][j] == carte[i][j + 1] && carte[i][j] == carte[i][j + 2] && carte[i][j] == carte[i][j + 3]) {
                        carte[i][j]     = CST_vide;
                        carte[i][j + 1] = CST_vide;
                        carte[i][j + 2] = CST_vide;
                        carte[i][j + 3] = CST_vide;
                        score += 4;
                        
                    }
                }
                
                
                if(j<total-2) {
                    if(carte[i][j]!=CST_vide && carte[i][j] == carte[i][j+1] && carte[i][j] == carte[i][j+2]) {
                        carte[i][j]     = CST_vide;
                        carte[i][j+1]   = CST_vide;
                        carte[i][j+2]   = CST_vide;
                        score += 3;
                    }
                }
                
                // Verticalement
                if(i<total-4) {
                    if (carte[i][j]!=CST_vide && carte[i][j] == carte[i + 1][j] && carte[i][j] == carte[i + 2][j] && carte[i][j] == carte[i + 3][j] && carte[i][j] == carte[i + 4][j]) {
                        carte[i][j]     = CST_vide;
                        carte[i + 1][j] = CST_vide;
                        carte[i + 2][j] = CST_vide;
                        carte[i + 3][j] = CST_vide;
                        carte[i + 4][j] = CST_vide;
                        score += 5;
                    }
                }
                
                if(i<total-3) {
                    if (carte[i][j]!=CST_vide && carte[i][j] == carte[i + 1][j] && carte[i][j] == carte[i + 2][j] && carte[i][j] == carte[i + 3][j]) {
                        carte[i][j]     = CST_vide;
                        carte[i + 1][j] = CST_vide;
                        carte[i + 2][j] = CST_vide;
                        carte[i + 3][j] = CST_vide;
                        score += 4;
                    }
                }
                
                if(i<total-2) {
                    if(carte[i][j]!=CST_vide && carte[i][j] == carte[i+1][j] && carte[i][j] == carte[i+2][j]) {
                        carte[i][j]     = CST_vide;
                        carte[i+1][j]   = CST_vide;
                        carte[i+2][j]   = CST_vide;
                        score += 3;
                    }
                }
            }
            
        }
    }
    
    // callback sur le cycle de vie de la surfaceview
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("-> FCT <-", "surfaceChanged "+ width +" - "+ height);
        initparameters();
    }
    
    public void surfaceCreated(SurfaceHolder arg0) {Log.i("-> FCT <-", "surfaceCreated"); }
    
    public void surfaceDestroyed(SurfaceHolder arg0) { 	Log.i("-> FCT <-", "surfaceDestroyed");  }
    
    /**
     * run (run du thread cree)
     * Endormissement du thread,
     * Modification le compteur d'animation,
     * Ca prend la main pour dessiner
     * et on dessine
     * puis on libere le canvas
     */
    public void run() {
        // Initialisation de la configuration des triplet
        int ii;
        for(ii = 0; ii < NB_TRIPLET+20;ii++) {
            triplet[ii] = 0;
            triplet2[ii] =0;
            triplet3[ii] = 0;
            nbclick[ii] = 0;
            nbclick2[ii] = 0;
            nbclick3[ii] = 0;
            postriplet[ii] = 0;
            postriplet2[ii] = 0;
            postriplet3[ii] = 0;
        }
        
        
        while (in) {
            try {
                
                cv_thread.sleep(40);
                currentStepZone = (currentStepZone + 1) % maxStepZone;
                try {
                    // getHolder : Permet d'avoir la main sur la surface sur laquelle on va travailler
                    canvas = holder.lockCanvas(null);
                    
                    nDraw(canvas, 0, 0);
                    if( canvas == null) in = false;
                } finally {
                    if (canvas != null) {
                        // Liberation , Finit d'éditer les pixels dans la surface.
                        holder.unlockCanvasAndPost(canvas);
                    }
                }
            } catch(Exception e) {
                Log.e("-> RUN <-", "PB DANS RUN");
                in= false;
            }
        }
        SharedPreferences prefs = getContext().getSharedPreferences("highscore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        int scoress = prefs.getInt("scores", 0);

        if(score-64>scoress ){
        editor.putInt("scores", score-64);

        editor.commit();}
    }
    
    
    @TargetApi(Build.VERSION_CODES.FROYO)
    public boolean onTouchEvent (MotionEvent event) {
        Log.i("-> FCT <-", "onTouchEvent: " + event.getX());
        float xTouch;
        float yTouch;
        int pointerId;
        int actionIndex = event.getActionIndex();
        // Passage du cliquer-glisser vers le dessinage
        
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("Coordonnées", "y : " + String.valueOf("ooooooooooooooooooooooooooooooooooo"+event.getY() + " x : " + String.valueOf(event.getX())));
                Log.i("dowwwn",""+String.valueOf((getWidth()- carteWidth*carteTileSize)/2)+"iiiiiiiiiiiirtertertretertertertertertretertreteri"+String.valueOf((getHeight()- carteHeight*carteTileSize)/2));
                //1)130-190
                //2)204 - 260
                //3)61 - 118
                if (event.getX()>130 && event.getX()<190 && event.getY()>getHeight()-50) {
                    pointerId = event.getPointerId(actionIndex);
                    if(triplet[nbtriplet] == 0) {
                        triplet[nbtriplet] = 1;
                    } else if(nbtriplet > 0 && triplet[nbtriplet-1] == 2 && triplet[nbtriplet]== 0) {
                        triplet[nbtriplet] = 1;
                    }
                    else if(nbtriplet == 0 && triplet[nbtriplet]== 0){
                        triplet[nbtriplet] = 1;
                    }
                    //  Log.i("ACTION_DO  bn WNfds", "fdsfsdfdsfsdfdsfdsfsdfsfdsfdsfdsfdsfdsfdsfds");
                }
                //Log.i("ACTION_DOWN", "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
                if (event.getX()>204&& event.getX()<260 && event.getY()>getHeight()-50) {
                    pointerId = event.getPointerId(actionIndex);
                    if(triplet2[nbtriplet2] == 0) {
                        triplet2[nbtriplet2] = 1;
                    } else if(nbtriplet2 > 0 && triplet2[nbtriplet2-1] == 2 && triplet2[nbtriplet2]== 0) {
                        triplet2[nbtriplet2] = 1;
                    }
                    else if(nbtriplet2 == 0 && triplet2[nbtriplet2]== 0){
                        triplet2[nbtriplet2] = 1;
                    }
                    //  Log.i("ACTION_DO  bn WNfds", "fdsfsdfdsfsdfdsfdsfsdfsfdsfdsfdsfdsfdsfdsfds");
                }

                if (event.getX()>61 && event.getX()<118 && event.getY()>getHeight()-50) {
                    pointerId = event.getPointerId(actionIndex);
                    if(triplet3[nbtriplet3] == 0) {
                        triplet3[nbtriplet3] = 1;
                    } else if(nbtriplet3 > 0 && triplet3[nbtriplet3-1] == 2 && triplet3[nbtriplet3]== 0) {
                        triplet3[nbtriplet3] = 1;
                    }
                    else if(nbtriplet3 == 0 && triplet3[nbtriplet3]== 0){
                        triplet3[nbtriplet3] = 1;
                    }
                    //  Log.i("ACTION_DO  bn WNfds", "fdsfsdfdsfsdfdsfdsfsdfsfdsfdsfdsfdsfdsfdsfds");
                }
                break;
                
                
            case MotionEvent.ACTION_MOVE:
                if(triplet[nbtriplet] == 1){
                    final int pointerCount = event.getPointerCount();
                    for (actionIndex = 0; actionIndex < pointerCount; actionIndex++) {
                        // Some pointer has moved, search it by pointer id
                        pointerId = event.getPointerId(actionIndex);
                        
                        //xTouch = event.getX();
                        //yTouch = event.getY();
                        
                        //canvas.drawBitmap(vert_bmp, xTouch , yTouch , null);
                        if (line.equals("rouge_blanc_blanc")) {
                            // Ajout du triplet rouge blanc_blanc dans le dessin
                            canvas.drawBitmap(rouge_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line.equals("rouge_rouge_rouge")) {
                            // Ajout du triplet rouge blanc_blanc dans le dessin
                            canvas.drawBitmap(rouge_rouge_rouge, event.getX(), event.getY(), null);
                        }
                        if (line.equals("blanc_blanc_blanc")) {
                            // Ajout du triplet rouge blanc_blanc dans le dessin
                            canvas.drawBitmap(blanc_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line.equals("vert_vert_vert")) {
                            canvas.drawBitmap(vert_vert_vert, event.getX(), event.getY(), null);
                        }
                        if (line.equals("bleu_blanc_blanc")) {
                            canvas.drawBitmap(bleu_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line.equals("vert_blanc_blanc")) {
                            canvas.drawBitmap(vert_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line.equals("rouge_vert_blanc")) {
                            canvas.drawBitmap(rouge_vert_blanc, event.getX(), event.getY(), null);
                        }
                        if (line.equals("jaune_blanc_blanc")) {
                            canvas.drawBitmap(jaune_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line.equals("blanc_blanc_rouge")) {
                            // Ajout du triplet rouge blanc_blanc dans le dessin
                            canvas.drawBitmap(blanc_blanc_rouge, event.getX(), event.getY(), null);
                        }
                        if (line.equals("blanc_blanc_bleu")) {
                            canvas.drawBitmap(blanc_blanc_bleu, event.getX(), event.getY(), null);
                        }
                        if (line.equals("blanc_blanc_vert")) {
                            canvas.drawBitmap(blanc_blanc_vert, event.getX(), event.getY(), null);
                        }
                        if (line.equals("blanc_vert_rouge")) {
                            canvas.drawBitmap(blanc_vert_rouge, event.getX(), event.getY(), null);
                        }
                        if (line.equals("jaune_blanc_blanc")) {
                            canvas.drawBitmap(blanc_blanc_jaune, event.getX(), event.getY(), null);
                        }
                        if (line.equals("bleu_blanc_blanch")) {
                            canvas.drawBitmap(bleu_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line.equals("bleu_blanc_blancb")) {
                            canvas.drawBitmap(bleu_blanc_blancb, event.getX(), event.getY(), null);
                        }
                        if (line.equals("rouge_blanc_blanch")) {
                            canvas.drawBitmap(rouge_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line.equals("rouge_blanc_blancb")) {
                            canvas.drawBitmap(rouge_blanc_blancb, event.getX(), event.getY(), null);
                        }
                        if (line.equals("blanc_blanc_blanch")) {
                            canvas.drawBitmap(blanc_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line.equals("jaune_blanc_blanch")) {
                            canvas.drawBitmap(jaune_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line.equals("jaune_blanc_blancb")) {
                            canvas.drawBitmap(jaune_blanc_blancb, event.getX(), event.getY(), null);
                        }
                        if (line.equals("vert_blanc_blanch")) {
                            canvas.drawBitmap(vert_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line.equals("vert_blanc_blancb")) {
                            canvas.drawBitmap(vert_blanc_blancb, event.getX(), event.getY(), null);
                        }
                        if (line.equals("vert_vert_verth")) {
                            canvas.drawBitmap(vert_vert_verth, event.getX(), event.getY(), null);
                        }
                        if (line.equals("rouge_vert_blanch")) {
                            canvas.drawBitmap(rouge_vert_blanch, event.getX(), event.getY(), null);
                        }
                        if (line.equals("rouge_vert_blancb")) {
                            canvas.drawBitmap(rouge_vert_blancb, event.getX(), event.getY(), null);
                        }
                        if (line.equals("rouge_rouge_rougeh")) {
                            canvas.drawBitmap(rouge_rouge_rougeh, event.getX(), event.getY(), null);
                        }
                        
                    }
                    //Log.i("ACTION_MOVE", "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
                    
                }
                if(triplet2[nbtriplet2] == 1){
                    final int pointerCount = event.getPointerCount();
                    for (actionIndex = 0; actionIndex < pointerCount; actionIndex++) {
                        // Some pointer has moved, search it by pointer id
                        pointerId = event.getPointerId(actionIndex);

                        //xTouch = event.getX();
                        //yTouch = event.getY();

                        //canvas.drawBitmap(vert_bmp, xTouch , yTouch , null);
                        if (line2.equals("rouge_blanc_blanc")) {
                            // Ajout du triplet rouge blanc_blanc dans le dessin
                            canvas.drawBitmap(rouge_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("rouge_rouge_rouge")) {
                            // Ajout du triplet rouge blanc_blanc dans le dessin
                            canvas.drawBitmap(rouge_rouge_rouge, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("blanc_blanc_blanc")) {
                            // Ajout du triplet rouge blanc_blanc dans le dessin
                            canvas.drawBitmap(blanc_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("vert_vert_vert")) {
                            canvas.drawBitmap(vert_vert_vert, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("bleu_blanc_blanc")) {
                            canvas.drawBitmap(bleu_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("vert_blanc_blanc")) {
                            canvas.drawBitmap(vert_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("rouge_vert_blanc")) {
                            canvas.drawBitmap(rouge_vert_blanc, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("jaune_blanc_blanc")) {
                            canvas.drawBitmap(jaune_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("blanc_blanc_rouge")) {
                            // Ajout du triplet rouge blanc_blanc dans le dessin
                            canvas.drawBitmap(blanc_blanc_rouge, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("blanc_blanc_bleu")) {
                            canvas.drawBitmap(blanc_blanc_bleu, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("blanc_blanc_vert")) {
                            canvas.drawBitmap(blanc_blanc_vert, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("blanc_vert_rouge")) {
                            canvas.drawBitmap(blanc_vert_rouge, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("jaune_blanc_blanc")) {
                            canvas.drawBitmap(blanc_blanc_jaune, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("bleu_blanc_blanch")) {
                            canvas.drawBitmap(bleu_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("bleu_blanc_blancb")) {
                            canvas.drawBitmap(bleu_blanc_blancb, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("rouge_blanc_blanch")) {
                            canvas.drawBitmap(rouge_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("rouge_blanc_blancb")) {
                            canvas.drawBitmap(rouge_blanc_blancb, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("blanc_blanc_blanch")) {
                            canvas.drawBitmap(blanc_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("jaune_blanc_blanch")) {
                            canvas.drawBitmap(jaune_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("jaune_blanc_blancb")) {
                            canvas.drawBitmap(jaune_blanc_blancb, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("vert_blanc_blanch")) {
                            canvas.drawBitmap(vert_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("vert_blanc_blancb")) {
                            canvas.drawBitmap(vert_blanc_blancb, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("vert_vert_verth")) {
                            canvas.drawBitmap(vert_vert_verth, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("rouge_vert_blanch")) {
                            canvas.drawBitmap(rouge_vert_blanch, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("rouge_vert_blancb")) {
                            canvas.drawBitmap(rouge_vert_blancb, event.getX(), event.getY(), null);
                        }
                        if (line2.equals("rouge_rouge_rougeh")) {
                            canvas.drawBitmap(rouge_rouge_rougeh, event.getX(), event.getY(), null);
                        }

                    }
                    //Log.i("ACTION_MOVE", "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");

                }
                if(triplet3[nbtriplet3] == 1){
                    final int pointerCount = event.getPointerCount();
                    for (actionIndex = 0; actionIndex < pointerCount; actionIndex++) {
                        // Some pointer has moved, search it by pointer id
                        pointerId = event.getPointerId(actionIndex);

                        //xTouch = event.getX();
                        //yTouch = event.getY();

                        //canvas.drawBitmap(vert_bmp, xTouch , yTouch , null);
                        if (line3.equals("rouge_blanc_blanc")) {
                            // Ajout du triplet rouge blanc_blanc dans le dessin
                            canvas.drawBitmap(rouge_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("rouge_rouge_rouge")) {
                            // Ajout du triplet rouge blanc_blanc dans le dessin
                            canvas.drawBitmap(rouge_rouge_rouge, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("blanc_blanc_blanc")) {
                            // Ajout du triplet rouge blanc_blanc dans le dessin
                            canvas.drawBitmap(blanc_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("vert_vert_vert")) {
                            canvas.drawBitmap(vert_vert_vert, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("bleu_blanc_blanc")) {
                            canvas.drawBitmap(bleu_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("vert_blanc_blanc")) {
                            canvas.drawBitmap(vert_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("rouge_vert_blanc")) {
                            canvas.drawBitmap(rouge_vert_blanc, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("jaune_blanc_blanc")) {
                            canvas.drawBitmap(jaune_blanc_blanc, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("blanc_blanc_rouge")) {
                            // Ajout du triplet rouge blanc_blanc dans le dessin
                            canvas.drawBitmap(blanc_blanc_rouge, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("blanc_blanc_bleu")) {
                            canvas.drawBitmap(blanc_blanc_bleu, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("blanc_blanc_vert")) {
                            canvas.drawBitmap(blanc_blanc_vert, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("blanc_vert_rouge")) {
                            canvas.drawBitmap(blanc_vert_rouge, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("jaune_blanc_blanc")) {
                            canvas.drawBitmap(blanc_blanc_jaune, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("bleu_blanc_blanch")) {
                            canvas.drawBitmap(bleu_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("bleu_blanc_blancb")) {
                            canvas.drawBitmap(bleu_blanc_blancb, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("rouge_blanc_blanch")) {
                            canvas.drawBitmap(rouge_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("rouge_blanc_blancb")) {
                            canvas.drawBitmap(rouge_blanc_blancb, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("blanc_blanc_blanch")) {
                            canvas.drawBitmap(blanc_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("jaune_blanc_blanch")) {
                            canvas.drawBitmap(jaune_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("jaune_blanc_blancb")) {
                            canvas.drawBitmap(jaune_blanc_blancb, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("vert_blanc_blanch")) {
                            canvas.drawBitmap(vert_blanc_blanch, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("vert_blanc_blancb")) {
                            canvas.drawBitmap(vert_blanc_blancb, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("vert_vert_verth")) {
                            canvas.drawBitmap(vert_vert_verth, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("rouge_vert_blanch")) {
                            canvas.drawBitmap(rouge_vert_blanch, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("rouge_vert_blancb")) {
                            canvas.drawBitmap(rouge_vert_blancb, event.getX(), event.getY(), null);
                        }
                        if (line3.equals("rouge_rouge_rougeh")) {
                            canvas.drawBitmap(rouge_rouge_rougeh, event.getX(), event.getY(), null);
                        }

                    }
                    //Log.i("ACTION_MOVE", "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");

                }
                //selection_triplet1 = 2;
                //Log.i("ACTION_MOVE", "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
                break;
                
                
            case MotionEvent.ACTION_UP:
                
                
                if(triplet[nbtriplet] == 1) {
                    if (event.getX() >= carteLeftAnchor + 20 && event.getX() <= carteLeftAnchor + 130 && event.getY() >= carteTopAnchor + 200 && event.getY() <= carteTopAnchor + 335) {

                        nbclick[nbtriplet] += 1;
                        if (nbclick[nbtriplet] > 3) {
                            nbclick[nbtriplet] = 0;

                        }

                        if(nbclick[nbtriplet] == 0)
                        {

                            postriplet[nbtriplet] = 0;

                        if (line.equals("bleu_blanc_blancb")) {
                            line = "bleu_blanc_blanc";


                            Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                        }
                        if (line.equals("rouge_blanc_blancb")) {
                            line = "rouge_blanc_blanc";

                        }

                        if (line.equals("vert_blanc_blancb")) {
                            line = "vert_blanc_blanc";
                        }

                        if (line.equals("vert_vert_verth")) {
                            line = "vert_vert_vert";
                        }
                        if (line.equals("rouge_vert_blancb")) {
                            line = "rouge_vert_blanc";
                        }
                        if (line.equals("rouge_rouge_rougeh")) {
                            line = "rouge_rouge_rouge";
                        }
                        if (line.equals("blanc_blanc_blanch")) {
                            line = "blanc_blanc_blanc";
                        }
                        if (line.equals("jaune_blanc_blancb")) {
                            line = "jaune_blanc_blanc";
                        }
                    }
                        if(nbclick[nbtriplet]==1) {
                            
                            if (line.equals("rouge_blanc_blanc")) {
                                line = "blanc_blanc_rouge";
                                
                                Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                            }
                            
                            if (line.equals("blanc_blanc_blanc")) {
                                line = "blanc_blanc_blanc";
                                
                                Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                            }
                            if (line.equals("bleu_blanc_blanc")) {
                                line = "blanc_blanc_bleu";
                                
                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line.equals("jaune_blanc_blanc")) {
                                line = "blanc_blanc_jaune";
                                
                                Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                            }
                            if (line.equals("vert_blanc_blanc")) {
                                line = "blanc_blanc_vert";
                                
                                Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                            }
                            if (line.equals("rouge_vert_blanc")) {
                                line = "blanc_vert_rouge";
                                
                                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                            }
                            if (line.equals("vert_vert_vert")) {
                                line = "vert_vert_vert";
                                
                                
                                Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                            }
                            if (line.equals("rouge_rouge_rouge")) {
                                line = "rouge_rouge_rouge";
                                Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                            }
                            
                            
                            Log.i("Coordonnées", "ydffdsfdsfdsfdsfdsffdsfdsfdsfdsfdsfdsfdsfdsfdiiiiiiiiiiiiiiiiiiiiiiiii" + toString().valueOf(nbclick[nbtriplet]));
                        }
                        if(nbclick[nbtriplet]==2) {
                            
                            postriplet[nbtriplet] = 1;
                            if (line.equals("blanc_blanc_bleu")) {
                                line = "bleu_blanc_blanch";
                                
                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line.equals("blanc_blanc_rouge")) {
                                line = "rouge_blanc_blanch";;
                            }
                            
                            if (line.equals("blanc_blanc_vert")) {
                                line = "vert_blanc_blanch";
                            }
                            
                            if (line.equals("vert_vert_vert")) {
                                line = "vert_vert_verth" ;
                            }
                            if (line.equals("blanc_vert_rouge")) {
                                line = "rouge_vert_blanch";
                            }
                            if (line.equals("rouge_rouge_rouge")) {
                                line = "rouge_rouge_rouge";
                            }
                            if (line.equals("blanc_blanc_blanc")) {
                                line = "blanc_blanc_blanch";
                            }
                            if (line.equals("blanc_blanc_jaune")) {
                                line = "jaune_blanc_blanch";
                            }
                            
                            
                            Log.i("Coordonnées", "ydffdsfdsfdsfdsfdsffdsfdsfdsfdsfdsfdsfdsfdsfdiiiiiiiiiiiiiiiiiiiiiiiii" + toString().valueOf(nbclick[nbtriplet]));
                        }
                        if(nbclick[nbtriplet]==3) {

                            postriplet[nbtriplet] = 1;
                            if (line.equals("bleu_blanc_blanch")) {
                                line = "bleu_blanc_blancb";

                                
                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line.equals("rouge_blanc_blanch")) {
                                line = "rouge_blanc_blancb";;
                            }
                            
                            if (line.equals("vert_blanc_blanch")) {
                                line = "vert_blanc_blancb";
                            }
                            
                            if (line.equals("vert_vert_verth")) {
                                line = "vert_vert_verth" ;
                            }
                            if (line.equals("rouge_vert_blanch")) {
                                line = "rouge_vert_blancb";
                            }
                            if (line.equals("rouge_rouge_rougeh")) {
                                line = "rouge_rouge_rougeh";
                            }
                            if (line.equals("blanc_blanc_blanch")) {
                                line = "blanc_blanc_blanch";
                            }
                            if (line.equals("jaune_blanc_blanch")) {
                                line = "jaune_blanc_blancb";
                            }

                            Log.i("Coordonnées", "ydffdsfdsfdsfdsfdsffdsfdsfdsfdsfdsfdsfdsfdsfdiiiiiiiiiiiiiiiiiiiiiiiii" + toString().valueOf(nbclick[nbtriplet]));
                        }
                        
                    }

                    //                    if (event.getX() >= carteLeftAnchor+20 && event.getX() <= carteLeftAnchor+130 && event.getY() >= carteTopAnchor+200 && event.getY() <= carteTopAnchor+335)
                    
                    if (event.getX() >= carteLeftAnchor - 5 && event.getX() <= carteLeftAnchor + 150 && event.getY() >= carteTopAnchor && event.getY() <= carteTopAnchor + 175) {
                        refTriplet[nbtriplet][0] = Math.round(event.getY() / carteTileSize) - 7;
                        refTriplet[nbtriplet][1] = Math.round(event.getX() / carteTileSize) - 3;
                        if(postriplet[nbtriplet] == 0) {

                            //Log.i("okokpjj","fjdfjdsfjdsfjdsfdskjlfdjsklfkdsfkdsfgdgdsgdsgdsgdsgds"+String.valueOf(son));
                            if (carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] == 4 && carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] == 4 && carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] == 4) {

                                if(isclickedson == true)
                                mp.start();
                                if (line.equals("rouge_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line.equals("rouge_rouge_rouge")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = rouge_num;
                                }
                                if (line.equals("blanc_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line.equals("vert_vert_vert")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = vert_num;
                                }
                                if (line.equals("bleu_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = bleu_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line.equals("vert_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line.equals("rouge_vert_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line.equals("jaune_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = jaune_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line.equals("blanc_blanc_rouge")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = rouge_num;
                                }


                                if (line.equals("blanc_blanc_bleu")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = bleu_num;
                                }
                                if (line.equals("blanc_blanc_vert")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = vert_num;
                                }
                                if (line.equals("blanc_vert_rouge")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = rouge_num;
                                }
                                if (line.equals("blanc_blanc_jaune")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = jaune_num;
                                }
                                triplet[nbtriplet] = 2;
                                // On prend le triplet suivant
                                nbtriplet++;

                                endSearch = true;
                                if (endSearch) {
                                    Log.i("", "endSearch");
                                }
                            } else {


                                if (line.equals("rouge_blanc_blanc")) {
                                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                }


                                if (line.equals("blanc_blanc_blanc")) {
                                    canvas.drawBitmap(blanc_blanc_blanc, (getWidth() - blanc_blanc_blanc.getWidth()) / 2, getHeight() - blanc_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                                }
                                if (line.equals("bleu_blanc_blanc")) {
                                    canvas.drawBitmap(bleu_blanc_blanc, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                }
                                if (line.equals("jaune_blanc_blanc")) {
                                    canvas.drawBitmap(jaune_blanc_blanc, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                }
                                if (line.equals("vert_blanc_blanc")) {
                                    canvas.drawBitmap(vert_blanc_blanc, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                }
                                if (line.equals("rouge_vert_blanc")) {
                                    canvas.drawBitmap(rouge_vert_blanc, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("vert_vert_vert")) {
                                    canvas.drawBitmap(vert_vert_vert, (getWidth() - vert_vert_vert.getWidth()) / 2, getHeight() - vert_vert_vert.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                                }
                                if (line.equals("rouge_rouge_rouge")) {
                                    canvas.drawBitmap(rouge_rouge_rouge, (getWidth() - rouge_rouge_rouge.getWidth()) / 2, getHeight() - rouge_rouge_rouge.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                                }
                                if (line.equals("blanc_blanc_rouge")) {
                                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                }

                                if (line.equals("blanc_blanc_bleu")) {
                                    canvas.drawBitmap(blanc_blanc_bleu, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                }
                                if (line.equals("blanc_blanc_jaune")) {
                                    canvas.drawBitmap(blanc_blanc_jaune, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                }
                                if (line.equals("blanc_blanc_vert")) {
                                    canvas.drawBitmap(blanc_blanc_vert, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                }
                                if (line.equals("blanc_vert_rouge")) {
                                    canvas.drawBitmap(blanc_vert_rouge, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("bleu_blanc_blanch")) {
                                    canvas.drawBitmap(bleu_blanc_blanch, (getWidth() - bleu_blanc_blanch.getWidth()) / 2, getHeight() - bleu_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("bleu_blanc_blancb")) {
                                    canvas.drawBitmap(bleu_blanc_blancb, (getWidth() - bleu_blanc_blancb.getWidth()) / 2, getHeight() - bleu_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_blanc_blanch")) {
                                    canvas.drawBitmap(rouge_blanc_blanch, (getWidth() - rouge_blanc_blanch.getWidth()) / 2, getHeight() - rouge_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_blanc_blancb")) {
                                    canvas.drawBitmap(rouge_blanc_blancb, (getWidth() - rouge_blanc_blancb.getWidth()) / 2, getHeight() - rouge_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("blanc_blanc_blanch")) {
                                    canvas.drawBitmap(blanc_blanc_blanch, (getWidth() - blanc_blanc_blanch.getWidth()) / 2, getHeight() - blanc_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("jaune_blanc_blanch")) {
                                    canvas.drawBitmap(jaune_blanc_blanch, (getWidth() - jaune_blanc_blanch.getWidth()) / 2, getHeight() - jaune_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("jaune_blanc_blancb")) {
                                    canvas.drawBitmap(jaune_blanc_blancb, (getWidth() - jaune_blanc_blancb.getWidth()) / 2, getHeight() - jaune_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("vert_blanc_blanch")) {
                                    canvas.drawBitmap(vert_blanc_blanch, (getWidth() - vert_blanc_blanch.getWidth()) / 2, getHeight() - vert_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("vert_blanc_blancb")) {
                                    canvas.drawBitmap(vert_blanc_blancb, (getWidth() - vert_blanc_blancb.getWidth()) / 2, getHeight() - vert_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("vert_vert_verth")) {
                                    canvas.drawBitmap(vert_vert_verth, (getWidth() - vert_vert_verth.getWidth()) / 2, getHeight() - vert_vert_verth.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_vert_blanch")) {
                                    canvas.drawBitmap(rouge_vert_blanch, (getWidth() - rouge_vert_blanch.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_vert_blancb")) {
                                    canvas.drawBitmap(rouge_vert_blancb, (getWidth() - rouge_vert_blancb.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_rouge_rougeh")) {
                                    canvas.drawBitmap(rouge_rouge_rougeh, (getWidth() - rouge_rouge_rougeh.getWidth()) / 2, getHeight() - rouge_rouge_rougeh.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }


                            }
                        }
                             if (postriplet[nbtriplet] == 1) {


                                if (carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] == 4 && carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] == 4 && carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] == 4) {
                                    if(isclickedson == true)
                                        mp.start();
                                    if (line.equals("bleu_blanc_blanch")) {
                                        // Ajout du triplet rouge blanc_blanc dans le dessin
                                        carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = bleu_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    }
                                    if (line.equals("bleu_blanc_blancb")) {
                                        // Ajout du triplet rouge blanc_blanc dans le dessin
                                        carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = bleu_num;
                                    }

                                    if (line.equals("rouge_blanc_blanch")) {
                                        // Ajout du triplet rouge blanc_blanc dans le dessin
                                        carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    }
                                    if (line.equals("rouge_blanc_blancb")) {
                                        // Ajout du triplet rouge blanc_blanc dans le dessin
                                        carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    }

                                    if (line.equals("vert_blanc_blancb")) {
                                        // Ajout du triplet rouge blanc_blanc dans le dessin
                                        carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    }
                                    if (line.equals("vert_blanc_blanch")) {
                                        // Ajout du triplet rouge blanc_blanc dans le dessin
                                        carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    }
                                    if (line.equals("jaune_blanc_blancb")) {
                                        // Ajout du triplet rouge blanc_blanc dans le dessin
                                        carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = jaune_num;
                                    }
                                    if (line.equals("jaune_blanc_blanch")) {
                                        // Ajout du triplet rouge blanc_blanc dans le dessin
                                        carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = jaune_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    }

                                    if (line.equals("vert_vert_verth")) {
                                        // Ajout du triplet rouge blanc_blanc dans le dessin
                                        carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    }

                                    if (line.equals("rouge_vert_blanch")) {
                                        // Ajout du triplet rouge blanc_blanc dans le dessin
                                        carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    }
                                    if (line.equals("rouge_vert_blancb")) {
                                        // Ajout du triplet rouge blanc_blanc dans le dessin
                                        carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    }
                                    if (line.equals("rouge_rouge_rougeh")) {
                                        // Ajout du triplet rouge blanc_blanc dans le dessin
                                        carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    }
                                    if (line.equals("blanc_blanc_blanch")) {
                                        // Ajout du triplet rouge blanc_blanc dans le dessin
                                        carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                        carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    }
                                    triplet[nbtriplet] = 2;
                                    // On prend le triplet suivant
                                    nbtriplet++;

                                    endSearch = true;
                                    if (endSearch) {
                                        Log.i("", "endSearch");
                                    }
                                }
                                else {


                                    if (line.equals("rouge_blanc_blanc")) {
                                        canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                    }


                                    if (line.equals("blanc_blanc_blanc")) {
                                        canvas.drawBitmap(blanc_blanc_blanc, (getWidth() - blanc_blanc_blanc.getWidth()) / 2, getHeight() - blanc_blanc_blanc.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                                    }
                                    if (line.equals("bleu_blanc_blanc")) {
                                        canvas.drawBitmap(bleu_blanc_blanc, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                    }
                                    if (line.equals("jaune_blanc_blanc")) {
                                        canvas.drawBitmap(jaune_blanc_blanc, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                    }
                                    if (line.equals("vert_blanc_blanc")) {
                                        canvas.drawBitmap(vert_blanc_blanc, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                    }
                                    if (line.equals("rouge_vert_blanc")) {
                                        canvas.drawBitmap(rouge_vert_blanc, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }
                                    if (line.equals("vert_vert_vert")) {
                                        canvas.drawBitmap(vert_vert_vert, (getWidth() - vert_vert_vert.getWidth()) / 2, getHeight() - vert_vert_vert.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                                    }
                                    if (line.equals("rouge_rouge_rouge")) {
                                        canvas.drawBitmap(rouge_rouge_rouge, (getWidth() - rouge_rouge_rouge.getWidth()) / 2, getHeight() - rouge_rouge_rouge.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                                    }
                                    if (line.equals("blanc_blanc_rouge")) {
                                        canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                    }

                                    if (line.equals("blanc_blanc_bleu")) {
                                        canvas.drawBitmap(blanc_blanc_bleu, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                    }
                                    if (line.equals("blanc_blanc_jaune")) {
                                        canvas.drawBitmap(blanc_blanc_jaune, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                    }
                                    if (line.equals("blanc_blanc_vert")) {
                                        canvas.drawBitmap(blanc_blanc_vert, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                    }
                                    if (line.equals("blanc_vert_rouge")) {
                                        canvas.drawBitmap(blanc_vert_rouge, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }
                                    if (line.equals("bleu_blanc_blanch")) {
                                        canvas.drawBitmap(bleu_blanc_blanch, (getWidth() - bleu_blanc_blanch.getWidth()) / 2, getHeight() - bleu_blanc_blanch.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }
                                    if (line.equals("bleu_blanc_blancb")) {
                                        canvas.drawBitmap(bleu_blanc_blancb, (getWidth() - bleu_blanc_blancb.getWidth()) / 2, getHeight() - bleu_blanc_blancb.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }
                                    if (line.equals("rouge_blanc_blanch")) {
                                        canvas.drawBitmap(rouge_blanc_blanch, (getWidth() - rouge_blanc_blanch.getWidth()) / 2, getHeight() - rouge_blanc_blanch.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }
                                    if (line.equals("rouge_blanc_blancb")) {
                                        canvas.drawBitmap(rouge_blanc_blancb, (getWidth() - rouge_blanc_blancb.getWidth()) / 2, getHeight() - rouge_blanc_blancb.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }
                                    if (line.equals("blanc_blanc_blanch")) {
                                        canvas.drawBitmap(blanc_blanc_blanch, (getWidth() - blanc_blanc_blanch.getWidth()) / 2, getHeight() - blanc_blanc_blanch.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }
                                    if (line.equals("jaune_blanc_blanch")) {
                                        canvas.drawBitmap(jaune_blanc_blanch, (getWidth() - jaune_blanc_blanch.getWidth()) / 2, getHeight() - jaune_blanc_blanch.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }
                                    if (line.equals("jaune_blanc_blancb")) {
                                        canvas.drawBitmap(jaune_blanc_blancb, (getWidth() - jaune_blanc_blancb.getWidth()) / 2, getHeight() - jaune_blanc_blancb.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }
                                    if (line.equals("vert_blanc_blanch")) {
                                        canvas.drawBitmap(vert_blanc_blanch, (getWidth() - vert_blanc_blanch.getWidth()) / 2, getHeight() - vert_blanc_blanch.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }
                                    if (line.equals("vert_blanc_blancb")) {
                                        canvas.drawBitmap(vert_blanc_blancb, (getWidth() - vert_blanc_blancb.getWidth()) / 2, getHeight() - vert_blanc_blancb.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }
                                    if (line.equals("vert_vert_verth")) {
                                        canvas.drawBitmap(vert_vert_verth, (getWidth() - vert_vert_verth.getWidth()) / 2, getHeight() - vert_vert_verth.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }
                                    if (line.equals("rouge_vert_blanch")) {
                                        canvas.drawBitmap(rouge_vert_blanch, (getWidth() - rouge_vert_blanch.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }
                                    if (line.equals("rouge_vert_blancb")) {
                                        canvas.drawBitmap(rouge_vert_blancb, (getWidth() - rouge_vert_blancb.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }
                                    if (line.equals("rouge_rouge_rougeh")) {
                                        canvas.drawBitmap(rouge_rouge_rougeh, (getWidth() - rouge_rouge_rougeh.getWidth()) / 2, getHeight() - rouge_rouge_rougeh.getHeight(), null);
                                        Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                    }


                                }
                            }
                        }
                        

                        
                        // Log.i("-> FCT <-", "onTouchEvent: " + event.getX());
                        
                        // Permet de recuperer les evenements tactiles
                        // ACTION_UP
                        //
                        // ACTION_MOVE
                        
                        // test pour gerer le cliquer-glisser
                        
                        //}
                        

                        
                        //canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                        

                }

                if(triplet[nbtriplet] == 1) {
                    if (event.getX() >= carteLeftAnchor + 20 && event.getX() <= carteLeftAnchor + 130 && event.getY() >= carteTopAnchor + 200 && event.getY() <= carteTopAnchor + 335) {

                        nbclick[nbtriplet] += 1;
                        if (nbclick[nbtriplet] > 3) {
                            nbclick[nbtriplet] = 0;

                        }

                        if(nbclick[nbtriplet] == 0)
                        {

                            postriplet[nbtriplet] = 0;

                            if (line.equals("bleu_blanc_blancb")) {
                                line = "bleu_blanc_blanc";


                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line.equals("rouge_blanc_blancb")) {
                                line = "rouge_blanc_blanc";

                            }

                            if (line.equals("vert_blanc_blancb")) {
                                line = "vert_blanc_blanc";
                            }

                            if (line.equals("vert_vert_verth")) {
                                line = "vert_vert_vert";
                            }
                            if (line.equals("rouge_vert_blancb")) {
                                line = "rouge_vert_blanc";
                            }
                            if (line.equals("rouge_rouge_rougeh")) {
                                line = "rouge_rouge_rouge";
                            }
                            if (line.equals("blanc_blanc_blanch")) {
                                line = "blanc_blanc_blanc";
                            }
                            if (line.equals("jaune_blanc_blancb")) {
                                line = "jaune_blanc_blanc";
                            }
                        }
                        if(nbclick[nbtriplet]==1) {

                            if (line.equals("rouge_blanc_blanc")) {
                                line = "blanc_blanc_rouge";

                                Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                            }

                            if (line.equals("blanc_blanc_blanc")) {
                                line = "blanc_blanc_blanc";

                                Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                            }
                            if (line.equals("bleu_blanc_blanc")) {
                                line = "blanc_blanc_bleu";

                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line.equals("jaune_blanc_blanc")) {
                                line = "blanc_blanc_jaune";

                                Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                            }
                            if (line.equals("vert_blanc_blanc")) {
                                line = "blanc_blanc_vert";

                                Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                            }
                            if (line.equals("rouge_vert_blanc")) {
                                line = "blanc_vert_rouge";

                                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                            }
                            if (line.equals("vert_vert_vert")) {
                                line = "vert_vert_vert";


                                Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                            }
                            if (line.equals("rouge_rouge_rouge")) {
                                line = "rouge_rouge_rouge";
                                Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                            }


                            Log.i("Coordonnées", "ydffdsfdsfdsfdsfdsffdsfdsfdsfdsfdsfdsfdsfdsfdiiiiiiiiiiiiiiiiiiiiiiiii" + toString().valueOf(nbclick[nbtriplet]));
                        }
                        if(nbclick[nbtriplet]==2) {

                            postriplet[nbtriplet] = 1;
                            if (line.equals("blanc_blanc_bleu")) {
                                line = "bleu_blanc_blanch";

                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line.equals("blanc_blanc_rouge")) {
                                line = "rouge_blanc_blanch";;
                            }

                            if (line.equals("blanc_blanc_vert")) {
                                line = "vert_blanc_blanch";
                            }

                            if (line.equals("vert_vert_vert")) {
                                line = "vert_vert_verth" ;
                            }
                            if (line.equals("blanc_vert_rouge")) {
                                line = "rouge_vert_blanch";
                            }
                            if (line.equals("rouge_rouge_rouge")) {
                                line = "rouge_rouge_rouge";
                            }
                            if (line.equals("blanc_blanc_blanc")) {
                                line = "blanc_blanc_blanch";
                            }
                            if (line.equals("blanc_blanc_jaune")) {
                                line = "jaune_blanc_blanch";
                            }


                            Log.i("Coordonnées", "ydffdsfdsfdsfdsfdsffdsfdsfdsfdsfdsfdsfdsfdsfdiiiiiiiiiiiiiiiiiiiiiiiii" + toString().valueOf(nbclick[nbtriplet]));
                        }
                        if(nbclick[nbtriplet]==3) {

                            postriplet[nbtriplet] = 1;
                            if (line.equals("bleu_blanc_blanch")) {
                                line = "bleu_blanc_blancb";


                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line.equals("rouge_blanc_blanch")) {
                                line = "rouge_blanc_blancb";;
                            }

                            if (line.equals("vert_blanc_blanch")) {
                                line = "vert_blanc_blancb";
                            }

                            if (line.equals("vert_vert_verth")) {
                                line = "vert_vert_verth" ;
                            }
                            if (line.equals("rouge_vert_blanch")) {
                                line = "rouge_vert_blancb";
                            }
                            if (line.equals("rouge_rouge_rougeh")) {
                                line = "rouge_rouge_rougeh";
                            }
                            if (line.equals("blanc_blanc_blanch")) {
                                line = "blanc_blanc_blanch";
                            }
                            if (line.equals("jaune_blanc_blanch")) {
                                line = "jaune_blanc_blancb";
                            }

                            Log.i("Coordonnées", "ydffdsfdsfdsfdsfdsffdsfdsfdsfdsfdsfdsfdsfdsfdiiiiiiiiiiiiiiiiiiiiiiiii" + toString().valueOf(nbclick[nbtriplet]));
                        }

                    }

                    //                    if (event.getX() >= carteLeftAnchor+20 && event.getX() <= carteLeftAnchor+130 && event.getY() >= carteTopAnchor+200 && event.getY() <= carteTopAnchor+335)

                    if (event.getX() >= carteLeftAnchor - 5 && event.getX() <= carteLeftAnchor + 150 && event.getY() >= carteTopAnchor && event.getY() <= carteTopAnchor + 175) {
                        refTriplet[nbtriplet][0] = Math.round(event.getY() / carteTileSize) - 7;
                        refTriplet[nbtriplet][1] = Math.round(event.getX() / carteTileSize) - 3;
                        if(postriplet[nbtriplet] == 0) {

                            //Log.i("okokpjj","fjdfjdsfjdsfjdsfdskjlfdjsklfkdsfkdsfgdgdsgdsgdsgdsgds"+String.valueOf(son));
                            if (carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] == 4 && carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] == 4 && carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] == 4) {

                                if(isclickedson == true)
                                    mp.start();
                                if (line.equals("rouge_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line.equals("rouge_rouge_rouge")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = rouge_num;
                                }
                                if (line.equals("blanc_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line.equals("vert_vert_vert")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = vert_num;
                                }
                                if (line.equals("bleu_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = bleu_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line.equals("vert_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line.equals("rouge_vert_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line.equals("jaune_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = jaune_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line.equals("blanc_blanc_rouge")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = rouge_num;
                                }


                                if (line.equals("blanc_blanc_bleu")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = bleu_num;
                                }
                                if (line.equals("blanc_blanc_vert")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = vert_num;
                                }
                                if (line.equals("blanc_vert_rouge")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = rouge_num;
                                }
                                if (line.equals("blanc_blanc_jaune")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = jaune_num;
                                }
                                triplet[nbtriplet] = 2;
                                // On prend le triplet suivant
                                nbtriplet++;

                                endSearch = true;
                                if (endSearch) {
                                    Log.i("", "endSearch");
                                }
                            } else {


                                if (line.equals("rouge_blanc_blanc")) {
                                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                }


                                if (line.equals("blanc_blanc_blanc")) {
                                    canvas.drawBitmap(blanc_blanc_blanc, (getWidth() - blanc_blanc_blanc.getWidth()) / 2, getHeight() - blanc_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                                }
                                if (line.equals("bleu_blanc_blanc")) {
                                    canvas.drawBitmap(bleu_blanc_blanc, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                }
                                if (line.equals("jaune_blanc_blanc")) {
                                    canvas.drawBitmap(jaune_blanc_blanc, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                }
                                if (line.equals("vert_blanc_blanc")) {
                                    canvas.drawBitmap(vert_blanc_blanc, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                }
                                if (line.equals("rouge_vert_blanc")) {
                                    canvas.drawBitmap(rouge_vert_blanc, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("vert_vert_vert")) {
                                    canvas.drawBitmap(vert_vert_vert, (getWidth() - vert_vert_vert.getWidth()) / 2, getHeight() - vert_vert_vert.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                                }
                                if (line.equals("rouge_rouge_rouge")) {
                                    canvas.drawBitmap(rouge_rouge_rouge, (getWidth() - rouge_rouge_rouge.getWidth()) / 2, getHeight() - rouge_rouge_rouge.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                                }
                                if (line.equals("blanc_blanc_rouge")) {
                                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                }

                                if (line.equals("blanc_blanc_bleu")) {
                                    canvas.drawBitmap(blanc_blanc_bleu, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                }
                                if (line.equals("blanc_blanc_jaune")) {
                                    canvas.drawBitmap(blanc_blanc_jaune, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                }
                                if (line.equals("blanc_blanc_vert")) {
                                    canvas.drawBitmap(blanc_blanc_vert, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                }
                                if (line.equals("blanc_vert_rouge")) {
                                    canvas.drawBitmap(blanc_vert_rouge, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("bleu_blanc_blanch")) {
                                    canvas.drawBitmap(bleu_blanc_blanch, (getWidth() - bleu_blanc_blanch.getWidth()) / 2, getHeight() - bleu_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("bleu_blanc_blancb")) {
                                    canvas.drawBitmap(bleu_blanc_blancb, (getWidth() - bleu_blanc_blancb.getWidth()) / 2, getHeight() - bleu_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_blanc_blanch")) {
                                    canvas.drawBitmap(rouge_blanc_blanch, (getWidth() - rouge_blanc_blanch.getWidth()) / 2, getHeight() - rouge_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_blanc_blancb")) {
                                    canvas.drawBitmap(rouge_blanc_blancb, (getWidth() - rouge_blanc_blancb.getWidth()) / 2, getHeight() - rouge_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("blanc_blanc_blanch")) {
                                    canvas.drawBitmap(blanc_blanc_blanch, (getWidth() - blanc_blanc_blanch.getWidth()) / 2, getHeight() - blanc_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("jaune_blanc_blanch")) {
                                    canvas.drawBitmap(jaune_blanc_blanch, (getWidth() - jaune_blanc_blanch.getWidth()) / 2, getHeight() - jaune_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("jaune_blanc_blancb")) {
                                    canvas.drawBitmap(jaune_blanc_blancb, (getWidth() - jaune_blanc_blancb.getWidth()) / 2, getHeight() - jaune_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("vert_blanc_blanch")) {
                                    canvas.drawBitmap(vert_blanc_blanch, (getWidth() - vert_blanc_blanch.getWidth()) / 2, getHeight() - vert_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("vert_blanc_blancb")) {
                                    canvas.drawBitmap(vert_blanc_blancb, (getWidth() - vert_blanc_blancb.getWidth()) / 2, getHeight() - vert_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("vert_vert_verth")) {
                                    canvas.drawBitmap(vert_vert_verth, (getWidth() - vert_vert_verth.getWidth()) / 2, getHeight() - vert_vert_verth.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_vert_blanch")) {
                                    canvas.drawBitmap(rouge_vert_blanch, (getWidth() - rouge_vert_blanch.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_vert_blancb")) {
                                    canvas.drawBitmap(rouge_vert_blancb, (getWidth() - rouge_vert_blancb.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_rouge_rougeh")) {
                                    canvas.drawBitmap(rouge_rouge_rougeh, (getWidth() - rouge_rouge_rougeh.getWidth()) / 2, getHeight() - rouge_rouge_rougeh.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }


                            }
                        }
                        if (postriplet[nbtriplet] == 1) {


                            if (carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] == 4 && carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] == 4 && carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] == 4) {
                                if(isclickedson == true)
                                    mp.start();
                                if (line.equals("bleu_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = bleu_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                if (line.equals("bleu_blanc_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = bleu_num;
                                }

                                if (line.equals("rouge_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                if (line.equals("rouge_blanc_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                }

                                if (line.equals("vert_blanc_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                }
                                if (line.equals("vert_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                if (line.equals("jaune_blanc_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = jaune_num;
                                }
                                if (line.equals("jaune_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = jaune_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }

                                if (line.equals("vert_vert_verth")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                }

                                if (line.equals("rouge_vert_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                if (line.equals("rouge_vert_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                }
                                if (line.equals("rouge_rouge_rougeh")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                }
                                if (line.equals("blanc_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                triplet[nbtriplet] = 2;
                                // On prend le triplet suivant
                                nbtriplet++;

                                endSearch = true;
                                if (endSearch) {
                                    Log.i("", "endSearch");
                                }
                            }
                            else {


                                if (line.equals("rouge_blanc_blanc")) {
                                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                }


                                if (line.equals("blanc_blanc_blanc")) {
                                    canvas.drawBitmap(blanc_blanc_blanc, (getWidth() - blanc_blanc_blanc.getWidth()) / 2, getHeight() - blanc_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                                }
                                if (line.equals("bleu_blanc_blanc")) {
                                    canvas.drawBitmap(bleu_blanc_blanc, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                }
                                if (line.equals("jaune_blanc_blanc")) {
                                    canvas.drawBitmap(jaune_blanc_blanc, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                }
                                if (line.equals("vert_blanc_blanc")) {
                                    canvas.drawBitmap(vert_blanc_blanc, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                }
                                if (line.equals("rouge_vert_blanc")) {
                                    canvas.drawBitmap(rouge_vert_blanc, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("vert_vert_vert")) {
                                    canvas.drawBitmap(vert_vert_vert, (getWidth() - vert_vert_vert.getWidth()) / 2, getHeight() - vert_vert_vert.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                                }
                                if (line.equals("rouge_rouge_rouge")) {
                                    canvas.drawBitmap(rouge_rouge_rouge, (getWidth() - rouge_rouge_rouge.getWidth()) / 2, getHeight() - rouge_rouge_rouge.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                                }
                                if (line.equals("blanc_blanc_rouge")) {
                                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                }

                                if (line.equals("blanc_blanc_bleu")) {
                                    canvas.drawBitmap(blanc_blanc_bleu, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                }
                                if (line.equals("blanc_blanc_jaune")) {
                                    canvas.drawBitmap(blanc_blanc_jaune, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                }
                                if (line.equals("blanc_blanc_vert")) {
                                    canvas.drawBitmap(blanc_blanc_vert, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                }
                                if (line.equals("blanc_vert_rouge")) {
                                    canvas.drawBitmap(blanc_vert_rouge, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("bleu_blanc_blanch")) {
                                    canvas.drawBitmap(bleu_blanc_blanch, (getWidth() - bleu_blanc_blanch.getWidth()) / 2, getHeight() - bleu_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("bleu_blanc_blancb")) {
                                    canvas.drawBitmap(bleu_blanc_blancb, (getWidth() - bleu_blanc_blancb.getWidth()) / 2, getHeight() - bleu_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_blanc_blanch")) {
                                    canvas.drawBitmap(rouge_blanc_blanch, (getWidth() - rouge_blanc_blanch.getWidth()) / 2, getHeight() - rouge_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_blanc_blancb")) {
                                    canvas.drawBitmap(rouge_blanc_blancb, (getWidth() - rouge_blanc_blancb.getWidth()) / 2, getHeight() - rouge_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("blanc_blanc_blanch")) {
                                    canvas.drawBitmap(blanc_blanc_blanch, (getWidth() - blanc_blanc_blanch.getWidth()) / 2, getHeight() - blanc_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("jaune_blanc_blanch")) {
                                    canvas.drawBitmap(jaune_blanc_blanch, (getWidth() - jaune_blanc_blanch.getWidth()) / 2, getHeight() - jaune_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("jaune_blanc_blancb")) {
                                    canvas.drawBitmap(jaune_blanc_blancb, (getWidth() - jaune_blanc_blancb.getWidth()) / 2, getHeight() - jaune_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("vert_blanc_blanch")) {
                                    canvas.drawBitmap(vert_blanc_blanch, (getWidth() - vert_blanc_blanch.getWidth()) / 2, getHeight() - vert_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("vert_blanc_blancb")) {
                                    canvas.drawBitmap(vert_blanc_blancb, (getWidth() - vert_blanc_blancb.getWidth()) / 2, getHeight() - vert_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("vert_vert_verth")) {
                                    canvas.drawBitmap(vert_vert_verth, (getWidth() - vert_vert_verth.getWidth()) / 2, getHeight() - vert_vert_verth.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_vert_blanch")) {
                                    canvas.drawBitmap(rouge_vert_blanch, (getWidth() - rouge_vert_blanch.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_vert_blancb")) {
                                    canvas.drawBitmap(rouge_vert_blancb, (getWidth() - rouge_vert_blancb.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line.equals("rouge_rouge_rougeh")) {
                                    canvas.drawBitmap(rouge_rouge_rougeh, (getWidth() - rouge_rouge_rougeh.getWidth()) / 2, getHeight() - rouge_rouge_rougeh.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }


                            }
                        }
                    }



                    // Log.i("-> FCT <-", "onTouchEvent: " + event.getX());

                    // Permet de recuperer les evenements tactiles
                    // ACTION_UP
                    //
                    // ACTION_MOVE

                    // test pour gerer le cliquer-glisser

                    //}



                    //canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);


                }
                if(triplet2[nbtriplet2] == 1) {
                    if (event.getX() >= 203 && event.getX() <= 259 && event.getY() >= carteTopAnchor + 200 && event.getY() <= carteTopAnchor + 335) {

                        nbclick2[nbtriplet2] += 1;
                        if (nbclick2[nbtriplet2] > 3) {
                            nbclick2[nbtriplet2] = 0;

                        }

                        if(nbclick2[nbtriplet2] == 0)
                        {

                            postriplet2[nbtriplet2] = 0;

                            if (line2.equals("bleu_blanc_blancb")) {
                                line2 = "bleu_blanc_blanc";


                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line2.equals("rouge_blanc_blancb")) {
                                line2 = "rouge_blanc_blanc";

                            }

                            if (line2.equals("vert_blanc_blancb")) {
                                line2 = "vert_blanc_blanc";
                            }

                            if (line2.equals("vert_vert_verth")) {
                                line2 = "vert_vert_vert";
                            }
                            if (line2.equals("rouge_vert_blancb")) {
                                line2 = "rouge_vert_blanc";
                            }
                            if (line2.equals("rouge_rouge_rougeh")) {
                                line2 = "rouge_rouge_rouge";
                            }
                            if (line2.equals("blanc_blanc_blanch")) {
                                line2 = "blanc_blanc_blanc";
                            }
                            if (line2.equals("jaune_blanc_blancb")) {
                                line2 = "jaune_blanc_blanc";
                            }
                        }
                        if(nbclick2[nbtriplet2]==1) {

                            if (line2.equals("rouge_blanc_blanc")) {
                                line2 = "blanc_blanc_rouge";

                                Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                            }

                            if (line2.equals("blanc_blanc_blanc")) {
                                line2= "blanc_blanc_blanc";

                                Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                            }
                            if (line2.equals("bleu_blanc_blanc")) {
                                line2 = "blanc_blanc_bleu";

                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line2.equals("jaune_blanc_blanc")) {
                                line2 = "blanc_blanc_jaune";

                                Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                            }
                            if (line2.equals("vert_blanc_blanc")) {
                                line2 = "blanc_blanc_vert";

                                Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                            }
                            if (line2.equals("rouge_vert_blanc")) {
                                line2 = "blanc_vert_rouge";

                                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                            }
                            if (line2.equals("vert_vert_vert")) {
                                line2 = "vert_vert_vert";


                                Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                            }
                            if (line2.equals("rouge_rouge_rouge")) {
                                line2 = "rouge_rouge_rouge";
                                Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                            }


                            Log.i("Coordonnées", "ydffdsfdsfdsfdsfdsffdsfdsfdsfdsfdsfdsfdsfdsfdiiiiiiiiiiiiiiiiiiiiiiiii" + toString().valueOf(nbclick[nbtriplet2]));
                        }
                        if(nbclick2[nbtriplet]==2) {

                            postriplet2[nbtriplet2] = 1;
                            if (line2.equals("blanc_blanc_bleu")) {
                                line2 = "bleu_blanc_blanch";

                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line2.equals("blanc_blanc_rouge")) {
                                line2 = "rouge_blanc_blanch";;
                            }

                            if (line2.equals("blanc_blanc_vert")) {
                                line2 = "vert_blanc_blanch";
                            }

                            if (line2.equals("vert_vert_vert")) {
                                line2 = "vert_vert_verth" ;
                            }
                            if (line2.equals("blanc_vert_rouge")) {
                                line2 = "rouge_vert_blanch";
                            }
                            if (line2.equals("rouge_rouge_rouge")) {
                                line2 = "rouge_rouge_rouge";
                            }
                            if (line2.equals("blanc_blanc_blanc")) {
                                line2 = "blanc_blanc_blanch";
                            }
                            if (line2.equals("blanc_blanc_jaune")) {
                                line2 = "jaune_blanc_blanch";
                            }


                            Log.i("Coordonnées", "ydffdsfdsfdsfdsfdsffdsfdsfdsfdsfdsfdsfdsfdsfdiiiiiiiiiiiiiiiiiiiiiiiii" + toString().valueOf(nbclick[nbtriplet2]));
                        }
                        if(nbclick2[nbtriplet2]==3) {

                            postriplet2[nbtriplet2] = 1;
                            if (line2.equals("bleu_blanc_blanch")) {
                                line2 = "bleu_blanc_blancb";


                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line2.equals("rouge_blanc_blanch")) {
                                line2 = "rouge_blanc_blancb";;
                            }

                            if (line2.equals("vert_blanc_blanch")) {
                                line2 = "vert_blanc_blancb";
                            }

                            if (line2.equals("vert_vert_verth")) {
                                line2 = "vert_vert_verth" ;
                            }
                            if (line2.equals("rouge_vert_blanch")) {
                                line2 = "rouge_vert_blancb";
                            }
                            if (line2.equals("rouge_rouge_rougeh")) {
                                line2 = "rouge_rouge_rougeh";
                            }
                            if (line.equals("blanc_blanc_blanch")) {
                                line = "blanc_blanc_blanch";
                            }
                            if (line2.equals("jaune_blanc_blanch")) {
                                line2 = "jaune_blanc_blancb";
                            }

                            Log.i("Coordonnées", "ydffdsfdsfdsfdsfdsffdsfdsfdsfdsfdsfdsfdsfdsfdiiiiiiiiiiiiiiiiiiiiiiiii" + toString().valueOf(nbclick[nbtriplet2]));
                        }

                    }

                    //                    if (event.getX() >= carteLeftAnchor+20 && event.getX() <= carteLeftAnchor+130 && event.getY() >= carteTopAnchor+200 && event.getY() <= carteTopAnchor+335)

                    if (event.getX() >= carteLeftAnchor - 5 && event.getX() <= carteLeftAnchor + 150 && event.getY() >= carteTopAnchor && event.getY() <= carteTopAnchor + 175) {
                        refTriplet[nbtriplet2][0] = Math.round(event.getY() / carteTileSize) - 7;
                        refTriplet[nbtriplet2][1] = Math.round(event.getX() / carteTileSize) - 3;
                        if(postriplet2[nbtriplet2] == 0) {

                            //Log.i("okokpjj","fjdfjdsfjdsfjdsfdskjlfdjsklfkdsfkdsfgdgdsgdsgdsgdsgds"+String.valueOf(son));
                            if (carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] == 4 && carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] == 4 && carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] == 4) {

                                if(isclickedson == true)
                                    mp.start();
                                if (line2.equals("rouge_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line2.equals("rouge_rouge_rouge")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = rouge_num;
                                }
                                if (line2.equals("blanc_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line2.equals("vert_vert_vert")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = vert_num;
                                }
                                if (line2.equals("bleu_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = bleu_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line2.equals("vert_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line2.equals("rouge_vert_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line2.equals("jaune_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = jaune_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line2.equals("blanc_blanc_rouge")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = rouge_num;
                                }


                                if (line2.equals("blanc_blanc_bleu")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = bleu_num;
                                }
                                if (line2.equals("blanc_blanc_vert")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = vert_num;
                                }
                                if (line2.equals("blanc_vert_rouge")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = rouge_num;
                                }
                                if (line2.equals("blanc_blanc_jaune")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = jaune_num;
                                }
                                triplet2[nbtriplet2] = 2;
                                // On prend le triplet suivant
                                nbtriplet2++;

                                endSearch2 = true;
                                if (endSearch2) {
                                    Log.i("", "endSearch");
                                }
                            } else {


                                if (line2.equals("rouge_blanc_blanc")) {
                                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                }


                                if (line2.equals("blanc_blanc_blanc")) {
                                    canvas.drawBitmap(blanc_blanc_blanc, (getWidth() - blanc_blanc_blanc.getWidth()) / 2, getHeight() - blanc_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                                }
                                if (line2.equals("bleu_blanc_blanc")) {
                                    canvas.drawBitmap(bleu_blanc_blanc, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                }
                                if (line2.equals("jaune_blanc_blanc")) {
                                    canvas.drawBitmap(jaune_blanc_blanc, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                }
                                if (line2.equals("vert_blanc_blanc")) {
                                    canvas.drawBitmap(vert_blanc_blanc, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                }
                                if (line2.equals("rouge_vert_blanc")) {
                                    canvas.drawBitmap(rouge_vert_blanc, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("vert_vert_vert")) {
                                    canvas.drawBitmap(vert_vert_vert, (getWidth() - vert_vert_vert.getWidth()) / 2, getHeight() - vert_vert_vert.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                                }
                                if (line2.equals("rouge_rouge_rouge")) {
                                    canvas.drawBitmap(rouge_rouge_rouge, (getWidth() - rouge_rouge_rouge.getWidth()) / 2, getHeight() - rouge_rouge_rouge.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                                }
                                if (line2.equals("blanc_blanc_rouge")) {
                                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                }

                                if (line2.equals("blanc_blanc_bleu")) {
                                    canvas.drawBitmap(blanc_blanc_bleu, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                }
                                if (line2.equals("blanc_blanc_jaune")) {
                                    canvas.drawBitmap(blanc_blanc_jaune, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                }
                                if (line2.equals("blanc_blanc_vert")) {
                                    canvas.drawBitmap(blanc_blanc_vert, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                }
                                if (line2.equals("blanc_vert_rouge")) {
                                    canvas.drawBitmap(blanc_vert_rouge, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("bleu_blanc_blanch")) {
                                    canvas.drawBitmap(bleu_blanc_blanch, (getWidth() - bleu_blanc_blanch.getWidth()) / 2, getHeight() - bleu_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("bleu_blanc_blancb")) {
                                    canvas.drawBitmap(bleu_blanc_blancb, (getWidth() - bleu_blanc_blancb.getWidth()) / 2, getHeight() - bleu_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("rouge_blanc_blanch")) {
                                    canvas.drawBitmap(rouge_blanc_blanch, (getWidth() - rouge_blanc_blanch.getWidth()) / 2, getHeight() - rouge_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("rouge_blanc_blancb")) {
                                    canvas.drawBitmap(rouge_blanc_blancb, (getWidth() - rouge_blanc_blancb.getWidth()) / 2, getHeight() - rouge_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("blanc_blanc_blanch")) {
                                    canvas.drawBitmap(blanc_blanc_blanch, (getWidth() - blanc_blanc_blanch.getWidth()) / 2, getHeight() - blanc_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("jaune_blanc_blanch")) {
                                    canvas.drawBitmap(jaune_blanc_blanch, (getWidth() - jaune_blanc_blanch.getWidth()) / 2, getHeight() - jaune_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("jaune_blanc_blancb")) {
                                    canvas.drawBitmap(jaune_blanc_blancb, (getWidth() - jaune_blanc_blancb.getWidth()) / 2, getHeight() - jaune_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("vert_blanc_blanch")) {
                                    canvas.drawBitmap(vert_blanc_blanch, (getWidth() - vert_blanc_blanch.getWidth()) / 2, getHeight() - vert_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("vert_blanc_blancb")) {
                                    canvas.drawBitmap(vert_blanc_blancb, (getWidth() - vert_blanc_blancb.getWidth()) / 2, getHeight() - vert_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("vert_vert_verth")) {
                                    canvas.drawBitmap(vert_vert_verth, (getWidth() - vert_vert_verth.getWidth()) / 2, getHeight() - vert_vert_verth.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("rouge_vert_blanch")) {
                                    canvas.drawBitmap(rouge_vert_blanch, (getWidth() - rouge_vert_blanch.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("rouge_vert_blancb")) {
                                    canvas.drawBitmap(rouge_vert_blancb, (getWidth() - rouge_vert_blancb.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("rouge_rouge_rougeh")) {
                                    canvas.drawBitmap(rouge_rouge_rougeh, (getWidth() - rouge_rouge_rougeh.getWidth()) / 2, getHeight() - rouge_rouge_rougeh.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }


                            }
                        }
                        if (postriplet2[nbtriplet2] == 1) {


                            if (carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] == 4 && carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] == 4 && carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] == 4) {
                                if(isclickedson == true)
                                    mp.start();
                                if (line2.equals("bleu_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = bleu_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                if (line2.equals("bleu_blanc_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = bleu_num;
                                }

                                if (line2.equals("rouge_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                if (line2.equals("rouge_blanc_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                }

                                if (line2.equals("vert_blanc_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                }
                                if (line2.equals("vert_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                if (line2.equals("jaune_blanc_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = jaune_num;
                                }
                                if (line2.equals("jaune_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = jaune_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }

                                if (line2.equals("vert_vert_verth")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                }

                                if (line2.equals("rouge_vert_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                if (line2.equals("rouge_vert_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                }
                                if (line2.equals("rouge_rouge_rougeh")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                }
                                if (line2.equals("blanc_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                triplet[nbtriplet2] = 2;
                                // On prend le triplet suivant
                                nbtriplet2++;

                                endSearch2 = true;
                                if (endSearch2) {
                                    Log.i("", "endSearch");
                                }
                            }
                            else {


                                if (line2.equals("rouge_blanc_blanc")) {
                                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                }


                                if (line2.equals("blanc_blanc_blanc")) {
                                    canvas.drawBitmap(blanc_blanc_blanc, (getWidth() - blanc_blanc_blanc.getWidth()) / 2, getHeight() - blanc_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                                }
                                if (line2.equals("bleu_blanc_blanc")) {
                                    canvas.drawBitmap(bleu_blanc_blanc, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                }
                                if (line2.equals("jaune_blanc_blanc")) {
                                    canvas.drawBitmap(jaune_blanc_blanc, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                }
                                if (line2.equals("vert_blanc_blanc")) {
                                    canvas.drawBitmap(vert_blanc_blanc, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                }
                                if (line2.equals("rouge_vert_blanc")) {
                                    canvas.drawBitmap(rouge_vert_blanc, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("vert_vert_vert")) {
                                    canvas.drawBitmap(vert_vert_vert, (getWidth() - vert_vert_vert.getWidth()) / 2, getHeight() - vert_vert_vert.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                                }
                                if (line2.equals("rouge_rouge_rouge")) {
                                    canvas.drawBitmap(rouge_rouge_rouge, (getWidth() - rouge_rouge_rouge.getWidth()) / 2, getHeight() - rouge_rouge_rouge.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                                }
                                if (line2.equals("blanc_blanc_rouge")) {
                                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                }

                                if (line2.equals("blanc_blanc_bleu")) {
                                    canvas.drawBitmap(blanc_blanc_bleu, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                }
                                if (line2.equals("blanc_blanc_jaune")) {
                                    canvas.drawBitmap(blanc_blanc_jaune, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                }
                                if (line2.equals("blanc_blanc_vert")) {
                                    canvas.drawBitmap(blanc_blanc_vert, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                }
                                if (line2.equals("blanc_vert_rouge")) {
                                    canvas.drawBitmap(blanc_vert_rouge, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("bleu_blanc_blanch")) {
                                    canvas.drawBitmap(bleu_blanc_blanch, (getWidth() - bleu_blanc_blanch.getWidth()) / 2, getHeight() - bleu_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("bleu_blanc_blancb")) {
                                    canvas.drawBitmap(bleu_blanc_blancb, (getWidth() - bleu_blanc_blancb.getWidth()) / 2, getHeight() - bleu_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("rouge_blanc_blanch")) {
                                    canvas.drawBitmap(rouge_blanc_blanch, (getWidth() - rouge_blanc_blanch.getWidth()) / 2, getHeight() - rouge_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("rouge_blanc_blancb")) {
                                    canvas.drawBitmap(rouge_blanc_blancb, (getWidth() - rouge_blanc_blancb.getWidth()) / 2, getHeight() - rouge_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("blanc_blanc_blanch")) {
                                    canvas.drawBitmap(blanc_blanc_blanch, (getWidth() - blanc_blanc_blanch.getWidth()) / 2, getHeight() - blanc_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("jaune_blanc_blanch")) {
                                    canvas.drawBitmap(jaune_blanc_blanch, (getWidth() - jaune_blanc_blanch.getWidth()) / 2, getHeight() - jaune_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("jaune_blanc_blancb")) {
                                    canvas.drawBitmap(jaune_blanc_blancb, (getWidth() - jaune_blanc_blancb.getWidth()) / 2, getHeight() - jaune_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("vert_blanc_blanch")) {
                                    canvas.drawBitmap(vert_blanc_blanch, (getWidth() - vert_blanc_blanch.getWidth()) / 2, getHeight() - vert_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("vert_blanc_blancb")) {
                                    canvas.drawBitmap(vert_blanc_blancb, (getWidth() - vert_blanc_blancb.getWidth()) / 2, getHeight() - vert_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("vert_vert_verth")) {
                                    canvas.drawBitmap(vert_vert_verth, (getWidth() - vert_vert_verth.getWidth()) / 2, getHeight() - vert_vert_verth.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("rouge_vert_blanch")) {
                                    canvas.drawBitmap(rouge_vert_blanch, (getWidth() - rouge_vert_blanch.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("rouge_vert_blancb")) {
                                    canvas.drawBitmap(rouge_vert_blancb, (getWidth() - rouge_vert_blancb.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line2.equals("rouge_rouge_rougeh")) {
                                    canvas.drawBitmap(rouge_rouge_rougeh, (getWidth() - rouge_rouge_rougeh.getWidth()) / 2, getHeight() - rouge_rouge_rougeh.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }


                            }
                        }
                    }



                    // Log.i("-> FCT <-", "onTouchEvent: " + event.getX());

                    // Permet de recuperer les evenements tactiles
                    // ACTION_UP
                    //
                    // ACTION_MOVE

                    // test pour gerer le cliquer-glisser

                    //}



                    //canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);


                }

                //selection_triplet1 = 2;
                //Log.i("ACTION_MOVE", "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
                if(triplet3[nbtriplet3] == 1) {
                    if (event.getX() >= 60 && event.getX() <= 116 && event.getY() >= carteTopAnchor + 200 && event.getY() <= carteTopAnchor + 335) {

                        nbclick3[nbtriplet3] += 1;
                        if (nbclick3[nbtriplet3] > 3) {
                            nbclick3[nbtriplet3] = 0;

                        }

                        if(nbclick3[nbtriplet3] == 0)
                        {

                            postriplet3[nbtriplet3] = 0;

                            if (line3.equals("bleu_blanc_blancb")) {
                                line3 = "bleu_blanc_blanc";


                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line3.equals("rouge_blanc_blancb")) {
                                line3 = "rouge_blanc_blanc";

                            }

                            if (line3.equals("vert_blanc_blancb")) {
                                line3 = "vert_blanc_blanc";
                            }

                            if (line3.equals("vert_vert_verth")) {
                                line3 = "vert_vert_vert";
                            }
                            if (line3.equals("rouge_vert_blancb")) {
                                line3 = "rouge_vert_blanc";
                            }
                            if (line3.equals("rouge_rouge_rougeh")) {
                                line3 = "rouge_rouge_rouge";
                            }
                            if (line3.equals("blanc_blanc_blanch")) {
                                line3 = "blanc_blanc_blanc";
                            }
                            if (line3.equals("jaune_blanc_blancb")) {
                                line3 = "jaune_blanc_blanc";
                            }
                        }
                        if(nbclick3[nbtriplet3]==1) {

                            if (line3.equals("rouge_blanc_blanc")) {
                                line3 = "blanc_blanc_rouge";

                                Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                            }

                            if (line3.equals("blanc_blanc_blanc")) {
                                line3 = "blanc_blanc_blanc";

                                Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                            }
                            if (line3.equals("bleu_blanc_blanc")) {
                                line3 = "blanc_blanc_bleu";

                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line3.equals("jaune_blanc_blanc")) {
                                line3 = "blanc_blanc_jaune";

                                Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                            }
                            if (line3.equals("vert_blanc_blanc")) {
                                line3 = "blanc_blanc_vert";

                                Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                            }
                            if (line3.equals("rouge_vert_blanc")) {
                                line3 = "blanc_vert_rouge";

                                Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                            }
                            if (line3.equals("vert_vert_vert")) {
                                line3= "vert_vert_vert";


                                Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                            }
                            if (line3.equals("rouge_rouge_rouge")) {
                                line3 = "rouge_rouge_rouge";
                                Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                            }


                            Log.i("Coordonnées", "ydffdsfdsfdsfdsfdsffdsfdsfdsfdsfdsfdsfdsfdsfdiiiiiiiiiiiiiiiiiiiiiiiii" + toString().valueOf(nbclick[nbtriplet3]));
                        }
                        if(nbclick3[nbtriplet3]==2) {

                            postriplet3[nbtriplet3] = 1;
                            if (line3.equals("blanc_blanc_bleu")) {
                                line3 = "bleu_blanc_blanch";

                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line3.equals("blanc_blanc_rouge")) {
                                line3 = "rouge_blanc_blanch";;
                            }

                            if (line3.equals("blanc_blanc_vert")) {
                                line3 = "vert_blanc_blanch";
                            }

                            if (line3.equals("vert_vert_vert")) {
                                line3 = "vert_vert_verth" ;
                            }
                            if (line3.equals("blanc_vert_rouge")) {
                                line3 = "rouge_vert_blanch";
                            }
                            if (line3.equals("rouge_rouge_rouge")) {
                                line3 = "rouge_rouge_rouge";
                            }
                            if (line3.equals("blanc_blanc_blanc")) {
                                line3 = "blanc_blanc_blanch";
                            }
                            if (line3.equals("blanc_blanc_jaune")) {
                                line3 = "jaune_blanc_blanch";
                            }


                            Log.i("Coordonnées", "ydffdsfdsfdsfdsfdsffdsfdsfdsfdsfdsfdsfdsfdsfdiiiiiiiiiiiiiiiiiiiiiiiii" + toString().valueOf(nbclick[nbtriplet3]));
                        }
                        if(nbclick3[nbtriplet3]==3) {

                            postriplet3[nbtriplet3] = 1;
                            if (line3.equals("bleu_blanc_blanch")) {
                                line3 = "bleu_blanc_blancb";


                                Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                            }
                            if (line3.equals("rouge_blanc_blanch")) {
                                line3 = "rouge_blanc_blancb";;
                            }

                            if (line3.equals("vert_blanc_blanch")) {
                                line3 = "vert_blanc_blancb";
                            }

                            if (line3.equals("vert_vert_verth")) {
                                line3 = "vert_vert_verth" ;
                            }
                            if (line3.equals("rouge_vert_blanch")) {
                                line3 = "rouge_vert_blancb";
                            }
                            if (line3.equals("rouge_rouge_rougeh")) {
                                line3 = "rouge_rouge_rougeh";
                            }
                            if (line3.equals("blanc_blanc_blanch")) {
                                line3 = "blanc_blanc_blanch";
                            }
                            if (line3.equals("jaune_blanc_blanch")) {
                                line3 = "jaune_blanc_blancb";
                            }

                            Log.i("Coordonnées", "ydffdsfdsfdsfdsfdsffdsfdsfdsfdsfdsfdsfdsfdsfdiiiiiiiiiiiiiiiiiiiiiiiii" + toString().valueOf(nbclick[nbtriplet3]));
                        }

                    }

                    //                    if (event.getX() >= carteLeftAnchor+20 && event.getX() <= carteLeftAnchor+130 && event.getY() >= carteTopAnchor+200 && event.getY() <= carteTopAnchor+335)

                    if (event.getX() >= carteLeftAnchor - 5 && event.getX() <= carteLeftAnchor + 150 && event.getY() >= carteTopAnchor && event.getY() <= carteTopAnchor + 175) {
                        refTriplet[nbtriplet3][0] = Math.round(event.getY() / carteTileSize) - 7;
                        refTriplet[nbtriplet3][1] = Math.round(event.getX() / carteTileSize) - 3;
                        if(postriplet3[nbtriplet3] == 0) {

                            //Log.i("okokpjj","fjdfjdsfjdsfjdsfdskjlfdjsklfkdsfkdsfgdgdsgdsgdsgdsgds"+String.valueOf(son));
                            if (carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] == 4 && carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] == 4 && carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] == 4) {

                                if(isclickedson == true)
                                    mp.start();
                                if (line3.equals("rouge_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line3.equals("rouge_rouge_rouge")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = rouge_num;
                                }
                                if (line3.equals("blanc_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line3.equals("vert_vert_vert")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = vert_num;
                                }
                                if (line3.equals("bleu_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = bleu_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line3.equals("vert_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line3.equals("rouge_vert_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line3.equals("jaune_blanc_blanc")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = jaune_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = blanc_num;
                                }
                                if (line3.equals("blanc_blanc_rouge")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = rouge_num;
                                }


                                if (line3.equals("blanc_blanc_bleu")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = bleu_num;
                                }
                                if (line3.equals("blanc_blanc_vert")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = vert_num;
                                }
                                if (line3.equals("blanc_vert_rouge")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = rouge_num;
                                }
                                if (line3.equals("blanc_blanc_jaune")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 2] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 1] = jaune_num;
                                }
                                triplet3[nbtriplet3] = 2;
                                // On prend le triplet suivant
                                nbtriplet3++;

                                endSearch3 = true;
                                if (endSearch3) {
                                    Log.i("", "endSearch");
                                }
                            } else {


                                if (line3.equals("rouge_blanc_blanc")) {
                                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                }


                                if (line3.equals("blanc_blanc_blanc")) {
                                    canvas.drawBitmap(blanc_blanc_blanc, (getWidth() - blanc_blanc_blanc.getWidth()) / 2, getHeight() - blanc_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                                }
                                if (line3.equals("bleu_blanc_blanc")) {
                                    canvas.drawBitmap(bleu_blanc_blanc, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                }
                                if (line3.equals("jaune_blanc_blanc")) {
                                    canvas.drawBitmap(jaune_blanc_blanc, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                }
                                if (line3.equals("vert_blanc_blanc")) {
                                    canvas.drawBitmap(vert_blanc_blanc, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                }
                                if (line3.equals("rouge_vert_blanc")) {
                                    canvas.drawBitmap(rouge_vert_blanc, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("vert_vert_vert")) {
                                    canvas.drawBitmap(vert_vert_vert, (getWidth() - vert_vert_vert.getWidth()) / 2, getHeight() - vert_vert_vert.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                                }
                                if (line3.equals("rouge_rouge_rouge")) {
                                    canvas.drawBitmap(rouge_rouge_rouge, (getWidth() - rouge_rouge_rouge.getWidth()) / 2, getHeight() - rouge_rouge_rouge.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                                }
                                if (line3.equals("blanc_blanc_rouge")) {
                                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                }

                                if (line3.equals("blanc_blanc_bleu")) {
                                    canvas.drawBitmap(blanc_blanc_bleu, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                }
                                if (line3.equals("blanc_blanc_jaune")) {
                                    canvas.drawBitmap(blanc_blanc_jaune, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                }
                                if (line3.equals("blanc_blanc_vert")) {
                                    canvas.drawBitmap(blanc_blanc_vert, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                }
                                if (line3.equals("blanc_vert_rouge")) {
                                    canvas.drawBitmap(blanc_vert_rouge, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("bleu_blanc_blanch")) {
                                    canvas.drawBitmap(bleu_blanc_blanch, (getWidth() - bleu_blanc_blanch.getWidth()) / 2, getHeight() - bleu_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("bleu_blanc_blancb")) {
                                    canvas.drawBitmap(bleu_blanc_blancb, (getWidth() - bleu_blanc_blancb.getWidth()) / 2, getHeight() - bleu_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("rouge_blanc_blanch")) {
                                    canvas.drawBitmap(rouge_blanc_blanch, (getWidth() - rouge_blanc_blanch.getWidth()) / 2, getHeight() - rouge_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("rouge_blanc_blancb")) {
                                    canvas.drawBitmap(rouge_blanc_blancb, (getWidth() - rouge_blanc_blancb.getWidth()) / 2, getHeight() - rouge_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("blanc_blanc_blanch")) {
                                    canvas.drawBitmap(blanc_blanc_blanch, (getWidth() - blanc_blanc_blanch.getWidth()) / 2, getHeight() - blanc_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("jaune_blanc_blanch")) {
                                    canvas.drawBitmap(jaune_blanc_blanch, (getWidth() - jaune_blanc_blanch.getWidth()) / 2, getHeight() - jaune_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("jaune_blanc_blancb")) {
                                    canvas.drawBitmap(jaune_blanc_blancb, (getWidth() - jaune_blanc_blancb.getWidth()) / 2, getHeight() - jaune_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("vert_blanc_blanch")) {
                                    canvas.drawBitmap(vert_blanc_blanch, (getWidth() - vert_blanc_blanch.getWidth()) / 2, getHeight() - vert_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("vert_blanc_blancb")) {
                                    canvas.drawBitmap(vert_blanc_blancb, (getWidth() - vert_blanc_blancb.getWidth()) / 2, getHeight() - vert_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("vert_vert_verth")) {
                                    canvas.drawBitmap(vert_vert_verth, (getWidth() - vert_vert_verth.getWidth()) / 2, getHeight() - vert_vert_verth.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("rouge_vert_blanch")) {
                                    canvas.drawBitmap(rouge_vert_blanch, (getWidth() - rouge_vert_blanch.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("rouge_vert_blancb")) {
                                    canvas.drawBitmap(rouge_vert_blancb, (getWidth() - rouge_vert_blancb.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("rouge_rouge_rougeh")) {
                                    canvas.drawBitmap(rouge_rouge_rougeh, (getWidth() - rouge_rouge_rougeh.getWidth()) / 2, getHeight() - rouge_rouge_rougeh.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }


                            }
                        }
                        if (postriplet3[nbtriplet3] == 1) {


                            if (carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] == 4 && carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] == 4 && carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] == 4) {
                                if(isclickedson == true)
                                    mp.start();
                                if (line3.equals("bleu_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = bleu_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                if (line3.equals("bleu_blanc_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = bleu_num;
                                }

                                if (line3.equals("rouge_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                if (line3.equals("rouge_blanc_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                }

                                if (line3.equals("vert_blanc_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                }
                                if (line3.equals("vert_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                if (line3.equals("jaune_blanc_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = jaune_num;
                                }
                                if (line3.equals("jaune_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = jaune_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }

                                if (line3.equals("vert_vert_verth")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                }

                                if (line3.equals("rouge_vert_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                if (line3.equals("rouge_vert_blancb")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = vert_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                }
                                if (line3.equals("rouge_rouge_rougeh")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = rouge_num;
                                }
                                if (line3.equals("blanc_blanc_blanch")) {
                                    // Ajout du triplet rouge blanc_blanc dans le dessin
                                    carte[Math.round(event.getY() / carteTileSize) - 7][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 6][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                    carte[Math.round(event.getY() / carteTileSize) - 5][Math.round(event.getX() / carteTileSize) - 3] = blanc_num;
                                }
                                triplet3[nbtriplet3] = 2;
                                // On prend le triplet suivant
                                nbtriplet3++;

                                endSearch3 = true;
                                if (endSearch3) {
                                    Log.i("", "endSearch");
                                }
                            }
                            else {


                                if (line3.equals("rouge_blanc_blanc")) {
                                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                }


                                if (line3.equals("blanc_blanc_blanc")) {
                                    canvas.drawBitmap(blanc_blanc_blanc, (getWidth() - blanc_blanc_blanc.getWidth()) / 2, getHeight() - blanc_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "blanc_blanc_blanc");
                                }
                                if (line3.equals("bleu_blanc_blanc")) {
                                    canvas.drawBitmap(bleu_blanc_blanc, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                }
                                if (line3.equals("jaune_blanc_blanc")) {
                                    canvas.drawBitmap(jaune_blanc_blanc, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                }
                                if (line3.equals("vert_blanc_blanc")) {
                                    canvas.drawBitmap(vert_blanc_blanc, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                }
                                if (line3.equals("rouge_vert_blanc")) {
                                    canvas.drawBitmap(rouge_vert_blanc, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("vert_vert_vert")) {
                                    canvas.drawBitmap(vert_vert_vert, (getWidth() - vert_vert_vert.getWidth()) / 2, getHeight() - vert_vert_vert.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_vert_vert");
                                }
                                if (line3.equals("rouge_rouge_rouge")) {
                                    canvas.drawBitmap(rouge_rouge_rouge, (getWidth() - rouge_rouge_rouge.getWidth()) / 2, getHeight() - rouge_rouge_rouge.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_rouge_rouge");
                                }
                                if (line3.equals("blanc_blanc_rouge")) {
                                    canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_blanc_blanc");
                                }

                                if (line3.equals("blanc_blanc_bleu")) {
                                    canvas.drawBitmap(blanc_blanc_bleu, (getWidth() - bleu_blanc_blanc.getWidth()) / 2, getHeight() - bleu_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "bleu_blanc_blanc");
                                }
                                if (line3.equals("blanc_blanc_jaune")) {
                                    canvas.drawBitmap(blanc_blanc_jaune, (getWidth() - jaune_blanc_blanc.getWidth()) / 2, getHeight() - jaune_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "jaune_blanc_blanc");
                                }
                                if (line3.equals("blanc_blanc_vert")) {
                                    canvas.drawBitmap(blanc_blanc_vert, (getWidth() - vert_blanc_blanc.getWidth()) / 2, getHeight() - vert_blanc_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "vert_blanc_blanc");
                                }
                                if (line3.equals("blanc_vert_rouge")) {
                                    canvas.drawBitmap(blanc_vert_rouge, (getWidth() - rouge_vert_blanc.getWidth()) / 2, getHeight() - rouge_vert_blanc.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("bleu_blanc_blanch")) {
                                    canvas.drawBitmap(bleu_blanc_blanch, (getWidth() - bleu_blanc_blanch.getWidth()) / 2, getHeight() - bleu_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("bleu_blanc_blancb")) {
                                    canvas.drawBitmap(bleu_blanc_blancb, (getWidth() - bleu_blanc_blancb.getWidth()) / 2, getHeight() - bleu_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("rouge_blanc_blanch")) {
                                    canvas.drawBitmap(rouge_blanc_blanch, (getWidth() - rouge_blanc_blanch.getWidth()) / 2, getHeight() - rouge_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("rouge_blanc_blancb")) {
                                    canvas.drawBitmap(rouge_blanc_blancb, (getWidth() - rouge_blanc_blancb.getWidth()) / 2, getHeight() - rouge_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("blanc_blanc_blanch")) {
                                    canvas.drawBitmap(blanc_blanc_blanch, (getWidth() - blanc_blanc_blanch.getWidth()) / 2, getHeight() - blanc_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("jaune_blanc_blanch")) {
                                    canvas.drawBitmap(jaune_blanc_blanch, (getWidth() - jaune_blanc_blanch.getWidth()) / 2, getHeight() - jaune_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("jaune_blanc_blancb")) {
                                    canvas.drawBitmap(jaune_blanc_blancb, (getWidth() - jaune_blanc_blancb.getWidth()) / 2, getHeight() - jaune_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("vert_blanc_blanch")) {
                                    canvas.drawBitmap(vert_blanc_blanch, (getWidth() - vert_blanc_blanch.getWidth()) / 2, getHeight() - vert_blanc_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("vert_blanc_blancb")) {
                                    canvas.drawBitmap(vert_blanc_blancb, (getWidth() - vert_blanc_blancb.getWidth()) / 2, getHeight() - vert_blanc_blancb.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("vert_vert_verth")) {
                                    canvas.drawBitmap(vert_vert_verth, (getWidth() - vert_vert_verth.getWidth()) / 2, getHeight() - vert_vert_verth.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("rouge_vert_blanch")) {
                                    canvas.drawBitmap(rouge_vert_blanch, (getWidth() - rouge_vert_blanch.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("rouge_vert_blancb")) {
                                    canvas.drawBitmap(rouge_vert_blancb, (getWidth() - rouge_vert_blancb.getWidth()) / 2, getHeight() - rouge_vert_blanch.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }
                                if (line3.equals("rouge_rouge_rougeh")) {
                                    canvas.drawBitmap(rouge_rouge_rougeh, (getWidth() - rouge_rouge_rougeh.getWidth()) / 2, getHeight() - rouge_rouge_rougeh.getHeight(), null);
                                    Log.i("PAINTINTERFACE 2 > data", "rouge_vert_blanc");
                                }


                            }
                        }
                    }



                    // Log.i("-> FCT <-", "onTouchEvent: " + event.getX());

                    // Permet de recuperer les evenements tactiles
                    // ACTION_UP
                    //
                    // ACTION_MOVE

                    // test pour gerer le cliquer-glisser

                    //}



                    //canvas.drawBitmap(rouge_blanc_blanc, (getWidth() - rouge_blanc_blanc.getWidth()) / 2, getHeight() - rouge_blanc_blanc.getHeight(), null);


                }
                break;



            case MotionEvent.ACTION_CANCEL: {
                break;
            }

                
                
                
        }
        
        
        // Lis les coordonnées
        Log.i("Coordonnées", "y : " + String.valueOf(event.getY() + " x : " + String.valueOf(event.getX())));
        
        
        
        
        return true;
    }
    
    // test pour gerer le drag on drop
    private void nDraw2(Canvas canvas, int posInterfaceX, int posInterfaceY) {
        paintInterface2(canvas, posInterfaceX, posInterfaceY);
    }
    
    private void paintInterface2(Canvas canvas, int posX, int posY) {
        if(selection_triplet1 == 0 || selection_triplet1 == 1) canvas.drawBitmap(rouge_blanc_blanc, posX, posY, null);
    }



}
