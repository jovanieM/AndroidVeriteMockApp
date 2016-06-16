package  com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Cebu SQA on 10/06/2016.
 */
public class ImageCropper extends Activity implements View.OnClickListener{
    CropView cv;
    Button ok, cancel;
    int le, top, ri, bottom;
    Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_crop);

        extras = getIntent().getExtras();

        cv = (CropView) findViewById(R.id.my_view);
        ok = (Button) findViewById(R.id.button);
        cancel = (Button) findViewById(R.id.button2);
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
        cv.x = 20;
        cv.y = 20;
        cv.right = extras.getInt("width");
        cv.bottom = extras.getInt("height");
        cv.rect = new Rect(cv.x,cv.y,cv.right,cv.bottom);
        le = cv.newX;
        ri = cv.newY;

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){

            Intent intent = new Intent();
            intent.putExtra("left", 0);
            intent.putExtra("top", 0);
            intent.putExtra("right",300);
            intent.putExtra("bottom",20);
            setResult(2, intent);
            finish();
        }

        if (v.getId() == R.id.button2){
            Intent intent = new Intent();
            setResult(1,intent);
            finish();
        }
    }
}
