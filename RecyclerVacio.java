package com.softjk.waitapp.Metodos;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

public class ReciclerVacio {
    private final FirebaseFirestore BD;

    public ReciclerVacio(FirebaseFirestore BD) {
        this.BD = BD;
    }

    public interface Callback {
        void onResult(String result);
    }

    public void verificarRecycler(String Coleccion, RecyclerView.Adapter<?> mAdapter,Callback callback) {

        if (mAdapter.getItemCount() == 0) {
            BD.collection(Coleccion).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if (task.getResult().isEmpty()) {
                        callback.onResult("Vacio");
                    } else {
                        callback.onResult("NoVacio2");
                    }
                } else {
                    callback.onResult("Error al obtener los datos: " + task.getException());
                }
            });
        } else {
            callback.onResult("NoVacio");
        }
    }


    // llamar el Metodo
  /*  ReciclerVacio verRecycler = new ReciclerVacio(BD);
        verRecycler.verificarRecycler(MiColleccionBD, MiAdaptador,resultado -> {
        System.out.println(resultado);
    });                                                                                         */

}
