package com.example.atividadeavaliativa1.ui.ticket;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;

import android.app.AlertDialog.Builder;

import com.example.atividadeavaliativa1.EventoRecyclerViewAdapter;
import com.example.atividadeavaliativa1.R;
import com.example.atividadeavaliativa1.data.Evento;
import com.example.atividadeavaliativa1.data.EventoDAO;
import com.example.atividadeavaliativa1.data.EventoDatabase;
import com.example.atividadeavaliativa1.data.ticket.Ticket;
import com.example.atividadeavaliativa1.data.ticket.TicketDAO;
import com.example.atividadeavaliativa1.databinding.ActivityCompraIngressoBinding;

import java.util.ArrayList;
import java.util.List;
public class CompraIngresso extends AppCompatActivity {

    private Button btn_voltar;
    //private ListView listView;
    private List<Evento> eventoList;
    private ArrayAdapter<Evento> adapter;
    private ActivityCompraIngressoBinding binding;

    private EventoRecyclerViewAdapter eventoRecyclerViewAdapter;
    private EventoDAO dao;
    private TicketDAO ticketDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_compra_ingresso);

        binding = ActivityCompraIngressoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.eventoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.eventoRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //btn_voltar = (Button) findViewById(R.id.button_voltar_compraIngresso);
        //listView = findViewById(R.id.listView);

        // Inicializar a lista de itens
        eventoList = new ArrayList<>();

        // Popula a lista de eventos do banco de dados
        populateEventoList();

        eventoRecyclerViewAdapter = new EventoRecyclerViewAdapter(this,eventoList);
        binding.eventoRecyclerView.setAdapter(eventoRecyclerViewAdapter);
        eventoRecyclerViewAdapter.setClickListener((view, position)-> {
            Builder builder = new Builder(this);
            builder.setTitle(getResources().getString(R.string.purchase_ticket));
            builder.setPositiveButton(getResources().getString(R.string.yes), (dialog, which) -> {
                Evento selectedEvento = eventoList.get(position);
                Ticket ticket = new Ticket();
                ticket.setContatoEvento("35999999999");
                ticket.setDataEventoIngresso(selectedEvento.getDataEvento());
                ticket.setNomePessoa("AquiTemQuePuxarDoLoginMasNaoAchei");
                ticket.setNomeEventoIngresso(selectedEvento.getNomeEvento());
                comprarEvento(ticket);
                eventoRecyclerViewAdapter.notifyDataSetChanged();
            });
            builder.setNegativeButton(getResources().getString(R.string.no), null);
            builder.create().show();
        });
    }

    // Método para popular a lista de itens do banco de dados
    private void populateEventoList() {
        try {
            eventoList = EventoDatabase.getInstance(this).eventoDAO().loadAll();

        }catch (Exception e){
            e.printStackTrace();
        }
        Evento teste = new Evento("nome","21/08/2023","hora","localização","descrição", "contato", "nomeContato");
        eventoList.add(teste);
    }


    // Método para processar a compra de um item
    private void comprarEvento(Ticket ticket) {
        try {
            ticketDAO.inserirTicket(ticket);
        }catch(Exception e){
            e.printStackTrace();
        }
        Toast.makeText(this, getResources().getString(R.string.purchase_ticket_success), Toast.LENGTH_SHORT).show();
    }

    public void fecharTela(View v){
        this.finish();
    }
}