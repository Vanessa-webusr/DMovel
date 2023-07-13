package com.example.atividadeavaliativa1.ui.ticket;

import static com.example.atividadeavaliativa1.user.Activity_login.USER_FILE_NAME;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.atividadeavaliativa1.EventoRecyclerViewAdapter;
import com.example.atividadeavaliativa1.R;
import com.example.atividadeavaliativa1.data.Evento;
import com.example.atividadeavaliativa1.data.EventoDAO;
import com.example.atividadeavaliativa1.data.ticket.Ticket;
import com.example.atividadeavaliativa1.data.ticket.TicketDAO;
import com.example.atividadeavaliativa1.databinding.ActivityCompraIngressoBinding;
import com.example.atividadeavaliativa1.data.GeneralDatabase;
import com.example.atividadeavaliativa1.data.user.UserDao;
import com.example.atividadeavaliativa1.data.user.UserEntity;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CompraIngresso extends AppCompatActivity {

    private Flowable<List<Evento>> eventoList;
    private ActivityCompraIngressoBinding binding;
    private List<Ticket> listaIngressosTeste;

    private EventoRecyclerViewAdapter eventoRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCompraIngressoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.eventoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.eventoRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // Popula a lista de eventos do banco de dados
        populateEventoList();

    }

    // Método para popular a lista de itens do banco de dados
    private void populateEventoList() {
        EventoDAO eventoDAO = GeneralDatabase.getInstance(this).eventoDAO();
        eventoList = eventoDAO.getAll();
        if(eventoList == null){
            binding.eventoTextViewEmpty.setVisibility(View.VISIBLE);
        }else{
            binding.eventoTextViewEmpty.setVisibility(View.GONE);
            eventoList
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(eventos -> {
                        Log.d("CompraIngresso", "Eventos: " + eventos.size());
                        eventoRecyclerViewAdapter = new EventoRecyclerViewAdapter(eventos);
                        binding.eventoRecyclerView.setAdapter(eventoRecyclerViewAdapter);

                        eventoRecyclerViewAdapter.setClickListener((view, position)-> {
                            Builder builder = new Builder(this);
                            builder.setTitle(getResources().getString(R.string.purchase_ticket));
                            builder.setPositiveButton(getResources().getString(R.string.yes), (dialog, which) -> {

                                Evento selectedEvento = eventos.get(position);

                                Ticket ticket = new Ticket();
                                SharedPreferences preferences = getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
                                String display = preferences.getString("name", getResources().getString(R.string.user_name));
                                String userIdText = preferences.getString("email", "");
                                Log.d("CompraIngresso","RECUPERANDO: " + userIdText);
                                String passwordText = preferences.getString("password", "");
                               /* GeneralDatabase generalDatabase = GeneralDatabase.getInstance(getApplicationContext());
                                UserDao userDao = generalDatabase.userDao();
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        UserEntity userEntity = userDao.login(userIdText, passwordText);
                                        ticket.setEmailUsuario(userEntity.getUserId());
                                    }
                                }).start();*/
                                ticket.setEmailUsuario(userIdText);
                                ticket.setContatoEventoIngresso(selectedEvento.getContatoEvento());

                                ticket.setDataEventoIngresso(selectedEvento.getDataEvento());

                                ticket.setNomePessoa(display);

                                ticket.setNomeEventoIngresso(selectedEvento.getNomeEvento());

                                comprarEvento(ticket);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.purchase_ticket_success), Toast.LENGTH_SHORT).show();
                                        eventoRecyclerViewAdapter.notifyDataSetChanged();
                                    }
                                });

                            });
                            builder.setNegativeButton(getResources().getString(R.string.no), null);
                            builder.create().show();
                        });

                    }, error -> {
                        Log.e("CompraIngresso", "Erro ao obter eventos: " + error.getMessage());
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.populate_list_event_fail), Toast.LENGTH_SHORT).show();
                    });
        }
    }


    // Método para processar a compra de um item
    private void comprarEvento(Ticket ticket) {
        GeneralDatabase generalDatabase = GeneralDatabase.getInstance(getApplicationContext());
        TicketDAO ticketDAO = generalDatabase.ticketDAO();
        new Thread(new Runnable() {
            @Override
            public void run(){
                ticketDAO.inserirTicket(ticket);

            }
        }).start();
    }

    public void fecharTela(View v){
        this.finish();
    }

    public List<Ticket> pegaLista(){
        return listaIngressosTeste;
    }
}