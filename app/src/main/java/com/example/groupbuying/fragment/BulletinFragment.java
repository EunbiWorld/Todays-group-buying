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
        adapter = new ProductAdapter(getContext(), productList);
        rvBulletin.setAdapter(adapter);

        loadBulletins();

        return view;
    }

    private void loadBulletins() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("bulletins")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        productList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Product product = document.toObject(Product.class);
                            productList.add(product);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        // Handle the failure
                    }
                });
    }
}
