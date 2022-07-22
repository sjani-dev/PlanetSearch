package com.geektrust.planetsearch.viewmodel;

import android.content.Context;

import com.geektrust.planetsearch.api.APIInterface;
import com.geektrust.planetsearch.model.PlanetDetails;
import com.geektrust.planetsearch.util.RetrofitAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ViewModel extends Observable {

    private PlanetDetails mVersion;
    private Context mContext;
    private List<PlanetDetails> dataList;



    public ViewModel(Context mContext) {
        this.mContext = mContext;
        this.dataList = new ArrayList<PlanetDetails>();
    }

    public void getPlanetList(Observer mObserver) {

        new RetrofitAPI().getRetrofit().create(APIInterface.class)
                .getPlanetList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(mObserver);

    }

    public void updateVersionDataList(List<PlanetDetails> list) {
        dataList = list;
    }

    public List<PlanetDetails> getDataList() {
        return dataList;
    }


    public boolean hasData(){
         if(getDataList()!=null && getDataList().size()>0)
             return true;
         return false;
    }




}
