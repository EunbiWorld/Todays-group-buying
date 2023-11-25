package com.example.groupbuying.fragment;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupbuying.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView productList;
    private ProductAdapter productAdapter;
    private HorizontalScrollView categoryScrollView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        productList = view.findViewById(R.id.product_list);
        productList.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        categoryScrollView = (HorizontalScrollView) view.findViewById(R.id.category_scroll_view);

        EditText searchBar = view.findViewById(R.id.search_bar);

        // 각 카테고리에 대해 클릭 리스너를 설정
        TextView textFood = view.findViewById(R.id.textFood);
        textFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // '요리' 카테고리가 클릭되었을 때의 동작
                showCategoryProducts("Food");
            }
        });

        // 'Child' 카테고리에 대한 클릭 리스너 설정
        TextView textChild = view.findViewById(R.id.textChild);
        textChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryProducts("Child");
            }
        });

// 'Furniture' 카테고리에 대한 클릭 리스너 설정
        TextView textFurniture = view.findViewById(R.id.textFurniture);
        textFurniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryProducts("Furniture");
            }
        });

// 'Life' 카테고리에 대한 클릭 리스너 설정
        TextView textLife = view.findViewById(R.id.textLife);
        textLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryProducts("Life");
            }
        });

// 'Sports' 카테고리에 대한 클릭 리스너 설정
        TextView textSports = view.findViewById(R.id.textSport);
        textSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryProducts("Sports");
            }
        });

// 'Travel' 카테고리에 대한 클릭 리스너 설정
        TextView textTravel = view.findViewById(R.id.textTravel);
        textTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryProducts("Travel");
            }
        });


        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchProducts(v.getText().toString());

                    // 카테고리 스크롤 뷰를 숨김
                    categoryScrollView.setVisibility(View.GONE);
                    return true;
                }
                return false;
            }
        });
        loadProducts();
        return view;
    }

    private void loadProducts() {
        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Product> products = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Product product = document.toObject(Product.class);
                                products.add(product);
                            }
                            productAdapter = new ProductAdapter(getActivity(), products);
                            productList.setAdapter(productAdapter);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void showCategoryProducts(String category) {
        db.collection("products")
                .whereEqualTo("category", category) // 'category'가 카테고리를 저장한 필드라고 가정합니다.
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Product> products = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Product product = document.toObject(Product.class);
                                products.add(product);
                            }
                            productAdapter = new ProductAdapter(getActivity(), products);
                            productList.setAdapter(productAdapter);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void searchProducts(String query) {
        db.collection("products")
                .whereEqualTo("productName", query) // 'productName'이 상품 이름을 저장한 필드라고 가정합니다.
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Product> products = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Product product = document.toObject(Product.class);
                                products.add(product);
                            }
                            productAdapter = new ProductAdapter(getActivity(), products);
                            productList.setAdapter(productAdapter);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
