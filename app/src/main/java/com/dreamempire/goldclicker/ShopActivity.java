package com.dreamempire.goldclicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.dreamempire.goldclicker.databinding.ActivityShopBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private ActivityShopBinding binding;
    private long count, score,sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        score = getIntent().getLongExtra("score",10_000);
        sum = getIntent().getLongExtra("sum",150);
        binding = ActivityShopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button4.setText(String.format("%d = %d",score,sum));
        binding.invalidateAll();
        SharedPreferences preferences = getSharedPreferences("prefs",MODE_PRIVATE);
        count = preferences.getLong("coins",0);
        binding.button4.setEnabled(count>=score);
        binding.button4.setOnClickListener(view -> {
            binding.button4.setEnabled(false);
            FirebaseFirestore
                    .getInstance()
                    .collection("main")
                    .document("out")
                    .get()
                    .addOnCompleteListener(task -> {
                        OutList list = task.getResult().toObject(OutList.class);
                        if(list==null || list.list==null) {
                            list = new OutList();
                            list.list = new ArrayList<>();
                        }
                        Out out = new Out(binding.edit.getText().toString(),sum);
                        list.list.add(out);
                        FirebaseFirestore
                                .getInstance()
                                .collection("main")
                                .document("out")
                                .set(list)
                                .addOnCompleteListener(task1 -> {
                                    count-=score;
                                    preferences.edit().putLong("coins",count).apply();
                                    binding.button4.setEnabled(count>=score);
                                });
                    });
        });
    }

}
class Out {
    String number;
    long sum;
    public Out() {

    }
    Out(String p, long n) {
        this.number = p;
        this.sum = n;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }
}
class OutList {

    List<Out> list;

    public OutList() {

    }

    public List<Out> getList() {
        return list;
    }

    public void setList(List<Out> list) {
        this.list = list;
    }
}