package com.example.atividadeavaliativa1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividadeavaliativa1.data.Evento;
import com.example.atividadeavaliativa1.data.ticket.Ticket;

import java.util.List;

public class TicketRecyclerViewAdapter extends RecyclerView.Adapter<TicketRecyclerViewAdapter.TicketViewHolder> {
    //private Context context;
    private List<Ticket> tickets;
    private ItemClickListener mClickListener;
    private LayoutInflater mInflater;

    public TicketRecyclerViewAdapter(Context context, List<Ticket> data) {
        this.mInflater = LayoutInflater.from(context);
        this.tickets = data;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerviewticket_row, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        holder.nomePessoaTicketTextView.setText(ticket.getNomePessoa());
        holder.dataTicketTextView.setText(ticket.getDataEventoIngresso());
        holder.nomeTicketTextView.setText(ticket.getNomeEventoIngresso());
        holder.contatoTicketTextView.setText(ticket.getContatoEvento());
        // Defina os outros atributos do ticket no ViewHolder
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            itemView.setOnClickListener(this);
            // Inicialize os outros atributos de layout necessários
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Ticket getItem(int id) {
        return tickets.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}