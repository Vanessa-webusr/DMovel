package com.example.atividadeavaliativa1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.atividadeavaliativa1.data.Evento;
import com.example.atividadeavaliativa1.data.EventoDAO;
import com.example.atividadeavaliativa1.data.EventoDatabase;

public class CadastroEventoActivity extends Activity {

    private EditText et_eventoNome;
    private EditText et_eventoData;
    private EditText et_eventoEndereco;
    private EditText et_eventoDescricao;
    private Button btn_cadastroEvento;
    private EventoDAO eventoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        // Inicialização dos componentes de UI
        et_eventoNome = findViewById(R.id.et_eventoNome);
        et_eventoData = findViewById(R.id.et_eventoData);
        et_eventoEndereco = findViewById(R.id.et_eventoEndereco);
        et_eventoDescricao = findViewById(R.id.et_eventoDescricao);
        btn_cadastroEvento = findViewById(R.id.btn_cadastroEvento);

        // Criação do banco de dados do Room
        EventoDatabase db = Room.databaseBuilder(getApplicationContext(), EventoDatabase.class, "eventos-db").build();
        eventoDao = db.eventoDAO();

        btn_cadastroEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarEvento();
            }
        });
    }

    private void cadastrarEvento() {
        String nomeEvento = et_eventoNome.getText().toString().trim();
        String dataEvento = et_eventoData.getText().toString().trim();
        String localizacaoEvento = et_eventoEndereco.getText().toString().trim();
        String descricaoEvento = et_eventoDescricao.getText().toString().trim();

        // Validar se todos os campos foram preenchidos
        if (nomeEvento.isEmpty() || dataEvento.isEmpty() || localizacaoEvento.isEmpty() || descricaoEvento.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Criar um objeto Evento com os dados preenchidos
        Evento evento = new Evento();
        evento.setNomeEvento(nomeEvento);
        evento.setDataEvento(dataEvento);
        evento.setLocalizacaoEvento(localizacaoEvento);
        evento.setDescricaoEvento(descricaoEvento);

        // Inserir o evento no banco de dados usando o EventoDao
        eventoDao.inserirEvento(evento);

        Toast.makeText(this, "Evento cadastrado com sucesso!", Toast.LENGTH_SHORT).show();


        // Limpar os campos após o cadastro
        et_eventoNome.setText("");
        et_eventoData.setText("");
        et_eventoEndereco.setText("");
        et_eventoDescricao.setText("");
    }
}
