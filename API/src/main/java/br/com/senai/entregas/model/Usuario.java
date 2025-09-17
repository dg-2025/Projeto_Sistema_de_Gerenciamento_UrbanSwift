package br.com.senai.entregas.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Id do usuario
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "nomeCompleto", nullable = false, columnDefinition = "TEXT")
    private String nomeCompleto;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    @Column(name = "senha", nullable = false, columnDefinition = "TEXT")
    private String senha;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tipo_usuario_id" )
    private TipoUsuario tipoUsuario;


}
