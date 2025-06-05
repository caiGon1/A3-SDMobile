package br.anhembi.notificacao.model;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import java.time.LocalDate;

@Entity
@Table(name = "bdVerificacao")
public class model {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long notifId;


private Long userId;

private LocalDate data;

private double valorSaida;


public model() {
}
public model(Long userId, Long notifId, LocalDate data, double valorSaida) {
    this.userId = userId;
    this.notifId = notifId;
    this.data = data;
    this.valorSaida = valorSaida;
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
public Long  getUserId() {
    return userId;
}
public void setUserId(Long userId) {
    this.userId = userId;
}
public Long getNotifId() {
    return notifId;
}
public void setNotifId(Long notifId) {
    this.notifId = notifId;
}


}