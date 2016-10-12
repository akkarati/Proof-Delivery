package com.hitachi_tstv.yodpanom.yaowaluk.proofdelivery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private TextView nameDriverTextView, idDriverTextView;
    private Button jobListButton, closeButton;
    private ListView listView;
    private String[] loginStrings;
    private MyConstant myConstant = new MyConstant();
    private String[] planIdStrings,planDateStrings, cnt_storeStrings;
    private boolean aBoolean = true;
    private String[] workSheetStrings, storeNameStrings,
            planArrivalTimeStrings, planDtl2_idStrings ;
    private String driverChooseString ,dateChooseString;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Bind Widget
        nameDriverTextView = (TextView) findViewById(R.id.textView2);
        idDriverTextView = (TextView) findViewById(R.id.textView4);
        jobListButton = (Button) findViewById(R.id.button4);
        closeButton = (Button) findViewById(R.id.button2);
        listView = (ListView) findViewById(R.id.listJob);

        //get value from intent
        loginStrings = getIntent().getStringArrayExtra("Login");
        driverChooseString = getIntent().getStringExtra("PlanId");
        dateChooseString = getIntent().getStringExtra("Date");

        if(driverChooseString.length() != 0){
            //From MainActivity
            aBoolean = false;
        } // if

        //Show name
        nameDriverTextView.setText(loginStrings[1]);

        //SyncData
        SynDataWhereByDriverID synDataWhereByDriverID = new SynDataWhereByDriverID(ServiceActivity.this);
        synDataWhereByDriverID.execute(myConstant.getUrlDataWhereDriverID());

        //Close Controller
        closeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }

        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }//Main Method



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Service Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private class SynDataWhereByDriverID extends AsyncTask<String, Void, String> {
        //Explicit
        private ProgressDialog progressDialog;
        private Context context;

        public SynDataWhereByDriverID(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(context);
//            progressDialog.setMessage("Loading...");
//            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder().add("isAdd", "true").add("driver_id", loginStrings[0]).build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strings[0]).post(requestBody).build();
                Response response = okHttpClient.newCall(request).execute();

                return response.body().string();

            } catch (Exception e) {
                Log.d("12octV1", "e doInBack ==> " + e.toString());
                return null;
            }
//            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

//            progressDialog.dismiss();
            Log.d("12octV1", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                planDateStrings = new String[jsonArray.length()];
                cnt_storeStrings = new String[jsonArray.length()];
                planIdStrings = new String[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    planDateStrings[i] = jsonObject.getString("planDate");
                    cnt_storeStrings[i] = jsonObject.getString("cnt_store");
                    planIdStrings[i] = jsonObject.getString("planId");
                }//for
                if (aBoolean) {
                    //true :: not click on button
                    jobListButton.setText("Job List :: " + planDateStrings[0]);

                    createDetailList(planIdStrings[0] );


                } else {
                    // From Job ListView
                    jobListButton.setText("Job List = " +  dateChooseString);
                    createDetailList(driverChooseString);

                }

                // Get Event Form Click
                jobListButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(ServiceActivity.this, JobListView.class);
                        intent.putExtra("Date", planDateStrings);
                        intent.putExtra("Store", cnt_storeStrings);
                        intent.putExtra("Login", loginStrings);
                        intent.putExtra("PlanId", planIdStrings);
                        startActivity(intent);
                        finish();

                    } // onClick
                });


            } catch (Exception e) {
                Log.d("12octV1", "e onPost ==> " + e.toString());

            }
        }
    }//SynDataWhereByDriverID

    private void createDetailList(String planIDString) {

        SynDetail synDetail = new SynDetail(ServiceActivity.this,
                planIDString );
        synDetail.execute(myConstant.getUrlDataWhereDriverIDanDate());
    } //  createDetailList

    private class SynDetail extends AsyncTask<String, Void, String> {

        //Explicit
        private Context context;
        private String planIdString, planDateString;

        public SynDetail(Context context, String planIdString) {
            this.context = context;
            this.planIdString = planIdString;
            this.planDateString = planDateString;
        }


        @Override
        protected String doInBackground(String... params) {

            try{
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd","true")
                        .add("planId",planIdString)
                        .add("driver_id",loginStrings[0])
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(params[0]).post(requestBody).build();
                Response response = okHttpClient.newCall(request).execute();

                return response.body().string();


            }catch(Exception e){
                Log.d("12octV2","e doInBack " + e.toString());
                return null;
            }


        } // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("12octV2", "Json ==> " + s);

            try{

                JSONArray jsonArray = new JSONArray(s);

                workSheetStrings = new String[jsonArray.length()];
                storeNameStrings = new String[jsonArray.length()];
                planArrivalTimeStrings = new String[jsonArray.length()];
                planDtl2_idStrings = new String[jsonArray.length()];

                for (int i=0;i<jsonArray.length();i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    workSheetStrings[i] = jsonObject.getString("work_sheet_no");
                    storeNameStrings[i] = jsonObject.getString("store_nameEng");
                    planArrivalTimeStrings[i] = jsonObject.getString("plan_arrivalTime");
                    planDtl2_idStrings[i] = jsonObject.getString("planDtl2_id");

                } // for

                DetailAdapter detailAdapter = new DetailAdapter(context, workSheetStrings,
                        storeNameStrings, planArrivalTimeStrings);
                listView.setAdapter(detailAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ServiceActivity.this, DetailJob.class);
                        intent.putExtra("Login", loginStrings);
                        intent.putExtra("planDtl2_id", planDtl2_idStrings[position]);
                        startActivity(intent);

                    }
                });

            }catch(Exception e){
                Log.d("12octV2" , "e onPost ==> " + e.toString());
            }
        } // onPost

    } // SynDetail


}//Main Class

