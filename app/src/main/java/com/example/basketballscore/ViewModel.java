package com.example.basketballscore;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

public class ViewModel extends AndroidViewModel {
    private int aback, bback;
    SavedStateHandle handle;
    String shpname = getApplication().getResources().getString(R.string.shp_name);
    String Ascore = getApplication().getResources().getString(R.string.temaA);
    String Bscore = getApplication().getResources().getString(R.string.temaB);

    public ViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        if (!handle.contains(Ascore) && !handle.contains(Bscore)) {
            load();
        }
    }

    public void load() {
        SharedPreferences shp = getApplication().getSharedPreferences(shpname, Context.MODE_PRIVATE);
        int a = shp.getInt(Ascore, 0);
        int b = shp.getInt(Bscore, 0);
        handle.set(Ascore, a);
        handle.set(Bscore, b);
    }

    public void save() {
        SharedPreferences shp = getApplication().getSharedPreferences(shpname, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(Ascore, getaTeamScore().getValue());
        editor.apply();
        editor.putInt(Bscore, getbTeamScore().getValue());
        editor.apply();
    }

    public MutableLiveData<Integer> getaTeamScore() {
        if (Ascore == null) {
            handle.set(Ascore, 0);
        }
        return handle.getLiveData(Ascore);


    }

    public MutableLiveData<Integer> getbTeamScore() {
        if (Bscore == null) {
            handle.set(Bscore, 0);
        }
        return handle.getLiveData(Bscore);
    }

    public void ateamAdd(int n) {
        aback = handle.get(Ascore);
        bback = handle.get(Bscore);
        handle.set(Ascore, getaTeamScore().getValue() + n);
    }

    public void bteamAdd(int n) {
        aback = handle.get(Ascore);
        bback = handle.get(Bscore);
        handle.set(Bscore, getaTeamScore().getValue() + n);
    }

    public void reset() {
        aback = handle.get(Ascore);
        bback = handle.get(Bscore);
        handle.set(Ascore, 0);
        handle.set(Bscore, 0);
    }

    public void undo() {
        handle.set(Ascore, aback);
        handle.set(Bscore, bback);
    }

}
