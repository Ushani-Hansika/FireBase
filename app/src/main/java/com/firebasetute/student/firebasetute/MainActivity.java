package com.firebasetute.student.firebasetute;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText txtid,txtname,txtaddress,txtcontact;
    Button btsave,btshow,btdelete,btupdate;
    DatabaseReference dbref;
    Student std;

    public void clearControls(){
        txtid.setText("");
        txtname.setText("");
        txtaddress.setText("");
        txtcontact.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       txtid = findViewById(R.id.txtID);
       txtname = findViewById(R.id.txtName);
       txtaddress = findViewById(R.id.txtAddress);
       txtcontact = findViewById(R.id.txtContactNo);

       btsave = findViewById(R.id.btsave);
       btshow = findViewById(R.id.btshow);
       btdelete = findViewById(R.id.btdelete);
       btupdate = findViewById(R.id.btupdate);


       btsave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dbref = FirebaseDatabase.getInstance().getReference().child("Student");
               try{
                   if(TextUtils.isEmpty(txtid.getText().toString()))
                       Toast.makeText(getApplicationContext(), "Please enter an ID",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtaddress.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter an address",Toast.LENGTH_SHORT).show();
                    else{
                        std.setID(txtid.getText().toString().trim());
                        std.setName(txtname.getText().toString().trim());
                        std.setAddress(txtaddress.getText().toString().trim());
                        std.getConNo(Integer.parseInt(txtcontact.getText().toString().trim()));

                        dbref.push().setValue(std);
                        Toast.makeText(getApplicationContext(),"Data Saved Successfully",Toast.LENGTH_SHORT).show();
                        clearControls();

                   }
               }
               catch(NumberFormatException e){
                   Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();

               }
           }
       });

    }
}
