package com.luisotinianodavila.movieclean.platform.view.activity;

import android.app.SearchManager;
import android.content.Context;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.luisotinianodavila.movieclean.R;
import com.luisotinianodavila.movieclean.domain.model.Movie;
import com.luisotinianodavila.movieclean.platform.di.App;
import com.luisotinianodavila.movieclean.platform.navigation.Navigator;
import com.luisotinianodavila.movieclean.platform.view.adapter.MoviesAdapter;
import com.luisotinianodavila.movieclean.platform.view.base.BaseActivity;
import com.luisotinianodavila.movieclean.presentation.presenters.MovieMVP;
import com.luisotinianodavila.movieclean.presentation.presenters.Presenter;

import java.util.List;

import javax.inject.Inject;

public class MovieList extends BaseActivity implements MovieMVP.View {

    @Inject
    Presenter presenter;

    MoviesAdapter adapter;

    RecyclerView recyclerView;

    ProgressBar progressBar;

    SearchView searchView;

    @Override
    public void initView() {

        this.initializeDagger();

        presenter.setView(this);

        initializeAdapter();
        initializeRecyclerView();
    }

    @Override
    public void initElementos(){
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
    }

    private void viewReady(){
        presenter.viewReady(searchView);
    }

    private void initializeDagger() {
        ((App) getApplication()).getComponent().inject(this);
    }

    private void initializeAdapter() {
        adapter = new MoviesAdapter(presenter);
    }

    private void initializeRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.requestFocus();

        this.viewReady();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    @Override
    public void cleanAdapter() {
        adapter.clean();
    }

    @Override
    public void refreshList() {
        this.viewReady();
    }

    @Override
    public void mostrarMovie(List<Movie> list) {
        adapter.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void navigateToDetailMovie(int movieId) {
        Navigator navigator = new Navigator();
        navigator.navigateToMovieDetails(this, movieId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
