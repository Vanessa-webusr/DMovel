package com.example.atividadeavaliativa1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.atividadeavaliativa1.R;
import com.example.atividadeavaliativa1.databinding.FragmentHomeBinding;
import java.util.Calendar;
import java.util.List;
import com.example.atividadeavaliativa1.data.Evento;
import com.example.atividadeavaliativa1.data.EventoDAO;
import com.example.atividadeavaliativa1.data.GeneralDatabase;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.widget.SearchView;

public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView monthTextView;
    private TextView yearTextView;
    private Button prevMonthButton;
    private Button nextMonthButton;
    RecyclerView recyclerViewEventos;
    private String[] months = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho",
            "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
    private int currentMonthIndex = 0;
    private FragmentHomeBinding binding;

    private int currentMonth;
    private int currentYear;
    private SearchView searchView;
    Calendar calendar = Calendar.getInstance();

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        super.onViewCreated(view, savedInstanceState);

        monthTextView = view.findViewById(R.id.monthTextView);
        yearTextView = view.findViewById(R.id.yearTextView);
        prevMonthButton = view.findViewById(R.id.prevMonthButton);
        nextMonthButton = view.findViewById(R.id.nextMonthButton);

        prevMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMonthIndex--;
                if (currentMonthIndex < 0) {
                    currentMonthIndex = months.length - 1;
                    calendar.add(Calendar.YEAR, -1);
                }
                updateMonthTextView();

            }
        });

        nextMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMonthIndex++;
                if (currentMonthIndex >= months.length) {
                    currentMonthIndex = 0;
                    calendar.add(Calendar.YEAR, 1);
                }
                updateMonthTextView();

            }
        });

        binding.buttonEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_navigation_home_to_cadastroEventoActivity);
            }
        });

        // Obter o mês atual

        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);
        currentMonthIndex = currentMonth;

        // Atualizar o TextView com o mês atual
        updateMonthTextView();


/*        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                List<Evento> eventosEncontrados = buscarEventos(query);
                exibirResultados(eventosEncontrados);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Evento> eventosEncontrados = buscarEventos(newText);
                exibirResultados(eventosEncontrados);
                return false;
            }

            private List<Evento> buscarEventos(String query) {
                GeneralDatabase eventoDatabase = GeneralDatabase.getInstance(getActivity().getApplicationContext());
                EventoDAO eventoDAO = eventoDatabase.eventoDAO();

                if (query.isEmpty()) {
                    return eventoDAO.loadAll();
                } else {
                    return eventoDAO.buscarEventosPorNome(query);
                }
            }


            private void exibirResultados(List<Evento> eventos) {
                //listaEventosAdapter.atualizarLista(eventos);

                //eventoAdapter = new EventoAdapter(eventos);
                monthTextView.setText(currentMonth);
                yearTextView.setText(currentYear);
                EventoAdapter eventoAdapter = new EventoAdapter(eventos, monthTextView.getText().toString(), yearTextView.getText().toString());
                recyclerViewEventos.setAdapter(eventoAdapter);

            }
        });*/


        return view;
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewEventos = view.findViewById(R.id.recyclerViewNomes);
        EventoDAO eventoDAO = GeneralDatabase.getInstance(requireContext()).eventoDAO();

        updateRecycleView(recyclerViewEventos, eventoDAO);
    }

    private void updateRecycleView(RecyclerView recyclerViewEventos, EventoDAO eventoDAO){
        Flowable<List<Evento>> flowable = eventoDAO.getAll();
        flowable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        eventos -> {

                            EventoAdapter eventoAdapter = new EventoAdapter(eventos, monthTextView.getText().toString(), yearTextView.getText().toString());
                            recyclerViewEventos.setAdapter(eventoAdapter);
                            recyclerViewEventos.setLayoutManager(new LinearLayoutManager(requireContext()));
                        },
                        error -> {
                            // Lida com o erro aqui
                        }
                );
    }

    private void updateMonthTextView() {
        String currentMonth = months[currentMonthIndex];
        String currentYear = String.valueOf(calendar.get(Calendar.YEAR));
        monthTextView.setText(currentMonth);
        yearTextView.setText(currentYear);

        View view = binding.getRoot();
        RecyclerView recyclerViewEventos = view.findViewById(R.id.recyclerViewNomes);
        EventoDAO eventoDAO = GeneralDatabase.getInstance(requireContext()).eventoDAO();
        updateRecycleView(recyclerViewEventos, eventoDAO);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}