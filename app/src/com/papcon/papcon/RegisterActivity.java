package com.papcon.papcon;

import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    String checkedID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText addressText = (EditText) findViewById(R.id.addressText);
        final EditText introductionText = (EditText) findViewById(R.id.introductionText);

        Button registerButton = (Button) findViewById(R.id.registerButton);

        final Spinner sexSpinner = (Spinner) findViewById(R.id.sexSpinner);
        final ArrayAdapter sAdapter = ArrayAdapter.createFromResource(this,R.array.sex,R.layout.spinner_item);
        sexSpinner.setAdapter(sAdapter);
        sexSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) sexSpinner.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button idcheckButton = (Button) findViewById(R.id.idcheckButton);

        idcheckButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String userID = idText.getText().toString();

                Response.Listener<String> reponseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){ // 중복
                                Toast.makeText(RegisterActivity.this,"이미 사용중인 ID 입니다.", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"사용 가능한 ID 입니다.",Toast.LENGTH_SHORT).show();
                                checkedID = userID;
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                IDcheckRequest idcheckRequest = new IDcheckRequest(userID, reponseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(idcheckRequest);
            }

        });

        final Spinner ageSpinner = (Spinner) findViewById(R.id.ageSpinner);
        final ArrayAdapter aAdapter = ArrayAdapter.createFromResource(this,R.array.age,R.layout.spinner_item);
        ageSpinner.setAdapter(aAdapter);
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) ageSpinner.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userType = radioButton.getText().toString();
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String userName = nameText.getText().toString();
                String userSex = sexSpinner.getSelectedItem().toString();
                int userAge = Integer.parseInt(ageSpinner.getSelectedItem().toString());
                String userAddress = addressText.getText().toString();
                String userInfo = introductionText.getText().toString();


                Response.Listener<String> responseListner = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this,R.style.DialogStyle);
                                builder.setMessage("회원 등록에 실패했습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                if(!checkedID.equals(userID)){
                    Toast.makeText(RegisterActivity.this,"아이디 중복확인을 해주세요.",Toast.LENGTH_SHORT).show();
                }
                else if (!Pattern.matches("^[a-zA-Z0-9]*.{4,20}$",userID )){
                    Toast.makeText(RegisterActivity.this,"아이디 형식이 아닙니다.",Toast.LENGTH_SHORT).show();
                }
                else if (!Pattern.matches("^[a-zA-Z0-9!@.#$%^&*?_~]{6,20}$", userPassword)){
                    Toast.makeText(RegisterActivity.this,"비밀번호 형식이 아닙니다.",Toast.LENGTH_SHORT).show();
                }
                else if (!Pattern.matches("[가-힣a-zA-Z0-9!@.#$%^&*?_~]{1,10}$",userName)) {
                    Toast.makeText(RegisterActivity.this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(userAddress.equals("")){
                    Toast.makeText(RegisterActivity.this,"거주지역을 입력해주세요",Toast.LENGTH_SHORT).show();
                }
                else if(userInfo.equals("")){
                    Toast.makeText(RegisterActivity.this,"자기소개를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
                else {
                    RegisterRequest registerRequest = new RegisterRequest(userType, userID, userPassword, userName, userSex, userAge, userAddress, userInfo, responseListner);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);

                }
            }
        });{
        }
    }
}
