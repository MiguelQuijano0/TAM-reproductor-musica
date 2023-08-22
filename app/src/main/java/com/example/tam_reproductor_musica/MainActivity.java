package com.example.tam_reproductor_musica;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button play_pause, btn_repetir;
    MediaPlayer mp;
    ImageView iv;
    int repetir = 2, posicion = 0;

    MediaPlayer vectormp[] = new MediaPlayer[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_pause = (Button) findViewById(R.id.btn_play);
        btn_repetir = (Button) findViewById(R.id.btn_repetir);
        iv = (ImageView) findViewById(R.id.imageView);

        vectormp[0] = MediaPlayer.create(this, R.raw.himno_nacional_mexicano_en_maya);
        vectormp[1] = MediaPlayer.create(this, R.raw.himno_de_yucatan);
        vectormp[2] = MediaPlayer.create(this, R.raw.himno_a_felipe_carrillo_puerto);

    }

    // Metodo para el boton PlayPause
    public void PlayPause(View view) {
        if (vectormp[posicion].isPlaying()) {
            vectormp[posicion].pause();
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Pausa", Toast.LENGTH_LONG).show();
        } else {
            vectormp[posicion].start();
            play_pause.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "Esta escuchando himno en maya", Toast.LENGTH_LONG).show();
        }


    }
    // Metodo para el boton stop
    public void Stop(View view) {
        if (vectormp[posicion] != null) {
            vectormp[posicion].stop();

            vectormp[0] = MediaPlayer.create(this, R.raw.himno_nacional_mexicano_en_maya);
            vectormp[1] = MediaPlayer.create(this, R.raw.himno_de_yucatan);
            vectormp[2] = MediaPlayer.create(this, R.raw.himno_a_felipe_carrillo_puerto);

            posicion = 0;
            play_pause.setBackgroundResource(R.drawable.reproducir);
            iv.setImageResource(R.drawable.portada1);
            Toast.makeText(this, "stop", Toast.LENGTH_LONG).show();
        }
    }

    // Metodo para repetir pista
    public void Repetir (View view) {
        if (repetir == 1) {
            btn_repetir.setBackgroundResource(R.drawable.no_repetir);
            Toast.makeText(this, "No repetir", Toast.LENGTH_LONG).show();
            vectormp[posicion].setLooping(false);
            repetir = 2;
        } else {
            btn_repetir.setBackgroundResource(R.drawable.repetir);
            Toast.makeText(this, "Repetir", Toast.LENGTH_LONG).show();
            vectormp[posicion].setLooping(true);
            repetir = 1;
        }
    }

    // Metodo para salto de cancion
    public void Siguiente (View view) {
        if (posicion < vectormp.length -1) {

            if(vectormp[posicion].isPlaying()) {
                vectormp[posicion].stop();
                posicion ++;
                vectormp[posicion].start();

                if(posicion == 0) {
                    iv.setImageResource(R.drawable.portada1);
                    Toast.makeText(this, "Esta escuchando himno en maya", Toast.LENGTH_LONG).show();
                } else if(posicion == 1) {
                    iv.setImageResource(R.drawable.portada2);
                    Toast.makeText(this, "Esta escuchando himno de yucatan", Toast.LENGTH_LONG).show();
                } else if(posicion == 2) {
                    iv.setImageResource(R.drawable.portada3);
                    Toast.makeText(this, "Esta escuchando himno a felipe", Toast.LENGTH_LONG).show();
                }
            } else {
                posicion ++;

                if(posicion == 0) {
                    iv.setImageResource(R.drawable.portada1);
                } else if(posicion == 1) {
                    iv.setImageResource(R.drawable.portada2);
                } else if(posicion == 2) {
                    iv.setImageResource(R.drawable.portada3);
                }
            }
        } else {
            Toast.makeText(this, "No hay mas canciones", Toast.LENGTH_LONG).show();
        }
    }

    // Metodo para regreso de la cancion
    public void Anterior (View view) {
        if(posicion >= 1) {

            if(vectormp[posicion].isPlaying()) {
                vectormp[posicion].stop();
                vectormp[0] = MediaPlayer.create(this, R.raw.himno_nacional_mexicano_en_maya);
                vectormp[1] = MediaPlayer.create(this, R.raw.himno_de_yucatan);
                vectormp[2] = MediaPlayer.create(this, R.raw.himno_a_felipe_carrillo_puerto);

                posicion--;

                if(posicion == 0) {
                    iv.setImageResource(R.drawable.portada1);
                } else if(posicion == 1) {
                    iv.setImageResource(R.drawable.portada2);
                } else if(posicion == 2) {
                    iv.setImageResource(R.drawable.portada3);
                }
                vectormp[posicion].start();

            } else {
                posicion--;
                if(posicion == 0) {
                    iv.setImageResource(R.drawable.portada1);
                } else if(posicion == 1) {
                    iv.setImageResource(R.drawable.portada2);
                } else if(posicion == 2) {
                    iv.setImageResource(R.drawable.portada3);
                }
            }
        } else {
            Toast.makeText(this, "No hay mas canciones", Toast.LENGTH_LONG).show();
        }
    }
}