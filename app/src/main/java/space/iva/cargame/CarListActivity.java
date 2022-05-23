package space.iva.cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class CarListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_list);
        TextView coins = findViewById(R.id.CarListCoins);
        SharedPreferences sPref = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        coins.setText(String.valueOf(sPref.getInt("coins", 0)));
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViewById(R.id.LocListExitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageButton ycarBtn = (ImageButton)findViewById(R.id.YellowCarBtn) ;
        ImageButton rcarBtn = (ImageButton)findViewById(R.id.RedCarBtn) ;
        rcarBtn.setImageResource(R.drawable.rcar_selected);
        if(sPref.getBoolean("ycarbought",false)){
            ycarBtn.setImageResource(R.drawable.ycar);
            ycarBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GameView.carNum=2;
                    rcarBtn.setImageResource(R.drawable.rcar);
                    ycarBtn.setImageResource(R.drawable.ycar_selected);
                }
            });
        }else {
            ycarBtn.setImageResource(R.drawable.ycar_coins);
            ycarBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sPref.getInt("coins", 0)>=100){
                        editor.putInt("coins",sPref.getInt("coins",0)-100);
                        editor.putBoolean("ycarbought",true);
                        editor.apply();
                        coins.setText(String.valueOf(sPref.getInt("coins", 0)));
                        ycarBtn.setImageResource(R.drawable.ycar);
                        ycarBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                GameView.carNum=2;
                                rcarBtn.setImageResource(R.drawable.rcar);
                                ycarBtn.setImageResource(R.drawable.ycar_selected);
                            }
                        });
                    }
                }
            });
        }
        rcarBtn.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                GameView.carNum=1;
                rcarBtn.setImageResource(R.drawable.rcar_selected);
                if(sPref.getBoolean("ycarbought",false)) {
                    ycarBtn.setImageResource(R.drawable.ycar);
                }
            }
        });


    }
}