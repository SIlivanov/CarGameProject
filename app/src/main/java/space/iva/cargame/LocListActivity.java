package space.iva.cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class LocListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_loc);
        TextView coins = findViewById(R.id.LocListScoreText);
        SharedPreferences sPref = getSharedPreferences("settings", MODE_PRIVATE);
        coins.setText(String.valueOf(sPref.getInt("topScore", 0)));
        Window w = getWindow();
        ImageButton desBtn = findViewById(R.id.YellowCarBtn);
        if(sPref.getInt("topScore", 0)>=750){
            desBtn.setImageResource(R.drawable.desert_loc_image);
        }else {
            desBtn.setImageResource(R.drawable.desert_loc_image_score);
        }
        ImageButton forestBtn = findViewById(R.id.ForestLocBtn);
        if(sPref.getInt("topScore", 0)>=1500){
            forestBtn.setImageResource(R.drawable.forest_loc_image);
        }else {
            forestBtn.setImageResource(R.drawable.forest_loc_image_score);
        }
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViewById(R.id.LocListExitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        findViewById(R.id.HighwayLocBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameView.Loc = BitmapFactory.decodeResource(getResources(), R.drawable.img);
                startActivity(new Intent(LocListActivity.this, GameActivity.class));
                GameView.coins=0;
                GameView.points=0;
                GameView.health=5;
                finish();
            }
        });
        findViewById(R.id.YellowCarBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sPref.getInt("topScore", 0)>=750){
                    GameView.Loc = BitmapFactory.decodeResource(getResources(), R.drawable.desert_loc);
                    startActivity(new Intent(LocListActivity.this, GameActivity.class));
                    GameView.coins=0;
                    GameView.points=0;
                    GameView.health=5;
                    finish();
                }
            }
        });
        findViewById(R.id.ForestLocBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sPref.getInt("topScore", 0)>=1500){
                    GameView.Loc = BitmapFactory.decodeResource(getResources(), R.drawable.forest);
                    startActivity(new Intent(LocListActivity.this, GameActivity.class));
                    GameView.coins=0;
                    GameView.points=0;
                    GameView.health=5;
                    finish();
                }

            }
        });
        findViewById(R.id.CarListBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocListActivity.this, CarListActivity.class));

            }
        });
    }
}