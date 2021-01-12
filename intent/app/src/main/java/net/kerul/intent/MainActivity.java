package net.kerul.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind textbox
        txturl=(EditText)findViewById(R.id.txtURL);
    }
    EditText txturl;
    public void  openURL(View view){
        Toast t= Toast.makeText(this,"Entering openURL",Toast.LENGTH_SHORT);
        t.show();
        Log.e("openURL","entered");
        String url=txturl.getText().toString();
        //invoke browser
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
        startActivity(intent);//show the webpage

    }

    public void openMap(View view){
        Log.e("openURL","entered");
        String loc=txturl.getText().toString();
        //invoke the map
        Uri webpage = Uri.parse("geo:0,0?q="+loc);
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
        startActivity(intent);//show the webpage

    }
}
