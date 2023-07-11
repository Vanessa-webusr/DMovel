package com.example.atividadeavaliativa1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atividadeavaliativa1.data.Evento;
import com.example.atividadeavaliativa1.data.EventoDAO;
import com.example.atividadeavaliativa1.data.GeneralDatabase;

import java.util.Calendar;

public class CadastroEventoActivity extends AppCompatActivity {

    EditText et_eventoNome, et_eventoData, et_eventoHora, et_eventoEndereco, et_eventoDescricao, et_eventoContact, et_eventoContactName;
    Button btn_cadastroEvento;


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


        btn_cadastroEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarEvento();
            }
        });

        et_eventoData.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    abrirCalendario();
                }
            }
        });

        et_eventoHora.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    abrirSeletorHoras();
                }
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
                        String horaFormatada = String.format("%02d", selectedHour);
                        String minutoFormatado = String.format("%02d", selectedMinute);

                        // Aqui você pode receber o horário selecionado e atualizar o campo de texto ou realizar outras operações.
                        String horarioSelecionado = horaFormatada + ":" + minutoFormatado;
                        et_eventoHora.setText(horarioSelecionado);
                    }
                }, hora, minuto, true);

        timePickerDialog.show();
    }


    private void cadastrarEvento() {
        String nomeEvento = et_eventoNome.getText().toString();
        String dataEvento = et_eventoData.getText().toString();
        String horaEvento = et_eventoHora.getText().toString();
        String localizacaoEvento = et_eventoEndereco.getText().toString();
        String descricaoEvento = et_eventoDescricao.getText().toString();
        String contatoEvento = et_eventoContact.getText().toString();
        String nomeContatoEvento = et_eventoContactName.getText().toString();

        // Validar se todos os campos foram preenchidos
        if (nomeEvento.isEmpty() || dataEvento.isEmpty() || horaEvento.isEmpty() || localizacaoEvento.isEmpty() || descricaoEvento.isEmpty() || contatoEvento.isEmpty() || nomeContatoEvento.isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.fill_incomplete), Toast.LENGTH_SHORT).show(); //TODO: Como pegar strings do arquivo de strings
        } else {

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
            GeneralDatabase eventoDatabase = GeneralDatabase.getInstance(getApplicationContext());
            EventoDAO eventoDao = eventoDatabase.eventoDAO();

            new Thread(new Runnable() {
                @Override
                public void run() {

                    // Register User
                    eventoDao.registerEvento(evento);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.register_event_sucess), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).start();
        }



        // Limpar os campos após o cadastro
        et_eventoNome.setText(getResources().getString(R.string.title_event));
        et_eventoData.setText("__/__/____");
        et_eventoHora.setText("12:00");
        et_eventoEndereco.setText(getResources().getString(R.string.location));
        et_eventoDescricao.setText(getResources().getString(R.string.description));
        et_eventoContact.setText(getResources().getString(R.string.contact));
        et_eventoContactName.setText(getResources().getString(R.string.nameCont));
    }

    public void fecharTela(View v){
        this.finish();
    }
}
