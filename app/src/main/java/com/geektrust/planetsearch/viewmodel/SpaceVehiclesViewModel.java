package com.geektrust.planetsearch.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.geektrust.planetsearch.api.APIInterface;
import com.geektrust.planetsearch.model.PlanetDetails;
import com.geektrust.planetsearch.model.VehicleDetails;
import com.geektrust.planetsearch.util.RetrofitAPI;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SpaceVehiclesViewModel extends Observable {
    // TODO: Implement the ViewModel

        private VehicleDetails mVersion;
        private Context mContext;
        private List<VehicleDetails> dataList;


    public SpaceVehiclesViewModel() {
    }
    public SpaceVehiclesViewModel(Context mContext) {
            this.mContext = mContext;
            this.dataList = new ArrayList<VehicleDetails>();
        }

        public void getVehicleListList (Observer mObserver){

            new RetrofitAPI().getRetrofit().create(APIInterface.class)
                    .getVehicleList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(mObserver);

        }

        public void updateVersionDataList (List < VehicleDetails > list) {
            dataList = list;
        }

        public List<VehicleDetails> getDataList () {
            return dataList;
        }
}