package com.luisotinianodavila.movieclean.platform.view.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luisotinianodavila.movieclean.R;
import com.luisotinianodavila.movieclean.domain.model.Movie;
import com.luisotinianodavila.movieclean.presentation.presenters.MovieMVP;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final MovieMVP.Presenter presenter;
    private final List<Movie> movies;

    /* for folding cell */
    FoldingCell cell;

    public MoviesAdapter(MovieMVP.Presenter presenter){
        this.presenter = presenter;
        this.movies = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_movie, viewGroup, false);
        /* for folding cell */
        cell = (FoldingCell) view;
        return new MoviesViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MoviesViewHolder searchViewHolder = (MoviesViewHolder) viewHolder;

        /* for folding cell */
        // para el estado válido válido del conjunto de celdas existente (sin animación)
        if (searchViewHolder.getUnfoldedIndexes().contains(i)) {
            cell.unfold(true);
        } else {
            cell.fold(true);
        }
        /* fin for folding cell */

        presenter.configureImage(searchViewHolder, i);
        presenter.configureTitulo(searchViewHolder, i);
        presenter.configureFecha(searchViewHolder, i);
        presenter.configurePopularidad(searchViewHolder, i);
        presenter.configurePromedio(searchViewHolder, i);
        presenter.configureVoteAverage(searchViewHolder, i);
        presenter.configureVoto_contador(searchViewHolder, i);
        presenter.configureOverview(searchViewHolder, i);
        presenter.configureBotonLeerMas(searchViewHolder, i);

        presenter.onItemClick(searchViewHolder, i);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void addAll(Collection<Movie> collection) {
        movies.addAll(collection);
    }

    public void clean(){
        this.movies.clear();
        notifyDataSetChanged();
    }
}
