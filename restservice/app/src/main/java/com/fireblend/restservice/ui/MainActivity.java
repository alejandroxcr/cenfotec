package com.fireblend.restservice.ui;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fireblend.restservice.R;
import com.fireblend.restservice.entities.Post;
import com.fireblend.restservice.services.GestorMiServicio;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        try {
            at.execute(new URL("https://jsonplaceholder.typicode.com/posts"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
*/

        GestorMiServicio.MiServicio servicio =
                GestorMiServicio.obtenerServicio();

        Call<List<Post>> llamada = servicio.getPosts();

        llamada.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();

                for(Post post : posts){
                    Log.d("TAG", post.body);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        Call<Post> llamada2 = servicio.getPostById("5");
        llamada2.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post = response.body();

                Log.d("TAG", post.body);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        Post postNuevo = new Post();
        postNuevo.id = 500;
        postNuevo.userId = 1000;
        postNuevo.title = "Titulo";
        postNuevo.body = "Cuerpo del mensaje";

        Call<Post> llamada3 = servicio.createPost(postNuevo);

        llamada3.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post resultado = response.body();

                Log.d("TAG", resultado.body);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }



    AsyncTask<URL, Integer, Boolean> at = new AsyncTask<URL, Integer, Boolean>() {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(URL[] objects) {
            try {

                HttpsURLConnection connection =(HttpsURLConnection)
                        objects[0].openConnection();

                connection.setRequestMethod("GET");

                connection.setRequestProperty("User-Agent", "my-rest-app");
                connection.setRequestProperty("Contact-Me", "sergiome@gmail.com");

                if (connection.getResponseCode() == 200) {
                    InputStreamReader responseBodyReader =
                            new InputStreamReader(connection.getInputStream(), "UTF-8");

                    BufferedReader br = new BufferedReader(responseBodyReader);

                    StringBuilder total = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        total.append(line).append('\n');
                    }

                    String jsonString = total.toString();

                    JSONArray json = new JSONArray(jsonString);

                    JSONObject objeto5 = json.getJSONObject(5);

                    String body = objeto5.getString("body");

                    objeto5.put("atributo", "Hola");

                    objeto5.toString();

                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean fueExitoso) {
            super.onPostExecute(fueExitoso);
            if(fueExitoso){
                Toast.makeText(MainActivity.this, "Exito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
