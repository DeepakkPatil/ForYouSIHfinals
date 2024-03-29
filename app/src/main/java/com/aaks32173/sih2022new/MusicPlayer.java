package com.aaks32173.sih2022new;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class MusicPlayer extends AppCompatActivity{
    String group;
    String path;
    private boolean checkPermission = false;
    Uri uri;
    String songName,songUrl;
    ListView listView;
    ImageView imageviewbg;
    DatabaseReference dbref;
    FirebaseUser muser;
    String current_email;
    Integer age;
    ArrayList<String> arrayListSongsName = new ArrayList<>();
    ArrayList<String> arrayListSongsUrl = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    JcPlayerView jcPlayerView;
    ArrayList<JcAudio> jcAudios = new ArrayList<>();

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        listView = findViewById(R.id.myListView);
        jcPlayerView = findViewById(R.id.jcplayer);
        path = getIntent().getExtras().getString("path");
        group = getIntent().getExtras().getString("grp");
        retrieveSongs(path);
        imageviewbg = findViewById(R.id.musicplayer_bg);
        muser = FirebaseAuth.getInstance().getCurrentUser();
        current_email = muser.getEmail();
        dbref = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(encodeUserEmail(current_email)).child("info");
        dbref.child("age").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                age = Integer.parseInt(snapshot.getValue().toString());
                if (age>10 && !group.equals("podcast")){
                    imageviewbg.setBackground(getDrawable(R.drawable.musiccheck_bg1));
//            musicplayer_bg.setBackground(getDrawable(R.drawable.musiccheck_bg1));
                }
                else if (age>10 && group.equals("podcast")){
                    imageviewbg.setBackground(getDrawable(R.drawable.mic));

                }
                else {
                    imageviewbg.setBackground(getDrawable(R.drawable.musiccheck_bg1));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                jcPlayerView.playAudio(jcAudios.get(position));
                jcPlayerView.setVisibility(View.VISIBLE);
                //jcPlayerView.createNotification(
                // );

            }
        });




    }

    @Override
    public void onBackPressed() {
        jcPlayerView.pause();
        super.onBackPressed();
    }

    private void retrieveSongs(String path) {


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(path);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds :dataSnapshot.getChildren()){

                    Song songObj = ds.getValue(Song.class);
                    arrayListSongsName.add(songObj.getSongName());
                    arrayListSongsUrl.add(songObj.getSongUrl());
                    jcAudios.add(JcAudio.createFromURL(songObj.getSongName(),songObj.getSongUrl()));


                }

                arrayAdapter = new ArrayAdapter<String>(MusicPlayer.this,android.R.layout.simple_list_item_1,arrayListSongsName){


                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                        View view = super.getView(position,convertView,parent);
                        TextView textView = (TextView)view.findViewById(android.R.id.text1);

                        textView.setSingleLine(true);
                        textView.setMaxLines(1);



                        return view;
                    }
                };
                jcPlayerView.initPlaylist(jcAudios,null);
                listView.setAdapter(arrayAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()== R.id.nav_upload){

            if(validatePermision()){
                pickSong();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void pickSong() {

        Intent intent_upload = new Intent();
        intent_upload.setType("audio/*");
        intent_upload.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent_upload,1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode ==1){
            if(resultCode ==RESULT_OK){

                uri = data.getData();

                Cursor mcursor = getApplicationContext().getContentResolver()
                        .query(uri,null,null,null,null);

                int indexedname = mcursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                mcursor.moveToFirst();
                songName = mcursor.getString(indexedname);
                mcursor.close();

                uploadSongToFirebaseStorage();


            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadSongToFirebaseStorage() {

        StorageReference storageReference = FirebaseStorage.getInstance().getReference()
                .child("Songs").child(uri.getLastPathSegment());

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlSong = uriTask.getResult();
                songUrl = urlSong.toString();

                uploadDetailsToDatabase();
                progressDialog.dismiss();





            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MusicPlayer.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progres = (100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                int currentProgress = (int)progres;
                progressDialog.setMessage("Uploaded: "+currentProgress+"%");
            }
        });




    }

    private void uploadDetailsToDatabase() {

        Song songObj = new Song(songName,songUrl);

        FirebaseDatabase.getInstance().getReference("Songs")
                .push().setValue(songObj).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MusicPlayer.this, "Song Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MusicPlayer.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private boolean validatePermision(){

        Dexter.withActivity(MusicPlayer.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        checkPermission = true;
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        checkPermission = false;
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

        return checkPermission;
    }
    private String encodeUserEmail(String email) {
        return email.replace(".",",");
    }

}
