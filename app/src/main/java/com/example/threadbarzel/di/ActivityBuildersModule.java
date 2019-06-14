package com.example.threadbarzel.di;

import com.example.threadbarzel.di.main.MainModule;
import com.example.threadbarzel.di.main.MainScope;
import com.example.threadbarzel.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainModule.class}
    )
    abstract MainActivity contributeMainActivity();

}