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

import com.example.atividadeavaliativa1.R;
import com.example.atividadeavaliativa1.databinding.FragmentHomeBinding;

import java.util.Calendar;

public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView monthTextView;
    private Button prevMonthButton;
    private Button nextMonthButton;
    private String[] months = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho",
            "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
    private int currentMonthIndex = 0;
    private FragmentHomeBinding binding;

    //SearchView searchView;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //searchView = view.findViewById(R.id.searchView);

        super.onViewCreated(view, savedInstanceState);

        monthTextView = view.findViewById(R.id.monthTextView);
        prevMonthButton = view.findViewById(R.id.prevMonthButton);
        nextMonthButton = view.findViewById(R.id.nextMonthButton);

        prevMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMonthIndex--;
                if (currentMonthIndex < 0) {
                    currentMonthIndex = months.length - 1;
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

        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
                GeneralDatabase eventoDatabase = GeneralDatabase.getInstance();
                EventoDAO eventoDAO = eventoDatabase.eventoDAO();


                if (query.isEmpty()) {
                    return eventoDAO.loadAll();
                } else {
                    return eventoDAO.buscarEventosPorNome(query);
                }

            }

            private void exibirResultados(List<Evento> eventos) {
                //listaEventosAdapter.atualizarLista(eventos);
            }
        });*/

        // Obter o mês atual
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        currentMonthIndex = currentMonth;

        // Atualizar o TextView com o mês atual
        updateMonthTextView();

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

    }


    private void updateMonthTextView() {
        String currentMonth = months[currentMonthIndex];
        monthTextView.setText(currentMonth);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}