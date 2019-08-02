package org.orechou.pocket;

import android.app.Application;

import org.litepal.LitePal;

public class PocketApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
