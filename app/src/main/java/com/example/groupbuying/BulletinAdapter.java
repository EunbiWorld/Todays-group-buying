package com.example.groupbuying;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BulletinAdapter extends RecyclerView.Adapter<BulletinAdapter.BulletinViewHolder> {

    private List<Bulletin> bulletinList;
    private boolean isAdmin;  // 관리자 여부를 나타내는 필드

    // 생성자에 isAdmin 매개변수를 추가합니다.
    public BulletinAdapter(List<Bulletin> bulletinList, boolean isAdmin) {
        this.bulletinList = bulletinList;
        this.isAdmin = isAdmin;
    }

    @NonNull
    @Override
    public BulletinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bulletin_item, parent, false);
        return new BulletinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BulletinViewHolder holder, int position) {
        Bulletin bulletin = bulletinList.get(position);

        // 관리자이거나 visible 필드가 true인 경우에만 게시물을 표시합니다.
        if (bulletin.isVisible() || isAdmin) {
            // 데이터 바인딩
            holder.productNameTextView.setText(bulletin.getProductName());
            holder.productPriceTextView.setText(String.valueOf(bulletin.getPrice()));
            holder.productDescriptionTextView.setText(bulletin.getDescription());

            Glide.with(holder.productImageView.getContext()).load(bulletin.getImageUrl()).into(holder.productImageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), BulletinInfoActivity.class);
                    intent.putExtra("bulletin", bulletin);
                    v.getContext().startActivity(intent);
                }
            });
        }
        else {
            holder.itemView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return bulletinList.size();
    }

    // ViewHolder 클래스
    public class BulletinViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;  // 제품 이미지를 표시하는 ImageView
        TextView productNameTextView;  // 제품 이름을 표시하는 TextView
        TextView productPriceTextView;  // 제품 가격을 표시하는 TextView
        TextView productDescriptionTextView;  // 제품 설명을 표시하는 TextView

        public BulletinViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.product_image);
            productNameTextView = itemView.findViewById(R.id.product_name);
            productPriceTextView = itemView.findViewById(R.id.product_price);
            productDescriptionTextView = itemView.findViewById(R.id.product_description);
        }
    }
}


