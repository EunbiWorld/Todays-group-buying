package com.example.groupbuying.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groupbuying.ProductInfoActivity;
import com.example.groupbuying.R;

import java.text.NumberFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;
    private int layoutId; // 추가


    public ProductAdapter(Context context, List<Product> productList, int layoutId) {   //추가
        this.context = context;
        this.productList = productList;
        this.layoutId = layoutId; // 추가

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);  //수정함
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getProductName());

        int price = Integer.parseInt(product.getPrice().replaceAll(",", ""));
        String formattedPrice = NumberFormat.getInstance().format(price);

        holder.productPrice.setText(formattedPrice + "원");

        // Glide를 사용하여 이미지 로드
        Glide.with(context).load(product.getImageUrl()).into(holder.productImage);

        // 아이템 클릭 리스너 설정
        // 아이템 클릭 리스너 설정
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductInfoActivity.class);
                intent.putExtra("product", product); // 'Product' 클래스가 'Serializable' 또는 'Parcelable'을 구현
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        TextView productDescription;
        ImageView productImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productDescription = itemView.findViewById(R.id.product_description);
            productImage = itemView.findViewById(R.id.product_image);  // 새로 추가된 코드
        }
    }

}
