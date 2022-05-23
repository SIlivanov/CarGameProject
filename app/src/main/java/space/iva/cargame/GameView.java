package space.iva.cargame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

    boolean stopGame = false;

    static Bitmap Loc;
    static int carNum=1;
    static boolean soundsOn=true;

    Bitmap carPosL = BitmapFactory.decodeResource(getResources(), R.drawable.car_1_l);
    Bitmap carPosR = BitmapFactory.decodeResource(getResources(), R.drawable.car_1_r);
    Bitmap car = BitmapFactory.decodeResource(getResources(), R.drawable.car_1);
    MediaPlayer mCoin = MediaPlayer.create(getContext(),R.raw.coin_sound);
    MediaPlayer mCrash = MediaPlayer.create(getContext(), R.raw.crash);
    MediaPlayer mHeal = MediaPlayer.create(getContext(), R.raw.heal);

    private int viewWidth;
    private int viewHeight;

    private int pointsMult=1;
    static int points=0;
    static int health = 5;
    static int coins = 0;
    private int carPos=1;
    private Sprite player;
    private Sprite enemy;
    private Background frstHalf;
    private Background scndHalf;
    private Bars healthBar;
    private Bars coinBar;
    private Sprite heart;
    private Sprite coin,coin2,coin3;

    private final int timerInterval = 30;

    int count=0;


    void updatePos(){
        if (carNum==1){
        if (health == 5){
            car = BitmapFactory.decodeResource(getResources(), R.drawable.car_1);
            carPosL = BitmapFactory.decodeResource(getResources(), R.drawable.car_1_l);
            carPosR = BitmapFactory.decodeResource(getResources(), R.drawable.car_1_r);
            healthBar.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.health_bar5));
        }else if (health == 4){
            car = BitmapFactory.decodeResource(getResources(), R.drawable.car_2);
            carPosL = BitmapFactory.decodeResource(getResources(), R.drawable.car_2_l);
            carPosR = BitmapFactory.decodeResource(getResources(), R.drawable.car_2_r);
            healthBar.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.health_bar4));
        }else if (health == 3){
            car = BitmapFactory.decodeResource(getResources(), R.drawable.car_3);
            carPosL = BitmapFactory.decodeResource(getResources(), R.drawable.car_3_l);
            carPosR = BitmapFactory.decodeResource(getResources(), R.drawable.car_3_r);
            healthBar.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.health_bar3));
        }else if (health == 2){
            car = BitmapFactory.decodeResource(getResources(), R.drawable.car_4);
            carPosL = BitmapFactory.decodeResource(getResources(), R.drawable.car_4_l);
            carPosR = BitmapFactory.decodeResource(getResources(), R.drawable.car_4_r);
            healthBar.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.health_bar2));
        }else if (health == 1){
            car = BitmapFactory.decodeResource(getResources(), R.drawable.car_5);
            carPosL = BitmapFactory.decodeResource(getResources(), R.drawable.car_5_l);
            carPosR = BitmapFactory.decodeResource(getResources(), R.drawable.car_5_r);
            healthBar.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.health_bar1));
        }else if (health == 0){
            healthBar.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.health_bar0));
            Intent intent = new Intent(getContext(),ScoreActivity.class);
            mCoin.stop();
            mCrash.stop();
            mHeal.stop();
            stopGame = true;
            getContext().startActivity(intent);
        }}else if (carNum==2){
            if (health == 5){
                car = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_1);
                carPosL = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_1_l);
                carPosR = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_1_r);
                healthBar.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.health_bar5));
            }else if (health == 4){
                car = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_2);
                carPosL = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_2_l);
                carPosR = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_2_r);
                healthBar.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.health_bar4));
            }else if (health == 3){
                car = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_3);
                carPosL = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_3_l);
                carPosR = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_3_r);
                healthBar.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.health_bar3));
            }else if (health == 2){
                car = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_4);
                carPosL = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_4_l);
                carPosR = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_4_r);
                healthBar.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.health_bar2));
            }else if (health == 1){
                car = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_5);
                carPosL = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_5_l);
                carPosR = BitmapFactory.decodeResource(getResources(), R.drawable.ycar_5_r);
                healthBar.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.health_bar1));
            }else if (health == 0){
                healthBar.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.health_bar0));
                Intent intent = new Intent(getContext(),ScoreActivity.class);
                mCoin.stop();
                mCrash.stop();
                mHeal.stop();
                stopGame = true;
                getContext().startActivity(intent);
            }
        }

    }
    public void addSpeed(int speed){
        frstHalf.setVy((frstHalf.getVy())+speed);
        scndHalf.setVy((scndHalf.getVy())+speed);
        coin.setVy((coin.getVy())+speed);
        coin2.setVy((coin2.getVy())+speed);
        coin3.setVy((coin3.getVy())+speed);
        enemy.setVy((enemy.getVy())+speed);
        heart.setVy((heart.getVy())+speed);
    }
    protected void update () {
        enemy.update(timerInterval);
        player.update(timerInterval);
        frstHalf.update(timerInterval);
        scndHalf.update(timerInterval);
        heart.update(timerInterval);
        coin.update(timerInterval);
        coin2.update(timerInterval);
        coin3.update(timerInterval);
        if (-100<=frstHalf.getY()&&frstHalf.getY()<=0) {
            teleportBackground(scndHalf);
        }
        if (-100<=scndHalf.getY()&&scndHalf.getY()<=0) {
            teleportBackground(frstHalf);
        }
        if (player.getX()+player.getFrameWidth() > viewWidth-200) {
            player.setVx(0);
        }
        else if (player.getX() < 200) {
            player.setVx(0);
        }

        count++;
        if(count%5==0){
        points=(points+1)*pointsMult;
        }
        if(points%100==0&&points!=0){
            teleportBody(heart);
        }
        if (points%100==15&&points!=0){
            teleportBody(coin);
        }
        if (points%100==50&points!=0){
            teleportBody(coin2);
        }
        if (points%100==70&points!=0){
            teleportBody(coin3);
        }
        if (enemy.getY() > viewHeight) {
            teleportBody(enemy);
        }
        if (points%100==0&&points!=0){
            addSpeed(20);
            pointsMult=(pointsMult+20/100);
        }
        if (enemy.intersect(player)) {
            teleportBody(enemy);
            health--;
            updatePos();
            if(soundsOn){
            mCrash.start();}
        }
        if(coin.intersect(player)){
           coin.setX(-1000);
           coins++;
           if(soundsOn){
            mCoin.start();}
        }
        if(coin2.intersect(player)){
            coin2.setX(-1000);
            coins++;
            if(soundsOn){
            mCoin.start();}
        }
        if(coin3.intersect(player)){
            coin3.setX(-1000);
            coins++;
            if(soundsOn){
            mCoin.start();}
        }

        if (heart.intersect(player)){
            heart.setX(-1000);
            if(health<5)
            {health++;
            updatePos();}
            if(soundsOn){
            mHeal.start();}
        }

        if (carPos==1){
            player.setBitmap(car);
        }else if (carPos==2){
            player.setBitmap(carPosL);
        }else if (carPos==3){
            player.setBitmap(carPosR);
        }

        invalidate();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewWidth = w;
        viewHeight = h;
    }
    public GameView(Context context) {
        super(context);

        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.health_bar5);
        int w = b.getWidth();
        int h = b.getHeight();
        Rect healthB = new Rect (0,0,w,h);
        healthBar = new Bars(0,10,0,0,healthB,b);
        b = BitmapFactory.decodeResource(getResources(),R.drawable.coin_bar);
        Rect coinB = new Rect(0,0,w,h);
        coinBar= new Bars(0 , 120,0,0,coinB,b);
        updatePos();

        b = BitmapFactory.decodeResource(getResources(), R.drawable.enemy);
        w = b.getWidth();
        h = b.getHeight();
        Rect firstFrame = new Rect(0, 0, w, h);
        enemy = new Sprite(-1000, -300, 0, 1000, firstFrame, b);

         b = car;
         w = b.getWidth();
         h = b.getHeight();
        firstFrame = new Rect(0, 0, w, h);
        player = new Sprite(450, 1250, 0, 0, firstFrame, b);

        b = Loc;
        w = b.getWidth();
        h = b.getHeight();
        Rect back1 = new Rect(0, 0, w, h);
        frstHalf = new Background(0, 0, 0, 400, back1, b);

        b = Loc;
        w = b.getWidth();
        h = b.getHeight();
        Rect back2 = new Rect(0, 0, w, h);
        scndHalf = new Background(0, -1920, 0, 400, back2, b);

        b = BitmapFactory.decodeResource(getResources(), R.drawable.heart_image);
        w = b.getWidth();
        h = b.getHeight();
        firstFrame = new Rect(0,0,w,h);
        heart = new Sprite(-1000,2000,0,400,firstFrame,b);

        b = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
        w = b.getWidth();
        h = b.getHeight();
        firstFrame = new Rect(0,0,w,h);
        coin = new Sprite(-1000,2000,0,400,firstFrame,b);
        coin2 = new Sprite(-1000,2000,0,400,firstFrame,b);
        coin3 = new Sprite(-1000,2000,0,400,firstFrame,b);

        Timer t = new Timer();
        t.start();

    }
    private void teleportBackground (Background half) {
        half.setY(-1920);

    }
    private void teleportBody(Sprite body){
        body.setX((int)((Math.random() * 4) + 1)*(viewWidth-400)/4+200-body.getFrameWidth());
        body.setY(-300);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        scndHalf.draw(canvas);
        frstHalf.draw(canvas);
        heart.draw(canvas);
        player.draw(canvas);
        coin.draw(canvas);
        coin2.draw(canvas);
        coin3.draw(canvas);
        enemy.draw(canvas);
        healthBar.draw(canvas);
        coinBar.draw(canvas);
        Paint paint = new Paint();

        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.coin_medium_image),10,125,paint);

        TextPaint p = new TextPaint();
        p.setAntiAlias(true);
        p.setTextSize(55.0f);
        p.setColor(Color.WHITE);
        p.setTypeface(Typeface.create("game_font",Typeface.BOLD));
        canvas.drawText(points+"", viewWidth-viewWidth/3, 100, p);
        p.setColor(getResources().getColor(R.color.ORANGE));
        canvas.drawText(coins+"", 120, 185, p);
    }

    class Timer extends CountDownTimer {
        public Timer() {
            super(Integer.MAX_VALUE, timerInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (!stopGame)update();
        }

        @Override
        public void onFinish() {
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN)  {
            if (event.getX() < player.getBoundingBoxRect().left && player.getX()>200) {
                player.setVx(-500);
                player.setBitmap(carPosL);
                carPos=2;
            }
            else if (event.getX() > (player.getBoundingBoxRect().right) && player.getX()+player.getFrameWidth() < viewWidth-200) {
                player.setVx(500);
                player.setBitmap(carPosR);
                carPos=3;
            }
        }else if(eventAction == MotionEvent.ACTION_UP) {
            player.setVx(0);
            player.setBitmap(car);
            carPos=1;
        }
        return true;
    }
}
