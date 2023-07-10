package com.example.atividadeavaliativa1;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.room.Room;

import com.example.atividadeavaliativa1.data.Evento;
import com.example.atividadeavaliativa1.data.EventoDAO;
import com.example.atividadeavaliativa1.data.EventoDatabase;

import java.util.Calendar;

public class CadastroEventoActivity extends Activity {

    private EditText et_eventoNome;
    private EditText et_eventoData;
    private EditText et_eventoHora;
    private EditText et_eventoEndereco;
    private EditText et_eventoDescricao;
    private EditText et_eventoContact;
    private EditText et_eventoContactName;
    private Button btn_cadastroEvento;
    private EventoDAO eventoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        // Inicialização dos componentes de UI
        et_eventoNome = findViewById(R.id.et_eventoNome);
        et_eventoData = findViewById(R.id.et_eventoData);
        et_eventoHora = findViewById(R.id.et_eventoHora);
        et_eventoEndereco = findViewById(R.id.et_eventoEndereco);
        et_eventoDescricao = findViewById(R.id.et_eventoDescricao);
        btn_cadastroEvento = findViewById(R.id.btn_cadastroEvento);
        et_eventoContact = findViewById(R.id.et_eventoContact);
        et_eventoContactName = findViewById(R.id.et_eventoContactName);

        // Criação do banco de dados do Room
        EventoDatabase db = Room.databaseBuilder(getApplicationContext(), EventoDatabase.class, "eventos-db").build();
        eventoDao = db.eventoDAO();

        btn_cadastroEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarEvento();
            }
        });

        et_eventoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { abrirCalendario();  }
        });

        et_eventoHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirSeletorHoras();
            }
        });
    }

    private void abrirCalendario() {
        Calendar calendario = Calendar.getInstance();
        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        // Aqui você pode receber a data selecionada e atualizar o campo de texto ou realizar outras operações.
                        String dataSelecionada = selectedDayOfMonth + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        et_eventoData.setText(dataSelecionada);
                    }
                }, ano, mes, dia);

        datePickerDialog.show();
    }

    private void abrirSeletorHoras() {
        Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minuto = calendario.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        // Aqui você pode receber o horário selecionado e atualizar o campo de texto ou realizar outras operações.
                        String horarioSelecionado = selectedHour + ":" + selectedMinute;
                        et_eventoHora.setText(horarioSelecionado);
                    }
                }, hora, minuto, true);

        timePickerDialog.show();
    }


    private void cadastrarEvento() {
        String nomeEvento = et_eventoNome.getText().toString().trim();
        String dataEvento = et_eventoData.getText().toString().trim();
        String horaEvento = et_eventoHora.getText().toString().trim();
        String localizacaoEvento = et_eventoEndereco.getText().toString().trim();
        String descricaoEvento = et_eventoDescricao.getText().toString().trim();
        String contatoEvento = et_eventoContact.getText().toString().trim();
        String nomeContatoEvento = et_eventoContactName.getText().toString().trim();

        // Validar se todos os campos foram preenchidos
        if (nomeEvento.isEmpty() || dataEvento.isEmpty() || localizacaoEvento.isEmpty() || descricaoEvento.isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.fill_incomplete), Toast.LENGTH_SHORT).show(); //TODO: Como pegar strings do arquivo de strings
            return;
        }

        // Criar um objeto Evento com os dados preenchidos
        Evento evento = new Evento();
        evento.setNomeEvento(nomeEvento);
        evento.setDataEvento(dataEvento);
        evento.setHoraEvento(horaEvento);
        evento.setLocalizacaoEvento(localizacaoEvento);
        evento.setDescricaoEvento(descricaoEvento);
        evento.setContatoEvento(contatoEvento);
        evento.setNomeContatoEvento(nomeContatoEvento);

        // Inserir o evento no banco de dados usando o EventoDao
        eventoDao.inserirEvento(evento);

        Toast.makeText(this, getResources().getString(R.string.register_event_sucess), Toast.LENGTH_SHORT).show();


        // Limpar os campos após o cadastro
        et_eventoNome.setText(getResources().getString(R.string.title_event));
        et_eventoData.setText("__/__/____");
        et_eventoHora.setText("12:00");
        et_eventoEndereco.setText(getResources().getString(R.string.location));
        et_eventoDescricao.setText(getResources().getString(R.string.description));
        et_eventoContact.setText(getResources().getString(R.string.contact));
        et_eventoContactName.setText(getResources().getString(R.string.nameCont));
    }

    public void fecharCadastro(View v){
        this.finish();
    }
}
