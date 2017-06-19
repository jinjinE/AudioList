package emirim0223.kr.hs.emirim.audiolist;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView list;
    Button butPlay, butStop;
    TextView textMusic;
    ProgressBar progress;
    MediaPlayer musicPlayer;
    String[] musics = {"boneafour","iwish","mrpotter","sandeul","staywithme","whoareyou"};
    int[] musicResIds = {R.raw.boneafour,R.raw.iwish,R.raw.mrpotter,R.raw.sandeul,R.raw.staywithme,R.raw.whoareyou};
    int selectedMusicId;
    musicPlayer = MediaPlayer.create(this,);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.List_music);
        butPlay = (Button)findViewById(R.id.but_play);
        butStop = (Button)findViewById(R.id.but_stop);
        textMusic = (TextView)findViewById(R.id.text_music);
        progress = (ProgressBar)findViewById(R.id.progress_music);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,musics);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setItemChecked(0,true);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMusicId = musicResIds[i];
            }
        });
        butPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlayer.start();
            }
        });
        butStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlayer.stop();
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        musicPlayer.onStop();
    }
}
