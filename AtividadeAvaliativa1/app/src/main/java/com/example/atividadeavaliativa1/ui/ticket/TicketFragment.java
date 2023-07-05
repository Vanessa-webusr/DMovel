package com.example.atividadeavaliativa1.ui.ticket;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.atividadeavaliativa1.MainActivity;
import com.example.atividadeavaliativa1.R;
import com.example.atividadeavaliativa1.data.Evento;
import com.example.atividadeavaliativa1.data.EventoDAO;
import com.example.atividadeavaliativa1.data.ticket.Ticket;
import com.example.atividadeavaliativa1.data.ticket.TicketDAO;
import com.example.atividadeavaliativa1.databinding.FragmentTicketBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TicketFragment extends Fragment {

    private FragmentTicketBinding binding;

    private Button btn_comprarIngresso;
    private ListView listView;
    private List<Ticket> ticketList;
    private ArrayAdapter<Ticket> adapter;

    private TicketViewModel mViewModel;
    private TicketDAO dao;

    public static TicketFragment newInstance() {
        return new TicketFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        /*View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        return view;*/
        binding = FragmentTicketBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //btn_comprarIngresso = (Button) view.findViewById(R.id.button_ticket);
        //listView = listView.findViewById(R.id.listViewTicket);

        // Inicializar a lista de itens
        /*ticketList = new ArrayList<>();

        // Popula a lista de eventos do banco de dados
        populateTicketList();

        // Configura o adaptador do ListView
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, ticketList);
        listView.setAdapter(adapter);*/


        /*btn_comprarIngresso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CompraIngresso.class);
                startActivity(intent);
            }
        });*/

        //Como pode ser feito
        binding.buttonTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TicketFragment.this)
                        .navigate(R.id.action_navigation_ticket_to_compraIngresso);
            }
        });

    }

    // Método para popular a lista de itens do banco de dados
/*    private void populateTicketList() {
        ticketList = loadAllValids();
    }

    //Método para comparar duas datas
    private List<Ticket> loadAllValids() {
        List<Ticket> lista = new ArrayList<>();
        List<Ticket> validList = new ArrayList<>();

        // Obtém a data atual
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(currentDate);
        Date finalDate = null;
        try {
            finalDate = dateFormat.parse(formattedDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            lista = dao.loadAll();
        }catch(Exception e){
            e.printStackTrace();
        }

        for(Ticket ticket : lista){
            String data = ticket.getDataEventoIngresso();
            Date d = null;
            try {
                d = dateFormat.parse(data);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Calendar dataAtual = Calendar.getInstance();
            Calendar dataIngresso = Calendar.getInstance();
            dataAtual.setTime(finalDate);
            dataIngresso.setTime(d);

            if(dataAtual.after(dataIngresso)){
                validList.add(ticket);
            }
        }

        return validList;

    }*/
}