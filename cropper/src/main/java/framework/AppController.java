package framework;

import android.app.Application;
import android.text.TextUtils;

import com.jcminarro.philology.Philology;
import com.jcminarro.philology.PhilologyInterceptor;
import com.jcminarro.philology.PhilologyRepository;
import com.jcminarro.philology.PhilologyRepositoryFactory;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

import androidx.multidex.MultiDexApplication;
import io.github.inflationx.viewpump.ViewPump;
import manager.CachingManager;

public class AppController extends MultiDexApplication {
    private static AppController mInstance;
    private  Application application;

    public AppController(Application app) {
        this.application =app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CachingManager.setApplicationContext(application);
        PhilologyRepositoryFactory repositoryFactory = new GMPhilologyRepositoryFactory();
        Philology.INSTANCE.init(repositoryFactory);
        ViewPump.init(ViewPump.builder().addInterceptor(PhilologyInterceptor.INSTANCE).build());


    }




    class GMPhilologyRepositoryFactory implements PhilologyRepositoryFactory {
        @Nullable
        @Override
        public PhilologyRepository getPhilologyRepository(@NotNull Locale locale) {
            if (Locale.ENGLISH.getLanguage().equals(locale.getLanguage())) {
                return new GMPhilologyRepository();
            }
            return null;
        }
    }

    class GMPhilologyRepository implements PhilologyRepository {


        @Nullable
        @Override
        public CharSequence getPlural(@NotNull String s, @NotNull String s1) {
            return null;
        }

        @Nullable
        @Override
        public CharSequence getText(@NotNull String s) {

            return CachingManager.getLocaleString().get(s);
        }

        @Nullable
        @Override
        public CharSequence[] getTextArray(@NotNull String s) {
            return null;
        }



    }
}

