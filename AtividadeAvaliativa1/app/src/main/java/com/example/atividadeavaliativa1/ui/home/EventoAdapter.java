package com.example.atividadeavaliativa1.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividadeavaliativa1.data.Evento;
import com.example.atividadeavaliativa1.R;

import org.w3c.dom.Text;

import java.util.List;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.ViewHolder> {

    private List<Evento> eventos;
    private List<Evento> eventosFiltrados; // Lista filtrada de eventos
    private String filtroMes; // Filtro de mês
    private String filtroAno;

    public EventoAdapter(List<Evento> eventos, String filtroMes, String filtroAno) {
        this.eventos = eventos;
        this.filtroMes = filtroMes;
        this.filtroAno = filtroAno;
        filterEventos();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewevento_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Evento evento = eventosFiltrados.get(position);
        holder.bind(evento);
    }

    @Override
    public int getItemCount() {
        return eventosFiltrados.size();
    }

    private void filterEventos() {
        eventosFiltrados = new ArrayList<>(); // Limpa a lista de eventos filtrados

        for (Evento evento : eventos) {
            String eventoMes = obterMesDoEvento(evento.getDataEvento()); // Obter o mês do evento
            String eventoAno = obterAnoDoEvento(evento.getDataEvento()); // Obter o ano do evento

            if (filtroMes.equals(eventoMes) && filtroAno.equals(eventoAno)) {
                eventosFiltrados.add(evento);
            }
        }

        notifyDataSetChanged(); // Notifica a RecyclerView para atualizar a exibição dos itens filtrados
    }

    // Método fictício para obter o mês do evento
    private String obterMesDoEvento(String dataEvento) {
        String mes = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date data = format.parse(dataEvento);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            int numeroMes = calendar.get(Calendar.MONTH);
            mes = obterNomeMes(numeroMes);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mes;
    }

    // Método para obter o nome do mês com base no número do mês
    private String obterNomeMes(int numeroMes) {
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho",
                "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        if (numeroMes >= 0 && numeroMes < meses.length) {
            return meses[numeroMes];
        } else {
            return "";
        }
    }

    // Método para obter o ano do evento a partir da data
    private String obterAnoDoEvento(String dataEvento) {
        String ano = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date data = format.parse(dataEvento);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            int numeroAno = calendar.get(Calendar.YEAR);
            ano = String.valueOf(numeroAno);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ano;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNomeEvento;
        private TextView textViewDataEvento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomeEvento = itemView.findViewById(R.id.nomeEventoTextView);
            textViewDataEvento = itemView.findViewById(R.id.dataEventoTextView);
        }

        public void bind(Evento evento) {
            textViewNomeEvento.setText(evento.getNomeEvento());
            textViewDataEvento.setText(evento.getDataEvento());
        }
    }
}
