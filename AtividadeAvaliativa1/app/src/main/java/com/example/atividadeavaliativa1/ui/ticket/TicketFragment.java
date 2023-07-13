package com.example.atividadeavaliativa1.ui.ticket;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.atividadeavaliativa1.EventoRecyclerViewAdapter;
import com.example.atividadeavaliativa1.MainActivity;
import com.example.atividadeavaliativa1.R;
import com.example.atividadeavaliativa1.TicketRecyclerViewAdapter;
import com.example.atividadeavaliativa1.data.Evento;
import com.example.atividadeavaliativa1.data.EventoDAO;
import com.example.atividadeavaliativa1.data.GeneralDatabase;
import com.example.atividadeavaliativa1.data.ticket.Ticket;
import com.example.atividadeavaliativa1.data.ticket.TicketDAO;
import com.example.atividadeavaliativa1.databinding.FragmentTicketBinding;
import com.example.atividadeavaliativa1.ui.ticket.CompraIngresso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TicketFragment extends Fragment {

    private FragmentTicketBinding binding;
    private Flowable<List<Ticket>> ticketList;
    private List<Ticket> validList;
    private TicketRecyclerViewAdapter ticketRecyclerViewAdapter;

    public static TicketFragment newInstance() {
        return new TicketFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTicketBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Popula a lista de tickets do banco de dados
        populateTicketList();



        //Como pode ser feito
        binding.buttonTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TicketFragment.this)
                        .navigate(R.id.action_navigation_ticket_to_compraIngresso);
            }
        });

    }

    // Método para popular a lista de itens do banco de dado
    public void populateTicketList() {
        validList = new ArrayList<>();
        TicketDAO ticketDAO = GeneralDatabase.getInstance(getContext()).ticketDAO();
        ticketList = ticketDAO.getAll();
        Log.d("TicketFragment","chegou aaqqqqqqqqqq");
        ticketList
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                //.flatMap(Flowable::fromIterable) // Converte List<Ticket> em Ticket individual
                //.toList() // Converte Ticket individual em List<Ticket>
                .subscribe(tickets -> {
                    Log.d("TicketFragment","Tickets: " + tickets.size());
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
                    validList.clear(); // Limpar a lista antes de preenchê-la novamente
                    for(Ticket ticket : tickets){
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

                        if(dataIngresso.after(dataAtual)){
                            validList.add(ticket);
                        }
                    }
                    if(validList.isEmpty()){Log.d("TicketFragment","validList está vazia!!!!!!");}
                    Log.d("TicketFragment","ValidList: " + validList.size());
                    configAdapter(validList);
                }, error -> {
                    // Tratamento de erros
                });
    }

    public void configAdapter (List<Ticket> validList){
        if(validList == null){
            binding.ticketTextViewEmpty.setVisibility(View.VISIBLE);
        }else {
            if (validList.isEmpty()) {
                binding.ticketTextViewEmpty.setVisibility(View.VISIBLE);
            } else {
                binding.ticketTextViewEmpty.setVisibility(View.GONE);
                Log.d("TicketFragment","oq eh isso gente");

                binding.ticketRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.ticketRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

                ticketRecyclerViewAdapter = new TicketRecyclerViewAdapter(validList);
                binding.ticketRecyclerView.setAdapter(ticketRecyclerViewAdapter);
            }
        }
    }
}