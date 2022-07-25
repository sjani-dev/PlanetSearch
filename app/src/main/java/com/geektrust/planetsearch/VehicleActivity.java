package com.geektrust.planetsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.geektrust.planetsearch.databinding.ActivityVehicleBinding;
import com.geektrust.planetsearch.model.PlanetDetails;
import com.geektrust.planetsearch.model.VehicleDetails;
import com.geektrust.planetsearch.util.Logger;
import com.geektrust.planetsearch.util.PlanetAdapter;
import com.geektrust.planetsearch.viewmodel.SpaceVehiclesViewModel;
import com.geektrust.planetsearch.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class VehicleActivity extends AppCompatActivity {

    private SpaceVehiclesViewModel mViewModel;
    private ActivityVehicleBinding binding;

    private DisposableObserver getVehiclesobserver;
    private PlanetAdapter planetAdapter;
    ArrayList<PlanetDetails> planetlist;

    public VehicleActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        planetlist = getIntent().getParcelableExtra("planetlist");
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vehicle);
        mViewModel = new SpaceVehiclesViewModel(this);
        binding.setVehicleViewModel(mViewModel);
    }
    private void getVehiclesList() {
        getVehiclesobserver = new DisposableObserver<List<VehicleDetails>>() {

            @Override
            public void onNext(List<VehicleDetails> data) {
                if (data != null && data.size() > 0) {
                    mViewModel.updateVersionDataList(data);
                    binding.setVehicleViewModel(mViewModel);
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

        mViewModel.getVehicleListList(getVehiclesobserver);
    }
    @Override
    public void onResume() {
        super.onResume();
        getVehiclesList();
    }
    private void updateList() {
        try {
            mViewModel.getDataList();
            if(null!=mViewModel.getDataList()) {
                planetAdapter = new PlanetAdapter(this, mViewModel.getDataList(), planetlist);
                GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
                binding.rvVehiclelist.setLayoutManager(layoutManager);
                binding.rvVehiclelist.setItemAnimator(new DefaultItemAnimator());
                binding.rvVehiclelist.setAdapter(planetAdapter);

                binding.rvPlanetlist.setLayoutManager(layoutManager);
                binding.rvPlanetlist.setItemAnimator(new DefaultItemAnimator());
                binding.rvPlanetlist.setAdapter(planetAdapter);


                planetAdapter.setItemClick(new PlanetAdapter.OnItemClick() {

                    @Override
                    public void onItemClick(View view, VehicleDetails planetname, int position) {
                        Intent intent = new Intent(VehicleActivity.this, FindFalconeActivity.class);
                        startActivity(intent);
                    }
                });
                //86.5 96.5
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}