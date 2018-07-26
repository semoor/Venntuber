package com.semtube.videodowner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.semtube.videodowner.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String[] tit = new String[]{"Sanki bu gün biraz canın sıkkın\nSana müzik iyi gelir", "Kanka Nasılsın?", "Naber?\nİşler Yolundamı?", "Herşeyi Kafana Takma!"
            , "Kendini çok yorma\nGit biraz dinlen!", "Birader uygulamaya\n5 yıldız vermeyi unutma!", "Hayatınızın kalitesini hayatınızdaki\ninsanların kalitesi belirler.", "Yükleniyor...", "Biraz bekle!\nYükleniyor", "Başarı, istediğini elde\netmek, mutluluk ise,\nelde ettiğini sevmektir.", "Peşinden gidecek cesaretin varsa,\nbütün hayaller gerçek olabilir!", "Günümüzde insanlar, yalnızca\nfiyatı biliyorlar, değeri değil.", "Mutluluğu tatmanın tek\nçaresi, onu paylaşmaktır.", "Akıllı telefonmuş.\nKarşı taraf aptal olunca, telefon\nakıllı olsa bile işe yaramıyor.", "Hayat illegal!", "Eğer hala yalnızsanız, Allah\nsizi birilerinden koruyor demektir."
    ,"Gidenin arkasından nokta\nkoyun ki, gelecek olanın\nismi büyük harfle başlasın.","İnsan beklentisi kadar mutludur.\nFormül: Sıfır beklenti, sonsuz mutluluk.","Hayat avucundaki su gibidir;\nsen tutmaya çalıştıkça o akıp gider.","Sırtından vurana kızma arkanı\ndönen sensin, arkandan konuşana da\ndarılma adam yerine koyan sensin."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LottieAnimationView animationView = findViewById(R.id.animation_view);
        TextView y=findViewById(R.id.textView3);
        Random random = new Random();
        Integer i=random.nextInt(tit.length - 0 + 1) + 0;
        y.setText(tit[i]);
        animationView.setAnimation(R.raw.pink_drink_machine);

        animationView.playAnimation();
    }
}
