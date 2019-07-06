package com.luisotinianodavila.movieclean.platform.view.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.luisotinianodavila.movieclean.R;
import com.luisotinianodavila.movieclean.presentation.MovieCellView;
import com.luisotinianodavila.movieclean.presentation.presenters.MovieMVP;
import com.ramotion.foldingcell.FoldingCell;
import com.squareup.picasso.Picasso;

import java.util.HashSet;

public class MoviesViewHolder extends RecyclerView.ViewHolder implements MovieCellView {
    private final MovieMVP.Presenter presenter;

    /* for folding cell */
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    /* fin for folding cell */

    ImageView thumbnail;
    TextView titulo, titulo2, voteAverage, fecha, fecha2, popularidad, voto_contador, promedio, overview, popularity2, vote_count2, average2;
    Button content_leermas;

    public MoviesViewHolder(@NonNull View itemView, @NonNull MovieMVP.Presenter presenter){
        super(itemView);
        this.presenter = presenter;

        //thumbnail = itemView.findViewById(R.id.imagen);
        thumbnail = itemView.findViewById(R.id.iv_imagen);

        //titulo = itemView.findViewById(R.id.titulo);
        titulo = itemView.findViewById(R.id.tv_title);

        titulo2 = itemView.findViewById(R.id.tv_titulo2);
        voteAverage = itemView.findViewById(R.id.text_voteAverage);
        average2 = itemView.findViewById(R.id.tv_vote_average2);
        fecha = itemView.findViewById(R.id.text_releaseDate);
        fecha2 = itemView.findViewById(R.id.text_releaseDate2);
        popularidad = itemView.findViewById(R.id.tv_popularity);
        popularity2 = itemView.findViewById(R.id.tv_popularity2);
        voto_contador = itemView.findViewById(R.id.tv_vote_count);
        vote_count2 = itemView.findViewById(R.id.tv_vote_count2);
        promedio = itemView.findViewById(R.id.tv_vote_average);
        overview = itemView.findViewById(R.id.text_overview);

        content_leermas = itemView.findViewById(R.id.content_leermas_btn);

        //ButterKnife.bind(this, itemView);
    }
    public HashSet<Integer> getUnfoldedIndexes(){
        return this.unfoldedIndexes;
    }
    @Override
    public void onItemClick(int i) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                /* for folding cell */
                try {
                    //((FoldingCell) v).initialize(30, 1000,-7829368,5);
                    ((FoldingCell) v).toggle(false);
                    registerToggle(i);
                }catch(Exception e){
                    Log.e("fondingcell", e.getMessage());
                }
                /* fin for folding cell */

                //presenter.navigateToDetailMovie(i);
            }
        });
    }

    /* for folding cell */
    // métodos simples para registrar cambios en el estado de la celda
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }
    /* fin for folding cell */

    @Override
    public void displayImage(String url) {
        Picasso.with(itemView.getContext())
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(thumbnail);
    }

    @Override
    public void displayTitulo(String name) {
        this.titulo.setText(name);
        this.titulo2.setText(name);
    }

    @Override
    public void displayVoteAverage(Double param) {
        this.voteAverage.setText(String.valueOf(param));
        this.average2.setText(String.valueOf(param));
    }

    @Override
    public void displayFecha(String param) {
        this.fecha.setText(param);
        this.fecha2.setText(param);
    }

    @Override
    public void displayPopularidad(Double param) {
        popularidad.setText(String.valueOf(param));
        popularity2.setText(String.valueOf(param));
    }

    @Override
    public void displayVoto_contador(Integer param) {
        voto_contador.setText(String.valueOf(param));
        vote_count2.setText(String.valueOf(param));
    }

    @Override
    public void displayPromedio(Double param) {
        promedio.setText(String.valueOf(param));
    }

    @Override
    public void displayOverview(String param) {
        overview.setText(param);
    }

    @Override
    public void displayBotonLeerMas(Integer posicion) {
        content_leermas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.navigateToDetailMovie(posicion);
            }
        });
    }

}
