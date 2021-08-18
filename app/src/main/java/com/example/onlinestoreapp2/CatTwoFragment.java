package com.example.onlinestoreapp2;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.onlinestoreapp2.Model.Data;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class CatTwoFragment extends Fragment {

    private GridView gridView;
    private DatabaseReference catTwoDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cat_two, container, false);

        gridView = view.findViewById(R.id.catTwo_grid);

        catTwoDatabase = FirebaseDatabase.getInstance().getReference().child("CatTwoDatabase");
        catTwoDatabase.keepSynced(true);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

//        FirebaseRecyclerAdapter<Data, CatTwoViewHolder> adapter = new FirebaseRecyclerAdapter<Data, CatTwoViewHolder>
//                (
//                        Data.class,
//                        R.layout.item_data_grid,
//                        CatTwoViewHolder.class,
//                        catTwoDatabase
//                ) {
//            @Override
//            protected void populateViewHolder(CatTwoViewHolder catTwoViewHolder, Data data, int i) {
//
//                catTwoViewHolder.setTitlFiree(data.getTitle());
//                catTwoViewHolder.setDescription(data.getDescription());
//
//            }
//        };

        ListAdapter listAdapter = new FirebaseListAdapter<Data>
                (
                      getActivity(),
                      Data.class,
                      R.layout.item_data_grid,
                      catTwoDatabase
                )
        {

            @Override
            protected void populateView(View view, Data model, int position) {

                TextView xTitle = (TextView) view.findViewById(R.id.title_grid);
                TextView xDesc = (TextView) view.findViewById(R.id.description_grid);
                ImageView xImage = (ImageView) view.findViewById(R.id.imageview_grid);
                xTitle.setText(model.getTitle());
                xDesc.setText(model.getDescription());

                Picasso.get().load(model.getImage()).networkPolicy(NetworkPolicy.OFFLINE).fit().into(xImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get().load(model.getImage()).into(xImage);
                    }
                });

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getActivity(),CatTwoDetailsActivity.class);

                        intent.putExtra("title",model.getTitle());
                        intent.putExtra("image",model.getImage());
                        intent.putExtra("description",model.getDescription());

                        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation
                                (
                                         getActivity(),
                                        new Pair<>(view.findViewById(R.id.imageview_grid),"picture"),
                                        new Pair<>(view.findViewById(R.id.title_grid),"data"),
                                        new Pair<>(view.findViewById(R.id.description_grid),"content")
                                );

                        ActivityCompat.startActivity(getContext(),intent,compat.toBundle());

                    }
                });

            }

        };


        gridView.setAdapter(listAdapter);

    }



    public static class CatTwoViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView xTitle;
        TextView xDesc;
        ImageView xImage;

        public CatTwoViewHolder(View itemView){
            super(itemView);
            view=itemView;
            xTitle = (TextView) view.findViewById(R.id.titled);
            xDesc = (TextView) view.findViewById(R.id.description);
            xImage = (ImageView) view.findViewById(R.id.imageView);
        }


        public  void setTitle(String title){
            xTitle.setText(title);
        }

        public  void setDescription(String desc){
            xDesc.setText(desc);
        }

        public void setImage(final String image){

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