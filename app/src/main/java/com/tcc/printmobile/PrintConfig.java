package com.tcc.printmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tcc.printmobile.model.Img;
import com.tcc.printmobile.model.Pdf;

/**
 * Created by ddutra9 on 13/09/15.
 */
public class PrintConfig extends ActionBarActivity {

    private Toolbar mToobar2;
    private Img img = null;
    private Pdf pdf = null;

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

        Log.d("printconfig", "param imagem : " + params.isEmpty());
        if(params.containsKey("image"))
        {
            Log.d("printconfig", "tem imagem");
            byte[] bImage = params.getByteArray("image");
            img = new Img(false, false, 0l);
            img.setByteOfObj(bImage);
        } else {
            Log.d("printconfig", "tem pdf");
            byte[] bPdf = params.getByteArray("pdf");
            pdf = new Pdf(false, false, 1l, null);
            pdf.setByteOfObj(bPdf);
        }
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
