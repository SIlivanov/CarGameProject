package space.iva.cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    SharedPreferences sPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_board);
        sPref = getSharedPreferences("settings", MODE_PRIVATE);
        TextView textTopScore = findViewById(R.id.ScoreBoardTopScore);
        TextView textScore = findViewById(R.id.ScoreBoardScore);
        TextView textCoins = findViewById(R.id.ScoreBoardCoins);
        textScore.setText(""+GameView.points);
        textCoins.setText(""+GameView.coins);
        setTopScore();
        textTopScore.setText(""+sPref.getInt("topScore",0));
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        saveCoins();
        findViewById(R.id.ScoreBoardExitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScoreActivity.this, LocListActivity.class));
                finish();
            }
        });
        findViewById(R.id.ScoreBoardRestartBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameView.coins=0;
                GameView.points=0;
                GameView.health=5;
                startActivity(new Intent(ScoreActivity.this, GameActivity.class));
                finish();
            }
        });
    }
    private void saveCoins(){
        sPref = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt("coins",sPref.getInt("coins", 0)+GameView.coins);
        editor.apply();
    }
    private void setTopScore(){
        sPref = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        if (sPref.getInt("topScore",0)<GameView.points){
            editor.putInt("topScore",GameView.points);
            editor.apply();
        }
    }
}