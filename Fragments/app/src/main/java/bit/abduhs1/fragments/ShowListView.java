package bit.abduhs1.fragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowListView extends Fragment {


    public ShowListView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_show_list_view, container, false);
        ListView lvCities = (ListView) fragmentView.findViewById(R.id.lvCities);

        Resources resourceMachine = getResources();
        String[] cityNamesArray = resourceMachine.getStringArray(R.array.city_name_array);

        ArrayAdapter<String> cityNameAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, cityNamesArray);

        lvCities.setAdapter(cityNameAdapter);

        // Inflate the layout for this fragment
        return fragmentView;
    }

}
