package com.example.atividadeavaliativa1.data;

import android.content.Context;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    private EventoDatabase db;

    public Repository(Context context) {
        db = EventoDatabase.getInstance(context);
    }

    public void addCategory(Evento evento) {
        Evento e = new Evento(evento.getNomeEvento(), evento.getDataEvento(), evento.getHoraEvento(), evento.getLocalizacaoEvento(), evento.getDescricaoEvento(), evento.getContatoEvento(), evento.getNomeContatoEvento());
        db.eventoDAO().inserirEvento(e)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {

                },  throwable -> {

                });
    }

}