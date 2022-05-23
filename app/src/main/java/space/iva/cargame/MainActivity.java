package space.iva.cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer gMusic = MediaPlayer.create(this, R.raw.game_music);
        gMusic.setLooping(true);
        gMusic.start();
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViewById(R.id.MainPlayBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LocListActivity.class));
            }
        });
        ImageButton musBtn = (ImageButton) findViewById(R.id.musicBtn);
        musBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gMusic.isPlaying()){
                    gMusic.pause();
                    musBtn.setImageResource(R.drawable.music_btn_off);
                }else {
                    gMusic.start();
                    musBtn.setImageResource(R.drawable.music_btn_on);
                }
            }
        });
        ImageButton soundsBtn = (ImageButton) findViewById(R.id.soundBtn);
        soundsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameView.soundsOn){
                    GameView.soundsOn=false;
                    soundsBtn.setImageResource(R.drawable.sound_btn_off);
                }else {
                    GameView.soundsOn=true;
                    soundsBtn.setImageResource(R.drawable.sound_btn_on);
                }
            }
        });

    }

}