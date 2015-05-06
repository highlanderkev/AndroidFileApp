package com.kpw.fileapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
import java.io.File;
import java.io.RandomAccessFile;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
	final TextView tv = new TextView(this);
	Button b = new Button(this);
	b.setText("Update");
	b.setOnClickListener(new View.OnClickListener(){
		public void onClick(View v){
		    tv.setText(updateFile());
		}
	    });
	LinearLayout ll = new LinearLayout(this);
	ll.setOrientation(LinearLayout.VERTICAL);
	ll.addView(b);
	ll.addView(tv);
       	setContentView(ll);
    }

    public String updateFile(){
	File f = new File(getFilesDir(), "myfile.txt");
	String result;
	try{
	    RandomAccessFile raf = new RandomAccessFile(f, "rw");
	    byte[] buffer = new byte[(int) Math.min(raf.length(), 16384)];
	    raf.readFully(buffer);
	    result = new String(buffer, "US-ASCII");
	    raf.seek(raf.length());
	    raf.writeByte('x');
	    raf.close();
	}catch(Exception e){
	    result = "error: " + e;
	}
	return result;
    }
}
