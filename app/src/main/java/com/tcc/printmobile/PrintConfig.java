package com.tcc.printmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.tcc.printmobile.model.File;
import com.tcc.printmobile.model.Img;
import com.tcc.printmobile.model.Pdf;
import com.tcc.printmobile.service.PostFile;

import java.util.concurrent.ExecutionException;

/**
 * Created by ddutra9 on 13/09/15.
 */
public class PrintConfig extends ActionBarActivity {

    private Toolbar mToobar2;
    private Img img = null;
    private Pdf pdf = null;
    private File file = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracao_de_pagina);

        mToobar2 = (Toolbar) findViewById(R.id.tb_confpagina);
        mToobar2.setTitle("Configurar impressão");
        mToobar2.setLogo(R.mipmap.icon);
        setSupportActionBar(mToobar2);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if(params.containsKey("image"))
        {
            Log.d("printconfig", "tem imagem");
            byte[] bImage = params.getByteArray("image");
            img = new Img(false, false, 0);
            img.setByteOfObj(bImage);
            file = img;
            EditText etIntervalo = (EditText) findViewById(R.id.etIntervalo);
            etIntervalo.setVisibility(View.INVISIBLE);
            Log.d("Debug_img: ", file.getByteOfObj().toString());
            Log.d("img_base64", Base64.encodeToString(file.getByteOfObj(), Base64.NO_WRAP));
        } else {
            Log.d("printconfig", "tem pdf");
            byte[] bPdf = params.getByteArray("pdf");
            pdf = new Pdf(false, false, 1, null);
            pdf.setByteOfObj(bPdf);
            file = pdf;
            Log.d("Debug: ", params.toString());
        }

        Button btnPrint = (Button) findViewById(R.id.btImprimir);

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populate();
                PostFile postFile = new PostFile();
                postFile.execute(file);

                try {
                    if(postFile.get() != null)
                        Toast.makeText(getApplicationContext(), "Impresso com sucesso!", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), postFile.get(), Toast.LENGTH_LONG).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    Log.d("Debug: ",e.toString());
                    e.printStackTrace();
                }
                //Toast.makeText(getApplicationContext(), "Erro ao imprimir arquivo!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populate(){
        if(pdf != null){
            EditText etIntervalo = (EditText) findViewById(R.id.etIntervalo);
            pdf.setIntervalPage(etIntervalo.getText().toString());
        }

        Switch swtColor = (Switch) findViewById(R.id.swtColor);
        Switch swtPaisagem = (Switch) findViewById(R.id.swtPaisagem);
        EditText edCopies = (EditText) findViewById(R.id.edCopies);

        file.setColorful(swtColor.isChecked());
        file.setLandscape(swtPaisagem.isChecked());
        if(isEmpty(edCopies)) {
            file.setCopies(Integer.parseInt(edCopies.getText().toString()));
            Log.d("copies", "" + Integer.parseInt(edCopies.getText().toString()));
        }

        Log.d("populate", file.toString());
    }

    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().isEmpty();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
