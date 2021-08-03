package com.example.onlinestoreapp2;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.provider.MediaStore;
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

import java.io.ByteArrayOutputStream;


public class CatOneFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference mCatOneDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cat_one, container, false);

        recyclerView = view.findViewById(R.id.recycler_cat_one);

        mCatOneDatabase = FirebaseDatabase.getInstance().getReference().child("CatOneDatabase");
        mCatOneDatabase.keepSynced(true);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setStackFromEnd(true);
        manager.setReverseLayout(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);

//        StaggeredGridLayoutManager staggered = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        staggered.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
//        recyclerView.setLayoutManager(staggered);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data,CatOneViewHolder> adapter = new FirebaseRecyclerAdapter<Data, CatOneViewHolder>
                (
                        Data.class,
                        R.layout.item_data_custom,
                        CatOneViewHolder.class,
                        mCatOneDatabase
                ) {
            @Override
            protected void populateViewHolder(CatOneViewHolder view, Data data, int i) {

                view.setTitle(data.getTitle());
                try {
                    view.setImage(data.getImage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                view.setDesc(data.getDescription());

                view.xView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),CatOneDetailsActivity.class);

                        intent.putExtra("title",data.getTitle());
                        intent.putExtra("description",data.getDescription());
                        intent.putExtra("image",data.getImage());

                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                getActivity(),

                                // Now we provide a list of Pair items which contain the view we can transitioning
                                // from, and the name of the view it is transitioning to, in the launched activity
                                new Pair<>(v.findViewById(R.id.imageView_cus),
                                        "picture"),
                                new Pair<>(v.findViewById(R.id.title_cus),
                                        "name"),
                                new Pair<>(v.findViewById(R.id.description_cus),
                                        "content"));

                        ActivityCompat.startActivity(getContext(),intent,options.toBundle());

//
                    }
                });

            }

        };

        recyclerView.setAdapter(adapter);

    }

    public static class CatOneViewHolder extends RecyclerView.ViewHolder{

        View xView;

        public CatOneViewHolder(@NonNull View itemView) {
            super(itemView);
            xView = itemView;
        }

        public void setTitle(String title){
            TextView textView = xView.findViewById(R.id.title_cus);
            textView.setText(title);
        }

        public void setDesc(String desc){
            TextView textView = xView.findViewById(R.id.description_cus);
            textView.setText(desc);
        }

        public void setImage(String image) throws Exception {
            ImageView xImage = xView.findViewById(R.id.imageView_cus);
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                Bitmap bitmap = ThumbnailUtils.createImageThumbnail(image, MediaStore.Images.Thumbnails.MINI_KIND);
//
//                Picasso.get().load(getImageUri(getApplicationUsingReflection().getApplicationContext(),bitmap)).fit().into(xImage);
//
//
//            }else
                Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).fit().into(xImage, new Callback() {


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

    public static Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public static Application getApplicationUsingReflection() throws Exception {
        return (Application) Class.forName("android.app.AppGlobals")
                .getMethod("getInitialApplication").invoke(null, (Object[]) null);
    }

}