package emirim0223.kr.hs.emirim.audiolist;

import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    ListView list;
    Button butPlay, butStop,butPause;
    TextView textMusic,TextTime;
    MediaPlayer musicPlayer;
    SeekBar progress;
    String[] musics = {"boneafour","iwish","mrpotter","sandeul","staywithme","whoareyou"};
    int[] musicResIds = {R.raw.boneafour,R.raw.iwish,R.raw.mrpotter,R.raw.sandeul,R.raw.staywithme,R.raw.whoareyou};
    int selectedMusicId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.List_music);
        butPlay = (Button)findViewById(R.id.but_play);
        butStop = (Button)findViewById(R.id.but_stop);
        butPause = (Button)findViewById(R.id.but_pause);
        textMusic = (TextView)findViewById(R.id.text_music);
        TextTime = (TextView)findViewById(R.id.text_time);
        progress = (SeekBar) findViewById(R.id.progress_music);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,musics);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setItemChecked(0,true);
        selectedMusicId=musicResIds[0];
        musicPlayer = MediaPlayer.create(MainActivity.this,selectedMusicId);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                musicPlayer.stop();
                selectedMusicId = musicResIds[i];
                progress.setVisibility(View.INVISIBLE);
                MainActivity.this.i=1;

            }
        });
        butPlay.setOnClickListener(new View.OnClickListener() {
            SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
            @Override
            public void onClick(View view) {
                musicPlayer = MediaPlayer.create(MainActivity.this,selectedMusicId);
                musicPlayer.start();
                progress.setVisibility(View.VISIBLE);
                Thread musicThread = new Thread(){
                    @Override
                    public void run() {
                        if(musicPlayer == null){
                            return;
                        }
                        progress.setMax(musicPlayer.getDuration());
                            progress.setProgress(musicPlayer.getCurrentPosition());
                        TextTime.setText("진행시간 : "+timeFormat.format(musicPlayer.getCurrentPosition()));
                        SystemClock.sleep(200);
                    }
                };
                musicThread.start();

            }
        });
        butStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlayer.stop();
                progress.setVisibility(View.INVISIBLE);
            }
        });

        butPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlayer.pause();
                progress.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        musicPlayer.stop();
    }
}
