package ke.co.appslab.manuel.geeksblog;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contact extends AppCompatActivity {
    Button cosend , comap, comailer;
    EditText coname, coemail, comess;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cosend = (Button) findViewById(R.id.cosend);
        coname = (EditText) findViewById(R.id.coname);
        coemail = (EditText) findViewById(R.id.coemail);
        comess = (EditText) findViewById(R.id.comess);
        comap = (Button) findViewById(R.id.comap);
        comailer = (Button) findViewById(R.id.comailer);

    }
    //method to start service
    public void  sendSms(View v) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED){
            //name of method calling message
            MyMessage();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
    }

    private void MyMessage() {
        String myNumber = "+254726871890";
        String myName = coname.getText().toString().trim();
        String myEmail = coemail.getText().toString().trim();
        String myMess = comess.getText().toString().trim();
        String myCont = myName +"\n"+ myEmail +"\n"+ myMess;
        if (myName==null || myName.equals("") || myEmail == null || myEmail.equals("") || myMess==null || myMess.equals("")){
            Toast.makeText(this, "Field can't be empty", Toast.LENGTH_SHORT).show();
        } else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(myNumber, null, myCont, null, null);
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        }
    }

    public  void  onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_SEND_SMS:
            {
                if (grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    MyMessage();
                } else {
                    Toast.makeText(this, "You don't have required permission to make action", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void viewMap(View v){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("geo:-0.511065,34.728878"));
        Intent chooser = Intent.createChooser(i, "Our Location");
        startActivity(chooser);
    }
    public void sendMail(View v){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("email"));
        String[] s={"emashmagak@appslab.co.ke"};
        i.putExtra(Intent.EXTRA_EMAIL,s);
        i.putExtra(Intent.EXTRA_SUBJECT,"Geek Blog Mail");
        i.putExtra(Intent.EXTRA_TEXT,"Edit your message here...");
        i.setType("message/rfc822");
        Intent chooser = Intent.createChooser(i,"Send Mail");
        startActivity(chooser);
    }
}
