package css.cis3334.teambuilder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * This class contains buttons and their logic for users to sign into the application.
 * This class extends AppCompatActivity, which is the base class for activities that use
 * the support library action bar features.
 *
 * @author Izaiah Bishop
 *
 * @version 5.5.2017
 */
public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    Button buttonLogin, buttonCreateNewUser;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    /**
     * This method opens the activity login layout for users to sign into the app. We call
     * both the login and create buttons as well.
     *
     * @param savedInstanceState Refers to the state of the bundle object passed into onCreate.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        loginButton();
        createButton();
    }

    /**
     * This contains logic for the login button. There is an on click listener to
     * detect when the button is being pushed. Email and password text are
     * converted to a string and sent as parameters to the signIn method.
     */
    private void loginButton() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("Pokemon", "Signing in the user");
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                signIn(email, password);
            }
        });
    }

    /**
     * This contains logic for the create user button. There is an on click listener to
     * detect when the button is being pushed. Email and password text are
     * converted to a string and sent as parameters to the createAccount method.
     */
    private void createButton() {
        buttonCreateNewUser = (Button) findViewById(R.id.buttonCreate);
        buttonCreateNewUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("CIS3334", "Creating a new user account");
                //create account for new user
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                createAccount(email, password);
            }
        });
    }

    /**
     * This method uses firebase authentication to create new users.
     *
     * @param email The email of the user passed as a string
     * @param password The password of the user passed as a string
     */
    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {  //update listener.
                if (!task.isSuccessful()) {         //when failed
                    Toast.makeText(LoginActivity.this, "You cannot create this user. Please enter valid credentials, or sign in with existing credentials.",Toast.LENGTH_LONG).show();
                } else {                            //return to MainActivity if login works
                    Intent mainActIntent = new Intent(getBaseContext(), MainActivity.class);
                    finish();                       //Close LoginActivity
                    startActivity(mainActIntent);   //Start up the MainActivity
                }
            }
        });
    }

    /**
     * This method uses firebase authentication to sign in existing users.
     *
     * @param email The email of the user passed as a string
     * @param password The password of the user passed as a string
     */
    private void signIn(String email, String password) {
        //sign in the recurrent user with email and password previously created.
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() { //add to listener
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {         //when failed
                    Toast.makeText(LoginActivity.this, "Sign in failed. Please use existing credentials, or create a new user.", Toast.LENGTH_LONG).show();
                } else {                            //return to MainActivity if login works
                    Intent mainActIntent = new Intent(getBaseContext(), MainActivity.class);
                    finish();                       //Close LoginActivity
                    startActivity(mainActIntent);   //Start up the MainActivity
                }
            }
        });
    }

}