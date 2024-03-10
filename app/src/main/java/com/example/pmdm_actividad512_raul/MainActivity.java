package com.example.pmdm_actividad512_raul;
import com.example.pmdm_actividad512_raul.R;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencias a los botones
        Button paginaWebButton = findViewById(R.id.paginaWeb);
        Button telefonoButton = findViewById(R.id.telefono);
        Button mapsButton = findViewById(R.id.maps);
        Button fotoButton = findViewById(R.id.foto);
        Button emailButton = findViewById(R.id.email);

        // Configurar el OnClickListener para cada botón
        paginaWebButton.setOnClickListener(this);
        telefonoButton.setOnClickListener(this);
        mapsButton.setOnClickListener(this);
        fotoButton.setOnClickListener(this);
        emailButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Manejar los eventos de clic para cada botón
        if (v.getId() == R.id.paginaWeb) {
            abrirPaginaWeb();
        } else if (v.getId() == R.id.telefono) {
            llamarPorTelefono();
        } else if (v.getId() == R.id.maps) {
            abrirGoogleMaps();
        } else if (v.getId() == R.id.foto) {
            tomarFoto();
        } else if (v.getId() == R.id.email) {
            mandarCorreo();
        }
    }


    private void abrirPaginaWeb() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ieslosmontecillos.es/wp/"));
        startActivity(intent);
    }

    private void llamarPorTelefono() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:644 04 40 11"));
        startActivity(intent);
    }

    private void abrirGoogleMaps() {
        Uri gmmIntentUri = Uri.parse("geo:36.737075460313555, -4.432891041210694");
        Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void tomarFoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, "No se encontró una aplicación de cámara disponible", Toast.LENGTH_SHORT).show();
        }
    }

    private void mandarCorreo() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:destinatario@example.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Asunto del correo");
        intent.putExtra(Intent.EXTRA_TEXT, "Cuerpo del correo");
        startActivity(intent);
    }
}
