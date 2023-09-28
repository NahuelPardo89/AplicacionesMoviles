package com.example.ampamain.ui.home;



import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ampamain.R;
import com.example.ampamain.ui.home.tabbed.ReservasFragment;
import com.example.ampamain.ui.home.tabbed.SectionsPagerAdapter;
import com.example.ampamain.ui.home.tabbed.torneosFragment;
import com.example.ampamain.ui.home.tabbed.RutinasFragment;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Inicializar el SectionsPagerAdapter
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        //  títulos de las pestañas
        sectionsPagerAdapter.addFragment(new torneosFragment(), "Torneos");
        sectionsPagerAdapter.addFragment(new ReservasFragment(), "Reservas");
        sectionsPagerAdapter.addFragment(new RutinasFragment(), "Rutinas");

        // Configurar el ViewPager
        ViewPager viewPager = root.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        // Conectar el TabLayout con el ViewPager
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        return root;
    }
}
