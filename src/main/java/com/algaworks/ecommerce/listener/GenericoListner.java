package com.algaworks.ecommerce.listener;

import jakarta.persistence.PostLoad;

public class GenericoListner {

    @PostLoad
    public void logaCarregamento(Object obj) {
        System.out.println("Entidade " + obj.getClass().getSimpleName() + " foi carregada.");
    }
}
