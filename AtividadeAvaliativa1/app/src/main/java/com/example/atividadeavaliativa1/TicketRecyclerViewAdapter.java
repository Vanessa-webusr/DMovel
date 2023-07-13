package com.example.atividadeavaliativa1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividadeavaliativa1.data.ticket.Ticket;

import java.util.List;

public class TicketRecyclerViewAdapter extends RecyclerView.Adapter<TicketRecyclerViewAdapter.TicketViewHolder> {
    //private Context context;
    private List<Ticket> tickets;



    public TicketRecyclerViewAdapter(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewticket_row, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        holder.nomePessoaTicketTextView.setText(ticket.getNomePessoa());
        holder.nomeTicketTextView.setText(ticket.getNomeEventoIngresso());
        holder.dataTicketTextView.setText(ticket.getDataEventoIngresso());
        holder.contatoTicketTextView.setText(ticket.getContatoEventoIngresso());
        // Defina os outros atributos do ticket no ViewHolder
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView nomeTicketTextView;
        TextView dataTicketTextView;
        TextView nomePessoaTicketTextView;
        TextView contatoTicketTextView;
        // Adicione os outros atributos de layout necessários

        public TicketViewHolder(View itemView) {
            super(itemView);
            nomePessoaTicketTextView = itemView.findViewById(R.id.nomePessoaTicketTextView);
            dataTicketTextView = itemView.findViewById(R.id.dataTicketTextView);
            nomeTicketTextView = itemView.findViewById(R.id.nomeTicketTextView);
            contatoTicketTextView = itemView.findViewById(R.id.contatoTicketTextView);

            // Inicialize os outros atributos de layout necessários
        }

    }

}