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
import java.util.Random;

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

        // 카테고리 'TextView'를 클릭 리스너와 연결
        setCategoryClickListener(view, R.id.textFood, "Food");
        setCategoryClickListener(view, R.id.textChild, "Child");
        setCategoryClickListener(view, R.id.textFurniture, "Furniture");
        setCategoryClickListener(view, R.id.textLife, "Life");
        setCategoryClickListener(view, R.id.textSport, "Sports");
        setCategoryClickListener(view, R.id.textTravel, "Travel");
        setCategoryClickListener(view, R.id.textNew, null);
        setCategoryClickListener(view, R.id.textRecommended, null);
        setCategoryClickListener(view, R.id.textBest, null);

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

        // 처음에는 추천 상품을 보여줍니다.
        showRandomProducts(20);

        return view;
    }

    private void setCategoryClickListener(View view, int textViewId, String category) {
        TextView textView = view.findViewById(textViewId);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category != null) {
                    showCategoryProducts(category);
                } else {
                    showRandomProducts(20);
                }
            }
        });
    }

    private void showCategoryProducts(String category) {
        db.collection("products")
                .whereEqualTo("category", category)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            setProductList(task);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void showRandomProducts(int count) {
        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Product> allProducts = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Product product = document.toObject(Product.class);
                                allProducts.add(product);
                            }

                            // 모든 상품 중에서 랜덤하게 상품을 선택
                            List<Product> selectedProducts = new ArrayList<>();
                            Random rand = new Random();
                            for (int i = 0; i < count && !allProducts.isEmpty(); i++) {
                                int randomIndex = rand.nextInt(allProducts.size());
                                selectedProducts.add(allProducts.remove(randomIndex));
                            }

                            // 선택된 상품들을 RecyclerView에 표시
                            productAdapter = new ProductAdapter(getActivity(), selectedProducts, R.layout.product_item);
                            productList.setAdapter(productAdapter);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void searchProducts(String query) {
        db.collection("products")
                .whereEqualTo("productName", query)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            setProductList(task);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void setProductList(@NonNull Task<QuerySnapshot> task) {
        List<Product> products = new ArrayList<>();
        for (QueryDocumentSnapshot document : task.getResult()) {
            Product product = document.toObject(Product.class);
            products.add(product);
        }
        productAdapter = new ProductAdapter(getActivity(), products, R.layout.product_item);
        productList.setAdapter(productAdapter);
    }
}
