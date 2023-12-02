package com.example.groupbuying.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.groupbuying.MainActivity;
import com.example.groupbuying.R;
import com.google.firebase.auth.FirebaseAuth;

public class SettingFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        TextView logoutButton = view.findViewById(R.id.LogoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutConfirmationDialog();
            }
        });

        return view;
    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("로그아웃 확인");
        builder.setMessage("로그아웃 하시겠습니까?");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 확인 버튼을 눌렀을 때 로그아웃 실행
                logout();
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 취소 버튼을 눌렀을 때 아무 동작 없음
            }
        });
        builder.show();
    }

    private void logout() {
        // Firebase 로그아웃 구현
        // FirebaseAuth를 사용하는 예시:
        FirebaseAuth.getInstance().signOut();

        // Toast 메시지 표시
        Toast.makeText(getActivity(), "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();

        // 로그아웃 후 MainActivity로 이동
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }

}