package com.example.onlinestoreapp2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.palette.graphics.Palette;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class CatOneDetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView title;
    private TextView description;
     String xImage = "";


    private Palette.Swatch vibrantSwatch;
    private Palette.Swatch lightVibrantSwatch;
    private Palette.Swatch darkVibrantSwatch;
    private Palette.Swatch mutedSwatch;
    private Palette.Swatch lightMutedSwatch;
    private Palette.Swatch darkMutedSwatch;

    private int swatchNumber;
    private Palette.Swatch dominantSwatch;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_cat_one_details);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setTitle(null);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        imageView = findViewById(R.id.image_data);
        title = findViewById(R.id.title_details);
        description = findViewById(R.id.title_description);

        Intent intent = getIntent();

        String xTitle = intent.getStringExtra("title");
        String xDesc = intent.getStringExtra("description");
        xImage = intent.getStringExtra("image");

        title.setOnClickListener(v -> {
//                loadBitmap();
//            nextSwatch(v);
//            swatchNumber++;
//            title.setText(getSupportActionBar().getTouchables().size());
//            title.setText((int) getSupportActionBar().getCustomView().getY());
        });


//        imageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction()==MotionEvent.ACTION_UP){
//
//                    Bitmap bitmap = imageView.getDrawingCache();
////                    int pixel = bitmap.getPixel((int)getSupportActionBar().getCustomView().getX(),(int)event.getY());
//                    int[] pixels = new int[bitmap.getByteCount()];
//                    bitmap.getPixels(pixels,0,40,40,40,40,40);
//
//                    int r = Color.red(pixels[5]);
//                    int g = Color.green(pixels[5]);
//                    int b = Color.blue(pixels[5]);
//
//                    title.setBackgroundColor(Color.rgb(r,g,b));
////                    description.setText("R: "+r+"\nG: "+g+"\nB: "+b);
//                    description.setText("R: "+bitmap.getByteCount());
//
//
//                    final Drawable actionBar = getResources().getDrawable(R.drawable.ic_round_keyboard_backspace_24);
//                    actionBar.setTint(Color.argb(250,200/(r+1),200/(g+1),200/(b+1) ));
//                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_keyboard_backspace_24);
//
////                    getDrawable(R.id)
//
//                }
//
//                return true;
//            }
//        });

//        getSupportActionBar().getCustomView().getTouchables().

        // BEGIN_INCLUDE(detail_set_view_name)
        /*
         * Set the name of the view's which will be transition to, using the static values above.
         * This could be done in the layout XML, but exposing it via static variables allows easy
         * querying from other Activities
         */
        ViewCompat.setTransitionName(imageView, "picture");
        ViewCompat.setTransitionName(title, "name");
        ViewCompat.setTransitionName(description, "content");
        // END_INCLUDE(detail_set_view_name)

        loadFullSizeImage();
        title.setText(xTitle);
        description.setText(xDesc);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && addTransitionListener()) {
            // If we're running on Lollipop and we have added a listener to the shared element
            // transition, load the thumbnail. The listener will load the full-size image when
            // the transition is complete.

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                Bitmap bitmap = ThumbnailUtils.createImageThumbnail(xImage, MediaStore.Images.Thumbnails.MINI_KIND);
//
//                Picasso.get().load(getImageUri(getApplicationContext(),bitmap)).fit().into(imageView);
//
//            }
        } else {
            // If all other cases we should just load the full-size image now
//            loadFullSizeImage();
        }

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    @RequiresApi(21)
    private boolean addTransitionListener() {
        final Transition transition = getWindow().getSharedElementEnterTransition();

        if (transition != null) {
            // There is an entering shared element transition so add a listener to it
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    // As the transition has ended, we can now load the full-size image
//                    loadFullSizeImage();

                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionStart(Transition transition) {
                    // No-op
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this);
                }


                @Override
                public void onTransitionPause(Transition transition) {
                    // No-op
                }

                @Override
                public void onTransitionResume(Transition transition) {
                    // No-op
                }
            });
            return true;
        }

        // If we reach here then we have not added a listener
        return false;
    }

    private void loadBitmap(){

        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache(true);

        Bitmap bitmap = ( (BitmapDrawable) imageView.getDrawable() ).getBitmap();

        Palette.from(bitmap).maximumColorCount(5).generate(palette -> {
            darkMutedSwatch = palette.getDarkMutedSwatch();
            dominantSwatch = palette.getDominantSwatch();
            lightMutedSwatch = palette.getLightMutedSwatch();
            darkVibrantSwatch = palette.getDarkVibrantSwatch();
            lightVibrantSwatch = palette.getLightVibrantSwatch();
            mutedSwatch = palette.getMutedSwatch();
            vibrantSwatch = palette.getVibrantSwatch();

            getWindow().findViewById(R.id.rootLayout_cat1_details).setBackgroundColor(darkMutedSwatch.getRgb());
            getWindow().findViewById(R.id.title_details).setBackgroundColor(darkMutedSwatch.getRgb());
//            description.setTextColor(Color.WHITE );
//            getResources().getDrawable(R.drawable.back_nav).setTint(lightVibrantSwatch.getBodyTextColor());

                description.setTextColor(darkMutedSwatch.getTitleTextColor());




        });

    }

    private void load_navBar() {
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache(true);
        Bitmap bitmap = ( (BitmapDrawable) imageView.getDrawable() ).getBitmap();

//                    int pixel = bitmap.getPixel((int)getSupportActionBar().getCustomView().getX(),(int)event.getY());
        int[] pixels = new int[bitmap.getByteCount()];
        bitmap.getPixels(pixels,0,40,40,40,40,40);

        int r = Color.red(pixels[5]);
        int g = Color.green(pixels[5]);
        int b = Color.blue(pixels[5]);
//
//            title.setBackgroundColor(Color.rgb(r,g,b));
//            description.setText("R: "+r+"\nG: "+g+"\nB: "+b);


        final Drawable actionBar = getResources().getDrawable(R.drawable.ic_round_keyboard_backspace_24);
        actionBar.setTint(Color.argb(250,200/(r+1),200/(g+1),200/(b+1) ));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_keyboard_backspace_24);
    }

    private void loadFullSizeImage() {

        if (xImage.contains("http")){

            Picasso.get().load(xImage).networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
                @Override
                public void onSuccess() {

                    loadBitmap();
                    load_navBar();
                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(xImage).fit().into(imageView);
                    loadBitmap();
                    load_navBar();
                }
            });
        }else {
            Picasso.get().load(R.drawable.woman).into(imageView);
        }


    }


    private void nextSwatch(View view){

        Palette.Swatch current = null;

        switch (swatchNumber){

            case 0:
                current = vibrantSwatch;
                title.setText("Vibrant Swatch");
                break;
            case 1:
                current = mutedSwatch;
                title.setText("Muted Swatch");
                break;
            case 2:
                current = lightVibrantSwatch;
                title.setText("Light Vibrant Swatch");
                break;
            case 3:
                current = darkVibrantSwatch;
                title.setText("Dark Vibrant Swatch");
                break;
            case 4:
                current = lightMutedSwatch;
                title.setText("Light Muted Swatch");
                break;
            case 5:
                current = darkMutedSwatch;
                title.setText("Dark Muted Swatch");
                break;
            default:
                swatchNumber=0;
                current=dominantSwatch;
                title.setText("Dominant");

        }
//        final Drawable actionBar = getResources().getDrawable(R.drawable.back_nav);
//        title.setText(actionBar.getBounds().toShortString());
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            title.setText(actionBar.getOpticalInsets().toString());
//        }
        if (current!=null){
            getWindow().findViewById(R.id.rootLayout_cat1_details).setBackgroundColor(current.getRgb());
            ((TextView) ( getWindow().findViewById(R.id.title_description) )).setTextColor(current.getTitleTextColor());

        }else{
            getWindow().findViewById(R.id.rootLayout_cat1_details).setBackgroundColor(Color.WHITE);
            ((TextView) ( getWindow().findViewById(R.id.title_description) )).setTextColor(Color.BLACK);

        }

    }



}