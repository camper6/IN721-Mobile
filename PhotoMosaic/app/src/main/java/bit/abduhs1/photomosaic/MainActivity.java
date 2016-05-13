package bit.abduhs1.photomosaic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private Button photoIntent;
    private String mPhotoFileName = "";
    private File mPhotoFile;
    private Uri mPhotoFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);

        photoIntent = (Button)findViewById(R.id.button);
        photoIntent.setOnClickListener(new photoIntentButtonHandler());
    }

    public File createTimeStampedFile() {
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File imageStorageDirectory = new File(imageRootPath, "PhotoMosaic");
        if (!imageStorageDirectory.exists()) {
            imageStorageDirectory.mkdirs();
        }

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        mPhotoFileName = "IMG_" + timeStamp + ".jpg";

        File photoFile = new File(imageStorageDirectory.getPath() + File.separator + mPhotoFileName);
        return photoFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (requestCode == RESULT_OK) {
                String readFilePath = mPhotoFile.getPath();
                Bitmap userPhotoBitmap = BitmapFactory.decodeFile(readFilePath);

                imageView.setImageBitmap(userPhotoBitmap);
                imageView2.setImageBitmap(userPhotoBitmap);
                imageView3.setImageBitmap(userPhotoBitmap);
                imageView4.setImageBitmap(userPhotoBitmap);
            } else {
                Toast.makeText(this, "No photo saved. Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
    }

    public class photoIntentButtonHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mPhotoFile = createTimeStampedFile();

            mPhotoFileUri = Uri.fromFile(mPhotoFile);

            Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoFileUri);

            startActivityForResult(imageCaptureIntent, 1);
        }
    }
}
