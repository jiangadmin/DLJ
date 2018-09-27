package com.jiang.dlj.scan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jiang.dlj.R;
import com.zebra.adc.decoder.Barcode2DWithSoft;

import java.io.UnsupportedEncodingException;

/*
new demo 20171212
 */
public class Scan_Activity extends AppCompatActivity {
    String TAG = "MainActivity";
    String barCode = "";
    EditText data1;
    Button btn;
    Barcode2DWithSoft barcode2DWithSoft = null;
    String seldata = "UTF-8";

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Scan_Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        barcode2DWithSoft = Barcode2DWithSoft.getInstance();

        data1 = findViewById(R.id.editText);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(view -> ScanBarcode());

        new InitTask().execute();
    }


    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        if (barcode2DWithSoft != null) {
            barcode2DWithSoft.stopScan();
            barcode2DWithSoft.close();
        }
        super.onDestroy();
    }

    public Barcode2DWithSoft.ScanCallback ScanBack = new Barcode2DWithSoft.ScanCallback() {
        @Override
        public void onScanComplete(int i, int length, byte[] bytes) {
            if (length < 1) {
                if (length == -1) {
                    data1.setText("Scan cancel");
                } else if (length == 0) {
                    data1.setText("Scan TimeOut");
                } else {
                    Log.i(TAG, "Scan fail");
                }
            } else {
                SoundManage.PlaySound(Scan_Activity.this, SoundManage.SoundType.SUCCESS);
                barCode = "";

                //  String res = new String(dd,"gb2312");
                try {
                    Log.i("Ascii", seldata);
                    barCode = new String(bytes, 0, length, seldata);

                } catch (UnsupportedEncodingException ex) {
                }
                data1.setText(barCode);
            }

        }
    };

    private void ScanBarcode() {
        if (barcode2DWithSoft != null) {
            Log.i(TAG, "ScanBarcode");

            barcode2DWithSoft.scan();
            barcode2DWithSoft.setScanCallback(ScanBack);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 139) {
            if (event.getRepeatCount() == 0) {
                ScanBarcode();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == 139) {
            if (event.getRepeatCount() == 0) {
                barcode2DWithSoft.stopScan();
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    public class InitTask extends AsyncTask<String, Integer, Boolean> {
        ProgressDialog mypDialog;

        @Override
        protected Boolean doInBackground(String... params) {

            boolean reuslt = false;
            if (barcode2DWithSoft != null) {
                reuslt = barcode2DWithSoft.open(Scan_Activity.this);
                Log.i(TAG, "open=" + reuslt);

            }
            return reuslt;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {

                barcode2DWithSoft.setParameter(6, 1);
                barcode2DWithSoft.setParameter(22, 0);
                barcode2DWithSoft.setParameter(23, 55);

                Toast.makeText(Scan_Activity.this, "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Scan_Activity.this, "fail", Toast.LENGTH_SHORT).show();
            }
            mypDialog.cancel();
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            mypDialog = new ProgressDialog(Scan_Activity.this);
            mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mypDialog.setMessage("init...");
            mypDialog.setCanceledOnTouchOutside(false);
            mypDialog.show();
        }
    }
}
