package com.aamirchoksi.wombatreddit.di

import android.app.Application
import com.aamirchoksi.wombatreddit.WombatRedditApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBuilder::class,
        AndroidInjectionModule::class,
        RetrofitModule::class,
        DataModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: WombatRedditApplication)
}