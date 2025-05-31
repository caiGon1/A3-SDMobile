package br.anhembi.centralalertas.dto;

import java.util.UUID;

public class AlertaDTO {
    private String tipo;
    private String mensagem;
    private String severidade;
    private String idosoId;

    // Getters
    public String getTipo() {
        return tipo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getSeveridade() {
        return severidade;
    }

    public String getIdosoId() {
        return idosoId;
    }

    // Setters
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setSeveridade(String severidade) {
        this.severidade = severidade;
    }

    public void setIdosoId(String idosoId) {
        this.idosoId = idosoId;
    }

    // Método que retorna o UUID convertido a partir do idosoId (string)
    public UUID getUserId() {
        if (idosoId == null || idosoId.isEmpty()) {
            return null; // ou lance exceção se preferir
        }
        return UUID.fromString(idosoId);
    }
}