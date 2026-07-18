package br.com.reireal.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class FechamentoResponse {
private UUID id;

private LocalDateTime dataFechamento;

private BigDecimal totalDinheiro;

private BigDecimal totalCartaoCredito;

private BigDecimal totalCartaoDebito;

private BigDecimal totalPix;

private BigDecimal creditoGerado;

private BigDecimal creditoUtilizado;

private BigDecimal totalGeral;

public FechamentoResponse(UUID id, LocalDateTime dataFechamento, BigDecimal totalDinheiro, BigDecimal totalCartaoCredito, BigDecimal totalCartaoDebito, BigDecimal totalPix, BigDecimal creditoGerado, BigDecimal creditoUtilizado, BigDecimal totalGeral) {
    this.id = id;
    this.dataFechamento = dataFechamento;
    this.totalDinheiro = totalDinheiro;
    this.totalCartaoCredito = totalCartaoCredito;
    this.totalCartaoDebito = totalCartaoDebito;
    this.totalPix = totalPix;
    this.creditoGerado = creditoGerado;
    this.creditoUtilizado = creditoUtilizado;
    this.totalGeral = totalGeral;
}
public UUID getId() {
    return id;
}
public LocalDateTime getDataFechamento() {
    return dataFechamento;
}
public BigDecimal getTotalDinheiro() {
    return totalDinheiro;
}
public BigDecimal getTotalCartaoCredito() {
    return totalCartaoCredito;
}
public BigDecimal getTotalCartaoDebito() {
    return totalCartaoDebito;
}
public BigDecimal getTotalPix() {
    return totalPix;
}
public BigDecimal getCreditoGerado() {
    return creditoGerado;
}
public BigDecimal getCreditoUtilizado() {
    return creditoUtilizado;
}
public BigDecimal getTotalGeral() {
    return totalGeral;
}
}
