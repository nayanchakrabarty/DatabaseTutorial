package com.example.databasetutorial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    FloatingActionButton fab;
    Button addBtn;
    TextInputEditText todo,time;
    ArrayList<itemClass> users;
    ListveiwAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        users =new ArrayList<>();

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialoguebox();
            }
        });

        adapter=new ListveiwAdapter(this,users);
        listView.setAdapter(adapter);


    }

    void showDialoguebox(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View DialogueView = getLayoutInflater().inflate(R.layout.dialougebox,null);

        todo=DialogueView.findViewById(R.id.work);
        time=DialogueView.findViewById(R.id.time);
        addBtn=DialogueView.findViewById(R.id.btnAdd);

        builder.setView(DialogueView);
        final AlertDialog alertDialog=builder.create();
        alertDialog.setCanceledOnTouchOutside(true);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todotext=String.valueOf(todo.getText());
                String timetext=String.valueOf(time.getText());



                Log.e("entrys",todotext);
                Log.e("entry",timetext);

                boolean error=false;
                if(TextUtils.isEmpty(todotext)){
                    todo.setError("Field Can Not Be Empty!");

                    error=true;
                }else if(TextUtils.isEmpty(timetext)){
                    time.setError("Field Can Not Be Empty!");

                    error=true;
                }
                if(!error){
                    users.add(new itemClass(todotext,timetext));
                    adapter.notifyDataSetChanged();
                    alertDialog.dismiss();
                }else {
                    error=false;

                }
            }
        });
        alertDialog.show();
    }

    class itemClass{
        String work;
        String time;
        public itemClass(String todo,String time){
            this.work=todo;
            this.time=time;
        }
    }

    class ListveiwAdapter extends ArrayAdapter<itemClass> {

        public ListveiwAdapter(@NonNull Context context, @NonNull ArrayList<itemClass> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView==null){
                itemClass itemClass=getItem(position);

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.customlistviewlayout, parent, false);
                TextView todotext=convertView.findViewById(R.id.todo);
                TextView timetext=convertView.findViewById(R.id.time);
                todotext.setText(itemClass.work);
                timetext.setText(itemClass.time);



            }

            return convertView;
        }
    }

}
