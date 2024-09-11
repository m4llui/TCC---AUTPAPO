package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Tela_Atv1_fase1 extends AppCompatActivity {

    MediaPlayer audio;
    boolean selecionouO, selecionouI, erroO, erroI;
    private ImageView btn_Let_A, btn_Let_A_Certo, btn_Let_B, btn_Let_B_Inc, btn_Let_C, btn_Let_C_Inc,
            btn_Let_D, btn_Let_D_Inc, btVoltar2, btEnunciado, btBalao;
    private Handler handler = new Handler();
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv1_fase1);

        btn_Let_A = findViewById(R.id.btn_Flor);
        btn_Let_A_Certo = findViewById(R.id.btn_Let_YCerto);
        btn_Let_B = findViewById(R.id.btn_Nariz);
        btn_Let_B_Inc = findViewById(R.id.btn_Let_A_errado);
        btn_Let_C = findViewById(R.id.btn_Zebra);
        btn_Let_C_Inc = findViewById(R.id.btn_Let_K_errado);
        btn_Let_D = findViewById(R.id.btn_Planeta);
        btn_Let_D_Inc = findViewById(R.id.btn_Let_Q_errado);
        btVoltar2 = findViewById(R.id.btnVoltarAtv7Fase2);
        btBalao = findViewById(R.id.imageBalaoLetraZ);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                playAudio(R.raw.qual_letraessa);
            }
        }, 1000);

        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.qual_letraessa);
            }
        });

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAudio(R.raw.letra_a);
            }
        });

        btVoltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent verificar se será necessário if e else
                Intent abrirHome =  new Intent(Tela_Atv1_fase1.this, Tela_Home.class);
                startActivity(abrirHome);
            }
        });


        botoesInativados();


        setOnClickListeners();
    }


    private void botoesInativados() {
        btn_Let_A_Certo.setVisibility(View.INVISIBLE);
        btn_Let_B_Inc.setVisibility(View.INVISIBLE);
        btn_Let_C_Inc.setVisibility(View.INVISIBLE);
        btn_Let_D_Inc.setVisibility(View.INVISIBLE);

        btn_Let_A_Certo.setEnabled(false);
        btn_Let_B_Inc.setEnabled(false);
        btn_Let_C_Inc.setEnabled(false);
        btn_Let_D_Inc.setEnabled(false);
    }


    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCorrect = false;

                int id = view.getId();

                if (id == R.id.btn_Flor) {
                    btn_Let_A_Certo.setVisibility(View.VISIBLE);
                    btn_Let_A_Certo.setEnabled(true);
                    playAudio(R.raw.letra_a);

                } else if (id == R.id.btn_Nariz) {
                    btn_Let_B_Inc.setVisibility(View.VISIBLE);
                    btn_Let_B_Inc.setEnabled(true);
                    playAudio(R.raw.letra_b);

                } else if (id == R.id.btn_Zebra) {
                    btn_Let_C_Inc.setVisibility(View.VISIBLE);
                    btn_Let_C_Inc.setEnabled(true);
                    playAudio(R.raw.letra_o);

                } else if (id == R.id.btn_Planeta) {
                    btn_Let_D_Inc.setVisibility(View.VISIBLE);
                    btn_Let_D_Inc.setEnabled(true);
                    playAudio(R.raw.letra_d);


                }

                // Salva o resultado no banco de dados
                //salvarResultadoNoBanco(isCorrect);


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv1_fase1.this, Tela_Atv2_fase1.class));
                        finish();//
                    }
                }, 2000);
            }
        };


        btn_Let_A.setOnClickListener(listener);
        btn_Let_B.setOnClickListener(listener);
        btn_Let_C.setOnClickListener(listener);
        btn_Let_D.setOnClickListener(listener);
    }

    private void playAudio(int audioResId) {

        if (audio != null) {
            audio.release();
        }

        audio = MediaPlayer.create(this, audioResId);
        audio.start();
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Utilize a setinha para voltar para home!", Toast.LENGTH_SHORT).show();
    }
}

