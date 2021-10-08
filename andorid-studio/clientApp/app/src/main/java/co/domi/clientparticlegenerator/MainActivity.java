package co.domi.clientparticlegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import co.domi.clientparticlegenerator.model.ParticleGroup;

public class MainActivity extends AppCompatActivity {

    private String message;
    private int typeP;
    private String name;
    private int number;
    private int originalPosX;
    private int originalPosY;
    private int mx;
    private int my;

    private EditText nameT;
    private EditText amountT;
    private EditText posXT;
    private EditText posYT;
    private Button redB;
    private Button greenB;
    private Button blueB;
    private Button erraseB;
    private Button createB;

    private Communication coms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coms = new Communication(this);
        coms.start();

        nameT = findViewById(R.id.nameId);
        amountT = findViewById(R.id.AmountId);
        posXT = findViewById(R.id.posXId);
        posYT = findViewById(R.id.posYId);
        redB = findViewById(R.id.RId);
        greenB = findViewById(R.id.gId);
        blueB = findViewById(R.id.bId);
        erraseB = findViewById(R.id.erraseId);
        createB = findViewById(R.id.createId);

        redB.setOnClickListener(
                (view) -> {
                    redB.setBackground(getResources().getDrawable(R.drawable.buttonstroke));
                    typeP = 1;
                });

        greenB.setOnClickListener(
                (view) -> {
                    greenB.setBackground(getResources().getDrawable(R.drawable.buttonstroke));
                    typeP = 2;
                });

        blueB.setOnClickListener(
                (view) -> {
                    blueB.setBackground(getResources().getDrawable(R.drawable.buttonstroke));
                    typeP = 3;
                });

        createB.setOnClickListener(
                (view) -> {
                    Gson gson = new Gson();

                    name = nameT.getText().toString().trim();
                    if(Integer.parseInt(amountT.getText().toString()) < 0){
                        number = 1;
                    }else{
                        number = Integer.parseInt(amountT.getText().toString());
                    }

                    if(Integer.parseInt(posXT.getText().toString()) <= 0){
                        originalPosX = 1;
                    }else if(Integer.parseInt(posXT.getText().toString()) > 799){
                        originalPosX = 799;
                    }else{
                        originalPosX = Integer.parseInt(posXT.getText().toString());
                    }

                    if(Integer.parseInt(posYT.getText().toString()) <= 0){
                        originalPosY = 1;
                    }else if(Integer.parseInt(posYT.getText().toString()) > 599){
                        originalPosX = 599;
                    }else{
                        originalPosY = Integer.parseInt(posYT.getText().toString());
                    }

                    mx = 800;
                    my = 600;

                    ParticleGroup obj = new ParticleGroup(name, number, originalPosX, originalPosY,  typeP,  mx,  my);
                    String json = gson.toJson(obj);
                    coms.sendMessage(json);
                });

    }

}