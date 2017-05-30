package com.example.felipe.sgl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
/*
    Created by Felipe Monzón
    Fecha: 28/05/2017.
*/

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private Button _loginButton;
    private EditText _usuarioText;
    private EditText _passwordText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        _loginButton = (Button) findViewById(R.id.btn_login);
        _usuarioText = (EditText) findViewById(R.id.txtUsuario);
        _passwordText = (EditText) findViewById(R.id.txtPassword);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login"); //ESCRIBE EN EL LOG

        if (!validaDatos()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Por Favor Espere...");
        progressDialog.show();

        String usuario = _usuarioText.getText().toString();
        String password = _passwordText.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Error al Iniciar Sessión", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }


    public boolean validaDatos(){
        boolean isvalid = true;

        String usuario = _usuarioText.getText().toString();
        String password = _passwordText.getText().toString();

        if (usuario.isEmpty() || usuario.length() < 5 ) {
            _usuarioText.setError("Usuario y/o Contraeña Incorrecto");
            isvalid = false;
        } else {
            _usuarioText.setError(null);
        }

        if (password.isEmpty() || password.length() < 8) {
            _passwordText.setError("Usuario y/o Contraeña Incorrecto");
            isvalid = false;
        } else {
            _passwordText.setError(null);
        }

        return isvalid;
    }
}
