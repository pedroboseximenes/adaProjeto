package com.ada.companhia_aerea.adapter.passagem;
import com.ada.companhia_aerea.adapter.voo.JpaVooEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "passagem")
public class JpaPassagemEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voo_id", nullable = false)
    private JpaVooEntity voo;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime data_compra;


    protected JpaPassagemEntity() {}

    public JpaPassagemEntity(Long id, JpaVooEntity voo, Long user_id, String nome_passageiro, String email_passageiro) {
        this.id = id;
        this.voo = voo;
        this.user_id = user_id;
        this.nome = nome_passageiro;
        this.email = email_passageiro;
        this.data_compra = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public JpaVooEntity getVoo() {
        return voo;
    }

    public void setVoo(JpaVooEntity voo) {
        this.voo = voo;
    }

    public LocalDateTime getData_compra() {
        return data_compra;
    }

    public void setData_compra(LocalDateTime data_compra) {
        this.data_compra = data_compra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
