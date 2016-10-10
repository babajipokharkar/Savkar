package com.babasoft.savkar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.babasoft.savkar.adapter.CustomStringArrayAdapter;
import com.babasoft.savkar.receiver.AlarmReceiver;
import com.babasoft.savkar.utils.DataBaseManager;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.regex.Pattern;

public class AddCustomer extends AppCompatActivity {
    ImageView profile_image,vitnessphoto;
    private static final int CAMERA_REQUEST = 1888;
    private static final int CAMERA_REQUEST_VITNESSIMAGE = 1811;
    Button btnsubmit;
    LinearLayout repeate_view;
    DataBaseManager dataBaseManager;
    Spinner spinner_repeatetime,timeunit,array_before_duespinner;
    AppCompatRadioButton radio_repeat,not_repeat;
    EditText input_name,inputEmail,input_phone,input_address,inputamount,edt_interest;
    private ArrayAdapter<String> apModeAdapter;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        profile_image=(ImageView)findViewById(R.id.profile_image);
        vitnessphoto=(ImageView)findViewById(R.id.vitnessphoto);
        btnsubmit=(Button) findViewById(R.id.btnsubmit);
        spinner_repeatetime=(Spinner)findViewById(R.id.repeatetime);
        timeunit=(Spinner)findViewById(R.id.timeunit);
        array_before_duespinner=(Spinner)findViewById(R.id.array_before_duespinner);
        input_name=(EditText)findViewById(R.id.input_name);
        inputEmail=(EditText)findViewById(R.id.inputEmail);
        input_phone=(EditText)findViewById(R.id.input_phone);
        radio_repeat=(AppCompatRadioButton)findViewById(R.id.radio_repeat);
        not_repeat=(AppCompatRadioButton)findViewById(R.id.not_repeat);
        input_address=(EditText)findViewById(R.id.input_address);
        repeate_view=(LinearLayout)findViewById(R.id.repeate_view);
        inputamount=(EditText)findViewById(R.id.inputamount);
        edt_interest=(EditText)findViewById(R.id.edt_interest);
        dataBaseManager=new DataBaseManager(this);
        dataBaseManager.open();
        Resources res = getResources();
        CustomStringArrayAdapter customStringArrayAdapter=new CustomStringArrayAdapter(this,R.id.spinner_textview,R.id.spinner_textview,res.getStringArray(R.array.repetetimeperiod),this);
        CustomStringArrayAdapter customStringSpinnerunit=new CustomStringArrayAdapter(this,R.id.spinner_textview,R.id.spinner_textview,res.getStringArray(R.array.timeunit),this);
        CustomStringArrayAdapter array_before_due=new CustomStringArrayAdapter(this,R.id.spinner_textview,R.id.spinner_textview,res.getStringArray(R.array.array_before_due),this);
        array_before_duespinner.setAdapter(array_before_due);
        apModeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, res.getStringArray(R.array.repetetimeperiod) );
        timeunit.setAdapter(customStringSpinnerunit);
        spinner_repeatetime.setAdapter(customStringArrayAdapter);
        if(not_repeat.isChecked()){
            repeate_view.setVisibility(View.GONE);
        }
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        vitnessphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST_VITNESSIMAGE);
            }
        });
        radio_repeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    repeate_view.setVisibility(View.VISIBLE);
                    not_repeat.setChecked(false);
                    radio_repeat.setChecked(true);

                }/*else{
                    repeate_view.setVisibility(View.GONE);
                }*/
            }
        });
        not_repeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    repeate_view.setVisibility(View.GONE);
                    radio_repeat.setChecked(false);
                    not_repeat.setChecked(true);
                }/*else{
                    repeate_view.setVisibility(View.GONE);
                    not_repeat.setChecked(true);
                }*/
            }
        });
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regex = "-?\\d+(\\.\\d+)?";
                Pattern ptrn = Pattern.compile(regex);
                if(input_name.getText()==null || input_name.getText().equals("") || input_name.getText().toString().length()<5){
                    input_name.setError("Please Check Name");
                }else if(!isValidEmail(inputEmail.getText())){
                   inputEmail.setError("Please Check email id");
                }else if(!isValidPhoneNumber(input_phone.getText())){
                    input_phone.setError("Please Check Mobile number");
                }else if(input_address.getText()==null || input_address.getText().equals("") || input_address.getText().toString().length()<9){
                    input_address.setError("Please Check Address");
                }else if(!ptrn.matcher(inputamount.getText().toString().trim()).matches()){
                    inputamount.setError("Please Check Amount");
                }else if(!ptrn.matcher(edt_interest.getText().toString().trim()).matches()){
                    edt_interest.setError("Please Check Interest");
                }else{
                    profile_image.buildDrawingCache();
                    Bitmap b = profile_image.getDrawingCache();
                   // Bitmap b = BitmapFactory.decodeResource(profile_image.getIma);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    b.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    byte[] photo = bos.toByteArray();
                    vitnessphoto.buildDrawingCache();
                    Bitmap b1 = vitnessphoto.getDrawingCache();
                    // Bitmap b = BitmapFactory.decodeResource(profile_image.getIma);
                    ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
                    b1.compress(Bitmap.CompressFormat.JPEG, 100, bos1);
                    byte[] vitnessphoto = bos1.toByteArray();
                    dataBaseManager.createCustomer(input_name.getText().toString(),input_phone.getText().toString(),inputEmail.getText().toString()
                            ,input_address.getText().toString(),Integer.parseInt(inputamount.getText().toString()),
                            Integer.parseInt(edt_interest.getText().toString()),photo,vitnessphoto);
                    Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();

                    alarmMgr = (AlarmManager)getSystemService(ALARM_SERVICE);
                    Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
// Set the alarm to start at 8:30 a.m.
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, 18);
                    calendar.set(Calendar.MINUTE, 48);

// setRepeating() lets you specify a precise custom interval--in this case,
// 20 minutes.
                    alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            1000 * 60 * 5, alarmIntent);

                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            profile_image.setImageBitmap(photo);
        }else if (requestCode == CAMERA_REQUEST_VITNESSIMAGE) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            vitnessphoto.setImageBitmap(photo);
        }
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    public final static boolean isValidPhoneNumber(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            if (target.length() < 9 || target.length() > 13) {
                return false;
            } else {
                return android.util.Patterns.PHONE.matcher(target).matches();
            }
        }
    }
}
