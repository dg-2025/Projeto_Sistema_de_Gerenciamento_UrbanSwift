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
@Table(name = "tipo_usuario")
public class TipoUsuario {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "tipo_usuario_id", nullable = false)
    private Integer tipoUsuarioId;

    @Column(name = "descricao",  nullable = false)
    private String descricao;


}
