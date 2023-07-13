package com.example.atividadeavaliativa1.ui.ticket;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
import com.example.atividadeavaliativa1.user.UserDao;


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


        // Popula a lista de eventos do banco de dados
        populateEventoList();


        eventoRecyclerViewAdapter.setClickListener((view, position)-> {
            Builder builder = new Builder(this);
            builder.setTitle(getResources().getString(R.string.purchase_ticket));
            builder.setPositiveButton(getResources().getString(R.string.yes), (dialog, which) -> {
                eventoList
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .flatMap(Flowable::fromIterable) // Converte List<Evento> em Evento individual
                        .toList() // Converte Evento individual em List<Evento>
                        .subscribe(eventos -> {
                            Evento selectedEvento = eventos.get(position);
                            Ticket ticket = new Ticket();
                            ticket.setContatoEventoIngresso(selectedEvento.getContatoEvento());
                            ticket.setDataEventoIngresso(selectedEvento.getDataEvento());
                            ticket.setNomePessoa("AquiTemQuePuxarDoLoginMasNaoAchei");
                            ticket.setNomeEventoIngresso(selectedEvento.getNomeEvento());

                            comprarEvento(ticket);
                            eventoRecyclerViewAdapter.notifyDataSetChanged();
                        }, error -> {
                            // Tratamento de erro
                        });
            });
            builder.setNegativeButton(getResources().getString(R.string.no), null);
            builder.create().show();
        });
    }

    // Método para popular a lista de itens do banco de dados
    private void populateEventoList() {
        EventoDAO eventoDAO = GeneralDatabase.getInstance(this).eventoDAO();
        eventoList = eventoDAO.getAll();
        if(eventoList == null){
            binding.eventoTextViewEmpty.setVisibility(View.VISIBLE);
        }else{
            eventoList
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(eventos -> {
                        eventoRecyclerViewAdapter = new EventoRecyclerViewAdapter(eventos);
                        binding.eventoRecyclerView.setAdapter(eventoRecyclerViewAdapter);
                        binding.eventoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                        binding.eventoRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

                    }, error -> {
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

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.purchase_ticket_success), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
        //listaIngressosTeste.add(ticket);
    }

    public void fecharTela(View v){
        this.finish();
    }

    public List<Ticket> pegaLista(){
        return listaIngressosTeste;
    }
}