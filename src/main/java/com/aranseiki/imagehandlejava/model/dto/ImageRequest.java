package com.aranseiki.imagehandlejava.model.dto;


public class ImageRequest {
    private String imagemAtualBase64;
    private String caminhoImagemNova;
    private String tipoImagem;
    private Integer resolucaoHorizontal;
    private Integer resolucaoVertical;

    // Getters e setters
    public String getImagemAtualBase64() {
        return imagemAtualBase64;
    }

    public void setImagemAtualBase64(String imagemAtualBase64) {
        this.imagemAtualBase64 = imagemAtualBase64;
    }

    public String getCaminhoImagemNova() {
        return caminhoImagemNova;
    }

    public void setCaminhoImagemNova(String caminhoImagemNova) {
        this.caminhoImagemNova = caminhoImagemNova;
    }

    public String getTipoImagem() {
        return tipoImagem;
    }

    public void setTipoImagem(String tipoImagem) {
        this.tipoImagem = tipoImagem;
    }

    public Integer getResolucaoHorizontal() {
        return resolucaoHorizontal;
    }

    public void setResolucaoHorizontal(Integer resolucaoHorizontal) {
        this.resolucaoHorizontal = resolucaoHorizontal;
    }

   public Integer getResolucaoVertical() {
        return resolucaoVertical;
    }

    public void setResolucaoVertical(Integer resolucaoVertical) {
        this.resolucaoVertical = resolucaoVertical;
    }
}
