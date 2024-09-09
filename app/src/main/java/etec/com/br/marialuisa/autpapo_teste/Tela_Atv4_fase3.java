package etec.com.br.marialuisa.autpapo_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_Atv4_fase3 extends AppCompatActivity {

    MediaPlayer audio;
    TextView btEnunciado;
    private ImageView btSim, btSimCerto, btCoracao, btCoracaoErrado, btMorango, btMorangoErrado,
            btMaos, btMaosErrado, btVolta, btBalao;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atv4_fase3);

        //REFERENCIANDO
        btEnunciado = findViewById(R.id.txtEnunImg);
        btBalao = findViewById(R.id.balao_sim);
        btSim = findViewById(R.id.btn_sim_joia);
        btSimCerto = findViewById(R.id.btn_sim_joia_certo);
        btCoracao = findViewById(R.id.btn_coracao);
        btCoracaoErrado = findViewById(R.id.btn_coracao_errada);
        btMorango = findViewById(R.id.btn_morango4);
        btMorangoErrado = findViewById(R.id.btn_morango4_errado);
        btMaos = findViewById(R.id.btn_maos);
        btMaosErrado = findViewById(R.id.btn_maos_errada);
        btVolta = findViewById(R.id.btnVoltarAtv4Fase3);

        // Toca o áudio uma vez quando a Activity é carregada ESTÁ DUPLICANDO ESTE AUDIO
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (audio == null || !audio.isPlaying()) {
                    playAudio(R.raw.enun_escolha_figplvra);
                }
            }
        }, 1000); // Atraso de 1,1 segundo

        btBalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.sim);
            }
        });

        btEnunciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(R.raw.enun_escolha_figplvra);
            }
        });

        //BOTAO VOLTAR
        btVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent verificar se será necessário if e else
                Intent abrirHome =  new Intent(Tela_Atv4_fase3.this, Tela_Home.class);
                startActivity(abrirHome);
            }
        });

        // Inicializa os botões desativados e invisíveis
        botoesInativados();

        // Configura os OnClickListeners
        setOnClickListeners();

    }

    private void botoesInativados() {
        // Inicializa os botões "certo" e "errado" como invisíveis
        btSimCerto.setVisibility(View.INVISIBLE);
        btCoracaoErrado.setVisibility(View.INVISIBLE);
        btMorangoErrado.setVisibility(View.INVISIBLE);
        btMaosErrado.setVisibility(View.INVISIBLE);

        // Inicializa os botões "certo" e "errado" como desativados
        btSimCerto.setEnabled(false);
        btCoracaoErrado.setEnabled(false);
        btMorangoErrado.setEnabled(false);
        btMaosErrado.setEnabled(false);
    }

    private void setOnClickListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();

                if (id == R.id.btn_sim_joia) {
                    btSimCerto.setVisibility(View.VISIBLE);
                    btSimCerto.setEnabled(true);

                } else if (id == R.id.btn_coracao) {
                    btCoracaoErrado.setVisibility(View.VISIBLE);
                    btCoracaoErrado.setEnabled(true);

                } else if (id == R.id.btn_morango4) {
                    btMorangoErrado.setVisibility(View.VISIBLE);
                    btMorangoErrado.setEnabled(true);

                } else if (id == R.id.btn_maos) {
                    btMaosErrado.setVisibility(View.VISIBLE);
                    btMaosErrado.setEnabled(true);

                }

                // HANDLER É QUEM FAZ O ATRASO ANTES DE IR PARA A PRÓXIMA TELA
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Tela_Atv4_fase3.this, Tela_Atv5_fase3.class));
                        finish(); // Fecha a tela atual
                    }
                }, 2000); // Atraso de 2 segundos em milissegundos
            }
        };

        // Configura o mesmo listener para todos os botões
        btMorango.setOnClickListener(listener);
        btMaos.setOnClickListener(listener);
        btSim.setOnClickListener(listener);
        btCoracao.setOnClickListener(listener);
    }

    private void playAudio(int audioResId) {
        // Libere o MediaPlayer anterior, se houver
        if (audio != null) {
            audio.release();
        }
        // Cria um novo MediaPlayer e toca o áudio
        audio = MediaPlayer.create(this, audioResId);
        audio.start();

        // Listener para liberar o MediaPlayer após a reprodução
        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                audio = null;
            }
        });
    }
    //BLOQUEIO DO BOTÃO VOLTAR DO CELULAR
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Utilize a setinha para voltar para home!", Toast.LENGTH_SHORT).show();
       }
    }