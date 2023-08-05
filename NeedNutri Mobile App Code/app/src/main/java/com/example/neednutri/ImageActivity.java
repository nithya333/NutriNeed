//package com.example.neednutri;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.os.Bundle;
////import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.io.InputStream;
//public class ImageActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.image_page);
//        // Image link from internet
//        new DownloadImageFromInternet((ImageView) findViewById(R.id.image_view)).execute("https://static.toiimg.com/thumb/msid-78767001,width-1280,resizemode-4/78767001.jpg");
//    }
//    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
//        ImageView imageView;
//        public DownloadImageFromInternet(ImageView imageView) {
//            this.imageView=imageView;
//            Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...",Toast.LENGTH_SHORT).show();
//        }
//        protected Bitmap doInBackground(String... urls) {
//            String imageURL=urls[0];
//            Bitmap bimage=null;
//            try {
//                InputStream in=new java.net.URL(imageURL).openStream();
//                bimage=BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error Message", e.getMessage());
//                e.printStackTrace();
//            }
//            return bimage;
//        }
//        protected void onPostExecute(Bitmap result) {
//            imageView.setImageBitmap(result);
//        }
//    }
//}