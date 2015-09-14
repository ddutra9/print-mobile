package com.tcc.printmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.tcc.printmobile.model.Img;

/**
 * Created by ddutra9 on 13/09/15.
 */
public class PrintConfig extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracao_de_pagina);

        Intent intent = getIntent();

        Bundle params = intent.getExtras();

        Log.d("printconfig", "param imagem : " + params.isEmpty());
        if(params.containsKey("image"))
        {
            Log.d("printconfig", "tem imagem");
            byte[] images = params.getByteArray("image");
            Img img = new Img(false, false, 0l);
            img.setByteOfObj(images);
        }
    }

}
