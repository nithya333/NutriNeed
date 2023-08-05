package com.example.neednutri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NgoActivity extends AppCompatActivity {

    // creating variables for our list view.
    private ListView coursesLV;


    // creating a new array list.
    ArrayList<String> coursesArrayList;

    // creating a variable for database reference.
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ngo);

        // initializing variables for listviews.
        coursesLV = findViewById(R.id.rest_details);


        // initializing our array list
        coursesArrayList = new ArrayList<String>();

        // calling a method to get data from
        // Firebase and set data to list view
        initializeListView();
    }

    private void initializeListView() {
        // creating a new array adapter for our list view.
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, coursesArrayList);

        // below line is used for getting reference
        // of our Firebase Database.
        reference = FirebaseDatabase.getInstance("https://nutrineed-83fdb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Restaurants");

        // in below line we are calling method for add child event
        // listener to get the child of our database.
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // this method is called when new child is added to
                // our data base and after adding new child
                // we are adding that item inside our array list and
                // notifying our adapter that the data in adapter is changed.
                coursesArrayList.add(snapshot.getKey());
                String all_data = "";
                for (DataSnapshot child : snapshot.getChildren()) {
//                    coursesArrayList.add(child.getValue(String.class));
                    all_data = all_data + child.getKey() + " : " + child.getValue(String.class) + "\n";
                }
                coursesArrayList.add(all_data);
//                coursesArrayList.add(snapshot.getValue(String.class));
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // this method is called when the new child is added.
                // when the new child is added to our list we will be
                // notifying our adapter that data has changed.
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // below method is called when we remove a child from our database.
                // inside this method we are removing the child from our array list
                // by comparing with it's value.
                // after removing the data we are notifying our adapter that the
                // data has been changed.
                coursesArrayList.remove(snapshot.getValue(String.class));

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // this method is called when we move our
                // child in our database.
                // in our code we are note moving any child.
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // this method is called when we get any
                // error from Firebase with error.
            }
        });
        // below line is used for setting
        // an adapter to our list view.
        coursesLV.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}

//import android.os.Bundle;
//import android.util.Log;
//import android.widget.TextView;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class ngo_page extends AppCompatActivity {
//
//    //    private DatabaseReference databaseReference;
//    private DatabaseReference mDatabase;
//    TextView rest_details;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ngo_page);
//
//        // Get a reference to the Firebase database
////        FirebaseDatabase database = FirebaseDatabase.getInstance("https://nutrineed-83fdb-default-rtdb.asia-southeast1.firebasedatabase.app");
////        databaseReference = database.getReference("Halli Mane"); // Replace "your_database_node" with your actual node name
//        mDatabase = FirebaseDatabase.getInstance("https://nutrineed-83fdb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Restaurants");
// rest_details=findViewById(R.id.rest_details);
//        mDatabase.child("Halli Mane").child("food_name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//                } else {
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                    rest_details.setText(String.valueOf(task.getResult().getValue()));
//
//                }
//            }
//        });
//    }
//}