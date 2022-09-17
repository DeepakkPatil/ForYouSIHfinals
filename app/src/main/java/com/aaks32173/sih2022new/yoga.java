package com.aaks32173.sih2022new;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import pl.droidsonroids.gif.GifImageView;

public class yoga extends AppCompatActivity {
    String videoUrl9=null;
    String videoUrl8=null;
    String videoUrl6=null;
    String videoUrl7=null;
    String videoUrl5=null;
    String videoUrl4=null;
    String videoUrl3=null;
    String videoUrl = null;
    String videoUrl2=null;
    LinearLayout linearLayout;
      @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_yoga);

          getSupportActionBar().setDisplayHomeAsUpEnabled(true);

          String category = getIntent().getExtras().getString("category");


          TextView heading=findViewById(R.id.head);
            ImageButton btn1 = findViewById(R.id.btn1);
          ImageView background= findViewById(R.id.background) ;
          ImageButton btn4 = findViewById(R.id.btn4);
          ImageButton btn2 = findViewById(R.id.btn2);
          ImageButton btn3 = findViewById(R.id.btn3);
          ImageButton btn5 = findViewById(R.id.btn5);
          ImageButton btn6 = findViewById(R.id.btn6);
          ImageButton btn7 = findViewById(R.id.btn7);
          ImageButton btn8 = findViewById(R.id.btn8);
          linearLayout = findViewById(R.id.giflinearlayout);
          LottieAnimationView gif2 =findViewById(R.id.gif2);
          GifImageView gif1=findViewById(R.id.gif1);
          linearLayout = findViewById(R.id.giflinearlayout);
          ImageButton btn9 = findViewById(R.id.btn9);
          if( category.equals("exercises")){

              videoUrl="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fyoga%2Fvideoplayback%20(1).mp4?alt=media&token=3a92fa5d-8f01-43a8-8460-d2235221fa54";
              videoUrl2="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fyoga%2Fvideoplayback%20(2).mp4?alt=media&token=1066c402-f42b-4cb4-adb2-f7bbe8b0bd23";
              videoUrl3="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fyoga%2Fvideoplayback%20(3).mp4?alt=media&token=c247b817-bc3e-4bbc-8ce6-d5d49ed77c84";
              videoUrl4="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fyoga%2Fvideoplayback%20(4).mp4?alt=media&token=7b1938a0-552c-45e2-b571-17cc41a7b586";
              videoUrl5="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fyoga%2Fvideoplayback.mp4?alt=media&token=01b76b1f-3337-4270-a089-59cab6dce358";
              videoUrl6="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fyoga%2Fvideoplayback%20(7).mp4?alt=media&token=d90b5430-e0aa-472f-9fa7-f88d58299734";
              videoUrl7="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/7th-9th%2Fyoga%2Fvideoplayback%20(8).mp4?alt=media&token=e171c2d8-f31b-4214-ad17-5dc1c5761634";
              videoUrl9 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fyogas%2Fanxiety_release_yoga5.mp4?alt=media&token=ff509dbb-24f9-4d8c-921c-fd16e687cf2e";
              videoUrl8 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fyogas%2Fanxiety_release_yoga4.mp4?alt=media&token=d04e8c49-885a-4713-9d87-5406eae0d5cc";
          }
          else if(category.equals("exercises1")){
              background.setImageResource(R.drawable.yogabgcheck);
              heading.setVisibility(View.GONE);
              gif2.setVisibility(View.INVISIBLE);
              gif1.setImageResource(R.drawable.yogagifcheck);
              gif1.setVisibility(View.GONE);
              gif1.setVisibility(View.GONE);
//yoga
              videoUrl9 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fyogas%2Fanxiety_release_yoga5.mp4?alt=media&token=ff509dbb-24f9-4d8c-921c-fd16e687cf2e";
              videoUrl8 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fyogas%2Fanxiety_release_yoga4.mp4?alt=media&token=d04e8c49-885a-4713-9d87-5406eae0d5cc";
              videoUrl6 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fyogas%2Fanxiety_release_yoga2.mp4?alt=media&token=479e5819-5e8d-45ac-bc57-ef7c812a5d32";
              videoUrl7 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fyogas%2Fanxiety_release_yoga3.mp4?alt=media&token=1dcf914f-c059-4f30-ad98-f4ff2f1d24a0";
              videoUrl5 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fyogas%2Fanxiety_release_yoga.mp4?alt=media&token=7ac66073-e040-4268-8385-3df8acc266b5";
              videoUrl4 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fyogas%2Fneck_streching_yoga.mp4?alt=media&token=4834a212-a6e8-47f1-979b-bda7fc5bf124";
              videoUrl3 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fyogas%2Ffocus_build_yoga.mp4?alt=media&token=ac306705-5d17-419b-b010-c4f001603aae";
              videoUrl = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fyogas%2Fconcentration_build_yoga1.mp4?alt=media&token=7208a3c5-5d4c-4a79-82e0-cca9cd53e1a9";
              videoUrl2 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fyogas%2Fconcentration_build_yoga2.mp4?alt=media&token=0abd607f-9ff1-43df-a3ae-cea3d2b7d446";
          }
          else if(category.equals("health")){
              background.setImageResource(R.drawable.gk_bg2);
              heading.setVisibility(View.INVISIBLE);
              gif2.setVisibility(View.INVISIBLE);
              gif1.setImageResource(R.drawable.food_gif);

              videoUrl9="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fquiz1.mp4?alt=media&token=2a3e42b5-8d3b-4c2b-8de6-6b2c34750c1f";
              videoUrl8="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fquiz2.mp4?alt=media&token=e6c25d0f-3c6e-48ca-be0b-5c0597a99d74";
              videoUrl6 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fquiz3.mp4?alt=media&token=5ddd34ed-a5f4-4fa0-b9ce-dc434f68337a";
              videoUrl7="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fquiz4.mp4?alt=media&token=16aaea66-a049-4867-8f8a-0de8251cae14";
              videoUrl5 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback%20(1).mp4?alt=media&token=0aebfebc-cba0-4cbb-9b26-88bdcb99f160";
              videoUrl4 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback%20(2).mp4?alt=media&token=faafabcd-2e68-46d3-b129-f266e8895443";
              videoUrl3 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback.mp4?alt=media&token=cd9302c6-896f-45b9-bb74-72e3fb3b90b9";
              videoUrl ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback.mp4?alt=media&token=cd9302c6-896f-45b9-bb74-72e3fb3b90b9";
              videoUrl2 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback.mp4?alt=media&token=cd9302c6-896f-45b9-bb74-72e3fb3b90b9";

          }
          else if(category.equals("health")){
                       background.setImageResource(R.drawable.gk_bg2);
              heading.setVisibility(View.INVISIBLE);
              gif2.setVisibility(View.INVISIBLE);
              gif1.setImageResource(R.drawable.food_gif);

              videoUrl9="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fquiz1.mp4?alt=media&token=2a3e42b5-8d3b-4c2b-8de6-6b2c34750c1f";
              videoUrl8="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fquiz2.mp4?alt=media&token=e6c25d0f-3c6e-48ca-be0b-5c0597a99d74";
              videoUrl6 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fquiz3.mp4?alt=media&token=5ddd34ed-a5f4-4fa0-b9ce-dc434f68337a";
              videoUrl7="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fquiz4.mp4?alt=media&token=16aaea66-a049-4867-8f8a-0de8251cae14";
              videoUrl5 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback%20(1).mp4?alt=media&token=0aebfebc-cba0-4cbb-9b26-88bdcb99f160";
              videoUrl4 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback%20(2).mp4?alt=media&token=faafabcd-2e68-46d3-b129-f266e8895443";
              videoUrl3 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback.mp4?alt=media&token=cd9302c6-896f-45b9-bb74-72e3fb3b90b9";
              videoUrl ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback.mp4?alt=media&token=cd9302c6-896f-45b9-bb74-72e3fb3b90b9";
              videoUrl2 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback.mp4?alt=media&token=cd9302c6-896f-45b9-bb74-72e3fb3b90b9";

          }
        else if(category.equals("health1")){

            background.setImageResource(R.drawable.bg_poem);

              heading.setText("Healthy Habits");
              gif2.setVisibility(View.INVISIBLE);
              gif1.setImageResource(R.drawable.health35);
              btn1.setImageResource(R.drawable.health1);
              btn2.setImageResource(R.drawable.health2);
              btn3.setImageResource(R.drawable.health3);
              btn4.setImageResource(R.drawable.health4);
              btn5.setImageResource(R.drawable.health5);
              btn6.setImageResource(R.drawable.health1);
              btn7.setImageResource(R.drawable.health2);
              btn8.setImageResource(R.drawable.health3);
              btn9.setImageResource(R.drawable.health5);
          videoUrl9="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fhealth%2FIs%20Sugar%20Bad%20For%20You_%20_%20What%20SUGAR%20Does%20To%20Our%20Body_%20_%20Dr%20Binocs%20Show%20_%20Peekaboo%20Kidz.mp4" +
                  "?alt=media&token=037699cb-5d48-4f44-85b5-e7fb81dd9cae";
          videoUrl8="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fhealth%2FWellbeing%20for%20Children_%20Healthy%20Habits.mp4?alt=media&tok" +
                  "en=eb4f0015-3351-453e-a6b7-3a253ed6ed7f";
          videoUrl6 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fhealth%2FWhat%20Causes%20Conjunctivitis_%20_%20CONJUNCTIVITIS%20_%20Pink-Eye%20_%20The%20Dr%20Binocs%20Show%20_%20Peekaboo%20Kidz.mp4?alt=media&token=" +
                  "b49d872d-dc18-4e5e-abbc-a693c913be50";
          videoUrl7="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fhealth%2FWhat%20Causes%20Diarrhea_%20-%20The%20Dr.%20Binocs%20Show%20_%20Best%20Learning%20Videos%20For%20Kids%20_%20Peekaboo%20Kidz.mp4?alt=m" +
                  "edia&token=bd1d85e1-fbf3-4a07-a5cd-322c85a67581";
          videoUrl5 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fhealth%2FWhat%20If%20We%20Stop%20Brushing%20Teeth_%20_%20Why%20Do%20We%20BRUSH%20TEETH_%20_%20Dr%20Binocs%20Show%20_%20Peekaboo%20Kidz.mp4?alt=media&token=ab1" +
                  "94c0a-e36b-428c-846a-818f089c16ec";
          videoUrl4 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fhealth%2FWhat%20If%20We%20Stopped%20Washing%20Hair_%20_%20Importance%20of%20HAIR%20WASH%20_%20The%20Dr%20Binocs%20Show%20_%20Peekaboo%20Kidz.mp4?alt=" +
                  "media&token=19d3d225-5086-47a8-9bf7-fafdf4a9ef8a";
          videoUrl3 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fhealth%2FWhy%20Appendix%20Burst_%20_%20APPENDIX%20_%20Dr%20Binocs%20Show%20_%20Peekaboo%20Kidz" +
                  ".mp4?alt=media&token=8f43aab8-46e9-4e96-a19d-e832792d6de0";
          videoUrl ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fhealth%2FWhy%20Do%20We%20Drink%20Water_%20_%20Importance%20Of%20Water%20_%20Stay%20Hydrated%20_%20The%20Dr%20Binocs%20Show%20_%20Peekaboo%20Kidz.mp4?alt=medi" +
                  "a&token=8b94a616-39b7-48a6-bedf-eb635b865912";
          videoUrl2 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fhealth%2FWhy%20Do%20We%20Puke_%20_%20The%20Dr.%20Binocs%20Show%20_%20Best%20Learning%20Videos%20For%20Kids%20_%20Peekaboo%20Kidz.mp4?alt=media&token=92" +
                  "a5fef9-8a3b-498e-b403-8c584f3741bd";

      }
          else if(category.equals("gksection")){

              background.setImageResource(R.drawable.evai);

              heading.setText("Did you Know?");
              gif2.setVisibility(View.INVISIBLE);
              gif1.setImageResource(R.drawable.quiz);

              btn1.setImageResource(R.drawable.gk1);
              btn2.setImageResource(R.drawable.gk2);
              btn3.setImageResource(R.drawable.gk3);
              btn4.setImageResource(R.drawable.gk4);
              btn5.setImageResource(R.drawable.gk5);
              btn6.setImageResource(R.drawable.gk6);
              btn7.setImageResource(R.drawable.gk7);
              btn8.setImageResource(R.drawable.gk1);
              btn9.setImageResource(R.drawable.gk2);
              videoUrl9="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fquiz1.mp4?alt=media&token=2a3e42b5-8d3b-4c2b-8de6-6b2c34750c1f";
              videoUrl8="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fquiz2.mp4?alt=media&token=e6c25d0f-3c6e-48ca-be0b-5c0597a99d74";
              videoUrl6 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fquiz3.mp4?alt=media&token=5ddd34ed-a5f4-4fa0-b9ce-dc434f68337a";
              videoUrl7="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fquiz4.mp4?alt=media&token=16aaea66-a049-4867-8f8a-0de8251cae14";
              videoUrl5 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback%20(1).mp4?alt=media&token=0aebfebc-cba0-4cbb-9b26-88bdcb99f160";
              videoUrl4 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback%20(2).mp4?alt=media&token=faafabcd-2e68-46d3-b129-f266e8895443";
              videoUrl3 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback.mp4?alt=media&token=cd9302c6-896f-45b9-bb74-72e3fb3b90b9";
              videoUrl ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback.mp4?alt=media&token=cd9302c6-896f-45b9-bb74-72e3fb3b90b9";
              videoUrl2 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FG.K%2Fvideoplayback.mp4?alt=media&token=cd9302c6-896f-45b9-bb74-72e3fb3b90b9";

          }
          else if(category.equals("fightyourfear")){

              background.setImageResource(R.drawable.fightfear_bg);
              heading.setText("Fight Your Fears");
              gif2.setVisibility(View.INVISIBLE);
              new Handler().postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      gif1.setImageResource(R.drawable.fight_fear_gif2);
                  }
              }, 8000);

              gif1.setImageResource(R.drawable.fightfeargif2);

              btn1.setImageResource(R.drawable.fear1);
              btn2.setImageResource(R.drawable.fear2);
              btn3.setImageResource(R.drawable.fear3);
              btn4.setImageResource(R.drawable.fear4);
              btn5.setImageResource(R.drawable.fear5);
              btn6.setImageResource(R.drawable.fear1);
              btn7.setImageResource(R.drawable.fear3);
              btn8.setImageResource(R.drawable.fear2);
              btn9.setImageResource(R.drawable.fear4);

               videoUrl9="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FFight_your_fear%2F100%20Kids%20Tell%20Us%20Their%20Fears%20%F0%9F%91%B9%F0%9F%8E%83%F0%9F%91%BB%20_%20100%20Kids%20_%20HiHo%20Kids.mp4?alt=media&token=05d8" +
                      "1a20-a16b-44eb-8c50-46ed6044c298";
               videoUrl8="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FFight_your_fear%2FFight%20Your%20Fears%20to%20Be%20Victorious%20_%20Coward%20Pompu%20Story%20_%20Moral%20Stories%20By%20Granny%20_%20Woka%20English.mp4?a" +
                      "lt=media&token=20ce7cee-3f0c-4c2a-9a86-bc8d952cd767";
               videoUrl6 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FFight_your_fear%2FFinding%20Your%20Voice%20From%20a%20Kid%20Who%20Had%20Stage%20Fright%20_%20Eamonn%20Kennedy%20_%20TEDxStJosephsSchoolYorkville.mp4?" +
                      "alt=media&token=0fe6cf6b-a684-4934-bb30-c7523adaf08e";
              videoUrl7="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FFight_your_fear%2FProven%20Strategies%20to%20Help%20Children%20Overcome%20Fears%20_%20Age-Related%20Fears%20_%20Why%20is%20my%20Child%20Afraid_.mp4?alt=media&token=3f52b" +
                      "d34-b57e-41a9-a797-132331363b13";
               videoUrl5 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FFight_your_fear%2FSmall%20Talk%20_%20Fears%20_%20CBC%20Kids.mp4?alt=media&token=ae6555ec-3bfe-" +
                      "4a8a-9ddb-4f215b3964d9";
               videoUrl4 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FFight_your_fear%2Fvideoplayback%20(1).mp4?alt=media&token=c71" +
                      "28b63-618e-45de-95f4-e35d43a07511";
               videoUrl3 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FFight_your_fear%2Fvideoplayback%20(1).mp4?alt=media&token=c" +
                      "7128b63-618e-45de-95f4-e35d43a07511";
               videoUrl = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FFight_your_fear%2Fvideoplayback%20(2).mp4?alt=media&token=cea6a8c5-b4a1-4792-9728-1440" +
                      "545d081f";
               videoUrl2 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2FFight_your_fear%2Fvideoplayback%20(2).mp4?alt=media&token=cea6a8c5-b4a1-" +
                      "4792-9728-1440545d081f";
          }
          else if(category.equals("stories")){

              background.setImageResource(R.drawable.fightfear_bg);
              heading.setText("Stories");
              gif2.setVisibility(View.INVISIBLE);
              gif1.setImageResource(R.drawable.ttrain);

              btn1.setImageResource(R.drawable.story1);
              btn2.setImageResource(R.drawable.story2);
              btn3.setImageResource(R.drawable.story3);
              btn4.setImageResource(R.drawable.story4);
              btn5.setImageResource(R.drawable.story5);
              btn6.setImageResource(R.drawable.story6);
              btn7.setImageResource(R.drawable.story7);
              btn8.setImageResource(R.drawable.story8);
              btn9.setImageResource(R.drawable.story1);

              videoUrl9="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fpodcast%2Fvideoplayback%20(1).mp4?alt=media&token=03fb5dee-5dd1-4636-95ab-e0e600140ce5";
              videoUrl8="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fpodcast%2Fvideoplayback%20(2).mp4?alt=media&token=94fb88f1-ddcc-46c2-9ec1-281a7d8bfbd8";
              videoUrl6 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fpodcast%2Fvideoplayback%20(3).mp4?alt=media&token=f08c7496-28a8-4870-b223-76569da775e4";
              videoUrl7="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fpodcast%2Fvideoplayback%20(4).mp4?alt=media&token=570aae37-61c2-40c0-a554-c72b92e77597";
              videoUrl5 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fpodcast%2Fvideoplayback%20(5).mp4?alt=media&token=bfefd090-ca92-40a4-bb4b-0fc23710c5fa";
              videoUrl4 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fpodcast%2Fvideoplayback%20(6).mp4?alt=media&token=330db1b2-01ad-4590-b609-76b40869c062";
              videoUrl3 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fpodcast%2Fvideoplayback%20(7).mp4?alt=media&token=308e8eb6-6bf8-46db-9a69-357b42bc4305";
              videoUrl ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fpodcast%2Fvideoplayback%20(6).mp4?alt=media&token=330db1b2-01ad-4590-b609-76b40869c062";
              videoUrl2 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Fpodcast%2Fvideoplayback%20(7).mp4?alt=media&token=308e8eb6-6bf8-46db-9a69-357b42bc4305";

          }
         else if(category.equals("rhymes")) {

             heading.setText("Rhymes");
              background.setImageResource(R.drawable.bg_poem);
             gif2.setAnimation(R.raw.unicorn);
             gif1.setVisibility(View.INVISIBLE);
              btn1.setImageResource(R.drawable.rhyme1);
              btn2.setImageResource(R.drawable.rhyme5);
              btn3.setImageResource(R.drawable.rhyme3);
              btn4.setImageResource(R.drawable.rhyme4);
              btn5.setImageResource(R.drawable.rhyme2);
              btn6.setImageResource(R.drawable.rhyme6);
              btn7.setImageResource(R.drawable.rhyme7);
              btn8.setImageResource(R.drawable.rhyme8);
              btn9.setImageResource(R.drawable.rhyme9);
//
              videoUrl9="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Frhymes%2Fvideoplayback%20(1).mp4?alt=media&token=f4681b94-8e25-493f-903e-db18bc31f0ba";
              videoUrl8="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Frhymes%2Fvideoplayback%20(2).mp4?alt=media&token=2129d043-103a-4204-8b01-d3cdbb0d9936";
              videoUrl6 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/exercises%2Fyogas%2Fanxiety_release_yoga2.mp4?alt=media&token=479e5819-5e8d-45ac-bc57-ef7c812a5d32";
              videoUrl7="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Frhymes%2Fvideoplayback%20(3).mp4?alt=media&token=44236090-a52c-4cd2-9214-9d53a6053e39";
              videoUrl5 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Frhymes%2Fvideoplayback%20(5).mp4?alt=media&token=94c40c63-122c-4ced-882c-27c76f16e310";
              videoUrl4 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Frhymes%2Fvideoplayback%20(6).mp4?alt=media&token=ce3fbaa0-0a2a-49f2-8f7f-9afd9edb15f1";
              videoUrl3 ="https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Frhymes%2Fvideoplayback%20(7).mp4?alt=media&token=f1b9bb6e-4d24-43ec-b232-c032f5146e0f";
              videoUrl = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Frhymes%2Fvideoplayback%20(8).mp4?alt=media&token=37fbb7e8-0f98-4c15-a7bc-576206775fb6";
              videoUrl2 = "https://firebasestorage.googleapis.com/v0/b/sih2022-15182.appspot.com/o/kids%2Frhymes%2Fvideoplayback.mp4?alt=media&token=11b9d95e-0648-4ecb-866f-bea3d43b6117";


          }


            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vedioPlayer(videoUrl);
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vedioPlayer(videoUrl2);
                }
            });
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vedioPlayer(videoUrl3);
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vedioPlayer(videoUrl4);
                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vedioPlayer(videoUrl5);
                }
            });
            btn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vedioPlayer(videoUrl6);
                }
            });
            btn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vedioPlayer(videoUrl7);
                }
            });
            btn8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vedioPlayer(videoUrl8);
                }
            });
            btn9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vedioPlayer(videoUrl9);
                }
            });
        }
        private void vedioPlayer(String url){
            Intent intent = new Intent(this,vedioPlay.class);
            intent.putExtra("url", url);
            intent.putExtra("nxt", "yoga");
            startActivity(intent);

        }
}