package in.bitcode.resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtMessage;
    private ImageView mImg;
    private EditText mEdtData;
    private Button mBtnSetData;
    int val = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mt("onCreate");

        mEdtData = findViewById(R.id.edtData);
        mBtnSetData = findViewById(R.id.btnSet);

        mBtnSetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTxtMessage.setText(mEdtData.getText().toString());
            }
        });

        mTxtMessage = findViewById(R.id.txtMessage);
        mImg = findViewById(R.id.img);

        mTxtMessage.setText(R.string.my_message);

        Resources res = getResources();

        float txtSize = res.getDimension(R.dimen.gen_text_size);
        mTxtMessage.setTextSize(txtSize);
        Log.e("tag", "txtsize " +  txtSize);

        String welcomeMessage = res.getString(R.string.welcome_message);
        Log.e("tag", "wel msg " +  welcomeMessage);

        int bgColor = res.getColor(R.color.txtbgcolor);
        Log.e("tag", "txt bg color " +  bgColor);

        Log.e("tag", "bool " + res.getBoolean(R.bool.is_password));
        Log.e("tag", "integer " + res.getInteger(R.integer.company_code));

        String [] cities = res.getStringArray(R.array.cities);
        for(String city : cities) {
            Log.e("tag", "city " + city);
        }

        int [] cityCodes = res.getIntArray(R.array.city_codes);
        for(int cityCode : cityCodes) {
            Log.e("tag", "city code" + cityCode);
        }

        Drawable drawable = res.getDrawable(R.drawable.flag);
        mImg.setImageDrawable(drawable);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.flag);

        MyData myData = (MyData) getLastCustomNonConfigurationInstance();
        if(myData != null) {
            mt("Got backed up data");
            mTxtMessage.setText(myData.getData());
        }

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mt("onConfigurationChanged");
    }

    @Override
    protected void onDestroy() {
        mt("onDestroy()");
        super.onDestroy();
    }
    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();;
    }

    @Nullable
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        //code to backup the data
        //return mTxtMessage.getText().toString();
        mt("onRetain");
        //write the data to external storage SD cards, secondary storage
        return new MyData(mTxtMessage.getText().toString());
        //return "path to the actual data";
    }
}

class MyData {
    private String data;

    public MyData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}




















