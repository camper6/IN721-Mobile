package bit.abduhs1.lastfm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sakinah on 14/04/2016.
 */
public class CustomArrayAdapter extends ArrayAdapter<Artist> {

    public CustomArrayAdapter(Context context, int resource, List<Artist> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View customView = inflater.inflate(R.layout.custom_list_item, parent, false);

        TextView textView = (TextView)customView.findViewById(R.id.textView);
        TextView textView2 = (TextView)customView.findViewById(R.id.textView2);

        Artist currentItem = getItem(position);

        textView2.setText(currentItem.getSecondArgument());
        textView.setText(currentItem.toString());

        return customView;
    }
}
