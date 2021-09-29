package com.example.smartrestaurentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Orders extends AppCompatActivity {


    ListView listview1,listview2,listView3,listView4;

    DatabaseReference dbref,dbref2,dbref3,dbref4;

    ValueEventListener listener1,listener2,listener3,listener4;
    ArrayList<String> list,list2,list3,list4;

    ArrayAdapter<String> ad2;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> ad3;
    ArrayAdapter<String> ad4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        //listview decalration
        listview1=(ListView) findViewById(R.id.listview1);
        listview2=(ListView) findViewById(R.id.listview2);
        listView3=(ListView) findViewById(R.id.listview3);
        listView4=(ListView) findViewById(R.id.listview4);

        //Firebase  reference declaration
        dbref=FirebaseDatabase.getInstance().getReference("TableNo1");
        dbref2=FirebaseDatabase.getInstance().getReference("TableNo2");
        dbref3=FirebaseDatabase.getInstance().getReference("TableNo3");
        dbref4=FirebaseDatabase.getInstance().getReference("TableNo4");


        //assigning list to string type
        list=new ArrayList<String>();
        list2=new ArrayList<String>();
        list3=new ArrayList<String>();
        list4=new ArrayList<String>();


        //assigning adapter to array
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        ad2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list2);
        ad3=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list3);
        ad4=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list4);


        //binding listview wth adapter
        listview1.setAdapter(adapter);
        listview2.setAdapter(ad2);
        listView3.setAdapter(ad3);
        listView4.setAdapter(ad4);


        fetchdata();
        adapter.notifyDataSetChanged();
        ad2.notifyDataSetChanged();
        ad3.notifyDataSetChanged();
        ad4.notifyDataSetChanged();
       //root.setValue("Table No 1");
        //Toast.makeText(getApplicationContext(),"Record Inserted", Toast.LENGTH_SHORT).show();

    }

    public void fetchdata()
    {
        listener1=dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot mydata: dataSnapshot.getChildren())
                {
                    list.add(mydata.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listener2=dbref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2.clear();
                for (DataSnapshot mydata: dataSnapshot.getChildren())
                {
                    list2.add(mydata.getValue().toString());
                }
                ad2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listener3=dbref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3.clear();
                for (DataSnapshot mydata: dataSnapshot.getChildren())
                {
                    list3.add(mydata.getValue().toString());
                }
                ad3.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listener4=dbref4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4.clear();
                for (DataSnapshot mydata: dataSnapshot.getChildren())
                {
                    list4.add(mydata.getValue().toString());
                }
                ad4.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}