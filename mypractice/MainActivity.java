package com.ibrahimkiceci.mypractice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    TextView textView;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        textView = findViewById(R.id.textView);

        sharedPreferences = this.getSharedPreferences("com.ibrahimkiceci.mypractice", Context.MODE_PRIVATE);
        String storedTextName = sharedPreferences.getString("storedName","No Name" );
        String storedTextSurname =  sharedPreferences.getString("storedSurName", "No Surname" );
        int storedAgeNumber = sharedPreferences.getInt("storedAge",0);
        String storedMailText = sharedPreferences.getString("storedMail", "No mail");
        textView.setText("Your Information: " + "\n" + storedTextName + "\n" + storedTextSurname + "\n" + storedAgeNumber + "\n" + storedMailText);






    }

    // Tiklaninca kayit etmesini isteyelim

    //Kullanicinin girdigi veriyi alert dialog ve tost mesajlari ile ekrana yazdiralim,

    public void save(View view){

        AlertDialog.Builder my_alert = new AlertDialog.Builder(this);
        my_alert.setTitle("Save");
        my_alert.setMessage("Are you sure? ");
        my_alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (editText1.getText().toString().matches("") || (editText2.getText().toString().matches(""))||(editText3.getText().toString().matches("")) || (editText4.getText().toString().matches(""))){
                        // Kullanicinin tum degerleri girmesini burada zorunlu kildik.

                    Toast.makeText(MainActivity.this, "Please Input ALL Values", Toast.LENGTH_SHORT).show();

                }else{

                    String name = editText1.getText().toString();
                    String surname = editText2.getText().toString();
                    int age = Integer.parseInt(editText3.getText().toString());
                    String mail = editText4.getText().toString();
                    textView.setText("Your Information: " + "\n" + name + "\n" + surname + "\n" + age + "\n" + mail);
                    // Bu kucuk veriler shaered preferences kullanarak kayit edelim;

                    sharedPreferences.edit().putString("storedName", name).apply();
                    sharedPreferences.edit().putString("storedSurname", surname).apply();
                    sharedPreferences.edit().putInt("storedAge", age).apply();
                    sharedPreferences.edit().putString("storedMail", mail).apply();

                }
            }
        });

        my_alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(MainActivity.this, "Your Information is NOT saved", Toast.LENGTH_SHORT).show();

            }
        });



        my_alert.show();




    }

    public void delete(View view){

        AlertDialog.Builder my_alert2 = new AlertDialog.Builder(this);
        my_alert2.setTitle("Delete");
        my_alert2.setMessage("Are you sure to delete your Information? ");
        my_alert2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String storedNameText = sharedPreferences.getString("storedName", "No Name");
                String storedSurnameText = sharedPreferences.getString("storedSurname", "No Surname");
                int storedAgeNumber = sharedPreferences.getInt("storedAge", 0);
                String storedMailText = sharedPreferences.getString("storedMail", "No mail");

                if ((!storedNameText.matches("")) || (!storedSurnameText.matches("")) || (storedAgeNumber !=0) || (!storedMailText.matches(""))){

                    sharedPreferences.edit().remove("storedName").apply();
                    sharedPreferences.edit().remove("storedSurname").apply();
                    sharedPreferences.edit().remove("storedAge").apply();
                    sharedPreferences.edit().remove("storedMail").apply();
                    textView.setText("Your Information is Empty");

                }
            }
        });

        my_alert2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(MainActivity.this, "It is Cancellled", Toast.LENGTH_LONG).show();

            }
        });


        my_alert2.show();


    }



}