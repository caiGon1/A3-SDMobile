package br.anhembi.notificacao.dto;

import java.time.LocalDate;

public class dto {
private Long userId;
private Long notifId;
private LocalDate data;
private double valorSaida;



public Long getNotifId() {
    return notifId;
}
public void setNotifId(Long notifId) {
    this.notifId = notifId;
}
public LocalDate getData() {
    return data;
}
public void setData(LocalDate data) {
    this.data = data;
}
public double getValorSaida() {
    return valorSaida;
}
public void setValorSaida(double valorSaida) {
    this.valorSaida = valorSaida;
}
public Long getUserId() {
    return userId;
}
public void setUserId(Long userId) {
    this.userId = userId;
}


public dto() {
}
public dto(Long notifId, LocalDate data, double valorSaida, Long userId) {
    this.userId = userId;
    this.notifId = notifId;
    this.data = data;
    this.valorSaida = valorSaida;
}








}
