package com.example.groupbuying.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.groupbuying.LoginActivity;
import com.example.groupbuying.R;

public class LoginRequestFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loginrequest, container, false);

        SpannableStringBuilder builder = new SpannableStringBuilder("로그인 후, '오늘공구' \n이용해주시길 바랍니다.");

        int start = builder.toString().indexOf("'오늘공구'");
        int end = start + "'오늘공구'".length();

        // '오늘공구' 부분의 크기를 키웁니다.
        builder.setSpan(new RelativeSizeSpan(1.5f), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // '오늘공구' 부분의 색깔을 변경합니다.
        int mainColor = getResources().getColor(R.color.mainColor);
        builder.setSpan(new ForegroundColorSpan(mainColor), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = view.findViewById(R.id.textView);
        textView.setText(builder);

        Button goLoginActivityButton = view.findViewById(R.id.GoLoginAcivityButton);
        goLoginActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }
}

