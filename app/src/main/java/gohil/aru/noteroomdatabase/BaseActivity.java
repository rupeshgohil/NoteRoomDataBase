package gohil.aru.noteroomdatabase;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void showToast(Context mContext,String str){
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }
    public static String currentMMMDate() throws ParseException {
        return new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());

    }
}
