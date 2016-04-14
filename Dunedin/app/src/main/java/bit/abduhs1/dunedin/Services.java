package bit.abduhs1.dunedin;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;

public class Services extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        //Resources resourceMachine = getResources();
        ImageView imageViewLibrary = (ImageView)findViewById(R.id.imageViewLibrary);
        imageViewLibrary.setImageResource(R.drawable.library);
    }
}
