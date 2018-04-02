package com.example.kawag.appmaster2;

public class Livro {
    String livroId;
    String livroName;
    String livroGenre;

    public Livro() {
    }

    public Livro(String livroId, String livroName, String livroGenre) {
        this.livroId = livroId;
        this.livroName = livroName;
        this.livroGenre = livroGenre;
    }

    public String getLivroId() {
        return livroId;
    }

    public void setLivroId(String livroId) {
        this.livroId = livroId;
    }

    public String getLivroName() {
        return livroName;
    }

    public void setLivroName(String livroName) {
        this.livroName = livroName;
    }

    public String getLivroGenre() {
        return livroGenre;
    }

    public void setLivroGenre(String livroGenre) {
        this.livroGenre = livroGenre;
    }
}
