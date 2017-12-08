package p8.demo.p8sokoban;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


// Declaration de notre activity héritée de Activity
public class p8_Sokoban extends Activity {

    TextView scoreslabel, tvscores,etatson;
    Button soundb;
    Button quit;
    private SokobanView mSokobanView;
    private MyCount mc;
    public static long timer = 10;
    public static boolean isclickedson = true;

    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // initialise notre activity avec le constructeur parent
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        Button button_play = (Button) findViewById(R.id.jouer);
        
        button_play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // charge le fichier main.xml comme vue de l'activité
                setContentView(R.layout.main);
                // recuperation de la vue une voie cree à partir de son id
                mSokobanView = (SokobanView) findViewById(R.id.SokobanView);
                // rend visible la vue
                mSokobanView.setVisibility(View.VISIBLE);
                
                mc = new MyCount(50000, 1000);  // 10 seconds*
                mc.start();
            }
        });
        
        
        Button button_best_scores = (Button) findViewById(R.id.best_scores);
        
        button_best_scores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // charge le fichier main.xml comme vue de l'activité
                setContentView(R.layout.best_scores);
                scoreslabel = (TextView) findViewById(R.id.scores_label);
                tvscores = (TextView) findViewById(R.id.tvscores);
                SharedPreferences prefs = getSharedPreferences("highscore", Context.MODE_PRIVATE);
                int score = prefs.getInt("scores", 0); //0 is the default value
                tvscores.setText(""+score+"\n");
            }
        });

        etatson = (TextView) findViewById(R.id.son);
        soundb = (Button) findViewById(R.id.soundeffect);
        quit = (Button) findViewById(R.id.quitter);
        soundb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!isclickedson){
                    isclickedson=true;
                }
                else{
                    isclickedson=false;
                }
                if(isclickedson) etatson.setText("son activé");
                if(!isclickedson) etatson.setText("son desactivé");
            }
        });

        quit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                System.exit( 0);
            }
        });



    }
    
    // Permet de récupérer la classe CountDownTimer
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        
        @Override
        public void onTick(long millisUntilFinished) {
            timer = millisUntilFinished / 1000;
        }
        
        @Override
        public void onFinish() {
            timer = 0;
            Toast.makeText(getApplicationContext(), "Game over !", Toast.LENGTH_LONG).show();
            setContentView(R.layout.main2);
            
        }
    }
}


