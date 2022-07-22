package com.geektrust.planetsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Button;

import com.geektrust.planetsearch.databinding.ActivityMainBinding;
import com.geektrust.planetsearch.model.PlanetDetails;
import com.geektrust.planetsearch.model.PlanetListResposne;
import com.geektrust.planetsearch.util.Logger;
import com.geektrust.planetsearch.viewmodel.ViewModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ViewModel viewModel;
    private DisposableObserver mGetAndroidVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setUpView();
    }


    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModel(this);
        binding.setAndroidVersionViewModel(viewModel);
    }
    private void setUpView() {
    }
    private void getPlanetList() {
        mGetAndroidVersion = new DisposableObserver<List<PlanetDetails>>() {

            @Override
            public void onNext(List<PlanetDetails> data) {
                if (data != null && data.size() > 0) {
                    viewModel.updateVersionDataList(data);
                    binding.setAndroidVersionViewModel(viewModel);
                    updateList();
                }
            }

            @Override
            public void onError(Throwable e) {
                Logger.d("Android error", e.getMessage());
            }
            @Override
            public void onComplete() {
                Logger.d("Android complete","done");
            }
        };

        viewModel.getPlanetList(mGetAndroidVersion);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPlanetList();
    }

    private void updateList() {
        viewModel.getDataList();
    }
}