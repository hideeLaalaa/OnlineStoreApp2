package com.example.onlinestoreapp2;

import android.app.ActivityOptions;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlinestoreapp2.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import maes.tech.intentanim.CustomIntent;

import static maes.tech.intentanim.CustomIntent.customType;

public class AllFragment extends Fragment {

    private RecyclerView allRecycler;
    private RecyclerView recyclerCatTwo;

    //Firebase.

    private DatabaseReference xCartOneDatabase;

    private DatabaseReference xCartTwoDatabase;



    public AllFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        xCartOneDatabase = FirebaseDatabase.getInstance().getReference().child("CatOneDatabase");

        xCartTwoDatabase = FirebaseDatabase.getInstance().getReference().child("CatTwoDatabase");

        //cat one recyclerx

        allRecycler = view.findViewById(R.id.recycler_all);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        allRecycler.setHasFixedSize(true);
        allRecycler.setLayoutManager(layoutManager);

        //cat two recycler
        recyclerCatTwo = view.findViewById(R.id.recycler_two);
        LinearLayoutManager layoutManagerCatTwo = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        layoutManagerCatTwo.setReverseLayout(true);
        layoutManagerCatTwo.setStackFromEnd(true);
        recyclerCatTwo.setHasFixedSize(true);
        recyclerCatTwo.setLayoutManager(layoutManagerCatTwo );


        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data,CatOneViewHolder> adapterOne = new FirebaseRecyclerAdapter<Data, CatOneViewHolder>
                (
                        Data.class,
                        R.layout.item_data,
                        CatOneViewHolder.class,
                        xCartOneDatabase

                ) {
            @Override
            protected void populateViewHolder(CatOneViewHolder viewHolder, Data model, int i) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImage(model.getImage());

                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getActivity(),CatOneDetailsActivity.class);

                        intent.putExtra("title",model.getTitle());
                        intent.putExtra("description",model.getDescription());
                        intent.putExtra("image",model.getImage());
//                        startActivity(intent);
//                        customType(getContext(),"bottom-to-up");=-098u7y6t rewaq



                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                getActivity(),

                                // Now we provide a list of Pair items which contain the view we can transitioning
                                // from, and the name of the view it is transitioning to, in the launched activity
                                new Pair<>(v.findViewById(R.id.imageView),
                                        "picture"),
                                new Pair<>(v.findViewById(R.id.titled),
                                        "name"),
                                new Pair<>(v.findViewById(R.id.description),
                                        "content"));


                        ActivityCompat.startActivity(getContext(),intent,options.toBundle());

//                        startActivity(intent,
//                                ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());

//                        *left-to-right
//                        *right-to-left
//                        *bottom-to-up
//                        *up-to-bottom
//                        *fadein-to-fadeout
//                        *rotateout-to-rotatein

                    }
                });

            }
        };

        allRecycler.setAdapter(adapterOne);

        FirebaseRecyclerAdapter<Data,CatTwoViewHolder> adapterTwo = new FirebaseRecyclerAdapter<Data, CatTwoViewHolder>
                (
                        Data.class,
                        R.layout.item_data,
                        CatTwoViewHolder.class,
                        xCartTwoDatabase

                ) {
            @Override
            protected void populateViewHolder(CatTwoViewHolder viewHolder, Data model, int i) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImage(model.getImage());


                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getActivity(),CatTwoDetailsActivity.class);

                        intent.putExtra("title",model.getTitle());
                        intent.putExtra("description",model.getDescription());
                        intent.putExtra("image",model.getImage());
//                        startActivity(intent);
//                        customType(getContext(),"bottom-to-up");


                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                getActivity(),

                                // Now we provide a list of Pair items which contain the view we can transitioning
                                // from, and the name of the view it is transitioning to, in the launched activity
                                new Pair<>(v.findViewById(R.id.imageView),
                                        "picture"),
                                new Pair<>(v.findViewById(R.id.titled),
                                        "name"));


                        ActivityCompat.startActivity(getContext(),intent,options.toBundle());


//                        startActivity(intent,
//                                ActivityOptions.makeSceneTransitionAnimation
//                                        (getActivity(), v.findViewById(R.id.imageView),"picture").toBundle());


//                         startActivity(intent,
//                                ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());

//                        *left-to-right
//                        *right-to-left
//                        *bottom-to-up
//                        *up-to-bottom
//                        *fadein-to-fadeout
//                        *rotateout-to-rotatein
                    }
                });


            }
        };

        recyclerCatTwo.setAdapter(adapterTwo);

    }

    public static class  CatOneViewHolder extends RecyclerView.ViewHolder{

        View view;

        public CatOneViewHolder(View itemView){
            super(itemView);
            view = itemView;
        }

        public  void setTitle(String title){
            TextView xTitle = view.findViewById(R.id.titled);
            xTitle.setText(title);
        }

        public  void setDescription(String desc){
            TextView xDesc = view.findViewById(R.id.description);
            xDesc.setText(desc);
        }

        public void setImage(final String image){
            final ImageView xImage = view.findViewById(R.id.imageView);

            Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).into(xImage, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(image).fit().into(xImage);
                }
            });
        }

    }

    public static class CatTwoViewHolder extends RecyclerView.ViewHolder{
        View view;

        public CatTwoViewHolder(View itemView){
            super(itemView);
            view=itemView;
        }


        public  void setTitle(String title){
            TextView xTitle = view.findViewById(R.id.titled);
            xTitle.setText(title);
        }

        public  void setDescription(String desc){
            TextView xDesc = view.findViewById(R.id.description);
            xDesc.setText(desc);
        }

        public void setImage(final String image){
            final ImageView xImage = view.findViewById(R.id.imageView);

            Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).into(xImage, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(image).into(xImage);
                }
            });
        }
    }

}