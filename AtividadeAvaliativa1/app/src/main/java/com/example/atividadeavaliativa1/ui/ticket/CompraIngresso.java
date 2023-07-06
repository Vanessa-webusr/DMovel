package com.example.atividadeavaliativa1.ui.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.atividadeavaliativa1.R;
import com.example.atividadeavaliativa1.data.Evento;
import com.example.atividadeavaliativa1.data.EventoDAO;
import com.example.atividadeavaliativa1.data.ticket.Ticket;
import com.example.atividadeavaliativa1.data.ticket.TicketDAO;
import com.example.atividadeavaliativa1.databinding.ActivityCompraIngressoBinding;

import java.util.ArrayList;
import java.util.List;

public class CompraIngresso extends AppCompatActivity {

    private Button btn_voltar;
    private ListView listView;
    private List<Evento> eventoList;
    private ArrayAdapter<Evento> adapter;
    private ActivityCompraIngressoBinding binding;

    private EventoDAO dao;
    private TicketDAO ticketDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_compra_ingresso);

        binding = ActivityCompraIngressoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // btn_voltar = (Button) findViewById(R.id.button_voltar_compraIngresso);
        listView = findViewById(R.id.listView);

        // Inicializar a lista de itens
        eventoList = new ArrayList<>();

        // Popula a lista de eventos do banco de dados
        populateEventoList();

        // Configura o adaptador do ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventoList);
        listView.setAdapter(adapter);

        // Configura o clique nos itens do ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento selectedEvento = eventoList.get(position);
                Ticket ticket = new Ticket();
                ticket.setContatoEvento("35999999999");
                ticket.setDataEventoIngresso(selectedEvento.getDataEvento());
                ticket.setNomePessoa("AquiTemQuePuxarDoLoginMasNaoAchei");
                ticket.setNomeEventoIngresso(selectedEvento.getNomeEvento());
                comprarEvento(ticket);
            }
        });
    }

    // Método para popular a lista de itens do banco de dados
    private void populateEventoList() {
        try {
            eventoList = dao.loadAll();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    // Método para processar a compra de um item
    private void comprarEvento(Ticket ticket) {
        ticketDAO.inserirTicket(ticket);
        Toast.makeText(this, getResources().getString(R.string.purchase_ticket), Toast.LENGTH_SHORT).show();
    }

    public void fecharTela(View v){
        this.finish();
    }
}