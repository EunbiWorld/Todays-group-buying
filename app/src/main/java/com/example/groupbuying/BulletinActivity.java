package com.example.groupbuying;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class BulletinActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText etProductName, etPrice, etDescription, etNum;
    private Button btnUploadImage, btnSave;
    private ImageView imgPreview; // 이미지 미리보기를 위한 ImageView 추가
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private Uri imageUri;
    private Spinner spCategory; // Spinner 추가


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin);

        etProductName = findViewById(R.id.etProductName);
        etPrice = findViewById(R.id.etPrice);
        spCategory = findViewById(R.id.spCategory);
        etDescription = findViewById(R.id.etDescription);
        etNum = findViewById(R.id.etNum);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        btnSave = findViewById(R.id.btnSave);
        imgPreview = findViewById(R.id.imgPreview); // 레이아웃에서 ImageView 연결

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });

        //Spinner 연결과 ArrayAdapter 설정
        spCategory = findViewById(R.id.spCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgPreview.setImageBitmap(bitmap); // 이미지 미리보기에 선택한 이미지를 표시
                imgPreview.setVisibility(View.VISIBLE); // 이미지 미리보기를 보이게 설정
                Toast.makeText(BulletinActivity.this, "이미지가 추가되었습니다.", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveProduct() {
        String productName = etProductName.getText().toString();
        String price = etPrice.getText().toString();
        String description = etDescription.getText().toString();
        String num = etNum.getText().toString();
        //saveProduct() 메소드에서 선택된 카테고리 가져오기
        String category = spCategory.getSelectedItem().toString();


        // 필수 입력 항목 확인
        if (TextUtils.isEmpty(productName) || TextUtils.isEmpty(price)
                || TextUtils.isEmpty(category) || TextUtils.isEmpty(description)
                || TextUtils.isEmpty(num)) {
            Toast.makeText(BulletinActivity.this, "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 고유한 이름 생성
        String uniqueName = "(bulletins)" + category + "_" + productName;
        StorageReference storageRef = storage.getReference().child(uniqueName);

        // 이미지가 선택되었을 때만 업로드
        if (imageUri != null) {
            UploadTask uploadTask = storageRef.putFile(imageUri);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Handle unsuccessful uploads
                    Toast.makeText(BulletinActivity.this, "저장 실패 : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // 업로드가 성공적으로 완료되면, 이미지의 다운로드 URL을 가져옵니다.
                    storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            // 다운로드 URL을 얻었습니다. 이제 이 URL과 상품 정보를 Firestore에 저장합니다.
                            Map<String, Object> product = new HashMap<>();
                            product.put("productName", productName);
                            product.put("price", price);
                            product.put("category", category);
                            product.put("description", description);
                            product.put("num", num);
                            product.put("imageUrl", uri.toString());

                            db.collection("bulletins").document(productName).set(product) // Firestore에 저장하는 코드 수정
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(BulletinActivity.this, "저장이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                            resetInputFields();  // 입력 필드 초기화
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(BulletinActivity.this, "저장 실패 : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    });
                }
            });
        } else {
            Toast.makeText(BulletinActivity.this, "저장 실패 : 이미지 없음", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetInputFields() {
        // 모든 입력 필드와 이미지 미리보기를 초기화합니다.
        etProductName.setText("");
        etPrice.setText("");
        etDescription.setText("");
        etNum.setText("");
        imgPreview.setImageURI(null);
        imgPreview.setVisibility(View.GONE); // 이미지 미리보기를 숨김
        imageUri = null;
    }
}
