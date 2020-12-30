package com.magic.mtgquicksearchjava.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.magic.mtgquicksearchjava.R;
import com.magic.mtgquicksearchjava.model.Card;
import com.magic.mtgquicksearchjava.rest.APIClient;
import com.magic.mtgquicksearchjava.rest.ScryfallEndPoints;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardActivity extends AppCompatActivity
{
    TextView tv;
    Bundle extras;
    String str;
    ImageView cardImg;
    Bitmap cardBit;

    @Override
    protected void onCreate(@Nullable Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.card_activity_layout);

        tv = findViewById(R.id.test_text);
        cardImg = findViewById(R.id.cardImage);

        extras = getIntent().getExtras();
        str = extras.getString("STRING_I_NEED");
        tv.setText(str);

        loadCardData();
    }

    private void loadCardData()
    {
        final ScryfallEndPoints apiService =
                APIClient.getClient().create(ScryfallEndPoints.class);
        Call<Card> call = apiService.getCard(str);
        call.enqueue(new Callback<Card>()
        {
            @Override
            public void onResponse(Call<Card> call, Response<Card> response)
            {
                ImageDownloader task = new ImageDownloader();

                try
                {
                    cardBit = task.execute(response.body().getNormalImage_uris()).get();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                cardImg.setImageBitmap(cardBit);


                if(response.body().getName() == null)
                {
                    tv.setText("No name provided");
                } else {
                    tv.setText("Card name is : " + response.body().getName());
                }

            }

            @Override
            public void onFailure(Call<Card> call, Throwable t)
            {

            }


        });
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                return BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}