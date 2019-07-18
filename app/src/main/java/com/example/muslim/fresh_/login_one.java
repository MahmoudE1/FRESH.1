package com.example.muslim.fresh_;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.muslim.fresh_.Retrofit.IFreshAPI;
import com.example.muslim.fresh_.Utils.Common;
import com.example.muslim.fresh_.model.CheckUserResponse;
import com.example.muslim.fresh_.model.User;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dmax.dialog.SpotsDialog;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class login_one  extends AppCompatActivity {
    private static final int REQUEST_CODE = 1000;
    Button bttnlog;
    IFreshAPI myservice ;


    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.login_one );


        myservice = Common.getAPI ( );


        bttnlog = ( Button ) findViewById ( R.id.bttnlog2 ) ;
        bttnlog.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public
            void onClick(View v) {

                startloginpage( LoginType.PHONE);

            }
        } );
    }


    private
    void startloginpage(LoginType loginType) {

        Intent intent = new Intent (this, AccountKitActivity.class );
        AccountKitConfiguration.AccountKitConfigurationBuilder builder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder
                        ( loginType.PHONE , AccountKitActivity.ResponseType.TOKEN );
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,builder.build ());
        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    protected
    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult ( requestCode, resultCode, data );
        if ( requestCode == REQUEST_CODE) {
            final AccountKitLoginResult result = AccountKit.loginResultWithIntent(data);
            if ( result.getError () != null) {
                Toast.makeText ( this, ""+result.getError ().getErrorType ().getMessage (),
                        Toast.LENGTH_SHORT ).show ( );
            }
            else if (result.wasCancelled()) {

                Toast.makeText ( this, "تم الإلغاء", Toast.LENGTH_SHORT ).show ( );
            }
            else
            {
                if (result.getAccessToken ()!= null) {

                    final AlertDialog alertDialog = new SpotsDialog (login_one.this);
                    alertDialog.show ( );
                    alertDialog.setMessage ( "من فضلك انتظر ..." );


                    AccountKit.getCurrentAccount ( new AccountKitCallback <Account> ( ) {
                        @Override
                        public
                        void onSuccess(final Account account) {
                            myservice.checkUserExists ( account.getPhoneNumber ().toString () )
                                    .enqueue ( new Callback <CheckUserResponse> ( ) {

                                        @Override
                                        public
                                        void onResponse(Call <CheckUserResponse> call, Response <CheckUserResponse> response) {
                                            CheckUserResponse userResponse = response.body ();
                                            if ( userResponse.isExists () ){



                                            }
                                            else {


                                                showeRegisterDialog( account.getPhoneNumber ().toString () );

                                            }

                                        }

                                        @Override
                                        public
                                        void onFailure(Call <CheckUserResponse> call, Throwable t) {

                                        }
                                    } );

                        }

                        @Override
                        public
                        void onError(AccountKitError accountKitError) {

                            Log.d ("ERROR",accountKitError.getErrorType ().getMessage () );

                        }
                    } );


                }


            }

        }


    }

    private
    void showeRegisterDialog(final String phone ) {
        final Builder alertDialog = new Builder ( login_one.this );
        alertDialog.setTitle ( "REGISTER" ) ;

        LayoutInflater inflater = this.getLayoutInflater ();
        View register_layout = inflater.inflate (R.layout.register_layout, null );


        final MaterialEditText editname = (MaterialEditText) register_layout.findViewById ( R.id.edit_name );
        final MaterialEditText editaddress = ( MaterialEditText) register_layout.findViewById ( R.id.edit_address );
        final MaterialEditText editbirthdate = (MaterialEditText) register_layout.findViewById ( R.id.edit_birthdate );
        Button button = (Button) register_layout.findViewById ( R.id.sign_in );


        editbirthdate.addTextChangedListener ( new PatternedTextWatcher ("###-##-##"));


        button.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public
            void onClick(View v) {

                alertDialog.create ().dismiss ();



                if ( TextUtils.isEmpty(editaddress.getText().toString())) {
                    Toast.makeText ( login_one.this, "من فضلك أدخل العنوان", Toast.LENGTH_SHORT ).show ( );
                    return;
                }
                if ( TextUtils.isEmpty(editbirthdate.getText().toString())) {
                    Toast.makeText ( login_one.this, "من فضلك أدخل تاريخ الميلاد", Toast.LENGTH_SHORT ).show ( );
                    return;
                }
                if ( TextUtils.isEmpty(editname.getText().toString())) {
                    Toast.makeText ( login_one.this, "من فضلك أدخل الاسم", Toast.LENGTH_SHORT ).show ( );
                    return;
                }
                final AlertDialog watingDialog = new SpotsDialog (login_one.this);
                watingDialog.show ( );
                watingDialog.setMessage ( "من فضلك انتظر ..." );
                myservice.registerNewUser ( phone ,
                        editname.getText ().toString (),
                        editaddress.getText ().toString (),
                        editbirthdate.getText ().toString ()
                ).enqueue ( new Callback <User> ( ) {
                    @Override
                    public
                    void onResponse(Call <User> call, Response <User> response) {
                        watingDialog.dismiss();
                        User user = response.body ();
                        if ( TextUtils.isEmpty ( user.getError_msg () ) ){

                            Toast.makeText ( login_one.this, "تم التسجيل بنجاح", Toast.LENGTH_SHORT ).show ( );

                        }
                    }

                    @Override
                    public
                    void onFailure(Call <User> call, Throwable t) {
                        watingDialog.dismiss();




                    }
                } );


            }
        } );

        alertDialog.setView ( register_layout );
        alertDialog.show ();

    }

    private void kayhash (){
        try {
            PackageInfo
                    info = getPackageManager().getPackageInfo( "com.example.muslim.fresh" ,
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace ();

        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace ();


        }
    }

}


