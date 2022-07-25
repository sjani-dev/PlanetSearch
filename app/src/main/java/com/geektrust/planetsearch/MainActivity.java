package com.geektrust.planetsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.geektrust.planetsearch.databinding.ActivityMainBinding;
import com.geektrust.planetsearch.model.PlanetDetails;
import com.geektrust.planetsearch.model.PlanetListResposne;
import com.geektrust.planetsearch.model.VehicleDetails;
import com.geektrust.planetsearch.util.Logger;
import com.geektrust.planetsearch.util.PlanetAdapter;
import com.geektrust.planetsearch.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ViewModel viewModel;
    private DisposableObserver mGetAndroidVersion;
    private PlanetAdapter planetAdapter;
    ArrayList<PlanetDetails> selected = new ArrayList<>();


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
        binding.btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSelected();
                Intent intent = new Intent(MainActivity.this, VehicleActivity.class);
                Bundle bundle =new Bundle();
                bundle.putParcelableArrayList("planetlist", (ArrayList<? extends Parcelable>) selected);
                startActivity(intent);
            }
        });
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
        try {
            viewModel.getDataList();
            ArrayList<PlanetDetails> selectedPlanet = new ArrayList<>();
            if(null!=viewModel.getDataList()) {
                planetAdapter = new PlanetAdapter(this, viewModel.getDataList(), getApplicationContext());
                GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
                binding.rvPlanetlist.setLayoutManager(layoutManager);
                binding.rvPlanetlist.setItemAnimator(new DefaultItemAnimator());
                binding.rvPlanetlist.setAdapter(planetAdapter);
                planetAdapter.setItemClick(new PlanetAdapter.OnItemClick() {

                    @Override
                    public void onItemClick(View view, VehicleDetails planetname, int position) {

                    }
                });
                //86.5 96.5
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PlanetDetails> getSelected() {
        for (int i = 0; i < viewModel.getDataList().size(); i++) {
            if (viewModel.getDataList().get(i).isSelected()) {
                selected.add(viewModel.getDataList().get(i));
            }
        }
        return selected;
    }
}