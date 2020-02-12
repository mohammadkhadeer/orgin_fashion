package com.azez.rest.firebase;

import android.content.Context;
import android.util.Log;

import com.azez.rest.model.Offer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class FireBaseLinkes {

    public static Query getDataBase() {
        Query mRef = FirebaseDatabase.getInstance().getReference();
        return mRef;
    }

    public static List<Offer> getOffers(final List<Offer> offerList) {
        Query mRef =FirebaseDatabase.getInstance().getReference().child("menu").child("category").child("offers");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot offersList: dataSnapshot.getChildren()) {
                    Offer offer = offersList.getValue(Offer.class);
                    offerList.add(offer);
                    //Log.i("TAG", offersList.toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("TAG ERROR", databaseError.toString());

            }

        });
        return offerList;
    }
}
