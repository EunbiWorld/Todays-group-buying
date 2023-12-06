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

import com.example.groupbuying.Bulletin;
import com.example.groupbuying.BulletinAdapter;
import com.example.groupbuying.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class BulletinFragment extends Fragment {

    private RecyclerView rvBulletin;
    private BulletinAdapter adapter;
    private List<Bulletin> bulletinList;

    public BulletinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bulletin, container, false);

        rvBulletin = view.findViewById(R.id.rv_bulletin);
        rvBulletin.setLayoutManager(new LinearLayoutManager(getContext()));
        bulletinList = new ArrayList<>();

        loadBulletins();

        return view;
    }

    private void loadBulletins() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        boolean isAdmin = false;  // 기본값은 false입니다.

        if (currentUser != null) {
            String uid = currentUser.getUid();
            String adminUid = "5Y8iPYqWzOfBbu8Gk1QMZyMnP0f2";

            if (uid.equals(adminUid)) {
                isAdmin = true;
            }
        }

        adapter = new BulletinAdapter(bulletinList, isAdmin);  // 관리자 여부를 생성자에 전달합니다.
        rvBulletin.setAdapter(adapter);

        if (isAdmin) {
            // 관리자인 경우, 모든 게시글을 보여줍니다.
            db.collection("bulletins")
                    .get()
                    .addOnCompleteListener(bulletinTask -> {
                        if (bulletinTask.isSuccessful()) {
                            bulletinList.clear();
                            for (QueryDocumentSnapshot bulletinDocument : bulletinTask.getResult()) {
                                Bulletin bulletin = bulletinDocument.toObject(Bulletin.class);
                                bulletinList.add(bulletin);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            // Handle the failure
                        }
                    });
        } else {
            // 일반 사용자인 경우, 또는 로그인하지 않은 경우 'visible' 필드가 true인 게시글만 보여줍니다.
            db.collection("bulletins")
                    .whereEqualTo("visible", true)
                    .get()
                    .addOnCompleteListener(bulletinTask -> {
                        if (bulletinTask.isSuccessful()) {
                            bulletinList.clear();
                            for (QueryDocumentSnapshot bulletinDocument : bulletinTask.getResult()) {
                                Bulletin bulletin = bulletinDocument.toObject(Bulletin.class);
                                bulletinList.add(bulletin);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            // Handle the failure
                        }
                    });
        }
    }
}
