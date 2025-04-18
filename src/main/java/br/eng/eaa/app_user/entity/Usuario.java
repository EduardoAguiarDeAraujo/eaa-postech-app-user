package br.eng.eaa.app_user.entity;

import br.eng.eaa.app_user.enums.Perfil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Nome não pode ser vazio")
    @Column(nullable = false)
    private String nome;
    @NotNull(message = "Email não pode ser nulo")
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull(message = "Login não pode ser nulo")
    @Column(nullable = false, unique = true)
    private String login;
    @NotNull(message = "Senha não pode ser nula")
    @Column(nullable = false)
    private String senha;
    private LocalDateTime dataAtualizacao;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String login, String senha, LocalDateTime dataAtualizacao, Endereco endereco, Perfil perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataAtualizacao = dataAtualizacao;
        this.endereco = endereco;
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
