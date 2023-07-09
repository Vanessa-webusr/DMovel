package com.example.atividadeavaliativa1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividadeavaliativa1.data.Evento;
import com.example.atividadeavaliativa1.R;

import java.util.List;

public class EventoRecyclerViewAdapter extends RecyclerView.Adapter<EventoRecyclerViewAdapter.EventoViewHolder> {
    private Context context;
    private List<Evento> eventos;
    private ItemClickListener mClickListener;

    public EventoRecyclerViewAdapter(Context context, List<Evento> eventos) {
        this.context = context;
        this.eventos = eventos;
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new EventoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        Evento evento = eventos.get(position);
        holder.nomeEventoTextView.setText(evento.getNomeEvento());
        holder.dataEventoTextView.setText(evento.getDataEvento());
        // Defina os outros atributos do evento no ViewHolder
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class EventoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nomeEventoTextView;
        TextView dataEventoTextView;
        // Adicione os outros atributos de layout necessários

        public EventoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeEventoTextView = itemView.findViewById(R.id.nomeEventoTextView);
            dataEventoTextView = itemView.findViewById(R.id.dataEventoTextView);
            itemView.setOnClickListener(this);
            // Inicialize os outros atributos de layout necessários
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Evento getItem(int id) {
        return eventos.get(id);
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


