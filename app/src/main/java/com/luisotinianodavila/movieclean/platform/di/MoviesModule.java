package com.luisotinianodavila.movieclean.platform.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.luisotinianodavila.movieclean.data.entity.mapper.EntityDataMapper;
import com.luisotinianodavila.movieclean.data.net.MoviesService;
import com.luisotinianodavila.movieclean.data.repository.MoviesRepository;
import com.luisotinianodavila.movieclean.data.repository.MoviesRepositoryImpl;
import com.luisotinianodavila.movieclean.data.source.remote.MoviesDataRemote;
import com.luisotinianodavila.movieclean.data.source.remote.MoviesDataRemoteImpl;
import com.luisotinianodavila.movieclean.domain.interactor.UseCaseFactory;
import com.luisotinianodavila.movieclean.presentation.presenters.DetailMoviePresenter;
import com.luisotinianodavila.movieclean.presentation.presenters.MovieMVP;
import com.luisotinianodavila.movieclean.presentation.presenters.Presenter;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MoviesModule {
    public final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final int TIMEOUT = 20;

    @Provides
    MovieMVP.Presenter providePresenter(UseCaseFactory useCaseFactory){
        return new Presenter(useCaseFactory);
    }

    @Provides
    MovieMVP.PresenterMovieDetail provideDetailMoviePresenter(UseCaseFactory useCaseFactory){
        return new DetailMoviePresenter(useCaseFactory);
    }

    @Provides
    MoviesRepository provideMoviesRepositoryImpl(MoviesDataRemote dataRemote, EntityDataMapper entityDataMapper){
        return new MoviesRepositoryImpl(dataRemote, entityDataMapper);
    }

    @Provides
    MoviesDataRemote providesMoviesDataRemoteImpl(MoviesService service){
        return new MoviesDataRemoteImpl(service);
    }

    @Provides
    public OkHttpClient provideClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("api_key", "AQUI_TU_API_KEY").build();
                request = request.newBuilder().url(url)
                        .build();
                return chain.proceed(request);
            }
        })
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    Gson provideGsonSearch() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, (JsonDeserializer<Date>)
                        (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()))
                .create();
    }

    @Provides
    public Retrofit provideRetrofit(String baseUr, OkHttpClient client, Gson gson){
        return new Retrofit.Builder()
                .baseUrl(baseUr)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public MoviesService provideRequestApiSeacrh(){
        return provideRetrofit(BASE_URL, provideClient(), provideGsonSearch()).create(MoviesService.class);
    }
}
