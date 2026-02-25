package com.example.voo.adapter.voo;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "voo")
public class JpaVooEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String origem;
    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer assentos_totais;

    @Column(nullable = false)
    private Integer assentos_disponiveis;


    protected JpaVooEntity() {}

    public JpaVooEntity(Long id, String codigo, String origem, String destino, LocalDateTime data_hora, BigDecimal preco, Integer assentos_totais,
                        Integer assentos_disponiveis) {
        this.id = id;
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.dataHora = data_hora;
        this.preco = preco;
        this.assentos_totais = assentos_totais;
        this.assentos_disponiveis = assentos_disponiveis;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getAssentos_totais() {
        return assentos_totais;
    }

    public void setAssentos_totais(Integer assentos_totais) {
        this.assentos_totais = assentos_totais;
    }

    public Integer getAssentos_disponiveis() {
        return assentos_disponiveis;
    }

    public void setAssentos_disponiveis(Integer assentos_disponiveis) {
        this.assentos_disponiveis = assentos_disponiveis;
    }
}
