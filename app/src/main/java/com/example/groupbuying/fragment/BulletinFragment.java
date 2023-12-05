package com.example.groupbuying.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupbuying.fragment.Product;
import com.example.groupbuying.fragment.ProductAdapter;
import com.example.groupbuying.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class BulletinFragment extends Fragment {

    private RecyclerView rvBulletin;
    private ProductAdapter adapter;
    private List<Product> productList;

    public BulletinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bulletin, container, false);

        rvBulletin = view.findViewById(R.id.rv_bulletin);
        rvBulletin.setLayoutManager(new LinearLayoutManager(getContext()));
        productList = new ArrayList<>();
        adapter = new ProductAdapter(getActivity(), productList, R.layout.bulletin_item);
        rvBulletin.setAdapter(adapter);

        loadBulletins();

        return view;
    }

    private void loadBulletins() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // 현재 로그인한 사용자의 정보를 가져옵니다.
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            // 로그인한 사용자의 UID를 가져옵니다.
            String uid = currentUser.getUid();

            // 관리자의 UID를 정의합니다.
            String adminUid = "5Y8iPYqWzOfBbu8Gk1QMZyMnP0f2";

            if (uid.equals(adminUid)) {
                // 관리자인 경우, 모든 게시글을 보여줍니다.
                db.collection("bulletins")
                        .get()
                        .addOnCompleteListener(bulletinTask -> {
                            if (bulletinTask.isSuccessful()) {
                                productList.clear();
                                for (QueryDocumentSnapshot bulletinDocument : bulletinTask.getResult()) {
                                    Product product = bulletinDocument.toObject(Product.class);
                                    productList.add(product);
                                }
                                adapter.notifyDataSetChanged();
                            } else {
                                // Handle the failure
                            }
                        });
                return;
            }
        }

        // 일반 사용자인 경우, 또는 로그인하지 않은 경우 'visible' 필드가 true인 게시글만 보여줍니다.
        db.collection("bulletins")
                .whereEqualTo("visible", true)
                .get()
                .addOnCompleteListener(bulletinTask -> {
                    if (bulletinTask.isSuccessful()) {
                        productList.clear();
                        for (QueryDocumentSnapshot bulletinDocument : bulletinTask.getResult()) {
                            Product product = bulletinDocument.toObject(Product.class);
                            productList.add(product);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        // Handle the failure
                    }
                });
    }

    private void approveBulletin(String bulletinId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("bulletins").document(bulletinId)
                .update("visible", true)
                .addOnSuccessListener(aVoid -> {
                    // 게시글이 성공적으로 허용되었습니다.
                    // 필요한 추가 동작을 여기에 작성하세요.
                })
                .addOnFailureListener(e -> {
                    // 게시글을 허용하는데 실패했습니다.
                    // 실패 사유를 사용자에게 알리거나, 로그를 남기세요.
                });
    }

    private void disapproveBulletin(String bulletinId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("bulletins").document(bulletinId)
                .update("visible", false)
                .addOnSuccessListener(aVoid -> {
                    // 게시글이 성공적으로 비허용되었습니다.
                    // 필요한 추가 동작을 여기에 작성하세요.
                })
                .addOnFailureListener(e -> {
                    // 게시글을 비허용하는데 실패했습니다.
                    // 실패 사유를 사용자에게 알리거나, 로그를 남기세요.
                });
    }
}