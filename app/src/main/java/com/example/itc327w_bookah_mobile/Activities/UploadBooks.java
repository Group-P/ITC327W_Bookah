package com.example.itc327w_bookah_mobile.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.itc327w_bookah_mobile.Model.Book;
import com.example.itc327w_bookah_mobile.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;


public class UploadBooks extends AppCompatActivity {

    Button btnBrowse, btnUpload;
    //TextInputLayout bookAuthor, bookCondition, bookDescription, bookEdition, bookISBN, bookPublisher, bookTitle;
    TextInputEditText et_bookISBN, et_bookTitle, et_bookAuthor, et_bookEdition, et_bookPublisher, et_bookCondition, et_bookDescription;
    ImageView imageView;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_upload_books);

        storageReference = FirebaseStorage.getInstance().getReference("Book");
        databaseReference = FirebaseDatabase.getInstance().getReference("Book");

        btnBrowse = findViewById(R.id.btnBrowsePhoto);
        btnUpload= findViewById(R.id.btnUploadBook);

        imageView = findViewById(R.id.bookImage);

        et_bookISBN = findViewById(R.id.et_bookISBN);
        et_bookTitle = findViewById(R.id.et_bookTitle);
        et_bookAuthor = findViewById(R.id.et_bookAuthor);
        et_bookEdition = findViewById(R.id.et_bookEdition);
        et_bookPublisher = findViewById(R.id.et_bookPublisher);
        et_bookCondition = findViewById(R.id.et_bookCondition);
        et_bookDescription = findViewById(R.id.et_bookDescription);

        progressDialog = new ProgressDialog(UploadBooks.this);// context name as per your project name

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);

            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadBook();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    public void UploadBook() {

        if (FilePathUri != null) {

            progressDialog.setTitle("Book is Uploading...");
            progressDialog.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String bookISBN = et_bookISBN.getText().toString().trim();
                            String bookTitle = et_bookTitle.getText().toString().trim();
                            String bookAuthor = et_bookAuthor.getText().toString().trim();
                            String bookEdition = et_bookEdition.getText().toString().trim();
                            String bookPublisher = et_bookPublisher.getText().toString().trim();
                            String bookCondition = et_bookCondition.getText().toString().trim();
                            String bookDescription = et_bookDescription.getText().toString().trim();
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Book Uploaded Successfully ", Toast.LENGTH_LONG).show();
                            @SuppressWarnings("VisibleForTests")
                            Book bookUploadInfo = new Book(taskSnapshot.getUploadSessionUri().toString(),
                                    bookISBN,bookTitle,bookAuthor,bookEdition,bookPublisher,bookCondition,bookDescription);
                            String ImageUploadId = databaseReference.push().getKey();
                            assert ImageUploadId != null;
                            databaseReference.child(ImageUploadId).setValue(bookUploadInfo);
                        }
                    });
        }
        else {

            Toast.makeText(UploadBooks.this, "Please select an image and/or fill in all the details", Toast.LENGTH_LONG).show();

        }
    }
}